<template>
  <main class="main-game">
    <div class="h-wrap-2">
      <h2>Your Portfolio Details</h2>
    </div>
    <portfolio-details />

    <div class="h-wrap-1" >
      <h2>Leaderboard</h2>
    </div>
    <portfolio-list />

    <div class="h-wrap-1">
      <h2>Buy & Sell Stocks</h2>
    </div>
    <stock-list />
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
    PortfolioDetails,
  },
};
</script>

<style scoped>
h2 {
  padding: 0.75em 0 0.75em;
}
</style>