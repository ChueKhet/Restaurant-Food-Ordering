<template>
  <v-app>
    <v-main>
      <heading class="fixed-header"></heading>
      <div style="height:100%">
        <router-view/>
      </div>
      
      <footing class="fixed-footer"></footing>
      
      <!-- <heading></heading>
      
      <router-view/>

      <div style="position: relative;">
        <footing class="appFooter"></footing>
      </div> -->
      
    </v-main>  
  </v-app>
</template>

<script>

import heading from "./components/heading.vue";
import footing from "./components/footing.vue";

export default {
  name: "App",

  components: {
    heading,
    footing,
  },

  data: () => ({}),
};

</script>

<style>
.appFooter{
  position: absolute;
  bottom: 0;
  padding: 0;
}
</style>