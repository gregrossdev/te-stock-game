<template>
  <div>
    <div class="transactions">
      <table
        class="transaction"
        v-for="transaction in this.$store.state.transactions"
        v-bind:key="transaction.transactionId"
      >
      <thead>
          <tr>
            <th>transactionId</th>
            <th>portfolioId</th>
            <th>stockSymbol</th>
            <th>transactionType</th>
            <th>transactionAmount</th>
            <th>transactionShares</th>
            <th>sharePrice</th>
            <th>transactionTimestamp</th>
            <th>transactionStatus</th>
            <th>portfolioCashAfterTransaction</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{transaction.transactionId}}</td>
            <td>{{transaction.portfolioId}}</td>
            <td>{{transaction.stockSymbol}}</td>
            <td>{{transaction.transactionType}}</td>
            <td>{{transaction.transactionAmount}}</td>
            <td>{{transaction.transactionShares}}</td>
            <td>{{transaction.sharePrice}}</td>
            <td>{{transaction.transactionTimestamp}}</td>
            <td>{{transaction.transactionStatus}}</td>
            <td>{{transaction.portfolioCashAfterTransaction}}</td>
          </tr>
        </tbody>
      </table>

    </div>
  </div>
</template>

<script>
import requestTransactions from "@/services/ServiceTransactions";

export default {
  name: "stock-list",
  methods: {
    getTransactions() {
      requestTransactions.list().then((response) => {
        this.$store.commit("SET_TRANSACTIONS", response.data);
      });
    },
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


