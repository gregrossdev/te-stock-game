<template>
  <div>
    
  </div>
</template>

<script>
import serviceTransactions from "@/services/ServiceTransactions";

export default {
  name: "transaction-new-form",
  data() {
    return {
      transaction: {
        portfolioId: this.$store.state.activePortfolio.portfolioId,
        stockSymbol: "",
        transactionType: "",
        transactionAmount: null,
        transactionShares: null,
        sharePrice: this.$store.state.stocks.find(stock => stock.stockSymbol === this.stockSymbol).sharePrice,
      }

// EXAMPLE OF JSON RETURN OBJECT FROM SERVER:
// {
//   "transactionId": 1,
//     "portfolioId": 1,
//     "stockSymbol": "RKLB",
//     "transactionType": "BUY",
//     "transactionAmount": 1000,
//     "transactionShares": 10,
//     "sharePrice": 100,
//     "transactionTimestamp": "2021-12-01T18:00:00.000+00:00",
//     "transactionStatus": "COMPLETED",
//     "portfolioCashAfterTransaction": 99000
// }

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
          this.$router.push('/game/:gameId/portfolio/:portfolioId'); // NOT SURE IF THIS IS THE RIGHT WAY OR PATH TO RE-ROUTE THE URL. NEEDS TO GO BACK TO THE CURRENT/ACTIVE GAME VIEW.
        }
      });
    }
  }
};
</script>

<style>

</style>