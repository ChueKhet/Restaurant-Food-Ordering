<template>
  <div>
    <v-card class="my-5 mx-auto pa-3" max-width="344">
      <v-card-title>Profile</v-card-title>
      <v-card-text>
        <v-img :src="domainPath + userInfo.imagePath">
        </v-img>

        <v-row>
          <v-col cols="4" class="font-weight-bold">Name</v-col>
          <v-col cols="8">{{userInfo.name}}</v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="font-weight-bold">NRC</v-col>
          <v-col cols="8">{{userInfo.nrc}}</v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="font-weight-bold">DOB</v-col>
          <v-col cols="8">{{userInfo.dob}}</v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="font-weight-bold">Gender</v-col>
          <v-col cols="8">{{userInfo.gender}}</v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="font-weight-bold">Phone_No</v-col>
          <v-col cols="8">{{userInfo.phone}}</v-col>
        </v-row>
        <v-row>
          <v-col cols="4" class="font-weight-bold">Address</v-col>
          <v-col cols="8">{{userInfo.address}}</v-col>
        </v-row>
      </v-card-text>

      <v-btn text color="red" @click="changePwd()">
        Change Password
      </v-btn>

      <v-btn text color="red" @click="clear">
        <span v-if="!loading">Delete</span>
        <v-progress-circular
          v-else indeterminate color="primary">
        </v-progress-circular>
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
        userInfo: {},
        domainPath: utils.constant.imagePath 
      };
    },

    created() {
      this.$store.watch(
        () => {
          return this.$store.state.userInfo;
        },
        (newVal, oldVal) => {
          this.userInfo = newVal;
        },
        {
          deep: true,
        }
      );

      this.userInfo = this.$store.state.userInfo;
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

<style>



</style>