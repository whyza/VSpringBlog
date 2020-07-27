<template>
  <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" >
    <div class="comment-container">
      <div class="Comment-line">评论</div>
      <div class="comment-list">
        <li id="li-comment" class="comment-body comment-parent comment-odd comment-by-author">
          <div id="comment">
            <div class="comment-author">
              <img
                class="avatar"
                src="https://iazuresky.com/usr/uploads/2019/10/3486836713.jpg"
                alt="wl´s blog"
                width="40"
                height="40"
              />
              <cite class="fn">
                <a href="/" target="_blank" rel="external nofollow">wl´s blog</a>
              </cite>
            </div>
            <div class="comment-meta">
              <span href="/">October 18th, 2019 at 10:44:20 AM</span>
              <span class="comment-reply">
                <span>回复</span>
              </span>
            </div>
            <div class="comment-content">
              <span>2</span>
            </div>
          </div>

          <!-- 回复 -->
          <div class="comment-children">
            <div class="comment-list">
              <li
                id="li-comment-75"
                class="comment-body comment-child comment-level-odd comment-odd comment-by-author"
              >
                <div id="comment-75">
                  <div class="comment-author">
                    <img
                      class="avatar"
                      src="https://iazuresky.com/usr/uploads/2019/10/3486836713.jpg"
                      alt="wl´s blog"
                      width="40"
                      height="40"
                    />
                    <cite class="fn">
                      <a
                        href="https://iazuresky.com"
                        target="_blank"
                        rel="external nofollow"
                      >wl´s blog</a>
                    </cite>
                  </div>
                  <div class="comment-meta">
                    <span>November 7th, 2019 at 08:55:21 PM</span>
                    <span class="comment-reply">
                      <span>回复</span>
                    </span>
                  </div>
                  <div class="comment-content">
                    <span>
                      回复
                      <span style="color:#ff8300;">ChangMQ267</span>： 3
                    </span>
                  </div>
                </div>
              </li>
            </div>
          </div>
        </li>
      </div>
      <div class="comment">
        <div class="addComment">添加评论</div>

        <el-form :model="commentform" ref="commentform" class="commentform">
          <el-form-item prop="desc">
            <el-col :xs="24" :sm="24" :md="15" :lg="15" :xl="1">
              <el-input
                type="textarea"
                :autosize="{ minRows: 4.48, maxRows: 5 }"
                show-word-limit
                maxlength="200"
                v-model="commentform.desc"
                style="margin-top:10px;"
              ></el-input>
            </el-col>
            <el-col
              :xs="8"
              :sm="8"
              :md="6"
              :lg="6"
              :xl="1"
              style="padding-top:4px;padding-left:10px"
            >
              <el-input
                size="small"
                placeholder="昵称*"
                suffix-icon="el-icon-date"
                v-model="username"
              ></el-input>
              <el-input size="small" placeholder="邮箱*" suffix-icon="el-icon-date" v-model="email"></el-input>

              <el-input  class="pl20"  size="small" placeholder="网址*" suffix-icon="el-icon-date" v-model="address">
                <template slot="prepend">Http://</template>
              </el-input>
            </el-col>
            <el-col
              :xs="6"
              :sm="6"
              :md="3"
              :lg="3"
              :xl="1"
              style="text-align:center;vertical-align:middle"
            >
              <img
                src="https://iazuresky.com/usr/uploads/2019/10/3486836713.jpg"
                style="width: 75px;height: 75px;"
                alt
              />
              <el-button type="primary" size="mini" @click="submitForm('commentform')">添加评论</el-button>
            </el-col>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </el-col>
</template>

<script>
export default {
  name: "Comment",
  data() {
    return {
      commentform: {
        desc: ""
      },
      username: "",
      email: "",
      address: ""
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert("submit!");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>
<style lang="scss" scoped>
.addComment,
.Comment-line {
  font-size: 14px;
  padding: 5px 15px;
  border-left: 5px solid #1a8cff;
  text-shadow: 0 1px 3px rgba(88, 88, 88, 0.1);
}
.comment {
  background-color: #fff;
  padding: 10px 0;
  margin-top: 10px;
}
.commentform {
  padding: 0 25px;
}
.el-form-item {
  margin-bottom: 0;
}
.comment-container {
  background-color: #fff;
  padding: 10px 10px;
}
.comment-list #li-comment {
  padding: 15px;
  border: 1px dashed rgba(0, 0, 0, 0.2);
}
.comment-list li {
  margin-top: 15px;
}
.comment-list #li-comment:hover {
  border: 1px dashed rgba(0, 128, 255, 0.6);
}
.comment-list li > div > div > img {
  border-radius: 0;
  width: 40px;
  height: 40px;
}
.comment-author .avatar {
  float: left;
  margin-right: 10px;
  border-radius: 50%;
  box-shadow: 0 0px 10px rgba(0, 0, 0, 0.45);
  width: 32px;
  height: 32px;
}
.comment-author cite {
  font-weight: bold;
  font-style: normal;
}
cite.fn {
  font-size: 12px;
}
.comment-author a {
  color: #166;
  display: inline-block;
  font-size: 13px;
  transition: all 0.3s ease-in-out;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -ms-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  text-decoration: none;
}
.comment-author a:hover {
  color: rgba(77, 136, 255, 1);
}
.comment-meta {
  margin-top: 8px;
  display: flex;
}
.comment-meta span,
.comment-meta a {
  font-size: 12px;
  color: #aaa;
}
.comment-content {
  padding-top: 3px;
  word-wrap: break-word;
  padding-left: 40px;
}
.comment-content {
  padding-left: 5px;
}
.comment-content > span {
  padding: 5px;
  line-height: 18px;
  display: block;
  font-size: 12px;
  border-top: 1px dashed rgba(23, 0, 247, 0.05);
}
.respond {
  margin-top: 30px;
}
.comment-list {
  padding: 0 25px;
}
.comment-list:hover .comment-reply {
  opacity: 1;
}
.comment-reply {
  opacity: 0;
  padding-left: 5px;
  transition: all 0.3s ease-in-out;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -ms-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
}
.comment-reply span {
  font-size: 12px;
  color: #ff8300;
  transition: all 0.3s ease-in-out;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -ms-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
}
.comment-reply span:hover {
  color: rgba(0, 0, 102, 0.8);
}
.comment-by-author > div > div.comment-content > span {
  background: rgba(184, 205, 255, 0.1);
  border-radius: 3px;
  display: block;
  line-height: 18px;
}

</style>
<style>
.pl20 .el-input-group__prepend{
  padding: 0 3px;
}
</style>

