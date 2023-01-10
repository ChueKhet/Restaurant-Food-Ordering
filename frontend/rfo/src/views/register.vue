<template>
    <div style="height: 81.5%;">
        <h2 style="margin-left: 20px; margin-top: 10px; margin-bottom: 20px;">Register</h2>

        <div class="d-flex flex-column align-center fullScreenReg" style="margin-top: 5px;">
            <v-card class="mx-3" elevation="8">
            <!-- style="width: 98%;" -->
                <v-form ref="registerForm" v-model="registerForm">            
                    
                        <v-row>
                            <v-col style="padding-left: 50px; padding-right: 50px; padding-top: 25px; padding-bottom: 25px;" cols="12" sm="8">
                                <v-title><h3>Staff Info</h3></v-title>
                                
                                <v-row>
                                    <v-col cols="12" sm="5">

                                        <div class="d-flex justify-center mt-5">
                                            <v-img
                                                v-if="imagePreviewPath != null && imagePreviewPath != ''"
                                                :src="imagePreviewPath"
                                                width="200"
                                                height="215"
                                                contain
                                            >
                                            </v-img>

                                            <v-img
                                                v-if="imagePreviewPath == null || imagePreviewPath == ''"
                                                src="../assets/plus_sign for upload.png"
                                                width="200"
                                                height="215"
                                                contain
                                            >
                                            </v-img>
                                        </div>

                                        <v-file-input
                                            v-model="image"
                                            label="Add Photo"
                                            show-size
                                            prepend-icon="mdi-camera"
                                            placeholder="Choose Photo"
                                            accept="image/png, image/jpeg"
                                            :rules="[
                                            (v) =>
                                                !v ||
                                                v.size < 10000000 ||
                                                'Image size should be less than 10 MB!',
                                            ]"
                                            @change="onChangeImage"
                                        ></v-file-input>

                                    </v-col>

                                    <v-col cols="12" sm="7">

                                        <v-row>
                                            <v-col>
                                                <v-text-field v-model="name" label="Name"></v-text-field>
                                            </v-col>
                                            <v-col>
                                                <v-menu v-model="menu2" transition="scale-transition" min-width="auto"
                                                    :close-on-content-click="false"
                                                    :nudge-right="40"
                                                    offset-y>

                                                    <template v-slot:activator="{ on, attrs }" >
                                                        <v-text-field v-model="dob" label="DOB"
                                                            prepend-icon="mdi-calendar" v-bind="attrs" v-on="on"
                                                            readonly>
                                                        </v-text-field>
                                                    </template>

                                                    <v-date-picker v-model="dob" @input="menu2 = false">
                                                    </v-date-picker>
                                                </v-menu>
                                            </v-col>
                                        </v-row>

                                        <v-row>
                                            <v-col>
                                                <v-select
                                                    :items="['Male','Female','Other']"
                                                    v-model="gender"
                                                    label="Gender"
                                                    dense>
                                                </v-select>
                                            </v-col>
                                            <v-col>
                                                <v-text-field class="m_p_top_0" v-model="phone" label="Phone No">
                                                </v-text-field>
                                            </v-col>
                                        </v-row>

                                        <v-row>
                                            <v-col  class="d-flex pt-5"
                                                cols="12"
                                                sm="1">
                                                <label>NRC</label>
                                            </v-col>
                                            <v-col
                                                class="d-flex"
                                                cols="12"
                                                sm="2"
                                                >
                                                <v-select
                                                :items="divisions"
                                                v-model="division"
                                                label="-"
                                                @change="getNrcTownship"
                                                dense
                                                ></v-select>
                                            </v-col>
                                            <v-col
                                                class="d-flex"
                                                cols="12"
                                                sm="3"
                                                >
                                                <v-select
                                                :items="townships"
                                                v-model="township"
                                                label="-"
                                                dense
                                                ></v-select>
                                            </v-col>
                                            <v-col
                                                class="d-flex"
                                                cols="12"
                                                sm="2"
                                                >
                                                <v-select
                                                :items="nations"
                                                v-model="nation"
                                                label="-"
                                                dense
                                                ></v-select>
                                            </v-col>
                                            <v-col
                                                class="d-flex"
                                                cols="12"
                                                sm="4"
                                                >
                                                <v-text-field class="m_p_top_0" v-model="nrc_num"></v-text-field>
                                            </v-col>
                                        </v-row>

                                        <v-row>
                                            <v-col>
                                                <v-text-field class="m_p_top_0" v-model="address" label="Address"></v-text-field>
                                            </v-col>
                                        </v-row>

                                    </v-col>
                                </v-row>
                                
                            </v-col>
                            <v-col style="padding-left: 50px; padding-right: 50px; padding-top: 25px; padding-bottom: 25px;" cols="12" sm="4">
                                <v-title><h3>User Info</h3></v-title>
                               
                                <v-text-field v-model="username" label="User Name"></v-text-field>

                                <!-- <v-calendar v-model="DOB"          
                                    :rules="[
                                        (v) => !!v || 'Required'
                                    ]"
                                    label="DOB" required>
                                </v-calendar> -->
                                
                                <v-text-field type="password" v-model="password" label="Password"></v-text-field>

                                <v-text-field
                                    type="password"
                                    v-model="confirmpwd"
                                    :rules="[
                                        (v) => (confirmPassword(v)) || 'Password not match!!!',
                                    ]"
                                    label="Confirm Password"
                                ></v-text-field>

                                <v-row style="margin-top: 20px; margin-left: 0px;">
                                    <v-btn
                                        :disabled="!registerForm"
                                        color="success"
                                        class="mr-4"
                                        @click="register"
                                        style="width: 91px;"
                                    >
                                        <span v-if="!loading">Register</span>
                                        <v-progress-circular
                                        v-else
                                        indeterminate
                                        color="primary"
                                        ></v-progress-circular>
                                    </v-btn>

                                    <v-btn
                                        color="success"
                                        class="mr-4"
                                        @click="clear"
                                        style="width: 91px;"
                                    >
                                        <span v-if="!loading">Clear</span>
                                        <v-progress-circular
                                        v-else
                                        indeterminate
                                        color="primary"
                                        ></v-progress-circular>
                                    </v-btn>
                                </v-row>
                            </v-col>
                        </v-row>

                        <span class="alertboxReg">
                            <v-alert class="mt-3" v-show="errorAlert" transition="scroll-y-transition" dense 
                                :type="message_type">
                                    {{alert_message}}
                            </v-alert>
                        </span>
                        <!-- <v-alert class="mt-3 alertboxReg" v-show="errorAlert" transition="scroll-y-transition" dense 
                            :type="message_type">
                                {{alert_message}}
                        </v-alert> -->
                    
                </v-form>
            </v-card>
            
        </div>
    </div>
    
