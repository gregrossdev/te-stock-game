<template>
  <div class="games">

    <section class="games-grid">
      <game-card
        v-for="game in this.$store.getters.archivedGames"
        v-bind:key="game.gameId"
        v-bind:game="game"
      ></game-card>
    </section>
  </div>
</template>

<script>
import GameCard from "@/components/games/GameCard";

export default {
  name: "game-archived-list",
  components: { GameCard }
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


