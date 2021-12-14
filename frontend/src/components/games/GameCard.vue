<template>
  <div>

  </div>
</template>

<script>
import serviceGames from "@/services/ServiceGames";
import servicePortfolios from "@/services/ServicePortfolios";

export default {
  name: "game-card",
  props: {
    gameId: Number // TODO: MAKE SURE PARENT COMPONENT PASSES A GAMEID TO EACH GAMECARD AS A PROP.
  },
  methods: {},
  created() {
    serviceGames
        .getGameByGameId(this.gameId)
        .then(response => {
          this.$store.commit("SET_ACTIVE_GAME", response.data);
        })
        .catch(error => {
          if (error.response.status === 404) {
            this.$router.push("/"); // TODO: EITHER ADD CUSTOM ERROR MESSAGE ("GAME NOT FOUND") HERE, OR CREATE A CUSTOM 404 PAGE TO REDIRECT TO
          }
        });
    servicePortfolios
        .getPortfolioByUserIdAndGameId(this.$store.state.user.id, this.gameId)
        .then(response => {
          this.$store.commit("SET_ACTIVE_PORTFOLIO", response.data);
        })
        .catch(error => {
          if (error.response.status === 404) {
            this.$router.push("/"); // TODO: EITHER ADD CUSTOM ERROR MESSAGE ("GAME NOT FOUND") HERE, OR CREATE A CUSTOM 404 PAGE TO REDIRECT TO
          }
        });
  }
};
</script>

<style scoped>
section {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
}

article {
  max-width: 320px;
  overflow: hidden;
  border: 1px solid var(--clr-grey-30);
  padding: 0.25em;
  border-radius: 0.25em;
}

article > * {
  overflow-wrap: break-word;
}

table tr {
  text-align: left;
}

table th {
  margin-left: 10em;
}

th,
td {
  padding: 0 15px 0 0;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>


