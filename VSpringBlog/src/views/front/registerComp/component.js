import Header from "@/views/front/common/Header";
import Carousel from "@/views/front/common/Carousel";
import ArticleList from "@/views/front/common/ArticleList";
import Footer from "@/views/front/common/Footer";
import SiderBar from "@/views/front/common/SiderBar";
import Category from "@/views/front/common/Category";
export default (Vue) => {
  // 注册组件
  Vue.component('Header', Header);
  Vue.component('Carousel', Carousel);
  Vue.component('ArticleList', ArticleList);
  Vue.component('Footer', Footer);
  Vue.component('SiderBar', SiderBar);
  Vue.component('Category', Category);

}