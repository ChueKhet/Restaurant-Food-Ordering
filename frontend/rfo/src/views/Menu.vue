<template>
  <div class="ma-5">
    <v-card>
      <v-card-title class="d-flex justify-space-between">
        <span class="mr-5">
          Create Menu
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
        :items="menuRecords"
        :search="search"
        :items-per-page="5"
      >

        <template v-slot:item.category_id="{ item }">
          <label>{{ catIdOutput(item.category.id) }}</label>
        </template>

        <template v-slot:item.image="{ item }">
          <v-img 
            :src="ucImagePath + item.imagePath"
            width="100"
            height="105"
            contain
          >
          </v-img>
        </template>

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

            <div class="d-flex justify-center mt-5">
              <v-img
                v-if="imagePreviewPath != null && imagePreviewPath != ''"
                :src="imagePreviewPath"
                width="200"
                height="215"
                contain
              >
              </v-img>
              <v-img
                v-if="imagePreviewPath == null || imagePreviewPath == ''"
                src="../assets/plus_sign for upload.png"
                width="200"
                height="215"
                contain
              >
              </v-img>
            </div>

            <v-file-input
              v-model="toUpdateMenu.image"
              label="Add Photo"
              show-size
              prepend-icon="mdi-camera"
              placeholder="Choose Photo"
              accept="image/png, image/jpeg"
              :rules="[
              (v) =>
                  !v ||
                  v.size < 10000000 ||
                  'Image size should be less than 10 MB!',
              ]"
              @change="onChangeImage"
            ></v-file-input>

            <v-select
              v-model="category_id"
              :items="categoryList"
              label="Choose Category"
              item-text="description"
              item-value="id"
              outlined
              required
              :rules="[(v) => !!v || 'Required']"
            ></v-select>

            <v-select
              v-model="ingredients"
              :items="ingredientList"
              label="Choose Ingredient"
              item-text="description"
              outlined
              required
              attach
              chips
              return-object
              multiple
              :rules="[(v) => !!v || 'Required']"
            ></v-select>
     
            <v-text-field
              v-model="code"
              label="Code"
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
              ref='codeCreate'
            ></v-text-field>

            <v-text-field
              v-model="description"
              label="Menu"
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field>

            <v-text-field
              v-model="price"
              label="Price"
              type="number"
              :rules="[(v) => !!v || 'Required']"
              required
              outlined
            ></v-text-field>

            <div class="d-flex justify-end">
              <v-btn class="mt-5 width-100 mr-5"
                color="success" @click="createDialog = false">
                  Cancel
              </v-btn>

              <v-btn class="mt-5 width-100" color="success" @click="createMenu">
                Create
              </v-btn>
            </div>
            
          </v-form>
        </v-card-text>
      </v-card>

      <span class="alertboxMenu d-flex justify-center" v-if="message_type != '' && createDialog">
        <v-alert class="mt-3" v-show="errorAlert" transition="scroll-y-transition" dense 
          :type="message_type">
            {{alert_message}}
        </v-alert>
      </span>
    </v-dialog>

    <!-- Edit Dialog -->
    <v-dialog v-model="editDialog" width="500">
      <v-card>
        <v-card-title>Edit Menu</v-card-title>
        <v-card-text>
          <v-form ref="editMenuForm" v-model="editMenuForm">

            <div class="d-flex justify-center mt-5">
              <v-img
                v-if="imagePreviewPath != null && imagePreviewPath != ''"
                :src="imagePreviewPath"
                width="200"
                height="215"
                contain
              >
              </v-img>
              <v-img
                v-if="imagePreviewPath == null || imagePreviewPath == ''"
                src="../assets/plus_sign for upload.png"
                width="200"
                height="215"
                contain
              >
              </v-img>
            </div>

            <v-file-input
              v-model="toUpdateMenu.image"
              label="Add Photo"
              show-size
              prepend-icon="mdi-camera"
              placeholder="Choose Photo"
              accept="image/png, image/jpeg"
              :rules="[
              (v) =>
                !v ||
                v.size < 10000000 ||
                'Image size should be less than 10 MB!',
              ]"
              @change="onChangeImage"
            ></v-file-input>

            <v-select
              :items="categoryList"
              v-model="category_id"
              label="Choose Category"
              item-text="description"
              item-value="id"
              outlined
              required
              :rules="[(v) => !!v || 'Required']"
            ></v-select>

            <v-select
              v-model="ingredients"
              :items="ingredientList"
              label="Choose Ingredient"
              item-text="description"
              outlined
              required
              attach
              chips
              return-object
              multiple
              :rules="[(v) => !!v || 'Required']"
            ></v-select>

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
              label="Menu"
              required
              :rules="[(v) => !!v || 'Required']"
              outlined
            ></v-text-field>      

            <v-text-field
              v-model="price"
              label="Price"
              type="number"
              :rules="[(v) => !!v || 'Required']"
              required
              outlined
            ></v-text-field>

            <!-- <v-text-field
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
            ></v-text-field> -->

            <div class="d-flex justify-end">
              <v-btn class="mt-5 width-100 mr-5"
                color="success" @click="editDialog = false">
                  Cancel
              </v-btn>
              
              <v-btn class="mt-5 width-100" color="success" @click="editMenu">
                Edit
              </v-btn>
            </div>
            
          </v-form>
        </v-card-text>
      </v-card>

      <span class="alertboxMenu d-flex justify-center" v-if="message_type != '' && editDialog">
        <v-alert class="mt-3" v-show="errorAlert" transition="scroll-y-transition" dense 
          :type="message_type">
            {{alert_message}}
        </v-alert>
      </span>
    </v-dialog>

    <!-- Delete Dialog -->
    <v-dialog v-model="deleteDialog" width="300">
      <v-card>
        <v-card-title>Delete Menu</v-card-title>
        <v-card-text> Are you sure to delete this menu? </v-card-text>

        <v-card-actions class="d-flex justify-end">
          <v-btn @click="deleteDialog = false">Cancel</v-btn>

          <v-btn color="red" dark @click="deleteMenu(toDeleteMenu.id)">
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
import utils from "../utils/utils.js";

