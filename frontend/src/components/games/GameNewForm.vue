<template>
  <form v-on:submit.prevent="saveGame">
    <input
      type="text"
      placeholder="gameOrganizer"
      v-model="game.gameOrganizer"
    />
    <input type="text" placeholder="gameWinner" v-model="game.gameWinner" />
    <input
      type="text"
      placeholder="startTimestamp"
      v-model="game.startTimestamp"
    />
    <input type="text" placeholder="endTimestamp" v-model="game.endTimestamp" />
    <input type="text" placeholder="gameStatus" v-model="game.gameStatus" />
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
  flex-basis: 100%;
  margin: 0 auto;
  border: 4px solid var(--clr-pri-90);
  padding: 2em;  
}
</style>