import store from '../store/index.js';

export default (to, from, next) => {
  if (from.path == "/"){
    store.commit("logout");

    if(to.path != "/dashBoard"){
      next("/dashBoard");
    }

    next();
  } else {
    next();
  }
}