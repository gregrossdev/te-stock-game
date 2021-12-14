<template>
  <main class="profile">
    <nav>
      <game-new-form />
    </nav>
    <section>
      <game-list />
      <!-- <article v-for="data in $store.state.games" v-bind:key="data.gameId" class="game">
        <h4>{{ data }}</h4>
      </article>  -->
    </section>
    <section>
      <stock-list /> 
    </section>
  </main>
</template>


<script>
import GameList from "../components/games/GameTable.vue";
import GameNewForm from "../components/games/GameNewForm.vue";

import StockList from "../components/stocks/StockList.vue";

export default {
  name: "profile",
  components: {
    GameList,
    GameNewForm, 
    StockList,
  },
};

</script>

<style scope> 
  section {
    margin-bottom: 4rem; 
  }
</style>
