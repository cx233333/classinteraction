import Vue from 'vue'
import Router from 'vue-router'


import Login from '../pages/Login.vue'
import index from '../pages/index.vue'





Vue.use(Router)
export default new Router({
 // mode:'history',
  routes: [

    {
      path: '/',
      name: 'Login',
      component: Login,
      meta: {
        keepAlive:false
      }
    },

    {
      path: '/pages',
      name: 'pages',
      component:resolve => require(['@/pages'],resolve),
      children:[
        {
          path:'/pages/inmanage',
          name:'inmanage',
          component: resolve => require(['@/pages/inmanage'], resolve),
         meta:{
        keepAlive:true

      }
    },
    {
      path:'/pages/course',
      name:'course',
      component: resolve => require(['@/pages/course'], resolve),
     meta:{
    keepAlive:true

  }
},
    
    {
      path: '/pages/index',
      name: 'index',
      component: resolve => require(['@/pages/index'], resolve),
      meta:{
     keepAlive:true
   }
    },
    {
      path: '/pages/xuanke',
      name: 'xuanke',
      component: resolve => require(['@/pages/xuanke'], resolve),
      meta:{
     keepAlive:true
   }
    },
  
  
   
    {
      path: '/pages/dm',
      name: 'dm',
      component: resolve => require(['@/pages/dm'], resolve),
      meta:{
     keepAlive:true
   }
    },
   
  ]
    }
  ]
})

