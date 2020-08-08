<template>
  <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
    <router-link
      v-for="article in articleList"
      :key="article.id"
      :to="'/post/'+article.categoryName+'/'+article.id"
    >
      <div class="article art-card art-card-bordered animated fadeInUp">
        <div class="art-body">
          <span class="title-l"></span>

          <div class="art-row">
            <el-col
              :xs="6"
              :sm="7"
              :md="7"
              :lg="8"
              :xl="6"
              style="overflow:hidden;position:relative"
            >
              <span class="category">
                <router-link
                  class="gotocategory"
                  :to="'/post/'+article.categoryId"
                >{{article.categoryName}}</router-link>
              </span>
              <div class="block">
                <el-image
                  :src="article.articleThumbnail"
                  class="art-img"
                  style="width: 100%; height: 100%"
                  fit="cover"
                >
                  <div slot="error" class="image-slot">
                    <i class="el-icon-picture-outline"></i>
                  </div>
                  <div slot="placeholder" class="image-slot">
                    加载中
                    <span class="dot">...</span>
                  </div>
                </el-image>
              </div>
            </el-col>
            <el-col :xs="15" :sm="17" :md="17" :lg="16" :xl="18">
              <div class="live-info">
                <div class="live-title">{{article.title}}</div>
                <div
                  class="live-desc"
                >{{article.htmlText.substring(4,100)}}</div>
              </div>
            </el-col>
            <el-col class="hidden-xs-only" :sm="17" :md="17" :lg="16" :xl="18">
              <div class="live-down">
                <div class="live-down-left">
                  <div class="live-for" v-for="tags in article.sysTagsList" :key="tags.id">
                    <i class="fa fa-tags" style="color: rgb(213, 43, 179);"></i>
                    <span style="font-size:13px">{{tags.tagsName}}</span>
                  </div>
                </div>
                <div class="live-down-right">
                  <div class="live-name">
                    <i class="el-icon-user"></i>
                    <span>{{article.userName}}</span>
                  </div>
                  <div class="live-time">
                    <i class="fa fa-clock-o"></i>
                    <span>{{article.creatTime|changeTime}}</span>
                  </div>
                </div>
              </div>
            </el-col>
          </div>
        </div>
      </div>
    </router-link>

    <el-col :xs="16" :sm="16" :md="24" :lg="24" :xl="18">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageConf.pageCode"
        :page-size="pageConf.pageSize"
        layout="total, prev, pager, next, jumper"
        :total="pageConf.totalPage"
        class="pageing"
      ></el-pagination>
    </el-col>
  </el-col>
</template>


<script>
import { getArticleListPage, getArticleListByCategoryId } from "@/api/article";

export default {
  name: "articleList",

  props: {
    isIndexPage: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      articleList: "",
      pageConf: {
        pageCode: 1, //当前页
        pageSize: 5, //每页显示的记录数
        totalPage: 10, //总记录数
      },
    };
  },
  methods: {
    //pageSize改变时触发的函数
    handleSizeChange(val) {
      this.pageConf.pageSize = val;
      this.changeArticleListDetail();
      this.scollToSentences();
    },
    //当前页改变时触发的函数
    handleCurrentChange(val) {
      this.pageConf.pageCode = val;
      this.changeArticleListDetail();
      this.scollToSentences();
    },
    scollToSentences() {
      document
        .getElementById("sentences")
        .scrollIntoView({ block: "start", behavior: "smooth" });
    },
    getArticleListByCategoryId() {
      getArticleListByCategoryId("article/getArticleListByCategoryId", {
        current: this.pageConf.pageCode,
        size: this.pageConf.pageSize,
        categoryId: this.$route.params.categoryid,
      }).then((res) => {
        this.articleList = res.data.records;
        this.pageConf.totalPage = res.data.total;
      });
    },
    getArticleListPage() {
      getArticleListPage("article/getArticleListPage", {
        current: this.pageConf.pageCode,
        size: this.pageConf.pageSize,
      }).then((res) => {
        this.articleList = res.data.records;
        this.pageConf.totalPage = res.data.total;
      });
    },
    changeArticleListDetail() {
      if (this.isIndexPage) {
        this.getArticleListPage();
      } else {
        this.getArticleListByCategoryId();
      }
    },
  },
  created() {
    this.changeArticleListDetail();
  },
  watch: {
    $route: {
      handler() {
        this.id = this.$route.params.categoryid;
        this.changeArticleListDetail();
      },
      deep: true,
    },
  },
};
</script>

<style scoped>
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
  font-weight: 500;
}

.live-desc {
  color: #9ea7b4;
  margin-top: 20px;
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
  font-size: 13px;
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
}

.article:hover .live-title {
  color: #409eff;
  padding-left: 10px;
}

/* 分页 */
.page {
  margin-top: 40px;
}

.title-l {
  position: absolute;
  background: #448ef6;
  top: 4%;
  left: -1px;
  width: 5px;
  height: 25px;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
  margin: 3% 0 0 0;
}

.post-list:hover .vodlist_top {
  right: 0.35rem;
}

.category {
  font-style: normal;
  display: inline-block;
  text-overflow: ellipsis;
  white-space: nowrap;
  position: absolute;
  font-size: 0.3rem;
  color: #fff;
  top: 0.35rem;
  right: -10rem;
  z-index: 999;
  transition: all 0.4s ease-in-out;
}
.category .gotocategory {
  text-align: center;
  width: auto;
  height: 25px;
  line-height: 25px;
  padding: 0.4rem 0.8rem;
  border-radius: 4px;
  background: #448ef6;
}
.article:hover .category {
  z-index: 999;
  right: 0;
}
</style>
<style>
.el-image__inner {
  vertical-align: top;
  height: 150px;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 50%;
  border-radius: 4px;
}
</style>