<template>
  <router-link v-bind:to="{ name: 'ViewGame', params: { gameId: this.gameId } }">
    <article class="game card">
      <h2>Game #{{ this.gameId }}</h2>
      <img src="https://source.unsplash.com/300x150/?stocks" alt="stocks" />
    </article>
  </router-link>
</template>

<script>
// import serviceGames from "@/services/ServiceGames";
// import servicePortfolios from "@/services/ServicePortfolios";

export default {
  name: "game-card",
  props: {
    gameId: Number, // TODO: MAKE SURE PARENT COMPONENT PASSES A GAMEID TO EACH GAMECARD AS A PROP.
  },
  methods: {
    viewGame(gameId) {
      this.$router.push(`/game/${gameId}`);
    },
  }
};
</script>

<style scoped>



article img {
  width: 100%;
  border: 1px solid var(--clr-grey-30);
  border-radius: 0.25em;
}

h2 {
  color: var(--clr-grey-90);
  padding: 0.25em 0;
  margin: 0; 
  margin-left: 0.15em;
}


</style>


