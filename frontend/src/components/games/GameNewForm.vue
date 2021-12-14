<template>
  <form v-on:submit.prevent="saveGame">
    <label for="startTimestamp"> Start Date</label>
    <input type="date" v-model="game.startTimestamp" />
    <label for="endTimestamp"> End Date</label>
    <input type="date" v-model="game.endTimestamp" />
    <button>Save</button>
  </form>
</template>

<script>
import requestGames from "@/services/ServiceGames";

export default {
  name: "game-new-form",
  data() {
    return {
      game: {
        gameOrganizer: this.$store.state.user.id,
        startTimestamp: "",
        endTimestamp: "",
        gameStatus: "ACTIVE",
      },
    };
  },
  methods: {
    saveGame() {
      requestGames
      .create(this.game)
      .then(response => {
        if(response && response.status == 201) {
          this.game();
        }
      })
      .catch(error => {
        // log the error
        if (error.response) {
          this.errorMsg = "Error submitting new board. Response received was '" + error.response.statusText + "'.";

        } else if (error.request) {
          this.errorMsg = "Error submitting new board. Server could not be reached.";

        } else {
          this.errorMsg = "Error submitting new board. Request could not be created.";
        }
      });
    },
    resetGame() {
      this.game = {
        gameId: "",
        gameOrganizer: this.$store.state.user.id,
        gameWinner: "",
        startTimestamp: "",
        endTimestamp: "",
        gameStatus: ""
      };
    }
  }
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