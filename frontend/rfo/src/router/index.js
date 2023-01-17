import Vue from 'vue';
import VueRouter from 'vue-router';

import dashBoard from '../views/DashBoard.vue';
import homeView from '../views/HomeView.vue';
import login from '../views/logIn.vue';
import register from '../views/Register.vue';
import admin_user_list from '../views/admin_user_list.vue';
import menu from '../views/Menu.vue';
import Ingredients from '../views/Ingredients.vue';
import Kitchen from '../views/Kitchen.vue';
import Profile from '../views/Profile.vue';
import ChangePwd from '../views/ChangePwd.vue';
import SaleHeaders from '../views/SaleHeaders.vue';
import Payment from '../views/Payment.vue';

// import store from '../store/index.js';
import beforeEach from './beforeEach.js';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: homeView,
  },
  {
    path: '/dashBoard',
    name: 'dashBoard',
    component: dashBoard,
    props: true
  },
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/register',
    name: 'register',
    component: register
  },
  {
    path: '/admin_user_list',
    name: 'admin_user_list',
    component: admin_user_list,
    props: true
  },
  {
    path: '/menu',
    name: 'menu',
    component: menu
  },
  {
    path: '/kitchen',
    name: 'kitchen',
    component: Kitchen
  },
  {
    path: '/ingredients',
    name: 'ingredients',
    component: Ingredients
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile,
    props: true
  },
  {
    path: '/changepwd',
    name: 'changepwd',
    component: ChangePwd
  },
  {
    path: "/sale_headers",
    name: 'sale_headers',
    component: SaleHeaders
  },
  // {
  //   path: "/sale_detail/:headerId",
  //   name: 'sale_detail',
  //   component: SaleDetail
  // },
  {
    path: "/payment/:headerData",
    name: "payment",
    component: Payment,
    props: true
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: routes
});

router.beforeEach(beforeEach);

// router.beforeEach((to, from, next) => {
//   store.commit("logout");
//   next();
// });

export default router;