<template>
  <div>
    <div class="head">
      <el-input class="control-l" v-model="registerFormId" placeholder="发票号" style="margin-left: 30px; margin-top: 30px;"></el-input>
      <el-button type="primary" icon="el-icon-search" plain circle style="margin-right: 80px" @click="searchPatientId(registerFormId)"></el-button>
      <span>身份证号：</span>
      <el-input class="control-xl" v-model="patientId" style="margin-right: 80px" readonly></el-input>
      <el-button type="primary" @click="makePresc()">开立处方</el-button>
      <el-button type="primary" plain @click="init()">刷新页面</el-button>
      <el-button type="success" plain @click="newModelDrawer = true">新建模版</el-button>
    </div>
    <div class="main">
      <div class="box-1">
        <span class="table-title">已选处方</span><br>
        <el-table :data="prescList" height="250px" style="margin-left:30px; margin-right-30px; width: 90%">
          <el-table-column type="index"></el-table-column>
          <el-table-column label="处方ID">
            <template slot-scope="scope">{{scope.row.id}}</template>
          </el-table-column>
          <el-table-column label="处方名">
            <template slot-scope="scope">{{scope.row.name}}</template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="handleDel(scope.row)" plain>删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="box-2">
        <span class="table-title">处方模版</span><br>
        <el-table :data="prescModels" height="250px" style="margin-left:30px; margin-right-30px; width: 90%" highlight-current-row 
        @current-change="handleCurrentChangePresc">
          <el-table-column type="index"></el-table-column>
          <el-table-column label="处方ID">
            <template slot-scope="scope">{{scope.row.id}}</template>
          </el-table-column>
          <el-table-column label="处方名">
            <template slot-scope="scope">{{scope.row.name}}</template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="handleAdd(scope.row)" plain>添加</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="box-3">
        <span class="table-title">已选药品</span><br>
        <el-table :data="drugList" height="250px" style="margin-left:30px; margin-right:30px; width: 90%">
          <el-table-column type="index"></el-table-column>
          <el-table-column label="药品ID" prop="id"></el-table-column>
          <el-table-column label="药品名" prop="name"></el-table-column>
          <el-table-column label="药品数量" prop="num"></el-table-column>
        </el-table>
      </div>
      <div class="box-4">
        <span class="table-title">模版内容</span><br>
        <el-table :data="prescItems" height="250px" style="margin-left:30px; margin-right:30px; width: 90%">
          <el-table-column type="index"></el-table-column>
          <el-table-column label="药品ID" prop="id"></el-table-column>
          <el-table-column label="药品名" prop="name"></el-table-column>
          <el-table-column label="药品数量" prop="num"></el-table-column>
        </el-table>
      </div>
      <el-drawer title="新建处方模版" :visible.sync="newModelDrawer" direction="btt" size="50%">
        <div class="drug-table">
          <el-table :data="drugs" height="300px" highlight-current-row @current-change="handleCurrentChangeDrug">
            <el-table-column label="药品ID" prop="id"></el-table-column>
            <el-table-column label="药品编码" prop="code"></el-table-column>
            <el-table-column label="药品名" prop="name"></el-table-column>
          </el-table>
        </div>     
        <div class="add-drug-pane">
          <span>处方ID</span>
          <el-input class="control-l" v-model="prescModel.id" readonly></el-input>
          <span>处方名</span>
          <el-input class="control-l" v-model="prescModel.name"></el-input><br>
          <el-input v-model="drugCode" placeholder="药品编码" style="width: 180px"></el-input>
          <el-button type="primary" icon="el-icon-search" plain circle style="margin-bottom: 20px" @click="searchDrug(drugCode)">
          </el-button><br>
          <el-input v-model="drug.name" placeholder="添加药品" style="width: 180px" readonly></el-input>
          <el-input-number v-model="drug.num" :min="1" :max="99"></el-input-number>
          <el-button type="primary" icon="el-icon-check" plain circle @click="addDrug()"></el-button>
          <el-button type="primary" icon="el-icon-close" plain circle @click="delDrug()"></el-button><br><br><br>
          <el-button type="primary" @click="save()">保存处方</el-button>
          <el-button type="warning" plain @click="clear()">清空界面</el-button>
        </div>
      </el-drawer>
    </div>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: 'MakePresc',
  data() {
    return {
      newModelDrawer: false,
      registerFormId: null,
      patientId: null,
      prescList: [],
      drugListStr: null,
      drugList: [],
      prescModels: [],
      prescItems: [],
      drugs: [],
      prescModel: {
        id: null,
        name: null,
        drugs: []
      },
      drugCode: null,
      drug: {
        id: null,
        name: null,
        num: null
      }
    }
  },
  methods: {
    searchPatientId(registerFormId) {
      axios.post("/make_presc/patient_id", {id: registerFormId}).then((res)=>{
        this.patientId = res.data
      })
    },
    handleCurrentChangePresc(val) {
      this.prescItems = []
      this.drugsAnalysis(val.drugs, this.prescItems)
    },
    drugsAnalysis(str, list) {
      if (str == "" || str == null) list = []
      else {
        var items = str.split("_");
        for (var i=0; i<items.length; i++) {
          var atts = items[i].split("^")
          list.push({id: atts[0], name: atts[1], num: atts[2]})
        }
      }
    },
    handleCurrentChangeDrug(val) {
      this.drug.id = val.id
      this.drug.name = val.name
      this.drug.num = 1
      this.drugCode = val.code
    },
    handleAdd(row) {
      this.prescList.push(row)
      this.drugListStr = ""
      var len = this.prescList.length
      for (var i=0; i<len; i++) {
        if (i != 0) this.drugListStr += "_"
        this.drugListStr += this.prescList[i].drugs
      }
      this.drugList = []
      this.drugsAnalysis(this.drugListStr, this.drugList)
    },
    handleDel(row) {
      var target = -1
      var len = this.prescList.length
      for (var i=0; i<len; i++) {
        if (this.prescList[i].id == row.id) {
          target = i
          break
        }
      }
      this.prescList.splice(target, 1)
      this.drugListStr = ""
      var len = this.prescList.length
      for (var i=0; i<len; i++) {
        if (i != 0) this.drugListStr += "_"
        this.drugListStr += this.prescList[i].drugs
      }
      this.drugList = []
      this.drugsAnalysis(this.drugListStr, this.drugList)
    },
    makePresc() {
      if (this.patientId != null) {
        axios.post("/make_presc", {id: this.patientId, drugs: this.drugListStr})
        this.$message("处方开立成功")
      } else this.$message("请将信息填写完整")
    },
    init() {
      axios.post("/make_presc/init_presc").then((res)=>{
        if (res.data) this.prescModels = res.data
      })
      axios.post("/make_presc/init_drug").then((res)=>{
        if (res.data) this.drugs = res.data
      })
      this.registerFormId = null
      this.patientId = null
      this.drugListStr = null
      this.prescList = []
      this.drugList = []
      this.prescItems = []
      this.clear()
    },
    searchDrug(code) {
      var len = this.drugs.length
      var target = -1
      for (var i=0; i<len; i++) {
        if (this.drugs[i].code == code) {
          target = i
          break
        }
      }
      if (target != -1) {
        this.drug.id = this.drugs[i].id
        this.drug.name = this.drugs[i].name
        this.drug.num = 1
      }
    },
    addDrug() {
      if (this.drug.id != null) {
        this.prescModel.drugs.push(this.drug)
        this.$message("添加成功")
      } else this.$message("添加失败")
      this.drug = {
        id: null,
        name: null,
        num: 1
      }
      this.drugCode = null
    },
    delDrug() {
      var len = this.prescModel.drugs.length
      var target = -1
      for (var i=0; i<len; i++) {
        if (this.prescModel.drugs[i].id == this.drug.id) {
          target = i
          break
        }
      }
      if (target != -1) {
        this.prescModel.drugs.splice(target, 1)
        this.$message("删除成功")
      } else this.$message("删除失败")
      this.drug = {
        id: null,
        name: null,
        num: 1
      }
      this.drugCode = null
    },
    save() {
      var len = this.prescModel.drugs.length
      var drugsStr = ""
      for (var i=0; i<len; i++) {
        var drug = this.prescModel.drugs[i]
        var drugStr = drug.id + "^" + drug.name + "^" + drug.num
        if (i != 0) drugsStr += "_"
        drugsStr += drugStr
      }
      axios.post("/make_presc/save_presc", {id: this.prescModel.id, name: this.prescModel.name, drugs: drugsStr})
      this.prescModels.push({id: this.prescModel.id, name: this.prescModel.name, drugs: drugsStr})
    },
    clear() {
      this.drug = {
        id: null,
        name: null,
        num: 1
      }
      this.drugCode = null
      axios.post("/make_presc/init_id").then((res)=>{
        this.prescModel.id = res.data
      })
      this.prescModel.name = null
      this.prescModel.drugs = []
    }
  },
  mounted() {
    this.init()
  }
}
</script>

