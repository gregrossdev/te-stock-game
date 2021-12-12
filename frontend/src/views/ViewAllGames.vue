<template>
  <div>
    <h2>All Games</h2>
    <game-table />
  </div>
</template>

<script>
import GameTable from "@/components/games/GameTable";

export default {
  name: "allgames",
  components: { GameTable },
};
</script>

<style>
</style>