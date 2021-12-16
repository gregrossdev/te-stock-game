<template>
  <main>
    <transaction-new-form v-bind:stock="this.stock">

    </transaction-new-form>
  </main>
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