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
      orders: [], // Список заказов
      error: null, // Сообщение об ошибке
    };
  },
  created() {
    this.fetchOrders(); // Загружаем заказы при создании компонента
  },
  methods: {
    // Метод для загрузки заказов
    async fetchOrders() {
      try {
        const response = await axios.get('http://217.196.107.39/api/orders');
        this.orders = response.data; // Обновляем список заказов
        this.error = null; // Сбрасываем ошибку
      } catch (error) {
        console.error('Ошибка при загрузке заказов:', error);
        this.error = 'Не удалось загрузить заказы. Попробуйте снова.';
      }
    },
    // Метод для удаления заказа
    async deleteOrder(id) {
      try {
        await axios.delete(`http://217.196.107.39/api/orders/${id}`);
        this.fetchOrders(); // Обновляем список после удаления
        this.error = null; // Сбрасываем ошибку
      } catch (error) {
        console.error('Ошибка при удалении заказа:', error);
        this.error = 'Не удалось удалить заказ. Попробуйте снова.';
      }
    },
  },
};
</script>