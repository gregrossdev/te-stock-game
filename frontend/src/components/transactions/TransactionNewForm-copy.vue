<template>
  <div>
       <form class="transaction-form" v-on:submit.prevent="saveTransaction" autocomplete="off">
      <!-- <select v-model="transaction.transactionType" list="transactionType" name="transactionType"/>
      <datalist id="transactionType" autocomplete="off">
        <option value="BUY"></option>
        <option value="SELL"></option>
      </datalist> -->

  <select v-model="transaction.transactionType" name="transactionType" id="transactionType">
    <option value="BUY">BUY</option>
    <option value="SELL">SELL</option>
  </select>
      <input v-model="transaction.transactionShares" placeholder="Number of Shares" type="number"/>
      <button type="submit"> Save </button>
    </form>
  </div>
</template>

<script>
import serviceTransactions from "@/services/ServiceTransactions";

export default {
  name: "transaction-new-form",
  props: {
    stockSymbol: String,
    sharePrice: Number,
  },
  data() {
    return {
      transaction: {
        portfolioId: this.$store.state.activePortfolio.portfolioId,
        stockSymbol: this.stockSymbol,
        transactionType: "",
        transactionAmount: null,
        transactionShares: null,
        sharePrice: this.sharePrice,
      }
    }
  },

  methods: {
    saveTransaction() {
      serviceTransactions
      .create(this.transaction)
      .then((response) => {
        if (response && response.status === 201) {
          console.log(response.data)
          this.transaction = response.data;
        }
      });
      this.resetTransaction();
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