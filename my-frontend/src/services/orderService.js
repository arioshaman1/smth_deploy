import axios from 'axios';

const API_URL = 'http://localhost:8080/orders';

export default {
  getAllOrders() {
    return axios.get(API_URL);
  },
  getOrderById(id) {
    return axios.get(`${API_URL}/${id}`);
  },
  createOrder(order) {
    return axios.post(API_URL, order);
  },
  deleteOrder(id) {
    return axios.delete(`${API_URL}/${id}`);
  },
};