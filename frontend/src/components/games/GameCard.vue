<template>
  <router-link v-bind:to="{ name: 'ViewGame', params: { gameId: this.gameId } }">
    <div class="game-card">
      <h3>Game #{{ this.gameId }}</h3>
      <img src="https://source.unsplash.com/300x150/?stocks" alt="stocks" />
    </div>
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
section {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
}

article {
  max-width: 320px;
  overflow: hidden;
  border: 1px solid var(--clr-grey-30);
  padding: 0.25em;
  border-radius: 0.25em;
}

article > * {
  overflow-wrap: break-word;
}

table tr {
  text-align: left;
}

table th {
  margin-left: 10em;
}

th,
td {
  padding: 0 15px 0 0;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
.game-card {
  border: 1px solid var(--clr-grey-70);
  margin-bottom: 1em;
  border-radius: 0.25rem;
  padding: 0.5em;
  width: 12rem;
}
</style>


