<template>
  <div style="width: 100%; height: 100%; background-color: #4682B4; position: absolute; top: 0px; left: 0px;">
    <div style="margin-top: 40px; text-align: center;">
      <span style="color: white; font-size: 35px; font-weight: bold;">医院信息管理系统</span><br>
      <span style="color: white; font-size: 28px">Hospital Information System</span>
    </div>
    <div style="
    margin-top: 50px;
    width: 24%;
    height: 60%;
    position: relative;
    left: 39%;
    top: 3%;
    background-color: white;
    border-radius: 5px;
    opacity: 50%;
    "></div>
    <div style="
    margin-top: 50px; width: 24%;
    height: 60%;
    position: relative;
    left: 38%;
    bottom: 65%;
    background-color: white;
    border-radius: 5px;
    opacity: 95%;
    ">
      <div style="width: 100%; height: 15%; text-align: center;">
        <br>
        <span style="font-size: 30px; color: gray;">SignUp Form</span>
      </div>
      <div style="width: 80%; height: 40%; margin-left: 30px; margin-right: 30px; margin-bottom: 30px;">
        <span style="color: gray; font-size: 14px;">用户名</span><br>
        <el-input v-model="user.id" size="small"></el-input>
        <span style="color: gray; font-size: 14px;">真实姓名</span><br>
        <el-input v-model="user.name" size="small"></el-input>
        <span style="color: gray; font-size: 14px;">密码</span><br>
        <el-input v-model="user.psw" size="small" type="password"></el-input>
        <span style="color: gray; font-size: 14px;">重复密码</span><br>
        <el-input v-model="pswCheck" size="small" type="password"></el-input><br><br>
        <span style="color: gray; font-size: 14px;">用户类型</span>
        <el-select v-model="user.type" size="small">
          <el-option v-for="t in types" :key="t.value" :label="t.label" :value="t.value"></el-option>
        </el-select><br><br>
        <span style="color: gray; font-size: 14px;">所属科室</span>
        <el-select v-model="user.departmentId" size="small">
          <el-option v-for="d in departments" :key="d.id" :label="d.name" :value="d.id"></el-option>
        </el-select>
      </div>
      <div style="width: 156px; height: 40px; position: relative; left: 25%; top: 25%;">
        <el-button type="primary" @click="signUp()">注册</el-button>
        <el-button type="primary" @click="back()" plain>返回</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'SignUp',
  data() {
    return {
      user: {
        id: null,
        name: null,
        psw: null,
        departmentId: null,
        type: null
      },
      pswCheck: null,
      types: [
        {
          label: "挂号管理员",
          value: 1
        },
        {
          label: "医师",
          value: 2
        },
        {
          label: "检验师",
          value: 3
        },
        {
          label: "药剂师",
          value: 4
        },
        {
          label: "财务师",
          value: 5
        },
        {
          label: "行政管理员",
          value: 6
        }
      ],
      departments: []
    }
  },
  methods: {
    signUp() {
      if (this.user.psw != this.pswCheck) {
        this.$message("两次输入密码不一致")
      } else if (this.user.id == null || this.user.name == null || this.user.psw == null || 
      this.user.type == null || this.user.departmentId == null) {
        this.$message("请将信息填写完整")
      } else {
        axios.post("/sign_up", this.user).then((res)=>{
          if (res.data) {
            this.$store.commit("login", res.data)
            this.$router.replace("/index")
            this.$message("登陆成功")
          } else this.$message("注册失败")
        })
        this.init()
      }
    },
    back() {
      this.init()
      this.$router.replace("/login")
    },
    init() {
      this.user = {
        id: null,
        psw: null,
        type: null,
        department: null
      }
      axios.post("/sign_up/init_id").then((res)=>{
        this.user.id = res.data
      })
      axios.post("/sign_up/init_depart").then((res)=>{
        this.departments = res.data
      })
      this.pswCheck = null
    }
  },
  mounted() {
    this.init()
  }
}
</script>

<style scoped>

</style>