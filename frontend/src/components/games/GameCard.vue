<template>
  <router-link
    v-bind:to="{ name: 'ViewGame', params: { gameId: this.game.gameId } }"
    class="game card"
  >
    <p>Game ID: {{ this.game.gameId }}</p>
    <h2>{{ this.game.gameName }}</h2>
    <img :src="stonksImg(url, this.game.gameId)" alt="stocks" />
  </router-link>
</template>

<script>
export default {
  name: "game-card",
  data() {
    return {
      url: "",
    };
  },
  props: {
    game: Object,
  },
  methods: {
    viewGame(gameId) {
      this.$router.push(`/game/${gameId}`);
    },
    stonksImg(url, num) {
      if (num % 1 === 0) {
        url =
          "https://images.unsplash.com/photo-1593672715438-d88a70629abe?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bW9uZXl8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60";
      }
      if (num % 2 === 0) {
        url =
          "https://images.unsplash.com/photo-1579227114347-15d08fc37cae?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80";
      }
      if (num % 3 === 0) {
        url =
          "https://images.unsplash.com/photo-1580519542036-c47de6196ba5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1171&q=80";
      }
      if (num % 4 === 0) {
        url =
          "https://images.unsplash.com/photo-1603777953662-5310c93eeb1c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80";
      }
      if (num % 5 === 0) {
        url =
          "https://images.unsplash.com/photo-1575472782454-230f8dd14bd7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80";
      }
      if (num % 6 === 0) {
        url =
          "https://images.unsplash.com/photo-1579227114400-4752dc0a7506?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80";
      }
      if (num % 7 === 0) {
        url =
          "https://images.unsplash.com/photo-1602272385319-1dbf089994cf?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1242&q=80";
      }
      if (num % 8 === 0) {
        url =
          "https://images.unsplash.com/photo-1621932953986-15fcf084da0f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1172&q=80";
      }
      if (num % 9 === 0) {
        url =
          "https://images.unsplash.com/photo-1593713150481-de2e67b4bbf8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80";
      }

      return url;
    },
  },
};
</script>

<style scoped>
a {
  display: flex;
  flex-direction: column;
  align-self: stretch;
  justify-self: stretch;
}

a img {
  display: block;
  object-fit: cover;
  width: 100%;
  height: 150px;
  border: 1px solid var(--clr-grey-30);
  border-radius: 0.25em;
  margin-top: auto;
  padding-bottom: 0.25em;
}

a p {
  margin: 0;
  margin-top: 0.25em;
  color: var(--clr-grey-90);
}

h2 {
  color: var(--clr-grey-90);
  margin-bottom: 0.15em;
}
</style>


