<template>
  <div>
    <div class="stocks">
      <table>
      <thead>
          <tr>
            <th>stockSymbol</th>
            <th>sharePrice</th>
            <th>quoteTimestamp</th>
          </tr>
        </thead>
        <tbody>
          <tr class="stock" v-for="stock in this.$store.state.stocks"
                      v-bind:key="stock.stockSymbol">
            <td>{{stock.stockSymbol}}</td>
            <td>{{stock.sharePrice}}</td>
            <td>{{stock.quoteTimestamp}}</td>
          </tr>
        </tbody>
      </table>

    </div>
  </div>
</template>

<script>
import requestStocks from "@/services/ServiceStocks";

export default {
  name: "stock-list",
  methods: {
    getStocks() {
      requestStocks.list().then((response) => {
        this.$store.commit("SET_STOCKS", response.data);
      });
    },
  },
  created() {
    this.getStocks();
  },
};
</script>

<style scoped>

table {
  margin-bottom: 1em; 
}

table tr {
  text-align: left;
}

th,
td {
  padding: 0 1em 0 0;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

</style>


