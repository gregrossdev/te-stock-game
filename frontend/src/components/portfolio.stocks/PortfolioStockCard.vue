<template>
  <div class="stock-card">

    <h5>Stock: {{ stock.stockSymbol }}</h5>
    <p>Name: {{ stock.stockName }}</p>
    <p>Shares: {{portfolioStock.totalShares}}</p>
    <p>Share Price: {{ formatPrice(sharePrice) }}</p>
    <p>Investment Value: {{formatPrice(investmentValue)}}</p>
  </div>
</template>

<script>

import ServiceStocks from "@/services/ServiceStocks";

export default {
  name: "stock-card",
  props: {
    portfolioStock: Object
  },
  computed: {
    stock () {
      return this.$store.state.stocks.find(item => item.stockSymbol === this.portfolioStock.stockSymbol)
    },
    sharePrice () {
      return this.stock.sharePrice;
    },
    investmentValue () {
      return (this.sharePrice * this.portfolioStock.totalShares);
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
      const formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: 2
      });
      return formatter.format(value);
    }
  },
  created() {
    ServiceStocks.list().then(response => {
      this.$store.commit("SET_STOCKS", response.data);
    })
  }
};
</script>

<style scoped>

/*TODO:Improve styling for PortfolioStockCard.*/

p {
  color: white;
  font-size: 1em;
}

.stock-card {
  color: white;
  border: 1px solid var(--clr-grey-70);
  margin-bottom: 1em;
  border-radius: 0.25rem;
  padding: 0.5em;
  width: 10rem;
  display: flex;
  flex-direction: column;
}

</style>