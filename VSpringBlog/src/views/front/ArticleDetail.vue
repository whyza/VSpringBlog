<template>
  <div>
    <!-- Header start -->
    <navbar />
    <!-- Header end -->
    <!-- main-wrapper -->
    <div class="main-wrapper">
      <div class="home-content">
        <!-- Carousel start -->
        <notice />
        <el-col :xs="24" :sm="24" :md="17" :lg="18" :xl="18">
          <category />
        </el-col>
        <el-col :xs="24" :sm="24" :md="17" :lg="18" :xl="18">
          <articleDetail />
          <comment />
        </el-col>
        <!--siderBar start  -->
        <siderBar />

      </div>
    </div>
    <!-- main-wrapper -->
    <!-- Footer start -->
    <footers />
  </div>
</template>
<script>

export default {
  name: "articleDetailIndex",
};
</script>

<style scoped>
</style>
