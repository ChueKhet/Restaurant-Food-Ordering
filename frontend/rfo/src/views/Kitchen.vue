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
                  <div class="orderStatus">
                    {{ STATUS[item.orderStatus] }}
                  </div>
                </template>
                <!-- <template v-slot:item.btn="{ item }">

                  <v-btn
                    color="red"
                    fab
                    x-small
                    dark
                    @click="onClickDetail(item.id)" >
                      <v-icon>mdi-eye</v-icon>
                  </v-btn>
                </template> -->
            </v-data-table>
          </v-card>

        </v-col>
        
        <v-col cols="6" style="position: sticky">
          <v-card
            class="ma-3 ml-1 pa-3"
            style="height: 630px; position: sticky; right: 0; top: 80px"
          >
          <!-- v-if="detailDialog" -->

            <!-- <v-icon class="align-left" @click="closeDetailForm()">mdi-close</v-icon> -->
            <v-card-title>
              <v-row>
                <v-col>

                  <div class="d-flex justify-space-between">
                    <h6 style="margin-left: 5px">
                      Table No: {{ tableNo }}
                    </h6>
                    <!-- <v-text-field 
                      v-model="tableNo"
                      class="pa0_ma0 px-2 kit-vtxt"
                      min="1"
                    ></v-text-field> -->

                    <h6 style="margin-right: 5px">
                      Slip No: {{ slipNo }}
                    </h6>
                    <!-- <v-text-field 
                      v-model="slipNo"
                      class="pa0_ma0 px-2 kit-vtxt"
                      min="1"
                    ></v-text-field> -->
                  </div>

                  <v-data-table
                    :headers="detailHeaders"
                    :items="headerDetailRecords"
                    :hide-default-footer="true"
                  >
                  <!-- :item-key="slipNo" -->
                    <template v-slot:item.updateStatus="{ item }">
                      <v-btn
                        text
                        x-small
                        class="width-100"
                        color="success"
                        :disabled="item.orderStatus.toString() == '1'"
                        @click="tempData(item)"
                      >
                      <!-- (served && tempId.includes(item.id)) || -->
                        {{ item.orderStatus.toString() == "1" ? "Served" : "Ordered" }}
                      </v-btn>
                    </template>
                  </v-data-table>

                </v-col>
              </v-row>

              <!-- <span class="text-uppercase mt-5" v-if="allMenuServed">All Served</span> -->
              
            </v-card-title>

            <v-card-title>
              <v-btn
                class="mt-5 width-100"
                color="success"
                :disabled="!allMenuServed"
                @click="allServed()"
                >Complete
              </v-btn>
            </v-card-title>
          </v-card>

          <v-snackbar color="red" v-model="MenuLeftToServeSnackBar">
            {{ menuLeftToServeMsg }}
          </v-snackbar>

          <v-snackbar v-model="allServedSnackBar">
            {{ allServedMsg }}
          </v-snackbar>

          <v-snackbar v-model="closeFormSnackBar">
            {{ closeFormMsg }}
          </v-snackbar>
        </v-col>

      </v-row>
      
    </v-card>
  </div>

  <!-- <v-row style="height: 100%">
    <v-col cols="12" sm="6">
      <div class="ml-5 mt-10 mb-2 font-weight-bold item-center;">
        <span class="align-center">Today's Order </span>
      </div>

      <v-data-table
        class="ml-10"
        :headers="headers"
        :items="headerRecords"
        :item-key="slipNo"
        :items-per-page="10"
        @click:row="
          (item) =>
            !detailDialog
              ? fetchOrderDetail(item)
              : (this.closeFormSnackBar = true)
        "
      >
      </v-data-table>
    </v-col>

    <v-col cols="12" sm="6" style="position: sticky">
      <v-card
        class="ma-3 pa-3"
        style="height: 630px; position: sticky; right: 0; top: 80px"
        v-if="detailDialog"
      >
        <v-icon class="align-left" @click="closeDetailForm()">mdi-close</v-icon>
        <v-card-title>
          <v-row>
            <v-col>
              <div class="d-flex">
                <h6 style="margin-left: 5px">Table No:</h6>
                <v-text-field 
                  v-model="tableNo"
                  class="pa0_ma0 px-2 kit-vtxt"
                  min="1"
                ></v-text-field>

                <h6 style="margin-right: 5px">Slip No:</h6>
                <v-text-field 
                  v-model="slipNo"
                  class="pa0_ma0 px-2 kit-vtxt"
                  min="1"
                ></v-text-field>
              </div>
              <v-data-table
                :headers="detailHeaders"
                :items="headerDetailRecords"
                :item-key="slipNo"
                :hide-default-footer="true"
              >
                <template v-slot:item.updateStatus="{ item }">
                  <v-btn
                    text
                    x-small
                    class="width-100"
                    color="success"
                    :disabled="
                      (served && tempId.includes(item.id)) ||
                      item.orderStatus == '2'
                    "
                    @click="tempData(item)"
                  >
                    {{
                      (served && tempId.includes(item.id)) ||
                      item.orderStatus == "2"
                        ? "Served"
                        : "Sale"
                    }}</v-btn
                  >
                </template>
              </v-data-table>
            </v-col>
          </v-row>

          <span class="text-uppercase mt-5" v-if="allMenuServed"
            >All Served</span
          >
          <v-btn
            v-else
            class="mt-5 width-100"
            color="success"
            @click="allServed()"
            >Complete
          </v-btn>
        </v-card-title>
      </v-card>

      <v-snackbar color="red" v-model="MenuLeftToServeSnackBar">
        {{ menuLeftToServeMsg }}
      </v-snackbar>

      <v-snackbar v-model="allServedSnackBar">
        {{ allServedMsg }}
      </v-snackbar>

      <v-snackbar v-model="closeFormSnackBar">
        {{ closeFormMsg }}
      </v-snackbar>
    </v-col>
  </v-row> -->

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
        // {
        //   text: 'Actions',
        //   value: 'btn',
        // },
      ],

      detailHeaders: [
        { text: "Menu", align: "start", value: "menuDesc", sortable: false },
        { text: "Quantity", value: "qty", sortable: false },
        { text: "Remark", value: "remark", sortable: false },
        { text: "Status", value: "updateStatus", sortable: false },
      ],

      STATUS: ["Order", "Sales", "Served"],

      // served: false,
      // formId: "",
      // tempId: [],

      // MenuLeftToServeSnackBar: false,
      // allServedSnackBar: false,
      // closeFormSnackBar: false,
      // menuLeftToServeMsg: "Menu still left to serve",
      // allServedMsg: "All Served",
      // closeFormMsg: "Please close the form first",
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
          // this.saleHeaders = data;

          this.saleHeaders = data?.length > 0 ? data.map(
            it => ({...it, orderStatus:STATUS[it.orderStatus]})
          ) : [];
        }
      }
    },

    async fetchOrderDetail(item) {
      this.headerDetailRecords = item.detailList;
      this.slipNo = item.slipNo;
      this.tableNo = item.tableNo;
      this.header_id = item.id;
      this.detailCount = item.detailList.length;

      // this.detailDialog = true;
      // this.formId = item.id;

      this.checkAllServe();
    },

    async checkAllServe() {
      // this.headerDetailRecords.map((detail) => {
      //   if (detail.orderStatus == "2") {
      //     this.count++;
      //   }
      // });

      // if (this.count == this.detailCount) {
      //   this.allMenuServed = true;
      // }

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
        // this.served = true;
        // this.count++;
        // this.tempId.push(item.id);

        item.orderStatus = 1;
        this.checkAllServe();
      }
    },

    async allServed() {
      // if (this.count !== this.detailCount) {
      //   this.MenuLeftToServeSnackBar = true;
      //   return false;
      // }

      const resp = await http.put("/kitchen/order/status/done", {
        id: this.header_id,
        orderStatus: "2",
      });

      if (resp && resp.status === 200) {
        // this.served = true;
        // this.allServedSnackBar = true;
        // this.closeDetailForm();

        this.saleHeaders.map(
          data => {
            if(data.id = this.header_id){
              data.orderStatus = 2;
            }
          }
        );

        this.headerDetailRecords = [];
        this.allMenuServed = false;
      }
    },

    // async closeDetailForm() {
    //   // this.detailDialog = false;
      
    //   this.allMenuServed = false;
    //   this.count = 0;
    //   this.fetchKitchenOrderLists();
    // },
  },
};
</script>

<style>
.kit-vtxt > .v-input__control > .v-input__slot:before {
  border-style: none;
}
</style>
