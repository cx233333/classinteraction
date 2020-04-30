<template>
   <div>
        <el-container>
                <el-header style="text-align: left; font-size: 12px;height: 100px">
                        <div> 
                              <div style="text-align:left" >
                                条形码：
                              <el-input v-model="searchCode" placeholder="请输入条形码" style="width: 200px"></el-input> 
                               <el-button type="primary" icon="el-icon-search"  @click="searchgoods">查询</el-button>
                               <el-button   @click="reset">重置</el-button>
                             </div>
                             <br>
                              <el-button type="primary" icon="el-icon-edit"  @click="dialogVisibleAdd = true"  >新增</el-button>
                            
                            
                            </div>
                              
              
                          <el-dialog  :title="'新增物资'"  :close-on-click-modal="false" :before-close='closeIt' :visible.sync="dialogVisibleAdd"  width="50%"  >
                                
                              <el-form ref="form" :model="form" :rules="rules" label-width="80px"   @submit.prevent="submit">
          
                                    <el-form-item label="条形码"   prop="barcode" >   <el-input placeholder="请输入" v-model="form.barcode"></el-input></el-form-item>
                                    <el-form-item label="物资名称" prop="name">    <el-input  placeholder="请输入" v-model="form.name"></el-input></el-form-item>
                                    <el-form-item label="单位">    <el-input  placeholder="请输入" v-model="form.unit"></el-input></el-form-item>
                                    <el-form-item label="规格">    <el-input  placeholder="请输入" v-model="form.spec"></el-input></el-form-item>
                                    <el-form-item label="颜色">    <el-input  placeholder="请输入" v-model="form.color"></el-input></el-form-item>
                                    <el-form-item label="品牌">    <el-input  placeholder="请输入" v-model="form.brand"></el-input></el-form-item>
                                    <el-form-item label="警戒值"  prop="warnNum">    <el-input  placeholder="请输入" v-model="form.warnNum"></el-input></el-form-item>
                                    
                                      <el-form-item>
                                          <el-button  type="primary" @click="onSubmit('form')">确定</el-button>
                                          <el-button  @click="clearform()">取消</el-button>
                                        
                                        </el-form-item>
                            </el-form>
                            </el-dialog>
                            
                          <el-dialog  :title="'修改物资信息'"  :close-on-click-modal="false" :before-close='closeEdit' :visible.sync="dialogVisibleEdit"  width="50%"  >
                                
                                <el-form ref="form1" :model="form1" label-width="80px"   @submit.prevent="submit">
            
                                      <el-form-item label="条形码"  prop="barcode">   <el-input placeholder="请输入" v-model="form1.barcode"></el-input></el-form-item>
                                      <el-form-item label="物资名称" prop="name">    <el-input  placeholder="请输入" v-model="form1.name"></el-input></el-form-item>
                                      <el-form-item label="单位" >    <el-input  placeholder="请输入" v-model="form1.unit"></el-input></el-form-item>
                                      <el-form-item label="规格">    <el-input  placeholder="请输入" v-model="form1.spec"></el-input></el-form-item>
                                      <el-form-item label="颜色">    <el-input  placeholder="请输入" v-model="form1.color"></el-input></el-form-item>
                                      <el-form-item label="品牌">    <el-input  placeholder="请输入" v-model="form1.brand"></el-input></el-form-item>
                                      <el-form-item label="警戒值" pro="warnNum">    <el-input  placeholder="请输入" v-model="form1.warnNum"></el-input></el-form-item>
                                      
                                        <el-form-item>
                                            <el-button  type="primary" @click="onEdit('form1')">确定</el-button>
                                            <el-button @click="clearform1()">取消</el-button>
                                          
                                          </el-form-item>
                              </el-form>
                              </el-dialog>
                        </el-header>
                        <el-main>
          
              
                                <el-table   ref="multipleTable" :data="tableData"  :header-cell-style="{background:'#F2F6FC',color:'#303133'}"  style="width: 100% ;height: 50%" >
                                 
                             
                                        <el-table-column label="序号" type="index" prop="id"style="width:11%">
                                       <template slot-scope="scope">
                                            <span>{{(page - 1) * size + scope.$index + 1}}</span>
                                          </template> 
                                        </el-table-column>
                                        <el-table-column label="条形码"  prop="barcode" style="width:11%"></el-table-column>
                                        <el-table-column label="物资名称" prop="name"style="width:11%"></el-table-column>
                                        <el-table-column  label="单位" prop="unit" style="width:11%"></el-table-column>
                                        <el-table-column label="规格" prop="spec" style="width:11%"></el-table-column>
                                        <el-table-column label="颜色" prop="color" style="width:11%"></el-table-column>
                                        <el-table-column label="品牌" prop="brand"style="width:11%"></el-table-column>
                                        <el-table-column label="警戒值" prop="warnNum"style="width:11%"></el-table-column>
                                        <!-- <el-table-column label="修改" width="125">
                                          <template slot-scope="scope">
                                            <el-button type="primary" icon="el-icon-edit" size="mini" @click="editGoods(scope.row)">编辑</el-button>
                                          </template>
                                          
                                        </el-table-column>
                                        <el-table-column label="删除" width="125">
                                        
                                           <template slot-scope="scope">
                                                <el-button type="danger" icon="el-icon-delete" size="mini" @click="delGoods(scope.row)">删除</el-button>
                                              </template>
                                        </el-table-column>
                                         -->
                                         <el-table-column fixed="right" label="操作" style="width:11%">
                                          <template slot-scope="scope">
                                            <el-button type="text" size="small" icon="el-icon-edit"  @click="editGoods(scope.row)">编辑</el-button>
                                              <el-button
                                           type="text" size="small"
                                           @click="delGoods(scope.row)">删除</el-button> 
                                           </template></el-table-column>
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
                
            
                <el-dialog
                title="提示"
                style="text-align:left !important"
                :visible.sync="dialogVisibleDel"
                :before-close="handleClose"
              >
                <span>你确定要删除吗?</span>
                <span slot="footer" class="dialog-footer">
                  <el-button @click="delone()" type="danger">删除</el-button>
                  <el-button type="primary" @click="dialogVisibleDel = false"plain>我再想想</el-button>
                </span>
              </el-dialog>
           </el-main>
        </el-container>
   </div>
