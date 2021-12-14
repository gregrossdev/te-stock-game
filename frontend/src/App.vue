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

<!--
TODO: DECIDE WHETHER OR NOT TO KEEP THIS NAV LINK.
SHOULD USERS BE ABLE TO VIEW STOCK PRICES OUTSIDE OF A CURRENT/ACTIVE GAME?
IF SO, WE NEED TO AT LEAST MAKE SURE THAT THEY CAN'T BUY OR SELL STOCKS UNTIL THEY'RE "INSIDE" A PARTICULAR GAME
-->
        <router-link v-bind:to="{ name: 'ViewAllStocks' }">Stocks</router-link
        >&nbsp;|&nbsp;

<!--
TODO: MAKE THIS "YOUR GAMES" AND DISPLAY GAMES FOR CURRENT USER ONLY
-->
        <router-link v-bind:to="{ name: 'ViewAllGames' }">Games</router-link
        >&nbsp;|&nbsp;

<!--
TODO: GET RID OF THIS NAV LINK, VIEW ALL PORTFOLIOS SHOULD ONLY HAPPEN WITHIN ACTIVE GAME, AS THE LEADERBOARD
-->
        <router-link v-bind:to="{ name: 'ViewAllPortfolios' }">Portfolios</router-link
        >&nbsp;|&nbsp;

<!--
TODO: GET RID OF THIS NAV LINK, VIEW ALL TRANSACTIONS SHOULD ONLY HAPPEN WITHIN ACTIVE GAME, FOR ACTIVE USER AND PORTFOLIO
-->
        <router-link v-bind:to="{ name: 'ViewAllTransactions' }">Transactions</router-link
        >&nbsp;|&nbsp;

<!--
TODO: GET RID OF THIS NAV LINK, BUT WE WILL NEED TO VIEW ALL USERS WHENEVER A USER INVITES OTHER USERS TO A CREATED GAME
-->
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