<template>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor, 'color-keep': isKeep }"
  >
    <!-- <b-col cols="1">{{ idx + 1 }}.</b-col> -->
    <b-col cols="11" class="align-self-center">
      {{ house.aptName }}
      <hr />
    </b-col>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      isColor: false,
      isKeep: false,
    };
  },
  props: {
    house: Object,
    idx: Number,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // TODO: 한번 누르면 파랑, 또 누르면 해제...
      // if (!this.isKeep) {
      //   this.isKeep = true;
      // } else {
      //   this.isKeep = false;
      // }
      // END

      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
    colorKeep(flag) {
      this.isKeep = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  /* background-color: lightblue; */
  color: black;
  font-weight: bold;
}
.color-keep {
  color: blue;
  font-weight: bold;
}
</style>
