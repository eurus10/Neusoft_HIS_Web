<template>
  <div>
    <div>
      <el-input class="control-l" v-model="registerForm.id" placeholder="发票号"></el-input>
      <el-button @click="searchRegisterFormById(registerForm.id)" icon="el-icon-search" circle></el-button>
    </div>
    <h3 style="color: gray;">挂号信息</h3><br>
    <div>
      <span>患者姓名：</span>
      <el-input class="control" v-model="patient.name" readonly></el-input>
      <span>患者性别：</span>
      <el-input class="control" v-model="patient.gender" readonly></el-input>
      <span>身份证号：</span>
      <el-input class="control-xl" v-model="patient.id" readonly></el-input>
      <span>挂诊科室：</span>
      <el-input class="control" v-model="registerForm.department" readonly></el-input><br>
      <span>挂诊医师：</span>
      <el-input class="control" v-model="registerForm.doctor" readonly></el-input>
      <span>挂号级别：</span>
      <el-input class="control" v-model="registerForm.level" readonly></el-input>
      <span>当前状态：</span>
      <el-input class="control" v-model="registerForm.diagnosed" readonly></el-input>
    </div>
    <div class="bottom">
      <span>总费用：</span>
      <el-input class="control" v-model="costAll" readonly></el-input>
      <el-button type="primary" @click="withdraw()">确认退号</el-button>
      <el-button type="primary" plain @click="init()">刷新界面</el-button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Withdraw',
  data() {
    return {
      registerForm: {
        id: null,
        patientId: null,
        department: null,
        doctor: null,
        level: null,
        recordNeeded: null,
        cost: null,
        diagnosed: null
      },
      patient: {
        id: null,
        name: null,
        gender: null,
        birth: null,
        age: null,
        addr: null,
        tel: null,
        recordId: null
      }
    }
  },
  methods: {
    searchRegisterFormById(registerForm) {
      axios.post("/withdraw/search_reg", {id: registerForm}).then((res)=>{
        if (res.data) {
          this.registerForm = res.data
          axios.post("/withdraw/search_patient", {id: res.data.patientId}).then((res)=>{
            if (res.data) this.patient = res.data
          })
        }
      })
    },
    withdraw() {
      if (this.registerForm.patientId == null) {
        this.$message("请将信息填写完整")
      } else if (this.registerForm.diagnosed == "已诊断") {
        this.$message("退号失败")
      } else {
        axios.post("/withdraw", {id: this.registerForm.id})
        this.$message("退号成功")
      }
    },
    init() {
      axios.post("/register/init_depart").then((res)=>{
        this.departments = res.data
      })
      axios.post("/withdraw/init_doc").then((res)=>{
        this.doctors = res.data
      })

      this.registerForm = {
        id: null,
        patientId: null,
        department: null,
        doctor: null,
        level: null,
        recordNeeded: null,
        cost: null,
        diagnosed: null
      }
      this.patient = {
        id: null,
        name: null,
        gender: null,
        birth: null,
        age: null,
        addr: null,
        tel: null,
        recordId: null
      }
    }
  },
  computed: {
    costAll() {
      if (this.registerForm.recordNeeded) return this.registerForm.cost - 1
      else return this.registerForm.cost
    }
  },
  mounted() {
    this.init()
  }
}
</script>

<style scoped>
.bottom {
  height: 100px;
  width: 500px;
  position: absolute;
  bottom: 0px;
  right: 150px;
}
span {
  color: gray;
  font-size: 14px;
}
</style>