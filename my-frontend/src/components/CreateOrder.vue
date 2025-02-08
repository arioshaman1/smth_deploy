<template>
  <div class="order-form-container">
    <h2>Создание заказа</h2>
    <form @submit.prevent="createOrder" class="order-form">
      <label for="client">Имя клиента:</label>
      <input type="text" id="client" v-model="order.client" required placeholder="Введите имя клиента" />

      <label for="address">Адрес:</label>
      <input type="text" id="address" v-model="order.address" required placeholder="Введите адрес" />

      <label for="date">Дата:</label>
      <input type="text" id="date" v-model="order.date" required placeholder="Введите дату заказа" />

      <button type="submit" class="submit-btn">Создать заказ</button>
    </form>

    <p v-if="successMessage" class="success-message">{{ successMessage }}</p>
    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      order: {
        client: '',
        address: '',
        date: '',
      },
      successMessage: '',
      errorMessage: '',
    };
  },
  methods: {
    async createOrder() {
      try {
        const response = await axios.post('http://217.196.107.39/api/orders', this.order);

        // Очищаем форму
        this.order = {
          client: '',
          address: '',
          date: '',
        };

        this.successMessage = 'Заказ успешно создан!';
        this.errorMessage = '';

        // Эмитируем событие, чтобы родительский компонент обновил список
        this.$emit('order-created');
      } catch (error) {
        this.errorMessage = 'Ошибка при создании заказа: ' + (error.response?.data || error.message);
        this.successMessage = '';
      }
    },
  },
};
</script>

<style scoped>
.order-form-container {
  max-width: 400px;
  margin: 30px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  color: #333;
  font-family: 'Arial', sans-serif;
}

.order-form {
  display: flex;
  flex-direction: column;
}

label {
  font-size: 14px;
  margin-bottom: 5px;
  color: #555;
  font-family: 'Arial', sans-serif;
}

input {
  padding: 12px;
  margin-bottom: 15px;
  border: 1px solid #ccc;
  border-radius: 6px;
  font-size: 14px;
  color: #333;
  transition: border-color 0.3s;
}

input:focus {
  border-color: #42b983;
  outline: none;
}

button {
  padding: 12px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 6px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #369f6e;
}

.success-message {
  color: #4caf50;
  text-align: center;
  margin-top: 15px;
}

.error-message {
  color: #f44336;
  text-align: center;
  margin-top: 15px;
}
</style>