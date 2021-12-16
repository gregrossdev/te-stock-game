<template>
  <section>
    <div class="stock-list">
      <portfolio-stock-card
        v-for="portfolioStock in this.$store.state.portfolioStocks"
        v-bind:key="portfolioStock.stockSymbol"
        class="stock card"
        v-bind:portfolioStock="portfolioStock"
      />
    </div>
  </section>
</template>

<script>
// import ServicePortfolios from "@/services/ServicePortfolios";
import PortfolioStockCard from "@/components/portfolio.stocks/PortfolioStockCard";
export default {
  name: "portfolio-stock-list",
  components: {
    PortfolioStockCard,
  },
  created() {

    // TODO: Check why this doesn't seem to always work. Make sure that portfolioStocks are being correctly set in the store.

    // ServicePortfolios.getPortfolioStocksByPortfolioId(this.$store.state.activePortfolio.portfolioId).then((response) => {
    //   this.$store.commit("SET_ACTIVE_PORTFOLIO_STOCKS", response.data);
    // });
  },
};
</script>

<style scoped>

/*TODO improve styling for the PortfolioStockList.*/

.stock-list {
  padding: 1.5rem 1rem; 
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 1rem;
}

</style>