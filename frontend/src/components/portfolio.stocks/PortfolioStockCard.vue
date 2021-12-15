<template>
  <div class="stock-card">
    <h5>Stock: {{ this.portfolioStock.stockSymbol }} | Shares: {{ this.portfolioStock.totalShares }}</h5>
  </div>
</template>

<script>

export default {
  name: "stock-card",
  props: {
    portfolioStock: Object
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