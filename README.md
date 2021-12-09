![logo](https://user-images.githubusercontent.com/70522500/145063439-2e0e667f-346d-4afc-97c6-2f6f0b7e1975.png)

# Happy House🏡

아파트 실거래가 정보를 카카오맵 API와 제공하고 비교 분석해주는 사이트

### 목적

서울 아파트의 실거래가 정보를 지도와 함께 알아보고 맘에 드는 매물을 찜해보자!

- 시,군구,동을 선택하거나 아파트 이름을 검색하여 해당하는 아파트 정보를 지도와 함께 제공하기
- 자유게시판에서 자유로운 의견 나누기
- 맘에 드는 매물을 찜하고 비교 분석하기

### 프로젝트 일정

![일정](https://user-images.githubusercontent.com/70522500/145055271-a6bdfd8d-2c3e-4703-a314-e85cbfc72200.jpg)

### 개발 환경

FrontEnd: Vue.js, BootstrapVue

BackEnd: Spring

DB: mySql

### 회원/비회원/관리자

비회원: 홈에서 지도 검색 가능, 상세 보기 가능(찜하기 불가능)

회원: 홈에서 지도 오버레이(상세보기)의 찜 기능 사용 가능, 자유게시판, 찜목록 사용 가능

관리자: 회원 관리(회원 삭제) 가능



### 스크린샷

#### 인트로 화면

- '로그인하기'를 눌러 로그인 창으로 이동
- '비회원으로 시작'을 눌러 홈으로 이동

![intro](https://user-images.githubusercontent.com/70522500/145055187-8e3ab997-83ef-478b-8783-a3ce116ca596.jpg)

#### HOME

- 지도가 보이는 메인화면
- 상단 네비게이션바는 선택하는 메뉴마다 검정색으로 표시

![home](https://user-images.githubusercontent.com/70522500/145055543-0b32dcc7-2d9f-46da-aa6f-3708cffbc842.jpg)

#### 시군구동 검색(셀렉트 박스 선택)

- 서울시, 군/구, 동을 셀렉트 박스로 선택

![시군구검색](https://user-images.githubusercontent.com/70522500/145055671-58c280a2-c803-4c07-8687-50d1aeddcbac.jpg)

#### 아파트 이름 검색 

- 아파트이름 검색 (포함 단어 검색)

![아파트검색](https://user-images.githubusercontent.com/70522500/145055687-a6fbb24e-ff86-4c83-a609-09c6bcbcb3ba.jpg)

#### 지도 오버레이 (아파트 상세정보)

- 시군구동/아파트이름 검색 시 지도에서 해당하는 모든 아파트의 마커가 표시되며 상세 오버레이는 뜨지 않음 (마커를 직접 선택 해야 오버레이가 뜸)
- 지도 좌측 리스트를 누를 경우 모든 마커가 사라지고 해당 아파트 마커와 오버레이가 바로 표시됨

- ✖를 눌러 오버레이 닫기

- ❤를 눌러 찜하기(회원만 가능)

![상세정보](https://user-images.githubusercontent.com/70522500/145055759-bb8241c8-b82b-43d1-b0cd-710b31f9c279.jpg)

#### 하트❤를 눌러 찜

#### 찜목록

- 하트를 눌러 찜한 아파트가 리스트로 뜸
- 가격/건축년도 우측 화살표를 눌러 정렬 가능

![찜목록](https://user-images.githubusercontent.com/70522500/145055738-27a7138a-e77d-4b9e-bd95-d2fc2af7bf38.jpg)

#### 찜목록의 아파트를 선택해 비교 분석

- 찜 리스트에서 아파트를 누르면 하단에 비교 카드가 뜸
- 두개의 아파트를 선택할 경우 카드뷰에 두 아파트를 비교한 내용이 빨간글씨로 생성

![비교분석](https://user-images.githubusercontent.com/70522500/145055730-f73a479b-0e94-4977-b308-52da081526cd.jpg)

#### 게시판

- 비회원은 글 리스트만 볼 수 있음
- 회원은 글 보기, 작성, 수정, 삭제 가능
- 회원은 댓글 작성, 삭제 가능

![자유게시판](https://user-images.githubusercontent.com/70522500/145055709-1068f331-7c4e-49f2-b469-4eeb3d39a218.jpg)

![게시판 내용](https://user-images.githubusercontent.com/70522500/145055717-0e59ffc1-28e2-407a-9cdc-81edf5a49831.jpg)


## Project setup

```
npm install
```

### Compiles and hot-reloads for development

```
npm run serve
```

### Compiles and minifies for production

```
npm run build
```

### Lints and fixes files

```
npm run lint
```

### Customize configuration

See [Configuration Reference](https://cli.vuejs.org/config/).