</template>

<script>

import utils from "../utils/utils";

export default {
    name: "Register",

    components: {},

    data(){
        return {
            menu2: false,
            image: null,
            name: "",
            dob: "",
            gender: "",
            nrc: "",
            phone: "",
            address: "",
            username: "",
            password: "",
            confirmpwd: "",
            township: "",
            division: "",
            nation: "",
            nrc_num: "",
            fromProfile: false,
            imagePreviewPath: null,
            loading: false,
            registerForm: false,
            divisions: [],
            townships: [],
            nations: [],
            errorAlert: false,
            alert_message: "",
            message_type: ""
        };
    },

    async created() {
        await this.getNrcCode();
        this.nations = ["(N)"];
    },

    methods: {
        async getNrcCode(){
            const resp = await utils.http.get("/nrc/code");

            if (resp && resp.status === 200){
                const data = await resp.json();

                if (data) {
                    this.divisions = data;
                }
            }
        },

        async getNrcTownship(){
            if(this.division == ""){
                this.townships = [];
                return;
            }

            const resp = await utils.http.get("/nrc/all/township/" + this.division);

            if (resp.status === 200){
                const data = await resp.json();

                if (data) {
                    this.townships = data;
                }
            }
        },

        async register () {
            this.nrc = this.makeNRC();

            if (this.validate()){    //  this.$refs.registerForm.validate()
                let respImageData = null;
                const respImage = await utils.http.postMedia(
                    "/media/file/create",
                    this.image,
                    this.image.type,
                    "User"
                );

                if (respImage && respImage.status === 200) {
                    respImageData = await respImage.text();
                } else {
                    this.errorAlert = true;
                }

                this.loading = true;

                if (respImageData) {
                    let param = {
                        userData: {
                            name: this.name,
                            dob: this.dob,
                            gender: this.gender,
                            nrc: this.nrc,
                            phone: this.phone,
                            address: this.address,
                            imagePath: respImageData,
                        },
                        accountData: {
                            username: this.username,
                            password: this.password
                        }
                    };

                    const resp = await utils.http.post("/user/add", param);
                    this.loading = false;

                    if (resp.status === 200){
                        const data = await resp.json();

                        if (data) {
                            this.$store.commit("setRegister", data);
                            
                            if(this.fromProfile){
                                utils.goToScreen("/profile");
                            } else {
                                this.alertbox("success", "Registered Successful!", 3000);
                                this.clear();
                            }
                        }
                    }

                }
                
                
            }
        },

        validate(){
            if(this.image == null || this.image == ""){
                this.alertbox("error", "Please add Image!!!", 3000);
                //  Please add image
                return false;
            }

            if(this.name == ""){
                this.alertbox("error", "Please add Name!!!", 3000);

                return false;
            }

            if(this.dob == ""){
                this.alertbox("error", "Please add DOB!!!", 3000);

                return false;
            }

            if(this.gender == ""){
                this.alertbox("error", "Please add gender!!!", 3000);

                return false;
            }

            if(this.nrc == ""){
                this.alertbox("error", "Please add NRC!!!", 3000);

                return false;
            }

            if(this.phone == ""){
                this.alertbox("error", "Please add Phone!!!", 3000);

                return false;
            }

            if(this.address == ""){
                this.alertbox("error", "Please add Address!!!", 3000);

                return false;
            }

            if(this.username == ""){
                this.alertbox("error", "Please add Username!!!", 3000);

                return false;
            }

            if(this.password == ""){
                this.alertbox("error", "Please add Password!!!", 3000);

                return false;
            }

            return true;
        },

        makeNRC(){
            return this.division + "/" + this.township + this.nation + this.nrc_num;
        },

        confirmPassword(confirm){
            return this.password == confirm;
        },
        
        clear(){
            this.image = null;
            this.name = "";
            this.dob = "";
            this.gender = "";
            this.nrc = "";
            this.phone = "";
            this.address = "";
            this.username = "";
            this.password = "";
            this.confirmpwd = "";
            this.township = "";
            this.division = "";
            this.nation = "";
            this.nrc_num = "";
            this.imagePreviewPath = null;
        },

        onChangeImage(image) {
            this.imagePreviewPath = URL.createObjectURL(image);
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
};

</script>

<style>

.fullScreenReg {
    width: 100%;
    height: 100%;
    margin: 0;
}

.alertboxReg {
  position: fixed;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  margin: 0 auto;
  z-index: 1;
}

.m_p_top_0{
    margin-top: 0px !important;
    padding-top: 0px !important;
}

</style>