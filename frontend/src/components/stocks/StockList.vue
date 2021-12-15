<template>
  <section>
    <input type="text" v-model="filterStock" />
    <div class="stock-list">
      <article
        v-for="stock in filterStocks"
        v-bind:key="stock.stockSymbol"
        class="stock card stock-card"
      >
        <h5>
          {{ stock.stockSymbol }} |
          {{ stock.sharePrice }}
        </h5>
        <p>{{ stock.stockName }}</p>
        <transaction-new-form v-bind:sharePrice="stock.sharePrice" v-bind:stockSymbol="stock.stockSymbol"/>
      </article>
    </div>
  </section>
</template>

<script>
import requestStocks from "@/services/ServiceStocks";
import TransactionNewForm from "@/components/transactions/TransactionNewForm";

export default {
  name: "stock-list",
  components: { TransactionNewForm }, 
  data() {
    return {
      filterStock: "",
    };
  },
  created() {
    requestStocks.list().then((response) => {
      this.$store.commit("SET_STOCKS", response.data);
    });
  },
  computed: {
    filterStocks() {
      return this.$store.state.stocks.filter((stock) => {
        return stock.stockName
          .toLowerCase()
          .includes(this.filterStock.toLowerCase());
      });
    },
  },
};
</script>


<style scoped>
.stock-list {
  padding: 1.5rem 1rem;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 1rem;
}

.transaction-form {
  display: flex;
  flex-direction: column;
}

.transaction-form input,
.transaction-form button {
  margin-bottom: 0;
  margin-top: 0.5em;
}

.hidden {
  display: none;
}

.stock-card {
  color: white;
  border: 1px solid var(--clr-grey-70);
  margin-bottom: 1em;
  border-radius: 0.25rem;
  padding: 0.5em;
  width: 10rem;
  display: flex;
  flex-direction: column;
}

.stock-card p {
  color: white;
  font-size: 1em;
}
</style>