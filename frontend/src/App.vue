<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'profile' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'test' }">This is an oversized link for testing data</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>


<style scoped>

#app {
  display: flex; 
  flex-direction: column;
  margin: 0 auto;
  min-height:100vh;
  max-width: 800px; 
}

</style>