<style scoped>
.head {
  height: 10%;
  width: 85%;
  position: absolute;
  top: 110px;
  left: 210px;
  /* background-color: red; */
}
.main {
  margin-top: 10px;
  height: 72%;
  width: 85%;
  position: absolute;
  top: 200px;
  left: 210px;
  /* background-color: green; */
}
.box-1 {
  height: 50%;
  width: 50%;
  text-align: center;
  /* background-color: blue; */
}
.box-2 {
  height: 50%;
  width: 50%;
  text-align: center;
  /* background-color: orange; */
}
.box-3 {
  height: 50%;
  width: 50%;
  text-align: center;
  /* background-color: purple; */
  position: relative;
  left: 50%;
  bottom: 100%;
}
.box-4 {
  height: 50%;
  width: 50%;
  text-align: center;
  /* background-color: brown; */
  position: relative;
  left: 50%;
  bottom: 100%;
}
.table-title {
  color: #409EFF;
  font-size: 16px;
  font-weight: bold;
}
.drug-table {
  width: 60%;
  height: 100%;
  /* background-color: red; */
}
.add-drug-pane {
  width: 60%;
  height: 100%;
  position: absolute;
  top: 100px;
  left: 900px;
  /* background-color: green; */
}
span {
  color: gray;
  font-size: 14px;
}
</style>