<template>
  <div>
    <portfolio-card v-for="portfolio in this.$store.state.gamePortfolios" v-bind:key="portfolio.portfolioId" v-bind:portfolio="portfolio"></portfolio-card>
  </div>
</template>

<script>

import servicePortfolios from "@/services/ServicePortfolios";
import PortfolioCard from "@/components/portfolios/PortfolioCard";

export default {
  name: "portfolio-list",
  components: {PortfolioCard},
  created() {
    servicePortfolios
      .getPortfoliosByGameId(this.$route.params.gameId)
      .then(response => {
        this.$store.commit("SET_GAME_PORTFOLIOS", response.data);
      });
  }
}
</script>

<style>

</style>