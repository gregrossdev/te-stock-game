<template>
  <div class="transaction-new-form">
    <form class="transaction-form" v-on:submit.prevent>
      <h3>{{this.stock.stockSymbol}}</h3>
      <h3>Buy or Sell?</h3>

<!--      <input type="radio" id="buy" name="buy-sell" value="BUY" checked v-model="this.transaction.transactionType">-->
<!--      <label for="buy">BUY</label>-->
<!--      <input type="radio" id="sell" name="buy-sell" value="SELL" v-model="this.transaction.transactionType">-->
<!--      <label for="sell">SELL</label>-->

      <select v-model="transaction.transactionType">
        <option>BUY</option>
        <option>SELL</option>
      </select>

      <input v-model="transaction.transactionShares" placeholder="Number of Shares" type="number"/>
      <button type="submit" v-on:click="saveTransaction()">Save</button>
    </form>
  </div>
</template>

<script>
import serviceTransactions from "@/services/ServiceTransactions";
// import servicePortfolios from "@/services/ServicePortfolios";

export default {
  name: "transaction-new-form",
  props: {
    stock: Object
    // stockSymbol: String,
    // sharePrice: Number,
  },
  // computed: {
  //   calculatedTransactionAmount() {
  //     return (this.stock.sharePrice * this.transaction.transactionShares);
  //   },
  //   calculatedTransactionShares() {
  //     return (this.transaction.transactionAmount / this.stock.sharePrice);
  //   }
  // },
  data() {
    return {
      transaction: {
        portfolioId: this.$store.state.activePortfolio.portfolioId,
        stockSymbol: this.stock.stockSymbol,
        transactionType: "",
        transactionAmount: 0,
        transactionShares: 0,
        sharePrice: this.stock.sharePrice
      }
    }
  },

  methods: {
    saveTransaction() {
      if (this.transaction.transactionAmount === 0) {
        this.transaction.transactionAmount = this.transaction.transactionShares * this.transaction.sharePrice;
      } else if (this.transactionShares === 0) {
        this.transaction.transactionShares = this.transaction.transactionAmount / this.transaction.sharePrice;
      }
      serviceTransactions.create(this.transaction).then((response) => {
        if (response && response.status === 201) {
          // this.getPortfolios();
          this.resetTransaction();
          this.$router.push({ name: 'ViewGame', params: { gameId: this.$store.state.activePortfolio.gameId }});
        }
      });
    },
    // getPortfolios() {
    //   servicePortfolios
    //       .getPortfolioByUserIdAndGameId(
    //           this.$store.state.user.id,
    //           this.$route.params.gameId
    //       )
    //       .then((response) => {
    //         this.$store.commit("SET_ACTIVE_PORTFOLIO", response.data);
    //       });
    //   servicePortfolios
    //       .getPortfoliosByGameId(this.$store.state.activeGame.gameId)
    //       .then((response) => {
    //         this.$store.commit("SET_GAME_PORTFOLIOS", response.data);
    //       });
    // },
    resetTransaction() {
      this.transaction = {
        portfolioId: this.$store.state.activePortfolio.portfolioId,
        stockSymbol: this.stockSymbol,
        transactionType: "",
        transactionAmount: null,
        transactionShares: null,
        sharePrice: this.sharePrice,
      }
    }
  }
};
</script>

<style scoped>
.transaction-form {
  display: flex;
  flex-direction: column;
}

.transaction-form input, .transaction-form button {
  margin-bottom: 0;
  margin-top: 0.5em;
}

.hidden {
  display: none;
}
</style>


<!--      TODO: Allow user to select either number of shares OR total dollar amount, if transaction is a BUY.-->
<!--      TODO: Only allow user to select POSITIVE numbers for dollar amount and number of shares.-->
<!--      TODO: For BUY, check that the user has enough money before submitting form.-->
<!--      TODO: Only allow user to SELL a stock if they already have shares of that stock.-->
<!--      TODO: For SELL, check that the user has that many shares available to sell.-->
<!--      TODO: Implement some kind of error message/alert for invalid input, or if the server throws an error.-->
