<template>
  <div>
      <div class="card"
        v-for="pendingPortfolio in this.$store.state.pendingPortfolios"
        v-bind:key="pendingPortfolio"
        v-bind:pendingGame="pendingPortfolio">
        <p>GameId has a pending invitation</p>
        <button @click="updateToActive">ACCEPT</button>
        <button @click="deleteTheGame">REJECT</button>
        <p></p>


      </div>
      
  </div>
</template>

<script>
import requestPortfolios from "@/services/ServicePortfolios";
export default {
    
    name: "portfolio-pending-card",
    props: {
      portfolio: Object
    },

    data(){
        return{
              
        }
    },

    methods: {
      updateToActive(){
        console.log('THIS SHOULD BE CHANGING BACK END');
        
    //     requestPortfolios.update(this.pendingPortfolio.portfolioId).then((response) => {
    //       if(response && response.status === 201) {
    //         this.$router.push('/');
    //       }
    //   });
    },
    deleteTheGame(){
      console.log('delete plz');
    //   requestPortfolios.delete(this.pendingPortfolio.portfolioId);
    }

},
    created(){
      requestPortfolios.getPendingPortfoliosByUserId(this.$store.state.user.id).then((response) => {
        this.$store.commit("SET_PENDING_PORTFOLIOS", response.data);
      });
    },    

}
</script>

<style>
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