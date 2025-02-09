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

    <p v-if="successMessage" class="success-message">{{ successMessage }}</p>
    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      order: {
        client: "",
        address: "",
        date: "",
      },
      successMessage: "",
      errorMessage: "",
    };
  },
  methods: {
    async createOrder() {
      try {
        const response = await axios.post("http://217.196.107.39/api/orders", this.order, {
          headers: { "Content-Type": "application/json" },
        });

        // Очищаем форму
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