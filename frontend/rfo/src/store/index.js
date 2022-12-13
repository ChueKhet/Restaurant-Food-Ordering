import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLogIn: false,
    userInfo: {},
  },
  getters: {
    isLogIn: (state) => {
      return isLogIn;

    },
    userInfo: (state) => {
      return userInfo;
    }
  },
  mutations: {
    setIsLogIn(state, isLogIn){
      state.isLogIn = isLogIn;
    },
    setUserInfo(state, userInfo){
      state.userInfo = userInfo;
    }
  },
  actions: {
  },
  modules: {
  }
})
