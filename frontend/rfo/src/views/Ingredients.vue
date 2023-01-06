<template>
  <div class="ma-5">
    <v-card>
      <v-card-title class="d-flex justify-space-between">
        <span class="mr-5">
          Create Ingredients
          <v-icon @click="onClickCreateBtn()">mdi-plus-box</v-icon>
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

      <!--  Table -->
      <v-data-table
        :headers="headers"
        :items="ingredientAddedMenuRecords"
        :search="search"
        :items-per-page="5"
      >
        <template v-slot:item.actions="{ item }">

          <div style="text-align:right;">
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
        </div>

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
            <v-select
              v-model="menuList"
              :items="menuRecords"
              attach
              chips
              label="Menu"
              item-text="description"
              return-object
              multiple
            ></v-select>

            <v-text-field
              v-model="description"
              label="Add ingredient"
              required
              :rules="[(v) => !!v || 'Required']"
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
        <v-card-title>
          <span class="mr-5">Edit Ingredients</span>
      
        </v-card-title>
        <v-card-text>
          <v-form ref="editIngredientForm" v-model="editIngredientForm">
            <v-select
              v-model="menuList"
              :items="menuRecords"
              attach
              chips
              label="Menu"
              item-text="description"
              return-object
              multiple
            ></v-select>

            <v-text-field
              v-model="description"
              label="Add ingredient"
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field>        
            <v-btn
              class="mt-5 width-100"
              color="success"
              @click="editIngredient()"
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
        <v-card-title>Delete Ingredient</v-card-title>
        <v-card-text> Are you sure to delete this Ingredient? </v-card-text>
        <v-card-actions>
          <v-btn @click="deleteDialog = false">Cancel</v-btn>
          <v-spacer></v-spacer>
          <v-btn color="red" dark @click="deleteIngredient(toDeleteID.id)"
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
  </div>
</template>

<script>
import http from "../utils/http";
export default {
  data() {
    return {
      lists: [],
      search: "",
      menuList :[],
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
        { text: "Ingredient", align: "start", value: "description" },
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
      const resp = await http.get("/ingredient/all");
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
      const resp = await http.get("/menu/all");
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
        let param = {
          code: this.code,
          description: this.description,
          menuDataList: this.menuList 
        };

        // this.menuList.map(
        //   data=>{
        //     param.menuDataList.push(data);
        //   }
        // );
  
        const resp = await http.post("/ingredient/add", param)
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
      // console.log(item.category_id);
      // this.id = item.id;
      // this.category_id = item.category_id;
      // this.price = item.price;
      // this.description = item.description;
      // this.modified_at;
      // this.user_id = item.user_id;
      this.fetchmenuRecords();
      this.id = item.id;
      this.code = item.code;
      this.created_at = item.created_at;
      this.description = item.description;
      this.menuList = item.menuDataList;
      this.editDialog = true;
    },
    async editIngredient() {
      if (this.$refs.editIngredientForm.validate()) {
        let param = {
          id: this.id,
          code: this.code,
          description: this.description,
          menuDataList: this.menuList 
        };
        const resp = await http.put("/ingredient/update",param);
        if (resp && resp.status === 200) {
          this.editSuccessSnackBar = true;
          await this.fetchmenuRecords();
          this.editDialog = false;
        }
      }
    },

    onClickDeleteBtn(item) {
      this.toDeleteID = item;
      this.deleteDialog = true;
    },

    async deleteIngredient(toDeleteID) {
      const resp = await http.del("/ingredient/del", {
        id: toDeleteID,
      });
      if (resp && resp.status === 200) {
        this.deleteDialog = false;
        await this.fetchIngredientAddedMenuLists();
        
        this.initialState();
        this.deleteSuccessSnackBar = true;
       
       
      }
    },
  },
};
</script>
