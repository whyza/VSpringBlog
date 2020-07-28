<template>
  <div>
    <!-- Header start -->
    <navbar />
    <!-- Header end -->
    <!-- main-wrapper -->
    <div class="main-wrapper">
      <div class="home-content">
        <sentences />
        <!-- Carousel start -->
        <el-col :xs="24" :sm="24" :md="17" :lg="18" :xl="1">
          <!-- <Carousel /> -->
          <category />
          <articleDetail />
        </el-col>
        <!--siderBar start  -->
        <!-- <el-col :md="7" :lg="6" :xl="1" class="hidden-sm-and-down"> -->
        <siderBar />
        <!-- </el-col> -->
        <el-col :xs="24" :sm="24" :md="17" :lg="18" :xl="1">
          <comment />
          <commentEdit />
        </el-col>
      </div>
    </div>
    <!-- main-wrapper -->
    <!-- Footer start -->
    <footers />
  </div>
</template>
<script>
import articleDetail from "@/views/front/common/articleDetail";
import comment from "@/views/front/common/comment";
import commentEdit from "@/views/front/common/commentEdit";

export default {
  name: "Article",
  components: {
    articleDetail,
    comment,
    commentEdit,
  },
};
</script>
<style>
</style>