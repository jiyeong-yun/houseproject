<template>
  <div class="main">
    <b-row>
      <b-col class="text-center">
        <br />
        <br />
        <br />
        <b-img
          src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdVHJT1%2FbtrlZu0LK43%2F652H9PmXQ7rIYSE2FqNWn0%2Fimg.png"
          fluid
          alt="Responsive image"
        ></b-img>
      </b-col>
    </b-row>
    <br />
    <br />
    <b-row>
      <b-col class="text-center">
        <router-link :to="{ name: 'SignIn' }" class="gologin"
          ><b-icon icon="arrow-right-circle" font-scale="1"></b-icon> 로그인
          하기</router-link
        >
      </b-col>
    </b-row>
    <br />
    <b-row>
      <b-col class="text-center">
        <router-link :to="{ name: 'Home' }" class="gohome"
          ><b-icon icon="arrow-right-circle" font-scale="1"></b-icon> 비회원으로
          시작</router-link
        >
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  name: "Main",

  data() {
    return {};
  },

  mounted() {},

  methods: {},
};
</script>

<style>
.gologin {
  color: black;
  font-size: 20px;
}
.gohome {
  color: black;
  font-size: 15px;
}
</style>
