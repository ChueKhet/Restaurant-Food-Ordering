<template>
  <v-container class="my-5 fullSize d-flex flex-column justify-center align-center">
    <v-card class="pt-5 d-flex flex-column justify-center align-center" elevation="8" width="350">

      <v-card-title><h1 class="logoFontLogIn">Ternion</h1></v-card-title>

      <v-card-title>
        <v-form ref="loginForm" v-model="loginForm" class="d-flex flex-column justify-center align-center">
          <v-text-field 
            label="Employee ID"
            v-model="userid"
            @keydown.enter="onClickLogIn"
            :rules="[(v) => !!v || 'Required',]" required 
          ></v-text-field>

          <v-text-field
            type="password"
            label="Password"
            v-model="password"
            @keydown.enter="onClickLogIn"
            :rules="[(v) => !!v || 'Required',]" required
          ></v-text-field>
          <v-btn @click="onClickLogIn">Login</v-btn>
        </v-form>
      </v-card-title>
      <v-card-title><a @click="utl.goToScreen('/changepwd')">Forgot Password?</a></v-card-title>
    </v-card>

    <span class="alertbox" v-if="message_type != ''">
      <v-alert class="mt-3" v-show="errorAlert" transition="scroll-y-transition" dense 
        :type="message_type">
          {{alert_message}}
      </v-alert>
    </span>
  </v-container>
</template>

<script>
import utils from '@/utils/utils';

export default {
  name: "Login",

  props: {
    isPasswordChange: Boolean
  },

  data() {
    return {
      loading: false,
      userid: "",
      password: "",
      loginForm: false,

      errorAlert: false,
      alert_message: "",
      message_type: "",

      utl: utils
    };
  },

  created() {
    if(this.isPasswordChange){
      this.alertbox("success", "Password Changed!", 3000);
    }
  },
  
  methods: {
    async onClickLogIn() {
      if(this.$refs.loginForm.validate()){
        this.loading = true;
        const resp = await utils.http.post("/account/login",{
          userid: this.userid,
          password: this.password,
        });
        this.loading = false;

        if (resp.status === 200){
          const data = await resp.json();

          if(data.message.toString() === "ID_NOT_FOUND"){
            this.alertbox("error", "User ID Not Found!!!", 3000);
            return;
          } else if(data.message.toString() === "WRONG_PASSWORD"){
            this.alertbox("error", "Wrong Password!!!", 3000);
            return;
          } else if(data.message.toString() === "USER_NOT_FOUND"){
            this.alertbox("error", "User Not Found!!!", 3000);
            return;
          }

          this.$store.commit("setLoginUser", data);

          utils.goToScreen("/dashBoard");
        }
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
  }

}
</script>

<style>

/* .fullSize {
  height: 77.3%;
  width: 90%;
} */

.alertbox {
  position: fixed;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  margin: 0 auto;
  z-index: 1;
}

.logoLogIn{
  height: 150px;
  width: 150px;
  background-image:url("../assets/logo/Ternion Logo.png");
  background-size: 100%;
}

.logoFontLogIn{
  font-family: 'Hanalei Fill', cursive;
  font-size: 40pt;
  color: rgb(126, 87, 194, 0.7);
}
</style>