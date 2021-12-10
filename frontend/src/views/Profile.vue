<template>
  <main class="profile">
    <nav class="profile-nav-bar">username Profile</nav>
    <button v-on:click="createGame()">Create New Game</button>
    <!-- <header>
      <h1>Virtual Stock Market Game</h1>
      <p>Welcome to the Virtual Stock Market Game!</p>
    </header> -->
    <section>
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

  </main>
</template>

<script>
export default {
  name: "profile",
};
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
