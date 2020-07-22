
<template>
  <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="1">
    <div class="foot">
      <span class="f-span Copyright">Copyright©2019-2020</span> |
      <span class="f-span beian">湘ICP备16017187号-1</span>
      <br />
      <span class="f-span push">Powered with Vue&SpringBoot</span>
    </div>
  </el-col>
</template>

<script>
export default {
  name: "Footer"
};
</script>

<style>
.foot {
  height: 70px;
  color: #2db7f5;
  font-size: 12px;
  text-align: center;
  background-color: #fff;
  padding-top: 25px;
  margin-top: 35px;
}
.push{
  margin-top: 10px;
}
</style>
