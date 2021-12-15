<template>
  <main class="main">
    <div class="h-wrap-2">
      <h2>
        Welcome to the Virtual Stock Market,
        {{ this.$store.state.user.username }}!
      </h2>
    </div>

    <section>
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
};
</script>

<style scope>
section {
  margin-bottom: 4rem;
}
</style>
