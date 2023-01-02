<template>
  <v-row style="height: 100%">
    <v-col cols="12" sm="6">
      <!--  Table -->
      <v-data-table
        :headers="headers"
        :items="kitchenOrderRecords"
        :item-key="slipNo"
        :items-per-page="10"
        @click:row="(item) => fetchOrderDetail(item)"
      >
      </v-data-table>
    </v-col>

    <v-col cols="12" sm="6" style="position: sticky">
      <v-card
        class="ma-3 pa-3"
        style="height: 630px; position: sticky; right: 0; top: 80px"
      >
        <v-card-title>
          <h1>hi</h1>
        </v-card-title>
      </v-card>
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

      headers: [
        { text: "Sip No", align: "start", value: "slipNo", sortable: false },
        { text: "Table No", value: "tableNo", sortable: false },
        { text: "Total Amount", value: "totalAmount", sortable: false },
      ],
      kitchenOrderRecords: [],
      kitchenOrderDetail: [],

      createMenuForm: "",
      editMenuForm: "",
      categoryLists: [1, 2],

      createDialog: false,
      createMenuForm: false,
      createSuccessSnackBar: false,
      createSuccessMsg: "Successfully created menu",

      editDialog: false,
      editMenuForm: false,
      editSuccessSnackBar: false,
      editSuccessMsg: "Successfully edited menu",

      deleteDialog: false,
      toDeleteMenu: {},
      deleteSuccessSnackBar: false,
      deleteSuccessMsg: "Successfully deleted menu",
    };
  },

  async created() {
    await this.fetchKitchenOrderLists();
  },

  methods: {
    async fetchKitchenOrderLists() {
      const resp = await http.get("/sale/kitchen/order/all");
      if (resp && resp.status === 200) {
        console.log("first");
        const data = await resp.json();
        console.log("second");
        if (data) {
          console.log("third");
          this.kitchenOrderRecords = data;

          console.log(data);
        }
        console.log("fourth");
      }
      console.log("fifth");
    },

    async fetchOrderDetail(item) {
      console.log(item, item.slipNo, item.tableNo);
      const resp = await utils.http.get(
        "/sale/kitchen/order/detail" + item.slipNo
      );
      if (resp && resp.status === 200) {
        const data = await resp.json();

        if (data) {
          this.kitchenOrderDetail = data;

          console.log(data);
        }
      }
    },
  },
};
</script>

<style></style>
