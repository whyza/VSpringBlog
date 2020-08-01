import Vue from 'vue'
import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import ElementUI from 'element-ui'
import 'font-awesome/css/font-awesome.min.css'
import 'element-ui/lib/theme-chalk/display.css'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n
import '@/styles/index.scss' // global css
import App from './App'
import router from './router'
import store from './store'
import i18n from './lang' // Internationalization
import '@/icons' // icon
import '@/permission' // permission control
import 'github-markdown-css';
// import 'highlight.js/styles/github.css'
import 'viewerjs/dist/viewer.css'
import component from '@/views/front/registerComp/component.js'
import VueParticles from 'vue-particles'
import animated from 'animate.css'
import Viewer from 'v-viewer'
import { format } from 'timeago.js';
//定义全局的过滤器 "changeTime":改变时间为几周前
Vue.filter('changeTime', function(dateStr) {
    return format(dateStr, 'zh_CN');
})
Vue.use(Viewer)
Vue.use(animated)
Vue.use(VueParticles)
Vue.use(component)
Vue.use(ElementUI, { locale })
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
})
