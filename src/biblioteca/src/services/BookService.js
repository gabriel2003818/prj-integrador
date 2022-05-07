import axios from "axios";

const getAll = (params) => {
  return axios.get("/livros", { params});
};

const get = id => {
  return axios.get(id);
};

const create = data => {
  return axios.post(`livros/add`, data);
};

const update = (id, data) => {
  return axios.put(id, data);
};

const remove = id => {
  return axios.delete(`livros/${id}`);
};

const findByTitle = title => {
  return axios.get(`livros?title=${title}`);
};

export default {
  getAll,
  get,
  create,
  update,
  remove,
  findByTitle
};