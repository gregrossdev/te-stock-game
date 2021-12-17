<template>
  <article class="stock-card">
    <h4>{{ this.stock.stockSymbol }} | {{ formatPrice(this.stock.sharePrice) }}</h4>
    <p>{{this.stock.stockName}}</p>
    <router-link v-bind:to="{ name: 'ViewNewTransaction', params: { stock: this.stock } }" tag="button">Buy/Sell</router-link>
  </article>
</template>

<script>
// import TransactionNewForm from "@/components/transactions/TransactionNewForm";
export default {
  name: "stock-card",
  props: {
    stock: Object
  },
  components: {
    // TransactionNewForm
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
  align-self: stretch;
}

.stock-card p {
  font-weight: 600;
  font-size: 1rem;
}

.stock-card button {
  display: block;
  padding: 0.75em;
  font-weight: 900;
  font-size: 1rem;
  margin-top: auto;
}

</style>