<template>
  <div class="dashboard-container">
    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="8">
      <el-card class="box-card">
        <i class="el-icon-medal-1 l-icon" style="color:orange"></i>
        <div class="r-detail fr">
          <div class="detail">浏览总数</div>
          <div class="nummber">12233</div>
        </div>
      </el-card>
    </el-col>
    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="8">
      <el-card class="box-card">
        <i class="el-icon-reading l-icon" style="color:#409EFF"></i>
        <div class="r-detail fr">
          <div class="detail">文章总数</div>
          <div class="nummber">12233</div>
        </div>
      </el-card>
    </el-col>
    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="8">
      <el-card class="box-card">
        <i class="el-icon-chat-dot-round l-icon" style="color:#909399"></i>
        <div class="r-detail fr">
          <div class="detail">评论总数</div>
          <div class="nummber">12</div>
        </div>
      </el-card>
    </el-col>
    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="8">
      <el-card class="box-card">
        <i class="el-icon-thumb l-icon" style="color:#F56C6C"></i>
        <div class="r-detail fr">
          <div class="detail">点赞总数</div>
          <div class="nummber">12233</div>
        </div>
      </el-card>
    </el-col>
    <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
      <div id="container" class="box"></div>
    </el-col>
    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
      <div class="box" style="margin:10px 20px 10px 10px">
        <el-tabs>
          <el-tab-pane label="浏览排行">浏览排行</el-tab-pane>
          <el-tab-pane label="点赞排行">点赞排行</el-tab-pane>
        </el-tabs>
      </div>
    </el-col>
  </div>
</template>

<script>
import { Chart } from "@antv/g2";

export default {
  name: "Dashboard",
  mounted() {
    const data = [
      { year: "1951 年", sales: 38 },
      { year: "1952 年", sales: 52 },
      { year: "1956 年", sales: 61 },
      { year: "1957 年", sales: 145 },
      { year: "1958 年", sales: 48 },
      { year: "1959 年", sales: 38 },
      { year: "1960 年", sales: 38 },
      { year: "1962 年", sales: 38 },
    ];
    const chart = new Chart({
      container: "container",
      autoFit: true,
      height: 500,
    });

    chart.data(data);
    chart.scale("sales", {
      nice: true,
    });

    chart.tooltip({
      showMarkers: false,
    });
    chart.interaction("active-region");

    chart.interval().position("year*sales");

    chart.render();
  },
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 10px;
    .box {
      margin-top: 10px;
      padding: 10px;
      box-shadow: 0 10px 12px 0 rgba(0, 0, 0, 0.1);
      background-color: #fff;
    }
    .box-card {
      margin-right: 20px;
      .l-icon {
        line-height: 60px;
        font-size: 55px;
      }
      .fr {
        float: right;
      }
      .r-detail {
        line-height: 40px;
        .detail {
          font-size: 14px;
          color: #7d7d7d;
        }
        .nummber {
          font-size: 15px;
          font-weight: 600;
        }
      }
    }
  }
}
</style>
