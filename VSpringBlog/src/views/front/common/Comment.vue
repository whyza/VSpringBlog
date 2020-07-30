<template>
  <div style="position: relative" id="comment">
    <div v-for="(item,index) in comments" :key="index" class="reply-parent">
      <div class="reply-box">
        <div class="reply-box-children">
          <el-avatar shape="square" :size="40" class="header-img" :src="item.userIcon"></el-avatar>
          <div class="author-info">
            <span class="author-name">{{item.id}}----{{item.userName}}</span>
            <span class="reply-time">{{item.createTime}}</span>
          </div>
          <!--点击回复-->
          <div :id="'scollto'+item.id" class="icon-btn" @click="scrollTopComment($event)">
            <i
              slot="reference"
              :data-parentid="item.id"
              class="el-icon-s-comment"
              style="cursor: pointer"
            >回复</i>
          </div>
          <div class="comment-box">
            <div>
              <mavon-editor
                style="min-height: 25px;"
                :subfield="false"
                :boxShadow="false"
                defaultOpen="preview"
                :codeStyle="codeStyle"
                :ishljs="true"
                v-model="item.content"
                :toolbarsFlag="false"
                :imageClick="$imageClick"
                v-viewer="{navbar:false,title:false}"
              />
            </div>
          </div>
        </div>
      </div>
      <!--回复内容-->
      <div
        v-if="item.sysCommentsChild!=undefined&& item.sysCommentsChild.length!=0"
        class="reply-box"
      >
        <div class="child-box">
          <div
            v-for="(reply,replyChildrenIndex) in item.sysCommentsChild"
            :key="replyChildrenIndex"
            class="reply-box-children"
          >
            <el-avatar class="header-img" shape="square" :size="40" :src="reply.userIcon"></el-avatar>
            <div class="author-info">
              <span class="author-name">{{reply.id}}----{{reply.userName}}</span>
              <span class="reply-time">{{reply.createTime}}</span>
            </div>
            <div :id="'scollto'+reply.id" class="icon-btn" @click="scrollTopComment($event)">
              <i
                slot="reference"
                :data-parentid="reply.id"
                class="el-icon-s-comment"
                style="cursor: pointer"
              >回复</i>
            </div>
            <div class="comment-box reply-comment">
              <div>
                <span>回复 {{reply.parentId}}:</span>
                <mavon-editor
                  style="min-height: 25px"
                  :subfield="false"
                  :boxShadow="false"
                  defaultOpen="preview"
                  :codeStyle="codeStyle"
                  :ishljs="true"
                  v-model="reply.content"
                  :toolbarsFlag="false"
                  :imageClick="$imageClick"
                  v-viewer="{navbar:false,title:false}"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <commentEdit :parentId="parentId" />
  </div>
</template>

<script>
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import commentEdit from "@/views/front/common/commentEdit";
import { queryComment } from "@/api/comment";

export default {
  name: "comment",
  components: {
    mavonEditor,
    commentEdit,
  },
  data() {
    return {
      codeStyle: "monokai",
      editorVisible: true,
      comments: [],
      parentId: 0,
    };
  },
  methods: {
    show() {
      const viewer = this.$el.querySelector("img").$viewer;
      viewer.show();
    },
    $imageClick() {
      return false;
    },
    queryCommentByArticleId() {
      var _this = this;
      queryComment("/comments/queryComments", {
        articleId: _this.$route.params.id,
      }).then((res) => {
        _this.comments = res.data;
      });
    },
    scrollTopComment(event) {
      this.$nextTick(() => {
        document
          .getElementById("commentform")
          .scrollIntoView({ block: "start", behavior: "smooth" });
        this.parentId = event.target.dataset.parentid;
      });
    },
  },
  mounted() {
    this.$nextTick(function () {
      this.queryCommentByArticleId();
    });
  },
};
</script>
<style>
.reply-box {
  padding: 5px;
}
.reply-box-popover .el-form-item__content {
  margin-left: 0 !important;
}

.reply-parent {
  margin-bottom: 10px;
  border: 1px dashed rgba(0, 0, 0, 0.2);
  overflow-x: auto;
  padding: 20px 10px 10px 20px;
  background-color: #fff;
}
.header-img {
  display: inline-block;
  vertical-align: top;
  box-shadow: 0 0px 10px rgba(0, 0, 0, 0.45);
}

.reply-btn-box {
  height: 25px;
  margin: 10px 0;
}

.reply-btn {
  position: relative;
  float: right;
  margin-right: 15px;
}

.my-comment-reply {
  margin-left: 50px;
}

.reply-time {
  color: #aaa;
  font-size: 12px;
}
.author-info {
  display: inline-block;
  margin-left: 5px;
  width: 60%;
  height: 40px;
  line-height: 20px;
}

.author-info span {
  display: block;
  cursor: pointer;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.author-name {
  color: #166;
  font-size: 13px;
  font-weight: bold;
}

.icon-btn {
  /* width: 30%; */
  padding: 0 !important;
  float: right;
}
.icon-btn {
  cursor: pointer;
  font-size: 13px;
  transition: all 0.3s ease-in-out;
}
.icon-btn:hover {
  color: orange;
  transition: all 0.3s ease-in-out;
}
.child-box {
  margin: 0 30px;
  padding: 6px;
  border: 1px dashed rgba(0, 0, 0, 0.2);
}
.comment-box {
  margin: 0 50px;
}
.comment-box.reply-comment {
  border-radius: 3px;
  line-height: 25px;
}
.comment-box span {
  background: rgba(184, 205, 255, 0.2);
  padding-left: 10px;
  font-size: 13px;
  color: #000;
  float: left;
}
.reply-box .v-note-wrapper {
  background: rgba(184, 205, 255, 0.1);
}

#comment
  .comment-box
  .reply-comment
  .v-note-wrapper
  .v-note-panel
  .v-note-show
  .v-show-content,
#comment
  .comment-box
  .reply-comment
  .v-note-wrapper
  .v-note-panel
  .v-note-show
  .v-show-content-html {
  padding: 0 0 0 5px;
  font-size: 13px;
  /* z-index: -1; */
}
#comment .markdown-body blockquote,
#comment .markdown-body dl,
#comment .markdown-body ol,
#comment .markdown-body p,
#comment .markdown-body pre,
#comment .markdown-body table,
#comment .markdown-body ul {
  margin-bottom: 0px;
}
.articlede-body .v-note-wrapper {
  border: none;
}
#comment .markdown-body {
  line-height: 1.5;
  border: none !important;
}
#comment
  .comment-box
  .v-note-wrapper
  .v-note-panel
  .v-note-show
  .v-show-content,
#comment
  .comment-box
  .v-note-wrapper
  .v-note-panel
  .v-note-show
  .v-show-content-html {
  font-size: 13px;
  padding: 0 0 0 5px;
  z-index: auto;
}
.comment-box {
  cursor: pointer;
}
/* v-show-content scroll-style scroll-style-border-radius */
.comment-box.reply-comment
  .v-note-panel
  .v-show-content.scroll-style.scroll-style-border-radius {
  background: rgba(184, 205, 255, 0.1) !important;
}
.reply-box-children {
  padding-top: 8px;
  padding-bottom: 15px;
}
.child-box .reply-box-children:not(:last-child) {
  border-bottom: 1px dashed rgba(0, 0, 0, 0.2);
}
</style>
