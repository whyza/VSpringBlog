<template>
  <div>
    <!-- Header start -->
    <Header />
    <!-- Header end -->
    <!-- main-wrapper -->
    <div class="main-wrapper">
      <div class="home-content">
        <!-- Carousel start -->
        <Sentences />
        <el-col :xs="24" :sm="24" :md="17" :lg="18" :xl="1">
          <Category />
        </el-col>
        <el-col :xs="24" :sm="24" :md="17" :lg="18" :xl="1">
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
  name: "ArtCategory"
};
</script>

<style>
</style>