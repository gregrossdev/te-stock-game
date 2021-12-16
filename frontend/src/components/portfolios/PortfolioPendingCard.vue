<template>
  <div class="portfolio-pending-card">
      <h3>Invitation to: {{ this.invitationGame.gameName }}</h3>
      <p>You were invited by {{ this.usernameWhoInvited }}</p>
      <button @click="acceptInvitation">ACCEPT</button>
      <button @click="rejectInvitation">REJECT</button>
  </div>
</template>

<script>

import ServicePortfolios from "@/services/ServicePortfolios";

export default {

  name: "portfolio-pending-card",
  props: {
    portfolio: Object
  },
  methods: {
    acceptInvitation() {
      // eslint-disable-next-line vue/no-mutating-props
      this.portfolio.portfolioStatus = "ACTIVE";
      ServicePortfolios.update(this.portfolio).then(response => {
        if (response.status === 200) {
          this.$router.push({ name: 'ViewGame', params: { gameId: this.invitationGame.gameId }});
        }
      });
    },
    rejectInvitation() {
      // eslint-disable-next-line vue/no-mutating-props
      this.portfolio.portfolioStatus = "ARCHIVED";
      ServicePortfolios.update(this.portfolio).then(response => {
        if (response.status === 200) {
          this.getPendingPortfoliosForCurrentUser();
          this.$router.push('/');
        }
      });
    },
    getPendingPortfoliosForCurrentUser() {
      ServicePortfolios
          .getPendingPortfoliosByUserId(this.$store.state.user.id)
          .then((response) => {
            this.$store.commit("SET_PENDING_PORTFOLIOS", response.data)
          });
    },
  },
  computed: {
   invitationGame() {
     const invitationGameArray = this.$store.state.games.filter(game => game.gameId === this.portfolio.gameId);
     return invitationGameArray[0];
   },
    usernameWhoInvited() {
     const userInQuestion = this.$store.state.users.filter(user => user.id === this.invitationGame.gameOrganizer);
     return userInQuestion[0].username.trim().replace(/^\w/, (c) => c.toUpperCase());
   }
  }
}
</script>

<style>

.portfolio-pending-card {
  color: white;
  border: 1px solid var(--clr-grey-70);
  margin-bottom: 1em;
  border-radius: 0.25rem;
  padding: 0.5em;
  width: 10rem;
  display: flex;
  flex-direction: column;
}

</style>