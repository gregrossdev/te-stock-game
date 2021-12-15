<template>
  <div id="app">
    <div id="nav">
      <nav class="nav-profile" v-if="$store.state.token !== ''">
        <router-link v-bind:to="{ name: 'profile' }">Profile</router-link>
        <router-link class="btn-create" v-bind:to="{ name: 'ViewNewGame' }">Create Game</router-link>
        <router-link v-bind:to="{ name: 'logout' }">Logout</router-link>
      </nav>
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

.btn-create {
  background-color: var(--clr-green);
  padding: 0.1em 0.5em;
  border-radius: 0.25rem; 
}

</style>

