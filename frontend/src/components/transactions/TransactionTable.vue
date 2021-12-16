<template>
  <section class="transactions">
    <div
      class="transaction"
      v-for="transaction in this.portfolioTransactions"
      v-bind:key="transaction.transactionId"
    >
      <div class="well">
        <h3>Stock</h3>
        <p>{{ transaction.stockSymbol }}</p>
      </div>

      <div class="well">
        <h3>Buy/Sell</h3>
        <p>{{ transaction.transactionType }}</p>
      </div>

      <div class="well">
        <h3>Amount</h3>
        <p>{{ formatPrice(transaction.transactionAmount) }}</p>
      </div>

      <div class="well">
        <h3>Shares</h3>
        <p>{{ transaction.transactionShares }}</p>
      </div>

      <div class="well">
        <h3>Price</h3>
        <p>{{ formatPrice(transaction.sharePrice) }}</p>
      </div>

      <div class="well">
        <h3>Timestamp</h3>
        <p>{{ transaction.transactionTimestamp }}</p>
      </div>

      <div class="well">
        <h3>Status</h3>
        <p>{{ transaction.transactionStatus }}</p>
      </div>

      <div class="well">
        <h3>Cash Balance</h3>
        <p>
          {{ formatPrice(transaction.portfolioCashAfterTransaction) }}
        </p>
      </div>
    </div>
  </section>
</template>

<script>
import requestTransactions from "@/services/ServiceTransactions";

export default {
  name: "transaction-table",
  computed: {
    portfolioTransactions() {
      return this.$store.state.transactions.filter(
        (transaction) =>
          transaction.portfolioId ===
          this.$store.state.activePortfolio.portfolioId
      );
    },
  },
  methods: {
    getTransactions() {
      requestTransactions
        .getTransactionsByPortfolioId(
          this.$store.state.activePortfolio.portfolioId
        )
        .then((response) => {
          this.$store.commit("SET_TRANSACTIONS", response.data);
        });
    },
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
    this.getTransactions();
  },
};
</script>

<style scoped>
.transaction {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: 1fr;
  border: 3px solid var(--clr-yellow);
}

.well {
  background-color: var(--clr-green); 
  padding: 0.35em 0.5em;
}
.well h3 {
  font-weight: 900;
  color: var(--clr-grey-20);
  border-bottom: 1px solid var(--clr-grey-50); 
  width: fit-content; 
}

.well p {
  font-weight: 700;
  color: var(--clr-grey-10);
}

</style>


