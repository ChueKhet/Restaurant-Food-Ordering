import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLogIn: false,
    userInfo: null,
    loginUser: null,
  },

  getters: {
    isLogIn: (state) => {
      let isLogIn = sessionStorage.getItem("isLogIn");
      if (isLogIn != null) {
        state.isLogIn = isLogIn;
        return isLogIn;
      } else {
        return state.isLogIn;
      }
    },

    loginUser: (state) => {
      let loginUser = sessionStorage.getItem("loginUser");

      if (loginUser != null) {
        state.loginUser = JSON.parse(loginUser);
        return JSON.parse(loginUser);
      } else {
        return state.loginUser;
      }
    },

    userInfo: (state) => {
      let userInfo = sessionStorage.getItem("userInfo");

      if (userInfo != null) {
        state.userInfo = JSON.parse(userInfo);
      }
      
      return state.userInfo;
    },
  },

  mutations: {
    setLoginUser(state, data) {
      sessionStorage.setItem("isLogIn", true);
      sessionStorage.setItem("loginUser", JSON.stringify(data.account));
      sessionStorage.setItem("userInfo", JSON.stringify(data.user));
      
      state.isLogIn = true;
      state.loginUser = data.account;
      state.userInfo = data.user;
    },

    logout(state) {
      sessionStorage.removeItem("isLogIn");
      sessionStorage.removeItem("loginUser");
      sessionStorage.removeItem("userInfo");

      state.isLogIn = false;
      state.loginUser = null;
      state.userInfo = null;
    },
  },

  actions: {
  },

  modules: {
  }
})
