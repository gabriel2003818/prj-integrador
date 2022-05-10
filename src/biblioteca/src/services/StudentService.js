import axios from "axios";

const API_URL = "http://localhost:8080/alunos";

const getAll = (params) => {
  return axios.get("/alunos", { params});
};

const get = id => {
  return axios.get(`alunos/${id}`);
};

const create = data => {
  return axios.post(`alunos/add`, data);
};

const update = (id, data) => {
  return axios.put(`alunos/${id}`, data);
};

const remove = id => {
  return axios.delete(`alunos/${id}`);
};

const findByName = name => {
  return axios.get(`alunos?name=${name}`);
};

export default {
  getAll,
  get,
  create,
  update,
  remove,
  findByName
};