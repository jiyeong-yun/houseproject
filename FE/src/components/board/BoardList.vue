<template>
  <b-container class="bv-example-row mt-3">
    <!-- <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row> -->
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          id="my-table"
          :fields="fields"
          :items="articles"
          @row-clicked="viewArticle"
        >
        </b-table>
      </b-col>
    </b-row>
    <div class="d-flex justify-content-center">
      <b-pagination
        pills
        v-model="pg"
        :total-rows="rows"
        :per-page="spp"
        aria-controls="my-table"
        @page-click="pageChanged"
      ></b-pagination>
    </div>
  </b-container>
</template>

<script>
import { listArticle, listCount } from "@/api/board";

export default {
  name: "BoardList",
  components: {},
  data() {
    return {
      pg: 1,
      spp: 5,
      key: null,
      word: null,
      rows: 0,
      articles: [],
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    let param = {
      pg: this.pg,
      spp: this.spp,
      key: null,
      word: null,
    };
    // 해당 페이지에 맞는 개수만큼 게시글가져오기
    listArticle(
      param,
      (response) => {
        console.log("data:" + response.data.length);
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
    // 게시글 총 개수 가져오기
    listCount(
      param,
      (response) => {
        console.log("data:" + response.data);
        this.rows = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "BoardWrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "BoardView",
        params: { articleno: article.articleno },
      });
    },
    pageChanged(e, page) {
      let param = {
        pg: page,
        spp: this.spp,
        start: this.start,
        key: null,
        word: null,
      };
      listArticle(
        param,
        (response) => {
          this.articles = response.data;
          console.log("data:" + response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
