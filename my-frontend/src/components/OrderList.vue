<template>
  <div>
    <h2>Список заказов</h2>
    <ul>
      <li v-for="order in orders" :key="order.id">
         {{ order.address }} - {{ order.client }} - {{ order.date }}
        <button @click="deleteOrder(order.id)">Удалить</button>
      </li>
    </ul>

    <!-- Вставляем дочерний компонент для создания заказа -->
    <CreateOrder @order-created="fetchOrders" />
  </div>
</template>

<script>
import axios from 'axios';
import CreateOrder from './CreateOrder.vue';

export default {
  components: {
    CreateOrder,
  },
  data() {
    return {
      orders: [],
    };
  },
  created() {
    this.fetchOrders();
  },
  methods: {
    async fetchOrders() {
      const response = await axios.get('http://217.196.107.39/api/orders');
      this.orders = response.data;
    },
    async deleteOrder(id) {
      await axios.delete(`http://217.196.107.39/api/orders/${id}`);
      this.fetchOrders();
    },
  },
};
</script>