// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import moment from 'moment'
Vue.prototype.$moment=moment
import axios from 'axios'
import VueHighcharts from 'vue-highcharts'

Vue.use(VueHighcharts)
import HighCharts from 'highcharts'

Vue.use(HighCharts)
Vue.config.productionTip = false

Vue.use(ElementUI)

Vue.prototype.axios=axios
// axios.defaults.headers.common['token'] = localStorage.getItem('token');//全局默认配置
// console.log("这里是全局请求头："+ axios.defaults.headers.common['token'] )


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,

  components: { App },
  template: '<App/>'
})
