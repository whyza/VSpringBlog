<template>
  <div>
    <!-- Header start -->
    <navbar />
    <!-- Header end -->
    <!-- main-wrapper -->
    <div class="main-wrapper">
      <div class="home-content">
        <!-- Carousel start -->
        <notice id="sentences" />

        <el-col :xs="24" :sm="24" :md="17" :lg="18" :xl="18">
          <carousel />
          <!-- <el-row :gutter="10"> -->
          <category />
          <!-- </el-row> -->
          <!-- ArticleList start -->
          <articleList :isIndexPage="true" />
        </el-col>
        <!--siderBar start  -->
        <!-- <el-col :md="7" :lg="6" :xl="1" class="hidden-sm-and-down"> -->
        <siderBar />
        <!-- </el-col> -->
      </div>
    </div>
    <!-- Footer start -->
    <footers />
  </div>
</template>


<script>
export default {
  name: "home",
  data() {
    return {};
  },
  mounted() {
    document.title = "wl´s blog";
  },
  metaInfo: {
    title: "首页",
    meta: [
      {
        name: "keywords",
        content: "首页,Vue,Spring,SpringBoot,JAVA,Mysql",
      },
      {
        name: "description",
        content: "wl´s blog",
      },
    ],
  },
};
</script>

<style>
</style>
