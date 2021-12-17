<template>
  <article class="card">
    <h2>{{ usernameCapitalized }}</h2>
    <h3>Total Value: {{ formatPrice(this.portfolio.portfolioTotalValue) }}</h3>
    <h3>Available Cash: {{ formatPrice(this.portfolio.portfolioCash) }}</h3>
    <p>Portfolio #{{ this.portfolio.portfolioId }}</p>
  </article>
</template>

<script>

export default {
  name: "portfolio-card",
  props: {
    portfolio: Object,
  },
  methods: {
    formatPrice(value) {
      if (typeof value !== "number") {
        return value;
      }
      const formatter = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
        minimumFractionDigits: 0,
      });
      return formatter.format(value);
    }
  },
  computed: {
    usernameCapitalized() {
      const userToLookUp = this.$store.state.users.filter(item => item.id === this.portfolio.userId)[0];
      const usernameToLookUp = userToLookUp.username;
      return usernameToLookUp.trim().replace(/^\w/, (c) => c.toUpperCase());
    }
  }
};
</script>

<style scoped>
article {
  padding: 1em;
  border-radius: 0.5em;
  display: flex;
  flex-direction: column;
}

article h2,
article h3 {
  color: var(--clr-grey-90);
}

article h2 {
  color: var(--clr-grey-80);
  font-size: 1.4rem;
  border-bottom: 1px solid var(--clr-green);
}

article h3 {
  font-size: 1.25rem;
}

article p {
  margin-top: 1rem;
  margin-bottom: 0;
  margin-left: auto;
  padding: 0; 
  font-size: 1.25rem;
  font-weight: 800;
  color: var(--clr-grey-10);
}
</style>