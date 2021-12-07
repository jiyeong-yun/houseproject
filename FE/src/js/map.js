import { addZzim } from "@/api/house.js";

var content = document.createElement("div");

var title = document.createElement("div");

var aptname = document.createElement("span");

var ulinfo = document.createElement("ul");

var liinfo1 = document.createElement("li");
var addrtitle = document.createElement("span");

var addr = document.createElement("span");

var liinfo2 = document.createElement("li");
var yeartitle = document.createElement("span");

var year = document.createElement("span");

var liinfo3 = document.createElement("li");
var pricetitle = document.createElement("span");

var price = document.createElement("span");

var buttonContainer = document.createElement("div");

var zzimBtn = document.createElement("button");

var zzim = document.createElement("img");

var closeBtn = document.createElement("button");

var ximg = document.createElement("img");

function put(pos, overlay, isMember, memberid) {
  content.className = "overlaybox";

  title.className = "map-popup-title";

  aptname.className = "boxtitle";
  aptname.appendChild(document.createTextNode(pos.aptName));
  title.appendChild(aptname);
  content.appendChild(title);

  addrtitle.className = "title";
  liinfo1.appendChild(addrtitle);

  addr.className = "count";
  addr.appendChild(
    document.createTextNode(
      pos.sidoName + " " + pos.gugunName + " " + pos.dongName + " " + pos.jibun
    )
  );
  liinfo1.appendChild(addr);
  ulinfo.appendChild(liinfo1);

  yeartitle.className = "title";
  yeartitle.appendChild(document.createTextNode("건축년도:"));
  liinfo2.appendChild(yeartitle);

  year.className = "year";
  year.appendChild(document.createTextNode(pos.buildYear + "년"));
  liinfo2.appendChild(year);
  ulinfo.appendChild(liinfo2);

  pricetitle.className = "title";
  pricetitle.appendChild(document.createTextNode("거래금액:"));
  liinfo3.appendChild(pricetitle);

  price.className = "price";
  price.appendChild(document.createTextNode(pos.recentPrice + "(만원)"));
  liinfo3.appendChild(price);
  ulinfo.appendChild(liinfo3);

  content.appendChild(ulinfo);

  buttonContainer.className = "popup-buttons";

  zzimBtn.className = "popup-button";

  zzim.className = "btn";
  zzim.src =
    "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbXsRiB%2FbtrlC59kAOm%2FljKekdNRcsuEKD1mLFIFR1%2Fimg.png";
  zzimBtn.appendChild(zzim);

  zzimBtn.onclick = () => {
    if (!isMember) {
      alert("회원만 사용가능한 기능입니다.");
    } else {
      console.log(memberid.userid, pos.aptCode);
      const params = {
        userId: memberid.userid,
        aptCode: pos.aptCode,
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

  closeBtn.className = "popup-button";

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

  overlay.setContent(content);
}

export { put };
