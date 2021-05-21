<template>
  <div class="content animated fadeIn">
    <div id="art-main">
      <loading v-if="isLoading"></loading>
      <div class="art-title animated fadeInDown">{{ articleDetail.title }}</div>
      <div class="user-des">
        <div class="user-rdes">
          <div class="otherdesc">
            <span class="username">author:{{ articleDetail.userName }}</span>
            <span class="art-tag">
              <i class="el-icon-collection-tag"></i>
              <span
                class="tagspan"
                v-for="tags in articleDetail.sysTagsList"
                :key="tags.id"
                >{{ tags.tagsName }}</span
              >
            </span>
            <span class="art-time">
              <i class="el-icon-date"></i>
              <span>{{ articleDetail.creatTime }}</span>
            </span>
            <span class="art-commentscount">
              <i class="el-icon-chat-line-round"></i>
              <span>{{ articleDetail.commentCounts }}</span>
            </span>
            <span class="art-commentscount" style="cursor: pointer">
              <span @click="gotoedit()">
                <i class="el-icon-edit">编辑</i>
              </span>
            </span>
            <span class="art-commentscount">
              <i class="fa fa-eye">{{ articleDetail.pageView }}次阅读</i>
            </span>
          </div>
        </div>
      </div>
      <el-divider></el-divider>

      <div class="articlede-body animated fadeInUp">
        <div class="markdown-body">
          <div
            id="article-main-page"
            ref="content"
            v-html="articleDetail.htmlText"
            v-viewer="{ navbar: false, title: false }"
          ></div>
        </div>
        <!-- <div class="arttags"></div> -->
      </div>
      <el-divider></el-divider>

      <div class="metas">
        <div class="item">
          <div class="title">
            <i class="el-icon-s-flag"></i>
            <span>相关标签:</span>
            <span v-for="tags in articleDetail.sysTagsList" :key="tags.id">{{
              tags.tagsName
            }}</span>
          </div>
        </div>
        <div class="item">
          <div class="title">
            <i class="el-icon-share"></i>
            <span>版权声明:</span>
            <span
              >自由转载-署名-非商业性使用 | Creative Commons BY-NC 3.0 CN
              著作权归作者所有。</span
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { getArticleContentByArticleId } from "@/api/article";
import Loading from "@/views/front/common/Loading";
import TOC from "@/utils/MarkdownToc";
import TocScrollSpy from "@/utils/TocScrollSpy";

export default {
  name: "articleDetail",
  components: {
    Loading,
  },
  data() {
    return {
      articleDetail: "",
      codeStyle: "monokai-sublime",
      articlecontent: "",
      isLoading: false,
    };
  },
  methods: {
    addCodeLineNumber() {
      // 添加行号
      let blocks = this.$refs.content.querySelectorAll("pre .hljs code");
      blocks.forEach((block) => {
        // 去前后空格并添加行号
        block.innerHTML =
          "<ol><li>" +
          block.innerHTML
            .replace(/(^\s*)|(\s*$)/g, "")
            .replace(/\n/g, "\n</li><li>") +
          "\n</li></ol>";
      });
    },
    getContent() {
      let _this = this;
      _this.isLoading = true;
      getArticleContentByArticleId("article/getArticleContentByArticleId", {
        articleId: this.$route.params.id,
      }).then((res) => {
        if (res.data === undefined || res.data === null) {
          _this.$router.push("/404");
        }
        this.articleDetail = res.data;
        this.articlecontent = res.data.mrdText;
        _this.isLoading = false;
        this.$nextTick(function () {
          this.addCodeLineNumber();
          this.refreshDiectory();
          document.title = this.articleDetail.title + "wl´s blog";
        });
      });
    },
    show() {
      const viewer = this.$el.querySelector("img").$viewer;
      viewer.show();
    },
    $imageClick() {
      return false;
    },
    gotoedit() {
      localStorage.setItem("path", this.$route.path);
      this.$router.push("/article/editArticle/" + this.articleDetail.id);
    },
    refreshDiectory() {
      new TOC("article-main-page", {
        level: 5,
        top: 200,
        class: "list",
        targetId: "side-toc",
      });
      new TocScrollSpy("article-main-page", "side-toc", {
        spayLevel: 5,
        articleMarginTop: 0,
      });
    },
  },
  created() {
    this.getContent();
  },
  watch: {
    $route: {
      handler() {
        this.getContent();
      },
      deep: true,
    },
  },
};
</script>
<style>
.toTop {
  padding: 10px;
  box-shadow: 0px 0px 5px black;
}

.markdown-body pre > code {
  position: relative;
  left: 14px;
  margin-bottom: 1em !important;
}

.markdown-body img {
  box-shadow: 0 10px 20px 0 rgba(0, 0, 0, 0.4);
  cursor: pointer;
}

.markdown-body pre {
  position: relative;
  padding: 28px 0 0 0 !important;
  border-radius: 5px !important;
  font: 16px/22px "Consolas" !important;
  line-height: 1.6 !important;
  margin-bottom: 1.6em !important;
  max-width: 100% !important;
  overflow: auto;
  text-shadow: none !important;
  color: #000;
  padding-top: 30px;
  box-shadow: 0 2px 10px 0 rgba(0, 0, 0, 0.2);
}
.markdown-body pre > code {
  font-size: 14px !important;
  font-weight: 500;
}
.markdown-body pre::before {
  content: " ";
  position: absolute;
  -webkit-border-radius: 50%;
  border-radius: 50%;
  background: #fc625d;
  width: 12px;
  height: 12px;
  left: 12px;
  margin-top: -18px;
  -webkit-box-shadow: 20px 0 #fdbc40, 40px 0 #35cd4b;
  box-shadow: 20px 0 #fdbc40, 40px 0 #35cd4b;
  z-index: 2;
}
.markdown-body .hljs ol li:hover {
  background-color: rgba(255, 255, 255, 0.1);
  cursor: pointer;
}
</style>
<style scoped>
#art-main {
  border: 1px solid #dcdee2;
  border-color: #e8eaec;
  background-color: #fff;
  overflow: hidden;
  position: relative;
}

#art-main .art-title {
  padding-top: 25px;
  padding-bottom: 10px;
  text-align: center;
  color: #555;
  font-size: 21px;
  text-shadow: 0 1px 3px rgba(88, 88, 88, 0.2);
  line-height: 30px;
}

.user-des {
  padding-left: 30px;
  text-align: center;
  z-index: 999;
}

.user-rdes .art-time,
.user-rdes .username,
.user-rdes .art-tag,
.user-rdes .art-commentscount {
  color: #969696;
  font-size: 13px;
  margin-right: 10px;
}

.tagspan {
  padding-left: 5px;
  cursor: pointer;
}

.user-rdes .username {
  margin-bottom: 10px;
}

.articlede-body {
  padding: 5px 28px;
  word-wrap: break-word;
  /* line-height: 26px; */
}

.markdown-body {
  z-index: auto;
}

.content {
  min-height: 100px;
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
<style>
.articlede-body.markdown-body .v-note-wrapper {
  z-index: inherit;
  border: inherit;
  min-height: 100px !important;
}

.articlede-body .markdown-body .v-note-panel .v-note-show .v-show-content {
  background-color: #fff !important;
}

.el-divider--horizontal {
  margin: 10px 0 5px 0;
}

.v-note-wrapper {
  z-index: auto !important;
}
</style>
