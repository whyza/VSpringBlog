import navbar from "@/views/front/common/navbar";
import carousel from "@/views/front/common/carousel";
import articleList from "@/views/front/common/articleList";
import footers from "@/views/front/common/footers";
import siderBar from "@/views/front/common/siderBar";
import category from "@/views/front/common/category";
import sentences from "@/views/front/common/sentences";
export default (Vue) => {
  // 注册组件
  Vue.component('navbar', navbar);
  Vue.component('carousel', carousel);
  Vue.component('articleList', articleList);
  Vue.component('footers', footers);
  Vue.component('siderBar', siderBar);
  Vue.component('category', category);
  Vue.component('sentences', sentences);
}