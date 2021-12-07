<template>
  <b-container class="bv-example-row mt-3">
    <!-- <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row> -->
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="removeArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <!-- <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
          class="mb-1"
          border-variant="dark"
          no-body
        > -->
        <b-card
          border-variant="warning"
          header-bg-variant="transparent"
          align="center"
        >
          <b-card-header class="mb-1">
            <b-col>
              <h3>{{ article.subject }}</h3>
            </b-col>
            <b-row>
              <b-col cols="2">작성자: {{ article.userid }}</b-col>
              <b-col cols="7"></b-col>
              <b-col cols="3">등록시간: {{ article.regtime }}</b-col>
            </b-row>
          </b-card-header>
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>

    <b-col class="memo text-center mt-3">댓글</b-col>
    <hr />

    <div v-for="(memo, index) in memos" :key="index">
      <b-row class="text-center mb-2">
        <b-col>{{ memo.userid }}</b-col>
        <!-- <b-col cols="7" v-html="memo.comment"></b-col> -->
        <b-col cols="7">{{ memo.comment }}</b-col>
        <b-col>{{ memo.memotime }}</b-col>
        <b-button
          class="delbtn"
          variant="outline-danger"
          @click="delmemo(memo.memono)"
          >삭제</b-button
        >
      </b-row>
    </div>

    <div>
      <b-input-group class="mb-3">
        <b-form-input v-model="comment" @keyup.enter="addAnswer"></b-form-input>
        <b-input-group-append>
          <b-button size="sm" text="Button" variant="warning" @click="addAnswer"
            >등록</b-button
          >
        </b-input-group-append>
      </b-input-group>
    </div>
  </b-container>
</template>

<script>
// import moment from "moment";
import {
  getArticle,
  deleteArticle,
  listMemo,
  writeMemo,
  deleteMemo,
} from "@/api/board";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  data() {
    return {
      text: "댓글",
      article: {},
      memos: {},
      show: true,
      comment: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    // changeDateFormat() {
    //   return moment(new Date(this.article.regtime)).format(
    //     "YYYY.MM.DD hh:mm:ss"
    //   );
    // },
  },

  created() {
    this.load();
  },
  methods: {
    load() {
      console.log("새로고침");
      getArticle(
        this.$route.params.articleno,
        (response) => {
          this.article = response.data;
        },
        (error) => {
          console.log("삭제시 에러발생!!", error);
        }
      );

      listMemo(
        this.$route.params.articleno,
        (response) => {
          this.memos = response.data;

          // this.items = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    delmemo(memono) {
      // console.log(memono);
      deleteMemo(
        memono,
        ({ data }) => {
          let msg = "댓글 삭제 실패";
          if (data === "success") {
            msg = "댓글이 삭제되었습니다.";
            this.load();
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async addAnswer() {
      await writeMemo(
        {
          userid: this.userInfo.userid,
          comment: this.comment,
          articleno: this.$route.params.articleno,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            this.comment = "";
            this.load();
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        },

        (error) => {
          console.log(error);
        }
      );
    },

    listArticle() {
      this.$router.push({ name: "BoardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "BoardUpdate",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    removeArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticle(this.article.articleno, () => {
          this.$router.push({ name: "BoardList" });
        });
      }
    },
  },
};
</script>

<style>
.memo {
  font-weight: bold;
}
.delbtn {
  margin-top: -6px;
}
</style>
