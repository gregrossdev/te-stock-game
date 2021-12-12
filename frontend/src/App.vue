<template>
  <div id="app">
    <div id="nav">
      <nav class="nav-profile">
        <router-link v-bind:to="{ name: 'profile' }">Profile</router-link
        >
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
      </nav>
      <nav>
        <router-link v-bind:to="{ name: 'ViewAllStocks' }">Stocks</router-link
        >&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'ViewAllGames' }">Games</router-link
        >&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'ViewAllPortfolios' }">Portfolios</router-link
        >&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'ViewAllTransactions' }">Transactions</router-link
        >&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'ViewAllUsers' }">Users</router-link>
        
      </nav>
    </div>
    <router-view />
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

.nav-profile {
  display: flex;
  justify-content: space-between;
}

.nav-profile a{
  font-size: 1.35rem;
  font-weight: 800;
  color: inherit;
  border-bottom: 1px solid var(--clr-grey-80);   
}
</style>