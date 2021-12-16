<template>
  <div>
    <form class="transaction-form" v-on:submit="saveTransaction">
      <input v-model="transaction.transactionType" list="transactionType" name="transactionType"/>
      <datalist id="transactionType" autocomplete="off">
        <option value="BUY"></option>
        <option value="SELL"></option>
      </datalist>
      <input min="0" v-model="transaction.transactionShares" placeholder="Number of Shares" type="number"/>
      <button>Save</button>
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
      if (this.transaction.transactionAmount == null || this.transaction.transactionAmount === 0) {
        this.transaction.transactionAmount = this.transaction.transactionShares * this.transaction.sharePrice;
      } else if (this.transaction.transactionShares == null || this.transactionShares === 0) {
        this.transaction.transactionShares = this.transaction.transactionAmount / this.transaction.sharePrice;
      }
      serviceTransactions.create(this.transaction).then((response) => {
        if (response && response.status === 201) {
          this.$router.push('/');
        }
      });
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