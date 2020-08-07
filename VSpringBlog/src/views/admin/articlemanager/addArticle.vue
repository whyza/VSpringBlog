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
          <el-cascader
            :options="categoryList"
            :props="{checkStrictly: true,value: 'id',label: 'categoryName',children: 'sysCategoryList',expandTrigger: 'hover'}"
            v-model="selectedOptions"
            :placeholder="chosecategory"
            @change="handleChange"
            :show-all-levels="false"
            clearable
          ></el-cascader>
        </el-form-item>
        <el-form-item label="缩略图" prop="article_thumbnail">
          <el-upload
            :limit="1"
            drag
            action="actionUp"
            :before-upload="beforeUpload"
            :http-request="fileRequest"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :on-exceed="outLimit"
            :file-list="fileList[0]"
            list-type="picture"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt />
          </el-dialog>
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
          <div class="markdown" style>
            <div class="container">
              <!-- codeStyle="color-brewer monokai-sublime monokai" -->
              <mavon-editor
                v-model="articleForm.content"
                ref="md"
                @imgAdd="$imgAdd"
                :navigation="true"
                codeStyle="monokai"
                placeholder="请输入..."
                :ishljs="true"
                :externalLink="externalLink"
              />
            </div>
          </div>
        </el-form-item>
      </div>
      <div class="addArticle-btn">
        <el-form-item>
          <el-button
            ref="submitbtn"
            type="primary"
            @click="submit('articleForm')"
            :loading="loading"
          >{{submitbtnval}}</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import { uploadImg } from "@/api/uploadfile";
import { getArticleContentByArticleId, addArticle } from "@/api/article";
import { getAllCategory } from "@/api/category";

