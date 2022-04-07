import axios from "axios";

const getAll = (params) => {
  return axios.get("/livros", { params });
};

const get = id => {
  return axios.get(`/livros/${id}`);
};

const create = data => {
  return axios.post("/livros", data);
};

const update = (id, data) => {
  return axios.put(`/livros/${id}`, data);
};

const remove = id => {
  return axios.delete(`/livros/${id}`);
};

const removeAll = () => {
  return axios.delete(`/livros`);
};

const findByTitle = title => {
  return axios.get(`/livros?title=${title}`);
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