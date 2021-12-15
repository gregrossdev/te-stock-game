<template>
  <main>
    <div class="h-wrap-1">
      <h2>New Game</h2>
    </div>
      
      <game-new-form />
  </main>
</template>

<script>
import GameNewForm from "../components/games/GameNewForm.vue";


export default {
  name: "ViewNewGame",
  components: {
    GameNewForm
  },

}

</script>

<style>



</style>

