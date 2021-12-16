<template>
  <section>

<!--    TODO: Add some way to FILTER and SORT the StockList. Perhaps just use StockTable with some added functionality/styling if StockCards prove too difficult to filter and sort?-->
    <label for="stock-search">Filter the Stock List by Name </label>
    <input type="search" v-model="filterStock" />
    <div class="stock-list">
      <stock-card
        v-for="stock in filterStocks"
        v-bind:key="stock.stockSymbol"
        class="stock card"
        v-bind:stock="stock"
      />
    </div>
  </section>
</template>

<script>
import requestStocks from "@/services/ServiceStocks";
import StockCard from "@/components/stocks/StockCard";

export default {
  name: "stock-list",
  data() {
    return {
      filterStock: "",
    };
  },

  components: {
    StockCard,
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
</style>