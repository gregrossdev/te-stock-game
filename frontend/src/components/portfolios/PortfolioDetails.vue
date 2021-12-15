<template>
  <div class="portfolio-details">
    <div class="h-wrap-1 active">
      <h2>Your user ID is: {{ this.$store.state.user.id }}.</h2>
      <h2>You're playing Game #: {{ this.$store.state.activeGame.gameId }}.</h2>
      <h2>Your portfolio # is: {{ this.$store.state.activePortfolio.portfolioId }}.</h2>
    </div>

    <div class="h-wrap-2">
      <h3>
        Current Portfolio Value:
        {{ formatPrice(this.$store.state.activePortfolio.portfolioTotalValue) }}
      </h3>
    </div>

    <div class="h-wrap-2">
      <h3>Your Investments</h3>
    </div>

    <!--    TODO: Make sure portfolioStocks are displaying correctly, and that they update when stocks update.-->

    <portfolio-stock-list></portfolio-stock-list>

    <div class="h-wrap-2">
      <h3>Your Transaction History</h3>
    </div>

<!--    TODO: Make sure transaction history displays correctly. Perhaps make it a dropdown show/hide section?-->

    <transaction-table></transaction-table>

  </div>
</template>

<script>
import TransactionTable from "@/components/transactions/TransactionTable";
// import TransactionList from "@/components/transactions/TransactionList";
// import StockList from "@/components/stocks/StockList";
import serviceGames from "@/services/ServiceGames";
import servicePortfolios from "@/services/ServicePortfolios";
import PortfolioStockList from "@/components/portfolio.stocks/PortfolioStockList";

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
    serviceGames
      .getGameByGameId(this.$route.params.gameId)
      .then((response) => {
        this.$store.commit("SET_ACTIVE_GAME", response.data);
      })
      .catch((error) => {
        if (error.response.status === 404) {
          this.$router.push("/"); // TODO: EITHER ADD CUSTOM ERROR MESSAGE ("GAME NOT FOUND") HERE, OR CREATE A CUSTOM 404 PAGE TO REDIRECT TO
        }
      });
    servicePortfolios
      .getPortfolioByUserIdAndGameId(
        this.$store.state.user.id,
        this.$route.params.gameId
      )
      .then((response) => {
        this.$store.commit("SET_ACTIVE_PORTFOLIO", response.data);
      })
      .catch((error) => {
        if (error.response.status === 404) {
          this.$router.push("/"); // TODO: EITHER ADD CUSTOM ERROR MESSAGE ("GAME NOT FOUND") HERE, OR CREATE A CUSTOM 404 PAGE TO REDIRECT TO
        }
      });
  },
  // components: {StockList, TransactionList}
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