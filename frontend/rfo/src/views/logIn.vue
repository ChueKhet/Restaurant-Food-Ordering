<template>
  <v-container class="my-5 fullSize d-flex flex-column justify-center align-center">
    <v-card class="pt-5 d-flex flex-column justify-center align-center" elevation="8" width="350">

      <v-card-title><h1 class="logoFontLogIn">Ternion</h1></v-card-title>

      <v-card-title>
        <v-form ref="loginForm" v-model="loginForm" class="d-flex flex-column justify-center align-center">
          <v-text-field 
            label="Employee ID" 
            placeholder="Enter Employee ID" 
            v-model="userid" 
            :rules = "[(v) => !!v || 'Required',]" required 
          ></v-text-field>

          <v-text-field
            v-model="password"
            type="password"
            :rules="[
              (v) => !!v || 'Required',
              (v) =>
                (v && v.length <= 10) ||
                'Password must be less than 10 characters',
            ]"
            label="Password" required
          ></v-text-field>
          <v-btn @click="onClickLogIn">Login</v-btn>
        </v-form>
      </v-card-title>
      <v-card-title><a @click="utl.goToScreen('/changepwd')">Forgot Password?</a></v-card-title>
    </v-card>
  </v-container>
</template>

<script>
import utils from '@/utils/utils';

export default {
  name: "Login",
  data() {
    return {
      loading: false,
      userid: "",
      password: "",
      loginForm: false,
      utl: utils
    };
  },
  
  methods: {
    async onClickLogIn() {
      if(this.$refs.loginForm.validate()){
        this.loading = true;
        const resp = await utils.http.post("/account/login",{
          userid: this.userid,
          password: this.password,
        });

        if (resp.status === 200){
          const data = await resp.json();
          this.loading = false;

          if (data) {
            this.$store.commit("setLoginUser", data);

            utils.goToScreen("/");                     
          }
        }
      }
    }

  }

}
</script>

<style>
/* .fullSize {
  height: 77.3%;
  width: 90%;
} */

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