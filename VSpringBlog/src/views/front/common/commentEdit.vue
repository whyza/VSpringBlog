<template>
  <div>
    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
      <div class="reply-box-popover" id="commentform" ref="commentform">
        <mavon-editor
          ref="md"
          style="min-height: 60px"
          :subfield="false"
          :ishljs="true"
          v-model="content"
          :toolbarsFlag="true"
          :toolbars="toolbar"
          :placeholder="''+this.replyName"
        />
        <el-form
          :model="commentForm"
          status-icon
          :rules="rules"
          ref="commentForm"
          label-width="100px"
        >
          <el-col :xs="9" :sm="9" :md="5" :lg="5" :xl="3" class="userName">
            <el-form-item prop="userName">
              <el-input
                v-model="commentForm.userName"
                autocomplete="off"
                prefix-icon="el-icon-user"
                placeholder="昵称*用户名"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="15" :sm="15" :md="5" :lg="5" :xl="3" class="email">
            <el-form-item prop="qq" style="margin-left:10px;margin-right:10px">
              <el-input
                v-model="commentForm.qq"
                autocomplete="off"
                prefix-icon="el-icon-wallet"
                placeholder="QQ"
                @blur="setUserAvater()"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :xs="24" :sm="24" :md="9" :lg="9" :xl="3" class="userAddress"
                  style="padding-left: 10px;padding-right: 10px">
            <el-form-item prop="userAddress">
              <el-input
                placeholder="请输入网址,可以为空"
                v-model="commentForm.userAddress"
                class="input-with-select"
              >
                <el-select v-model="select" slot="prepend" placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-input>
            </el-form-item>
          </el-col>
          <el-avatar class="user-icon" :size="40" :src="userAvater" style="margin-top:10px;"></el-avatar>
          <el-form-item style="float:right;" class="submitbtn">
            <el-button v-show="!isReply"  size="medium" type="primary" @click="submit('commentForm')" :loading="loading">发表评论
            </el-button>
            <el-button-group>
              <el-button v-show="isReply" type="primary" size="mini" @click="cancelReply()" :loading="loading">取消回复
              </el-button>
              <el-button v-show="isReply" type="primary" size="mini" @click="submit('commentForm')" :loading="loading">
                回复
              </el-button>
            </el-button-group>
          </el-form-item>
        </el-form>
      </div>
    </el-col>
  </div>
</template>
<script>
  import {mavonEditor} from "mavon-editor";
  import "mavon-editor/dist/css/index.css";
  import {addComment} from "@/api/comment";

  export default {
    name: "commentEdit",
    props: {
      parentId: {
        type: String,
        default: null
      },
      replyuserName: {
        type: String,
        default: null
      },
      isReply: {
        type: Boolean,
        default: false
      }
    },
    components: {
      mavonEditor,
    },
    data() {
      var checkqq = (rule, value, callback) => {
        let boolean = new RegExp("^[1-9][0-9]{5,10}").test(value);
        if (!value) {
          return callback(new Error("qq不能为空"));
        }
        setTimeout(() => {
          if (boolean) {
            callback();
          } else {
            callback(new Error("请输入正确的qq号"));
          }
        }, 1000);
      };
      var checkAddress = (rule, value, callback) => {
        let boolean = new RegExp(
          "^[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9](?:.[a-zA-Z]{2,})+$"
        ).test(value);
        if (!value) {
          callback();
        } else {
          setTimeout(() => {
            if (boolean) {
              callback();
            } else {
              callback(new Error("请输入正确的域名地址"));
            }
          }, 1000);
        }
      };
      return {
        loading: false,
        isShow: false, // 验证码模态框是否出现
        codeStyle: "monokai", //主题
        content: "",
        select: "HTTP",
        options: [
          {
            value: "HTTP",
            label: "HTTP",
          },
          {
            value: "HTTPS",
            label: "HTTPS",
          },
        ],
        toolbar: {
          bold: true, // 粗体
          italic: true, // 斜体
          ol: true, // 有序列表
          imagelink: true, // 图片链接
          code: true, // code
          table: true, // 表格
          alignleft: true, // 左对齐
          aligncenter: true, // 居中
          alignright: true, // 右对齐
          preview: true,
        },
        commentForm: {
          userName: "",
          userAddress: "",
          qq: "",
        },
        rules: {
          userName: [
            {required: true, message: "请输入用户名", trigger: "blur"},
          ],
          qq: [{validator: checkqq, trigger: "blur"}],
          userAddress: [{validator: checkAddress, trigger: "blur"}],
        },
        inputComment: "",
        userAvater: "",
      };
    },
    methods: {
      submit(formName) {
        var _this = this;
        this.loading = true;
        _this.$refs[formName].validate((valid) => {
          if (valid) {
            let comment = {
              articleId: _this.$route.params.id,
              userName: _this.commentForm.userName,
              userAddress: _this.select + "://" + _this.commentForm.userAddress,
              content: _this.$refs.md.d_render,
              parentId: _this.parentId,
              userIcon: _this.userAvater,
              replyName: _this.replyuserName
            };
            addComment("/comments/addComments", comment)
              .then((res) => {
                _this.$notify({
                  title: res.code === 200 ? "成功" : "失败",
                  message: res.msg,
                  type: res.code === 200 ? "success" : "warning",
                });
                //添加成功 重新加载数据
                _this.$parent.queryCommentByArticleId();
                if (_this.parentId != 0) {
                  document
                    .getElementById("scollto" + _this.parentId)
                    .scrollIntoView({block: "start", behavior: "smooth"});
                } else {
                  document
                    .getElementById("comment")
                    .scrollIntoView({block: "start", behavior: "smooth"});
                }
                if (localStorage.getItem("userInfo")) {
                  localStorage.removeItem("userInfo")
                }
                localStorage.setItem("userInfo", JSON.stringify(_this.commentForm));
              });
          } else {
            _this.$message.error("失败！");
            return false;
          }
          this.loading = false;
        });
      },
      setUserAvater() {
        this.userAvater =
          "http://q1.qlogo.cn/g?b=qq&nk=" + this.commentForm.qq + "&s=40";
      },
      cancelReply() {
        this.$emit('changereplyuserName', "");
        this.$emit('changeparentId', "0");
        this.$emit('changeisReply', false);
      }
    },
    created() {
      this.replyName = "开始编辑";
      if (JSON.parse(localStorage.getItem("userInfo"))) {
        let remenberForm = JSON.parse(localStorage.getItem("userInfo"));
        this.commentForm.userName = remenberForm.userName;
        this.commentForm.userAddress = remenberForm.userAddress;
        this.commentForm.qq = remenberForm.qq;
        this.userAvater ="http://q1.qlogo.cn/g?b=qq&nk=" + this.commentForm.qq + "&s=40";
      }
    },
    watch:{
      replyuserName(){
        this.replyName=this.isReply ? "@"+this.replyuserName : "开始编辑" ;
      }
    }
  };
</script>
<style scoped>
  .el-form-item__error {
    z-index: 9999;
  }

  .el-form-item {
    margin-bottom: 10px;
  }

  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }

  @media screen and (min-width: 768px) and (max-width: 992px) {
    .userName {
      width: 20%;
    }

    .email {
      width: 29%;
    }

    .userAddress {
      width: 33%;
    }
  }

  @media screen and (max-width: 500px) {
    .submitbtn {
      padding-right: 10px;
    }

    .userName {
      padding-left: 10px;
    }

    .user-icon {
      margin-left: 10px;
    }
  }
</style>
<style>

  #commentform .el-select .el-input {
    width: 100px !important;
  }
</style>
