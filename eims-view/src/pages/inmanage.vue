<template>

  <div>
    <el-container>
      <el-container>
        <el-header style="text-align: left; font-size: 12px">
  单据号：
                <el-input v-model="select" placeholder="请输入单据号" style="width: 250px"  @keyup.enter.native="onSelect"></el-input>
                <el-button type="primary" icon="el-icon-search"  @click="onSelect">查询</el-button>
                <el-button type="primary"  plain @click="reset">重置</el-button>
                <el-button  icon="el-icon-refresh" @click="getData">刷新</el-button>

                <br/>
              <br/>

                <el-button type="primary"  @click="OpenCreate" >新增</el-button>

            </el-header>

        <el-dialog title="提示" :visible.sync="dialogVisible" width="30%"
          :before-close="handleClose">
          <span>您确定删除这一单据吗？</span>
          <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="Ondel()">确 定</el-button>
                 </span>
        </el-dialog>

        <el-dialog title="提示" :visible.sync="dialogVisibledel" width="30%"
        :before-close="handleClose">
        <span>您确定删除这一物资吗？</span>
        <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisibledel = false">取 消</el-button>
              <el-button type="primary" @click="Ondelgoods()">确 定</el-button>
               </span>
      </el-dialog>

      <el-dialog title="提示" :visible.sync="dialogVisiblepush" width="30%"
      :before-close="handleClose">
      <span>您确定提交吗？</span>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisiblepush = false">取 消</el-button>
            <el-button type="primary" @click="Onpush()">确 定</el-button>
             </span>
    </el-dialog>

        <el-dialog title="新增入库单" v-model="cdialogVisible" :visible.sync="cdialogVisible"
                   :close-on-click-modal="false" :close-on-press-escape="false" customClass="customWidth"  @close="resetForm">

          <el-form  :model="formInline" ref="formInline" :visible.sync="formInline" :inline="true" :rules="rules"
                    style="text-align: left">
            <el-form-item label="单据号:" >
              <span>{{danju}}</span>
            </el-form-item>
            <el-form-item style="width: 100px"></el-form-item>
            <el-form-item label="制单人:">
              {{username}}
            </el-form-item>
            <el-form-item style="width: 100px"></el-form-item>
            <el-form-item label="制单日期:">
              {{nowDate}}
            </el-form-item>
            <br/>
            <el-form-item label="仓库：" prop="region"  >
              <el-select v-model="formInline.region" placeholder="请选择" style="width: 200px" >
                <el-option selected ="selected"   value=''>请选择</el-option>
                <el-option  v-for="warehouse in warehouse" :value="warehouse.id" :label="warehouse.name"  :key="warehouse.id" ></el-option>
              </el-select>
            </el-form-item>
          </el-form>


          <el-form ref="form" :model="form" label-width="80px" >
            <el-table  ref="multipleTable" :data="tableData" :header-cell-style="{background:'#F2F6FC',color:'#303133'}" >
              <el-table-column type="index" label="序号" prop="goodsId" width="80">
              </el-table-column>
              <el-table-column label="条形码"  width="200" prop="goodsBarcode" >
                <template slot-scope="scope" >
                  <div slot="reference" class="name-wrapper">
                    <el-input v-model="scope.row.goodsBarcode" @keyup.enter.native="csearchcode(scope.$index,scope.row)" placeholder="请输入内容"  ></el-input>
                  </div>
                </template>
              </el-table-column>

              <el-table-column  label="名称" prop="goodsName" width="120">
              </el-table-column>

              <el-table-column label="单位" prop="goodUnit" width="120">
              </el-table-column>
              <el-table-column label="规格"  prop="goodsSpec" width="120">
              </el-table-column>
              <el-table-column label="颜色" prop="goodColor" width="120">
              </el-table-column>
              <el-table-column label="品牌" prop="goodsBrand" width="120">
              </el-table-column>
              <el-table-column label="入库数量" prop="inOrOutNum" placeholder="请输入数量" width="120">
                <template  slot-scope="scope">
                  <el-input v-model="scope.row.inOrOutNum"></el-input>
                </template>
              </el-table-column>

              <el-table-column label="库存" prop="goodsStock" width="120">
              </el-table-column>

              <el-table-column fixed="right" label="删除" width="100">
                <template slot-scope="scope">
                  <el-button size="mini" type="text"
                             @click="handleDelete1(scope.$index, tableData)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <center>
              <el-button type="primary"  @click="onSubmit('formInline')">提交</el-button>
              <el-button type="primary" @click="saveData('formInline')">保存</el-button>
              <el-button  @click="resetForm()">取消</el-button>
              <el-button  @click="resetForm()">关闭</el-button>
            </center>
          </el-form>
        </el-dialog>

        <el-dialog title="编辑出库单" v-model="edialogVisible" :visible.sync="edialogVisible"
                  :close-on-click-modal="false" :close-on-press-escape="false" customClass="customWidth" >

                 <el-form :model="edit" ref="edit" :inline="true" class="demo-form-inline" width="200"
                        :ref="edit"  style="text-align: left" :rules="rules">
                    <el-form-item label="单据号："> {{danju2}}  </el-form-item>
                   <el-form-item style="width: 100px"></el-form-item>
                    <el-form-item label="制单人："> {{userName}}   </el-form-item>
                   <el-form-item style="width: 100px"></el-form-item>
                    <el-form-item label="制单日期："> {{makeDate}} </el-form-item>
                <br/>
                    <el-form-item label="仓库：" prop="name">
                      {{houseName}}
                    </el-form-item>

                  <el-table    ref="multipleTable" :data="Editlist"  :header-cell-style="{background:'#F2F6FC',color:'#303133'}">
                    <el-table-column type="index" label="序号"  prop="goodsId" width="80">
                    </el-table-column>
                    <el-table-column label="条形码"  prop="goodsBarcode" width="200">
                      <template slot-scope="scope">
                        <div slot="reference" class="name-wrapper">
                          <el-input v-model="Editlist[scope.$index].goodsBarcode" @keyup.enter.native="searchcode(scope.$index,scope.row)"></el-input>
                        </div>
                      </template>
                    </el-table-column>
                    <el-table-column label="名称" prop="goodsName" width="120"></el-table-column>
                    <el-table-column label="单位" prop="goodUnit" width="120"></el-table-column>
                    <el-table-column label="规格"  prop="goodsSpec" width="120"></el-table-column>
                    <el-table-column label="颜色" prop="goodColor" width="120"></el-table-column>
                    <el-table-column label="品牌" prop="goodsBrand" width="120"></el-table-column>
                    <el-table-column label="入库数量"  prop="inOrOutNum"  width="120">
                      <template  slot-scope="scope">
                        <el-input v-model="Editlist[scope.$index].inOrOutNum">  </el-input>
                      </template>
                    </el-table-column>
                    <el-table-column label="库存" prop="goodsStock" width="120"></el-table-column>

                    <el-table-column fixed="right" label="操作" width="100">
                      <template slot-scope="scope">
                        <el-button size="mini" type="danger"  @click="goodsdel(scope.$index,scope.row)">删除</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                </el-form>

                <div slot="footer" class="dialog-footer">
       <center> <el-button type="primary" @click="onSubmit2">提交</el-button>
                <el-button type="primary"  @click="onEdit">保存</el-button>
                <el-button @click="edialogVisible=false">取消</el-button>
                <el-button @click="edialogVisible=false">关闭</el-button>
       </center>

                </div>
              </el-dialog>

              <el-dialog title="查看出库单" :visible.sync="dialogTableVisibleout" customClass="customWidth">

                <div style="text-align: left" >
                  <el-form :inline="true">
                    <el-form-item label="单据号："> {{inOrOutId}}  </el-form-item>
                    <el-form-item style="width: 100px"></el-form-item>
                    <el-form-item label="制单人："> {{userName}}   </el-form-item>
                    <el-form-item style="width: 100px"></el-form-item>
                    <el-form-item label="制单日期："> {{makeDate}} </el-form-item>
                    <br/>
                    <span>仓  库：</span> <span>{{houseName}}</span>
                    <br/>
                    <br/>
                  </el-form>
                </div>

                <el-table :data="gridData1"   ref="multipleTable"  :header-cell-style="{background:'#F2F6FC',color:'#303133'}">
                <el-table-column type="index" label="序号"style="width:11.1%" prop="id">
                </el-table-column>
                <el-table-column label="条形码"  prop="goodsBarcode" style="width:11.1%"></el-table-column>
                <el-table-column label="名称" prop="goodsName"style="width:11.1%"></el-table-column>
                <el-table-column label="单位" prop="goodUnit" style="width:11.1%"></el-table-column>
                <el-table-column label="规格"  prop="goodsSpec" style="width:11.1%"></el-table-column>
                <el-table-column label="颜色" prop="goodColor"style="width:11.1%"></el-table-column>
                <el-table-column label="品牌" prop="goodsBrand" style="width:11.1%"></el-table-column>
                <el-table-column label="入库数量"  prop="inOrOutNum" style="width:11.1%"></el-table-column>
                <el-table-column label="库存" prop="goodsStock" style="width:11.1%"></el-table-column>
              </el-table>
                <el-button type="primary"  @click="dialogTableVisibleout=false" >关闭</el-button>
              </el-dialog>

