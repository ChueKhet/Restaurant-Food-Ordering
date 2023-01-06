<template>
    <div class="ma-3">
        <v-row
            style="
                margin-left: 5px;
                margin-top: 0px !important;
                margin-bottom: 20px;
            "
        >
            <v-title><h2>Sale Headers</h2></v-title>
        </v-row>
        <v-data-table
            class="elevation-4"
            :headers="tableHeaders"
            :items="saleHeaders"
            :items-per-page="5"
        >
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
                    @click="onClickDetail(item.id)"
                >
                    <v-icon>mdi-eye</v-icon>
                </v-btn>
            </template>
        </v-data-table>
    </div>
</template>

<script>
import utils from '@/utils/utils';

export default {
  name: 'SaleHeaders',

  components: {},

  data(){
    return {
      tableHeaders: [
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
          value: 'createdDate',
        },
        {
          text: 'Order Status',
          value: 'orderStatus',
        },
        {
          text: 'Actions',
          value: 'btn',
        },
      ],
      saleHeaders: [],
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
      }
      const userId=this.$store.state.userInfo?.id;
      const resp = await utils.http.get("/sale/headers"+"?userId="+userId);

      if(resp && resp.status == 200){
        const data = await resp.json();

        if(data){
          this.saleHeaders = data?.length>0?data.map(it=>({...it,orderStatus:STATUS[it.orderStatus]})):[];
        }
      }
    },

    async onClickDetail(headerId){
        utils.goToScreen("/sale_detail/"+headerId);
    }
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
