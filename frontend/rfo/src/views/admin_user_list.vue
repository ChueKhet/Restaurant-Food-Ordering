<template>
  <div class="ma-3">
  <!-- style="height: 79.5%;" -->
    <v-row style="margin-left: 5px; margin-top: 0px !important; margin-bottom: 20px;">
        <v-title><h2>Staff List</h2></v-title>
        <v-spacer></v-spacer>
        <v-btn icon @click="register()">
        <v-icon size="30">mdi-plus-box</v-icon>
      </v-btn>
    </v-row>
    <v-data-table class="elevation-4"
      :headers="tableHeaders"
      :items="userList"
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
  </div>
</template>

<script>
import utils from '@/utils/utils';

export default {
  name: 'UserList',

  components: {},

  data(){
    return {
      tableHeaders: [
        {
          text: 'ID',
          value: 'id',
          sortable: true,
        },
        {
          text: 'Name',
          value: 'name',
          sortable: true,
        },
        {
          text: 'DOB',
          value: 'dob',
          sortable: true,
        },
        {
          text: 'NRC',
          value: 'nrc',
        },
        {
          text: 'Gender',
          value: 'gender',
          sortable: true,
        },
        {
          text: 'PhoneNo',
          value: 'phone',
          sortable: true,
        },  
        {
          text: 'Created Date',
          value: 'createdDate',
        },
        {
          text: 'Modified Date',
          value: 'modifiedDate',
        },
        {
          text: 'Address',
          value: 'address',
        },
        {
          text: 'Actions',
          value: 'btn',
        },
      ],
      userList: [],
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
    };
  },

  async created(){
    await this.fetchUserList();
  },

  methods: {
    async fetchUserList(){
      const resp = await utils.http.get("/user/all");

      if(resp && resp.status == 200){
        const data = await resp.json();

        if(data){
          this.userList = data;
        }
      }
    },  

    register(){
      utils.goToScreen("/register");       
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
      }
    }
  },
}
</script>

<style>

</style>