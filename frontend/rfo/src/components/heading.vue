<template>
    <v-app-bar color="deep-purple lighten-1" dark>
    <!-- style="background-color: #5d6e22;"  -->
        <!-- <v-btn class="text-h1" text @click="goToScreen('/')">Home</v-btn> -->
        <div class="logoApp" @click="goToScreen('/')"></div>
        <!-- <label class="logoFontApp" @click="goToScreen('/')">Ternion</label> -->
        
        <v-spacer></v-spacer>
        <!-- <v-btn text v-show = "!isRegister" @click = "goToScreen('/register')" >Register</v-btn>
        <v-btn text v-show = "isRegister" @click = "goToScreen('/profile')" >Profile</v-btn>
        <v-btn text @click = "goToScreen('/about')" >About</v-btn>  -->
        <v-btn text @click = "onClickLogInAndOut">{{isLogIn ? "LogOut" : "LogIn"}}</v-btn> 
    </v-app-bar>
</template>

<script>

export default {
  name: 'Heading',

  data: () => ({
    isLogIn: false,
    screenPath: "",
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
  
  },

  methods: {
    onClickLogInAndOut(){
      if(this.isLogIn){
        this.$store.commit("setIsLogIn",false);
        this.$store.commit("setUserInfo",{}); 
        if(this.$router.currentRoute.path !="/"){
          this.isLogIn = false;
          this.goToScreen("/");
        }
      } else{
        this.goToScreen("/login");
      }
    },

    goToScreen(screenPath){
      this.$router.push({ path: screenPath})
    }
  }

};

</script>

<style>

.logoApp{
  margin: 5px;
  max-height: 67px;
  max-width: 72px;
  height: 65px;
  width: 70px;
  background-image:url("../assets/logo/Ternion Logo.png");
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

</style>