<template>
  <div>
    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
      <div class="reply-box-popover" id="commentform" ref="commentform">
        <el-form
          :model="commentForm"
          status-icon
          :rules="rules"
          ref="commentForm"
          label-width="100px"
        >
          <mavon-editor
            ref="md"
            style="min-height: 60px"
            :subfield="false"
            :ishljs="true"
            v-model="content"
            :toolbarsFlag="true"
            :toolbars="toolbar"
            placeholder="请输入..."
          />

          <el-form-item prop="userAddress">
            <el-input
              placeholder="请输入网址"
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
          <el-form-item prop="userName">
            <el-input
              v-model="commentForm.userName"
              autocomplete="off"
              prefix-icon="el-icon-user"
              placeholder="用户名"
            ></el-input>
          </el-form-item>
          <Vcode style="z-index:9999999" :show="isShow" @success="success" @close="close" />
          <el-form-item>
            <el-button type="primary" @click="submit('commentForm')">发表评论</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-col>
  </div>
</template>
<script>
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import { addComment } from "@/api/comment";
import { queryComment } from "@/api/comment";
import Vcode from "vue-puzzle-vcode";

export default {
  name: "commentEdit",
  props: {
    parentId: {
      type: Number,
      default: null,
    },
  },
  components: {
    mavonEditor,
    Vcode,
  },
  data() {
    return {
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
        superscript: true, // 上角标
        subscript: true, // 下角标
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
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
      },
      rules: {
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
      },
      inputComment: "",
    };
  },
  methods: {
    showVal() {
      this.isShow = true;
    },
    // 用户通过了验证
    success(msg) {
      let _this = this;
      _this.isShow = false; // 通过验证后，需要手动隐藏模态框
      let comment = {
        articleId: _this.$route.params.id,
        userName: _this.commentForm.userName,
        userAddress: _this.commentForm.userAddress,
        content: _this.$refs.md.d_render,
        parentId: _this.parentId,
      };
      addComment("/comments/addComments", comment).then((res) => {
        console.log(res);
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
            .scrollIntoView({ block: "start", behavior: "smooth" });
        } else {
          document
            .getElementById("comment")
            .scrollIntoView({ block: "start", behavior: "smooth" });
        }
      });
    },
    // 用户点击遮罩层，应该关闭模态框
    close() {
      this.isShow = false;
    },
    // 提交
    submit(formName) {
      var _this = this;
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          _this.showVal();
        } else {
          _this.$message.error("失败！");
          return false;
        }
      });
    },
  },
};
</script>
<style>
.el-form-item__error {
  z-index: 9999;
}
.el-form-item {
  margin-bottom: 10px;
}
.el-select .el-input {
  width: 130px !important;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
.el-form-item__content button {
  margin-top: 10px;
}
</style>