<template>
  <div class="addartcle-body">
    <el-form
      ref="articleForm"
      :rules="rules"
      :model="articleForm"
      label-width="80px"
      class="articleForm"
    >
      <div class="headform">
        <el-form-item label="文章标题" prop="title">
          <el-input v-model="articleForm.title" size="medium"></el-input>
        </el-form-item>
        <el-form-item label="文章分类" prop="category">
          <el-col :xs="7" :sm="7" :md="5" :lg="4" :xl="3">
            <el-autocomplete
              class="inline-input"
              v-model="articleForm.category"
              :fetch-suggestions="querySearch"
              placeholder="请输入内容"
              :trigger-on-focus="false"
              @select="handleSelect"
            ></el-autocomplete>
          </el-col>
        </el-form-item>
        <div class="tags-form">
          <el-form-item label="文章标签" prop="tags">
            <el-col :xs="7" :sm="7" :md="5" :lg="4" :xl="1">
              <el-input
                v-model="articleForm.uinputtags"
                size="medium"
                style="widht:180px"
                placeholder="请输入标签"
                @keyup.enter.native="entertoadd"
              ></el-input>
            </el-col>
            <el-button
              type="primary"
              icon="el-icon-edit"
              :loading="false"
              size="small"
              style="margin-left:5px"
              @click="entertoadd"
            ></el-button>
          </el-form-item>
          <el-form-item label>
            <el-col :xs="24" :sm="24" :md="16" :lg="16" :xl="1">
              <el-tag
                :key="tag.name"
                v-for="tag in tags"
                closable
                :disable-transitions="false"
                @close="deleteTags(tag)"
                :type="tag.type"
              >{{tag.name}}</el-tag>
            </el-col>
          </el-form-item>
        </div>
      </div>
      <div class="markdown-edit">
        <el-form-item prop="title">
          <div class="markdown" style="margin-left:0">
            <div class="container">
              <mavon-editor
                v-model="articleForm.content"
                ref="md"
                @imgAdd="$imgAdd"
                @change="change"
                :navigation="true"
                placeholder="请输入..."
                codeStyle="agate"
                :ishljs="true"
              />
            </div>
          </div>
        </el-form-item>
      </div>
      <div class="addArticle-btn">
        <el-form-item>
          <el-button type="primary" @click="submit('articleForm')">发布文章</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import { addArticle } from "@/api/article";
import { showMessage } from "@/utils/showMessage";

export default {
  name: "",
  props: [],
  components: {
    mavonEditor,
  },
  data() {
    return {
      articleForm: {
        title: "",
        content: "",
        category: "",
        uinputtags: "",
      },
      tags: [{ name: "标签一", type: "" }],
      rules: {
        title: [
          { required: true, message: "请输入文章标题", trigger: "blur" },
          {
            min: 3,
            max: 500,
            message: "长度在 3 到 500 个字符",
            trigger: "blur",
          },
        ],
        category: [
          { required: true, message: "请输入文章分类", trigger: "blur" },
          {
            min: 3,
            max: 50,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
      },
      restaurants: [],
    };
  },
  methods: {
    // 将图片上传到服务器，返回地址替换到md中
    $imgAdd(pos, $file) {
      let formdata = new FormData();
      this.$upload
        .post("/上传接口地址", formdata)
        .then((res) => {
          console.log(res.data);
          this.$refs.md.$img2Url(pos, res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 所有操作都会被解析重新渲染
    change(value, render) {
      // render 为 markdown 解析后的结果[html]c
      this.html = render;
    },
    // 提交
    submit(formName) {
      var _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.articleForm);
          console.log(this.html);
          this.$message.success("提交成功，已打印至控制台！");
          this.loading = true;
          let articleForm = this.articleForm;
          let data = {
            title: articleForm.title,
            mrdText: _this.html,
            htmlText: _this.$refs.md.d_render,
            category: articleForm.category,
            uid: 1,
            state: 0,
          };
          let tags = this.tags;
          addArticle("/article/addArticle", {data, tags}).then((res) => {
            this.$notify({
              title: res.success === true ? "成功" : "失败",
              message: res.message,
              type: res.code === 200 ? "success" : "warning",
            });
            console.log(res);
            this.loading = false;
          });
        } else {
          this.$message.error("失败！");
          return false;
        }
      });
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (
          restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) ===
          0
        );
      };
    },
    loadAll() {
      return [
        { value: "三全鲜食（北新泾店）", address: "长宁区新渔路144号" },
        {
          value: "Hot honey 首尔炸鸡（仙霞路）",
          address: "上海市长宁区淞虹路661号",
        },
        {
          value: "新旺角茶餐厅",
          address: "上海市普陀区真北路988号创邑金沙谷6号楼113",
        },
      ];
    },
    querySearch(queryString, cb) {
      var restaurants = this.restaurants;
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    handleSelect(item) {
      console.log(item);
    },
    entertoadd() {
      if (this.articleForm.uinputtags && this.articleForm.uinputtags != null) {
        this.tags.push({ name: this.articleForm.uinputtags, type: "success" });
        this.articleForm.uinputtags = "";
        showMessage(true, "success", "a", 3000);
      }
    },
    deleteTags(tag) {
      console.log(this.tags.splice(this.tags.indexOf(tag), 1));
    },
  },
  mounted() {
    this.restaurants = this.loadAll();
  },
};
</script>


<style>
.articleForm {
  padding: 10px 25px;
}
.markdown-edit .el-form-item__content,
.addArticle-btn .el-form-item__content {
  margin-left: 0 !important;
  margin-top: 10px;
}
.articleForm .el-form-item {
  margin-bottom: 20px;
}
.tags-form .el-form-item {
  margin-bottom: 10px;
}
.headform {
  background: #fff;
  padding: 20px;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;
}
.tags-form .el-input__suffix {
  cursor: pointer;
}
.tags-form .el-tag {
  margin-right: 5px;
}
</style>