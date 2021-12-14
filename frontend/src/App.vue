<template>
  <div id="app">
    <div id="nav">
      <nav v-if="$store.state.token !== ''" class="nav-profile">
        <router-link v-bind:to="{ name: 'profile' }">Profile</router-link>
        <router-link v-bind:to="{ name: 'ViewNewGame' }">New Game</router-link>
        <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
      </nav>
      <div class="all">
        <router-link v-bind:to="{ name: 'ViewAllStocks' }">Stocks
        </router-link
        >&nbsp;|&nbsp;

        <router-link v-bind:to="{ name: 'ViewAllGames' }">Games
        </router-link
        >&nbsp;|&nbsp;
      </div>
    </div>
    <router-view/>
  </div>
</template>


<style scoped>
#app {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  min-height: 100vh;
  max-width: 960px;
}

#nav {
  padding-top: 1em;
}

.nav-profile {
  display: flex;
  justify-content: space-between;
  margin-bottom: 1em;
}

.nav-profile a, .all a {
  font-size: 1.35rem;
  font-weight: 800;
  color: inherit;
  border-bottom: 1px solid var(--clr-grey-80);
}

button {
  font-size: 1rem;
  padding: 0.5em;
}
</style>

