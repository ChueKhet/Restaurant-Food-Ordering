<template>
    <v-app-bar class="headMenuBar" color="deep-purple lighten-1" dark>
    <!-- style="background-color: #5d6e22;"  -->
        <!-- <v-btn class="text-h1" text @click="goToScreen('/')">Home</v-btn> -->
        <div class="logoApp" @click = "utl.goToScreen('/')"></div>
        <!-- <label class="logoFontApp" @click="goToScreen('/')">Ternion</label> -->
        
        <v-spacer></v-spacer>
        <!-- <v-btn text v-show = "!isRegister" @click = "goToScreen('/register')" >Register</v-btn>
        <v-btn text @click = "goToScreen('/about')" >About</v-btn>  -->
       
        <v-btn text @click = "utl.goToScreen('/menu')">Menu</v-btn>
        <v-btn text @click = "utl.goToScreen('/admin_user_list')">UserList</v-btn>
        <v-btn text @click = "utl.goToScreen('/sale_headers')">Sales</v-btn>
        <v-btn text @click = "utl.goToScreen('/register')">Register</v-btn>
        <v-btn text @click = "utl.goToScreen('/profile')">Profile</v-btn>
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
    utl: utils
  }),

  created(){
    this.isLogIn = this.$store.state.isLogIn;
    this.$store.watch(
      () => {
        return this.$store.state.isLogIn;
      },
      (newVal, oldVal) =>{
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
        this.$store.commit("setIsLogIn",false);
        this.$store.commit("setUserInfo",{}); 
        if(this.$router.currentRoute.path !="/"){
          this.isLogIn = false;
          utils.goToScreen("/");
        }
      } else{
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