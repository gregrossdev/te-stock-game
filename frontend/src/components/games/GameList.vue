<template>
  <div class="games">
    <div class="h-wrap-1">
      <h2>Your Games</h2>
    </div>
    <section class="games-grid">
      <game-card
        v-for="game in this.$store.state.games"
        v-bind:key="game.gameId"
        v-bind:gameId="game.gameId"
      ></game-card>
    </section>
  </div>
</template>

<script>
import requestGames from "@/services/ServiceGames";
import GameCard from "@/components/games/GameCard";

export default {
  name: "game-list",
  components: { GameCard },
  methods: {},
  created() {
    requestGames
      .getGamesByUserId(this.$store.state.user.id)
      .then((response) => {
        this.$store.commit("SET_GAMES", response.data);
      });
  },
};
</script>

<style scoped>

h2 {
  font-size: 2rem;
}


</style>


