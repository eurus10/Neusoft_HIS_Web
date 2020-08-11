<template>
  <div>
    <el-container>
      <el-header height="100px" style="background-color: #409EFF; line-height: 100px;">
        <span style="font-size: 40px; color: white;">NeuSoft HIS</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="font-size: 20px; color: white;">version 1.0.1</span>
        <span class="hello-text" style="font-size: 20px; color: white;">Hello, {{user.name}}</span>
        <el-button @click="drawer = true" class="user-center-btn" type="primary" icon="el-icon-more" circle></el-button>
      </el-header>
      <el-container>
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu default-active="1" unique-opened=true>
            <el-submenu index="1">
              <template slot="title">挂号收费</template>
              <el-menu-item index="register" @click="goTo('/index/register')">挂号</el-menu-item>
              <el-menu-item index="withdraw" @click="goTo('/index/withdraw')">退号</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">门诊医生</template>
              <el-menu-item index="diagnose" @click="goTo('/index/diagnose')">门诊病历</el-menu-item>
              <el-menu-item index="exam_req">检验申请</el-menu-item>
              <el-menu-item index="make_presc" @click="goTo('/index/make_presc')">开立处方</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title">检验处置</template>
              <el-menu-item index="do_exam">患者检验</el-menu-item>
              <el-menu-item index="exam_man">项目管理</el-menu-item>
            </el-submenu>
            <el-submenu index="4">
              <template slot="title">药房管理</template>
              <el-menu-item index="drug_trans" @click="goTo('/index/drug_trans')">药房发药</el-menu-item>
              <el-menu-item index="drug_rcv" @click="goTo('/index/drug_rcv')">药房退药</el-menu-item>
              <el-menu-item index="drug_man">药品管理</el-menu-item>
            </el-submenu>
            <el-submenu index="5">
              <template slot="title">财务管理</template>
              <el-menu-item index="pay" @click="goTo('/index/pay')">缴费事务</el-menu-item>
              <el-menu-item index="refund" @click="goTo('/index/refund')">退费事务</el-menu-item>
              <el-menu-item index="finance">财务统计</el-menu-item>
            </el-submenu>
            <el-submenu index="6">
              <template slot="title">系统信息</template>
              <el-menu-item index="constant">常数项管理</el-menu-item>
              <el-menu-item index="department">科室管理</el-menu-item>
              <el-menu-item index="user">用户管理</el-menu-item>
              <el-menu-item index="disease">疾病信息管理</el-menu-item>
              <el-menu-item index="examination">检验项目管理</el-menu-item>
              <el-menu-item index="drug">药品管理</el-menu-item>
              <el-menu-item index="arrange">医师排班管理</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
      <el-drawer title="用户中心" :visible.sync="drawer" size="20%" direction="rtl">
        <div>
          <div class="user-center-main">
            <span class="user-center-font">用户ID</span><br>
            <el-input style="width: 90%;" v-model="userTemp.id" readonly></el-input><br>
            <span class="user-center-font">用户密码</span><br>
            <el-input type="password" style="width: 90%;" v-model="userTemp.psw"></el-input><br>
            <span class="user-center-font">用户名字</span><br>
            <el-input style="width: 90%;" v-model="userTemp.name"></el-input><br>
            <span class="user-center-font">所属科室</span><br>
            <!-- <el-input style="width: 90%;" v-model="userTemp.department"></el-input><br> -->
            <el-select v-model="userTemp.departmentId" style="width: 90%">
              <el-option v-for="d in departments" :key="d.id" :label="d.name" :value="d.id"></el-option>
            </el-select><br>
            <span class="user-center-font">用户类别</span><br>
            <!-- <el-input style="width: 90%;" v-model="userTemp.type"></el-input><br> -->
            <el-select v-model="userTemp.type" style="width: 90%">
              <el-option v-for="t in types" :key="t.value" :label="t.label" :value="t.value"></el-option>
            </el-select><br>
            <el-button type="primary" style="margin-top: 50px; margin-left: 45px;" size="small" @click="save()">保存</el-button>
            <el-button type="primary" style="margin-top: 50px;" size="small" plain @click="reset()">重置</el-button>
          </div>
          <div class="user-center-bottom">
            <el-button type="danger" @click="logout()" plain>退出登陆</el-button> 
          </div>
        </div>
      </el-drawer>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Index',
  data() {
    return {
      drawer: false,
      user: {
        id: null,
        name: null,
        psw: null,
        departmentId: null,
        type: null
      },
      userTemp: {
        id: null,
        name: null,
        psw: null,
        departmentId: null,
        type: null
      },
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
    goTo(url) {
      this.$router.replace(url)
    },
    save() {
      axios.post("/update_user", this.userTemp)
      this.user.id = this.userTemp.id
      this.user.psw = this.userTemp.psw
      this.user.name = this.userTemp.name
      this.user.departmentId = this.userTemp.departmentId
      this.user.type = this.userTemp.type
    },
    reset() {
      this.userTemp.id = this.user.id
      this.userTemp.name = this.user.name
      this.userTemp.psw = this.user.psw
      this.userTemp.departmentId = this.user.departmentId
      this.userTemp.type = this.user.type
    },
    logout() {
      this.$store.commit('logout')
      this.$router.replace("/login")
    },
    init() {
      axios.post("/init_depart").then((res)=>{
        this.departments = res.data
      })
      this.user = this.$store.state.user
      this.userTemp.id = this.user.id
      this.userTemp.psw = this.user.psw
      this.userTemp.name = this.user.name
      this.userTemp.departmentId = this.user.departmentId
      this.userTemp.type = this.user.type
    }
  },
  mounted() {
    this.init()
  }
}
</script>

<style scoped>
.hello-text {
  position: absolute;
  right: 120px;
}
.user-center-btn {
  position: absolute;
  right: 70px;
  top: 38px;
}
.user-center-main {
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 30px;
  margin-bottom: 30px;
}
.user-center-bottom {
  width: 100px;
  height: 5%;
  position: absolute;
  left: 20px;
  bottom: 30px;
}
.user-center-font {
  color: gray;
  font-size: 14px;
}
</style>
