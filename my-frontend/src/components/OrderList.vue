<template>
  <div class="orders-container">
    <h2>Список заказов</h2>
    <ul>
      <li v-for="order in orders" :key="order.id" class="order-item">
        <span>{{ order.address }} - {{ order.client }} - {{ order.date }}</span>
        <button @click="deleteOrder(order.id)">Удалить</button>
      </li>
    </ul>
    <CreateOrder @order-created="fetchOrders" />
  </div>
</template>

<script>
import axios from 'axios';
import CreateOrder from './CreateOrder.vue';
export default {
  components: { CreateOrder },
  data() {
    return { orders: [], error: null };
  },
  created() {
    this.fetchOrders();
  },
  methods: {
    async fetchOrders() {
      try {
        const response = await axios.get('/api/orders');
        this.orders = response.data;
      } catch (error) {
        this.error = 'Ошибка загрузки';
      }
    },
    async deleteOrder(id) {
      try {
        await axios.delete(`/api/orders/${id}`);
        this.fetchOrders();
      } catch (error) {
        this.error = 'Ошибка удаления';
      }
    },
  },
};
</script>

<style scoped>
.orders-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background: white;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.order-item {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  margin: 5px 0;
  background: #f4f4f4;
  border-radius: 6px;
}

button {
  background: #e74c3c;
  color: white;
  padding: 8px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background: #c0392b;
}
</style>
