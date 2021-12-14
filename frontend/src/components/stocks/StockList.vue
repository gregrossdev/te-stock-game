<template>
  <div>
    <h1>Current stocks available for purchase</h1>
    <h2>Stock Ticker - Stock Price</h2>
    <div class="stocks">
      <article
        class="stock"
        v-for="stock in this.$store.state.stocks"
        v-bind:key="stock.stockSymbol"
      >
      <input class="hidden" type="text" :value="stock.stockSymbol"/>
        <h5>{{ stock.stockSymbol }} - ${{ stock.sharePrice }}</h5>
        <transaction-new-form v-bind:stockSymbol="stock.stockSymbol" v-bind:sharePrice="stock.sharePrice" v-model="stock.stockSymbol"/> <!-- I don;'t think this is working the way I want it to - also very backwards component building I know sorry! -->
      </article>
    </div>
  </div>
</template>

<script>
import requestStocks from "@/services/ServiceStocks";
import TransactionNewForm from "../transactions/TransactionNewForm.vue";

export default {
  name: "stock-list",

  props:{
    //i want to pass in stockSymbol to the each transaction form im building inside the list
  },

  components:{
    TransactionNewForm,
  },

  created() {
    requestStocks.list().then((response) => {
      this.$store.commit("SET_STOCKS", response.data);
    });
  },
};
</script>

<style scoped>
.stocks {
  padding: 1rem;
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
}

.stock {
  border: 1px solid var(--clr-grey-70);
  margin-bottom: 1em;
  border-radius: 0.25rem;
  padding: 0.5em;
  /* width: 20vh;
  height: 20vh; */
  width: 10rem; 
  display: flex;
  flex-direction: column;
}
.hidden{
  display: none;
}
</style>



<!--// <section class="stocks">-->
<!--//       <article-->
<!--//         v-for="data in dataStocks"-->
<!--//         v-bind:key="data.sharePrice"-->
<!--//         class="stock"-->
<!--//       >-->
<!--//         <h4>{{ data.stockSymbol }}</h4>-->
<!--//         <h4>{{ data.sharePrice }}</h4>-->
<!--//       </article>-->
<!--//     </section>-->