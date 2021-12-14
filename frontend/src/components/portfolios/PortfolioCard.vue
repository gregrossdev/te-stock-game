<template>
  <div class="portfolio-card">
    <h3>Portfolio #{{this.portfolio.portfolioId}}</h3>
    <p>Total Value: ${{this.portfolio.portfolioTotalValue}}</p>
  </div>
</template>

<script>
export default {
  name: "portfolio-card",
  props: {
    portfolio: Object
  },
  methods: {},
  created() {}
}
</script>

<style>

</style>