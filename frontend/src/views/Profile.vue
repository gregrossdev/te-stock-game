<template>
  <main class="profile">
    <nav>
      <game-new-form />
    </nav>
    <section>
      <game-card />
      <article v-for="data in $store.state.games" v-bind:key="data.gameId" class="game">
        <h4>{{ data }}</h4>
      </article> 
    </section>
    <section></section>
  </main>
</template>

<script>
import GameCard from "../components/games/GameCard.vue";
import GameNewForm from "../components/games/GameNewForm.vue";


export default {
  name: "profile",
  components: {
    GameCard,
    GameNewForm
  },
};
</script>
