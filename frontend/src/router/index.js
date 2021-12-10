import Vue from 'vue'
import Router from 'vue-router'
import store from '../store/index'
import ViewProfile from '../views/ViewProfile.vue'
import ViewLogin from '../views/ViewLogin.vue'
import ViewLogout from '../views/ViewLogout.vue'
import ViewRegister from '../views/ViewRegister.vue'
import ViewTest from '../views/ViewTest.vue'
import ViewStocks from '../views/ViewStocks.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

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
      name: 'stocks',
      component: ViewStocks,
      meta: {
        requiresAuth: true
      }
    }
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
