<template>
  <div>
    <div>
      <el-input class="control-xl" v-model="patient.id" placeholder="身份证号"></el-input>
      <el-button @click="searchPatientById(patient.id)" icon="el-icon-search" circle></el-button>
      <span style="position: absolute; right: 600px; top: 137px">发票号：</span>
      <el-input class="control-l" style="position: absolute; right: 390px" v-model="registerForm.id"></el-input>
      <span style="position: absolute; right: 260px; top: 137px">病历号：</span>
      <el-input class="control-l" style="position: absolute; right: 50px" v-model="patient.recordId"></el-input><br>
    </div>
    <h3 style="color: gray;">挂号信息</h3><br>
    <div>
      <!-- 第一行 -->
      <span>患者姓名：</span>
      <el-input class="control" v-model="patient.name"></el-input>
      <span>患者性别：</span>
      <el-select class="control" v-model="patient.gender">
        <el-option v-for="g in genders" :key="g.value" :label="g.label" :value="g.value"></el-option>
      </el-select>
      <span>出生日期：</span>
      <el-date-picker class="control" v-model="patient.birth" value-format="yyyy-MM-dd"></el-date-picker>
      <span>患者年龄：</span>
      <el-input class="control" v-model="patient.age"></el-input><br>
      <!-- 第二行 -->
      <span>身份证号：</span>
      <el-input class="control-xl" v-model="patient.id"></el-input>
      <span>家庭住址（可选）：</span>
      <el-input class="control-xl" style="width: 582px" v-model="patient.addr"></el-input><br>
      <!-- 第三行 -->
      <span>联系电话（可选）：</span>
      <el-input class="control-l" v-model="patient.tel" style="width: 223px;"></el-input>
      <span>挂诊科室：</span>
      <el-select class="control-xl" v-model="registerForm.departmentId" @change="handleChange(registerForm.departmentId)">
        <el-option v-for="d in departments" :key="d.id" :label="d.name" :value="d.id"></el-option>
      </el-select>
      <span>挂诊医师：</span>
      <el-select class="control-xl" v-model="registerForm.doctorId">
        <el-option v-for="d in doctors" :key="d.id" :label="d.name" :value="d.id"></el-option>
      </el-select><br>
      <span>挂号级别：</span>
      <el-select class="control" v-model="registerForm.level">
        <el-option v-for="l in levels" :key="l.value" :label="l.label" :value="l.value"></el-option>
      </el-select>
      <!-- 第四行 -->
      <span>购买病历本：</span>
      <el-radio v-model="registerForm.recordNeeded" label="false">否</el-radio>
      <el-radio v-model="registerForm.recordNeeded" label="true">是</el-radio>
    </div>
    <div class="bottom">
      <span>总费用：</span>
      <el-input class="control" v-model="costAll" readonly></el-input>
      <el-button type="primary" @click="register()">确认挂号</el-button>
      <el-button type="primary" plain @click="init()">刷新界面</el-button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Register',
  data() {
    return {
      // 性别常数
      genders: [
        {
          label: "男",
          value: 0
        },
        {
          label: "女",
          value: 1
        }
      ],
      // 科室列表
      departments: [],
      // 所选科室对应医师列表
      doctors: [],
      // 挂号级别常数
      levels: [
        {
          label: "普通号",
          value: 1
        },
        {
          label: "急诊号",
          value: 2
        },
        {
          label: "专家号",
          value: 3
        }
      ],
      registerForm: {
        id: null,
        patientId: null,
        departmentId: null,
        doctorId: null,
        level: null,
        recordNeeded: null,
        cost: null,
        state: false
      },
      patient: {
        name: null,
        gender: null,
        birth: null,
        age: null,
        id: null,
        addr: null,
        tel: null,
        recordId: null
      }
    }
  },
  methods: {
    searchPatientById(patient) {
      axios.post("/register/search_patient", {id: patient}).then((res)=>{
        if (res.data) this.patient = res.data
      })
    },
    handleChange(department) {
      axios.post("/register/doc_depart", {id: department}).then((res)=>{
        this.doctors = res.data
      })
    },
    register() {
      if (this.registerForm.id == null || this.registerForm.departmentId == null || this.registerForm.doctorId == null || 
      this.registerForm.level == null || this.registerForm.recordNeeded == null || this.costAll == null || 
      this.patient.name == null || this.patient.gender == null || this.patient.birth == null || this.patient.age == null || 
      this.patient.id == null || this.patient.recordId == null) {
        this.$message("请将信息填写完整")
      } else {
        this.registerForm.patientId = this.patient.id
        this.registerForm.cost = this.costAll
        axios.post("/register/save_patient", this.patient)
        axios.post("/register", this.registerForm)
        this.$message("挂号成功")
      }
    },
    init() {
      axios.post("/register/init_rigid").then((res)=>{
        this.registerForm.id = res.data
      })
      axios.post("/register/init_ricid").then((res)=>{
        this.patient.recordId = res.data
      })
      axios.post("/register/init_depart").then((res)=>{
        this.departments = res.data
      })
      this.doctors = []

      this.registerForm.patientId = null
      this.registerForm.department = null
      this.registerForm.doctor = null
      this.registerForm.level = null
      this.registerForm.recordNeeded = null
      this.registerForm.cost = null
      this.registerForm.state = false

      this.patient.name = null
      this.patient.gender = null
      this.patient.birth = null
      this.patient.age = null
      this.patient.id = null
      this.patient.addr = null
      this.patient.tel = null
    }
  },
  computed: {
    costAll: function() {
      var cost = Math.pow(this.registerForm.level, 2) * 5
      if (this.registerForm.recordNeeded == "true") cost += 1
      return cost
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