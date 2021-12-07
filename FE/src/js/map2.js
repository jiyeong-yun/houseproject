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
    pos.sidoName + " " + pos.gugunName + " " + pos.dongName + " " + pos.jibun
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
zzimBtn.onclick = function () {
  overlay.setMap(null);
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
