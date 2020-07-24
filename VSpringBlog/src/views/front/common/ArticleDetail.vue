<template>
  <div class="content">
    <div id="art-main">
      <div class="art-title">nginx反向代理解决跨域</div>
      <div class="user-des">
        <img class="user-limg" src="https://iazuresky.com/usr/uploads/2019/10/3486836713.jpg" alt />
        <div class="user-rdes">
          <div class="username">whyzaa</div>
          <div class="otherdesc">
            <span class="art-time">2019.05.22 19:32:56</span>
            <span class="art-commentscount">
              <i class="el-icon-chat-line-round"></i>123
            </span>
          </div>
        </div>
      </div>
      <el-divider></el-divider>

      <div class="articlede-body">
        <div class="markdown-body">
            <mavon-editor
              v-model="content"
              :subfield="false"
              :boxShadow="false"
              defaultOpen="preview"
              :toolbarsFlag="false"
            />
        </div>
        <div class="arttags"></div>
      </div>
    </div>
    <div class="metas">
      <div class="item">
        <div class="title">
          <i class="el-icon-s-flag"></i>
          <span>相关标签:</span>
        </div>
      </div>
      <div class="item">
        <div class="title">
          <i class="el-icon-position"></i>

          <span>永久地址:</span>
          <span>http://localhost:8080/#/ArticleDetail/1</span>
        </div>
      </div>
      <div class="item">
        <div class="title">
          <i class="el-icon-share"></i>
          <span>版权声明:</span>
          <span>自由转载-署名-非商业性使用 | Creative Commons BY-NC 3.0 CN 著作权归作者所有。</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { getArticleContentByUserId } from "@/api/article";
import { mavonEditor } from "mavon-editor";

export default {
  name: "ArticleDetail",
  components: {
    mavonEditor
  },
  data() {
    return {
      content: "",
    };
  },
  methods: {
    getContent() {
      getArticleContentByUserId("article/getArticleContentByUserId", {
        articleId: this.$route.params.id,
      }).then((res) => {
        this.content = res.result.htmlText;
      });
    },
  },
  created() {
    this.getContent();
  },
};
</script>
<style>
#art-main {
  border: 1px solid #dcdee2;
  border-color: #e8eaec;
  background-color: #fff;
  overflow: hidden;
}
#art-main .art-title {
  padding-top: 30px;
  padding-bottom: 10px;
  text-align: center;
  /* border-bottom: 1px solid #f4f4f4; */
  color: #555;
  font-size: 21px;
  text-shadow: 0 1px 3px rgba(88, 88, 88, 0.2);
  line-height: 30px;
}

.user-des {
  /* padding: 10px 0; */
  padding-left: 30px;
  /* border-bottom: 1px solid #f4f4f4; */
}
.user-des .user-limg {
  display: block;
  border-radius: 50%;
  border: 1px solid #eee;
  min-width: 48px;
  min-height: 48px;
  width: 48px;
  height: 48px;
  float: left;
  margin-left: 10px;
}
.user-des .user-rdes {
  margin-left: 72px;
}
.user-rdes .username {
  font-size: 16px;
  font-weight: 400;
}
.user-rdes .art-time,
.user-rdes .art-commentscount {
  color: #969696;
  font-size: 13px;
  margin-right: 10px;
}
.user-rdes .username {
  margin-bottom: 10px;
}
.articlede-body {
  padding: 0 30px;
  word-wrap: break-word;
  /* line-height: 26px; */
}
.content {
  margin-bottom: 10px;
}
.content .metas {
  padding: 1rem;
  background-color: #fff;
}
.content .metas > .item {
  margin-bottom: 8px;
  padding-left: 1rem;
  word-break: break-all;
}
.content .metas .item .title {
  color: #555;
  font-size: 13px;
}
.content .metas .item .title span {
  padding-right: 10px;
}
.content .metas .item .title i {
  color: rgb(255, 0, 0);
  font-size: 14px;
  padding-right: 10px;
}
</style>