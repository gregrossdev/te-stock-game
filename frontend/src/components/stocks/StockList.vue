<template>
  <div class="stock-list">
    <stock-card v-for="stock in this.$store.state.stocks" v-bind:key="stock.stockSymbol" class="stock card"
                v-bind:stock="stock"></stock-card>
  </div>
</template>

<script>
import requestStocks from "@/services/ServiceStocks";
import StockCard from "@/components/stocks/StockCard";

export default {
  name: "stock-list",

  components: {
    StockCard
  },

  created() {
    requestStocks.list().then((response) => {
      this.$store.commit("SET_STOCKS", response.data);
    });
  },
};
</script>

<style scoped>

.stock-list {
  padding: 1rem;
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
}

</style>