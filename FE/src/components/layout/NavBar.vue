<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="warning">
      <b-navbar-brand href="#">
        <router-link to="/home">
          <img
            src="@/assets/logo.png"
            class="d-inline-block align-middle"
            width="50px"
            alt="Kitten"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Home' }" class="link"
              ><b-icon icon="map" font-scale="1"></b-icon> HOME</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Board' }" class="link"
              ><b-icon icon="chat-dots" font-scale="1"></b-icon>
              게시판</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Mylist' }" class="link"
              ><b-icon class="heart" icon="heart" font-scale="1"></b-icon>
              찜목록</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item class="align-self-center">
            <b-icon icon="emoji-smile" font-scale="1"></b-icon>
            {{ userInfo.username }}님 환영합니다.</b-nav-item
          >
          <b-nav-item class="align-self-center"
            ><router-link
              :to="{ name: 'MyPage' }"
              class="mylink align-self-center"
              >마이페이지</router-link
            ></b-nav-item
          >
          <b-nav-item
            class="mylink align-self-center"
            @click.prevent="onClickLogout"
            >로그아웃</b-nav-item
          >
          <b-nav-item
            v-if="userInfo.userid == 'admin'"
            class="mylink align-self-center"
            :to="{ name: 'Admin' }"
            >회원관리</b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item>
            <b-button v-b-modal.login-modal class="member" variant="warning">
              <b-icon icon="lock"></b-icon> 로그인</b-button
            >
            <!-- <b-button variant="warning" @click="login" class="member">
              <b-icon icon="lock"></b-icon>
              로그인</b-button
            > -->
            <b-button variant="warning" @click="signup" class="member">
              <b-icon icon="person-fill"></b-icon>
              회원가입</b-button
            >
          </b-nav-item>

          <b-modal id="login-modal">
            <template #modal-header><h5>로그인</h5> </template>

            <template #default>
              <!-- <b-alert show variant="danger" v-if="isLoginError"
                >아이디 또는 비밀번호를 확인하세요!</b-alert
              > -->
              <b-form-group label="아이디:" label-for="userid">
                <b-form-input
                  id="userid"
                  v-model="user.userid"
                  required
                  placeholder="아이디를 입력하세요"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="비밀번호:" label-for="userpwd">
                <b-form-input
                  type="password"
                  id="userpwd"
                  v-model="user.userpwd"
                  required
                  placeholder="비밀번호를 입력하세요"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
            </template>

            <template #modal-footer="{ cancel }">
              <b-button variant="outline-secondary" @click="cancel()">
                취소
              </b-button>

              <b-button
                type="button"
                variant="primary"
                class="m-1"
                @click="confirm"
                >로그인</b-button
              >
            </template>
          </b-modal>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NaviBar",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo", "isLoginError"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),

    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      this.user.userid = "";
      this.user.userpwd = "";
      if (this.$route.path != "/home") this.$router.push({ name: "Home" });
    },

    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        if (this.$route.path != "/home") this.$router.push({ name: "Home" });
      } else {
        alert("아이디 또는 비밀번호를 확인하세요");
        this.user.userid = "";
        this.user.userpwd = "";
      }
    },
    signup() {
      // document.querySelector("b-navbar-nav").className("#login");
      if (this.$route.path != "/user/signup")
        this.$router.push({ name: "SignUp" });
    },
    login() {
      if (this.$route.path != "/user/signin")
        this.$router.push({ name: "SignIn" });
    },
  },
};
</script>

<style>
.member {
  border: none;
}
.member:hover {
  font-weight: bold;
}
</style>
