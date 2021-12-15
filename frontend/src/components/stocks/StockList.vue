<template>
  <div class="stocks">
    <article v-for="stock in this.$store.state.stocks" v-bind:key="stock.stockSymbol" class="stock card">
      <input :value="stock.stockSymbol" class="hidden" type="text"/>
      <h5>{{ stock.stockSymbol }} - ${{ stock.sharePrice }}</h5>
      <transaction-new-form v-model="stock.stockSymbol" v-bind:sharePrice="stock.sharePrice" v-bind:stockSymbol="stock.stockSymbol"/>
    </article>
  </div>
</template>

<script>
import requestStocks from "@/services/ServiceStocks";
import TransactionNewForm from "../transactions/TransactionNewForm.vue";

export default {
  name: "stock-list",

  components: {
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
  width: 10rem;
  display: flex;
  flex-direction: column;
}


.hidden {
  display: none;
}
</style>