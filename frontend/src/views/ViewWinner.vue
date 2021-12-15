<template>
  <main>
    <div class="h-wrap-1">
      <h2>Game Winner</h2>
    </div>
    <div class="h-wrap-2">
      <h1>Ellen</h1>
    </div>
    <section class="finale">
      <figure>
        <img :src="stonksImg(url)" alt="" />
        <figcaption>Game Prize</figcaption>
      </figure>
    </section>
  </main>
</template>

<script>
export default {
  name: "ViewWinner",
  data() {
    return {
      url: 'https://'
    }
  },
  methods: {
    stonksImg(url) {
      let num = Math.floor(Math.random() * 9);
      let query;
      switch (num) {
        case 0:
          query = "media1.giphy.com/media/Wrv5v6egIh7Ww/giphy.gif?cid=790b7611b062b05090bb53ebe898e7543b304c5515ad4b2d&rid=giphy.gif&ct=g";
          break;
        case 1:
          query = "media1.giphy.com/media/jbESG3ZIPIvHa/giphy.gif?cid=790b76118866fd4cb490fcef7ce5268a860a9bf78e741e7f&rid=giphy.gif&ct=g";
          break;
        case 2:
          query = "media1.giphy.com/media/nbJUuYFI6s0w0/giphy.gif?cid=ecf05e47p3sryyhi6igep9hyksy47hk1ujon922cksxwyj3e&rid=giphy.gif&ct=g";
          break;
        case 3:
          query = "media2.giphy.com/media/w5f56AhubQo8w/giphy.gif?cid=790b7611b2b13b13a10883e5d775c6773413420268204850&rid=giphy.gif&ct=g";
          break;
        case 4:
          query = "media0.giphy.com/media/ubVCX5sm4qri8/giphy.gif?cid=790b7611180b9c4a657fba782ee0628a4826a79b048d0b6e&rid=giphy.gif&ct=g";
          break;
        case 5:
          query = "media2.giphy.com/media/k8kG3Plo87Aqs/giphy.gif?cid=790b7611b1493886e01b75c3d1a84737e67bbe10a4e689ff&rid=giphy.gif&ct=g";
          break;
        default:
          query = "media3.giphy.com/media/g9582DNuQppxC/giphy.gif?cid=790b7611592a5279c33e3e6a6053044652b07f77ae10ac21&rid=giphy.gif&ct=g";
      }
      return url + query 
    }
  }
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
  color: var(--clr-yellow);
  font-weight: 900;
}
</style>