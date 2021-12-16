<template>
  <div class="view-new-transaction">
    <transaction-new-form v-bind:stock="this.stock">

    </transaction-new-form>
  </div>
</template>

<script>
import TransactionNewForm from "@/components/transactions/TransactionNewForm";

export default {
  name: "ViewNewTransaction",
  components: {
    TransactionNewForm
  },
  props: {
    stock: Object
  }
}
</script>

<style scoped>

</style>