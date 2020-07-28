<template>
  <div>
    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="1">
      <div class="reply-box-popover">
        <el-form
          :model="commentForm"
          status-icon
          :rules="rules"
          ref="commentForm"
          label-width="100px"
        >
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
              type="password"
              v-model="commentForm.userName"
              autocomplete="off"
              prefix-icon="el-icon-user"
              placeholder="用户名"
            ></el-input>
          </el-form-item>

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

export default {
  name: "commentEdit",
  components: {
    mavonEditor,
  },
  data() {
    return {
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
    // 提交
    submit(formName) {
      var _this = this;
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          _this.loading = true;
          let comment = {
            articleId: this.$route.params.id,
            userName: _this.commentForm.userName,
            userAddress: _this.commentForm.userAddress,
            content: _this.$refs.md.d_render,
          };
          addComment("/comments/addComments", comment).then((res) => {
            _this.$notify({
              title: res.code === 200 ? "成功" : "失败",
              message: res.msg,
              type: res.code === 200 ? "success" : "warning",
            });
            _this.loading = false;
          });
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
  margin-top: ;
}
</style>