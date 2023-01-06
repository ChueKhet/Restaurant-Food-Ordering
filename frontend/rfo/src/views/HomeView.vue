<template>
  <v-row style="height: 100%;">

    <v-col cols="12" :sm="(loginUser && loginUser.role == 0) ? 6 : 12">
      <v-row>
        <v-col cols="12" 
        :sm="(loginUser && loginUser.role == 0) ? 12 : 6" 
        :md="(loginUser && loginUser.role == 0) ? 6 : 3" 
        :lg="(loginUser && loginUser.role == 0) ? 4 : 2" 
        v-for="menu in menuList" :key="menu.id">

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
                <div class="card__content d-flex flex-column align-center justify-center">

                  <div class="card__header d-flex flex-column align-center justify-center">
                    <v-icon color="deep-purple lighten-1" dark @click="addToCart(menu)"
                      style="position: absolute; top: 3px; left: 10px;"
                      v-show="loginUser && loginUser.role == 0">
                        shopping_cart
                    </v-icon>

                    <v-icon color="deep-purple lighten-1" dark @click="cardFlip(menu.id)"
                      style="position: absolute; top: 3px; right: 10px;">
                        mdi-cached
                    </v-icon>

                    <img :src="imagePath + menu.imagePath" alt="" class="pp" style="position: absolute;"/>
                  </div>
                  
                  <div class="card__body d-flex flex-column align-center justify-center">
                    <h4 class="card-title">{{menu.code}}</h4>
                    <h4 class="card-title">{{menu.description}}</h4>
                    <label class="card-title">price : {{menu.price}}</label>
                  </div>

                </div>
              </div>

              <div class="card__face card__face--back">
                <v-icon color="deep-purple lighten-1" dark @click="addToCart(menu)"
                  style="position: absolute; top: 3px; left: 10px;"
                  v-show="loginUser && loginUser.role == 0">
                    shopping_cart
                </v-icon>

                <v-icon color="deep-purple lighten-1" dark @click="cardFlip(menu.id)"
                  style="position: absolute; top: 3px; right: 10px;">
                    mdi-cached
                </v-icon>

                <ul class="ml-3 mr-3" style="width: 100%; padding: 0px;">
                  <li v-for="ingredient in menu.ingredientList" :key="ingredient.id"
                    style="list-style-type: none;">
                      <v-row class="d-flex">
                        <v-col class="d-flex align-center">
                          <label style="font-size: 18px;">{{ingredient.description}}</label>
                        </v-col>
                        <v-col>
                          <v-select 
                            class="ver-center"
                            v-model="ingredient.status"
                            :items="remarkList"
                            item-text="desc"
                            item-value="code"
                            style="padding-left: 0px;"
                            dense>
                          </v-select>
                        </v-col>
                      </v-row>
                  </li>
                </ul>
              </div>

            </div>
          </div>

        </v-col>
      </v-row>
    </v-col>

    <v-col cols="12" sm="6" style="position: sticky;" v-show="loginUser && loginUser.role == 0">
      <v-card class="ma-3 pa-3" style="height: 630px; position: sticky; right: 0; top: 80px;">

        <v-card-title>

          <v-row class="pa0_ma0">
            <div class="d-flex">
              <h3 style="margin-right: 5px;">Table No: </h3>
              <v-text-field type="number" class="pa0_ma0" min="1" v-model="saveHeaderData.tableNo" 
                style="width: 50px; font-size: 20px;">
              </v-text-field>
            </div>
          </v-row>
          
        </v-card-title>

        <div>

          <v-row>
            <v-data-table class="elevation-4" style="width: 100%; height: 490px;"
              :headers="orderHeaders"
              :items="saveDetailList"
              :footer-props="{
                'items-per-page-options':[8],
                'disable-items-per-page': true,
              }">

                <template v-slot:item.qty="{item}">
                  <v-text-field type="number" class="pa0_ma0" min="1" v-model="item.qty" 
                    style="width: 30px;" @change="changeQTY(item)">
                  </v-text-field>
                </template>

                <template v-slot:item.btn="{item}">
                  <v-btn class="mr-3" color="primary" fab x-small @click="onClickRemoveBtn(item)">
                    <v-icon>mdi-delete</v-icon>
                  </v-btn>
                </template>

            </v-data-table>
          </v-row>

          <v-row>
            <v-col class="d-flex flex-column align-center justify-center">
              <v-btn color="primary" @click="saveTransition">Order</v-btn>
            </v-col>
          </v-row>

        </div>

      </v-card>
    </v-col>
    <span class="alertboxReg">
      <v-alert class="mt-3" v-show="errorAlert" transition="scroll-y-transition" dense 
        :type="message_type">
          {{alert_message}}
      </v-alert>
    </span>
  </v-row>
  
</template>

<script>
import utils from "../utils/utils.js";
import payment from "./Payment.vue"

