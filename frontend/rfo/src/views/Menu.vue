<template>
  <div class="ma-5">
    <v-card>
      <v-card-title class="d-flex justify-space-between">
        <span class="mr-5">
          Create Menu
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
              :items="categoryLists"
              label="Choose Category"
              outlined
              required
              :rules="[(v) => !!v || 'Required']"
            ></v-select>
     
            <v-text-field
              v-model="code"
              label="Code"
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
            ></v-text-field> -->

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
              :items="categoryLists"
              v-model="category_id"
              label="Choose Category"
              outlined
              required
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
      code: "",//Math.floor(1000 + Math.random() * 9000)
      category_id: "",
      price: "",
      created_at: new Date().toISOString().substr(0, 10),
      modified_at: new Date().toISOString().substr(0, 10),
      user_id: this.$store.state.loginUser.name,
      search: "",
      createMenuForm: "",
      editMenuForm: "",

      headers: [
        { text: "No", align: "start", value: "id" },
        { text: "Code", value: "code" },
        { text: "Menu", value: "description" },
        { text: "Category", value: "catId" },
        { text: "Price", value: "price" },
        { text: "Created At", value: "created_at" },
        { text: "Modified At", value: "modified_at" },
        { text: "Created By", value: "user_id" },
        { text: "Actions", value: "actions", sortable: false },
      ],
      menuRecords: [],
      categoryLists: [1, 2],

      toUpdateMenu: {
        image: null,
        imagePath: "",
      },
      imagePreviewPath: null,

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
    await this.fetchMenuLists();
  },

  methods: {
    initialState() {
      this.id = "";
      this.description = "";
      this.code = "";//Math.floor(1000 + Math.random() * 9000)
      this.category_id = "";
      this.price = "";
      this.user_id = "";
      this.toUpdateMenu.image = null;
      this.toUpdateMenu.imagePath = "";
      this.imagePreviewPath = null;
    },

    async fetchMenuLists() {
      const resp = await http.get("/menu/all");
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
      this.initialState();
      // console.log(this.created_at);
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

        let saveData = {
          imagePath: respImageData,
          code: this.code,
          description: this.description,
          price: this.price,
          created_at: this.created_at,
          user_id: this.user_id,
          category: {
            id: this.category_id
          }
        };

        const resp = await http.post("/menu/add", saveData);

        if (resp && resp.status === 200) {
          await this.fetchMenuLists();
          this.createDialog = false;
          this.initialState();

          this.createSuccessSnackBar = true;
        }
      }
    },

    onClickEditBtn(item) {
      this.toUpdateMenu = Object.assign({}, item);
      this.imagePreviewPath = utils.constant.imagePath + item.imagePath;
      
      this.toUpdateMenu.image = this.fileCreate(item);

      this.id = item.id;
      // this.toUpdateMenu.image = item.imagePath;
      // this.imagePreviewPath = this.toUpdateMenu.imagePath + item.imagePath;
      this.code = item.code;
      this.category_id = item.catId;
      this.price = item.price;
      this.description = item.description;
      this.modified_at;
      this.user_id = item.user_id;
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

        const resp = await http.put("/menu/update", {
          id: this.id,
          imagePath: respImageData,
          code: this.code,
          description: this.description,
          price: this.price,
          created_at: this.created_at,
          user_id: this.user_id,
          category: {
            id: this.category_id
          }
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

    onChangeImage(image) {
      this.imagePreviewPath = URL.createObjectURL(image);
    },
  },
};
</script>
