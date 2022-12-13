<template>
  <v-container class="my-5 fullSize d-flex flex-column justify-center align-center">
    <v-card class="pt-5 d-flex flex-column justify-center align-center" elevation="8" width="350">
      <!-- <v-img class="imgSize" src="../assets/logo/Ternion Logo.png"
        :aspect-ratio="1/1"
        :width="250">
      </v-img> -->

      <!-- <div class="logoLogIn"></div> -->

      <!-- <hr style="width: 100%;"> -->

      <v-card-title><h1 class="logoFontLogIn">Ternion</h1></v-card-title>

      <v-card-title>
        <v-form ref="loginForm" v-model="loginForm" class="d-flex flex-column justify-center align-center">
          <v-text-field 
            label="Name" 
            placeholder="Enter Name" 
            v-model="name" 
            :rules = "[(v) => !!v || 'Required',]" required 
          ></v-text-field>

          <v-text-field
            v-model="password"
            :counter="10"
            :rules="[
              (v) => !!v || 'Required',
              (v) =>
                (v && v.length <= 10) ||
                'Password must be less than 10 characters',
            ]"
            label="Password" required
          ></v-text-field>
          <v-btn @click = "onClickLogIn">Login</v-btn>
        </v-form>
      </v-card-title>
      <v-card-title><a>Forgot Password?</a></v-card-title>
    </v-card>
  </v-container>
</template>

<script>
import utils from '@/utils/utils';

export default {
  data() {
    return {
        loading: false,
    }

  },
  method: {
    async onClickLogIn() {
      if(this.$refs.loginForm.validate()){
        this.loading = true;
        const resp = await utils.http.post("/user/login",{
          name: this.name,
          password: this.password,
        });
        if(resp.status === 200){
          
        }
      }
    }

  }

}
</script>

<style>
.fullSize {
  height: 90%;
  width: 90%;
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
  color: #efbe47;
}
</style>