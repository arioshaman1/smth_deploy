<script>
import OrderList from './components/OrderList.vue';
import CreateOrder from './components/CreateOrder.vue';

export default {
  components: {
    OrderList,
    CreateOrder,
  },
  methods: {
    fetchOrders() {
      this.$refs.orderList.fetchOrders();
    },
  },
};
</script>

<template>
  <div id="app">
    <h1>Управление заказами</h1>
    <CreateOrder @order-created="fetchOrders" />
    <OrderList ref="orderList" />
  </div>
</template>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  margin-top: 60px;
  background: rgb(63,94,251);
  background: radial-gradient(circle, rgba(63,94,251,1) 47%, rgba(166,128,136,1) 87%);
  min-height: 100vh;
  padding: 20px;
  box-sizing: border-box;
}

h1 {
  color: #333;
  margin-bottom: 20px;
  font-size: 2.5em;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
}

button {
  background-color: #42b983;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 1em;
  cursor: pointer;
  border-radius: 8px;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

button:hover {
  background-color: #369f6e;
  transform: translateY(-2px);
}

.success-message {
  color: #42b983;
  font-weight: bold;
  margin-top: 15px;
  animation: fadeIn 0.5s ease-in-out;
}

.error-message {
  color: #e74c3c;
  font-weight: bold;
  margin-top: 15px;
  animation: fadeIn 0.5s ease-in-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
