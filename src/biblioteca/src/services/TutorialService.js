import axios from "axios";

const getAll = (params) => {
  return axios.get("/tutorials", { params });
};

const get = id => {
  return axios.get(`/tutorials/${id}`);
};

const create = data => {
  return axios.post("/tutorials", data);
};

const update = (id, data) => {
  return axios.put(`/tutorials/${id}`, data);
};

const remove = id => {
  return axios.delete(`/tutorials/${id}`);
};

const removeAll = () => {
  return axios.delete(`/tutorials`);
};

const findByTitle = title => {
  return axios.get(`/tutorials?title=${title}`);
};

export default {
  getAll,
  get,
  create,
  update,
  remove,
  removeAll,
  findByTitle
};