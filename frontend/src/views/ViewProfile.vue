<template>
  <main class="main">

    <div class="h-wrap-2">
      <h2>Welcome to the Virtual Stock Market, {{ capitalizedUsername }}!</h2>
    </div>

    <div v-if="this.$store.state.pendingPortfolios.length > 0" class="h-wrap-1">
      <h2>Your Pending Game Invitations</h2>

      <portfolio-pending-invitation-list/>
    </div>


    <div class="h-wrap-1" v-show="this.$store.getters.activeGames.length >0">
      <h2>Your Active Games</h2>
    </div>
    <game-list v-show="this.$store.getters.activeGames.length >0" />

    <div class="h-wrap-1" v-show="this.$store.getters.archivedGames.length > 0">
      <h2>Your Archived Games</h2>
    </div>
      <game-archived-list v-show="this.$store.getters.archivedGames.length > 0" /> 
  </main>
</template>


<script>
import GameList from "@/components/games/GameList.vue";
import GameArchivedList from "@/components/games/GameArchivedList.vue";
import PortfolioPendingInvitationList from "../components/portfolios/PortfolioPendingInvitationList.vue";
import ServicePortfolios from "../services/ServicePortfolios"
import ServiceGames from "@/services/ServiceGames";
import ServiceUsers from "@/services/ServiceUsers";
import ServiceStocks from "@/services/ServiceStocks";

export default {
  name: "profile",
  data () {
    return {
      placeholderData: ""
    }
  },
  components: {
    GameList,
    PortfolioPendingInvitationList,
    GameArchivedList,
  },
  computed: {
    capitalizedUsername() {
      let username = this.$store.state.user.username;
      return username.trim().replace(/^\w/, (c) => c.toUpperCase());
    }
  },
  methods: {
    getStocks() {
      ServiceStocks.list().then(response => {
        this.$store.commit("SET_STOCKS", response.data);
      })
    },

    getPendingPortfoliosForCurrentUser() {
      ServicePortfolios
          .getPendingPortfoliosByUserId(this.$store.state.user.id)
          .then((response) => {
            this.$store.commit("SET_PENDING_PORTFOLIOS", response.data)
          });
    },

    getGamesForCurrentUser() {
      ServiceGames
          .getGamesByUserId(this.$store.state.user.id)
          .then((response) => {
            this.$store.commit("SET_GAMES", response.data);
            // this.setExpiredGamesToArchived();
          });
    },

    // setExpiredGamesToArchived() {
    //   this.$store.getters.expiredGames.forEach(item => {
    //     item.gameStatus = "ARCHIVED";
    //     ServiceGames.update(item).then(response => this.placeholderData = response.data);
    //   });
    // },

    // setExpiredGamesToArchived() {
    //   this.$store.state.games.forEach(item => {
    //     if (Date.parse(item.endTimestamp) < Date()) {
    //       item.gameStatus = "ARCHIVED";
    //       ServiceGames.update(item).then(response => this.placeholderData = response.data);
    //     }
    //   });
    // },
    //
    // setArchivedGameWinners() {
    //   this.$store.getters.archivedGames.forEach(item => {
    //     ServicePortfolios
    //         .getPortfoliosByGameId(item.gameId)
    //         .then(response => {
    //           this.$store.commit("SET_GAME_PORTFOLIOS", response.data);
    //           item.gameWinner = this.$store.state.gamePortfolios[0].userId;
    //           ServiceGames.update(item).then(response => this.placeholderData = response.data);
    //         });
    //   });
    // },

    getAllUsers() {
      ServiceUsers.list().then((response) => {
        this.$store.commit("SET_USERS", response.data);
      });
    }
  },
  created() {
    this.getStocks();
    this.getPendingPortfoliosForCurrentUser();
    this.getGamesForCurrentUser();
    this.getAllUsers();
  }
};
</script>

<style scope>

</style>