<br/>
    <br/>

            <el-main>
              <el-table :data="tableDatain" :header-cell-style="{background:'#F2F6FC',color:'#303133'}" >
                       <el-table-column  type="index"  label="序号" style="width:16.6%" prop="id">
                         <template slot-scope="scope">
                         <span>{{(page - 1) * size + scope.$index + 1}}</span>
                       </template></el-table-column>
                        <el-table-column label="状态" prop="status"  width="180">
                            <template slot-scope="scope">
                            <span v-if="scope.row.status==0" >未提交</span>
                            <span v-if="scope.row.status==1" >已提交</span>
                          </template>                 
                          </el-table-column>
                        <el-table-column label="单据号" prop="inOrOutId"  style="width:16.6%"></el-table-column>
                        <el-table-column label="制单人" prop="userId"  style="width:16.6%"></el-table-column>
                        <el-table-column label="制单日期"  prop="makeDate"   style="width:16.6%">
<!--                        <template slot-scope="scope">-->
<!--                          <span >{{ makeDate | formatDate}}</span>-->
<!--                        </template>-->
                        </el-table-column>
                        <el-table-column label="仓库" prop="warehouseId"  style="width:16.6%" v-if="show"></el-table-column>
                        <el-table-column label="仓库名" prop="warehouseName"  style="width:16.6%" v-if="show"></el-table-column>

                        <el-table-column fixed="right" label="操作" style="width:16.6%" class="caozuo">
                          <template slot-scope="scope">
                            <el-button type="text" size="mini" @click="handleScan(scope.row)" v-if="scope.row.status==1">查看</el-button>
                            <el-button type="text" size="mini" @click="handleEdit(scope.$index, scope.row)" v-if="scope.row.status==0">编辑</el-button>
                            <el-button type="text" size="mini" @click="handlePush(scope.row)" v-if="scope.row.status==0">提交</el-button>
                            <el-button type="text" size="mini" @click="handleDelete(scope.row)" v-if="scope.row.status==0">删除</el-button>
                          </template>
                         </el-table-column>
                         </el-table>
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="page"
                :page-sizes="[5, 10, 20]"
                :page-size="size"
                style="float:right"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
              </el-pagination>
               </el-main>
          </el-container>
      </el-container>

      </div>
    </template>


    <script>
