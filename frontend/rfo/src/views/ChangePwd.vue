<template>
  <div>
    <v-container>
      <form>
        <v-text-field
          v-model="userId"
          label="Employee ID"
          v-show="isForgetPwd"
          required
        ></v-text-field>

        <v-text-field
          v-model="current_pwd"
          label="Current Password"
          v-show="!isForgetPwd"
          required
        ></v-text-field>

        <v-text-field
          v-model="new_pwd"
          label="New Password"
          required
        ></v-text-field>

        <v-text-field
          v-model="con_new_pwd"
          label="Confirm New Password"
          :rules="[
            (v) => (confirmPassword(v)) || 'Password not match!!!',
          ]"
          required
        ></v-text-field>

        <v-btn class="mr-4" @click="submit"> submit </v-btn>

        <v-btn @click="clear"> clear </v-btn>

        <!-- <v-alert class="mt-3" v-show="errorAlert" dense type="error">
          {{ errMsg }} <br />
        </v-alert> -->
      </form>
    </v-container>

    <span class="alertboxReg">
      <v-alert class="mt-3" v-show="errorAlert" transition="scroll-y-transition" dense 
        :type="message_type">
          {{alert_message}}
      </v-alert>
    </span>
  </div>
</template>
  
<script>
  import utils from "../utils/utils";

  export default {
    name: "profile",
    components: {},

    data() {
      return {
        con_new_pwd: "",
        new_pwd:"",
        current_pwd: "",
        userid: "",

        errorAlert: false,
        alert_message: "",
        message_type: "",

        loginUser: {},
        isForgetPwd: true,
      };
    },

    async created() {
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

      if(this.loginUser && this.loginUser.userid != undefined && this.loginUser.userid != ""){
        this.isForgetPwd = false;
      } else {
        this.isForgetPwd = true;
      }
    },

    methods: {
      validate(){
        if(this.isChangePwd){
          if(this.current_pwd == ""){
            this.alertbox("error", "Please Add Current Password!!!", 3000);

            return false;
          }
        } else {
          if(this.userId == ""){
            this.alertbox("error", "Please Add Employee ID!!!", 3000);

            return false;
          }
        }
        
        if(this.new_pwd == ""){
          this.alertbox("error", "Please Add New Password!!!", 3000);

          return false;
        }
        
        if(this.new_pwd != this.con_new_pwd){
          this.alertbox("error", "Password doesn't match!!!", 3000);

          return false;
        }

        return true;
      },

      async submit() {
        if(this.validate()){
          let param = {
            userId: "",
            oldPwd: this.current_pwd,
            newPwd: this.new_pwd,
            isForgetPwd: this.isForgetPwd ? "true" : "false",
          };

          if(this.isForgetPwd){
            param.userId = this.userId;
          } else {
            param.userId = this.loginUser.userid;
          }

          const resp = await utils.http.put("/account/password/update", param);

          if (resp.status === 200) {
            this.$router.push({ path: "/" });
          } 
          // else {
          //   this.errorAlert = true;
          //   const data = await resp.json();
          //   this.errMsg = data.message;
          // }
        }
      },

      confirmPassword(confirm){
        return this.new_pwd == confirm;
      },

      clear() {
        this.current_pwd = "";
        this.new_pwd = "";
        this.con_new_pwd = "";
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
  }
</script>

<style>

.alertboxReg {
  position: fixed;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  margin: 0 auto;
  z-index: 1;
}

</style>