<template>
  <div class="ma-5">
    <v-card>
      <v-card-title class="d-flex justify-space-between">
        <span class="mr-5">
          Create Ingredients
          <v-icon @click="onClickCreateBtn">mdi-plus-box</v-icon>
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

          <div>
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

            <v-text-field
              v-model="code"
              label="Code"
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field>

            <v-text-field
              v-model="description"
              label="Add ingredient"
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field>

            <div class="d-flex justify-end">
              <v-btn class="mt-5 width-100 mr-5"
                color="success" @click="createDialog = false">
                  Cancel
              </v-btn>

              <v-btn class="mt-5 width-100"
                color="success" @click="createIngredient">
                  Save
              </v-btn>
            </div>
            
          </v-form>
        </v-card-text>
      </v-card>

      <span class="alertboxIngredient d-flex justify-center" v-if="message_type != '' && createDialog">
        <v-alert class="mt-3" v-show="errorAlert" transition="scroll-y-transition" dense 
          :type="message_type">
            {{alert_message}}
        </v-alert>
      </span>
    </v-dialog>

    <!-- Edit Dialog -->
    <v-dialog v-model="editDialog" width="500">
      <v-card>
        <v-card-title>
          <span class="mr-5">Edit Ingredients</span>
      
        </v-card-title>
        <v-card-text>
          <v-form ref="editIngredientForm" v-model="editIngredientForm">

            <v-text-field
              v-model="code"
              label="Code"
              :readonly="true"
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field>

            <v-text-field
              v-model="description"
              label="Add ingredient"
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field>
            
            <div class="d-flex justify-end">
              <v-btn class="mt-5 width-100 mr-5"
                color="success" @click="editDialog = false">
                  Cancel
              </v-btn>

              <v-btn class="mt-5 width-100"
                color="success" @click="editIngredient">
                  Edit
              </v-btn>
            </div>
            
          </v-form>
        </v-card-text>
      </v-card>

      <span class="alertboxIngredient d-flex justify-center" v-if="message_type != '' && editDialog">
        <v-alert class="mt-3" v-show="errorAlert" transition="scroll-y-transition" dense 
          :type="message_type">
            {{alert_message}}
        </v-alert>
      </span>
    </v-dialog>

    <!-- Delete Dialog -->
    <v-dialog v-model="deleteDialog" width="300">
      <v-card>
        <v-card-title>Delete Ingredient</v-card-title>
        <v-card-text>Are you sure to delete this Ingredient?</v-card-text>

        <v-card-actions class="d-flex justify-end">
          <v-btn @click="deleteDialog = false">Cancel</v-btn>

          <v-btn color="red" dark @click="deleteIngredient(toDeleteID.id)">
            Delete
          </v-btn>
        </v-card-actions>

      </v-card>
    </v-dialog>

    <span class="alertbox" v-if="message_type1 != ''">
      <v-alert class="mt-3" v-show="errorAlert1" transition="scroll-y-transition" dense 
        :type="message_type1">
          {{alert_message1}}
      </v-alert>
    </span>
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
      code: "",
      menu:[], //selected menu list for creating ingredient 
      ingredient:"",
      created_at: new Date().toISOString().substr(0, 10),
      modified_at: new Date().toISOString().substr(0, 10),
      user_id: "",
      description: "",
      
      addIngredientForm: "",
      editIngredientForm: "",

      headers: [
        { text: "Code", align: "start", value: "code" },
        { text: "Ingredient", align: "start", value: "description" },
        { text: "Created By", value: "userid" },
        { text: "Actions", align: "center", value: "actions", sortable: false },
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

      errorAlert: false,
      alert_message: "",
      message_type: "",

      errorAlert1: false,
      alert_message1: "",
      message_type1: "",
    };
  },

  async created() {
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
    
    await this.fetchIngredientAddedMenuLists();
  },

  methods: {
    initialState() {
      this.id = "";
      this.description = "";
      this.code = "";
      this.user_id = "";
    },

    async fetchIngredientAddedMenuLists() {
      const resp = await http.get("/ingredient/all");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.ingredientAddedMenuRecords = data;
        }
      }
    },

    onClickCreateBtn() {
      this.initialState();
      this.createDialog = true;
    },

    //fetch menu list in create ingredient page load
    async fetchmenuRecords() {
      const resp = await http.get("/menu/all");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.menuRecords = data;
        }
      }
    },

    async createIngredient() {
      if (this.$refs.addIngredientForm.validate()) {
        let param = {
          code: this.code,
          userid: this.loginUser.userid,
          description: this.description,
        };
  
        const resp = await http.post("/ingredient/add", param)
       
        if (resp && resp.status === 200) {
          const data = await resp.json();

          if(data.message.toString() == "CODE_ALREADY_EXIST"){
            this.alertbox("error", "Code Already Exist!!!", 3000);
            this.$refs.codeCreate.focus();
            return;
          }

          await this.fetchIngredientAddedMenuLists();
          this.initialState();
          this.createDialog = false;

           this.createSuccessSnackBar = true;
           this.alertbox1("success", "Create Successful!", 3000);
        } else {
          this.alertbox("error", "Create Failed!", 3000);
        }
      }
    },

    onClickEditBtn(item) {
      this.initialState();
      this.id = item.id;
      this.code = item.code;
      this.created_at = item.created_at;
      this.description = item.description;
      this.editDialog = true;
    },

    async editIngredient() {
      if (this.$refs.editIngredientForm.validate()) {
        let param = {
          id: this.id,
          description: this.description,
        };
        const resp = await http.put("/ingredient/update",param);
        if (resp && resp.status === 200) {
          this.editSuccessSnackBar = true;
          await this.fetchIngredientAddedMenuLists();
          this.editDialog = false;
          this.alertbox1("success", "Update Successful!", 3000);
        } else {
          this.alertbox("error", "Update Failed!", 3000);
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
        this.alertbox1("success", "Delete Successful!", 3000);
      } else {
        this.alertbox("error", "Delete Failed!", 3000);
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

    alertbox1(type, message, timer){
      this.message_type1 = type;
      this.alert_message1 = message;
      this.errorAlert1 = true;

      setTimeout(() => {
        this.errorAlert1 = false;
      }, timer);
    }
  },
};
</script>

<style>

.alertbox, .alertboxIngredient {
  position: fixed;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  margin: 0 auto;
  z-index: 1;
}

.alertboxIngredient > .alert {
  display: inline-block;
}

</style>