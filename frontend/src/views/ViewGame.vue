<template>
  <main class="main-game">
    <h2 class="l-align">Your Portfolio Details</h2>
    <portfolio-details/>

    <h2 class="l-align">Leaderboard</h2>
    <portfolio-list/>

    <h2 class="l-align">Buy & Sell Stocks</h2>
    <stock-list/>
  </main>
</template>

<script>
import StockList from "@/components/stocks/StockList.vue";
import PortfolioList from "@/components/portfolios/PortfolioList.vue";
import PortfolioDetails from "@/components/portfolios/PortfolioDetails";

export default {
  name: "GameId",
  components: {
    StockList,
    PortfolioList,
    PortfolioDetails
  },

}
</script>

<style scoped>

h2 {
  padding: 0.75em 0 0.75em; 
}

</style>