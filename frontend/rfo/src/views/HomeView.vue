<template>
  <div>
  <!-- style="height: 95%;" -->
    <v-row>

      <v-col cols="12" sm="9">
        <v-row>
          <v-col cols="12" sm="6" md="4" lg="3" v-for="menu in menuList" :key="menu">

            <!-- <v-card class="ma-2" elevation="8" width="220">

              <div align="right" style="padding-top: 5px; margin-right: 5px;">
                <v-btn color="deep-purple lighten-1" fab dark small>
                  <v-icon dark>mdi-cached</v-icon>
                </v-btn>
              </div>

              <div class="d-flex flex-column align-center justify-center">
                <v-img src="../assets/saved Images/menu/logo.png" width="200" height="215"></v-img>

                <v-card-title>{{menu.description}}</v-card-title>
                <v-card-text align="center">price : {{menu.code}}</v-card-text>
              </div>

            </v-card> -->

            <div class="card ma-3">
              <div class="card__inner" :id="'inner' + menu.id">

                <div class="card__face card__face--front">
                  <v-icon color="deep-purple lighten-1" dark @click="cardFlip(menu.id)"
                    style="position: absolute; top: 3px; right: 10px;">
                      mdi-cached
                  </v-icon>

                  <h2>Card Front</h2>
                </div>

                <div class="card__face card__face--back">
                  <div class="card__content d-flex flex-column align-center justify-center">

                    <div class="card__header d-flex flex-column align-center justify-center">
                      <v-icon color="deep-purple lighten-1" dark @click="cardFlip(menu.id)"
                        style="position: absolute; top: 3px; right: 10px;">
                          mdi-cached
                      </v-icon>

                      <img src="../assets/saved Images/menu/logo.png" alt="" class="pp" style="position: absolute;"/>
                    </div>

                    <div class="card__body d-flex flex-column align-center justify-center">
                      <card-title><h4>{{menu.description}}</h4></card-title>
                      <card-title>price : {{menu.code}}</card-title>
                    </div>

                  </div>
                </div>

              </div>
            </div>

          </v-col>
        </v-row>
      </v-col>

      <v-col cols="12" sm="3">
        
      </v-col>

    </v-row>
    
  </div>
</template>

<script>
import utils from '../utils/utils.js'

export default {
  name: 'Home',

  components: {},

  data() {
    return {
      menuList:"",
    };
  },

  async created() {
    await this.fetchMenuList();
  },

  methods: { 
    async fetchMenuList(){
      const resp = await utils.http.get("/menu/list");

      if(resp && resp.status == 200){
        this.menuList = await resp.json();

        // let temp = {
        //   "id": 1,
        //   "code": "12000",
        //   "description": "Kyay Oo"
        // }
        // this.menuList = [temp];
      }
    },

    cardFlip(id){
      let innerId = "#inner" + id;
      const card = document.querySelector(innerId);

      card.classList.toggle('is-flipped');
    }
  }
}

</script>

<style>

:root {
  --primary: #FFCE00;
  --secondary: #FE4880;
  --dark: #212121;
  --light: #F3F3F3;
}

.card {
  /* width: 220px; */
  width: 100%;
  height: 300px;
  perspective: 1000px;
}

.card__inner {
  width: 100%;
  height: 100%;
  transition: transform 1s;
  transform-style: preserve-3d;
  /* cursor: pointer; */
  position: relative;
}

.card__inner.is-flipped {
  transform: rotateY(180deg);
}

.card__face {
  position: absolute;
  width: 100%;
  height: 100%;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  overflow: hidden;
  border-radius: 16px;
  box-shadow: 0px 3px 18px 3px rgba(0, 0, 0, 0.2);
}

.card__face--front {
  background-image: linear-gradient(to bottom right, var(--primary), var(--secondary));
  display: flex;
  align-items: center;
  justify-content: center;
}

.card__face--front h2 {
  color: #000;
  font-size: 32px;
}

.card__face--front {
  background-color: var(--light);
  transform: rotateY(180deg);
}

.card__content {
  width: 100%;
  height: 100%;
}

.card__header {
  position: relative;
  width: 100%;
  height: 100%;
}

.card__header:after {
  content: '';
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-image: linear-gradient(to bottom left, var(--primary) 10%, var(--secondary) 115%);
  z-index: -1;
  /* border-radius: 0px 0px 50% 0px; */
}

.pp {
  display: block;
  width: 200;
  height: 215;
  margin: 0;
  border-radius: 50%;
  background-color: #FFF;
  border: 5px solid #FFF;
  object-fit: cover;
}

.card__header h2 {
  color: #FFF;
  font-size: 32px;
  font-weight: 900;
  text-transform: uppercase;
  text-align: center;
}

.card__body {
  padding: 5px;
}

.card__body h3 {
  color: var(--dark);
  font-size: 24px;
  font-weight: 600;
  margin-bottom: 15px;
}

.card__body p {
  color: var(--dark);
  font-size: 18px;
  line-height: 1.4;
}

</style>