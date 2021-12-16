<template>
  <div class="games">

    <section class="games-grid">
      <game-card
        v-for="game in this.activeGames"
        v-bind:key="game.gameId"
        v-bind:game="game"
      ></game-card>
    </section>
  </div>
</template>

<script>
import GameCard from "@/components/games/GameCard";

export default {
  name: "game-list",
  components: { GameCard },
  computed: {
    inactiveGameIds() {
      let inactiveGameIdsArray = [];
      this.$store.state.pendingPortfolios.forEach(portfolio => {
        inactiveGameIdsArray.push(portfolio.gameId);
      })
      return inactiveGameIdsArray;
    },
    activeGames() {
      return this.$store.state.games.filter(item => !(this.inactiveGameIds.includes(item.gameId)));
    }
  }
};
</script>

<style scoped>

h2 {
  font-size: 2rem;
}

section {
  margin-bottom: 0; 
}

</style>


