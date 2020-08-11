import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import SignUp from '@/components/SignUp'
import Index from '@/components/Index'
import Register from '@/components/Register'
import Withdraw from '@/components/Withdraw'
import Diagnose from '@/components/Diagnose'
import MakePresc from '@/components/MakePresc'
import Pay from '@/components/Pay'
import Refund from '@/components/Refund'
import DrugTrans from '@/components/DrugTrans'
import DrugRcv from '@/components/DrugRcv'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/sign_up',
      name: 'SignUp',
      component: SignUp
    },
    {
      path: '/index',
      name: 'Index',
      component: Index,
      children: [
        {
          path: 'register',
          name: 'Register',
          component: Register,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'withdraw',
          name: 'Withdraw',
          component: Withdraw,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'diagnose',
          name: 'Diagnose',
          component: Diagnose,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'make_presc',
          name: 'MakePresc',
          component: MakePresc,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'pay',
          name: 'Pay',
          component: Pay,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'refund',
          name: 'Refund',
          component: Refund,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'drug_trans',
          name: 'DrugTrans',
          component: DrugTrans,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'drug_rcv',
          name: 'DrugRcv',
          component: DrugRcv,
          meta: {
            requireAuth: true
          }
        }
      ],
      meta: {
        requireAuth: true
      }
    } 
  ]
})
