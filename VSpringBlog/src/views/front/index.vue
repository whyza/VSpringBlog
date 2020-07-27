<template>
  <div>
    <!-- Header start -->
    <Header ref="header" class="header-container" />
    <!-- Header end -->
    <!-- main-wrapper -->
    <div class="main-wrapper">
      <div class="home-content">
        <!-- Carousel start -->
        <Sentences />

        <el-col :xs="24" :sm="24" :md="17" :lg="18" :xl="1">
          <Carousel />
          <!-- <el-row :gutter="10"> -->
          <Category />
          <!-- </el-row> -->
          <!-- ArticleList start -->
          <ArticleList />
        </el-col>
        <!--SiderBar start  -->
        <!-- <el-col :md="7" :lg="6" :xl="1" class="hidden-sm-and-down"> -->
        <SiderBar />
        <!-- </el-col> -->
      </div>
    </div>
    <!-- main-wrapper -->
    <!-- Footer start -->
    <Footer />
  </div>
</template>


<script>
export default {
  name: "index",
  data() {
    return {};
  },
};
</script>

<style>
</style>