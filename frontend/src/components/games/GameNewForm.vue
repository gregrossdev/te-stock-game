<template>
  <form v-on:submit.prevent="saveGame" class="form-onboard">
    <label for="endTimestamp"> End Date</label>
    <input type="date" v-model="game.endTimestamp" class="date"/>
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
          if (error.response) {
            this.errorMsg =
              "Error submitting new game. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsg =
              "Error submitting new game. Server could not be reached.";
          } else {
            this.errorMsg =
              "Error submitting new game. Request could not be created.";
          }
        });
        this.$router.push("/")
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
  width: 50%;
  margin: 1.5rem auto;
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

.date {
  padding: 1.25em 0.25em;
}

</style>