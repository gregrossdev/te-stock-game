<template>
  <main class="profile">
    <nav class="profile-nav-bar">username Profile</nav>
    <button v-on:click="createGame()">Create New Game</button>
    <nav>username</nav>
    <!-- <header>
      <h1>Virtual Stock Market Game</h1>
      <p>Welcome to the Virtual Stock Market Game!</p>
    </header> -->
    <section>
      <game-card />
      Active Games

    <article class="game-card">
      Game Card #
    </article>

    </section>

    <section>
      Portfolio
    <nav class="stocksearch-nav-bar">Search Stocks</nav>

    Stock Cards

    </section>

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

<style scoped>

main {
  width: 600px;
  margin: 0 auto;
  margin-top: 2rem;
}

.profile-nav-bar {
  background-color: blue 
}

.profile {
  border: 3px solid var(--clr-pri-40);
}

</style>
