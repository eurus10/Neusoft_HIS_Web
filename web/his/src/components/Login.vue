<template>
  <div style="width: 100%; height: 100%; background-color: #4682B4; position: absolute; top: 0px; left: 0px;">
    <div style="margin-top: 40px; text-align: center;">
      <span style="color: white; font-size: 35px; font-weight: bold;">医院信息管理系统</span><br>
      <span style="color: white; font-size: 28px;">Hospital Information System</span>
    </div>
    <div style="
    margin-top: 50px;
    width: 24%;
    height: 50%;
    position: relative;
    left: 39%;
    top: 3%;
    background-color: white;
    border-radius: 5px;
    opacity: 50%;
    "></div>
    <div style="
    margin-top: 50px;
    width: 24%;
    height: 50%;
    position: relative;
    left: 38%;
    bottom: 55%;
    background-color: white;
    border-radius: 5px;
    opacity: 95%;
    ">
      <div style="width: 100%; height: 20%; text-align: center;">
        <p>
          <br>
          <span style="font-size: 30px; color: gray;">Login Form</span>
        </p>
      </div>
      <div style="width: 80%; height: 40%; margin-left: 30px; margin-right: 30px; margin-top: 30px; margin-bottom: 30px;
      ">
        <span style="font-size: 18px; color: gray;">用户名</span><br>
        <el-input v-model="id" type="text"></el-input><br><br>
        <span style="font-size: 18px; color: gray;">用户密码</span><br>
        <el-input v-model="psw" type="password"></el-input><br>
      </div>
      <div style="width: 156px; height: 40px; position: relative; left: 25%;">
        <el-button type="primary" @click="login()">登陆</el-button>
        <el-button type="primary" @click="goToSignUp()" plain>注册</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Login',
  data() {
    return {
      id: null,
      psw: null
    }
  },
  methods: {
    login() {
      if (this.id == null || this.psw == null) {
        this.$message("请将信息填写完整")
      } else {
        axios.post("/login", {userId: this.id, userPsw: this.psw}).then((res)=>{
          if (res.data) {
            this.$store.commit("login", res.data)
            this.$router.replace("/index")
            this.$message("登陆成功")
          } else {
            this.$message("用户名或密码错误")
          }
        })
        this.clear()
      }
    },
    goToSignUp() {
      this.clear()
      this.$router.replace("/sign_up")
    },
    clear() {
      this.id = null,
      this.psw = null
    }
  },
}
</script>

<style scoped>

</style>