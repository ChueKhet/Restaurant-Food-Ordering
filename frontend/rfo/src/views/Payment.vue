<template>
  <div class="ma-5">
    <v-card>
      <v-card-title>
        <div class="mr-5">
          Payment
        </div>
      </v-card-title>
      <v-card-title>
        <div class="mr-5 d-flex justify-space-between" style="width: 100%;">
          <label style="font-size: 1rem; margin-right: 5px;">Table No: {{ saleHeaders.tableNo }}</label>
          <label style="font-size: 1rem; margin-right: 5px;">Slip No: {{ saleHeaders.slipNo }}</label>
        </div>
      </v-card-title>
      

      <v-data-table
        :headers="tableHeaders"
        :items="this.saleHeaders.detailList"
        :hide-default-footer="true">
              
          <template v-slot:body.append>
            <tr>
              <td colspan="6" align="right">Total Amount</td>
              <td align="right">{{ totalAmount }}</td>
            </tr>
            <tr>
              <td colspan="6" align="right">Paid</td>
              <td align="right" style="padding-right: 0px;">
                <div style="width: 100px;">
                  <v-text-field type="number" v-model="paidAmount" 
                    class="right-input"
                    :min="totalAmount" 
                    @change="calculateChange"
                    required>
                  </v-text-field>
                </div>
              </td>
            </tr>
            <tr>
              <td colspan="6" align="right">Change</td>
              <td align="right">{{ changeAmount }}</td>
            </tr>
          </template>
      
      </v-data-table>

      <div align="right">
        <v-btn class="ma-3" color="red" dark @click="backToOrder" style="width: 103px;">Back</v-btn>
        <v-btn class="ma-3" color="primary" dark @click="savePayment">Confirm</v-btn>
      </div>
    </v-card>

    <span class="alertbox" v-if="message_type != ''">
      <v-alert class="mt-3" v-show="errorAlert" transition="scroll-y-transition" dense 
        :type="message_type">
          {{alert_message}}
      </v-alert>
    </span>
  </div>
</template>

<script>
import utils from '../utils/utils.js'

export default {
  name: 'payment',

  props: {
    headerData: Object,
    
    isNew: Boolean,

    isFromList: Boolean,
  },

  components: {},

  data() {
    return{
      saleHeaders: this.getHeaderData(),
      totalAmount: 0,
      paidAmount: 0,
      changeAmount: 0,
      userId: "",

      errorAlert: false,
      alert_message: "",
      message_type: "",
      
      loginUser: {},

      tableHeaders: [
        {
          text: 'No',
          value: 'id',
        },
        {
          text: 'Menu Code',
          value: 'menuCode',
        },
        {
          text: 'Menu',
          value: 'menuDesc',
        },
        {
          text: 'Remark',
          value: 'remark',
        },
        {
          text: 'QTY',
          value: 'qty',
        },
        {
          text: 'Price',
          value: 'price',
          align: "right"
        },
        {
          text: 'Total Price',
          value: 'totalPrice',
          align: "right"
        }
      ]
    };
  },

  async created(){
    if(this.isNew){
      this.alertbox("success", "Order Successful!", 3000);
    } else if(!this.isFromList) {
      this.alertbox("success", "Update Order Successful!", 3000);
    }

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

    await this.getOrderData();
  },

  methods: {
    getOrderData(){
      let tempAmount = 0;
      this.saleHeaders = this.headerData;

      this.saleHeaders.detailList.map(
        data => {
          tempAmount += data.totalPrice;
        }
      );

      if(tempAmount == this.saleHeaders.totalAmount){
        this.totalAmount = tempAmount;
      } else {
        this.alertbox("error", "TotalAmount doesn't match!!!", 3000);
      }
    },

    calculateChange(){
      this.changeAmount = this.paidAmount - this.totalAmount;
    },

    validate(){
      if(this.paidAmount == ""){
        this.alertbox("error", "Please add PaidAmount!!!", 3000);

        return false;
      }
      
      if(this.changeAmount < 0){
        this.alertbox("error", "Insufficient Paid Amount!!!", 3000);

        return false;
      }

      return true;
    },

    async savePayment(){
      if(this.validate()){
        this.loading = true;

        let saveTemp = {
          totalAmount: this.totalAmount,
          paidAmount: this.paidAmount,
          changeAmount: this.changeAmount,
          userid: this.loginUser.userid,
          hId: this.saleHeaders.id
        }
       
        const resp = await utils.http.post("/payments/create", saveTemp);
        this.loading = false;

        if(resp && resp.status == 200){
          let exportData = {
            isPaymentSuccess: true,
            headData: undefined
          };
          utils.goToScreenWithData("/dashBoard", "dashBoard", exportData);
        } else {
          this.alertbox("error", "Payment Failed!", 3000);
        }
      }

    },

    backToOrder(){
      let exportData = {
        isPaymentSuccess: false,
        headData: {}
      };
      exportData.headData = this.saleHeaders;

      utils.goToScreenWithData("/dashBoard", "dashBoard", exportData);
    },

    alertbox(type, message, timer){
      this.message_type = type;
      this.alert_message = message;
      this.errorAlert = true;

      setTimeout(() => {
        this.errorAlert = false;
      }, timer);
    },

    getHeaderData(){
      return {
        id: "",
        slipNo: "",
        tableNo: "",
        orderStatus: 0,     //    0 = Order Confirmed, 1 = Sales Confirmed, 2 = Served
        totalAmount: "",
        detailList: [],
      };
    },
  }

}
</script>

<style>

.right-input input {
  text-align: right;
}

.alertbox {
  position: fixed;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  margin: 0 auto;
  z-index: 1;
}

</style>