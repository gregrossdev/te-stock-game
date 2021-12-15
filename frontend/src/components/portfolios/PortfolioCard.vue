<template>
  <article class="card">
    <h2>Portfolio #{{this.portfolio.portfolioId}}</h2>
    <h3>Total Value: {{formatPrice(this.portfolio.portfolioTotalValue)}}</h3>
    <h3>Available Cash: {{formatPrice(this.portfolio.portfolioCash)}}</h3>
  </article>
</template>

<script>
export default {
  name: "portfolio-card",
  props: {
    portfolio: Object
  },
  methods: {
    formatPrice(value) {
      if (typeof value !== "number") {
        return value;
      }
      const formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: 0
      });
      return formatter.format(value);
    }
  },
  created() {}
}
</script>

<style>

article {
  width: 300px;
  padding: 2em; 
}

.card h2, .card h3 {
  color: var(--clr-grey-90); 
}

.card h2 {
  color: var(--clr-grey-10);
  font-size: 1.4rem; 
}

.card h3 {
  font-size: 1.25rem; 
}

</style>