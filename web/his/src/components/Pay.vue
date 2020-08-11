<template>
  <div>
    <div>
      <el-input class="control-xl" v-model="patient.id" placeholder="身份证号"></el-input>
      <el-button type="primary" icon="el-icon-search" plain circle @click="searchAffairs(patient.id)"></el-button><br>
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
      <el-table :data="affairs" height="350px" style="margin-left: 30px; margin-right: 30px; width: 90%;" @selection-change="handleSelection">
        <el-table-column type="selection" :selectable="checkSelectable"></el-table-column>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="缴费项目ID" prop="affairId"></el-table-column>
        <el-table-column label="缴费项目名" prop="affairName"></el-table-column>
        <el-table-column label="数量" prop="num"></el-table-column>
        <el-table-column label="总价" prop="cost"></el-table-column>
        <el-table-column label="缴费状态" prop="paid"></el-table-column>
        <el-table-column label="项目状态" prop="state"></el-table-column>
      </el-table>
    </div>
    <div class="foot">
      <span>缴费总额：</span>
      <el-input class="control" v-model="costAll" readonly></el-input>
      <el-button type="primary" plain @click="pay()">缴费结算</el-button>
      <el-button type="warning" plain @click="cancel()">取消缴费</el-button>
    </div>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: 'Pay',
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
      affairs: [],
      selected: [],
      costAll: 0
    }
  },
  methods: {
    searchAffairs(patientId) {
      axios.post("/fin/get_patient", {id: patientId}).then((res)=>{
        this.patient = res.data
      })
      axios.post("/fin/affair", {id: patientId}).then((res)=>{
        this.affairs = res.data
      })
    },
    checkSelectable(row) {
      if (row.paid == "已缴费") return false;
      else return true;
    },
    handleSelection(val) {
      this.selected = val
      this.costAll = 0
      var len = val.length
      for (var i=0; i<len; i++) {
        this.costAll += val[i].cost
      }
    },
    pay() {
      if (this.patient.name == null) {
        this.$message("请将信息填写完整")
      } else {
        axios.post("/pay", {affairs: this.selected})
        this.cancel()
        this.$message("缴费成功")
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
      this.affairs = [],
      this.selected = [],
      this.costAll = 0
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