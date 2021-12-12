<template>
  <div>
    <h2>All Stocks</h2>
    <stock-table />
  </div>
</template>

<script>
import StockTable from "@/components/stocks/StockTable";

export default {
  name: "allstocks",
  components: { StockTable },
};
</script>

<style>
</style>