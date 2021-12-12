<template>
  <div>
    <div class="portfolios">
      <table
        class="portfolio"
        v-for="portfolio in this.$store.state.portfolios"
        v-bind:key="portfolio.portfolioId"
      >
      <thead>
          <tr>
            <th>portfolioId</th>
            <th>userId</th>
            <th>gameId</th>
            <th>portfolioCash</th>
            <th>portfolioStocksValue</th>
            <th>portfolioTotalValue</th>
            <th>portfolioStatus</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{portfolio.portfolioId}}</td>
            <td>{{portfolio.userId}}</td>
            <td>{{portfolio.gameId}}</td>
            <td>{{portfolio.portfolioCash}}</td>
            <td>{{portfolio.portfolioStocksValue}}</td>
            <td>{{portfolio.portfolioTotalValue}}</td>
            <td>{{portfolio.portfolioStatus}}</td>
          </tr>
        </tbody>
      </table>

    </div>
  </div>
</template>

<script>
import requestPortfolios from "@/services/ServicePortfolios";

export default {
  name: "stock-list",
  methods: {
    getPortfolios() {
      requestPortfolios.list().then((response) => {
        this.$store.commit("SET_PORTFOLIOS", response.data);
      });
    },
  },
  created() {
    this.getPortfolios();
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


