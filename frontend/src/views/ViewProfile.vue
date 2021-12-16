<template>
  <main class="main">
    <div class="h-wrap-2">
      <h2>
        Welcome to the Virtual Stock Market,
        {{ capitalizedUsername }}!
      </h2>
    </div>

    <!-- <portfolio-pending-invitation-list 
    v-for="pendingGame in this.$store.state.pendingPortfolios"
    v-bind:key="pendingGame"
    v-bind:pendingGame="pendingGame"/> -->

  <portfolio-pending-invitation-list/>

    <section class="games-list">
      <game-list />
    </section>
  </main>
</template>


<script>
import GameList from "@/components/games/GameList.vue";
import PortfolioPendingInvitationList from "../components/portfolios/PortfolioPendingInvitationList.vue";
// import PortfolioPendingCard from "../components/portfolios/PortfolioPendingCard.vue";
// import requestPendingPortfolios from "../services/ServicePortfolios"

export default {
  name: "profile",
  components: {
    GameList,
    PortfolioPendingInvitationList,
  },
  // getPendingGames() {
  //   requestPendingPortfolios.getPendingPortfoliosByUserId().then((response) => {
  //     this.$store.commit("SET_PENDING_PORTFOLIOS", response.data)
  //   });
  // },
  computed: {
    capitalizedUsername() {
      let username = this.$store.state.user.username;
      return username.trim().replace(/^\w/, (c) => c.toUpperCase());
    },
  }
};
</script>

<style scope>

</style>
