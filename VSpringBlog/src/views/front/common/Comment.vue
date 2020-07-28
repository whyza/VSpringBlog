<!--参考https://blog.csdn.net/zLanaDelRey/article/details/100997792-->
<template>
  <div style="position: relative">
    <!-- <div class="reply">
      <el-avatar class="header-img" :size="40" :src="myHeader"></el-avatar>
      <div class="reply-info">
        <el-popover placement="right" width="400" trigger="click">
          <commentEdit></commentEdit>
          <el-button slot="reference">点击评论</el-button>
        </el-popover>
      </div>
    </div>-->
    <!--一级评论-->
    <div v-for="(item,index) in comments" :key="index" class="author-title reply-parent">
      <el-avatar class="header-img" :size="40" :src="item.userIcon"></el-avatar>
      <div class="author-info">
        <span class="author-name">{{item.id}}----{{item.userName}}</span>
        <span class="reply-time">{{item.time}}</span>
      </div>
      <!--点击回复-->
      <div class="icon-btn">
        <el-popover placement="top-end" width="400" trigger="click">
          <commentEdit></commentEdit>
          <i slot="reference" class="el-icon-s-comment" style="cursor: pointer">回复</i>
        </el-popover>
      </div>
      <div class="comment-box">
        <div>
          <span class="comment-content">{{item.content}}</span>
        </div>
      </div>
      <!--回复内容-->
      <commentReply :sysCommentsChild="item.sysCommentsChild" />
    </div>
  </div>
</template>

<script>
import { mavonEditor } from "mavon-editor";
import commentEdit from "@/views/front/common/commentEdit";
import { queryComment } from "@/api/comment";
import commentReply from "@/views/front/common/commentReply";

export default {
  name: "Comment",
  components: {
    mavonEditor,
    commentEdit,
    commentReply,
  },
  data() {
    return {
      editorVisible: true,
      btnShow: false,
      parentbtnShow: false,
      index: "0",
      replyComment: "",
      myName: "Lana Del Rey",
      myHeader:
        "https://ae01.alicdn.com/kf/Hd60a3f7c06fd47ae85624badd32ce54dv.jpg",
      myId: 19870621,
      to: "",
      toId: -1,
      comments: [],
    };
  },
  methods: {
    queryCommentByArticleId() {
      var _this = this;

      queryComment("/comments/queryComments", {
        articleId: _this.$route.params.id,
      }).then((res) => {
        console.log(res);
        _this.comments = res.data;
      });
    },
    dateStr(date) {
      //获取js 时间戳
      var time = new Date().getTime();
      //去掉 js 时间戳后三位，与php 时间戳保持一致
      time = parseInt((time - date) / 1000);
      //存储转换值
      var s;
      if (time < 60 * 10) {
        //十分钟内
        return "刚刚";
      } else if (time < 60 * 60 && time >= 60 * 10) {
        //超过十分钟少于1小时
        s = Math.floor(time / 60);
        return s + "分钟前";
      } else if (time < 60 * 60 * 24 && time >= 60 * 60) {
        //超过1小时少于24小时
        s = Math.floor(time / 60 / 60);
        return s + "小时前";
      } else if (time < 60 * 60 * 24 * 30 && time >= 60 * 60 * 24) {
        //超过1天少于30天内
        s = Math.floor(time / 60 / 60 / 24);
        return s + "天前";
      } else {
        //超过30天ddd
        var date = new Date(parseInt(date));
        return (
          date.getFullYear() +
          "/" +
          (date.getMonth() + 1) +
          "/" +
          date.getDate()
        );
      }
    },
  },
  created() {
    this.queryCommentByArticleId();
  },
};
</script>
<style>
.reply-box-popover .el-form-item__content {
  margin-left: 0 !important;
}

.reply-parent {
  margin-bottom: 10px;
  border: 1px dashed rgba(0, 0, 0, 0.2);
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

.author-title:not(:last-child) {
  border-bottom: 1px solid rgba(178, 186, 194, 0.3);
}

.author-title {
  padding: 20px 10px 10px 20px;
  background-color: #fff;
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
  width: 30%;
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
.comment-box {
  margin: 0 50px;
}
.comment-box.reply-comment {
  background: rgba(184, 205, 255, 0.1);
  border-radius: 3px;
  display: block;
  line-height: 30px;
  padding-left: 8px;
}
.comment-box span {
  font-size: 13px;
  color: #000;
}
.reply-box {
  padding: 10px 0 0 30px;
}
</style>
