<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <h2>회원가입</h2>
          <b-form class="text-left">
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                type="text"
                id="username"
                required
                placeholder="이름을 입력하세요"
                v-model="username"
                @keyup.enter="join"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="useremail">
              <b-form-input
                type="email"
                id="useremail"
                required
                placeholder="이메일을 입력하세요"
                v-model="useremail"
                @keyup.enter="join"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                type="text"
                id="userid"
                required
                placeholder="아이디를 입력하세요"
                v-model="userid"
                @keyup.enter="join"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                required
                placeholder="비밀번호를 입력하세요"
                v-model="userpwd"
                @keyup.enter="join"
              ></b-form-input>
            </b-form-group>
            <b-row>
              <b-col></b-col>
              <b-button
                type="button"
                variant="success"
                class="m-1 mr-3"
                @click="join"
                >가입하기</b-button
              >
            </b-row>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { join } from "@/api/member";
export default {
  name: "MemberJoin",
  data() {
    return {
      userid: null,
      userpwd: null,
      useremail: null,
      username: null,
    };
  },
  methods: {
    join() {
      // console.log("가입하기 click");
      join(
        {
          username: this.username,
          userid: this.userid,
          userpwd: this.userpwd,
          email: this.useremail,
        },
        ({ data }) => {
          let msg = "모든 칸을 채워주세요";
          if (data === "success") {
            msg = "회원가입되었습니다.";
            this.$router.push({ name: "SignIn" });
          }
          (this.userid = null),
            (this.userpwd = null),
            (this.useremail = null),
            (this.username = null),
            alert(msg);
        }
      );
    },
  },
};
</script>

<style></style>
