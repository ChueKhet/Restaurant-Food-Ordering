<template>
  <div class="ma-5">
    <v-card>
      <v-card-title class="d-flex justify-space-between">
        <span class="mr-5">
          Order List
        </span>

        <span style="width: 250px;">
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          ></v-text-field>
        </span>
      </v-card-title>

      <v-data-table
        :headers="hTableHeaders"
        :items="saleHeaders"
        :items-per-page="5">
          <template v-slot:item.orderStatus="{ item }">
            <div class="orderStatus">
              {{item.orderStatus}}
            </div>
          </template>
          <template v-slot:item.btn="{ item }">

            <v-btn
              color="red"
              fab
              x-small
              dark
              @click="onClickDetail(item.id)" >
                <v-icon>mdi-eye</v-icon>
            </v-btn>
          </template>
      </v-data-table>
    </v-card>

    <v-dialog v-model="detailDialog" width="1000">
      <v-data-table
        class="elevation-4"
        :headers="dTableHeaders"
        :items="saleDetails"
        :items-per-page="5">
          <template v-slot:item.status="{ item }">
            <div class="orderStatus">
              {{item.orderStatus.toString() == "0" ? "Ordered" : "Served"}}
            </div>
          </template>
          
      </v-data-table>
      <v-btn @click="detailDialog = false">Cancel</v-btn>
    </v-dialog>
  </div>
</template>

<script>
import utils from '@/utils/utils';
import saleDetail from '../components/SaleDetail.vue';

export default {
  name: 'SaleHeaders',

  components: {
    saleDetail
  },

  data(){
    return {
      detailDialog: false,
      saleHeaderId: "",
      saleHeaders: [],
      saleDetails: [],
      search: "",

      hTableHeaders: [
        {
          text: 'ID',
          value: 'id',
          sortable: true,
        },
        {
          text: 'Slip No',
          value: 'slipNo',
          sortable: true,
        },
        {
          text: 'Table No',
          value: 'tableNo',
          sortable: true,
        },
        {
          text: 'Total Amount',
          value: 'totalAmount',
        },
        {
          text: 'Created Date',
          value: 'createdAt',
        },
        {
          text: 'Confirm Status',
          value: 'orderStatus',
        },
        {
          text: 'Actions',
          value: 'btn',
        },
      ],
      dTableHeaders: [
        {
          text: 'ID',
          value: 'id',
          sortable: true,
        },
        {
          text: 'Code',
          value: 'menuCode',
          sortable: true,
        },
        {
          text: 'Menu',
          value: 'menuDesc',
          sortable: true,
        },
        {
          text: 'Qty',
          value: 'qty',
        },
        {
          text: 'Price',
          value: 'price',
        },
        {
          text: 'Total Price',
          value: 'totalPrice',
        },
        {
          text: 'Status',
          value: 'status',
        },
      ],
    };
  },

  async created(){
    await this.fetchSaleHeaders();
  },

  methods: {
    async fetchSaleHeaders(){
      const STATUS={
        0:'Order',
        1:'Sales',
        2:'Served'
      };

      const userId=this.$store.state.userInfo?.id;
      const resp = await utils.http.get("/sale/headers"+"?userId="+userId);

      if(resp && resp.status == 200){
        const data = await resp.json();

        if(data){
          this.saleHeaders = data?.length > 0 ? data.map(
            it => ({...it, orderStatus:STATUS[it.orderStatus]})
          ) : [];
        }
      }
    },

    async onClickDetail(headerId){
      // utils.goToScreen("/sale_detail/"+headerId);
      this.saleHeaderId = headerId;
      await this.fetchSaleDetail();

      this.detailDialog = true;
    },

    async fetchSaleDetail() {
      const resp = await utils.http.get(
        "/sale/detail?headerId=" + this.saleHeaderId
      );

      if (resp && resp.status == 200) {
        const data = await resp.json();

        if (data) {
          this.saleDetails = data;
        }
      }
    },
  },
}
</script>

<style>
.orderStatus{
  width:fit-content;
  padding:5px 10px;
  border-radius:20px 20px;
  background-color:red;
  color:white;
}
</style>
