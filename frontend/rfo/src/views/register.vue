<template>
    <div>
        <h2 style="margin-left: 20px;">Register</h2>

        <div class="d-flex flex-column align-center fullScreenReg" style="margin-top: 5px;">
            <v-card elevation="8" style="width: 98%;">
                <v-form ref="registerForm" v-model="registerForm">
                    
                        <v-row>
                            <v-col>
                                <img src="" alt="">
                                <v-text-field v-model="name"
                                    :rules="[
                                        (v) => !!v || 'Required'
                                    ]"
                                    label="Name" required>
                                </v-text-field>

                                <!-- <v-calendar v-model="DOB"          
                                    :rules="[
                                        (v) => !!v || 'Required'
                                    ]"
                                    label="DOB" required>
                                </v-calendar> -->
                                <v-menu v-model="menu2" transition="scale-transition" min-width="auto"
                                    :close-on-content-click="false"
                                    :nudge-right="40"
                                    offset-y>

                                    <template v-slot:activator="{ on, attrs }">
                                        <v-text-field v-model="date" label="DOB"
                                            prepend-icon="mdi-calendar" v-bind="attrs" v-on="on"
                                            readonly>
                                        </v-text-field>
                                    </template>

                                    <v-date-picker v-model="date" @input="menu2 = false"></v-date-picker>
                                </v-menu>

                                <v-text-field v-model="name" 
                                    :counter="15" 
                                    :rules="[
                                        (v) => !!v || 'Required'
                                    ]"
                                    label="Name" required>
                                </v-text-field>
                            </v-col>
                            <v-col>
                                <img src="" alt="">
                                <v-text-field v-model="name"
                                    :rules="[
                                        (v) => !!v || 'Required'
                                    ]"
                                    label="Name" required>
                                </v-text-field>

                                <!-- <v-calendar v-model="DOB"          
                                    :rules="[
                                        (v) => !!v || 'Required'
                                    ]"
                                    label="DOB" required>
                                </v-calendar> -->
                                <v-menu v-model="menu2" transition="scale-transition" min-width="auto"
                                    :close-on-content-click="false"
                                    :nudge-right="40"
                                    offset-y>

                                    <template v-slot:activator="{ on, attrs }">
                                        <v-text-field v-model="date" label="DOB"
                                            prepend-icon="mdi-calendar" v-bind="attrs" v-on="on"
                                            readonly>
                                        </v-text-field>
                                    </template>

                                    <v-date-picker v-model="date" @input="menu2 = false"></v-date-picker>
                                </v-menu>

                                <v-text-field v-model="name" 
                                    :counter="15" 
                                    :rules="[
                                        (v) => !!v || 'Required'
                                    ]"
                                    label="Name" required>
                                </v-text-field>
                            </v-col>
                        </v-row>
                    
                    
                </v-form>
            </v-card>
            
        </div>
    </div>
    
</template>

<script>
export default {
    data(){
        return {
            menu2: false,
        };
    }

}
</script>

<style>
.fullScreenReg {
    width: 100%;
    height: 91%;
    margin: 0;
}
</style>