<template>
  <form v-on:submit.prevent="saveGame">
    <label for="endTimestamp"> End Date</label>
    <input type="date" v-model="game.endTimestamp" />
    <div class="checkbox" v-for="user in users" :key="user.id">
      <input type="checkbox" name="userId" v-bind:value="user.id" v-model="invitedUsers"/>
      <label for="userId">{{user.username}}</label>
    </div>

    <button>Save</button>

  </form>
</template>

<script>
import requestGames from "@/services/ServiceGames";
import requestUsers from "@/services/ServiceUsers";

export default {
  name: "game-new-form",
  data() {
    return {
      game: {
        gameOrganizer: this.$store.state.user.id,
        endTimestamp: ""
      },
      users: [],
      invitedUsers: []
    };
  },
  methods: {
    saveGame() {
      requestGames
        .create(this.game)
        .then((response) => {
          if (response && response.status == 201) {
            this.game();
          }
        })
        .catch((error) => {
          // log the error
          if (error.response) {
            this.errorMsg =
              "Error submitting new board. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsg =
              "Error submitting new board. Server could not be reached.";
          } else {
            this.errorMsg =
              "Error submitting new board. Request could not be created.";
          }
        });
        this.$router.push("/gameId")
    },
    resetGame() {
      this.game = {
        gameId: "",
        gameOrganizer: this.$store.state.user.id,
        gameWinner: "",
        startTimestamp: "",
        endTimestamp: "",
        gameStatus: "",
      };
    },
  },
  created() {
      requestUsers.list().then((response) => {
        this.users = response.data;
      });
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

.checkbox {
  display: flex;
  align-items: baseline;
}

.checkbox input, .checkbox label {
  margin-left: 0.5em;
}

.checkbox[type="checkbox" i] {
   padding: 10em; 
}


</style>