<template>
  <div class="portfolio-details">

    <h3>Current Portfolio Value: {{this.$store.state.activePortfolio.portfolioTotalValue}}</h3>


    <h3>Your Investments</h3>
<!--    TODO: DISPLAY STOCK LIST OF STOCK CARDS HERE WITH THE ACTIVE PORTFOLIOS STOCK INVESTMENTS, DRAWN FROM PORTFOLIOS_STOCKS TABLE.-->
<!--    <stock-list></stock-list>-->

    <h3>Your Transaction History</h3>
<!--    TODO: DISPLAY TRANSACTION LIST OF TRANSACTION CARDS, DRAWN FROM TRANSACTIONS TABLE USING THE ACTIVE PORTFOLIO ID.-->
<!--    <transaction-list></transaction-list>-->
  </div>
</template>

<script>
// import TransactionList from "@/components/transactions/TransactionList";
// import StockList from "@/components/stocks/StockList";
export default {
  name: "PortfolioDetails",
  data() {
    return
  }
  // components: {StockList, TransactionList}
}
</script>

<style scoped>

</style>