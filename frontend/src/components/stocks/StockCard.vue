<template>
  <div class="stock-card">
    <h5>{{ this.stock.stockSymbol }} | {{ formatPrice(this.stock.sharePrice) }}</h5>
    <p>{{this.stock.stockName}}</p>
    <transaction-new-form v-bind:sharePrice="this.stock.sharePrice" v-bind:stockSymbol="stock.stockSymbol"/>
  </div>
</template>

<script>
import TransactionNewForm from "@/components/transactions/TransactionNewForm";

export default {
  name: "stock-card",
  props: {
    stock: Object
  },
  components: {
    TransactionNewForm
  },
  methods: {
    formatPrice(value) {
      if (typeof value !== "number") {
        return value;
      }
      const formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: 2
      });
      return formatter.format(value);
    }
  },
};
</script>

<style scoped>

p {
  color: white;
  font-size: 1em;
}

.stock-card {
  color: white;
  border: 1px solid var(--clr-grey-70);
  margin-bottom: 1em;
  border-radius: 0.25rem;
  padding: 0.5em;
  width: 10rem;
  display: flex;
  flex-direction: column;
}

</style>