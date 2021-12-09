<template>
  <main class="profile">
    <nav>username</nav>
    <!-- <header>
      <h1>Virtual Stock Market Game</h1>
      <p>Welcome to the Virtual Stock Market Game!</p>
    </header> -->
    <section>
      <game-card />
      Active Games
    </section>
    <section>
      <stocks />
    </section>
    <section></section>
  </main>
</template>


<script>
import GameCard from "../components/games/GameCard.vue";
import Stocks from "../components/stocks/Stocks.vue";

export default {
  name: "profile",
  components: {
    GameCard,
    Stocks,
}}
</script>
