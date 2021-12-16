<template>
  <div class="portfolio-details">
    <div class="h-wrap-1 active">
      <h2>Cash Remaining: {{ formatPrice(this.$store.state.activePortfolio.portfolioCash) }}</h2>
      <ul>
        <li>Your user ID is: {{ this.$store.state.user.id }}.</li>
        <li>You're playing Game #: {{ this.$store.state.activeGame.gameId }}.</li>
        <li>Your portfolio # is: {{ this.$store.state.activePortfolio.portfolioId }}.</li>
      </ul>

    </div>

    <div class="h-wrap-2">
      <h3>
        Current Portfolio Value:
        {{ formatPrice(this.$store.state.activePortfolio.portfolioTotalValue) }}
      </h3>
    </div>

    <div v-show="this.$store.state.portfolioStocks.length > 0" class="h-wrap-2">
      <h3>Your Investments</h3>
      <portfolio-stock-list></portfolio-stock-list>
    </div>

    <!--    TODO: Make sure portfolioStocks are displaying correctly, and that they update when stocks update.-->


    <div v-if="this.$store.state.transactions.length > 0" class="h-wrap-2">
      <h3>Your Transaction History</h3>
      <transaction-table></transaction-table>
      <!--    TODO: Make sure transaction history displays correctly. Perhaps make it a dropdown show/hide section?-->
    </div>


  </div>
</template>

<script>
import TransactionTable from "@/components/transactions/TransactionTable";
import PortfolioStockList from "@/components/portfolio.stocks/PortfolioStockList";
import ServicePortfolios from "@/services/ServicePortfolios";

export default {
  name: "PortfolioDetails",
  components: {
    PortfolioStockList,
    TransactionTable
  },
  methods: {
    formatPrice(value) {
      if (typeof value !== "number") {
        return value;
      }
      const formatter = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
        minimumFractionDigits: 0,
      });
      return formatter.format(value);
    },
  },
  created() {
    ServicePortfolios.getPortfolioStocksByPortfolioId(this.$store.state.activePortfolio.portfolioId).then((response) => {
      this.$store.state.commit("SET_ACTIVE_PORTFOLIO_STOCKS", response.data);
    })
  }
};
</script>

<style scoped>
.portfolio-details {
  background-color: var(--clr-pri);
}

.portfolio-details h3 {
  margin-bottom: 0;
  padding: 1em 0;
  font-weight: 800;
  font-size: 1.25rem;
  color: var(--clr-yellow);
}

.active h2 {
  margin-bottom: 0.75em;
  color: var(--clr-black);
}


</style>