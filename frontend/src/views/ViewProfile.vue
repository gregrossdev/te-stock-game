<!--TODO: STYLE THIS TEMPLATE SECTION WITH CONSISTENT CSS AND FORMATTING.-->

<template>
  <main class="main">

    <div class="h-wrap-2">
      <h2>Welcome to the Virtual Stock Market, {{ capitalizedUsername }}!</h2>
    </div>

    <div class="h-wrap-2" v-if="this.$store.state.pendingPortfolios.length > 0">
      <h2>Your Pending Game Invitations</h2>

      <!--
      THE CREATED() LIFECYCLE HOOK WILL SET PENDING PORTFOLIOS IN THE STORE WHEN THIS VIEW PROFILE COMPONENT IS RENDERED.
      THE PORTFOLIO PENDING INVITATION LIST WILL THEN DRAW ITS DATA DIRECTLY FROM THE STORE.
      -->
      <portfolio-pending-invitation-list/>

    </div>

    <div class="h-wrap-2">
      <h2>Your Active Games</h2>

      <!--
      THE CREATED() LIFECYCLE HOOK WILL SET GAMES (FOR THE CURRENT USER) IN THE STORE WHEN THIS VIEW PROFILE COMPONENT IS RENDERED.
      THE GAME LIST WILL THEN DRAW ITS DATA DIRECTLY FROM THE STORE.
      -->
      <game-list/>

    </div>

  </main>
</template>


<script>
import GameList from "@/components/games/GameList.vue";
import PortfolioPendingInvitationList from "../components/portfolios/PortfolioPendingInvitationList.vue";
import ServicePortfolios from "../services/ServicePortfolios"
import ServiceGames from "@/services/ServiceGames";
import ServiceUsers from "@/services/ServiceUsers";

export default {
  name: "profile",
  components: {
    GameList,
    PortfolioPendingInvitationList,
  },
  computed: {
    capitalizedUsername() {
      let username = this.$store.state.user.username;
      return username.trim().replace(/^\w/, (c) => c.toUpperCase());
    },
  },
  methods: {
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
          });
    },
    getAllUsers() {
      ServiceUsers.list().then((response) => {
        this.$store.commit("SET_USERS", response.data);
      });
    }
  },
  created() {
    this.getPendingPortfoliosForCurrentUser();
    this.getGamesForCurrentUser();
    this.getAllUsers();
  }
};
</script>

<style scope>

</style>
