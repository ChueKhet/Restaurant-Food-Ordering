<template>
  <div class="ma-5">
    <v-card>
      <v-card-title class="d-flex justify-space-between">
        <span class="mr-5">
          Staff List
          <v-icon @click="register">mdi-plus-box</v-icon>
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
      
      <v-data-table
        :headers="tableHeaders"
        :items="userAccList"
        :items-per-page="5">

          <template v-slot:item.btn="{item}">

            <v-btn class="mr-3" color="primary" fab x-small @click="onClickEditBtn(item)">
              <v-icon>mdi-pencil</v-icon>
            </v-btn>

            <v-btn color="red" fab x-small dark @click="onClickDeleteBtn(item)">
              <v-icon>mdi-delete</v-icon>
            </v-btn>

          </template>

      </v-data-table>
    </v-card>

    <v-dialog v-model="deleteDialog" width="350">
      <v-card>

        <v-card-title>Delete User</v-card-title>

        <v-card-text>
          You are going to delete this User
          <br>
          <v-row class="pa-5">
            <v-col cols="4" class="pa-0">ID : </v-col>
            <v-col cols="8" class="pa-0">{{toDeleteUser.id}}</v-col>
            <v-col cols="4" class="pa-0">Name : </v-col>
            <v-col cols="8" class="pa-0">{{toDeleteUser.name}}</v-col>
          </v-row>
        </v-card-text>

        <v-card-actions class="dflex justify-center">
          <v-btn color="red" dark @click="deleteUser(toDeleteUser.id)">Delete</v-btn>
          <v-btn @click="deleteDialog = false">Cancel</v-btn>
        </v-card-actions>

      </v-card>
    </v-dialog>

    <span class="alertbox" v-if="message_type != ''">
      <v-alert class="mt-3" v-show="errorAlert" transition="scroll-y-transition" dense 
        :type="message_type">
          {{alert_message}}
      </v-alert>
    </span>
  </div>
</template>

<script>
import utils from '@/utils/utils';

export default {
  name: 'UserList',

  props: {
    isFromProfile: Boolean,
    isFromRegister: Boolean
  },

  components: {},

  data(){
    return {
      tableHeaders: [
        {
          text: 'ID',
          value: 'user.id',
          sortable: true,
        },
        {
          text: 'Name',
          value: 'user.name',
          sortable: true,
        },
        {
          text: 'Employee ID',
          value: 'acc.userid',
          sortable: true,
        },
        {
          text: 'DOB',
          value: 'user.dob',
          sortable: true,
        },
        {
          text: 'NRC',
          value: 'user.nrc',
        },
        {
          text: 'Gender',
          value: 'user.gender',
          sortable: true,
        },
        {
          text: 'PhoneNo',
          value: 'user.phone',
          sortable: true,
        },
        {
          text: 'Address',
          value: 'user.address',
        },
        {
          text: 'Created Date',
          value: 'user.createdAt',
        },
        {
          text: 'Modified Date',
          value: 'user.modifiedAt',
        },
        {
          text: 'Actions',
          value: 'btn',
        },
      ],

      userAccList: [],
      toEditId: 0,
      name: "",
      dob: "",
      nrc: "",
      gender: "",
      phone: "",
      address: "",
      deleteDialog: false,
      toDeleteUser: {},
      createDialog: false,
      search: "",

      errorAlert: false,
      alert_message: "",
      message_type: "",
    };
  },

  async created(){
    if(this.isFromProfile){
      this.alertbox("success", "Delete Successful!", 3000);
    }

    if(this.isFromRegister){
      this.alertbox("success", "Register Successful!", 3000);
    }

    await this.fetchUserList();
  },

  methods: {
    async fetchUserList(){
      const resp = await utils.http.get("/user/all");

      if(resp && resp.status == 200){
        const data = await resp.json();

        if(data){
          this.userAccList = data;
        }
      }
    },  

    register(){
      let exportData = {
        isFromUserList: true
      };

      utils.goToScreenWithData("/register", "register", exportData);
    },

    onClickEditBtn(item){
      let exportData = {
        userProfile: {}
      };
      exportData.userProfile = item;

      utils.goToScreenWithData("/profile", "profile", exportData);
    },

    onClickDeleteBtn(item){
      this.toDeleteUser = item;
      this.deleteDialog = true;
    },

    async deleteUser(userId){
      const resp = await utils.http.del("/user/delete", {id: userId});

      if(resp && resp.status == 200){
        await this.fetchUserList();
        this.deleteDialog = false;
        this.alertbox("success", "Delete Successful!", 3000);
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
    }
  },
}
</script>

<style>

.alertbox {
  position: fixed;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  margin: 0 auto;
  z-index: 1;
}

</style>