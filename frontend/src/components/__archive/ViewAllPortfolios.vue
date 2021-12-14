<template>
  <div>
    <h2>All Portfolios</h2>
    <portfolio-table />
  </div>
</template>

<script>
import PortfolioTable from "@/components/portfolios/PortfolioTable";

export default {
  name: "allportfolios",
  components: { PortfolioTable },
};
</script>

<style>
</style>