<template>
  <form v-on:submit.prevent="saveGame">
    <label for="startTimestamp"> Start Date & Time </label>
    <input
      type="datetime-local"
      v-model="game.startTimestamp"
    />
    <label for="endTimestamp"> End Date & Time </label>
    <input type="datetime-local" v-model="game.endTimestamp" />
    <button>Save</button>
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
        startTimestamp: "",
        endTimestamp: "",
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
        startTimestamp: "",
        endTimestamp: "",
        gameStatus: "",
      };
      // this.$router.push({ name: "profile" });
    },
  },
};
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  /* flex-basis: 80%; */
  width: 50%;
  margin: 1rem auto;
  border: 4px solid var(--clr-pri-90);
  padding: 2em;  
}
</style>