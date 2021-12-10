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
      placeholder="startDatetime"
      v-model="game.startDatetime"
    />
    <input type="text" placeholder="endDatetime" v-model="game.endDatetime" />
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