<template>
  <div class="ma-3">
    <v-row style="margin-left: 5px; margin-top: 0px !important; margin-bottom: 20px;">
      <v-title>
        <h2>
          Sale Detail
        </h2>
      </v-title>
    </v-row>

    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">Code</th>
            <th class="text-left">Name</th>
            <th class="text-left">Quantity</th>
            <th class="text-left">Price</th>
            <th class="text-left">Total Price</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in saleDetail" :key="item.name">
            <td>{{ item.code }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.qty }}</td>
            <td>{{ item.price }}</td>
            <td>{{ item.totalPrice }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </div>
</template>

<script>
import utils from "@/utils/utils";

export default {
  props: {
    saleHeaderId: String
  },

  name: "SaleHeaders",

  components: {},

  data() {
    return {
      tableHeaders: [
        {
          text: "ID",
          value: "id",
          sortable: true,
        },
        {
          text: "Price",
          value: "price",
        },
        {
          text: "Quantity",
          value: "qty",
        },
        {
          text: "Total Price",
          value: "totalPrice",
        },
        {
          text: "Created Date",
          value: "createdDate",
        },
        {
          text: "Actions",
          value: "btn",
        },
      ],

      saleDetail: [],
      headerId: this.saleHeaderId
    };
  },

  async created() {
    await this.fetchSaleDetail();
  },

  methods: {
    async fetchSaleDetail() {
      // const headerId = this.$route.params.headerId;

      const resp = await utils.http.get(
        "/sale/detail" + "?headerId=" + this.headerId
      );

      if (resp && resp.status == 200) {
        const data = await resp.json();

        if (data) {
          this.saleDetail = [data];
        }
      }
    },
    
    detailClick() {
      console.log("sale detail menu items");
    },
  },
};
</script>

<style></style>
