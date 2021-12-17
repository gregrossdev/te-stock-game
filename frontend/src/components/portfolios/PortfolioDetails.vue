<template>
  <div class="portfolio-details">
    <div class="h-wrap-1 active">
      <h2>
        Cash Remaining:<span class="money">
          {{ formatPrice(this.$store.state.activePortfolio.portfolioCash) }}
        </span>
      </h2>
      <div class="active-id">
        <div class="wrap-id">
          <h3> User ID  {{ this.$store.state.user.id }}</h3>
        </div>
        <div class="wrap-id">
          <h3>
             Portfolio 
            {{ this.$store.state.activePortfolio.portfolioId }}
          </h3>
        </div>
        <div class="wrap-id">
          <h3>
             Game {{ this.$store.state.activeGame.gameId }}
          </h3>
        </div>
      </div>
    </div>

    <div class="h-wrap-2 portfolio-value">
      <h3>
        Portfolio Value:
        {{ formatPrice(this.$store.state.activePortfolio.portfolioTotalValue) }}
      </h3>
    </div>

    <div v-show="this.$store.state.portfolioStocks.length > 0" class="h-wrap-2 portfolio-investments">
      <h3>Investments</h3>
      <portfolio-stock-list></portfolio-stock-list>
    </div>

    <!--    TODO: Make sure portfolioStocks are displaying correctly, and that they update when stocks update.-->

    <div v-if="this.$store.state.transactions.length > 0" class="h-wrap-2 transaction-history">
      <h3>Transaction History</h3>
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
    TransactionTable,
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
    ServicePortfolios.getPortfolioStocksByPortfolioId(
      this.$store.state.activePortfolio.portfolioId
    ).then((response) => {
      this.$store.state.commit("SET_ACTIVE_PORTFOLIO_STOCKS", response.data);
    });
  },
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

.active {
  padding-top: 0.25em;
}

.active h2 {
  margin-bottom: 0.25em;
  padding: 1em;
  padding-left: 0.5em;
  margin-left: -0.5em;
  margin-right: -0.5em;
  color: var(--clr-pri);
  border-bottom: 1px solid var(--clr-yellow);
  text-shadow: 1px 1px var(--clr-yellow);
  box-shadow: var(--light-shadow);
}

span {
  color: var(--clr-black);
  font-size: 125%;
}

.active-id {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  justify-content: center;
  text-shadow: 1px 1px var(--clr-pri);
  box-shadow: var(--light-shadow);
  margin-left: -0.75em;
  margin-right: -0.75em;
  margin-top: 0;
}

.wrap-id {
  display: flex;
  justify-content: center;
  padding: 1em;
}

.active-id h3 {
  font-size: 1.75rem;
  margin-bottom: 0.25em;
  padding-bottom: 0.5em;
  border-bottom: 1px solid var(--clr-grey-40);
}

.portfolio-value h3 {
  font-size: 2rem;
  padding: 0.5em 0;
  text-shadow: 1px 1px var(--clr-green);
}

.portfolio-investments h3, .transaction-history h3  {
  font-size: 2rem;
  padding: 0.5em 0;
  text-shadow: 1px 1px var(--clr-grey-70);
}


</style>