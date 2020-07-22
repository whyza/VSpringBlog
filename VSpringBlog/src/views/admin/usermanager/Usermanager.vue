<template>
  <div class="app-container">
    <el-table
      v-loading="loading"
      ref="multipleTable"
      :data="tableData.filter(data => !search || data.userName.toLowerCase().includes(search.toLowerCase()))"
      :default-sort="{prop: 'userId', order: 'descending'}"
      style="width: 100%"
      stripe
      tooltip-effect="dark"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="userId" label="id" width="80" sortable></el-table-column>
      <el-table-column prop="createTime" label="注册日期" sortable>
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="userName" label="昵称" sortable>
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>昵称: {{ scope.row.userName }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.userName }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column prop="province" label="province" sortable></el-table-column>
      <el-table-column prop="gender" label="性别" sortable>
        <template slot-scope="scope">
          <el-tag :type="scope.row.gender === 1?'success':'danger'">{{scope.row.gender==1?"男":"女"}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column prop label="头像" sortable>
        <template slot-scope="scope">
          <img :src="scope.row.avatarUrl" class="head_pic" />
        </template>
      </el-table-column>
      <el-table-column prop="roleId" label="角色" width="100" align="center">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.roleId === 1 ? 'success' : 'primary'"
            disable-transitions
          >{{scope.row.roleId === 1?"admin":"primary"}}</el-tag>
        </template>
      </el-table-column>
      <!-- <el-table-column align="right">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageConf.pageCode"
        :page-sizes="pageConf.pageOption"
        :page-size="pageConf.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageConf.totalPage"
        class="pageing"
      ></el-pagination>
    </div>
  </div>
</template>
<script>
import { getUserList, getUserInfo, deleteUser } from "@/api/user";
import { parseTime } from "@/utils/index";
import { Loading } from "element-ui";

export default {
  data() {
    return {
      search: "",
      loading: true,
      tableData: null,
      pageConf: {
        pageCode: 1, //当前页
        pageSize: 10, //每页显示的记录数
        totalPage: 12, //总记录数
        pageOption: [1, 5, 10]
      }
    };
  },
  methods: {
    //pageSize改变时触发的函数
    handleSizeChange(val) {
      this.pageConf.pageSize = val;
      this.getUsers();
    },
    //当前页改变时触发的函数
    handleCurrentChange(val) {
      this.pageConf.pageCode = val;
      this.getUsers();
    },
    formatter(row, column) {
      return row.Address;
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleDel(id) {
      this.$confirm("确定删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        deleteUser("/user/deleteUser", {
          id: id
        }).then(res => {
          if (res.affectedRows > 0) {
            this.$message({
              message: "删除成功！！",
              type: "success"
            });
            this.getUsers();
          } else {
            this.$message.error("出错了！！");
          }
        });
      });
    },
    handleClick(id) {
      console.log(id);
    },
    getUsers() {
      getUserInfo("/queryUser", {
        pageNum: this.pageConf.pageCode,
        pageSize: this.pageConf.pageSize
      }).then(res => {
        this.tableData = res.list;
        this.pageConf.totalPage = res.total;
        this.loading = false;
      });
    }
  },
  created() {
    this.getUsers();
  }
};
</script>
<style>
.app-main {
  padding: 5px;
  margin: 5px;
}
.head_pic {
  width: 50px;
  height: 50px;
  border-radius: 50px;
}
.pageing {
  float: right;
  margin-top: 20px;
}
</style>
