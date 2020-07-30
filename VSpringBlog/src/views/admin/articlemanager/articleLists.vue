<template>
  <el-row>
    <el-col
      :xs="7"
      :sm="7"
      :md="5"
      :lg="3"
      :xl="4"
      v-for="article in articleList"
      :key="article.id"
      :offset="1"
    >
      <el-card class="articlecard" :body-style="{ padding: '0px' }" style="margin-bottom:20px;">
        <img src="https://iazuresky.com/usr/uploads/2019/10/1159780893.jpg" class="image" />
        <div style="padding: 14px;">
          <div class="bottom clearfix">
            <div class="articletitle">
              <i class="fa fa-bookmark" aria-hidden="true"></i>
              <span>{{article.title}}</span>
            </div>
            <div class="pushtime">
              <i class="fa fa-calendar" aria-hidden="true"></i>
              <span>2020-07-21T03:10:31</span>
            </div>
            <div class="tags">
              <i class="fa fa-tags" aria-hidden="true"></i>
              <span>vue</span>、
              <span>vue</span>、
              <span>vue</span>、
              <span>vue</span>
            </div>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>
<style scoped>
.articlecard {
  cursor: pointer;
}
.articlecard:hover {
  box-shadow: 0 6px 9px 2px rgba(0, 0, 0, 0.15);
}

.pushtime span,
.tags span {
  font-size: 12px;
  color: #999;
}
.articletitle,
.pushtime,
.tags {
  max-height: 22px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.articletitle span {
  font-size: 13px;
}

.articletitle i,
.pushtime i,
.tags i {
  font-size: 12px;
  padding: 5px 2px;
  color: red;
}
.pushtime i {
  font-size: 13px;
}
@media only screen and (min-width: 1200px) {
  .el-col-lg-3 {
    width: 14.2%;
  }
  .el-col-offset-1 {
    margin-left: 2.16667%;
  }
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.image {
  width: 100%;
  display: block;
  height: 60%;
}
</style>

<script>
import { getArticleListPage } from "@/api/article";

export default {
  data() {
    return {
      currentDate: new Date(),
      articleList: "",
    };
  },
  methods: {
    getArticleListPage() {
      getArticleListPage("article/getArticleListPage", {
        current: 1,
        size: 5,
      }).then((res) => {
        this.articleList = res.data.records;
      });
    },
  },
  created(){
    this.getArticleListPage();
  }
};
</script>

