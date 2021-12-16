<template>
  <div>
       
        <portfolio-pending-card v-bind:active="this.activePortfolio" />

        
  </div>
</template>

<script>
import requestPortfolios from "@/services/ServicePortfolios";
import PortfolioPendingCard from './PortfolioPendingCard.vue';
export default {
  components: { PortfolioPendingCard }, 
    name: "pending-invitation-list",
    props: {
      portfolio: Object
    },
    data() {
      return {
        activePortfolio: {
            portfolioId: this.pendingPortfolio.portfolioId,
            userId: this.$store.state.user.id,
            gameId: this.pendingPortfolio.gameId,
            portfolioCash: this.pendingPortfolio.portfolioCash,
            portfolioStocksValue: this.pendingPortfolio.portfolioStocksValue,
            portfolioTotalValue: this.pendingPortfolio.portfolioTotalValue,
            portfolioStatus: this.pendingPortfolio.portfolioStatus,
        }
      }
    },
    created(){
      requestPortfolios.getPendingPortfoliosByUserId(this.$store.state.user.id).then((response) => {
        this.$store.commit("SET_PENDING_PORTFOLIOS", response.data);
      });
    },

//     methods: {
//       updateToActive(){
//         console.log('THIS SHOULD BE CHANGING BACK END');
        
//         requestPortfolios.update(this.pendingPortfolio.portfolioId).then((response) => {
//           if(response && response.status === 201) {
//             this.$router.push('/');
//           }
//       });
//     },
//     deleteTheGame(){
//       console.log('delete plz');
//       requestPortfolios.delete(this.pendingPortfolio.portfolioId);
//     }

// }
}
</script>

<style scoped>
.card {
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