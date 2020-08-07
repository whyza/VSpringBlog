<template>
  <div class="commentmanagetr">
    <el-button @click="clearFilter">清除所有过滤器</el-button>
    <el-table
      ref="filterTable"
      :data="commentsList.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
    >
      <el-table-column label="日期" width="180" prop="date" sortable column-key="date">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="昵称" width="100">
        <template slot-scope="scope">
          <div>{{scope.row.userName}}</div>
        </template>
      </el-table-column>
      <el-table-column label="头像" width="180">
        <template slot-scope="scope">
          <el-avatar shape="square" size="medium" :src="scope.row.userIcon"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column label="评论内容" width="180">
        <template slot-scope="scope">
          <div v-html="scope.row.content"></div>
        </template>
      </el-table-column>
      <el-table-column label="个人主页" width="180">
        <template slot-scope="scope">
          <div v-html="scope.row.userAddress"></div>
        </template>
      </el-table-column>
      <el-table-column
        prop="tag"
        label="状态"
        width="180"
        :filters="[{ text: '通过', value: 1 }, { text: '审核', value: 0 }]"
        :filter-method="filterTag"
        filter-placement="bottom-end"
      >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.state"
            :active-value="0"
            :inactive-value="1"
            active-text="通过"
            inactive-text="审核"
            active-color="#13ce66"
            inactive-color="#ff4949"
            @change="changeSwitch(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column align="right">
        <template slot="header">
          <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageConf.pageCode"
      :page-size="pageConf.pageSize"
      layout="total, prev, pager, next, jumper"
      :total="pageConf.totalPage"
      class="pageing"
      style="margin-top:10px"
    ></el-pagination>
  </div>
</template>

<script>
import { queryAllCommentsListPage } from "@/api/comment";
export default {
  data() {
    return {
      pageConf: {
        pageCode: 1, //当前页
        pageSize: 8, //每页显示的记录数
        totalPage: 10, //总记录数
      },
      commentsList: [],
      search: "",
    };
  },
  methods: {
    clearFilter() {
      this.$refs.filterTable.clearFilter();
    },
    filterTag(value, row) {
      console.log(value, row.state);
      return row.state === value;
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    getAllComment() {
      queryAllCommentsListPage("comments/queryAllCommentsListPage", {
        current: this.pageConf.pageCode,
        size: this.pageConf.pageSize,
      }).then((res) => {
        console.log(res);
        this.commentsList = res.data.records;
        this.pageConf.totalPage = res.data.total;
      });
    },
    //pageSize改变时触发的函数
    handleSizeChange(val) {
      this.pageConf.pageSize = val;
      this.getAllComment();
    },
    //当前页改变时触发的函数
    handleCurrentChange(val) {
      this.pageConf.pageCode = val;
      this.getAllComment();
    },
    changeSwitch(data) {
      console.log(data);
    },
  },
  created() {
    this.getAllComment();
  },
};
</script>
<style lang="scss" scoped>
.commentmanagetr {
  padding: 20px;
}
</style>