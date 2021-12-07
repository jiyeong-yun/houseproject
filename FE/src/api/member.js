import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function join(user, success, fail) {
  await api.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail);
}

async function modify(user, success, fail) {
  await api.put(`/user/modify`, JSON.stringify(user)).then(success).catch(fail);
}

async function cancel(userid, success, fail) {
  await api.delete(`/user/cancel/${userid}`).then(success).catch(fail);
}

async function admin(success, fail) {
  await api.get(`/user/admin`).then(success).catch(fail);
}

// function logout(success, fail)

export { login, findById, join, modify, cancel, admin };
