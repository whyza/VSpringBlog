<template>
  <div>
    <!-- Header start -->
    <navbar />
    <!-- Header end -->
    <!-- main-wrapper -->
    <div class="main-wrapper">
      <div class="home-content">
        <!-- Carousel start -->
        <sentences />
        <el-col :xs="24" :sm="24" :md="17" :lg="18" :xl="18">
          <category />
        </el-col>
        <el-col :xs="24" :sm="24" :md="17" :lg="18" :xl="18">
          <articleList :isIndexPage=false />
        </el-col>
        <!--siderBar start  -->
        <!-- <el-col :md="7" :lg="6" :xl="1" class="hidden-sm-and-down"> -->
          <siderBar />
        <!-- </el-col> -->
      </div>
    </div>
    <!-- main-wrapper -->
    <!-- Footer start -->
    <footers />
  </div>
</template>
<script>
export default {
  name: "articleCategoryIndex"
};
</script>

<style>
</style>
