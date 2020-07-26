<template>
  <div class="login-container">
    <div class="lowin-brand">
      <img class="header-image" src="@/images/kodinger.jpg" alt="logo" />
    </div>
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <h3 class="title">Login-Admin</h3>
      <div class="signup">sign up to continue</div>
      <el-form-item prop="userName">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          v-model="loginForm.userName"
          name="userName"
          type="text"
          auto-complete="off"
          placeholder="userName"
        />
      </el-form-item>
      <el-form-item prop="passWord">
        <span class="svg-container">
          <svg-icon icon-class="passWord" />
        </span>
        <el-input
          :type="pwdType"
          v-model="loginForm.passWord"
          name="password"
          auto-complete="off"
          placeholder="passWord"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon icon-class="eye" />
        </span>
      </el-form-item>
      <el-form-item>
        <el-button
          :loading="loading"
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleLogin"
        >Sign in</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { isvalidUsername } from "@/utils/validate";
export default {
  name: "Login",
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("用户名不能为空"));
      } else {
        callback();
      }
    };
    const validatePass = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error("密码不能小于5位"));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        userName: "admin",
        passWord: "123456",
      },
      loginRules: {
        userName: [
          { required: true, trigger: "blur", validator: validateUsername },
        ],
        passWord: [
          { required: true, trigger: "blur", validator: validatePass },
        ],
      },
      loading: false,
      pwdType: "passWord",
      redirect: undefined,
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  methods: {
    showPwd() {
      if (this.pwdType === "passWord") {
        this.pwdType = "";
      } else {
        this.pwdType = "passWord";
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.$store
            .dispatch("Login", this.loginForm)
            .then(() => {
              this.loading = false;
              this.$router.push({ path: this.redirect || "/" });
            })
            .catch(() => {
              this.loading = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style rel="stylesheet/scss" lang="scss">
$bg: #2d3a4b;
$light_gray: #eee;

/* reset element-ui css */
.login-container {
  .lowin-brand {
    overflow: hidden;
    width: 100px;
    height: 100px;
    margin: 0 auto -50px auto;
    border-radius: 50%;
    -webkit-box-shadow: 0 4px 40px rgba(0, 0, 0, 0.07);
    box-shadow: 0 4px 40px rgba(0, 0, 0, 0.07);
    padding: 10px;
    background-color: #fff;
    z-index: 1;
    position: relative;
    margin-top: 120px;
    .header-image {
      width: 80px;
      height: 80px;
    }
  }
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: #44a0b3;
      height: 47px;
      &:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px rgba(68, 160, 179, 0.06) inset !important;
        // -webkit-text-fill-color: #fff !important;
      }
    }
  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(68, 160, 179, 0.06);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;
.login-container {
  position: fixed;
  height: 100%;
  width: 100%;
  background-color: rgba(68, 160, 179, 0.06);
  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 400px;
    max-width: 100%;
    padding: 35px 35px 15px 35px;
    margin: 0 auto;
    background-color: #fff;
    -webkit-box-shadow: 0 7px 25px rgba(0, 0, 0, 0.08);
    box-shadow: 0 7px 25px rgba(0, 0, 0, 0.08);
    padding: 60px 25px 25px 25px;
    // text-align: left;
    border-radius: 3px;
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  .title {
    font-size: 30px;
    font-weight: 600;
    color: $dark_gray;
    margin: 0px auto 20px auto;
    text-align: center;
    font-weight: bold;
  }
  .signup {
    font-size: 12px;
    font-weight: 300;
    color: $dark_gray;
    margin: 5px auto 20px auto;
    text-align: center;
    font-weight: bold;
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