</template>
<script>
  import {isInteger} from '../validate.js'
 export default {
      data() {
        
        return {
          rules:{ 
          barcode: [
            { required: true, message: '条形码不能为空', trigger: 'blur' },
            // { type: 'string', min: 11, message: '手机号不允许小于11位', trigger: 'blur' },
            // { validator: isPhone, trigger: 'blur' },
          ],
          name: [
            { required: true, message: '物资名称不能为空', trigger: 'blur' },
            // { type: 'string', min: 11, message: '手机号不允许小于11位', trigger: 'blur' },
            // { validator: isPhone, trigger: 'blur' },
          ],
          warnNum: [
          // { required: true, message: '物资名称不能为空', trigger: 'blur' },
            {
              validator: isInteger, trigger: 'blur'
            },

          ]
          },

     dialogVisibleAdd:false,
     dialogVisibleEdit:false,
     dialogVisibleDel:false,
     searchCode:'',
     curId:'',
     total: 0,
     delcode:'',
      size: 10,
      page: 1,
          tableData: [],
          input: '',
          form:{
            barcode:'',
           name:'',
           unit:'',
            spec:'',
            color:'',
            brand:'',
            warnNum:''
          },
          form1:{
            barcode:'',
           name:'',
           unit:'',
            spec:'',
            color:'',
            brand:'',
            warnNum:''
          },

        }
      },
      watch:{
    //2.x版本的bug 以前用1.x发现没有 假如现在是第三页，只有一条数据了。将其删除，就没有第三页了。应该跳到第二页展示出5条数据。
    //可是数据没有展示。原因是获取list的时候page参数没有改变。依然是3
      total(){
        if(this.total==(this.page-1)*this.size&& this.total!=0){
          this.page-=1;
          this.getGoodsList()
        }
      }
    },
      mounted () {     //加载用户列表
        this.getGoodsList()
      } ,  
      methods: {
          getGoodsList(){
            let self = this
          this.getTotalgoods()
         // this.getDept()
          this.axios({
          method: 'post',
       url: '/Goods/Goods-page',
            data:{
             // total: 0,
            
              pageSize:this.size,
              currPage:this.page
          
            }
            })

          .then((response) => {
            console.log(response)
            console.log(response.data)
            this.tableData=response.data.list
          //  this.tableData.id=response.data.id
          //  this.tableData.barcode=response.databarcode
          //  this.tableData.name=response.data.name
          //  this.tableData.unit=response.data.unit
          //  this.tableData.spec=response.data.spec

          //  this.tableData.color=response.data.color
          //  this.tableData.brand=response.data.brand
          //  this.tableData.warnNum=response.data.warnNum
           console.log( this.tableData)

          })
          .catch((error) => {
            console.log(error)
          })


          },
          getTotalgoods(){
            let self = this
          this.axios({
          method: 'get',
            url: '/Goods/Goods/count',
           
            })

          .then((response) => {
            console.log(response)
            console.log(response.data)
            self.total=response.data
          })
          .catch((error) => {
            console.log(error)
          })



          },
          searchgoods(){   //查询
            let self = this
      this.axios({
          method: 'get',
            url: '/Goods/barcode/'+self.searchCode,
            })
            .then((response) => {
       console.log(response)

        self.tableData.barcode=response.data.barcode
        self.tableData.name=response.data.name
        self.tableData.unit=response.data.unit
        self.tableData.spec=response.data.spec
        self.tableData.color=response.data.color
        self.tableData.brand=response.data.brand
        self.tableData.warnNum=response.data.warnNum
        self.tableData.id=response.data.id

        //console.log(self.tableData)

          })
          .catch((error) => {
            console.log(error)
          })
          this.$message({
          showClose: true,
          message: '查询物资信息成功',
          type: 'success'
        });

          },
          reset(){   //查询重置
            this.searchCode=''

          },
          onSubmit(formName){    //添加新增物资表单
            this.$refs[formName].validate((valid) => {
          if (valid) {
   
            this.axios({
          method: 'post',
            url: '/Goods/Goods_add',
            data:{
   
              name:this.form.name,
              barcode:this.form.barcode,
              spec:this.form.barcode,
              unit:this.form.unit,
             color:this.form.color,
              warnNum:this.form.warnNum,
             brand:this.form.brand,
          
            },
            })
            .then(function(result) {
                                console.log(result)
                               
                });
                this.$message({
          showClose: true,
          message: '新增物资成功',
          type: 'success'
        });
                this.form= {};
                this.dialogVisibleAdd= false
                  this.getGoodsList()  
      }else{
        this.$message({
          showClose: true,
          message: '输入的信息有误，请重新输入',
          type: 'error'
        });

      }
    })
          },
          delGoods(row) {     
       
       //  this.addFlag = false;
         this.dialogVisibleDel = true;
       //  this.curId = row.id;
       this.delcode=row.barcode
         
       },
      
          delone(){   //删除
         
            this.axios({
        method: 'delete',
         url: '/Goods/del/'+this.delcode,
     
         })
         .then(function(result) {
      
          console.log(result);
                        
             });
             this.dialogVisibleDel=false
             this.getGoodsList()      
 
          },
          handleClose(done) {
          done();
          },
          handleSizeChange(val){
      this.size = val
      this.getGoodsList()
    },
    handleCurrentChange(val) {
      this.page = val
      this.getGoodsList()
    },
    editGoods(row) {
     
    //  this.form= row;
    //  this.form1=this.form;
    //  this.form1.deptId=this.form.deptId
    //  this.dialogVisible2 = true;
    this.form1= row;
     
     
      this.dialogVisibleEdit = true;
 
   },
   onEdit(formName){
    let self=this
    this.$refs[formName].validate((valid) => {
          if (valid) {
      this.axios({
          method: 'put',
            url: '/Goods/Goods_update',
            data:{
              id:this.form1.id,
              name:this.form1.name,
              barcode:this.form1.barcode,
              spec:this.form1.barcode,
              unit:this.form1.unit,
             color:this.form1.color,
              warnNum:this.form1.warnNum,
             brand:this.form1.brand,
            },
          
            })
            .then(function(result) {
              
                  console.log(result)
                               
                });
                this.$message({
          showClose: true,
          message: '修改信息成功',
          type: 'success'
        });
                self.form= {};
                self.form1= {};
                this.id='';
                self.dialogVisibleEdit= false
               self.getGoodsList()  
      }else{
        this.$message({
          showClose: true,
          message: '信息有误，请重新填入后提交',
          type: 'error'
        });

      }      
    });

   },
   clearform(){
　//　this.creatGroup = false;
 
this.form.barcode='',
this.form.name='',
this.form.unit='',
this.form.spec='',
this.form.color='',
this.form.brand='',
this.form.warnNum='',
this.$refs['form'].clearValidate();

　//　this.$refs[formName].resetFields();
this.dialogVisibleAdd=false
},
closeIt(done){
  done();
  this.clearform()
 
// location.reload();
},
closeEdit(done){
  done();
  this.$refs['form1'].clearValidate();
  this.dialogVisibleEdit=false;

},
clearform1(){
　//　this.creatGroup = false;
 
this.form1.barcode='',
this.form1.name='',
this.form1.unit='',
this.form1.spec='',
this.form1.color='',
this.form1.brand='',
this.form1.warnNum='',
this.$refs['form1'].clearValidate();

　//　this.$refs[formName].resetFields();
this.dialogVisibleEdit=false
},



      }
    }

</script>