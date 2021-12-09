<template>
  <div>
     <h1>Current stocks available for purchase</h1>
    <h2>Stock Ticker - Stock Price</h2>
    <article v-for="data in dataStocks" v-bind:key="data.stockSymbol">
      <h3>{{data.stockSymbol}} - ${{data.sharePrice}}</h3>
    </article>
  </div>
</template>

<script>
import requestStocks from "@/services/ServiceStocks";

export default {
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

<style></style>
