import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import login from '../views/logIn.vue'
import register from '../views/register.vue'
import admin_user_list from '../views/admin_user_list.vue'
import Menu from '../views/Menu.vue'
import Ingredients from '../views/Ingredients.vue'
//import Chef from '../views/Chef.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
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
    component: admin_user_list
  },
  {
    path: '/menu',
    name: 'menu',
    component: Menu
  },
  {
    path: '/ingredients',
    name: 'ingredients',
    component: Ingredients
  },
  // {
  //   path: '/chef',
  //   name: 'chef',
  //   component: Chef
  // },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
