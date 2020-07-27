
<template>
  <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="1">
    <div class="foot">
      <div class="f-span push">Powered with Vue&SpringBoot</div>

      <div class="f-span Copyright">
        ©2020
        <i class="el-icon-loading" style="color:red"></i> whyzaa
      </div>
      <div class="beian">湘ICP备16017187号-1</div>
    </div>
  </el-col>
</template>

<script>
export default {
  name: "Footer",
};
</script>

<style scoped>
.foot {
  height: 100px;
  color: #b9b9b9;
  font-size: 12px;
  text-align: center;
  background-color: #fff;
  padding-top: 25px;
  margin-top: 10px;
}
.f-span {
  text-align: center;
  height: 20px;
  margin-bottom: 5px;
}
</style>
