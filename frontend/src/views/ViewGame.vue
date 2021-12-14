<template>
  <div>
    <h2>Your Portfolio Details</h2>
    <portfolio-details/>

    <h2>Leaderboard</h2>
    <portfolio-list/>

    <h2>Buy & Sell Stocks</h2>
    <stock-list/>
  </div>
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

<style>


</style>