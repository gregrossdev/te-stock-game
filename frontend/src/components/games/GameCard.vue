<template>
  <router-link
    v-bind:to="{ name: 'ViewGame', params: { gameId: this.gameId } }"
  >
    <article class="game card">
      <h2>Game #{{ this.gameId }}</h2>

<!--      TODO: add Game name.-->
<!--      TODO: make randomly generated image stay put with the same game, to help users remember/distinguish games.-->

      <img :src="stonksImg(url)" alt="stocks" />
    </article>
  </router-link>
</template>

<script>
export default {
  name: "game-card",
  data() {
    return {
      url: "https://source.unsplash.com/300x150/?"
    }
  },
  props: {
    gameId: Number,
  },
  methods: {
    viewGame(gameId) {
      this.$router.push(`/game/${gameId}`);
    },
    stonksImg(url) {
      let num = Math.floor(Math.random() * 25);
      let query;
      switch (num) {
        case 0:
          query = "coins";
          break;
        case 1:
          query = "stocks";
          break;
        case 2:
          query = "money";
          break;
        case 3:
          query = "cash";
          break;
        case 4:
          query = "win";
          break;
        case 5:
          query = "lottery";
          break;
        case 6:
          query = "finance";
          break;
        case 7:
          query = "currency";
          break;
        case 8:
          query = "crypto";
          break;
        case 9:
          query = "exchange";
          break;
        case 10:
          query = "slots";
          break;
        case 11:
          query = "charts";
          break;
        case 12:
          query = "coins";
          break;
        case 13:
          query = "rich";
          break;
        case 14:
          query = "bank";
          break;
        case 15:
          query = "wallet";
          break;
        case 16:
          query = "jackpot";
          break;
        case 17:
          query = "credit";
          break;
        case 18:
          query = "funds";
          break;
        case 19:
          query = "market";
          break;
        case 20:
          query = "cryptocurrency";
          break;
        case 21:
          query = "graph";
          break;
        case 22:
          query = "dollars";
          break;
        case 23:
          query = "dollar";
          break;
        case 24:
          query = "chart";
          break;
        default:
          query = "deco";
      }
      return url + query 
    }
  },
};
</script>

<style scoped>
article img {
  width: 100%;
  border: 1px solid var(--clr-grey-30);
  border-radius: 0.25em;
}

h2 {
  color: var(--clr-grey-90);
  padding: 0.25em 0;
  margin: 0 0 0 0.15em;
}
</style>


