<template>
  <div class="portfolio-details">

    <p style="color: red">Active User is: {{this.$store.state.user.id}}.
      Active Game is: {{this.$store.state.activeGame.gameId}}.
      Active Portfolio is: {{this.$store.state.activePortfolio.portfolioId}}.</p>

    <h3>Current Portfolio Value: {{formatPrice(this.$store.state.activePortfolio.portfolioTotalValue)}}</h3>


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
import serviceGames from "@/services/ServiceGames";
import servicePortfolios from "@/services/ServicePortfolios";

export default {
  name: "PortfolioDetails",
  methods: {
    formatPrice(value) {
      if (typeof value !== "number") {
        return value;
      }
      const formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: 0
      });
      return formatter.format(value);
    }
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
        .getPortfolioByUserIdAndGameId(this.$store.state.user.id, this.$route.params.gameId)
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
}
</script>

<style scoped>

.portfolio-details {
  background-color: var(--clr-pri); 
}

.portfolio-details h3 {
  margin-bottom: 0;
  padding: 1em 0;
}

</style>