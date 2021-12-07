<template>
  <div>
    <b-container class="bv-example-row mt-3 text-center">
      <h3 class="underline-hotpink text-center">
        <b-icon icon="house-door"></b-icon> 내가 찜한 아파트
      </h3>
      <br />
      두개의 아파트를 눌러 비교해보세요!
    </b-container>
    <my-list-view></my-list-view>
  </div>
</template>

<script>
import MyListView from "@/components/mylist/MylistView.vue";

export default {
  components: {
    MyListView,
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
