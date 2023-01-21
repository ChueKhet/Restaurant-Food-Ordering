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
        :search="search"
        :items-per-page="5">

          <template v-slot:item.orderStatus="{ item }">
            <div class="d-flex justify-center">
              <div :class="CssClass[item.orderStatus]">
                {{ STATUS[item.orderStatus] }}
              </div>
            </div>
          </template>

          <template v-slot:item.btn="{ item }">

            <v-btn class="mr-3"
              color="deep-purple lighten-1"
              fab
              x-small
              dark
              @click="onClickDetail(item.id)">
                <v-icon>mdi-eye</v-icon>
            </v-btn>

            <v-btn :disabled="(item.orderStatus.toString() != '0' || loginUser.role.toString() == '1') ? true : false"
              color="deep-purple lighten-1"
              fab x-small :dark="(item.orderStatus.toString() != '0' || loginUser.role.toString() == '1') ? false : true"
              @click="goToPayment(item)">
                <v-icon>mdi-credit-card</v-icon>
            </v-btn>

          </template>
      </v-data-table>
    </v-card>

    <v-dialog v-model="detailDialog" width="1000">
      <v-card>

        <v-card-title>
          Order Detail
        </v-card-title>

        <v-card-text>

          <v-data-table
            class="elevation-4"
            :headers="dTableHeaders"
            :items="saleDetails"
            :items-per-page="5">

              <template v-slot:item.status="{ item }">
                <div class="d-flex justify-center">
                  <div :class="item.orderStatus.toString() == '0' ? 'orderStatus1' : 'orderStatus3'">
                    {{item.orderStatus.toString() == "0" ? "Ordered" : "Served"}}
                  </div>
                </div>
              </template>
              
          </v-data-table>

          <div class="d-flex justify-end">
            <v-btn class="mt-5 width-100" color="success" @click="detailDialog = false">Cancel</v-btn>
          </div>
          
        </v-card-text>

      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import utils from '@/utils/utils';

export default {
  name: 'SaleHeaders',

  components: { },

  data(){
    return {
      loginUser: {},
      detailDialog: false,
      saleHeaderId: "",
      saleHeaders: [],
      saleDetails: [],
      search: "",

      STATUS: ["Order", "Sales", "Served"],
      CssClass: ["orderStatus1", "orderStatus2", "orderStatus3"],

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
          sortable: false,
        },
        { text: "Created By", 
          value: "userid" 
        },
        {
          text: 'Confirm Status',
          value: 'orderStatus',
          align: 'center',
          sortable: false,
        },
        {
          text: 'Actions',
          value: 'btn',
          align: 'center',
          sortable: false,
        },
      ],
      dTableHeaders: [
        {
          text: 'ID',
          value: 'id',
          sortable: false,
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
          align: 'center',
          sortable: false,
        },
      ],
    };
  },

  async created(){
    this.$store.watch(
      () => {
        return this.$store.state.loginUser;
      },
      (newVal, oldVal) => {
        this.loginUser = newVal;
      },
      {
        deep: true,
      }
    );

    this.loginUser = this.$store.state.loginUser;

    await this.fetchSaleHeaders();
  },

  methods: {
    async fetchSaleHeaders(){
      // const STATUS={
      //   0:'Order',
      //   1:'Sales',
      //   2:'Served'
      // };

      const userId=this.$store.state.userInfo?.id;
      const resp = await utils.http.get("/sale/headers");

      if(resp && resp.status == 200){
        const data = await resp.json();

        if(data){
          this.saleHeaders = data;
          // this.saleHeaders = data?.length > 0 ? data.map(
          //   it => ({...it, orderStatus:STATUS[it.orderStatus]})
          // ) : [];
        }
      }
    },

    async onClickDetail(headerId){
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

    goToPayment(item){
      let exportData = {
        headerData: item,
        isFromList: true,
        isNew: false
      };

      utils.goToScreenWithData("/payment", "payment", exportData);
    },
  },
}
</script>

<style>

.orderStatus1{
  width:fit-content;
  padding:5px 10px;
  border-radius:20px 20px;
  background-color:rgb(255, 0, 0);
  color:white;
}

.orderStatus2{
  width:fit-content;
  padding:5px 10px;
  border-radius:20px 20px;
  background-color:rgb(255, 170, 0);
  color:white;
}

.orderStatus3{
  width:fit-content;
  padding:5px 10px;
  border-radius:20px 20px;
  background-color:#4caf50;
  color:white;
}

</style>
