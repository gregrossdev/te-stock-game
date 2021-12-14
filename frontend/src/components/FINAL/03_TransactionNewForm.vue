<template>
  <div>
      <form v-on:submit.prevent="saveTransaction2" class="transaction-form">
        <input class="hidden" type="text" placeholder="stockSymbol" v-model="transaction.stockSymbol"/>
        <input list="transactionType" name="transactionType" v-model="transaction.transactionType"/>
          <datalist id="transactionType" autocomplete="off">
            <option value="BUY"></option>
            <option value="SELL"></option>
          </datalist>
      <input type="text" placeholder="transactionShares" v-model="transaction.transactionShares"/>
    <button>Save</button>
  </form>
  </div>
</template>

<script>
import serviceTransactions from "@/services/ServiceTransactions";

export default {
  name: "transaction-new-form",
  data() {
    return {
      transaction: {
        // portfolioId: this.$store.state.activePortfolio.portfolioId,
        portfolioId: 1,
        stockSymbol: "",
        transactionType: "",
        // transactionAmount: null, 
        // transactionShares: null,
         transactionAmount: 200, 
        transactionShares: 20,
        // sharePrice: this.$store.state.stocks.find(stock => stock.stockSymbol === this.stockSymbol).sharePrice,
        sharePrice: 50,
        //idk if this is what we need - we can just do the timestamp on back end?
        // transactionTimestamp: "2021-12-13 20:05:32",
        transactionStatus:"COMPLETED",
        portfolioCashAfterTransaction: 80085,
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
    },
    saveTransaction2(){
      // console.log(this.transaction.stockSymbol);
      // console.log(this.transaction.stockPrice);
      // console.log(this.transaction.transactionShares);
      console.log(this.$attrs) //this.$attrs allows me to grab the stocksymbol from the parent stockList when it is made in vfor loop
      serviceTransactions.create(this.transaction);


      // portfolioId: 1,
      //   stockSymbol: "",
      //   transactionType: "",
      //   // transactionAmount: null, 
      //   // transactionShares: null,
      //    transactionAmount: 200, 
      //   transactionShares: 20,
      //   // sharePrice: this.$store.state.stocks.find(stock => stock.stockSymbol === this.stockSymbol).sharePrice,
      //   sharePrice: 50,
      //   //idk if this is what we need
      //   // transactionTimestamp: "2021-12-13 20:05:32",
      //   transactionStatus:"COMPLETED",
      //   portfolioCashAfterTransaction: 80085,

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

.hidden{
  display: none;
}
</style>