<template>
  <div id="app">
    <div  style="position:relative">
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
        style="position:absolute;width:100%;"
      ></vue-particles>
    </div>
    <!-- <Player /> -->
    <router-view />
  </div>
</template>

<script>
export default {
  name: "app",
  data() {
    return {};
  },
  methods: {},
  mounted() {
    if (window.performance.navigation.type == 1) {
      console.log("页面被刷新");
    } else {
      console.log("首次被加载");

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
  background-color: #f5f5f5;
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
    width: 992px;
    margin: 15px auto 0;
    margin-bottom: 50px;
  }
}
@media screen and (min-width: 768px) and (max-width: 992px) {
  .home-content {
    width: 850px;
    margin: 15px auto 0;
    margin-bottom: 50px;
  }
}
@media screen and (min-width: 500px) and (max-width: 768px) {
  .home-content {
    width: 600px;
    margin: 15px auto 0;
    margin-bottom: 50px;
  }
}
@media screen and (max-width: 500px) {
  .home-content {
    width: 480px;
    margin: 15px auto 0;
    margin-bottom: 50px;
  }
}

.home-content {
  /* width: auto; */
  min-height: calc(100vh - 108px);
}
</style>
