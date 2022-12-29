<template>
  <dvi class="ma-5">
    <v-card>
      <v-card-title>
        <span class="mr-5"
          >Create Ingredients
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
        :items="lists"
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
        <v-card-title>
          <span class="mr-5">Add Ingredients</span>
      
        </v-card-title>
        <v-card-text>
          <v-form ref="addIngredientForm" v-model="addIngredientForm">
            <v-text-field
              v-model="code"
              label="Code"
              disabled
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field>

            <v-select
            v-model="menu"
              label="Choose Menu"
              outlined
              required
              multiple
              :rules="[(v) => !!v || 'Required']"
              
              :items="menuRecords"
              item-text="description"
              item-value="id"
              persistent-hint
             return-object
              single-line
            
            ></v-select>

            <v-text-field
              v-model="ingredient"
              label="Add ingredient"
              required
              :rules="[(v) => !!v || 'Required']"
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

            <v-btn
              class="mt-5 width-100"
              color="success"
              @click="createIngredient()"
              >Save</v-btn
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
        <v-card-title>Edit Invoice</v-card-title>
        <v-card-text>
          <v-form ref="editIngredientForm" v-model="editIngredientForm">
            <v-btn class="mt-5 width-100" color="success" @click="editInvoice()"
              >Edit</v-btn
            >
            <v-spacer></v-spacer>
            <v-btn
              class="mt-5 width-100"
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
        <v-card-title>Delete Invoice</v-card-title>
        <v-card-text> Are you sure to delete this invoice? </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="deleteDialog = false">Cancel</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="red" dark @click="deleteInvoice(toDeleteInvoice.id)"
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
import http from "../utils/http";
export default {
  data() {
    return {
      ingredientAddedMenuRecords: [],
      menuRecords: [], //menu list binding
     
      id: "",
      code: Math.floor(1000 + Math.random() * 9000),
      menu:[], //selected menu list for creating ingredient 
      ingredient:"",
      created_at: new Date().toISOString().substr(0, 10),
      modified_at: new Date().toISOString().substr(0, 10),
      user_id: "",
      
      addIngredientForm: "",
      editIngredientForm: "",

      headers: [
        { text: "No", align: "start", value: "id" },
        { text: "Menu", align: "start", value: "menu" },
        { text: "Actions", value: "actions", sortable: false },
      ],

      

      createDialog: false,
      addIngredientForm: false,
      createSuccessSnackBar: false,
      createSuccessMsg: "Successfully added ingredients to menu",

      editDialog: false,
      editIngredientForm: false,
      editSuccessSnackBar: false,
      editSuccessMsg: "Successfully edited",

      deleteDialog: false,
      toDeleteInvoice: {},
      deleteSuccessSnackBar: false,
      deleteSuccessMsg: "Successfully deleted",

      updateBtn: true,
      saveBtn: false,
    };
  },

  async created() {
    await this.fetchIngredientAddedMenuLists();
  },

  methods: {
    initialState() {
      this.id = "";
      this.code = Math.floor(1000 + Math.random() * 9000);
      this.user_id = "";
    },

    async fetchIngredientAddedMenuLists() {
      const resp = await http.get("/ingredient/list");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.ingredientAddedMenuRecords = data;
          console.log(data);
          console.log(this.ingredientAddedMenuRecords);
        }
      }
    },

    onClickCreateBtn() {
      this.createDialog = true;
      this.fetchmenuRecords();
    },

    //fetch menu list in create ingredient page load
    async fetchmenuRecords() {
      const resp = await http.get("/menu/list");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.menuRecords = data;
          console.log(data);
          console.log(this.menuRecords);
        }
      }
    },

 

    async createIngredient() {
      if (this.$refs.addIngredientForm.validate()) {
     //   console.log(this.menu, this.menu.map(m=>console.log(m,m.description)));

        this.createdIngredient=this.menu.map(m=>{
       m.code,
       m.description

        })
console.log(this.createdIngredient,"hi")

        const resp = await http.post("/ingredient/add", {
       

         })
        //   code: this.code,
        //   menuId: this.menu.id,
        //  ingredient:this.ingredientRows,
        //   created_at: this.created_at,
        //   user_id: this.user_id,
       
        if (resp && resp.status === 200) {
          await this.fetchIngredientAddedMenuLists();
          this.createDialog = false;
          this.initialState();

           this.createSuccessSnackBar = true;
         }
      }
    },

    onClickEditBtn(item) {
      // this.id = item.id;
      // this.date = item.date;
      // this.sender = item.sender;
      // this.receiver = item.receiver;
      // this.items = item.items;
      // this.quantity = item.quantity;
      // this.price = item.price;
      // this.total = item.total;
      // this.type = item.type;
      // this.remark = item.remark;
      // this.editDialog = true;
    },

    async editInvoice() {
      if (this.$refs.editIngredientForm.validate()) {
        const resp = await api.put("/api/receipt", {
          // id: this.id,
          // date: this.date,
          // sender: this.sender,
          // receiver: this.receiver,
          // items: this.items,
          // quantity: this.quantity,
          // price: this.price,
          // total: this.total,
          // type: this.type ? this.type : null,
          // remark: this.remark ? this.remark : null,
        });
        if (resp && resp.status === 200) {
          this.editSuccessMsg = true;
          await this.fetchInvoiceLists();
          this.editDialog = false;
        }
      }
    },

    onClickDeleteBtn(item) {
      // this.toDeleteInvoice = item;
      // this.deleteDialog = true;
    },

    async deleteInvoice(toDeleteID) {
      const resp = await api.del("/api/receipt", {
        id: toDeleteID,
      });
      if (resp && resp.status === 200) {
        this.deleteSuccessMsg = true;
        await this.fetchInvoiceLists();
        this.deleteDialog = false;
      }
    },
  },
};
</script>
