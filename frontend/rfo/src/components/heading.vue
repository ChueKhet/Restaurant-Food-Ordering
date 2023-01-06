<template>
    <v-app-bar class="headMenuBar" color="deep-purple lighten-1" dark>
        <div class="logoApp" @click = "utl.goToScreen('/')"></div>
        
        <v-spacer></v-spacer>

        <v-btn text @click = "utl.goToScreen('/menu')" v-show="loginUser && loginUser.role == 1">Menu</v-btn>
        <v-btn text @click = "utl.goToScreen('/ingredients')" v-show="loginUser && loginUser.role == 1">Ingredient</v-btn>
        <v-btn text @click = "utl.goToScreen('/admin_user_list')" v-show="loginUser && loginUser.role == 1">UserList</v-btn>
        <v-btn text @click = "utl.goToScreen('/register')" v-show="loginUser && loginUser.role == 1">Register</v-btn>
        <v-btn text @click = "utl.goToScreen('/sale_headers')" v-show="loginUser && loginUser.role == 0">Sales</v-btn>
        <v-btn text @click = "utl.goToScreen('/profile')" v-show="loginUser">Profile</v-btn>
        <v-btn text @click = "onClickLogInAndOut">{{isLogIn ? "LogOut" : "LogIn"}}</v-btn> 
    </v-app-bar>
</template>

<script>

import utils from "../utils/utils";

export default {
  name: 'Heading',

  data: () => ({
    isLogIn: false,
    loginUser:{},
    screenPath: "",
    loginUser: {},
    utl: utils
  }),

  created(){
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

    this.isLogIn = this.$store.state.isLogIn;
    this.$store.watch(
      () => {
        return this.$store.state.isLogIn;
      },
      (newVal, oldVal) => {
        this.isLogIn = newVal;
      },
      {
        deep: true,
      }
    );
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
    onClickLogInAndOut(){
      if(this.isLogIn){
        this.$store.commit("logout");

        if(this.$router.currentRoute.path !="/"){
          // this.isLogIn = false;
          utils.goToScreen("/");
        }
      } else{
        // this.$store.commit("setRegister", data);

        utils.goToScreen("/login");
      }
    }
  }

};

</script>

<style>

.logoApp{
  margin-top: 13px;
  max-height: 80px;
  max-width: 100px;
  height: 80px;
  width: 100px;
  background-image:url("../assets/logo/Ternion Logo 2.png");
  background-size: 100%;
}

.logoApp:hover, .logoFontApp:hover{
  cursor: pointer;
}

.logoFontApp{
  font-family: 'Hanalei Fill', cursive;
  font-size: 25pt;
  color: #efbe47;
}

.headMenuBar{
  position: sticky;
  top: 0;
  z-index: 1;
}
</style>