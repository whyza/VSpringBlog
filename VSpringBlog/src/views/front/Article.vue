<template>
  <div>
    <!-- Header start -->
    <Header />
    <!-- Header end -->
    <!-- main-wrapper -->
    <div class="main-wrapper">
      <div class="home-content">
                <Sentences />

        <!-- Carousel start -->
        <el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="1">
          <!-- <Carousel /> -->

          <Category />
          <ArticleDetail />
        </el-col>

        <!--SiderBar start  -->
        <!-- <el-col :md="7" :lg="6" :xl="1" class="hidden-sm-and-down"> -->
        <SiderBar />
        <!-- </el-col> -->
        <el-col :xs="24" :sm="24" :md="18" :lg="18" :xl="1">
          <Comment />
        </el-col>
      </div>
    </div>
    <!-- main-wrapper -->
    <!-- Footer start -->
    <Footer />
  </div>
</template>
<script>
import ArticleDetail from "@/views/front/common/ArticleDetail";
import Comment from "@/views/front/common/Comment";
export default {
  name: "Article",
  components: {
    ArticleDetail: ArticleDetail,
    Comment: Comment,
  },
};
</script>
<style>
</style>