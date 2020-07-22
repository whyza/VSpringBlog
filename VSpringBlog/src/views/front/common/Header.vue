<template>
  <div id="Header">
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
          <router-link class="nav-link contribute" :to="'/Category/'+'2'">
            <span class="iconfont icon-icon-test">技术交流</span>
          </router-link>
        </li>
        <li>
          <router-link class="nav-link contribute" :to="'/Category/'+'3'">
            <span class="iconfont icon-icon-test">技术交流</span>
          </router-link>
        </li>
        <li>
          <router-link class="nav-link contribute" :to="'/Category/'+'1'">
            <span class="iconfont icon-icon-test">技术交流</span>
          </router-link>
        </li>
        <li>
          <router-link class="nav-link contribute" :to="'/Category/'+'4'">
            <span class="iconfont icon-icon-test">技术交流</span>
          </router-link>
        </li>
        <li>
          <router-link class="nav-link contribute" :to="'/Category/'+'5'">
            <span class="iconfont icon-icon-test">技术交流</span>
          </router-link>
        </li>
        <li>
          <router-link class="nav-link contribute" :to="'/admin/index'">
            <span class="iconfont icon-icon-test">后台管理</span>
          </router-link>
        </li>
        <li>
          <el-input placeholder="请输入内容" prefix-icon="el-icon-search" v-model="input"></el-input>
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
      input: ""
    };
  }
};
</script>
<style>
body #Header {
  background-color: #fff;
  padding: 10px 60px;
  z-index: 11;
  position: fixed;
  width: 100%;
  top: 0;
}
#Header {
 box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)}
#logo {
  display: inline-block;
  font-size: 1.5em;
  line-height: 40px;
  color: #2c3e50;
  font-weight: 500;
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
}
#nav {
  list-style-type: none;
  margin: 0;
  padding: 0;
  position: absolute;
  right: 100px;
  top: 10px;
  height: 40px;
  line-height: 40px;
}
#nav li {
  display: inline-block;
  position: relative;
  margin: 0 0.6em;
}
li {
  list-style: none;
}
#nav .nav-link {
  cursor: pointer;
}
.nav-link.contribute,
.nav-link.team {
  margin-left: 20px;
}
.nav-link {
  padding-bottom: 3px;
  font-size: 15px;
}
@media screen and (max-width: 768px) {
  #Header {
    padding: 0px 10px !important;
  }
}
</style>
