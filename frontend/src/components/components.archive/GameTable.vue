<template>
  <section>
    <article v-for="game in this.$store.state.games" v-bind:key="game.id">
      <table>
        <thead>
          <tr>
            <th>gameId</th>
            <th>gameOrganizer</th>
            <th>gameWinner</th>
            <th>startTimestamp</th>
            <th>endTimestamp</th>
            <th>gameStatus</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{game.gameId}}</td>
            <td>{{game.gameOrganizer}}</td>
            <td>{{game.gameWinner}}</td>
            <td>{{game.startTimestamp}}</td>
            <td>{{game.endTimestamp}}</td>
            <td>{{game.gameStatus}}</td>
          </tr>
        </tbody>
      </table>
    </article>
  </section>
</template>

<script>
import requestGames from "@/services/ServiceGames";

export default {
  name: "game-list",
  methods: {
    getGames() {
      requestGames.list().then((response) => {
        this.$store.commit("SET_GAMES", response.data);
      });
    },
  },
  created() {
    this.getGames();
  },
};
</script>

<style scoped>
table {
  margin-bottom: 1em; 
}

table tr {
  text-align: left;
}

th,
td {
  padding: 0 1em 0 0;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>


