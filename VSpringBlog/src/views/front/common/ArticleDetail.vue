<template>
  <div class="content" ref="content123r">
    <div id="art-main">
      <div class="art-title">{{articleDetail.title}}</div>
      <div class="user-des">
        <div class="user-rdes">
          <div class="otherdesc">
            <span class="username">author:{{articleDetail.userName}}</span>
            <span class="art-tag">
              <i class="el-icon-collection-tag"></i>
              <span class="tagspan" v-for="tags in articleDetail.sysTagsList" :key="tags.id">{{tags.tagsName}}</span>
            </span>
            <span class="art-time">
              <i class="el-icon-date"></i>
              <span>{{articleDetail.creatTime}}</span>
            </span>
            <span class="art-commentscount">
              <i class="el-icon-chat-line-round"></i>
              <span>123</span>
            </span>
          </div>
        </div>
      </div>
      <el-divider></el-divider>

      <div class="articlede-body">
        <div class="markdown-body">

          <div ref="content" v-html="articleDetail.htmlText" v-viewer="{navbar:false,title:false}">
          </div>
          <!-- <mavon-editor codeStyle="monokai" v-model="content" :ishljs="true" /> -->
<!--          <mavon-editor-->
<!--            ref="content"-->
<!--            :subfield="false"-->
<!--            :boxShadow="false"-->
<!--            defaultOpen="preview"-->
<!--            :codeStyle="codeStyle"-->
<!--            :ishljs="true"-->
<!--            v-model="content"-->
<!--            :toolbarsFlag="false"-->
<!--            :imageClick="$imageClick"-->
<!--            v-viewer="{navbar:false,title:false}"-->
<!--          />-->
        </div>
        <div class="arttags"></div>
      </div>
      <el-divider></el-divider>

      <div class="metas">
        <div class="item">
          <div class="title">
            <i class="el-icon-s-flag"></i>
            <span>相关标签:</span>
            <span v-for="tags in articleDetail.sysTagsList" :key="tags.id">{{tags.tagsName}}</span>
            
          </div>
        </div>
        <div class="item">
          <div class="title">
            <i class="el-icon-position"></i>

            <span>永久地址:</span>
            <span>http://localhost:8080/#/articleDetail/1</span>
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
  </div>
</template>
<script>
  import {getArticleContentByUserId} from "@/api/article";

  export default {
    name: "articleDetail",
    data() {
      return {
        articleDetail: "",
        codeStyle: "monokai-sublime", //主题
      };
    },
    methods: {
      getContent() {
        getArticleContentByUserId("article/getArticleContentByUserId", {
          articleId: this.$route.params.id,
        }).then((res) => {
          this.articleDetail = res.data;
        });
      },
      show() {
        const viewer = this.$el.querySelector("img").$viewer;
        viewer.show();
      },
      $imageClick() {
        return false;
      },
      makeLink(h) {
        var link = document.createElement('li')
        window.arst = h
        var text = [].slice.call(h.childNodes).map(function (node) {
          if (node.nodeType === Node.TEXT_NODE) {
            return node.nodeValue
          } else if (['CODE', 'SPAN'].indexOf(node.tagName) !== -1) {
            return node.textContent
          } else {
            return ''
          }
        }).join('').replace(/\(.*\)$/, '')
        link.innerHTML =
          '<a class="section-link" data-scroll href="#' + h.id + '">' +
          this.htmlEscape(text) +
          '</a>'
        return link
      },
      htmlEscape(text) {
        return text
          .replace(/&/g, '&amp;')
          .replace(/"/g, '&quot;')
          .replace(/'/g, '&#39;')
          .replace(/</g, '&lt;')
          .replace(/>/g, '&gt;')
      },
      collectH3s(h) {
        var h3s = []
        var next = h.nextSibling
        while (next && next.tagName !== 'H2') {
          if (next.tagName === 'H3') {
            h3s.push(next)
          }
          next = next.nextSibling
        }
        return h3s
      },
      getNavTree() {
        var self = this
        var sectionContainer
        var each = [].forEach
        // 获取dom
        var content = this.$refs.content123r;
        console.log(content)
        // 提取h2标签
        var headers = content.querySelectorAll('a')
        console.log(headers)
        sectionContainer = document.createElement('ul')
        each.call(headers, function (h) {
          sectionContainer.appendChild(self.makeLink(h))
        })
        return sectionContainer.innerHTML
      }
    },
    created() {
      this.getContent();
    },
    mounted() {
      this.sectionContainer = this.getNavTree()
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
  }

  .markdown-body pre {
    position: relative;
    padding: 28px 0 0 0 !important;
    background: #21252b;
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
    padding-top: 30px;
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
    padding: 0 15px;
    word-wrap: break-word;
    /* line-height: 26px; */
  }

  .markdown-body {
    z-index: auto;
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
