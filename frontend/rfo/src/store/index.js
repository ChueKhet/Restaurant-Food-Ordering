import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLogIn: false,
    loginUser:{},
    userInfo: {},
    orderData: {},
    loginUser: null,
  },

  getters: {
    loginUser: (state) => {
      let loginUser = sessionStorage.getItem("loginUser");
      if (loginUser != null) {
        state.loginUser = JSON.parse(loginUser);
        return JSON.parse(loginUser);
      } else {
        return state.loginUser;
      }
    },
    isLogIn: (state) => {
      let isLogIn = sessionStorage.getItem("isLogin");
      if (isLogIn != null) {
        state.isLogIn = isLogIn;
        return isLogIn;
      } else {
        return state.isLogIn;
      }
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
    setLoginUser(state, user) {
      sessionStorage.setItem("loginUser", JSON.stringify(user));
      sessionStorage.setItem("isLogin", true);
      state.loginUser = user;
      state.isLogIn = true;
    },

    logout(state) {
      sessionStorage.removeItem("loginUser");
      sessionStorage.removeItem("isLogin");
      state.loginUser = null;
      state.isLogIn = false;
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

    setRegister(state, data){
      state.loginUser = data;
    },
  },

  actions: {
  },

  modules: {
  }
})
