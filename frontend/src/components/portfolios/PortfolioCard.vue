<template>
  <article>
    <h2>Portfolio #{{ this.portfolio.portfolioId }}</h2>
    <h3>Total Value: ${{ this.portfolio.portfolioTotalValue }}</h3>
    <h3>Available Cash: ${{ this.portfolio.portfolioCash }}</h3>
  </article>
</template>

<script>
export default {
  name: "portfolio-card",
  props: {
    portfolio: Object,
  },
  methods: {},
  created() {},
};
</script>

<style>
article {
  width: 300px;
  padding: 2em;
  background-color: var(--clr-pri);
  border: 2px solid var(--clr-green);
  border-radius: 0.25em;
}

article h2,
article h3 {
  color: var(--clr-grey-90);
}

article h2 {
  color: var(--clr-grey-10);
  font-size: 1.4rem;
}

article h3 {
  font-size: 1.25rem;
}
</style>