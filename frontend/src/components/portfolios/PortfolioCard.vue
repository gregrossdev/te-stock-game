<template>
  <article class="card">
    <!--    TODO: Display username here, in addition to portfolio #.-->

    <h4>Portfolio #{{ this.portfolio.portfolioId }}</h4>

    <h4>findUsersById {{ findUsersById(this.portfolio.userId) }}</h4>

    <h3>Total Value: {{ formatPrice(this.portfolio.portfolioTotalValue) }}</h3>
    <h3>Available Cash: {{ formatPrice(this.portfolio.portfolioCash) }}</h3>
  </article>
</template>

<script>
import requestUsers from "@/services/ServiceUsers";

export default {
  name: "portfolio-card",
  data() {
    return {
      users: [],
    }
  },
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
    },
    findUsersById(userId) {
      let un = '';  
      this.users.filter(user => {
        if(user.id == userId) {
          un = user.username; 
          return un; 
        }
      })
    },
    sortByLeader() {
      return this.$store.state.gamePortfolios.portfolioTotalValue.sort((a, b) => b - a); 
    }
  },
  created() {
    requestUsers.list().then((response) => {
      this.users = response.data;
    });
  },
};
</script>

<style scoped>
article {
  width: 300px;
  padding: 2em;
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
</style>