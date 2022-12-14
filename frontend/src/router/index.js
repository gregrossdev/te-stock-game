import Vue from 'vue'
import Router from 'vue-router'
import store from '../store/index'
import ViewLogin from '../views/ViewLogin.vue'
import ViewLogout from '../views/ViewLogout.vue'
import ViewRegister from '../views/ViewRegister.vue'

import ViewProfile from '../views/ViewProfile.vue'
import ViewNewGame from "@/views/ViewNewGame";
import ViewGame from "@/views/ViewGame";
import ViewWinner from "@/views/ViewWinner";
import ViewNewTransaction from "@/views/ViewNewTransaction";

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
      path: '/',
      name: 'profile',
      component: ViewProfile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/new',
      name: 'ViewNewGame',
      component: ViewNewGame,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/new-transaction',
      name: 'ViewNewTransaction',
      component: ViewNewTransaction,
      props: true,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/game/win',
      name: 'ViewWinner',
      component: ViewWinner,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: '/game/:gameId/',
      name: 'ViewGame',
      component: ViewGame,
      meta: {
        requiresAuth: true
      }
    }
  ],
  scrollBehavior() {
    return { x: 0, y: 0 };
  }
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
