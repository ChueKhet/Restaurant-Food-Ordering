<template>
  <dvi class="ma-5">
    <v-card>
      <v-card-title>
        <span class="mr-5"
          >Create Menu
          <v-icon @click="onClickCreateBtn()">mdi-plus-box</v-icon></span
        >
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
      </v-card-title>

      <!--  Table -->
      <v-data-table
        :headers="headers"
        :items="menuRecords"
        :search="search"
        :items-per-page="5"
      >
        <template v-slot:item.actions="{ item }">
          <v-btn
            class="mr-3"
            color="primary"
            fab
            x-small
            dark
            @click="onClickEditBtn(item)"
          >
            <v-icon>mdi-pencil</v-icon>
          </v-btn>

          <v-btn color="red" fab x-small dark @click="onClickDeleteBtn(item)">
            <v-icon>mdi-delete</v-icon>
          </v-btn>
        </template>
      </v-data-table>
    </v-card>

    <!-- Create Dialog -->
    <v-dialog v-model="createDialog" width="500">
      <v-card>
        <v-card-title>New Menu</v-card-title>
        <v-card-text>
          <v-form ref="createMenuForm" v-model="createMenuForm">
            <v-text-field
              v-model="code"
              label="Code"
              disabled
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field>

            <v-text-field
              v-model="description"
              label="Menu"
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field>

            <v-select
              v-model="category_id"
              :items="categoryLists"
              label="Choose Category"
              outlined
              required
              :rules="[(v) => !!v || 'Required']"
            ></v-select>

            <v-text-field
              v-model="price"
              label="Price"
              type="number"
              :rules="[(v) => !!v || 'Required']"
              required
              outlined
            ></v-text-field>

            <v-text-field
              v-model="created_at"
              label="Created At"
              type="date"
              disabled
              outlined
            ></v-text-field>

            <v-text-field
              v-model="user_id"
              label="Created By"
              outlined
            ></v-text-field>

            <v-btn class="mt-5 width-100" color="success" @click="createMenu()"
              >Create</v-btn
            >

            <v-btn
              class="mt-5 width-100 ml-5"
              color="success"
              @click="createDialog = false"
              >Cancel</v-btn
            >
          </v-form>
        </v-card-text>
      </v-card>
    </v-dialog>

    <!-- Edit Dialog -->
    <v-dialog v-model="editDialog" width="500">
      <v-card>
        <v-card-title>Edit Menu</v-card-title>
        <v-card-text>
          <v-form ref="editMenuForm" v-model="editMenuForm">
            <!-- <v-text-field
              v-model="code"
              label="Code"
              disabled
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field> -->

            <v-text-field
              v-model="description"
              label="Menu"
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field>

            <v-select
              :items="categoryLists"
              label="Choose Category"
              outlined
              required
              :rules="[(v) => !!v || 'Required']"
            ></v-select>

            <v-text-field
              v-model="price"
              label="Price"
              type="number"
              :rules="[(v) => !!v || 'Required']"
              required
              outlined
            ></v-text-field>

            <v-text-field
              v-model="modified_at"
              label="Modified At"
              type="date"
              disabled
              outlined
            ></v-text-field>

            <v-text-field
              v-model="user_id"
              label="Created By"
              outlined
            ></v-text-field>

            <v-btn class="mt-5 width-100" color="success" @click="editMenu()"
              >Edit</v-btn
            >

            <v-btn
              class="mt-5 width-100 ml-5"
              color="success"
              @click="editDialog = false"
              >Cancel</v-btn
            >
          </v-form>
        </v-card-text>
      </v-card>
    </v-dialog>

    <!-- Delete Dialog -->
    <v-dialog v-model="deleteDialog" width="500">
      <v-card>
        <v-card-title>Delete Menu</v-card-title>
        <v-card-text> Are you sure to delete this menu? </v-card-text>
        <v-card-actions>
          <v-btn @click="deleteDialog = false">Cancel</v-btn>
          <v-btn color="red" dark @click="deleteMenu(toDeleteMenu.id)"
            >Delete</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!--create success msg-->
    <v-snackbar v-model="createSuccessSnackBar">
      {{ createSuccessMsg }}
    </v-snackbar>

    <!--edit success msg-->
    <v-snackbar v-model="editSuccessSnackBar">
      {{ editSuccessMsg }}
    </v-snackbar>

    <!--delete success msg-->
    <v-snackbar v-model="deleteSuccessSnackBar">
      {{ deleteSuccessMsg }}
    </v-snackbar>
  </dvi>
</template>

<script>
import { createLogger } from "vuex";
import http from "../utils/http";
export default {
  data() {
    return {
      id: "",
      description: "",
      code: Math.floor(1000 + Math.random() * 9000),
      category_id: "",
      price: "",
      created_at: new Date().toISOString().substr(0, 10),
      modified_at: new Date().toISOString().substr(0, 10),
      user_id: "",
      search: "",

      createMenuForm: "",
      editMenuForm: "",

      headers: [
        { text: "No", align: "start", value: "id" },
        { text: "Menu", value: "description" },
        { text: "Code", value: "code" },
        { text: "Category", value: "category_id" },
        { text: "Price", value: "price" },
        { text: "Created At", value: "created_at" },
        { text: "Modified At", value: "modified_at" },
        { text: "Created By", value: "user_id" },
        { text: "Actions", value: "actions", sortable: false },
      ],
      menuRecords: [],
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

  // Run once when screen is loaded
  async created() {
    await this.fetchMenuLists();
  },

  methods: {
    initialState() {
      this.id = "";
      this.description = "";
      this.code = Math.floor(1000 + Math.random() * 9000);
      (this.category_id = ""), (this.price = "");
      this.user_id = "";
    },

    async fetchMenuLists() {
      const resp = await http.get("/menu/list");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.menuRecords = data;
          console.log(data);
        }
      }
    },

    onClickCreateBtn() {
      this.createDialog = true;
      console.log(this.created_at);
    },

    async createMenu() {
      if (this.$refs.createMenuForm.validate()) {
        const resp = await http.post("/menu/add", {
          description: this.description,
          code: this.code,
          category_id: this.category_id,
          price: this.price,
          created_at: this.created_at,
          user_id: this.user_id,
        });
        if (resp && resp.status === 200) {
          await this.fetchMenuLists();
          this.createDialog = false;
          this.initialState();

          this.createSuccessSnackBar = true;
        }
      }
    },

    onClickEditBtn(item) {
      console.log(item.category_id);
      this.id = item.id;
      this.category_id = item.category_id;
      this.price = item.price;
      this.description = item.description;
      this.modified_at;
      this.user_id = item.user_id;
      this.editDialog = true;
    },

    async editMenu() {
      if (this.$refs.editMenuForm.validate()) {
        console.log(this.category_id);
        const resp = await http.put("/menu/update", {
          id: this.id,
          category_id: this.category_id,
          price: this.price,
          description: this.description ? this.description : null,
          modified_at: this.modified_at,
          user_id: this.user_id,
        });
        if (resp && resp.status === 200) {
          this.editSuccessSnackBar = true;
          await this.fetchMenuLists();
          this.editDialog = false;
        }
      }
    },

    onClickDeleteBtn(item) {
      this.toDeleteMenu = item;
      this.deleteDialog = true;
    },

    async deleteMenu(toDeleteID) {
      const resp = await http.del("/menu/del", {
        id: toDeleteID,
      });
      if (resp && resp.status === 200) {
        this.deleteSuccessSnackBar = true;
        await this.fetchMenuLists();
        this.deleteDialog = false;
      }
    },
  },
};
</script>
