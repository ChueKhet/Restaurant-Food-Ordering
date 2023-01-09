<template>
  <v-row style="height: 100%">
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
                  class="pa0_ma0 px-2"
                  min="1"
                ></v-text-field>

                <h6 style="margin-right: 5px">Slip No:</h6>
                <v-text-field
                  v-model="slipNo"
                  class="pa0_ma0 px-2"
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
  </v-row>
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
      formId: "",

      headers: [
        { text: "Sip No", align: "start", value: "slipNo", sortable: false },
        { text: "Table No", value: "tableNo", sortable: false },
        { text: "Total Amount", value: "totalAmount", sortable: false },
      ],

      detailHeaders: [
        { text: "Menu", align: "start", value: "menuDesc", sortable: false },
        { text: "Quantity", value: "qty", sortable: false },
        { text: "Remark", value: "remark", sortable: false },
        { text: "Status", value: "updateStatus", sortable: false },
      ],
      headerRecords: [],
      headerDetailRecords: [],
      detailDialog: false,
      served: false,
      tempId: [],
      count: 0,
      detailCount: 0,
      menuLeftToServeMsg: "Menu still left to serve",
      MenuLeftToServeSnackBar: false,
      allServedSnackBar: false,
      allServedMsg: "All Served",
      closeFormSnackBar: false,
      closeFormMsg: "Please close the form first",
    };
  },

  async created() {
    await this.fetchKitchenOrderLists();
  },

  methods: {
    async fetchKitchenOrderLists() {
      const resp = await http.get("/sale/kitchen/order/all");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          console.log(data);
          this.headerRecords = data;
        }
      }
    },

    async fetchOrderDetail(item) {
      this.headerDetailRecords = item.detailList;
      this.formId = item.id;
      this.slipNo = item.slipNo;
      this.tableNo = item.tableNo;
      this.header_id = item.id;
      this.detailCount = item.detailList.length;
      this.detailDialog = true;
      this.setCount();
    },

    async setCount() {
      this.headerDetailRecords.map((detail) => {
        if (detail.orderStatus == "2") {
          this.count++;
        }
      });

      if (this.count == this.detailCount) {
        this.allMenuServed = true;
      }
    },

    async tempData(item, event) {
      this.tempId.push(item.id), this.changeStatus(item);
    },

    async changeStatus(item) {
      const resp = await http.put("/sale/kitchen/order/status/served", {
        id: item.id,
        orderStatus: "2",
      });

      if (resp && resp.status === 200) {
        this.served = true;
        this.count++;
        console.log("success update status");
      }
    },

    async allServed() {
      if (this.count !== this.detailCount) {
        this.MenuLeftToServeSnackBar = true;
        return false;
      }
      const resp = await http.put("/sale/kitchen/order/status/done", {
        id: this.header_id,
        orderStatus: "2",
      });
      if (resp && resp.status === 200) {
        this.served = true;
        this.allServedSnackBar = true;
        this.closeDetailForm();
        console.log("all served");
      }
    },

    async closeDetailForm() {
      this.detailDialog = false;
      this.allMenuServed = false;
      this.count = 0;
      this.fetchKitchenOrderLists();
    },
  },
};
</script>

<style>
.v-text-field > .v-input__control > .v-input__slot:before {
  border-style: none;
}
</style>
