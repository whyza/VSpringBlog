<template>
  <div class="category">
    <el-table
    :data="categoryList"
      style="width: 100%;margin-bottom: 20px;background: #f0f9eb;"
      row-key="id"
      border
      default-expand-all
      :tree-props="{children: 'sysCategoryList', hasChildren: 'hasChildren'}">
      <el-table-column prop="id" label="分类Id" sortable align="center"></el-table-column>
      <el-table-column prop="categoryName" label="分类名称" sortable align="center"></el-table-column>
      <el-table-column prop="categoryIcon" label="图标" align="center">
        <template slot-scope="scope">
          <i :class="'iconfont'+' '+ scope.row.categoryIcon"></i>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建日期" sortable align="center">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="updateTime" label="更新时间" sortable align="center">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.updateTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="right">
<!--        <template slot="header" slot-scope="scope">-->
<!--          <el-input v-model="search" size="mini" placeholder="输入关键字搜索"/>-->
<!--        </template>-->
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>
<script>
  import {getAllCategory} from "@/api/category";

  export default {
    data() {
      return {
        categoryList: [],
        dialogVisible: false
      };
    },
    methods: {
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
      getAllCategory() {
        getAllCategory("category/getAllCategory").then(res => {
          this.categoryList = res.data;
        });
      },
    },
    created() {
      this.getAllCategory();
    }
  };
</script>
<style>
  .category .el-table__row--level-0{
    background: oldlace;
  }
</style>
