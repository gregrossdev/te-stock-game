<template>
  <div id="app">

    <div id="nav" v-if="$store.state.token !== ''">
      <nav class="nav-profile" >
        <router-link v-bind:to="{ name: 'profile' }">Profile</router-link>
        <router-link class="btn-create" v-bind:to="{ name: 'ViewNewGame' }">Create Game</router-link>
        <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
      </nav>
    </div>

    <div id = "router-view">
      <router-view/>
    </div>

    <footer>
      <router-link v-bind:to="{ name: 'ViewWinner' }">Game Winners</router-link>
      <h2>Made with ☕ && ❤️ by Stonks Dev Team</h2>
    </footer>

  </div>
</template>


<style scoped>
#app {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  min-height: 100vh;
  max-width: 980px;
  margin-top: 2em;
  padding: 1em; 
  box-shadow: var(--light-shadow);
}

#nav {
  padding-top: 1em;
  text-align: center;
  background-color: var(--clr-pri);
  border-radius: 0.5rem 0.5rem 0px 0px; 
  border: 2px solid var(--clr-green);
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

.btn-create {
  background-color: var(--clr-green);
  padding: 0.1em 0.5em;
  border-radius: 0.25rem; 
}

footer {
  background-color: var(--clr-pri);
  border: 1px solid var(--clr-green);
  margin-bottom: 1rem;
  border-radius: 0 0 0.25rem 0.25rem;
}

footer h2 {
  padding: 1.5em 0 1em;
  text-align: center;
}

footer a {
  display: block;
  font-weight: 900;
  font-size: 1.35rem;
  text-align: center; 
  padding: 1em 0;
  color: var(--clr-yellow);
  border: 1px solid var(--clr-green);
}

</style>

