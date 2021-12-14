<template>
  <div>
    <h2>All Stocks</h2>
    <stock-list/>
  </div>
</template>

<script>
import StockList from "@/components/stocks/StockList.vue";

export default {
  name: "allstocks",
  components: { 
    StockList, 
 }, //settopics
};
</script>

<style>
</style>