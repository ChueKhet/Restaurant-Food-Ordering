import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLogIn: false,
    loginUser:{},
    userInfo: {},
    orderData: {}
  },

  getters: {
    isLogIn: (state) => {
      return isLogIn;
    },
    loginUser:(state)=>{
      return loginUser
    },

    userInfo: (state) => {
      return userInfo;
    },

    orderInfo: (state) => {
      return orderData;
    },
  },

  mutations: {
    setIsLogIn(state, isLogIn){
      state.isLogIn = isLogIn;
    },
    setloginUser(state,loginUser){
      state.loginUser=loginUser;
    },

    setUserInfo(state, userInfo){
      state.userInfo = userInfo;
    },

    setOrderInfo(state, orderData){
      state.orderData = orderData;
    },
  },

  actions: {
  },

  modules: {
  }
})
