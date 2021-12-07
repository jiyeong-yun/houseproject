<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead> 내 정보 확인페이지입니다. </template>

          <hr class="my-4" />

          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.username
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.joindate
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button variant="primary" href="#" class="mr-1"
            ><router-link :to="{ name: 'Modify' }" class="link"
              >정보수정</router-link
            ></b-button
          >
          <!-- <b-button v-b-modal.modify-modal variant="primary" class="mr-1"
            >정보수정</b-button
          >

          <b-modal
            id="modify-modal"
            ref="modal"
            title="비밀번호 확인"
            @show="resetModal"
            @hidden="resetModal"
            @ok="handleOk"
          >
            <form ref="form" @submit.stop.prevent="handleSubmit">
              <b-form-group
                label="비밀번호 확인"
                label-for="pwd-input"
                invalid-feedback="비밀번호를 입력해주세요"
                :state="pwdState"
              >
                <b-form-input
                  id="pwd-input"
                  v-model="pwd"
                  :state="pwdState"
                  required
                ></b-form-input>
              </b-form-group>
            </form>
          </b-modal> -->

          <b-button variant="danger" @click="remove">회원탈퇴</b-button>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { cancel } from "@/api/member";
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  data() {
    return {};
  },
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),

    remove() {
      if (confirm("정말로 탈퇴하시겠습니까?")) {
        cancel(this.userInfo.userid, ({ data }) => {
          let msg = "탈퇴 실패";
          if (data === "success") {
            msg = "탈퇴되었습니다.";
            this.SET_IS_LOGIN(false);
            this.SET_USER_INFO(null);
            sessionStorage.removeItem("access-token");
          }
          alert(msg);
          if (this.$route.path != "/home") this.$router.push({ name: "Home" });
        });
      }
    },
    // checkFormValidity() {
    //   const valid = this.$refs.form.checkValidity();
    //   this.pwdState = valid;
    //   return valid;
    // },
    // resetModal() {
    //   this.pwd = "";
    //   this.pwdState = null;
    // },
    // handleOk(bvModalEvt) {
    //   // Prevent modal from closing
    //   bvModalEvt.preventDefault();
    //   // Trigger submit handler
    //   this.handleSubmit();
    // },
    // handleSubmit() {
    //   // Exit when the form isn't valid
    //   if (!this.checkFormValidity()) {
    //     return;
    //   }
    //   // Hide the modal manually
    //   this.$nextTick(() => {
    //     if (this.userInfo.userpwd == this.pwd) {
    //       // this.$bvModal.hide("modify-modal");
    //       console.log("ok");
    //     } else {
    //       this.$router.push({ name: "Modify" });
    //       console.log("no", this.pwdok, "+", this.pwd);
    //     }
    //   });
    // },
  },
};
</script>

<style></style>
