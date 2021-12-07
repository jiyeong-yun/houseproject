<template>
  <b-container>
    <b-table
      striped
      hover
      :items="items"
      :fields="fields"
      select-mode="multi"
      responsive="sm"
      ref="selectableTable"
      selectable
      @row-selected="onRowSelected"
    ></b-table>

    <b-row>
      <b-col class="btn">
        <b-button
          class="mr-2"
          variant="outline-primary"
          @click="clearSelected"
          v-if="card1 != '' || card2 != ''"
          >비교 카드 모두 비우기</b-button
        >
        <b-button variant="outline-danger" @click="deleteZzimAll"
          >찜 목록 모두 삭제</b-button
        >
      </b-col>
    </b-row>

    <b-row class="text-center mb-3">
      <b-col cols="6">
        <b-card no-body style="max-width: 20rem" v-if="card1 != ''">
          <template #header>
            <h4 class="mb-0">{{ card1.aptName }}</h4>
          </template>

          <b-card-body>
            <b-card-title>{{ card1.recentPrice }}(만원)</b-card-title>
            <!-- <b-card-sub-title class="mb-2">{{ priceDiff1 }}</b-card-sub-title> -->
            <b-card-text class="restxt">
              {{ priceDiff1 }} <br />{{ yearDiff1 }}</b-card-text
            >
          </b-card-body>

          <b-list-group flush>
            <b-list-group-item
              >건축년도: {{ card1.buildYear }}</b-list-group-item
            >
            <b-list-group-item
              >{{ card1.sidoName }} {{ card1.gugunName }} {{ card1.dongName }}
              {{ card1.jibun }}</b-list-group-item
            >
          </b-list-group>

          <b-card-body>
            <b-button variant="outline-danger" @click="deleteZzim(0)"
              >찜 목록에서 삭제</b-button
            >
          </b-card-body>
        </b-card>
      </b-col>

      <b-col cols="6">
        <b-card no-body style="max-width: 20rem" v-if="card2 != ''">
          <template #header>
            <h4 class="mb-0">{{ card2.aptName }}</h4>
          </template>

          <b-card-body>
            <b-card-title>{{ card2.recentPrice }}(만원)</b-card-title>
            <!-- <b-card-sub-title class="mb-2">{{ priceDiff2 }}</b-card-sub-title> -->
            <b-card-text class="restxt">
              {{ priceDiff2 }} <br />{{ yearDiff2 }}</b-card-text
            >
          </b-card-body>

          <b-list-group flush>
            <b-list-group-item
              >건축년도: {{ card2.buildYear }}</b-list-group-item
            >
            <b-list-group-item
              >{{ card2.sidoName }} {{ card2.gugunName }} {{ card2.dongName }}
              {{ card2.jibun }}</b-list-group-item
            >
          </b-list-group>

          <b-card-body>
            <b-button variant="outline-danger" @click="deleteZzim(1)"
              >찜 목록에서 삭제</b-button
            >
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { zzimList, delZzim } from "@/api/house";
const memberStore = "memberStore";
export default {
  name: "Mylist",

  data() {
    return {
      card1: [],
      card2: [],
      priceDiff1: "",
      priceDiff2: "",
      yearDiff1: "",
      yearDiff2: "",
      // Note 'isActive' is left out and will not appear in the rendered table
      fields: [
        {
          key: "aptName",
          label: "아파트이름",
          sortable: false,
        },
        {
          key: "buildYear",
          label: "건축년도",
          sortable: true,
        },
        {
          key: "recentPrice",
          label: "가격",
          sortable: true,
          // Variant applies to the whole column, including the header and footer
        },
        {
          key: "dongName",
          label: "법정동",
          sortable: false,
          // Variant applies to the whole column, including the header and footer
        },
      ],
      items: [],
    };
  },

  created() {
    this.load();
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    load() {
      // console.log(this.userInfo.userid);
      zzimList(
        this.userInfo.userid,
        (response) => {
          // console.log(response);
          this.items = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    onRowSelected(items) {
      // console.log("items0: ", items[0], "items1: ", items[1]);
      if (items[0] != null) {
        this.card1 = items[0];
        this.priceDiff1 = "";
        this.priceDiff2 = "";
        this.yearDiff1 = "";
        this.yearDiff2 = "";
      } else {
        this.card1 = [];
      }

      if (items[1] != null) {
        this.card2 = items[1];

        const priceDiff =
          parseInt(items[0].recentPrice.replace(",", "") * 10000) -
          parseInt(items[1].recentPrice.replace(",", "") * 10000);
        const absPrice = Math.abs(priceDiff / 10000)
          .toString()
          .replace(/\B(?=(\d{3})+(?!\d))/g, ",");

        // 백만원 단위
        // const kk = Math.abs(priceDiff / 1000000)
        //   .toString()
        //   .replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        // console.log(kk+"(백만원)");

        if (priceDiff > 0) {
          this.priceDiff1 = "가격: + " + absPrice + "(만원)";
          this.priceDiff2 = "가격: - " + absPrice + "(만원)";
        } else if (priceDiff < 0) {
          this.priceDiff1 = "가격: - " + absPrice + "(만원)";
          this.priceDiff2 = "가격: + " + absPrice + "(만원)";
        } else {
          this.priceDiff1 = "가격이 같습니다.";
          this.priceDiff1 = "가격이 같습니다.";
        }

        const yearDiff = items[0].buildYear - items[1].buildYear;
        // console.log(yearDiff);

        if (yearDiff > 0) {
          this.yearDiff1 = Math.abs(yearDiff) + "년 늦게 지어졌습니다.";
          this.yearDiff2 = Math.abs(yearDiff) + "년 빨리 지어졌습니다.";
        } else if (yearDiff < 0) {
          this.yearDiff1 = Math.abs(yearDiff) + "년 빨리 지어졌습니다.";
          this.yearDiff2 = Math.abs(yearDiff) + "년 늦게 지어졌습니다.";
        } else {
          this.yearDiff1 = "같은 년도에 지어졌습니다.";
          this.yearDiff2 = "같은 년도에 지어졌습니다.";
        }
      } else {
        this.card2 = [];
      }
      if (items[2] != null) {
        alert("이미 카드가 꽉찼습니다.");
        this.$refs.selectableTable.clearSelected();
      }
    },
    deleteZzim(idx) {
      let aptCode;
      if (idx == 0) {
        console.log(this.card1.aptCode);
        aptCode = this.card1.aptCode;
      } else {
        console.log(this.card2.aptCode);
        aptCode = this.card2.aptCode;
      }
      const params = {
        aptCode: aptCode,
      };
      delZzim(params, ({ data }) => {
        console.log(data);
        let msg = "삭제 실패";
        if (data === "success") {
          msg = "찜목록에서 삭제되었습니다.";
          this.load();
        }
        alert(msg);
      });
    },
    clearSelected() {
      this.$refs.selectableTable.clearSelected();
    },
    deleteZzimAll() {
      const params = {
        userId: this.userInfo.userid,
      };
      delZzim(params, ({ data }) => {
        console.log(params);
        let msg = "삭제 실패";
        if (data === "success") {
          msg = "찜목록이 모두 삭제되었습니다.";
          this.load();
        }
        alert(msg);
      });
    },
  },
};
</script>

<style scoped>
.card {
  float: none;
  margin: 0 auto;
}
.btn {
  float: center;
  margin: 0 auto;
}
.restxt {
  color: red;
}
</style>
