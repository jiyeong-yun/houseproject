<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="text-center mt-2 mb-2 btitle">
      <b-col cols="2" class="title">회원 아이디</b-col>
      <b-col cols="7" class="title">회원 이름</b-col>
      <b-col cols="2" class="title">회원 이메일</b-col>
      <b-col cols="1" class="title">삭제</b-col>
    </b-row>
    <hr />
    <div v-for="(member, index) in members" :key="index">
      <b-row class="text-center mb-2">
        <b-col>{{ member.userid }}</b-col>
        <b-col cols="7">{{ member.username }}</b-col>
        <b-col>{{ member.email }}</b-col>
        <b-button
          class="delbtn"
          variant="outline-danger"
          @click="delmember(member.userid)"
          >삭제</b-button
        >
      </b-row>
      <hr />
    </div>
  </b-container>
</template>

<script>
import { admin, cancel } from "@/api/member.js";

export default {
  name: "Adminview",

  data() {
    return {
      members: [],
      //   fields: ["회원 아이디", "회원 이름", "회원 이메일"],
      fields: [
        { key: "userid", label: "회원 아이디" },
        { key: "username", label: "회원 이름" },
        { key: "email", label: "회원 이메일" },
      ],
    };
  },
  computed: {},
  created() {
    this.load();
  },

  mounted() {},

  methods: {
    delmember(userid) {
      if (confirm("정말로 탈퇴시키겠습니까?")) {
        cancel(userid, ({ data }) => {
          let msg = userid + "님 탈퇴 실패";
          if (data === "success") {
            msg = userid + "님을 탈퇴시켰습니다.";
            this.load();
          }
          alert(msg);
        });
      }
    },
    load() {
      admin(
        ({ data }) => {
          console.log(data);
          this.members = data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
.title {
  font-weight: bold;
}
</style>
