<template>
  <div id="Header" ref="header" class="animated fadeInDown header-container">
    <el-col :xs="24" :sm="24" :md="6" :lg="4" :xl="1" class="hidden-sm-and-down">
      <router-link class="router-link-active" id="logo" :to="'/index'">
        <img src="@/assets/logo.png" />
        <span class="title">答案</span>
        <span class="motto">go！go！go！</span>
      </router-link>
    </el-col>
    <el-col :xs="12" :sm="12" class="hidden-sm-and-up" style="padding-top:8px">
      <i class="el-icon-s-unfold" style="font-size:26px"></i>
    </el-col>
    <el-col :xs="11" :sm="11" class="hidden-sm-and-up">
      <router-link class id="sm-logo" :to="'/index'">
        <img src="@/assets/logo.png" />
      </router-link>
    </el-col>
    <el-col :xs="24" :sm="24" :md="4" :lg="4" :xl="1" class="hidden-sm-and-down">
      <ul id="nav">
        <li>
          <router-link class="nav-link contribute" :to="'/category/'+'2'">
            <span class="el-icon-magic-stick" style="color:orange">&#xA0;SpringBoot</span>
          </router-link>
        </li>
        <li>
          <router-link class="nav-link contribute" :to="'/category/'+'3'">
            <span class="el-icon-crop" style="color:#409EFF">&#xA0;Vue</span>
          </router-link>
        </li>
        <li>
          <router-link class="nav-link contribute" :to="'/category/'+'1'">
            <span class="el-icon-guide" style="color:#F56C6C">&#xA0;Web</span>
          </router-link>
        </li>
        <li>
          <router-link class="nav-link contribute" :to="'/category/'+'4'">
            <span class="el-icon-coin" style="color:#42b983">&#xA0;数据库</span>
          </router-link>
        </li>
        <li>
          <router-link class="nav-link contribute" :to="'/admin/index'">
            <span class="el-icon-user">&#xA0;后台管理</span>
          </router-link>
        </li>
        <li>
          <el-input placeholder="请输入内容" prefix-icon="el-icon-search" v-model="input" size="small"></el-input>
        </li>
      </ul>
    </el-col>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      input: "",
    };
  },
  methods: {
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
  mounted() {
    document.addEventListener("scroll", this.onScroll);
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.onScroll);
  },
};
</script>
<style scoped>
.header-container {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1999;
  width: 100%;
}
.header-container.not-top #logo img {
  width: 24px;
  height: 24px;
  /* float: left; */
  /* line-height: 55px; */
  text-shadow: 1px 2px 3px rgba(0, 0, 0, 0.1);
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
#sm-logo {
  display: inline-block;
  font-size: 1.5em;
  line-height: 40px;
  color: #2c3e50;
  font-weight: 500;
}
#sm-logo img {
  vertical-align: middle;
  margin-right: 6px;
  width: 23px;
  height: 23px;
}
#Header a {
  color: #7e8c8d;
  text-decoration: none;
}
#logo img {
  vertical-align: middle;
  margin-right: 6px;
  width: 40px;
  height: 40px;
  transition: all 0.5s ease-out;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
}
body #Header .title {
  color: #4f4f4f;
}
body #Header .motto {
  color: #409eff;
  font-size: 12px;
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
body #nav li {
  display: inline-block;
  position: relative;
  margin: 0 0.6em;
  transition: all 0.5s ease-out;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
}
.header-container.not-top #nav li {
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
.header-container.not-top #nav li .nav-link {
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
  #Header {
    padding: 0px 10px !important;
  }
}
</style>
