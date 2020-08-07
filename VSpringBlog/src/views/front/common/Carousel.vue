<template>
  <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" >
    <el-carousel :height="(imgHeight>=360? 360:imgHeight)+'px'" indicator-position="outside">
      <el-carousel-item v-for="(item,index) in imgurl" :key="index">
        <img ref="image" style="width:100%;" :src="item" alt="轮播图" />
      </el-carousel-item>
    </el-carousel>
  </el-col>
</template>
<script>
// 引入axios
import axios from "axios";
export default {
  name: "carousel",
  data() {
    return {
      imgurl: "",
      imgHeight: "",
    };
  },
  methods: {
    imgLoad() {
      this.$nextTick(function () {
        // 获取窗口宽度*图片的比例，定义页面初始的轮播图高度
        this.imgHeight = (document.body.clientWidth * 1) / 4;
      });
    },
    getImgUrl() {
      this.imgurl = [
        "https://imgs.iazuresky.com/52a27da1-cb25-4ab3-a69d-741f12c27648014258596b34b8a8012193a3c64c7a.jpg@2o.jpg",
        "https://imgs.iazuresky.com/541253c0-cb18-4839-9b7e-d5b73feb15691570711489953315.jpg",
      ];
      this.imgLoad();
    },
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);

    this.getImgUrl();
    // 监听窗口变化，使得轮播图高度自适应图片高度
    let that = this;
    window.onresize = function temp() {
      // 通过点语法获取img的height属性值
      that.imgHeight = `${that.$refs.image["0"].height}`;
    };
  },
  beforeDestroy() {
    window.onresize = null;
  },
};
</script>