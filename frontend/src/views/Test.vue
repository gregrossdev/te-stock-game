<template>
  <main>
    <h1>Test Data</h1>
    <section class="stocks">
      <article
        v-for="data in testData"
        v-bind:key="data.sharePrice"
        class="stock"
      >
        <h2>{{ data.stockSymbol }}</h2>
        <h3>{{ data.sharePrice }}</h3>
      </article>
    </section>
  </main>
</template>

<script>
import requests from "../services/TestDataService";

export default {
  name: "test-data",
  data() {
    return {
      testData: [],
    };
  },
  created() {
    requests.list().then((response) => {
      this.testData = response.data;
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

h1 {
  margin-left: 1rem;
}

.stocks {
  border: 3px solid blue;
  padding: 1rem;  
}

.stock {
  border-bottom: 1px solid var(--clr-grey-90);
  margin-bottom: 1em; 
}


</style>

