<template>
  <div>
    <div class="table-pane-1">
      <span class="table-title">待诊患者</span><br>
      <el-table :data="undiagnosedList" height="300px" highlight-current-row @current-change="handleCurrentChangePatient">
        <el-table-column label="发票号" prop="id"></el-table-column>
        <el-table-column label="患者姓名" prop="name"></el-table-column>
      </el-table>
    </div>
    <div class="table-pane-2">
      <span class="table-title">已诊患者</span><br>
      <el-table :data="diagnosedList" height="300px" highlight-current-row @current-change="handleCurrentChangePatient">
        <el-table-column label="发票号" prop="id"></el-table-column>
        <el-table-column label="患者姓名" prop="name"></el-table-column>
      </el-table>
    </div>
    <div class="info-pane">
      <span class="table-title">病历首页</span><br>
      <div class="patient-info">
        <span style="margin-right: 30px;">病历号：{{patient.recordId}}</span>
        <span style="margin-right: 30px;">患者姓名：{{patient.name}}</span>
        <span style="margin-right: 30px;">年龄：{{patient.age}}</span>
        <span style="margin-right: 30px;">性别：{{patient.gender}}</span>
      </div>
      <div class="record-info">
        <span>主诉：</span><br>
        <el-input v-model="record.complaint" type="textarea"></el-input>
        <span>现病史：</span><br>
        <el-input v-model="record.diseaseLog" type="textarea"></el-input>
        <span>过敏史：</span><br>
        <el-input v-model="record.allergicLog" type="textarea"></el-input>
        <span>诊断结果：</span><br>
        <el-input v-model="record.diseases" style="width: 90%;"></el-input>
        <el-button type="primary" icon="el-icon-plus" circle size="small" plain @click="addDiseaseDrawer=true"></el-button><br>
        <span>诊断建议：</span><br>
        <el-input v-model="record.advice" type="textarea"></el-input><br>
      </div>
      <div class="record-operate">
        <el-button type="primary" @click="uploadRecord()">提交病历</el-button>
        <el-button type="primary" plain @click="newPage()">新开一页</el-button>
        <el-button type="danger" plain @click="init()">刷新界面</el-button>
      </div>
      <el-drawer title="添加疾病" :visible.sync="addDiseaseDrawer" direction="btt" size="50%">
        <div class="disease-table">
          <el-table :data="diseases" height="300px" highlight-current-row @current-change="handleCurrentChangeDisease">
            <el-table-column label="国际ICD编码" prop="icd"></el-table-column>
            <el-table-column label="疾病编码" prop="code"></el-table-column>
            <el-table-column label="疾病名" prop="name"></el-table-column>
          </el-table>
        </div>     
        <div class="add-disease-pane">
          <el-input v-model="diseaseCode" placeholder="疾病编码" style="width: 180px"></el-input>
          <el-button type="primary" icon="el-icon-search" plain circle style="margin-right: 50px" @click="searchDisease(diseaseCode)">
          </el-button>
          <el-input v-model="diseaseName" placeholder="添加疾病" style="width: 180px"></el-input>
          <el-button type="primary" icon="el-icon-check" plain circle @click="addDisease(diseaseName)"></el-button>
        </div>
      </el-drawer>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Diagnose',
  data() {
    return {
      addDiseaseDrawer: false,
      undiagnosedList: [],
      diagnosedList: [],
      patient: {
        id: null,
        name: null,
        gender: null,
        birth: null,
        age: null,
        addr: null,
        tel: null,
        recordId: null
      },
      record: {
        order: null,
        id: null,
        complaint: null,
        diseaseLog: null,
        allergicLog: null,
        diseases: null,
        advice: null
      },
      registerFormId: null,
      diseases: [],
      diseaseCode: null,
      diseaseName: null,
      updated: false
    }
  },
  methods: {
    handleCurrentChangePatient(val) {
      this.registerFormId = val.id
      axios.post("/diagnose/search_reg", {id: val.id}).then((res)=>{
        if (res.data) {
          axios.post("/diagnose/search_patient", {id: res.data.patientId}).then((res)=>{
            if (res.data) {
              this.patient = res.data
              axios.post("/diagnose/search_rec", {id: res.data.recordId}).then((res)=>{
                if (res.data) this.record = res.data
                if (res.data.complaint == null) this.updated = true
              })
            }
          })
        }
      })
    },
    handleCurrentChangeDisease(val) {
      this.diseaseCode = val.code
      this.diseaseName = val.name
    },
    searchDisease(code) {
      var len = this.diseases.length
      for (var i=0; i<len; i++) {
        if (this.diseases[i].code == code) {
          this.diseaseName = this.diseases[i].name
          break
        }
      }
    },
    addDisease(name) {
      if (this.record.diseases == null) this.record.diseases = name
      else {
        this.record.diseases += "/"
        this.record.diseases += name
      }
      this.diseaseCode = null
      this.diseaseName = null
    },
    uploadRecord() {
      if (this.updated) {
        axios.post("/diagnose/insert", this.record)
        axios.post("/diagnose", {id: this.registerFormId})
        this.$message("已提交病历")
      } else if (this.patient.id == null) {
        this.$message("请将信息填写完整")
      } else {
        axios.post("/diagnose/update", this.record)
        axios.post("/diagnose", {id: this.registerFormId})
        this.$message("已提交病历")
      }
    },
    newPage() {
      this.updated = true
      this.record.order += 1
      this.record.complaint = null
      this.record.diseaseLog = null
      this.record.allergicLog = null
      this.record.diseases = null
      this.record.advice = null
    },
    init() {
      axios.post("/diagnose/init_undia", {id: this.$store.state.user.id}).then((res)=>{
        if (res.data) this.undiagnosedList = res.data
      })
      axios.post("/diagnose/init_diag", {id: this.$store.state.user.id}).then((res)=>{
        if (res.data) this.diagnosedList = res.data
      })
      axios.post("/diagnose/init_disease").then((res)=>{
        if (res.data) this.diseases = res.data
      })
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
      this.record = {
        order: null,
        id: null,
        complaint: null,
        diseaseLog: null,
        allergicLog: null,
        diseases: null,
        advice: null
      }
      this.updated = false
      this.diseaseCode = null
      this.diseaseName = null
    }
  },
  mounted() {
    this.init()
  }
}
</script>

<style scoped>
.table-pane-1 {
  width: 250px;
  height: 300px;
  text-align: center;
}
.table-pane-2 {
  width: 250px;
  height: 300px;
  text-align: center;
}
.info-pane {
  width: 60%;
  height: 80%;
  position: absolute;
  left: 550px;
  top: 130px;
  /* background-color: green; */
}
.patient-info {
  width: 500px;
  height: 100px;
  position: absolute;
  top: 50px;
}
.record-info {
  width: 100%;
  height: 65%;
  position: absolute;
  top: 100px;
/* background-color: red; */
}
.record-operate {
  width: 100%;
  height: 20%;
  position: absolute;
  top: 550px;
/* background-color: green; */
}
.disease-table {
  width: 60%;
  height: 100%;
  /* background-color: red; */
}
.add-disease-pane {
  width: 60%;
  height: 100%;
  position: absolute;
  top: 100px;
  left: 900px;
  /* background-color: green; */
}
.table-title {
  color: #409EFF;
  font-size: 16px;
  font-weight: bold;
}
span {
  color: gray;
  font-size: 14px;
}
</style>