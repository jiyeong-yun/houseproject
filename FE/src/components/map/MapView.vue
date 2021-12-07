<template>
  <div id="map" class="map"></div>
</template>

<script>
import { mapState } from "vuex";
import { addZzim } from "@/api/house.js";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "Map",

  data() {
    return {
      marker: "",
      markers: [],
    };
  },

  computed: {
    // houses는 전체 리스트, house는 리스트에서 아파트 한개 눌렀을 때
    ...mapState(houseStore, ["houses", "house"]),
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  // TODO: houses값이 바뀌면 watch가 보고있다가 value로 바뀐 값을 보내줌 => 지도 변환
  watch: {
    // 리스트에서 한 개의 아파트 눌렀을 때
    house(value) {
      console.log("watch detail: ", value);

      const bounds = new kakao.maps.LatLngBounds();
      const container = document.getElementById("map"); // 지도를 표시할 DOM 레퍼런스
      const options = {
        // 지도 생성 기본 옵션
        center: new kakao.maps.LatLng(37.50137163642524, 127.03965920154116), // 지도 중심좌표
        level: 3, // 지도 레벨(확대, 축소 정도)
      };
      this.removeMarker();
      const map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

      // 내 이미지로 마커 생성
      // const imageSrc =
      //     "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F6LJ5x%2FbtrlXCkJqfV%2FJiH98f2maDJVKBkKlmKaK1%2Fimg.png", // 마커이미지의 주소입니다
      //   imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
      //   imageOption = { offset: new kakao.maps.Point(25, 80) };

      // const markerImage = new kakao.maps.MarkerImage(
      //     imageSrc,
      //     imageSize,
      //     imageOption
      //   ),
      const markerPosition = new kakao.maps.LatLng(value.lat, value.lng);
      const marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: markerPosition, // 마커의 위치
        // image: markerImage,
      });

      // marker.setMap(map); // 마커 찍기
      bounds.extend(markerPosition); // LatLngBounds 객체에 좌표 추가
      map.setBounds(bounds); // 지도 재설정

      const overlay = new kakao.maps.CustomOverlay({
        // content: content, // 밑에서 setContent
        map: map,
        position: marker.getPosition(),
        // 오버레이 위치 설정
        xAnchor: 0.34,
        yAnchor: 0.7,
      });

      /* FIXME: 여기부터 DOM Manipulation*/
      var content = document.createElement("div");
      content.className = "overlaybox";

      var title = document.createElement("div");
      title.className = "map-popup-title";

      var aptname = document.createElement("span");
      aptname.className = "boxtitle";
      aptname.appendChild(document.createTextNode(value.aptName));
      title.appendChild(aptname);
      content.appendChild(title);

      var ulinfo = document.createElement("ul");

      var liinfo1 = document.createElement("li");
      var addrtitle = document.createElement("span");
      addrtitle.className = "title";
      // addrtitle.appendChild(document.createTextNode("주소"));
      liinfo1.appendChild(addrtitle);
      var addr = document.createElement("span");
      addr.className = "count";
      addr.appendChild(
        document.createTextNode(
          value.sidoName +
            " " +
            value.gugunName +
            " " +
            value.dongName +
            " " +
            value.jibun
        )
      );
      liinfo1.appendChild(addr);
      ulinfo.appendChild(liinfo1);

      var liinfo2 = document.createElement("li");
      var yeartitle = document.createElement("span");
      yeartitle.className = "title";
      yeartitle.appendChild(document.createTextNode("건축년도:"));
      liinfo2.appendChild(yeartitle);
      var year = document.createElement("span");
      year.className = "year";
      year.appendChild(document.createTextNode(value.buildYear + "년"));
      liinfo2.appendChild(year);
      ulinfo.appendChild(liinfo2);

      var liinfo3 = document.createElement("li");
      var pricetitle = document.createElement("span");
      pricetitle.className = "title";
      pricetitle.appendChild(document.createTextNode("거래금액:"));
      liinfo3.appendChild(pricetitle);
      var price = document.createElement("span");
      price.className = "price";
      price.appendChild(document.createTextNode(value.recentPrice + "(만원)"));
      liinfo3.appendChild(price);
      ulinfo.appendChild(liinfo3);

      content.appendChild(ulinfo);

      var buttonContainer = document.createElement("div");
      buttonContainer.className = "popup-buttons";

      var zzimBtn = document.createElement("button");
      zzimBtn.className = "popup-button";
      // zzimBtn.appendChild(document.createTextNode("찜"));
      var zzim = document.createElement("img");
      zzim.className = "btn";
      zzim.src =
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbXsRiB%2FbtrlC59kAOm%2FljKekdNRcsuEKD1mLFIFR1%2Fimg.png";
      zzimBtn.appendChild(zzim);
      console.log(this.isLogin);
      // that 용법
      // const that = this;
      // zzimBtn.onclick = function () {
      //   console.log(that.userInfo.userid);
      //   // if (this.userInfo.userid != null) {
      //   //   console.log("로그인");
      //   // } else {
      //   //   console.log("로그인 안함");
      //   // }
      //   overlay.setMap(null);
      // };

      // arrow function 용법
      zzimBtn.onclick = () => {
        if (!this.isLogin) {
          alert("회원만 사용가능한 기능입니다.");
        } else {
          console.log(this.userInfo.userid, value.aptCode);
          const params = {
            userId: this.userInfo.userid,
            aptCode: value.aptCode,
          };
          addZzim(
            params,
            ({ data }) => {
              let msg = "찜하기 실패! 이미 찜한 아파트입니다.";
              if (data === "success") {
                msg = "이 아파트를 찜 하였습니다.";
              }
              alert(msg);
            },
            (error) => {
              console.log(error);
            }
          );
        }
      };

      var closeBtn = document.createElement("button");
      closeBtn.className = "popup-button";
      // closeBtn.appendChild(document.createTextNode("취소"));
      var ximg = document.createElement("img");
      ximg.className = "btn";
      ximg.src =
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmFMCP%2FbtrlIcetO9V%2FjEiq3aoyM3SS53KGZIZdiK%2Fimg.png";
      closeBtn.appendChild(ximg);
      closeBtn.onclick = function () {
        overlay.setMap(null);
      };

      buttonContainer.appendChild(closeBtn);
      buttonContainer.appendChild(zzimBtn);

      content.appendChild(buttonContainer);

      kakao.maps.event.addListener(marker, "click", function () {
        overlay.setMap(map);
      });

      overlay.setContent(content);
      /** 여기까지 */

      //마커 안눌러도 상세정보 바로 뜨도록 설정
      // overlay.setMap(null);

      // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
      kakao.maps.event.addListener(marker, "click", function () {
        overlay.setMap(map);
      });

      overlay.setContent(content);
    },
    // 셀렉트 박스로 동까지 선택했을 때 모든 아파트 리스트
    houses(value) {
      console.log("watch: ", value);

      // forEach문을 돌게 되면 isLogin을 못받아오므로 임의로 설정
      let isMember = false;
      let memberid = "";
      if (this.isLogin) {
        isMember = true;
        memberid = this.userInfo.userid;
      }
      // console.log(isMember, memberid);

      const bounds = new kakao.maps.LatLngBounds();
      const container = document.getElementById("map"); // 지도를 표시할 DOM 레퍼런스
      const options = {
        // 지도 생성 기본 옵션
        center: new kakao.maps.LatLng(37.50137163642524, 127.03965920154116), // 지도 중심좌표
        level: 3, // 지도 레벨(확대, 축소 정도)
      };
      this.removeMarker();
      const map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

      // houses에 있는 정보를 이용해 지도 재설정
      this.houses.forEach(function (pos) {
        const markerPosition = new kakao.maps.LatLng(pos.lat, pos.lng);
        const marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: markerPosition, // 마커의 위치
        });

        // marker.setMap(map); // 마커 찍기
        bounds.extend(markerPosition); // LatLngBounds 객체에 좌표 추가
        map.setBounds(bounds); // 지도 재설정

        const overlay = new kakao.maps.CustomOverlay({
          // content: content, // 밑에서 setContent
          map: map,
          position: marker.getPosition(),
          // 오버레이 위치 설정
          xAnchor: 0.34,
          yAnchor: 0.7,
        });

        /* FIXME: 여기부터 DOM Manipulation*/
        var content = document.createElement("div");
        content.className = "overlaybox";

        var title = document.createElement("div");
        title.className = "map-popup-title";

        var aptname = document.createElement("span");
        aptname.className = "boxtitle";
        aptname.appendChild(document.createTextNode(pos.aptName));
        title.appendChild(aptname);
        content.appendChild(title);

        var ulinfo = document.createElement("ul");

        var liinfo1 = document.createElement("li");
        var addrtitle = document.createElement("span");
        addrtitle.className = "title";
        // addrtitle.appendChild(document.createTextNode("주소"));
        liinfo1.appendChild(addrtitle);
        var addr = document.createElement("span");
        addr.className = "count";
        addr.appendChild(
          document.createTextNode(
            pos.sidoName +
              " " +
              pos.gugunName +
              " " +
              pos.dongName +
              " " +
              pos.jibun
          )
        );
        liinfo1.appendChild(addr);
        ulinfo.appendChild(liinfo1);

        var liinfo2 = document.createElement("li");
        var yeartitle = document.createElement("span");
        yeartitle.className = "title";
        yeartitle.appendChild(document.createTextNode("건축년도:"));
        liinfo2.appendChild(yeartitle);
        var year = document.createElement("span");
        year.className = "year";
        year.appendChild(document.createTextNode(pos.buildYear + "년"));
        liinfo2.appendChild(year);
        ulinfo.appendChild(liinfo2);

        var liinfo3 = document.createElement("li");
        var pricetitle = document.createElement("span");
        pricetitle.className = "title";
        pricetitle.appendChild(document.createTextNode("거래금액:"));
        liinfo3.appendChild(pricetitle);
        var price = document.createElement("span");
        price.className = "price";
        price.appendChild(document.createTextNode(pos.recentPrice + "(만원)"));
        liinfo3.appendChild(price);
        ulinfo.appendChild(liinfo3);

        content.appendChild(ulinfo);

        var buttonContainer = document.createElement("div");
        buttonContainer.className = "popup-buttons";

        var zzimBtn = document.createElement("button");
        zzimBtn.className = "popup-button";
        // zzimBtn.appendChild(document.createTextNode("찜"));
        var zzim = document.createElement("img");
        zzim.className = "btn";
        zzim.src =
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbXsRiB%2FbtrlC59kAOm%2FljKekdNRcsuEKD1mLFIFR1%2Fimg.png";
        zzimBtn.appendChild(zzim);
        zzimBtn.onclick = () => {
          if (!isMember) {
            alert("회원만 사용가능한 기능입니다.");
          } else {
            console.log(memberid, pos.aptCode);
            const params = {
              userId: memberid,
              aptCode: pos.aptCode,
            };
            addZzim(
              params,
              ({ data }) => {
                let msg = "찜하기 실패!";
                if (data === "success") {
                  msg = "이 아파트를 찜 하였습니다.";
                }
                alert(msg);
              },
              (error) => {
                console.log(error);
              }
            );
          }
        };

        var closeBtn = document.createElement("button");
        closeBtn.className = "popup-button";
        // closeBtn.appendChild(document.createTextNode("취소"));
        var ximg = document.createElement("img");
        ximg.className = "btn";
        ximg.src =
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmFMCP%2FbtrlIcetO9V%2FjEiq3aoyM3SS53KGZIZdiK%2Fimg.png";
        closeBtn.appendChild(ximg);
        closeBtn.onclick = function () {
          overlay.setMap(null);
        };

        buttonContainer.appendChild(closeBtn);
        buttonContainer.appendChild(zzimBtn);

        content.appendChild(buttonContainer);

        kakao.maps.event.addListener(marker, "click", function () {
          overlay.setMap(map);
        });

        overlay.setContent(content);
        /** 여기까지 */

        // 마커 누르기 전에는 안보이게
        overlay.setMap(null);

        // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
        kakao.maps.event.addListener(marker, "click", function () {
          overlay.setMap(map);
        });

        overlay.setContent(content);
      });
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        // "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=667505ee63aa8e846b6e36b1b35cb48c"; // 무조건 됨
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=667505ee63aa8e846b6e36b1b35cb48c&libraries=services,clusterer,drawing"; // 일단 됨
      // "http://dapi.kakao.com/v2/maps/sdk.js?appkey=667505ee63aa8e846b6e36b1b35cb48c&libraries=services"; // 안될수도
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      // const container = document.querySelector("#map"); // 지도를 표시할 DOM 레퍼런스
      const container = document.getElementById("map"); // 지도를 표시할 DOM 레퍼런스
      const options = {
        // 지도 생성 기본 옵션
        center: new kakao.maps.LatLng(37.50137163642524, 127.03965920154116), // 지도 중심좌표
        level: 3, // 지도 레벨(확대, 축소 정도)
      };
      const map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

      // TODO: 첫 위치를 내 위치로 표시
      // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function (position) {
          var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도

          var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            message = '<div style="padding:5px;">현재위치</div>'; // 인포윈도우에 표시될 내용입니다

          // 마커와 인포윈도우를 표시합니다
          displayMarker(locPosition, message);
        });
      } else {
        // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

        var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
          message = "geolocation을 사용할수 없어요..";

        displayMarker(locPosition, message);
      }

      // 지도에 마커와 인포윈도우를 표시하는 함수입니다
      function displayMarker(locPosition, message) {
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map,
          position: locPosition,
        });

        var iwContent = message, // 인포윈도우에 표시할 내용
          iwRemoveable = true;

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
          removable: iwRemoveable,
        });

        // 인포윈도우를 마커위에 표시합니다
        infowindow.open(map, marker);

        // 지도 중심좌표를 접속위치로 변경합니다
        map.setCenter(locPosition);
      }
      // END
    },
    // 지도에 있는 마커 모두 지우기
    removeMarker() {
      this.markers.forEach(function (pos) {
        pos.setMap(null);
      });
      this.markers = [];
    },
  },
};
</script>

