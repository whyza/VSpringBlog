<template>
  <div id="app">
    <div style="position:relative">
      <vue-particles
        color="#999"
        :particleOpacity="1"
        :particlesNumber="60"
        shapeType="circle"
        :particleSize="4"
        linesColor="#000"
        :linesWidth="1"
        :lineLinked="true"
        :lineOpacity="1"
        :linesDistance="150"
        :moveSpeed="2"
        :hoverEffect="true"
        hoverMode="grab"
        :clickEffect="true"
        clickMode="push"
        style="position:absolute;width:100%;z-index:-2"
      ></vue-particles>
    </div>
    <!-- <Player /> -->
    <router-view />
  </div>
</template>

<script>
import { queryComment } from "@/api/comment";

export default {
  name: "app",
  data() {
    return {};
  },
  provide: function () {
    return {
      reload: this.reload,
    };
  },
  methods: {},
  mounted() {
    if (window.performance.navigation.type == 1) {
    } else {
      this.$notify({
        type: "success",
        title: "Hello 欢迎：游客",
        message:
          "你可以点击<a href='#/a' style='color:#409EFF'>这里</a>留言给我！",
        position: "bottom-right",
        duration: "6000",
        dangerouslyUseHTMLString: true,
      });
    }
  },
};
</script>
<style>
/* #app {
  background-image: url("@../../images/bg.png");
} */
html,
body,
#app {
  height: auto;
  width: 100%;
  overflow: auto;
  text-shadow: 0 1px 3px rgba(88, 88, 88, 0.2);
}
::-webkit-scrollbar {
  width: 5px;
  height: 5px;
}
::-webkit-scrollbar-thumb {
  border-radius: 5px;
  background-color: #409eff;
}
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: #ededed;
  border-radius: 5px;
}
body {
  height: auto;
  margin: 0;
  padding: 0;
  /* background-color: #f5f5f5; */
  font-family: Helvetica, Tahoma, Arial, "PingFang SC", "Hiragino Sans GB",
    "Heiti SC", "Microsoft YaHei", "WenQuanYi Micro Hei", sans-serif;
}
.main-wrapper {
  width: 100%;
  margin: 0 auto;
  padding-top: 60px;
}
@media screen and (min-width: 1200px) {
  .home-content {
    width: 1150px;
    margin: 15px auto 0;
    margin-bottom: 50px;
  }
}

@media screen and (min-width: 992px) and (max-width: 1199px) {
  .home-content {
    margin: 15px auto 0;
    margin-bottom: 50px;
  }
  .isFixed{
    width:23.8% !important
  }
}
@media screen and (min-width: 768px) and (max-width: 992px) {
  .home-content {
    margin: 15px auto 0;
    margin-bottom: 50px;
  }
  .el-menu--horizontal > .el-submenu .el-submenu__title {
    height: 40px !important;
    line-height: 40px !important;
  }
}
@media screen and (min-width: 500px) and (max-width: 768px) {
  .el-image__inner {
    height: 90px !important;
  }
  .home-content {
    margin: 15px auto 0;
    margin-bottom: 50px;
  }
  .live-title {
    height: 20px;
    overflow: hidden;
    font-size: 0.8rem !important;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .main-wrapper {
    padding-top: 36px;
  }
  .section-title .title .main-title {
    font-size: 18px !important;
  }
  .section-title .title .vertical-line {
    height: 18px;
  }
  .section-title {
    padding: 0 5px !important;
    height: 50px !important;
  }
}
@media screen and (max-width: 500px) {
  .home-content {
    margin: 15px auto 0;
    margin-bottom: 50px;
  }
  .live-info {
    max-height: 68px !important;
  }
  .live-title {
    height: 20px;
    overflow: hidden;
    font-size: 0.8rem !important;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .live-desc {
    margin-top: 10px !important;
    font-size: 0.6rem !important;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
    line-height: normal;
  }
  .main-wrapper {
    padding-top: 36px;
  }
  .section-title .title .main-title {
    font-size: 18px !important;
  }
  .section-title .title .vertical-line {
    height: 18px;
  }
  .section-title {
    padding: 0 5px !important;
    height: 50px !important;
  }
  #art-main .art-title {
    font-size: 18px !important;
  }
  .markdown-body blockquote,
  .markdown-body details,
  .markdown-body dl,
  .markdown-body ol,
  .markdown-body p,
  .markdown-body pre,
  .markdown-body table,
  .markdown-body ul {
    font-size: 13px !important;
  }
  .v-note-wrapper .v-note-panel .v-note-show .v-show-content,
  .v-note-wrapper .v-note-panel .v-note-show .v-show-content-html {
    padding: 8px !important;
  }
  .child-box {
    border: none;
  }
  .el-image__inner {
    height: 65px !important;
  }
}

.home-content {
  /* width: auto; */
  min-height: calc(100vh - 108px);
  position: relative;
}
</style>
