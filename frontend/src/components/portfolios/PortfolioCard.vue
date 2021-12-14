<template>
  <div class="portfolio-card">
    <h3>Portfolio #{{this.portfolioId}}</h3>
  </div>
</template>

<script>
export default {
  name: "portfolio-card",
  props: {
    portfolioId: Number
  },
  methods: {},
  created() {}
}
</script>

<style>

</style>