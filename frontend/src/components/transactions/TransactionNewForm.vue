<template>
  <div class="transaction-new-form">
    <form class="form-onboard" v-on:submit.prevent>
      <h3>{{this.stock.stockSymbol}} | {{this.stock.stockName}}</h3>
      <h3>Buy or Sell?</h3>

      <select v-model="transaction.transactionType">
        <option>BUY</option>
        <option>SELL</option>
      </select>

      <label for="share">Shares</label>
      <input v-model="transaction.transactionShares" placeholder="Number of Shares" type="number" min="0"/>
      <label for="dollars">Total Money</label>
      <input readonly :value="dollarValue()" placeholder="$" type="number" class="money"/>
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
          this.resetTransaction();
          this.$router.push({ name: 'ViewGame', params: { gameId: this.$store.state.activePortfolio.gameId }});
        }
      });
    },
    dollarValue() {
      return this.transaction.transactionAmount = this.transaction.transactionShares * this.transaction.sharePrice; 
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