import Cookies from 'js-cookie';
import moment from 'moment'
    export default {
      data() {
        return {
          selected:true,  //select选中
          show:false,   //隐藏列
          Gid:'',
          userId:'',
          pushid:'',
          curId:'',
          dialogVisible:false,        //删除单据提示
          dialogVisiblepush:false,   //
          cdialogVisible: false,    //新增对话框
          edialogVisible: false,    //编辑对话框
          dialogVisibledel: false,  //删除提示
          dialogTableVisibleout:false,  //查看入库单
          total: 0,
          size: 10,
          page: 1,
          // inorouttable:[],
          warehouse:[],    //下拉列表绑定仓库名
          tableDatain: [],

          i:'',
          goodslist:[],//新增
          userId:'',   //制单人id
          goodsid:'',
          status:'',
          username:'',
          nowDate:'',
          danju:'',    //生成单据号
          Qbarcode:'',
          unit:'',
          spec:'',
          color:'',
          brand:'',
          stock:'',
          formInline: {
            region: ''
          },
          form:{
            tableData:[],
          },
                         //  }

          tableData: [{}],
          userform:{},
          input:'',
          id:'',
          rules: {
            region: [{required: true, message: '请选择仓库', trigger:['blur', 'change'] } ],
          },


          ide:'',   //编辑
          select:'',   //按单据号查询
          inOrOutId:'',
          danju2:'',    //编辑
          userName:'',
          makeDate:'',
          houseName:'',
          name:'',     //仓库名
          wid:'',      //编辑时获取的仓库id
          Editlist:[{}],
          edit:{
          //   region:'',
          },
          id:'',  //查看
          gridData1: [],//查看
        }



      },


      watch:{
        //2.x版本的bug 以前用1.x发现没有 假如现在是第三页，只有一条数据了。将其删除，就没有第三页了。应该跳到第二页展示出5条数据。
        total(){
          if(this.total==(this.page-1)*this.size&& this.total!=0){
            this.page-=1;
            this.getData()
          }
        }
      },

      filters: {//过滤器
        //时间戳转日期
        formatDate(value) {
          let date = new Date(value * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
          let y = date.getFullYear();
          let MM = date.getMonth() + 1;
          MM = MM < 10 ? ('0' + MM) : MM;
          let d = date.getDate();
          d = d < 10 ? ('0' + d) : d;
          return y + '-' + MM + '-' + d;
        }
      },


      mounted () {
       this.getData()
       this.getUserID()
       this.getWh()
       this.username = Cookies.get("username")
      } ,



      methods: {

        getTime(){    //获取时间
          setInterval(()=>{
            //new Date() new一个data对象，当前日期和时间
            //toLocaleString() 方法可根据本地时间把 Date 对象转换为字符串，并返回结果。
            this.nowDate = new Date().toLocaleDateString()
          },1000)
          this.getUserID()
        },


        reset() {
          this.select = ""
          this.getData()
        },

        OpenCreate(){       //打开新增对话框
          this.getTime()
          this.cdialogVisible = true
          this.Danju()
          this.username = Cookies.get("username")
        },

        Danju(){         //生成单据号
          this.axios({
            method: 'get',
            url: '/into/addButton',
          }).then((response) => {
            console.log(response)
            this.danju = response.data
          }).catch((error) => {
            console.log(error)
          })
        },

        resetForm(){         //清空
          this.tableData = [{}]   //新增表格重置
          this.formInline.region = '请选择' //下拉框重置
          this.cdialogVisible = false
        },

        handleSizeChange(val) {
          this.size = val
          this.getData()
        },
        handleCurrentChange(val) {
          this.page = val
          this.getData()
        },

        getTotal() {
          let self = this
          this.axios({
            method: 'get',
            url: '/into/InNum',
          }).then((response) => {
            console.log(response)
            console.log(response.data)
            self.total = response.data
          }).catch((error) => {
            console.log(error)
          })
        },


        csearchcode(index,row){    //新增出库单，回车查询物资
          if (this.$refs.formInline.validate()) {
            if (this.tableData[index].goodsBarcode == null) {
              this.$message( {type: 'warning',
                message: '请输入条形码'
              })
            }else {
               this.QinOrOutNum = this.tableData[index].inOrOutNum
              let self = this
              this.axios({
                url: '/out/ByBarcode/' + this.formInline.region + '/' + row.goodsBarcode,
                method: 'get',
              }).then(function (response) {
                console.log(response.data)
                self.tableData[index].goodsId = response.data.id;
                self.tableData[index].goodsName = response.data.name;
                self.tableData[index].goodsBarcode = response.data.barcode;
                self.tableData[index].goodColor = response.data.color;
                self.tableData[index].goodUnit = response.data.unit;
                self.tableData[index].goodsSpec = response.data.spec;
                self.tableData[index].goodsBrand = response.data.brand;
                self.tableData[index].goodsStock = response.data.stock;
                self.tableData.push({});    //增加一行
              }).catch((error) => {
                console.log(error)
              });
            }
          }else{
            return false
          }
        },

        handleDelete1(index, rows){  //删除未保存的详细记录
          rows.splice(index, 1);
        },

        NumifNull(){
          for(var i=0;i<this.tableData.length;i++){
            if( this.tableData[i].goodsBarcode != null && this.tableData[i].inOrOutNum == null ){
              return 0;
            }
          }
        },

        onSubmit(formName) {    //提交
          this.$refs[formName].validate((valid) => {
            if (valid) {
              if(this.NumifNull()== 0){
              this.$message({
                message: '物资的入库数量不能为空',
                type: 'warning'
              })
            } else {
                this.$confirm('您确定提交吗?', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning'
                }).then(() => {
                  let self = this
                  this.getTime()
                  self.tableData.pop()
                  this.axios({
                    url: '/into/addIn',
                    method: 'post',
                    data: {
                      exitStatus: 1,
                      inOrOutId: self.danju,
                      inOrOutStatus: 1,   //入出库 ，1为入库，2 为出库
                      status: 1,          //1为提交，0为保存
                      userId: self.userId,
                      warehouseId: this.formInline.region,
                      listComplexes: self.tableData
                    }
                  }).then(function (result) {
                    console.log(result)

                  }).catch((error) => {
                    console.log(error)
                  });
                  this.cdialogVisible = false
                  this.getData()
                  this.getTotal()
                })
              }
            }else {
              console.log('error submit!!');
              return false;
            }
          })

        },

        saveData(formName){           //保存
          this.$refs[formName].validate((valid) => {
            if (valid) {
              if(this.NumifNull()==0){
                this.$message({
                  message: '物资的入库数量不能为空',
                  type: 'error'
                })
              }else {
                  let self = this
                  this.getTime()
                  self.tableData.pop()
                  this.axios({
                    url: '/into/addIn',
                    method: 'post',
                    data: {
                      exitStatus: 1,
                      inOrOutId: self.danju,
                      inOrOutStatus: 1,   //入出库 ，1为入库，2 为出库
                      status: 0,          //1为提交，0为保存
                      userId: self.userId,
                      warehouseId: this.formInline.region,
                      listComplexes: self.tableData
                    }
                  }).then(function (result) {
                    console.log(result)
                  }).catch((error) => {
                    console.log(error)
                  });
                this.getData()
                this.getTotal()
                this.cdialogVisible = false
              }
            }else {
              console.log('error submit!!');
              return false;
            }
          })

        },

        //单个删除
        handleClose(done) {
          done();
        },
        //删除单据
          handleDelete( row) {
              this.dialogVisible = true;
              this.curId = row.id
            },
         Ondel(){
            console.log(this.curId)
              this.axios({
            method:'delete',
            url: '/into/delSeOut/'+this.curId,
          }).then(function (result) {
                console.log(result)
          });
          this.dialogVisible = false;
          this.getData()
          this.getTotal()
        },


        searchcode(index,row){      // 编辑，回车查询物资
        let self=this
       this.axios({
        url:'/out/ByBarcode/' + this.wid + '/' + row.goodsBarcode,
        method: 'get',
      }).then(function(response) {
        console.log(response.data)
        self.Editlist.push({});
        self.Editlist[index].goodsId=response.data.id;
        self.Editlist[index].goodsName=response.data.name;
        self.Editlist[index].goodsBarcode=response.data.barcode;
        self.Editlist[index].goodColor=response.data.color;
        self.Editlist[index].goodUnit=response.data.unit;
        self.Editlist[index].goodsSpec=response.data.spec;
        self.Editlist[index].goodsBrand=response.data.brand;
        self.Editlist[index].goodsStock=response.data.stock;
        console.log( self.Editlist)

      })
      .catch((error) => {
      console.log(error)
    });
        },

            //入库详细列表删除
          goodsdel(index,row){
          this.dialogVisibledel=true,
          this.Gid = this.Editlist[index].goodsId
          console.log( this.Gid)
         },

        Ondelgoods(){
        this.axios({
            method:'delete',
            url: '/into/delCxIn',
            data:{
              goodsId:  this.Gid,
              id: this.ide
            }
          }).then(function (result) {
            console.log(result)
          });
          this. getEditlist()
          this.dialogVisibledel = false
       },

        //按单据号查询
        onSelect() {
          let self = this
          this.axios({
            method: 'get',
            url: '/out/ById/' + self.select,
          }).then((response) => {
            console.log(response)
            self.tableDatain = response.data
            console.log(self.tableDatain)
          }).catch((error) => {
              console.log(error)
            })
        },




        //编辑
        handleEdit(index,row) {
            this.ide = row.id
            this.danju2 = row.inOrOutId,
            this.getEditlist()   //查询详细入库列表
            this.userName=row.userId,
            this.makeDate=row.makeDate,
            this.houseName=row.warehouseName    //获取仓库名
            this.wid = row.warehouseId
            this.edialogVisible = true;
        },

        CheckNum(){
          for(var i=0;i<this.Editlist.length;i++){
            if( this.Editlist[i].goodsBarcode != null && this.Editlist[i].inOrOutNum == null ){
              return 0 ;
            }
          }
        },
        onSubmit2() {    //提交
          if(this.CheckNum() ==0){
            this.$message({
              message: '物资的入库数量不能为空',
              type: 'error'
            })
          }else {
                this.$confirm('您确定提交吗?', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning'
                }).then(() => {
                  let self = this
                  if(this.Editlist[this.Editlist.length -1].goodsBarcode == null){
                    this.Editlist.pop()
                  }
                  this.getTime()
                  this.axios({
                    url: '/into/addIn',
                    method: 'post',
                    data: {
                      id: this.ide,
                      inOrOutId: this.danju2,
                      exitStatus: 1,
                      inOrOutStatus: 1,
                      status: 1,
                      userId: self.userId,
                      // userId: 2,
                      warehouseId: this.wid ,
                      listComplexes: this.Editlist
                    }
                  }).then(function (result) {
                    console.log(result)

                  }).catch((error) => {
                    console.log(error)
                  });
                  this.getData()
                  this.edialogVisible = false
                })
              }
        },

        //编辑界面保存按钮
        onEdit() {
          if(this.CheckNum() ==0){
            this.$message({
              message: '物资的入库数量不能为空',
              type: 'error'
            })
          } else {
             if(this.Editlist[this.Editlist.length -1].goodsBarcode == null){
              this.Editlist.pop()
            }
              let self = this
              this.getTime()
              this.axios({
                url: '/into/addIn',
                method: 'post',
                data: {
                  id: this.ide,
                  inOrOutId: this.danju2,
                  exitStatus: 1,
                  inOrOutStatus: 1,
                  status: 0,
                  userId: self.userId,
                  // userId: 2,
                  warehouseId: this.wid ,
                  listComplexes: this.Editlist
                }
              }).then(function (result) {
                console.log(result)
              }).catch((error) => {
                console.log(error)
              });
            this.getData()
            this.edialogVisible = false
          }
        },

        handlePush(row){
          this.pushid = row.id
          this.dialogVisiblepush = true
        },

        Onpush(){     //提交操作
          this.axios({
            method: 'get',
            url: '/into/checkOut/' + this.pushid,
          }).then((response) => {
            console.log(response)
            this.getData()
            this.dialogVisiblepush = false
          }).catch((error) => {
              console.log(error)
            })
        },

        //出库列表
        getData() {
          let self = this
          this.getTotal()
          this.axios({
            method: 'post',
            url: '/into/sListPage' ,
            data: {
              pageSize: this.size,
              currPage: this.page
            }
          }).then((response) => {
            console.log(response)
            console.log(response.data)
            self.tableDatain = response.data
            for(var i=0;i<self.tableDatain.length;i++)
            {
              self.tableDatain[i].makeDate= moment(self.tableDatain[i].makeDate).format('YYYY-MM-DD')
            }
          }).catch((error) => {
              console.log(error)
            })
        },


        //查看
        handleScan(row) {
            this.dialogTableVisibleout=true
            this.id = row.id,
            this.inOrOutId=row.inOrOutId,
            this.userName=row.userId,
            this.makeDate=row.makeDate,
            this.houseName=row.warehouseName,
            this.getGridData1()
        },

        //获取出库单列表
        getGridData1() {
          let self = this
          this.axios({
            method: 'get',
            url: '/into/cList/' + this.id
          }).then((response) => {
            console.log(response)
            console.log(response.data)
            self.gridData1 = response.data
          }).catch((error) => {
            console.log(error)
          })
        },

        //获取出库单列表编辑
        getEditlist() {
          this.axios({
            method: 'get',
            url: '/into/cList/' + this.ide
          }).then((response) => {
            console.log(response)
            this.Editlist = response.data
          }).catch((error) => {
            console.log(error)
          })
        },


        //select绑定仓库名
        getWh(){
          let data={}
          // let self = this
          this.axios({
            method: 'get',
            url: '/warehouse/listAll'
          }).then((response) => {
              this.warehouse = response.data
              // console.log(this.warehouse)
            }).catch((error) => {
            console.log(error)
          })
        },
      
      getUserID(){
     let self = this
     this.axios({
         method: 'get',
           url: '/user/findByAny',
           params:{
             anyone: this.username ,
           }
           }).then((response) => {
        console.log(response)
        this.userform = response.data
        this.userId = this.userform[0].id
        console.log(this.userId)
           }).catch((error) => {
       console.log(error)
     })
  },


        pad2(n) { return n < 10 ? '0' + n : n },
        generateTimeReqestNumber() {
          var date = new Date();
          return date.getFullYear().toString() +this.pad2(date.getMonth() + 1) +this. pad2(date.getDate()) + this.pad2(date.getHours()) + this.pad2(date.getMinutes()) + this.pad2(date.getSeconds());
        },

      },


    }
    
    </script>


<style>
  .customWidth{
    width:85%;
  }
</style>
