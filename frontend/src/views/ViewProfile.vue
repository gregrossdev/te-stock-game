<template>
  <main class="profile">
    <h2>Welcome to the Virtual Stock Market, {{ this.$store.state.user.username }}!</h2>
    <p>Your user ID is {{ this.$store.state.user.id }}.</p>
  
    <section>
      <game-list></game-list>
    
    </section>
<!--    <section>-->
<!--      <stock-list /> -->
<!--    </section>-->
  </main>
</template>


<script>

import GameList from "@/components/games/GameList.vue";

export default {
  name: "profile",
  components: {
    GameList
  },
};

</script>

<style scope> 
  section {
    margin-bottom: 4rem; 
  }
</style>
