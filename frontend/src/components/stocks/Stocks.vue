<template>
  <div>
      <p v-for="data in dataStocks"
        v-bind:key="data.sharePrice"
        >{{stock.sharePrice}}</p>
      </div>
</template>

<script>
import requestStocks from "@/services/ServiceStocks";

export default {
    data() {
        return {
            dataStocks: [],

        }
    },

}
</script>

<style>

</style>