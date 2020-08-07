<template>
  <div id="Header" ref="header" class="animated fadeInDown header-container">
    <div class="line"></div>
    <el-col :sm="24" :md="6" :lg="4" :xl="18" class="hidden-xs-only">
      <div class="h-logo">
        <router-link class="router-link-active" id="logo" :to="'/'">
          <img src="@/assets/logo.png" />
          <span class="title">wl´s blog</span>
          <span class="motto">go！</span>
        </router-link>
      </div>
    </el-col>
    <el-col :xs="24" :sm="24" class="hidden-sm-and-up">
      <div class="left-btn">
        <i class="el-icon-s-unfold" style="font-size:26px;"></i>
      </div>
      <div class="smallheader">
        <div class="sm-logo">
          <router-link id="sm-title" :to="'/'">
            <span class="smalltitle">wl´s blog</span>
            <span class="motto">go！</span>
          </router-link>
        </div>
      </div>
    </el-col>
    <el-col class="hidden-xs-only">
      <el-menu id="nav" :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
        <el-submenu v-for="item in categoryList" :key="item.id" :index="item.id+''">
          <template slot="title">
            <router-link class="nav-link contribute" :to="'/category/'+item.id">
              <span :class="item.categoryIcon" style="color:orange"></span>
              <span class="categoryName">{{item.categoryName}}</span>
            </router-link>
          </template>
          <el-menu-item
            v-show="item.sysCategoryList!==undefined && item.sysCategoryList.length!=0"
            v-for="childItem in item.sysCategoryList"
            :key="childItem.id"
            :index="childItem.id+''"
          >
            <router-link class="nav-link contribute" :to="'/category/'+childItem.id">
              <span :class="childItem.categoryIcon" style="color:orange"></span>
              <span class="categoryName">{{childItem.categoryName}}</span>
            </router-link>
          </el-menu-item>
        </el-submenu>
        <el-submenu index="-1">
          <template slot="title">
            <router-link class="nav-link contribute" :to="'/timeline'">
              <span class="el-icon-user" style="color:orange"></span>
              <span class="categoryName">时间轴</span>
            </router-link>
          </template>
        </el-submenu>
        <el-submenu index="0" v-show="isAdmin">
          <template slot="title">
            <router-link class="nav-link contribute" :to="'/admin/index'">
              <span class="el-icon-user" style="color:orange"></span>
              <span class="categoryName">后台管理</span>
            </router-link>
          </template>
        </el-submenu>
        <el-col :md="5" :lg="4" :xl="4" class="hidden-xs-only">
          <el-input placeholder="请输入内容" prefix-icon="el-icon-search" v-model="input" size="small"></el-input>
        </el-col>
      </el-menu>
    </el-col>
  </div>
</template>

<script>
import { getAllCategory } from "@/api/category";
import { getRoles } from "@/utils/auth"; // 验权

export default {
  name: "navbar",
  data() {
    return {
      input: "",
      activeIndex: "1",
      categoryList: [],
      isAdmin: false,
    };
  },
  methods: {
    getAllCategory() {
      getAllCategory("category/getAllCategory").then((res) => {
        this.categoryList = res.data;
      });
    },
    onScroll() {
      const scrollTop =
        document.documentElement.scrollTop + document.body.scrollTop;
      const headerDom = this.$refs.header;
      if (scrollTop >= 160) {
        if (!headerDom.getAttribute("class").includes("not-top")) {
          headerDom.className = "header-container not-top"; // 添加类名 css里设置动画
        }
      } else {
        headerDom.className = "header-container";
      }
    },
  },
  created() {
    this.getAllCategory();
    if (getRoles()) {
      this.isAdmin = true;
    }
  },
  mounted() {
    document.addEventListener("scroll", this.onScroll);
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.onScroll);
  },
};
</script>
<style scoped>
.header-container.not-top #logo img {
  width: 24px;
  height: 24px;
  /* float: left; */
  /* line-height: 55px; */
  text-shadow: 1px 2px 3px rgba(0, 0, 0, 0.1);
  transition: all 0.5s ease-out;
}

.header-container.not-top #logo {
  line-height: 24px;
  font-size: 1em;
}

.header-container.not-top#Header {
  height: 40px;
}

.not-top {
  position: fixed;
  z-index: 100;
  top: 0px;
  height: 40px;
  background: rgba(255, 255, 255, 0.9);
  color: #000;
  box-shadow: 1px 2px 6px rgba(0, 0, 0, 0.2);
}

body #Header {
  background-color: #fff;
  padding: 10px 60px;
  z-index: 11;
  position: fixed;
  width: 100%;
  top: 0;
  height: 64px;
  box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
  transition: all 0.5s ease-out;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
}

