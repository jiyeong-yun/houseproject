<template>
  <b-row>
    <b-col></b-col>
    <b-col cols="8">
      <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
        <h2>회원 정보 수정</h2>
        <b-form class="text-left">
          <b-form-group label="아이디" label-for="userid">
            <b-form-input id="userid" required readonly v-model="userid">
            </b-form-input>
          </b-form-group>
          <b-form-group label="이름" label-for="username">
            <b-form-input
              id="username"
              required
              placeholder="이름을 입력하세요"
              v-model="username"
            ></b-form-input>
          </b-form-group>
          <b-form-group label="이메일" label-for="useremail">
            <b-form-input
              id="useremail"
              required
              placeholder="이메일을 입력하세요"
              v-model="useremail"
            ></b-form-input>
          </b-form-group>

          <b-form-group label="비밀번호" label-for="userpwd">
            <b-form-input
              type="password"
              id="userpwd"
              required
              placeholder="비밀번호를 입력하세요"
              v-model="userpwd"
            ></b-form-input>
          </b-form-group>
          <b-row>
            <b-col></b-col>
            <b-button
              type="button"
              variant="success"
              class="m-1 mr-3"
              @click="modifyInfo"
              >수정하기</b-button
            >
          </b-row>
        </b-form>
      </b-card>
    </b-col>
    <b-col></b-col>
  </b-row>
</template>

<script>
import { modify } from "@/api/member";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberModify",

  data() {
    return {
      userid: null,
      username: null,
      useremail: null,
      userpwd: null,
    };
  },

  created() {
    this.userid = this.userInfo.userid;
    this.username = this.userInfo.username;
    this.useremail = this.userInfo.email;
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    modifyInfo() {
      modify(
        {
          username: this.username,
          userid: this.userInfo.userid,
          email: this.useremail,
          userpwd: this.userpwd,
          joindate: this.userInfo.joindate,
        },
        ({ data }) => {
          let msg = "수정 실패! 모든 칸을 채워주세요";
          if (data === "success") {
            msg = "회원정보가 수정되었습니다.";
            console.log(this.userInfo);
            this.$store.commit("memberStore/SET_USER_INFO", {
              username: this.username,
              userid: this.userInfo.userid,
              email: this.useremail,
              userpwd: this.userpwd,
              joindate: this.userInfo.joindate,
            });
          }
          alert(msg);
          this.$router.push({ name: "MyPage" });
        }
      );
    },
  },
};
</script>

<style scoped></style>
