import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/map/apt`, { params: params }).then(success).catch(fail);
}

function aptNameList(params, success, fail) {
  api.get(`/map/aptNamelist`, { params: params }).then(success).catch(fail);
}

function zzimList(userid, success, fail) {
  api.get(`/map/zzim/${userid}`).then(success).catch(fail);
}

function addZzim(params, success, fail) {
  api.post(`/map/zzim`, JSON.stringify(params)).then(success).catch(fail);
}

async function delZzim(params, success, fail) {
  await api.delete(`/map/zzim`, { params: params }).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  aptNameList,
  zzimList,
  addZzim,
  delZzim,
};