export default {
  name: 'Home',

  components: {},

  data() {
    return {
      imagePath: utils.constant.imagePath,
      loginUser: {},
      menuList: [],
      saveHeaderData: this.getHeaderData(),
      saveDetailList: [],
      detailListSize: 0,
      orderHeaders: [
        {
          text: 'Description',
          value: 'menuDesc',
          sortable: true,
        },
        {
          text: 'Remark',
          value: 'remark',
        },
        {
          text: 'QTY',
          value: 'qty',
        },
        {
          text: 'Actions',
          value: 'btn',
        },
      ],
      remarkList: [
        {
          "code": 0,
          "desc": "Normal"
        },
        {
          "code": 1,
          "desc": "Remove"
        },
        {
          "code": 2,
          "desc": "Less"
        },
        {
          "code": 3,
          "desc": "More"
        },
      ],
      errorAlert: false,
      alert_message: "",
      message_type: ""
    };
  },

  async created() {
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

    this.saveHeaderData = this.getHeaderData();
    await this.fetchMenuList();
  },

  methods: { 
    async fetchMenuList(){
      const resp = await utils.http.get("/menu/ingredient/all");

      if(resp && resp.status == 200){
        this.menuList = await resp.json();
      }
    },

    cardFlip(id){
      let innerId = "#inner" + id;
      const card = document.querySelector(innerId);

      card.classList.toggle('is-flipped');
    },

    addToCart(item){
      this.detailListSize++;

      let tempData = {
        id: this.detailListSize,
        qty: 1,
        menuId: item.id,
        menuCode: item.code,
        menuDesc: item.description,
        price: item.price,
        totalPrice: item.price,
        remark: this.allRemark(item.ingredientList),
        orderStatus: 0    //    0 = Ordered, 1 = Served
      };

      let isExist = this.saveDetailList.some(
        data => {
          let boo = (data.menuId == tempData.menuId && data.menuCode == tempData.menuCode && data.remark == tempData.remark);

          if(boo){
            data.qty++;
            data.totalPrice = this.getTotalPrice(data.qty, data.price);
          }

          return boo;
        }
      );

      if(!isExist){
        this.saveDetailList.push(tempData);
      }
    },

    changeQTY(item){
      item.totalPrice = this.getTotalPrice(item.qty, item.price);
    },

    getTotalPrice(qty, price){
      return qty * price;
    },

    allRemark(ingredients){
      let tempRemark = "";
      let remarkDesc = "";

      ingredients.map(
        data => {
          if(data.status != 0){
            remarkDesc = this.remarkList.filter(
              rlData => {
                return rlData.code == data.status;
              } 
            )[0].desc;

            tempRemark += data.description + " : " + remarkDesc + " ";
          }
        }
      );

      if(tempRemark == ""){
        tempRemark = "-";
      }

      return tempRemark;
    },

    onClickRemoveBtn(item){
      let isExist = this.saveDetailList.filter(
        data => {
          return data.id == item.id;
        }
      );

      if(isExist){
        let temp = this.saveDetailList.filter(
          data => {
            return data.id != item.id;
          }
        );

        this.detailListSize--;
        this.saveDetailList = temp;
      }
    },

    validate(){
      if(this.saveHeaderData.tableNo == ""){
        this.alertbox("error", "Please add TableNo!!!", 3000);

        return false;
      }
      
      if(this.saveDetailList.length < 1){
        this.alertbox("error", "Please add Menu!!!", 3000);

        return false;
      }

      return true;
    },

    async saveTransition(){
      if(this.validate()){
        this.loading = true;
        let totalAmount = 0;
                
        this.saveDetailList.map(
          data => {
            totalAmount += data.totalPrice;

            this.saveHeaderData.detailList.push(data);
          }
        );

        this.saveHeaderData.totalAmount = totalAmount;
        this.saveHeaderData.userId=+this.$store.state.userInfo?.id;
        
        const resp = await utils.http.post("/sale/order/confirm", this.saveHeaderData);
        this.loading = false;

        if(resp && resp.status == 200){
          this.clear();
          let exportData = {
            headerData: {}
          };

          exportData.headerData = await resp.json();
          
          utils.goToScreenWithData("/payment", "payment", exportData);
        } else {
          console.log("FAIL!!!");
        }
      }
    },

    clear(){
      this.saveHeaderData = this.getHeaderData();
      this.saveDetailList = [];
      this.detailListSize = 0;
    },

    getHeaderData(){
      return {
        id: "",
        slipNo: "",
        tableNo: "",
        orderStatus: 0,     //    0 = Order Confirmed, 1 = Sales Confirmed, 2 = Served
        totalAmount: "",
        detailList: [],
      };
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

/* .ver-center, .ver-center .v-input, .ver-center .v-input .v-input__control {
  display: flex;
  align-items: center;
} */

.alertboxReg {
  position: fixed;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  margin: 0 auto;
  z-index: 1;
}

/******** card flip ********/

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

.card__face--back {
  background-image: linear-gradient(to bottom right, var(--primary), var(--secondary));
  display: flex;
  align-items: center;
  justify-content: center;
}

.card__face--back li {
  color: #000;
  font-size: 32px;
}

.card__face--back {
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

.card__face--back:after {
  z-index: 1;
}

.pp {
  display: block;
  width: 200px;
  height: 200px;
  max-width: 200px;
  max-height: 200px;
  margin: 0;
  border-radius: 50%;
  background-color: #FFF;
  border: 5px solid #FFF;
  object-fit: cover;
}

.card__header li {
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

/****************/

.pa0_ma0{
  padding: 0px !important;
  margin: 0px !important;
}

</style>