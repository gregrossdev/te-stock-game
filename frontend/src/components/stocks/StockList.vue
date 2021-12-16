<template>
  <section>

    <input type="text" v-model="filterStock" class="stock-search" placeholder="Search Stock Names"/>
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


.stock-search {
  display: block;
  margin: 0 auto;
  margin-top: 2rem;
  font-size: 1.25rem;
  
  border-radius: 4px;
  box-shadow: var(--dark-shadow);
  margin-bottom: 0.75em;
  padding: 0.5em 0.5em;
  font-family: "Jost", sans-serif;
  font-weight: 700;
  letter-spacing: 1px;
  background-color: var(--clr-pri);
  width: 80%;  
  color: var(--clr-yellow); 
}

::placeholder { 
 color: var(--clr-yellow); 
  opacity: 1; 
} 


</style>