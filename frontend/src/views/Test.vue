<template>
  <main>
    <h1>Test Data</h1>
    <hr />
    <br />
    <h3>Users</h3>
    <section class="users">
      <article v-for="data in dataUsers" v-bind:key="data.id" class="user">
        <h4>{{ data.username }}</h4>
      </article>
    </section>
    <h3>Stocks</h3>
    <section class="stocks">
      <article
        v-for="data in dataStocks"
        v-bind:key="data.sharePrice"
        class="stock"
      >
        <h4>{{ data.stockSymbol }}</h4>
        <h4>{{ data.sharePrice }}</h4>
      </article>
    </section>
    <h3>Games</h3>
    <section class="games">
      <article v-for="data in dataGames" v-bind:key="data.gameId" class="game">
        <h4>{{ data }}</h4>
      </article>
    </section>
    <h3>Portfolios</h3>
    <section class="portfolios">
      <article
        v-for="data in dataPortfolios"
        v-bind:key="data"
        class="portfolio"
      >
        <h4>{{ data }}</h4>
      </article>
    </section>
    <h3>Transactions</h3>
    <section class="transactions">
      <article
        v-for="data in dataTransactions"
        v-bind:key="data"
        class="transaction"
      >
        <h4>{{ data }}</h4>
      </article>
    </section>

    
    <!-- <button v-on:click="updateSpce()">UPDATE SPCE</button> -->
  </main>
</template>

<script>
import requestStocks from "@/services/ServiceStocks";
import requestUsers from "@/services/ServiceUsers";
import requestGames from "@/services/ServiceGames";
import requestPortfolios from "@/services/ServicePortfolios";
import requestTransactions from "@/services/ServiceTransactions";

export default { 
  name: "test-data",
  methods: {
    updateSpce() {
      requestStocks.test();
    },
  },
  data() {
    return {
      dataStocks: [],
      dataUsers: [],
      dataGames: [],
      dataPortfolios: [],
      dataTransactions: [],
    };
  },
  created() {
    requestStocks.list().then((response) => {
      this.dataStocks = response.data;
    });
    requestUsers.list().then((response) => {
      this.dataUsers = response.data;
    });
    requestGames.list().then((response) => {
      this.dataGames = response.data;
    });
    requestPortfolios.list().then((response) => {
      this.dataPortfolios = response.data;
    });
    requestTransactions.list().then((response) => {
      this.dataTransactions = response.data;
    });
  },
};
</script>




<style scoped>
main {
  width: 600px;
  margin: 0 auto;
  margin-top: 2rem;
}

h3 {
  font-size: 1.75rem;
  color: var(--clr-grey-10);
  font-weight: 800;
}

h4 {
  font-size: 1.25rem;
  color: var(--clr-grey-10);
  font-weight: 600;
}

section {
  padding: 1rem;
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  margin-bottom: 1rem;
}

.users h4 {
  text-transform: lowercase;
}

.users {
  border: 3px solid var(--clr-pri-10);
}

.stocks {
  border: 3px solid var(--clr-pri-30);
}

.games {
  border: 3px solid var(--clr-pri-40);
}

.portfolios {
  border: 3px solid var(--clr-pri-50);
}

.transactions {
  border: 3px solid var(--clr-pri-60);
}

.stock {
  border: 1px solid var(--clr-grey-70);
  margin-bottom: 1em;
  border-radius: 0.25rem;
  padding: 0.5em;
  width: 6rem;
  height: 6rem;
}
</style>

