<template>
  <div class="ma-5">
    <v-card>
      <v-card-title class="d-flex justify-space-between" style="width: 50%;">
        <span class="mr-5">
          Today's Order 
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

      <v-row>

        <v-col>

          <v-card class="ma-3 mr-1 pa-3" style="height: 630px;">
            <v-data-table
              :headers="hTableHeaders"
              :items="saleHeaders"
              :search="search"
              :items-per-page="5"
              @click:row="(item) => item.orderStatus.toString() != '2' ? fetchOrderDetail(item) : false">
              <!-- @click:row="(item) => !detailDialog ? fetchOrderDetail(item) : (this.closeFormSnackBar = true)" -->

                <template v-slot:item.orderStatus="{ item }">
                  <div :class="CssClass[item.orderStatus]">
                    {{ STATUS[item.orderStatus] }}
                  </div>
                </template>
            </v-data-table>
          </v-card>

        </v-col>
        
        <v-col cols="6" style="position: sticky">
          <v-card
            class="ma-3 ml-1 pa-3"
            style="height: 630px; position: sticky; right: 0; top: 80px"
          >
            <v-card-title>
              <v-row>
                <v-col>

                  <div class="d-flex justify-space-between">
                    <h6 style="margin-left: 5px">
                      Table No: {{ tableNo }}
                    </h6>

                    <h6 style="margin-right: 5px">
                      Slip No: {{ slipNo }}
                    </h6>
                  </div>

                  <v-data-table
                    :headers="detailHeaders"
                    :items="headerDetailRecords"
                    :hide-default-footer="true"
                  >
                    <template v-slot:item.updateStatus="{ item }">
                      <!-- <v-btn
                        text
                        x-small
                        class="width-100"
                        color="success"
                        :disabled="item.orderStatus.toString() == '1'"
                        @click="tempData(item)"
                      >
                        {{ item.orderStatus.toString() == "1" ? "Served" : "Ordered" }}
                      </v-btn> -->

                      <v-btn text small :disabled="item.orderStatus.toString() == '1'" @click="tempData(item)">
                        <div :class="item.orderStatus.toString() == '1' ? 'orderStatus3' : 'orderStatus1'">
                          {{ item.orderStatus.toString() == "1" ? "Served" : "Ordered" }}
                        </div>
                      </v-btn>
                    </template>
                  </v-data-table>

                </v-col>
              </v-row>
              
            </v-card-title>

            <v-card-title class="d-flex justify-end">
              <v-btn
                class="mt-5 width-100"
                color="success"
                :disabled="!allMenuServed"
                @click="allServed"
                >Complete
              </v-btn>
            </v-card-title>
          </v-card>
        </v-col>

      </v-row>
      
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
import http from "../utils/http";

export default {
  data() {
    return {
      slipNo: "",
      tableNo: "",
      totalAmount: "",
      menuDesc: [],
      qty: "",
      remark: "",
      orderStatus: "",
      header_id: "",
      allMenuServed: false,
      search: "",

      saleHeaders: [],
      headerDetailRecords: [],
      detailDialog: false,
      count: 0,
      detailCount: 0,

      errorAlert: false,
      alert_message: "",
      message_type: "",

      hTableHeaders: [
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
          text: 'Confirm Status',
          value: 'orderStatus',
        },
      ],

      detailHeaders: [
        { text: "Menu", align: "start", value: "menuDesc", sortable: false },
        { text: "Quantity", value: "qty", sortable: false },
        { text: "Remark", value: "remark", sortable: false },
        { text: "Status", value: "updateStatus", sortable: false },
      ],

      STATUS: ["Order", "Sales", "Served"],
      CssClass: ["orderStatus1", "orderStatus2", "orderStatus3"]
    };
  },

  async created() {
    await this.fetchKitchenOrderLists();
  },

  methods: {
    async fetchKitchenOrderLists() {
      const resp = await http.get("/kitchen/order/all");

      if (resp && resp.status === 200) {
        const data = await resp.json();

        if (data) {
          this.saleHeaders = data;
        }
      }
    },

    async fetchOrderDetail(item) {
      this.headerDetailRecords = item.detailList;
      this.slipNo = item.slipNo;
      this.tableNo = item.tableNo;
      this.header_id = item.id;
      this.detailCount = item.detailList.length;

      this.checkAllServe();
    },

    async checkAllServe() {
      let allNotServe = this.headerDetailRecords.some(
        data => {
          return data.orderStatus.toString() != "1";
        }
      );

      if(!allNotServe){
        this.allMenuServed = true;
      }
    },

    async tempData(item) {
      this.changeStatus(item);
    },

    async changeStatus(item) {
      const resp = await http.put("/kitchen/order/status/served", {
        id: item.id,
        orderStatus: "1",
      });

      if (resp && resp.status === 200) {
        this.alertbox("success", "Detail Status Changed!", 3000);
        item.orderStatus = 1;
        this.checkAllServe();
      } else {
        this.alertbox("error", "Detail Status Not Changed!", 3000);
      }
    },

    async allServed() {
      const resp = await http.put("/kitchen/order/status/done", {
        id: this.header_id,
        orderStatus: "2",
      });

      if (resp && resp.status === 200) {
        this.alertbox("success", "Header Status Changed!", 3000);

        this.saleHeaders.map(
          data => {
            if(data.id == this.header_id){
              data.orderStatus = 2;
            }
          }
        );

        this.headerDetailRecords = [];
        this.allMenuServed = false;
      } else {
        this.alertbox("error", "Header Status Not Changed!", 3000);
      }
    },

    alertbox(type, message, timer){
      this.message_type = type;
      this.alert_message = message;
      this.errorAlert = true;

      setTimeout(() => {
        this.errorAlert = false;
      }, timer);
    },
  },
};
</script>

<style>

.kit-vtxt > .v-input__control > .v-input__slot:before {
  border-style: none;
}

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
