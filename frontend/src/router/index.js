import Vue from 'vue'
import Router from 'vue-router'
import store from '../store/index'
import ViewLogin from '../views/ViewLogin.vue'
import ViewLogout from '../views/ViewLogout.vue'
import ViewRegister from '../views/ViewRegister.vue'

import ViewTest from '../views/ViewTest.vue'

import ViewAllGames from "@/views/ViewAllGames";
import ViewAllUsers from "@/views/ViewAllUsers";
import ViewAllStocks from '../views/ViewAllStocks.vue'
import ViewAllPortfolios from '../views/ViewAllPortfolios.vue'
import ViewAllTransactions from '../views/ViewAllTransactions.vue'

import ViewStocks from '../views/ViewStocks.vue'

import ViewProfile from '../views/ViewProfile.vue'
import ViewGame from "@/views/ViewGame";

import ViewNewTransaction from "@/views/ViewNewTransaction";
import ViewNewGame from "@/views/ViewNewGame";

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */


// TODO: DOUBLE CHECK ROUTES BELOW AND MAKE SURE THEY MATCH WHAT WE NEED FOR GAMEPLAY.

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'profile',
      component: ViewProfile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: ViewLogin,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: ViewLogout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: ViewRegister,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/test",
      name: "test",
      component: ViewTest,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/stocks',
      name: 'ViewStocks',
      component: ViewStocks,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/game/:gameId/portfolio/:portfolioId',
      name: 'ViewGame',
      component: ViewGame,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/new-game',
      name: 'ViewNewGame',
      component: ViewNewGame,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/game/:gameId/portfolio/:portfolioId/new-transaction',
      name: 'ViewNewTransaction',
      component: ViewNewTransaction,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/allgames',
      name: 'ViewAllGames',
      component: ViewAllGames,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/allstocks',
      name: 'ViewAllStocks',
      component: ViewAllStocks,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/allportfolios',
      name: 'ViewAllPortfolios',
      component: ViewAllPortfolios,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/alltransactions',
      name: 'ViewAllTransactions',
      component: ViewAllTransactions,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/allusers',
      name: 'ViewAllUsers',
      component: ViewAllUsers,
      meta: {
        requiresAuth: true
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
