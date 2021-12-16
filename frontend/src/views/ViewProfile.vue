<template>
  <main class="main">

    <div class="h-wrap-2">
      <h2>Welcome to the Virtual Stock Market, {{ capitalizedUsername }}!</h2>
    </div>

    <div class="h-wrap-1" v-if="this.$store.state.pendingPortfolios.length > 0">
      <h2>Your Pending Game Invitations</h2>
      <!--
      THE CREATED() LIFECYCLE HOOK WILL SET PENDING PORTFOLIOS IN THE STORE WHEN THIS VIEW PROFILE COMPONENT IS RENDERED.
      THE PORTFOLIO PENDING INVITATION LIST WILL THEN DRAW ITS DATA DIRECTLY FROM THE STORE.
      -->
      <portfolio-pending-invitation-list/>
<<<<<<< HEAD
      
      <h2>Your Archived Games</h2>
      <game-archived-list/>

=======
>>>>>>> 9f372dc21b50c416f49f7ee7978fd2fc44e7ce32
    </div>


    <div class="h-wrap-1">
      <h2>Your Active Games</h2>
    </div>

      <!--
      THE CREATED() LIFECYCLE HOOK WILL SET GAMES (FOR THE CURRENT USER) IN THE STORE WHEN THIS VIEW PROFILE COMPONENT IS RENDERED.
      THE GAME LIST WILL THEN DRAW ITS DATA DIRECTLY FROM THE STORE.
      -->
      <game-list/>

<<<<<<< HEAD
      <h2>Your Archived Games</h2>

      <!--
      THE CREATED() LIFECYCLE HOOK WILL SET GAMES (FOR THE CURRENT USER) IN THE STORE WHEN THIS VIEW PROFILE COMPONENT IS RENDERED.
      THE GAME LIST WILL THEN DRAW ITS DATA DIRECTLY FROM THE STORE.
      -->
      <game-archived-list/>

    </div>
=======

>>>>>>> 9f372dc21b50c416f49f7ee7978fd2fc44e7ce32

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
          });
    },
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
