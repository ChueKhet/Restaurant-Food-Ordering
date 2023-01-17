import store from '../store/index.js';

// const isAuthRoute = route => route.path == "/dashBoard";   //   route.path.indexOf('/login') !== -1;

// const isLogged = () => storeLoggedIn;   // || loadSessionFromLocalStorage;
// const storeLoggedIn = () => store.state.isLogIn;

// const loadSessionFromLocalStorage = () => {
  // if localstorage has token
  //   commit a mutation for loggedIn and then return true
  // else return false
// };

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