export default {
  data() {
    return {
      id: "",
      description: "",
      code: "",
      category_id: "",
      price: "",
      created_at: new Date().toISOString().substr(0, 10),
      modified_at: new Date().toISOString().substr(0, 10),
      user_id: "",
      search: "",
      createMenuForm: "",
      editMenuForm: "",
      loginUser: {},

      errorAlert: false,
      alert_message: "",
      message_type: "",

      errorAlert1: false,
      alert_message1: "",
      message_type1: "",

      headers: [
        { text: "Photo", value: "image" },
        { text: "Code", value: "code" },
        { text: "Menu", value: "description" },
        { text: "Category", value: "category_id" },
        { text: "Price", value: "price" },
        { text: "Created At", value: "createdAt" },
        { text: "Modified At", value: "modifiedAt" },
        { text: "Created By", value: "userid" },
        { text: "Actions", value: "actions", sortable: false },
      ],
      menuRecords: [],
      categoryList: [],
      ingredientList: [],
      ingredients: [],

      toUpdateMenu: {
        image: null,
        imagePath: "",
      },
      imagePreviewPath: null,
      ucImagePath: utils.constant.imagePath,
      editImageChanges: false,

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
  async created(){
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

    await this.getCategoryList();
    await this.getIngredientList();
    await this.fetchMenuLists();
  },

  methods: {
    initialState() {
      this.id = "";
      this.description = "";
      this.code = "";
      this.category_id = "";
      this.price = "";
      this.user_id = "";
      this.toUpdateMenu.image = null;
      this.toUpdateMenu.imagePath = "";
      this.imagePreviewPath = null;
      this.editImageChanges = false;
      this.ingredients = [];
    },

    catIdOutput(catId){
      let id = this.categoryList.filter(
        data => {
          return data.id == catId;
        }
      )[0].description;

      return id;
    },

    async fetchMenuLists() {
      const resp = await http.get("/menu/all");
      if (resp && resp.status === 200) {
        const data = await resp.json();

        if (data) {
          this.menuRecords = data;
        }
      }
    },

    async getCategoryList(){
      const resp = await utils.http.get("/category/all");

      if(resp && resp.status == 200){
        this.categoryList = await resp.json();
      }
    },

    async getIngredientList(){
      const resp = await utils.http.get("/ingredient/all");

      if(resp && resp.status == 200){
        this.ingredientList = await resp.json();
      }
    },

    onClickCreateBtn() {
      this.createDialog = true;
      this.initialState();
    },

    async createMenu() {
      if (this.$refs.createMenuForm.validate()) {
        let respImageData = null;
        const respImage = await http.postMedia(
          "/media/file/create",
          this.toUpdateMenu.image,
          this.toUpdateMenu.image.type,
          "Menu"
        );

        if (respImage && respImage.status === 200) {
          respImageData = await respImage.text();
        } else {
          this.errorAlert = true;
        }

        this.user_id = this.loginUser.userid;
        let saveData = {
          imagePath: respImageData,
          code: this.code,
          description: this.description,
          price: +this.price,
          created_at: this.created_at,
          userid: this.user_id,
          category: {
            id: this.category_id
          },
          ingredientList: this.ingredients
        };

        const resp = await http.post("/menu/add", saveData);

        if (resp && resp.status === 200) {
          const data = await resp.json();

          if(data.message.toString() == "CODE_ALREADY_EXIST"){
            this.alertbox("error", "Code Already Exist!!!", 3000);
            this.$refs.codeCreate.focus();
            return;
          }

          this.alertbox1("success", "Create Successful!", 4200);

          setTimeout(async () => {
            await this.fetchMenuLists();
            this.createDialog = false;
            this.initialState();
          }, 1200);
        } else {
          this.alertbox("error", "Create Failed!", 3000);
        }
      }
    },

    onClickEditBtn(item) {
      this.initialState();
      this.toUpdateMenu = Object.assign({}, item);
      this.imagePreviewPath = utils.constant.imagePath + item.imagePath;
      
      this.toUpdateMenu.image = this.fileCreate(item);

      this.id = item.id;
      this.code = item.code;
      this.category_id = item.category.id;
      this.price = item.price;
      this.description = item.description;
      this.modified_at;
      this.user_id = item.user_id;

      this.ingredients = [];
      item.ingredientList.map(
        data => {
          this.ingredients.push(data);
        }
      );
      this.editDialog = true;
    },

    fileCreate(item){
      const myArray1 = item.imagePath.split("/");
      const myArray2 = myArray1[myArray1.length - 1].split(".");

      return new File(
        [myArray2[0].toString()], 
        myArray1[myArray1.length - 1].toString(), 
        {type: "image/" + myArray2[1].toString()}
      );
    },

    async editMenu() {
      if (this.$refs.editMenuForm.validate()) {
        let respImageData = null;

        if(this.editImageChanges){
          const respImage = await http.putMedia(
            "/media/file/update",
            this.toUpdateMenu.image,
            this.toUpdateMenu.image.type,
            this.toUpdateMenu.imagePath,
            "Menu"
          );

          if (respImage && respImage.status === 200) {
            respImageData = await respImage.text();
          } else {
            this.errorAlert = true;
          }
        } else {
          respImageData = this.toUpdateMenu.imagePath;
        }
        
        this.user_id = this.loginUser.userid;
        const resp = await http.put("/menu/update", {
          id: this.id,
          imagePath: respImageData,
          code: this.code,
          description: this.description,
          price: this.price,
          created_at: this.created_at,
          userid: this.user_id,
          category: {
            id: this.category_id
          },
          ingredientList: this.ingredients
        });

        if (resp && resp.status === 200) {
          this.editSuccessSnackBar = true;
          this.alertbox1("success", "Update Successful!", 4200);

          setTimeout(async () => {
            await this.fetchMenuLists();
            this.editDialog = false;
            this.initialState();
          }, 1200);
        } else {
          this.alertbox("error", "Update Failed!", 3000);
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
        this.alertbox1("success", "Delete Successful!", 3000);
      } else {
        this.alertbox("error", "Delete Failed!", 3000);
      }
    },

    onChangeImage(image) {
      this.imagePreviewPath = URL.createObjectURL(image);
      this.editImageChanges = true;
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

.alertbox, .alertboxMenu {
  position: fixed;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  margin: 0 auto;
  z-index: 1;
}

.alertboxMenu > .alert {
  display: inline-block;
}

</style>