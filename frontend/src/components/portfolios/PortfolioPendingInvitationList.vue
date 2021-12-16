<template>
  <div class="portfolio-pending-invitation-list">
    <portfolio-pending-card v-for="portfolio in this.$store.state.pendingPortfolios" v-bind:key="portfolio.portfolioId"
                             v-bind:portfolio="portfolio"/>
  </div>
</template>

<script>
import PortfolioPendingCard from './PortfolioPendingCard.vue';

export default {
  name: "pending-invitation-list",
  components: {
    PortfolioPendingCard
  }
}
</script>

<style scoped>

</style>