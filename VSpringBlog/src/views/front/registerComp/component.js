import navbar from "@/views/front/common/navbar";
import carousel from "@/views/front/common/carousel";
import articleList from "@/views/front/common/articleList";
import footers from "@/views/front/common/footers";
import siderBar from "@/views/front/common/siderBar";
import category from "@/views/front/common/category";
import notice from "@/views/front/common/notice";
import articleDetail from "@/views/front/common/articleDetail";
import comment from "@/views/front/common/comment";
import timeline from "@/views/front/common/timeline";

export default (Vue) => {
  // 注册组件
  Vue.component('navbar', navbar);
  Vue.component('carousel', carousel);
  Vue.component('articleList', articleList);
  Vue.component('footers', footers);
  Vue.component('siderBar', siderBar);
  Vue.component('category', category);
  Vue.component('notice', notice);
  Vue.component('articleDetail', articleDetail);
  Vue.component('comment', comment);
  Vue.component('timeline', timeline);
}