#logo {
  display: inline-block;
  font-size: 1.5em;
  line-height: 40px;
  color: #2c3e50;
  font-weight: 500;
  transition: all 0.5s ease-out;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
}

.smallheader {
  height: 36px;
  text-align: center;
  line-height: 36px;
}

.left-btn {
  position: absolute;
  left: 1vh;
  top: 1vh;
}

#sm-logo {
  display: inline-block;
  font-size: 1.5em;
  line-height: 40px;
  color: #2c3e50;
  font-weight: 500;
}

#Header a {
  color: #7e8c8d;
  text-decoration: none;
}

#logo img {
  vertical-align: middle;
  margin-right: 6px;
  width: 40px;
  transition: all 0.5s ease-out;
  height: 40px;
}

body #Header .title {
  color: #4f4f4f;
  font-family: "华文行楷", "LingWai TC";
}

body #Header .motto {
  color: #409eff;
  font-size: 12px;
  font-family: "华文行楷", "LingWai TC";
}

body #nav {
  position: fixed;
  list-style-type: none;
  margin: 0;
  padding: 0;
  position: absolute;
  right: 100px;
  top: 10px;
  height: 40px;
  line-height: 40px;
  transition: all 0.5s ease-out;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
}

.header-container.not-top #nav {
  font-size: 12px;
  color: #1a1a1a;
  padding: 0px 10px;
  line-height: 36px;
  transition: all 0.5s ease-out;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
}

.header-container.not-top #nav .nav-link {
  font-size: 13px;
  transition: all 0.5s ease-out;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
}

.header-container.not-top #nav {
  top: 0;
}

#nav .nav-link {
  cursor: pointer;
  transition: all 0.5s ease-out;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
}

.nav-link.contribute,
.nav-link.team {
  margin-left: 20px;
}

@media screen and (max-width: 768px) {
  body #Header {
    padding: 0px 10px !important;
    height: 36px;
  }
}

@media screen and (min-width: 768px) and (max-width: 999px) {
  #logo {
    font-size: 1em;
  }

  body #Header {
    padding: 5px 20px;
    height: 50px;
  }

  #nav {
    width: 72%;
  }

  body #nav {
    position: absolute;
    right: 0;
    top: 5px;
  }

  body #nav li {
    margin: 0;
  }

  #nav li .nav-link.contribute {
    font-size: 13px;
    margin-left: 6px;
  }
}

@media screen and (min-width: 1000px) and (max-width: 1200px) {
  body #nav {
    position: absolute;
    right: 0;
    top: 5px;
  }

  #nav li .nav-link.contribute {
    font-size: 14px;
    margin-left: 6px;
  }
}
</style>
<style>
.body{
    position: relative;
    padding: 50px;
    line-height: 30px;
    width: 600px;
    height: 1200px;
    background-image: linear-gradient(to right top,#ffcc00 50%,#eee 50%);
    background-size: 100% calc(100% - 100vh + 5px);
    background-repeat: no-repeat;
    z-index: 1; 
}
.body::after {
    content: "";
    position: fixed;
    top: 5px;
    left: 0;
    bottom: 0;
    right: 0;
    background: white;
    z-index: -1;
    
}
.el-menu.el-menu--horizontal {
  border-bottom: none;
}

.el-menu--horizontal > .el-menu-item {
  border-bottom: none;
}

.el-menu--horizontal > .el-menu-item:not(.is-disabled):focus,
.el-menu--horizontal > .el-menu-item:not(.is-disabled):hover,
.el-menu--horizontal > .el-submenu .el-submenu__title:hover {
  background-color: rgba(0, 0, 0, 0);
}

.header-container.not-top
  #nav
  .el-menu--horizontal
  > .el-submenu
  .el-submenu__title {
  height: 40px;
  line-height: 40px;
  transition: all 0.5s ease-out;
}

.el-menu--horizontal > .el-submenu .el-submenu__title {
  height: 55px !important;
  line-height: 40px !important;
  transition: all 0.5s ease-out;
}

.header-container.not-top
  #nav.el-menu--horizontal
  > .el-submenu
  .el-submenu__title {
  height: 40px !important;
  line-height: 40px !important;
  padding: 0 10px;
}

.header-container.not-top .el-submenu__title {
  padding: 0 20px !important;
}

.header-container .el-submenu__title {
  padding: 0 10px !important;
}

.el-submenu__title i {
  display: none;
}

.header-container.not-top .categoryName {
  font-size: 13px;
  transition: all 0.5s ease-out;
  transition: all 0.5s ease-out;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
}

.categoryName {
  font-size: 15px;
  color: #7e8c8d;
  padding-left: 5px;
  transition: all 0.5s ease-out;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
}

.header-container.not-top .el-input--small,
.header-container.not-top .el-input--small .el-input__icon {
  line-height: 40px;
}
</style>
