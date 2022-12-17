<template>
  <v-app>
    <v-main>
      
      <heading></heading>
      
      <router-view/>

      <footing></footing>
      
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

</style>