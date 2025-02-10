<template>
  <div class="order-form-container">
    <h2>Создание заказа</h2>
    <form @submit.prevent="createOrder" class="order-form">
      <label for="client">Имя клиента:</label>
      <input type="text" id="client" v-model="order.client" required placeholder="Введите имя клиента" />

      <label for="address">Адрес:</label>
      <input type="text" id="address" v-model="order.address" required placeholder="Введите адрес" />

      <label for="date">Дата:</label>
      <input type="text" id="date" v-model="order.date" required placeholder="Введите дату (строка)" />

      <button type="submit" class="submit-btn">Создать заказ</button>
    </form>

    <transition name="fade">
      <p v-if="successMessage" class="success-message">{{ successMessage }}</p>
    </transition>
    <transition name="fade">
      <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
    </transition>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      order: { client: "", address: "", date: "" },
      successMessage: "",
      errorMessage: "",
    };
  },
  methods: {
    async createOrder() {
      try {
        await axios.post("arioshaman.ru/api/orders", this.order, {
          headers: { "Content-Type": "application/json" },
        });
        this.order = { client: "", address: "", date: "" };
        this.successMessage = "Заказ успешно создан!";
        this.errorMessage = "";
        this.$emit("order-created");
      } catch (error) {
        this.errorMessage = "Ошибка: " + (error.response?.data?.message || error.message);
        this.successMessage = "";
      }
    },
  },
};
</script>

<style scoped>
.order-form-container {
  max-width: 450px;
  margin: 30px auto;
  padding: 20px;
  background: linear-gradient(135deg, #42b983, #2c3e50);
  border-radius: 12px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}

h2 {
  text-align: center;
  color: white;
  font-family: 'Arial', sans-serif;
}

input {
  padding: 12px;
  margin-bottom: 15px;
  border: none;
  border-radius: 6px;
  font-size: 14px;
  color: #333;
  width: 100%;
}

button {
  padding: 12px;
  background: #ffcc00;
  color: #333;
  border: none;
  border-radius: 6px;
  font-size: 16px;
  cursor: pointer;
  transition: all 0.3s;
}

button:hover {
  background: #ff9900;
}

.success-message, .error-message {
  text-align: center;
  margin-top: 15px;
  color: white;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>