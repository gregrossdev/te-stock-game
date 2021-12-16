<template>
  <main class="main-game">
    <div class="h-wrap-2">
      <h2>Your Portfolio Details</h2>
    </div>
    <portfolio-details/>

    <div class="h-wrap-1">
      <h2>Leaderboard</h2>
    </div>
    <portfolio-list/>

    <div class="h-wrap-1">
      <h2>Buy & Sell Stocks</h2>
    </div>
    <stock-list/>
  </main>
</template>

<script>
import StockList from "@/components/stocks/StockList.vue";
import PortfolioList from "@/components/portfolios/PortfolioList.vue";
import PortfolioDetails from "@/components/portfolios/PortfolioDetails";
import ServiceGames from "@/services/ServiceGames";
import ServicePortfolios from "@/services/ServicePortfolios";
import ServiceTransactions from "@/services/ServiceTransactions";

export default {
  name: "GameId",
  components: {
    StockList,
    PortfolioList,
    PortfolioDetails,
  },
  methods: {
    getActiveGame() {
      ServiceGames
          .getGameByGameId(this.$route.params.gameId)
          .then((response) => {
            this.$store.commit("SET_ACTIVE_GAME", response.data);
          })
          .catch((error) => {
            if (error.response.status === 404) {
              this.$router.push("/"); // TODO: EITHER ADD CUSTOM ERROR MESSAGE ("GAME NOT FOUND") HERE, OR CREATE A CUSTOM 404 PAGE TO REDIRECT TO
            }
          });
    },
    getActivePortfolio() {
      ServicePortfolios
          .getPortfolioByUserIdAndGameId(
              this.$store.state.user.id,
              this.$route.params.gameId
          )
          .then((response) => {
            this.$store.commit("SET_ACTIVE_PORTFOLIO", response.data);
            this.getActivePortfolioStocks();
            this.getActivePortfolioTransactions();
          })
          .catch((error) => {
            if (error.response.status === 404) {
              this.$router.push("/"); // TODO: EITHER ADD CUSTOM ERROR MESSAGE ("GAME NOT FOUND") HERE, OR CREATE A CUSTOM 404 PAGE TO REDIRECT TO
            }
          });
    },
    getGamePortfolios() {
      ServicePortfolios
          .getPortfoliosByGameId()
          .then(response => {
            this.$store.commit("SET_GAME_PORTFOLIOS", response.data);
          });
    },
    getActivePortfolioStocks() {
      ServicePortfolios.getPortfolioStocksByPortfolioId(this.$store.state.activePortfolio.portfolioId).then((response) => {
        this.$store.commit("SET_ACTIVE_PORTFOLIO_STOCKS", response.data);
      });
    },
    getActivePortfolioTransactions() {
      ServiceTransactions.getTransactionsByPortfolioId(this.$store.state.activePortfolio.portfolioId).then((response) => {
        this.$store.commit("SET_TRANSACTIONS", response.data);
      });
    }
  },
  created() {
    this.getActiveGame();
    this.getActivePortfolio();
    this.getGamePortfolios();
    // this.getActivePortfolioStocks();
    // this.getActivePortfolioTransactions();
  },
};
</script>

<style scoped>
h2 {
  padding: 0.75em 0 0.75em;
}
</style>