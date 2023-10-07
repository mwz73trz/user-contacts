import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import EmployeeInformation from '../views/EmployeeInformation'
import OfficeDetails from '../views/OfficeDetails'
import DoctorDetails from '../views/DoctorDetails'
import PatientInformation from '../views/PatientInformation'
import Schedule from '../components/Schedule'
import EmployeeReviewList from "../views/EmployeeReviewList"
// import NewPatientInfo from '../views/NewPatientInfo'
import AddSchedule from '../views/AddSchedule'
import EditSchedule from '../views/EditSchedule'
import RepliesList from '../views/RepliesList'
import ReplyForm from '../views/ReplyForm'

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
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/profile',
      name: 'employee',
      component: EmployeeInformation,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/offices/:officeId',
      name: 'office-details',
      component: OfficeDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/employees/:employeeId',
      name: 'doctor-details',
      component: DoctorDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/patients/user',
      name: 'patient',
      component: PatientInformation,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/schedules/:employeeId',
      name: 'schedule',
      component: Schedule,
      meta: {
        requiresAuth: true
      }
    },
      {
        path: '/schedules',
        name: 'AddSchedule',
        component: AddSchedule,
        meta: {
          requiresAuth: true
        }
      },
      {
        path: '/schedules/:employeeId',
        name: 'EditSchedule',
        component: EditSchedule,
        meta: {
          requiresAuth: true
        }
      },
    {
      path: '/reviews/:officeId',
      name: 'EmployeeReview',
      component: EmployeeReviewList,
      meta: {
        requiresAuth: true
      } 
    },
    {
      path: '/reviews/:reviewId/replies',
      name: 'RepliesList',
      component: RepliesList,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: 'reviews/:reviewId/replies',
      name: 'AddReplyForm',
      component: ReplyForm,
      meta: {
        requiresAuth: true
      }
    }
    //{
    ///   path: '/patient/form',
    //   name: 'patient-form',
    //   component: NewPatientInfo,
    //   meta: {
    //     requiresAuth: true
    //   }
    // }

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
