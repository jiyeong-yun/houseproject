<template>
  <b-container class="bv-example-row mt-3 text-center">
    <!-- <h3 class="underline-steelblue"><b-icon icon="house"></b-icon> SSAFY</h3> -->
    <house-search-bar></house-search-bar>
    <b-row>
      <b-col> <house-list></house-list> </b-col>
      <b-col cols="9">
        <map-view></map-view>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import MapView from "@/components/map/MapView.vue";
import HouseSearchBar from "@/components/map/HouseSearchBar.vue";
import HouseList from "@/components/map/HouseList.vue";

export default {
  name: "Main",
  props: {
    msg: String,
  },
  components: {
    MapView,
    HouseSearchBar,
    HouseList,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
