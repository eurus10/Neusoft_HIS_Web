<template>
  <div>
    <div>
      <el-input class="control-xl" v-model="patient.id" placeholder="身份证号"></el-input>
      <el-button type="primary" icon="el-icon-search" plain circle @click="searchDrugs(patient.id)"></el-button><br>
    </div>
    <div class="main">
      <span>姓名：</span>
      <el-input class="control" v-model="patient.name" readonly></el-input>
      <span>性别：</span>
      <el-input class="control" v-model="patient.gender" readonly></el-input>
      <span>年龄：</span>
      <el-input class="control" v-model="patient.age" readonly></el-input>
    </div>
    <div>
      <el-table :data="drugs" height="350px" style="margin-left: 30px; margin-right: 30px; width: 90%;" @selection-change="handleSelection">
        <el-table-column type="selection" :selectable="checkSelectable"></el-table-column>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="药品ID" prop="affairId"></el-table-column>
        <el-table-column label="药品名" prop="affairName"></el-table-column>
        <el-table-column label="数量" prop="num"></el-table-column>
        <el-table-column label="缴费状态" prop="paid"></el-table-column>
        <el-table-column label="项目状态" prop="state"></el-table-column>
      </el-table>
    </div>
    <div class="foot">
      <el-button type="primary" plain @click="drugRcv()">确认退药</el-button>
      <el-button type="warning" plain @click="cancel()">取消退药</el-button>
    </div>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: 'DrugRcv',
  data() {
    return {
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
      drugs: [],
      selected: []
    }
  },
  methods: {
    searchDrugs(patientId) {
      axios.post("/disp/get_patient", {id: patientId}).then((res)=>{
        this.patient = res.data
      })
      axios.post("/disp/affair", {id: patientId}).then((res)=>{
        this.drugs = res.data
      })
    },
    checkSelectable(row) {
      if (row.state == "未出药") return false;
      else return true;
    },
    handleSelection(val) {
      this.selected = val
    },
    drugRcv() {
      if (this.patient.name == null) {
        this.$message("请将信息填写完整")
      } else {
        axios.post("/drug_rcv", {affairs: this.selected})
        this.cancel()
        this.$message("退药成功")
      }
    },
    cancel() {
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
      this.drugs = [],
      this.selected = []
    }
  }
}
</script>

<style scoped>
.main {
  margin-top: 30px;
  margin-left: 30px;
}
.foot {
  margin-top: 50px;
  margin-left: 30px;
}
span {
  color: gray;
  font-size: 14px;
}
</style>