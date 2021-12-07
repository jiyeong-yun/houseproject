<template>
  <div id="app">
    <nav-bar v-if="$route.path != '/'"></nav-bar>
    <router-view />
  </div>
</template>

<script>
import NavBar from "./components/layout/NavBar.vue";

export default {
  name: "App",
  components: {
    NavBar,
  },
};
</script>

<style>
a:hover {
  text-decoration: none;
  font-weight: bold;
}
.link {
  text-decoration: none;
  color: white;
}
.mylink {
  text-decoration: none;
  color: black;
}

a.router-link-exact-active {
  color: black;
}

a.router-link-active {
  color: black;
}
</style>
