<template>
  <section>
    <h2>Your Games</h2>
    <game-card v-for="game in this.$store.state.games" v-bind:key="game.gameId" v-bind:gameId="game.gameId"></game-card>
  </section>
</template>

<script>
import requestGames from "@/services/ServiceGames";
import GameCard from "@/components/games/GameCard";

export default {
  name: "game-list",
  components: {GameCard},
  methods: {},
  created() {
    requestGames.getGamesByUserId(this.$store.state.user.id).then((response) => {
      this.$store.commit("SET_GAMES", response.data);
    });
  },
};
</script>

<style scoped>
section {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 0.5rem;
}

article {
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
</style>


