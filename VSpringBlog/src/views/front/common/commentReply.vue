<template>
  <div class="reply-box">
    <div
      v-for="(reply,replyChildrenIndex) in sysCommentsChild"
      :key="replyChildrenIndex"
      class="author-title"
    >
      <el-avatar class="header-img" :size="40" :src="reply.userIcon"></el-avatar>
      <div class="author-info">
        <span class="author-name">{{reply.id}}----{{reply.userName}}</span>
        <span class="reply-time">{{reply.time}}</span>
      </div>
      <div class="icon-btn">
        <span>
          <i class="iconfont el-icon-s-comment"></i>
          {{reply.commentNum}}
        </span>
      </div>
      <div class="comment-box reply-comment">
        <div>
          <span>回复 {{reply.parentId}}:</span>
          <span class="comment-content">{{reply.content}}</span>
        </div>
      </div>
      <div class="reply-box">
        <commentReply
          v-if="reply.sysCommentsChild"
          :sysCommentsChild="reply.sysCommentsChild"
        ></commentReply>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "commentReply",
  props: {
    sysCommentsChild: {
      type: Array,
      default: [],
    },
  },
};
</script>
<style>
</style>