<template>
  <div class="stock-card">
    <h3><span class="stock-symbol">Stock:</span> {{ stock.stockSymbol }}</h3>
    <p class="stock-symbol">Name: </p>
    <h4>{{ stock.stockName }}</h4>
    <p class="stock-symbol">Shares:</p>
    <h4>{{ portfolioStock.totalShares }}</h4>
    <p class="stock-symbol">Share Price:</p>
    <h4>{{ formatPrice(sharePrice) }}</h4>
    <p class="stock-symbol">Investment Value:</p>
    <h4>{{ formatPrice(investmentValue) }}</h4>
  </div>
</template>

<script>
import ServiceStocks from "@/services/ServiceStocks";

export default {
  name: "stock-card",
  props: {
    portfolioStock: Object,
  },
  computed: {
    stock() {
      return this.$store.state.stocks.find(
        (item) => item.stockSymbol === this.portfolioStock.stockSymbol
      );
    },
    sharePrice() {
      return this.stock.sharePrice;
    },
    investmentValue() {
      return this.sharePrice * this.portfolioStock.totalShares;
    },
    // stockName () {
    //   return this.stock.stockName;
    // }
  },
  methods: {
    formatPrice(value) {
      if (typeof value !== "number") {
        return value;
      }
      const formatter = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
        minimumFractionDigits: 2,
      });
      return formatter.format(value);
    },
  },
  created() {
    ServiceStocks.list().then((response) => {
      this.$store.commit("SET_STOCKS", response.data);
    });
  },
};
</script>

<style scoped>
/*TODO:Improve styling for PortfolioStockCard.*/

p {
  color: white;
  font-size: 1em;
}

.stock-card {
  max-width: 15rem;
  display: flex;
  flex-direction: column;
  margin-bottom: 1em;
  padding: 1em;
  background-color: var(--clr-yellow);
  border: 4px solid var(--clr-green);
  border-radius: 0.25rem;
  color: var(--clr-grey-10);
}

.stock-card h3 {
  border-bottom: 2px solid var(--clr-green);
  margin-bottom: 1em;
  margin-top: 0.25em;
  font-size: 1.5rem;
  font-weight: 900;
}
.stock-card h4 {
  margin-bottom: 0.75em;
  padding-bottom: 0.25em;
  font-size: 1.25rem;
  font-weight: 900;
  border-bottom: 1px solid var(--clr-pri-40);
  width: fit-content;
}

.stock-symbol {
  font-family: "Limelight", cursive;
  font-size: 1.5rem;
  color: var(--clr-grey-10);
  margin-bottom: 0.25em;
}
</style>