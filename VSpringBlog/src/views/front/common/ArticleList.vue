<template>
  <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="1" >
    <router-link v-for="article in articleList" :key="article.id"  :to="'/ArticleDetail/'+article.id">
      <div class="article art-card art-card-bordered animated bounce" >
        <div class="art-body">
          <div class="art-row">
            <el-col :xs="9" :sm="7" :md="7" :lg="8" :xl="1" style="overflow:hidden">
              <img
                src="https://double.aquestian.cn/2020-07-17-16-36-12-ribbit-mq.jpg"
                class="art-img"
                style="width: 100%;"
              />
            </el-col>
            <el-col :xs="15" :sm="17" :md="17" :lg="16" :xl="1">
              <div class="live-info">
                <div class="live-title">{{article.title}}</div>
                <div
                  class="live-desc"
                >MQ(消MQ(消MQ(消息MQ()常见的应用场景解析MQ(消息队列)常见的应用场景解析MQ(消息队列)(消息队列)())))MQ(消息队列)</div>
              </div>
            </el-col>
            <el-col class="hidden-xs-only" :sm="17" :md="17" :lg="16" :xl="1">
              <div class="live-down">
                <div class="live-down-left">
                  <div name="消息队列" class="live-for">
                    <i class="fa fa-tags" style="color: rgb(213, 43, 179);"></i> 消息队列
                  </div>
                </div>
                <div class="live-down-right">
                  <div class="live-name">
                    <i class="fa fa-user"></i>
                    <span>aqian666</span>
                  </div>
                  <div class="live-time">
                    <i class="fa fa-clock-o"></i>
                    <span>2020-07-17 16:56:00</span>
                  </div>
                </div>
              </div>
            </el-col>
          </div>
        </div>
      </div>
    </router-link>

    <el-col :xs="16" :sm="16" :md="24" :lg="24" :xl="1">
      <el-pagination background layout="prev, pager, next " :total="100"></el-pagination>
    </el-col>
  </el-col>
</template>


<script>
import { getArticleListPage } from "@/api/article";

export default {
  name: "ArticleList",
  data() {
    return {
      articleList: "",
    };
  },
  methods: {
    getArticleListPage() {
      getArticleListPage("article/getArticleListPage", {
        current: 1,
        size: 50,
      }).then((res) => {
        this.articleList = res.data.records;
      });
    },
  },
  created() {
    this.getArticleListPage();
  },
};
</script>

<style  scoped>
/* 文章card */
.article {
  height: 100%;
  background-color: #fff;
  margin-bottom: 10px;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}
.article:hover {
  box-shadow: 0 1px 6px hsla(184, 100%, 50%, 0.5);
  border-color: #eee;
}
.art-card-bordered {
  border: 1px solid #dddee1;
  border-color: #e9eaec;
}
.art-card {
  background: #fff;
  border-radius: 4px;
  font-size: 14px;
  position: relative;
  transition: all 0.2s ease-in-out;
}
.art-card,
.art-card-bordered {
  border: 1px solid #dcdee2;
  border-color: #e8eaec;
}
.art-body {
  padding: 16px;
}
.art-row {
  position: relative;
  margin-left: 0;
  margin-right: 0;
  height: auto;
  zoom: 1;
  display: block;
  overflow: hidden;
}

.live-info {
  height: 100px;
  padding-left: 15px;
  overflow: hidden;
  padding-top: 10px;
}

.live-title {
  font-size: 16px;
  transition: all 0.6s;
  font-weight: 700;
}
.live-desc {
  color: #9ea7b4;
  margin-top: 20px;
  line-height: 20px;
  text-indent: 0.6em;
  font-size: 13px;
  max-height: 60px;
}
.live-down {
  margin-top: -10px;
}
.live-for {
  float: left;
  margin-left: 15px;
}
.live-name {
  width: 147px;
  font-size: 15px;
}
.live-time {
  color: #9ea7b4;
  width: 147px;
  margin-top: 5px;
  font-size: 10px;
  line-height: 20px;
}

.live-down-left {
  margin-top: 20px;
  float: left;
}
.live-down-right {
  margin-top: 20px;
  float: right;
}
.art-img {
  overflow: hidden;
  padding-top: 5px;
  transition: all 0.6s;
}
.article:hover .art-img {
  transform: scale(1.05);
}
.article:hover .live-title {
  color: #409eff;
  padding-left: 10px;
}
/* 分页 */
.page {
  margin-top: 40px;
}
</style>
