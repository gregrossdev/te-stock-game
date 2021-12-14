<template>
  <div>
      <portfolio-list />
      <stock-list />
  </div>
</template>

<script>
import StockList from "@/components/stocks/StockList.vue";
import PortfolioList from "@/components/portfolios/PortfolioList.vue";

export default {
    name: "GameId",
    components: { 
    StockList,
    PortfolioList 
 }, 

}
</script>

<style>



</style>