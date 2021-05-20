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
import 'highlight.js/styles/solarized-dark.css'
// import 'highlight.js/styles/monokai-sublime.css'
import 'viewerjs/dist/viewer.css'
import component from '@/views/front/registerComp/component.js'
import VueParticles from 'vue-particles'
import animated from 'animate.css'
import Viewer from 'v-viewer'
import {
  format
} from 'timeago.js';
//定义全局的过滤器 "changeTime":改变时间为几周前
Vue.filter('changeTime', function (dateStr) {
  return format(dateStr, 'zh_CN');
})
import outline from 'vue-outline'
import MetaInfo from 'vue-meta-info'
Vue.use(MetaInfo)
Vue.use(outline)
Vue.use(Viewer)
Vue.use(animated)
Vue.use(VueParticles)
Vue.use(component)
Vue.use(ElementUI, {
  locale
})
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  components: {
    App
  },
  template: '<App/>',
  render: h => h(App),
  /* 这句非常重要，否则预渲染将不会启动 */
  mounted() {
    document.dispatchEvent(new Event('render-event'))
  }
})
