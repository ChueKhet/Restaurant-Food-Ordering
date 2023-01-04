

<template>
    <div>
      <v-card class="mx-auto pa-3" max-width="344">
          <v-card-text>
            <div>Welcome</div>
            <p class="text-h4 text--primary">
              {{ loginUser.image }}
            </p>
    
            <v-row>
                <v-col cols="4" class="font-weight-bold">Name</v-col>
                <v-col cols="8">{{loginUser.name}}</v-col>
            </v-row>
            <v-row>
                <v-col cols="4" class="font-weight-bold">NRC</v-col>
                <v-col cols="8">{{loginUser.nrc}}</v-col>
            </v-row>
            <v-row>
                <v-col cols="4" class="font-weight-bold">Phone_No</v-col>
                <v-col cols="8">{{loginUser.phone}}</v-col>
            </v-row>
            <v-row>
                <v-col cols="4" class="font-weight-bold">DOB</v-col>
                <v-col cols="8">{{loginUser.dob}}</v-col>
            </v-row>
            <v-row>
                <v-col cols="4" class="font-weight-bold">Address</v-col>
                <v-col cols="8">{{loginUser.address}}</v-col>
            </v-row>
          </v-card-text>
    
          <v-btn text color="red" @click="changePwd()">
            Change Password
          </v-btn>
          <v-btn text color="red" @click="clear">
            <span v-if="!loading">Delete</span>
            <v-progress-circular
                v-else indeterminate color="primary"></v-progress-circular>
        </v-btn>
          </v-card>
      
      </div>
  </template>
  
  <script>
import utils from '@/utils/utils';

  export default {
    name: "profile",
    components:{},
    data() {
      return {
        localDomain: utils.constant.localDomain,
        loginUser: {},
        
      };
    },
    created() {
      this.loginUser = this.$store.state.loginUser;
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
    },
    methods: {
      changePwd() {
        this.$router.push({ path: "/changePwd" });
      },
      clear(){
        this.image=null;
        this.name="";
        this.dob="";
        this.nrc="";
        this.phone="";
        this.address="";
      }

      
      
    },
  };
  </script>
  