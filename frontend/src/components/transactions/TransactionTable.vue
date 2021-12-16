<template>
  <div>

<!--    TODO: EDIT this table so that it can be used to display the CURRENT USER'S TRANSACTION HISTORY.-->

    <div class="transactions">
      <table>
      <thead>
          <tr>
            <th>Stock</th>
            <th>Buy/Sell</th>
            <th>Amount</th>
            <th>Shares</th>
            <th>Price</th>
            <th>Timestamp</th>
            <th>Status</th>
            <th>Cash Balance</th>
          </tr>
        </thead>
        <tbody>
        <tr class="transaction"
            v-for="transaction in this.portfolioTransactions"
            v-bind:key="transaction.transactionId"
        >
            <td>{{transaction.stockSymbol}}</td>
            <td>{{transaction.transactionType}}</td>
            <td>{{formatPrice(transaction.transactionAmount)}}</td>
            <td>{{transaction.transactionShares}}</td>
            <td>{{formatPrice(transaction.sharePrice)}}</td>
            <td>{{transaction.transactionTimestamp}}</td>
            <td>{{transaction.transactionStatus}}</td>
            <td>{{formatPrice(transaction.portfolioCashAfterTransaction)}}</td>
          </tr>
        </tbody>
      </table>

    </div>
  </div>
</template>

<script>
import requestTransactions from "@/services/ServiceTransactions";

export default {
  name: "transaction-table",
  computed: {
    portfolioTransactions() {
      return this.$store.state.transactions.filter(transaction =>
        transaction.portfolioId === this.$store.state.activePortfolio.portfolioId);
    }
  },
  methods: {
    getTransactions() {
      requestTransactions.getTransactionsByPortfolioId(this.$store.state.activePortfolio.portfolioId).then((response) => {
        this.$store.commit("SET_TRANSACTIONS", response.data);
      });
    },
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
    this.getTransactions();
  },
};
</script>

<style scoped>

table {
  margin-bottom: 1em;  
}

table tr {
  text-align: left;
}

th,
td {
  padding: 0 1em 0 0;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

</style>


