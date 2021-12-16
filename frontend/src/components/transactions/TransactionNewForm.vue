<template>
  <div class="transaction-new-form">
    <form class="form-onboard" v-on:submit.prevent>
      <h3>{{this.stock.stockSymbol}} | {{this.stock.stockName}}</h3>
      <h3>Buy or Sell?</h3>

      <select v-model="transaction.transactionType">
        <option>BUY</option>
        <option>SELL</option>
      </select>

      <h3>How Many Shares?</h3>
      <input type="number" step="any" v-model="shares" @change="updateTotal">

      <h3>Total Amount?</h3>
      <input type="number" step="any" v-model="totalAmount" @change="updateShares">

      <button type="submit" v-on:click="saveTransaction()">Save</button>
    </form>
  </div>
</template>

<script>
import serviceTransactions from "@/services/ServiceTransactions";

export default {
  name: "transaction-new-form",
  props: {
    stock: Object
  },

  data() {
    return {
      shares: 0,
      totalAmount: 0,
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
    updateShares() {
      this.shares = this.totalAmount / this.stock.sharePrice;
    },
    updateTotal() {
      this.totalAmount = this.shares * this.stock.sharePrice;
    },
    saveTransaction() {
      this.transaction.transactionShares = this.shares;
      this.transaction.transactionAmount = this.totalAmount;
      serviceTransactions.create(this.transaction).then((response) => {
        if (response && response.status === 201) {
          this.resetTransaction();
          this.$router.push({ name: 'ViewGame', params: { gameId: this.$store.state.activePortfolio.gameId }});
        }
      });
    },
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

.money::before {
  content: "$"; 
}

.form-onboard {
  justify-content: space-between;
  margin: 2rem auto ; 
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
