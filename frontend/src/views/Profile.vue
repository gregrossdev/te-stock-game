<template>
  <main class="profile">
    <nav>username</nav>
    <!-- <header>
      <h1>Virtual Stock Market Game</h1>
      <p>Welcome to the Virtual Stock Market Game!</p>
    </header> -->
    <section>
      <game-card />
    </section>
    <section></section>
  </main>
</template>

<script>
import GameCard from "../components/games/GameCard.vue";

export default {
  name: "profile",
  components: {
    GameCard,
  },
};
</script>
