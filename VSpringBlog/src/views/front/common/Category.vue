
<template>
  <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
    <div class="section-title">
      <div class="title">
        <span class="main-title">{{categoryName}}</span>
        <span class="vertical-line"></span>
        <span class="sub-title">Articles</span>
        <span class="view-more"></span>
      </div>
    </div>
  </el-col>
</template>

<script>
import { getCategoryNameById } from "@/api/category";
export default {
  name: "category",
  data() {
    return { categoryName: "博文" };
  },
  methods: {
    getCategoryName() {
      if (
        this.$route.params.categoryid !== undefined &&
        this.$route.params.categoryid !== null
      ) {
        getCategoryNameById("/category/getCategoryNameById", {
          categoryId: this.$route.params.categoryid,
        }).then((res) => {
          if (res.data) {
            this.categoryName = res.data.categoryName;
            document.title = res.data.categoryName+"-wl´s blog"
          }
        });
      }
    },
  },
  created() {
    this.getCategoryName();
  },
  watch: {
    $route: {
      handler() {
        this.id = this.$route.params.categoryid;
        this.getCategoryName();
      },
      deep: true,
    },
  },
};
</script>

<style scoped>
.section-title {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-pack: justify;
  justify-content: space-between;
  padding: 10px 0;
  height: 75px;
  line-height: 50px;
  text-align: left;
  box-sizing: border-box;
}
.section-title .title .main-title {
  display: inline-block;
  vertical-align: middle;
  font-size: 22px;
  padding: 0 10px;
}
.section-title .title .vertical-line {
  display: inline-block;
  height: 20px;
  width: 1px;
  background-color: #333;
  vertical-align: middle;
}
.section-title .title .sub-title {
  display: inline-block;
  vertical-align: middle;
  font-size: 18px;
  padding-left: 10px;
}
.section-title .title .view-more {
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
  padding-left: 10px;
}
</style>
