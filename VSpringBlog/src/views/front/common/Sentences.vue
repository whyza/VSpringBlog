<template>
  <div class="insentence">
    <el-carousel height="20px" direction="vertical" :autoplay="true">
      <el-carousel-item v-for="item in 3" :key="item">
        <div>
          <i style="color: #ff0000;" class="fa fa-volume-up" aria-hidden="true"></i>&nbsp;
          <span
            style="word-wrap: break-word;word-break: break-all;overflow: hidden;font-size: 12px;"
          >{{item}}</span>
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
export default {
  name: "sentences",
};
</script>
<style  scoped>
.insentence {
  background: #fff;
  border-radius: 5px;
  padding: 6px 0 6px 15px;
  box-shadow: 0 0px 10px rgba(0, 0, 0, 0.05);
  height: auto;
  margin-bottom: 10px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #fff;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #fff;
}
</style>