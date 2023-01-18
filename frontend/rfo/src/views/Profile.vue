<template>
  <div class="ma-5">
    <v-card class="mx-auto" max-width="900">

      <v-card-title>
        <span class="mr-5">
          Profile
          <v-icon @click="onClickEdit" v-show="loginUser && loginUser.role != 0">mdi-pencil</v-icon>
        </span>
      </v-card-title>

      <v-card-text>
        <v-row>

          <v-col cols="5">
            <v-img :src="domainPath + userInfo.imagePath"></v-img>
          </v-col>

          <v-col>

            <v-row>
              <v-col>
                <v-text-field v-model="userInfo.name" label="Name" single-line
                  :disabled="!isEdit">
                </v-text-field>
              </v-col>

              <v-col class="d-flex align-center pt-6">
                <v-select
                  :items="['Male','Female','Other']"
                  v-model="userInfo.gender"
                  label="Gender"
                  :disabled="!isEdit"
                  dense>
                </v-select>
              </v-col>
            </v-row>

            <v-row>
              <v-col>
                <v-text-field v-model="userInfo.nrc" label="Nrc" single-line
                  :disabled="!isEdit">
                </v-text-field>
              </v-col>

              <v-col>
                <v-text-field v-model="userInfo.phone" label="Phone" single-line
                  :disabled="!isEdit">
                </v-text-field>
              </v-col>
            </v-row>

            <v-row>
              <v-col>
                <v-menu v-model="dobPopup" transition="scale-transition" min-width="auto"
                  :close-on-content-click="false"
                  :nudge-right="40" 
                  offset-y>
                    <template v-slot:activator="{ on, attrs }" >
                      <v-text-field v-model="userInfo.dob" label="DOB"
                        prepend-icon="mdi-calendar" v-bind="attrs" v-on="on"
                        readonly :disabled="!isEdit">
                      </v-text-field>
                    </template>

                    <v-date-picker v-model="userInfo.dob" @input="dobPopup = false">
                    </v-date-picker>
                </v-menu>
              </v-col>

              <v-col>
                <v-text-field v-model="userInfo.address" label="Address" single-line
                  :disabled="!isEdit">
                </v-text-field>
              </v-col>
            </v-row>

          </v-col>

        </v-row>
        
      </v-card-text>

      <div class="d-flex justify-end">
        <v-btn text color="red" @click="changePwd" v-show="userInfo.id == testUser.id">
          Change Password
        </v-btn>

        <v-btn text color="red" @click="deleteUser" v-show="(loginUser && loginUser.role != 0) && (userInfo.id != testUser.id)">
          <span v-if="!loading">Delete</span>
          <v-progress-circular
            v-else indeterminate color="primary">
          </v-progress-circular>
        </v-btn>

        <v-btn text color="red" @click="updateInfo" v-show="(loginUser && loginUser.role != 0)" :disabled="!isEdit">
          Update Info
        </v-btn>
      </div>

    </v-card>

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
  name: "profile",

  props: {
    userProfile: Object,
    required: true
  },

  components:{},

  data() {
    return {
      localDomain: utils.constant.localDomain,
      domainPath: utils.constant.imagePath,

      tempUserInfo: {},
      userInfo: {},
      testUser: {},
      loginUser:{},

      loading: false,

      isEdit: false,
      dobPopup: false,
      isFromUList: false,

      message_type: "",
      alert_message: "",
      errorAlert: false,
    };
  },

  created() {
    this.$store.watch(
      () => {
        return this.$store.state.userInfo;
      },
      (newVal, oldVal) => {
        this.testUser = newVal;
      },
      {
        deep: true,
      }
    );

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
    this.testUser = this.$store.state.userInfo;

    if(this.userProfile && this.userProfile != undefined 
      && this.userProfile.name != undefined && this.userProfile.name != ""){
        this.userInfo = this.userProfile;
        this.isFromUList = true;
    } else {
      this.userInfo = this.$store.state.userInfo;
    }
  },

  methods: {
    changePwd() {
      this.$router.push({ path: "/changePwd" });
    },

    onClickEdit(){
      let temp = {
        address: "",
        createdAt: "",
        dob: "",
        gender: "",
        id: "",
        imagePath: "",
        modifiedAt: "",
        name: "",
        nrc: "",
        phone: "",
      };

      if(this.isEdit){
        temp.address = this.tempUserInfo.address;
        temp.createdAt = this.tempUserInfo.createdAt;
        temp.dob = this.tempUserInfo.dob;
        temp.gender = this.tempUserInfo.gender;
        temp.id = this.tempUserInfo.id;
        temp.imagePath = this.tempUserInfo.imagePath;
        temp.modifiedAt = this.tempUserInfo.modifiedAt;
        temp.name = this.tempUserInfo.name;
        temp.nrc = this.tempUserInfo.nrc;
        temp.phone = this.tempUserInfo.phone;

        this.userInfo = temp;
      } else {
        temp.address = this.userInfo.address;
        temp.createdAt = this.userInfo.createdAt;
        temp.dob = this.userInfo.dob;
        temp.gender = this.userInfo.gender;
        temp.id = this.userInfo.id;
        temp.imagePath = this.userInfo.imagePath;
        temp.modifiedAt = this.userInfo.modifiedAt;
        temp.name = this.userInfo.name;
        temp.nrc = this.userInfo.nrc;
        temp.phone = this.userInfo.phone;

        this.tempUserInfo = temp;
      }
      
      this.isEdit = !this.isEdit;
    },

    validate(){
      if(this.name == ""){
        this.alertbox("error", "Please add Name!!!", 3000);

        return false;
      }

      if(this.dob == ""){
        this.alertbox("error", "Please add DOB!!!", 3000);

        return false;
      }

      if(this.gender == ""){
        this.alertbox("error", "Please add gender!!!", 3000);

        return false;
      }

      if(this.nrc == ""){
        this.alertbox("error", "Please add NRC!!!", 3000);

        return false;
      }

      if(this.phone == ""){
        this.alertbox("error", "Please add Phone!!!", 3000);

        return false;
      }

      if(this.address == ""){
        this.alertbox("error", "Please add Address!!!", 3000);

        return false;
      }

      return true;
    },

    async updateInfo() {
      if (this.validate()){
        this.loading = true;

        const resp = await utils.http.put("/user/update", this.userInfo);
        this.loading = false;

        if (resp.status === 200){
          const data = await resp.json();

          if (data) {
            this.alertbox("success", "Update Successful!", 3000);

            if(this.isFromUList){
              utils.goToScreen("/admin_user_list");
            } else {
              this.edit = false;
            }
          }
        } else {
          this.alertbox("error", "Update Failed!", 3000);
        }
      }
    },

    async deleteUser(){
      const resp = await utils.http.del("/user/delete", {id: this.userInfo.id});

      if(resp && resp.status == 200){
        this.alertbox("success", "Delete Successful!", 3000);
        utils.goToScreen("/admin_user_list");
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
};
</script>

<style>



</style>