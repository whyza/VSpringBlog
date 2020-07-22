<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "app"
};
</script>

<style>
::-webkit-scrollbar {
  width: 5px;
  height: 5px;
}
::-webkit-scrollbar-thumb { 
  border-radius: 5px;
  background-color: #409EFF;
}
::-webkit-scrollbar-track { 
  box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
  background: #ededed;
  border-radius: 5px;

}
body {
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
    width: 1200px;
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
    width: 750px;
    margin: 15px auto 0;
    margin-bottom: 50px;
  }
}

.home-content {
  /* width: auto; */
  min-height: calc(100vh - 108px);
}
</style>