export default {
  name: "",
  props: {
    isEditPage: {
      type: Boolean,
      default: false,
    },
  },
  components: {
    mavonEditor,
  },
  data() {
    return {
      loading: false,
      articleForm: {
        title: "",
        content: "",
        category: "",
        uinputtags: "",
      },
      categoryList: [],
      chosecategory: "请选择",
      submitbtnval: "发布文章",
      tags: [],
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
      },
      selectedOptions: [5],
      dialogImageUrl: "",
      dialogVisible: false,
      fileList: [],
      articleThumbnail: "",
      externalLink: {
        markdown_css: function () {
          // 这是你的markdown css文件路径
          return "/markdown/github-markdown.min.css";
        },
        hljs_js: function () {
          // 这是你的hljs文件路径
          return "/highlightjs/highlight.min.js";
        },
        hljs_css: function (css) {
          // 这是你的代码高亮配色文件路径
          return "/highlightjs/styles/" + css + ".min.css";
        },
      },
    };
  },
  methods: {
    // 绑定@imgAdd event
    $imgAdd(pos, $file) {
      // 第一步.将图片上传到服务器.
      let formdata = new FormData();
      formdata.append("multipartFile", $file);
      uploadImg("/uploadFile", formdata).then((res) => {
        this.$refs.md.$img2Url(pos, res.data);
      });
    },
    // 提交
    submit(formName) {
      var _this = this;
      _this.$refs[formName].validate((valid) => {
        let tagData = [];
        if (_this.tags && _this.tags.length > 0) {
          let tags = _this.tags;
          for (let tagIndex in tags) {
            tagData.push({ tagsName: tags[tagIndex].name });
          }
        }
        if (valid) {
          let oldarticleThumbnail = _this.fileList[0].url;
          let newarticleThumbnail = _this.articleThumbnail;
          let articelth =
            newarticleThumbnail === undefined || newarticleThumbnail === ""
              ? oldarticleThumbnail
              : newarticleThumbnail;
          _this.loading = true;
          let articleForm = _this.articleForm;
          let data = {
            title: articleForm.title,
            mrdText: _this.$refs.md.d_value,
            htmlText: _this.$refs.md.d_render,
            category: articleForm.category,
            uid: 1,
            state: 0,
            sysTagsList: tagData,
            articleThumbnail: articelth,
          };
          let url = "/article/addArticle";
          if (_this.$route.params.id) {
            url = "/article/updateArticle";
            data["id"] = _this.$route.params.id;
          }
          addArticle(url, data)
            .then((res) => {
              this.$notify({
                type: res.code === 200 ? "success" : "warning",
                title: res.code === 200 ? "成功" : "失败",
                message:
                  '你可以点击<span style="color:#409EFF;cursor:pointer" id="goto"/>这里</span>查看刚刚修改的文章哦！',
                position: "bottom-right",
                duration: "6000",
                dangerouslyUseHTMLString: true,
              });
              _this.loading = false;
            })
            .catch(function (error) {
              _this.loading = false;
            });
        } else {
          _this.$message.error("失败！");
          return false;
        }
      });
    },
    entertoadd() {
      if (this.articleForm.uinputtags && this.articleForm.uinputtags != null) {
        this.tags.push({ name: this.articleForm.uinputtags });
        this.articleForm.uinputtags = "";
      }
    },
    deleteTags(tag) {
      console.log(this.tags.splice(this.tags.indexOf(tag), 1));
    },
    getContent() {
      let _this = this;
      getArticleContentByArticleId("article/getArticleContentByArticleId", {
        articleId: _this.$route.params.id,
      }).then((res) => {
        if (res.data === undefined || res.data === null) {
          _this.$router.push("/404");
        }
        let articleForm = {
          title: res.data.title,
          content: res.data.mrdText,
          category: res.data.title,
          uinputtags: "",
        };
        _this.articleForm = articleForm;
        let tags = res.data.sysTagsList;
        let url = res.data.articleThumbnail;
        var index = url.lastIndexOf("/");
        _this.fileList[0] = [
          { name: url.substring(index + 1, url.length), url: url },
        ];
        //拿出标签
        for (let tagIndex in tags) {
          _this.tags.push({ name: tags[tagIndex].tagsName });
        }
        _this.chosecategory = res.data.categoryName;
      });
    },
    getAllCategory() {
      getAllCategory("category/getAllCategory").then((res) => {
        this.categoryList = res.data;
      });
    },
    handleChange(val) {
      console.log(val);
    },
    handleRemove(file, fileList) {},
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    beforeUpload(file) {
      const isMatch =
        file.type === "image/jpeg" ||
        file.type === "image/jpg" ||
        file.type === "image/png" ||
        file.type === "image/gif" ||
        file.type === "image/bmp";
      const isLt2M = file.size / 1024 / 1024 < 1;

      if (!isMatch) {
        this.$message.error("图片格式不匹配");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 1MB!");
      }
      return isMatch && isLt2M;
    },
    fileRequest(item) {
      let uploadData = new FormData();
      uploadData.append("multipartFile", item.file);
      uploadImg("/uploadFile", uploadData).then((res) => {
        this.articleThumbnail = res.data.url;
        let url = res.data.url;
        var index = url.lastIndexOf("/");
        this.fileList[0] = [
          { name: url.substring(index + 1, url.length), url: url },
        ];
      });
    },
    outLimit() {
      this.$message.error("只能上传一个哦！");
    },
  },
  created() {
    this.getAllCategory();
    if (this.$route.params.id !== null && this.$route.params.id !== undefined) {
      this.getContent();
      this.submitbtnval = "更新文章";
    } else {
      this.submitbtnval = "发布文章";
    }
  },
  mounted() {
    document.addEventListener("mouseup", (e) => {
      let _track = document.getElementById("goto");
      if (_track) {
        if (_track.contains(e.target)) {
          if (localStorage.getItem("path")) {
            this.$router.push(localStorage.getItem("path"));
          }
        }
      }
    });
  },
  watch: {
    $route: {
      handler() {
        if (
          this.$route.params.id !== null &&
          this.$route.params.id !== undefined
        ) {
          this.getContent();
          this.submitbtnval = "更新文章";
        } else {
          let articleForm = {
            title: "",
            content: "",
            category: "",
            uinputtags: "",
          };
          this.selectedOptions = [];
          this.fileList[0] = [];
          this.chosecategory = "请选择";
          this.articleForm = articleForm;
          this.submitbtnval = "发布文章";
        }
        this.tags = [];
      },
      deep: true,
    },
  },
};
</script>


<style>
.el-upload-list--picture .el-upload-list__item {
  height: 120px;
}
.el-upload-list--picture .el-upload-list__item-thumbnail {
  width: 150px;
  height: 100px;
}
.el-upload-dragger .el-icon-upload {
  font-size: 40px;
  margin: 20px 0 0px;
}
.el-upload-dragger {
  width: 205px;
  height: 120px;
}
.el-upload-dragger .el-upload__text {
  font-size: 12px;
}
.markdown-edit .el-form-item__content,
.addArticle-btn .el-form-item__content {
  margin: 7px 7px 0 7px !important;
}
.articleForm .el-form-item {
  margin-bottom: 20px;
}
.tags-form .el-form-item {
  margin-bottom: 10px;
}
.headform {
  background: #fff;
  padding: 10px;
  margin: 7px;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 2px 12px 0px;
}
.tags-form .el-input__suffix {
  cursor: pointer;
}
.tags-form .el-tag {
  margin-right: 5px;
}
</style>