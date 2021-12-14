<template>
  <div>
    <h2>All Transactions</h2>
    <transaction-table />
  </div>
</template>

<script>
import TransactionTable from "@/components/transactions/TransactionTable";

export default {
  name: "alltransactions",
  components: { TransactionTable },
};
</script>

<style>
</style>