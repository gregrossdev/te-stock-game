<template>

<!--  TODO: Make sure that this form creates new PORTFOLIOS for each invited user, with a portfolioStatus of "PENDING"-->
<!--  TODO: Decide exactly what should happen when the SAVE button gets clicked.-->

  <form v-on:submit.prevent="saveGame" class="form-onboard">

    <label for="gameName">Game Name</label>
    <input type="text" name="gameName" v-model="game.gameName" class="game-name" />
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
import requestPortfolios from "@/services/ServicePortfolios";

export default {
  name: "game-new-form",
  data() {
    return {
      game: {
        gameName: "",
        gameOrganizer: this.$store.state.user.id,
        endTimestamp: ""
      },
      createdGame: {},
      users: [],
      invitedUsers: [],
      invitedUserPortfolio: {
        userId: 0,
        gameId: 0,
        portfolioStatus: 'PENDING'
      },
      invitedUsersPortfolios: []
    };
  },
  methods: {

    // TODO: Chain this "saveGame()" method to an "inviteUsers()" method that creates a new portfolio, with a status of PENDING, for each user in invitedUsers.

    saveGame() {
      requestGames
        .create(this.game)
        .then((response) => {
          if (response && response.status === 201) {
            this.createdGame = response.data;
            this.updateGames();
            this.inviteUsers();
            this.$router.push('/');
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
    },
    inviteUsers() {
      // TODO FIGURE OUT WHAT IS GOING WRONG HERE. USERID IS NOT GETTING SUCCESSFULLY ASSIGNED IN THE PORTFOLIO OBJECT THAT'S BEING CREATED.
      this.invitedUsers.forEach(value => {
        this.invitedUserPortfolio.userId = value;
        this.invitedUserPortfolio.gameId = this.createdGame.gameId;
        requestPortfolios.create(this.invitedUserPortfolio).then(response => {
          if (response && response.status === 201) {
            this.invitedUsersPortfolios.push(this.invitedUserPortfolio);
          }
        });
      });
      this.invitedUsers = [];
      this.invitedUsersPortfolios = [];
    },
    updateGames() {
      requestGames.list().then(response => {
        this.$store.commit("SET_GAMES", response.data);
      })
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
  height: 100%;
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