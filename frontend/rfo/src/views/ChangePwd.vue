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
          :type="visible3 ? 'text' : 'password'"
          label="Current Password"
          v-show="!isForgetPwd"
          :append-icon="visible3 ? 'visibility' : 'visibility_off'"
          @click:append="() => (visible3 = !visible3)"
          required
        ></v-text-field>

        <v-text-field
          v-model="new_pwd"
          :type="visible1 ? 'text' : 'password'"
          label="New Password"
          :append-icon="visible1 ? 'visibility' : 'visibility_off'"
          @click:append="() => (visible1 = !visible1)"
          required
        ></v-text-field>

        <v-text-field
          v-model="con_new_pwd"
          :type="visible2 ? 'text' : 'password'"
          label="Confirm New Password"
          :append-icon="visible2 ? 'visibility' : 'visibility_off'"
          @click:append="() => (visible2 = !visible2)"
          :rules="[
            (v) => (confirmPassword(v)) || 'Password not match!!!',
          ]"
          required
        ></v-text-field>

        <v-btn class="mr-4" @click="submit" color="success"> submit </v-btn>

        <v-btn @click="clear" color="success"> clear </v-btn>
      </form>
    </v-container>

    <span class="alertbox" v-if="message_type != ''">
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
        userId: "",

        errorAlert: false,
        alert_message: "",
        message_type: "",

        loginUser: {},
        isForgetPwd: true,
        visible1:false,
        visible2:false,
        visible3:false,

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
        if(this.isForgetPwd){
          if(this.userId == ""){
            this.alertbox("error", "Please Add Employee ID!!!", 3000);

            return false;
          }
        } else {
          if(this.current_pwd == ""){
            this.alertbox("error", "Please Add Current Password!!!", 3000);

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
            //userid: param.userid,
            oldPwd: this.current_pwd,
            newPwd: this.new_pwd, 
            isForgetPwd: this.isForgetPwd ? "true" : "false",      
          };

          if(this.isForgetPwd){
            param.userid = this.userId;
          } else {
            param.userid = this.loginUser.userid;
          }
      

          const resp = await utils.http.put("/account/password/update", param);

          if (resp.status === 200) {
            const data = await resp.json();

            if(data.message.toString() === "ID_NOT_FOUND"){
              this.alertbox("error", "Employee ID Not Found!!!", 3000);
              return;
            } else if(data.message.toString() === "WRONG_PASSWORD"){
              this.alertbox("error", "Wrong Current Password!!!", 3000);
              return;
            }

            if(this.isForgetPwd){
              let exportData = {
                isPasswordChange: true
              };

              utils.goToScreenWithData("/login", "login", exportData);
            } else {
              let exportData = {
                userProfile: undefined,
                isPasswordChange: true
              };

              utils.goToScreenWithData("/profile", "profile", exportData);
            }
            
          } else {
            this.alertbox("error", "Password Not Change!!!", 3000);
          }
        }
      },

      confirmPassword(confirm){
        return this.new_pwd == confirm;
      },

      clear() {
        this.userId = "";
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

.alertbox {
  position: fixed;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  margin: 0 auto;
  z-index: 1;
}

</style>