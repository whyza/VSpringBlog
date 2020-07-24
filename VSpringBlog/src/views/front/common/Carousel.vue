<template>
  <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="1">
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
  name: "First",
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
        "https://iazuresky.com/usr/themes/Akina/images/postbg/1.jpg",
        "https://iazuresky.com/usr/themes/Akina/images/postbg/2.jpg",
      ];
      this.imgLoad();
    },
  },
  mounted() {
    this.getImgUrl();
    // 监听窗口变化，使得轮播图高度自适应图片高度
    let that = this;
    window.onresize = function temp() {
      // 通过点语法获取img的height属性值
      that.imgHeight = `${that.$refs.image["0"].height}`;
    };
  },
};
</script>