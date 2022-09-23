<template>
  <main>
    <div class="h-wrap-1">
      <h2>Game Winners</h2>
      <section class="finale">
        <figure>
          <img :src="stonksImg(url)" alt="" />
          <figcaption>Game Prize</figcaption>
        </figure>
      </section>
    </div>
    <div class="h-wrap-2">
      <a href="http://localhost:8081/game/win">
        <h1>Game 1:</h1>
        <h2>Denny</h2>
      </a>
      <a href="http://localhost:8081/game/win">
        <h1>Game 2:</h1>
        <h2>Alex</h2>
      </a>
      <a href="http://localhost:8081/game/win">
        <h1>Game 3:</h1>
        <h2>Josh</h2>
      </a>
      <a href="http://localhost:8081/game/win">
        <h1>Game 4:</h1>
        <h2>Aubrey &amp; Josh</h2>
      </a>
      <a href="http://localhost:8081/game/win">
        <h1>Game 5:</h1>
        <h2>Everyone Wins</h2>
      </a>
    </div>
  </main>
</template>

<script>
export default {
  name: "ViewWinner",
  data() {
    return {
      url: "https://",
    };
  },
  methods: {
    stonksImg(url) {
      let num = Math.floor(Math.random() * 9);
      let query;
      switch (num) {
        case 0:
          query =
            "media1.giphy.com/media/bjU3D9h1896WuJRTuj/giphy.gif?cid=790b761139b7b41e44be707af39b75c2d82a825d2886f6f2&rid=giphy.gif&ct=g";
          break;
        case 1:
          query =
            "media1.giphy.com/media/jbESG3ZIPIvHa/giphy.gif?cid=790b76118866fd4cb490fcef7ce5268a860a9bf78e741e7f&rid=giphy.gif&ct=g";
          break;
        case 2:
          query =
            "media1.giphy.com/media/nbJUuYFI6s0w0/giphy.gif?cid=ecf05e47p3sryyhi6igep9hyksy47hk1ujon922cksxwyj3e&rid=giphy.gif&ct=g";
          break;
        case 3:
          query =
            "media1.giphy.com/media/DhstvI3zZ598Nb1rFf/giphy.gif?cid=ecf05e47auyouokiiwsg7iyp1fb71xyr911edop9nq8wfv7m&rid=giphy.gif&ct=g";
          break;
        case 4:
          query =
            "media2.giphy.com/media/f9RHPRFxJhKTF0MQth/giphy.gif?cid=ecf05e470qk364kdqqvm2mdc0pio12dnn5les5nssbiwmciy&rid=giphy.gif&ct=g";
          break;
        case 5:
          query =
            "media2.giphy.com/media/k8kG3Plo87Aqs/giphy.gif?cid=790b7611b1493886e01b75c3d1a84737e67bbe10a4e689ff&rid=giphy.gif&ct=g";
          break;
        default:
          query =
            "media3.giphy.com/media/g9582DNuQppxC/giphy.gif?cid=790b7611592a5279c33e3e6a6053044652b07f77ae10ac21&rid=giphy.gif&ct=g";
      }
      return url + query;
    },
  },
};
</script>

<style scoped>
h2 {
  padding: 0.75em 0 0.75em;
}

.finale {
  border: 1px solid var(--clr-green);
  margin: 0 auto;
}

figure {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
}

figcaption {
  text-align: center;
  font-size: 1.5rem;
  padding: 1em;
  background-color: var(--clr-green);
  border: 1px solid var(--clr-pri);
  color: var(--clr-pri);
  font-weight: 900;
}

a {
  color: inherit;
}
</style>