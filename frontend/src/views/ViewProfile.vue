<template>
  <main class="main">
    <div class="h-wrap-2">
      <h2>
        Welcome to the Virtual Stock Market,
        {{ capitalizedUsername }}!
      </h2>
    </div>

    <section class="games-list">
      <game-list />
    </section>
  </main>
</template>


<script>
import GameList from "@/components/games/GameList.vue";

export default {
  name: "profile",
  components: {
    GameList,
  },
  computed: {
    capitalizedUsername() {
      let username = this.$store.state.user.username;
      return username.trim().replace(/^\w/, (c) => c.toUpperCase());
    }
  }
};
</script>

<style scope>

</style>
