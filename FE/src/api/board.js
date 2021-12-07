import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

function listCount(param, success, fail) {
  api.get(`/board/allcnt`, { params: param }).then(success).catch(fail);
}

function listMemo(articleno, success, fail) {
  api.get(`/board/memo/${articleno}`).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function writeMemo(memo, success, fail) {
  api.post(`/board/memo`, JSON.stringify(memo)).then(success).catch(fail);
}

function deleteMemo(memono, success, fail) {
  api.delete(`/board/memo/${memono}`).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/board/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/board/${articleno}`).then(success).catch(fail);
}

export {
  listArticle,
  listCount,
  writeArticle,
  getArticle,
  modifyArticle,
  deleteArticle,
  listMemo,
  writeMemo,
  deleteMemo,
};
