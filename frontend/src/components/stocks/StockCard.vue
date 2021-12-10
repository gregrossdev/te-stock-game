<template>
  <div>
    <h1>Current stocks available for purchase</h1>
    <h2>Stock Ticker - Stock Price</h2>
    <div class="stocks">
      <article
        class="stock"
        v-for="data in dataStocks"
        v-bind:key="data.stockSymbol"
      >
        <h3>{{ data.stockSymbol }} - ${{ data.sharePrice }}</h3>
      </article>
    </div>
  </div>
</template>

<script>
import requestStocks from "@/services/ServiceStocks";

export default {
  name: "stock-card",
  data() {
    return {
      dataStocks: [],
    };
  },
  created() {
    requestStocks.list().then((response) => {
      this.dataStocks = response.data;
    });
  },
};
</script>

<style>
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
  width: 12rem;
}
</style>



// <section class="stocks">
//       <article
//         v-for="data in dataStocks"
//         v-bind:key="data.sharePrice"
//         class="stock"
//       >
//         <h4>{{ data.stockSymbol }}</h4>
//         <h4>{{ data.sharePrice }}</h4>
//       </article>
//     </section>