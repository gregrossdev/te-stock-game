<template>
  <form v-on:submit.prevent="saveGame">
    <button>New Game</button>
  </form>
</template>

<script>
export default {
  name: "game-new-form",
  data() {
    return {
      game: {
        gameId: "",
        gameOrganizer: "",
        gameWinner: "",
        startDatetime: "",
        endDatetime: "",
        gameStatus: "",
      },
    };
  },
  methods: {
    saveGame() {
      this.$store.commit("SAVE_GAME", this.game);
      this.game = {
        gameId: "",
        gameOrganizer: "",
        gameWinner: "",
        startDatetime: "",
        endDatetime: "",
        gameStatus: "",
      };
      // this.$router.push({ name: "test" });
    },
  },
};
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  flex-basis: 100%;
  margin: 0 auto;
  border: 4px solid var(--clr-pri-90);
  padding: 2em;  
}
</style>