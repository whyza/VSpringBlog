
<template>
  <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="1" >
    <div class="section-title">
      <div class="title">
        <span class="main-title">博文</span>
        <span class="vertical-line"></span>
        <span class="sub-title">Articles</span>
        <span class="view-more"></span>
      </div>
    </div>
  </el-col>
</template>

<script>
export default {
  name: "Category"
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