<style>
.map {
  width: 100%;
  height: 600px;
}
.overlaybox {
  position: relative;
  width: 360px;
  height: 350px;
  background: url("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbGup51%2FbtrlEUlfrEl%2FtYTfhl9oOK85mIJ7tJNi6k%2Fimg.png")
    no-repeat;
  padding: 15px 10px;
}
.overlaybox div,
ul {
  overflow: hidden;
  margin: 0;
  padding: 0;
}
.overlaybox li {
  list-style: none;
}
.overlaybox .map-popup-title {
  color: black;
  font-size: 15px;
  text-align: left;
  font-weight: bold;
  margin-bottom: 7px;
}
.overlaybox .boxtitle {
  margin-left: 3px;
}

/*버튼 그룹 */
.overlaybox .popup-buttons {
  margin-top: -20px;
  margin-left: -90px;
}
.overlaybox .popup-button {
  border: none;
  background: none;
}

/* 진노랑 박스*/
.overlaybox ul {
  width: 243px;
}
.overlaybox li {
  position: relative;
  margin-bottom: 2px;
  background: rgb(255, 192, 0);
  padding: 5px 10px;
  color: black;
}
.overlaybox li span {
  display: inline-block;
}

.overlaybox li .title {
  font-size: 15px;
}

/* .overlaybox li .up {
    background-position: 0 -40px;
  }
  .overlaybox li .down {
    background-position: 0 -60px;
  } */
.overlaybox li .count {
  /* position: absolute; */
  margin-top: 5px;
  right: 15px;
  font-size: 14px;
}
.overlaybox .popup-button:hover {
  cursor: pointer;
}
/* .overlaybox li:hover .up {
    background-position: 0 0px;
  }
  .overlaybox li:hover .down {
    background-position: 0 -20px;
  } */
</style>
