<template>
    <div>
      <el-container style="height:100%; " >         
        <el-container>
            <el-header style="text-align: left; font-size: 12px;height: 100px">
              <div>
                    <el-button type="primary" icon="el-icon-edit"  @click="dialogVisible = true"  >新增</el-button>
                  
                    <el-button type="danger"  icon="el-icon-delete"    @click="dodel" plain>删除</el-button>
                    <el-button @click="toggleSelection()" type="primary" plain icon="el-icon-remove">取消选择</el-button>
                   
                    <div style="text-align: right" >
                    <el-input v-model="search" placeholder="请输入要查询的内容" style="width: 200px"></el-input> 
                     <el-button type="success" icon="el-icon-search" round   @click="searchUser">查询</el-button>
                   </div>
                  </div>
                    
    
                <el-dialog  :title="'新增课程'"  :close-on-click-modal="false" :before-close='closeIt' :visible.sync="dialogVisible"  width="50%"  >
                      
                    <el-form ref="form" :model="form"  :rules="rules" label-width="80px"    @submit.prevent="submit">   
                      <el-form-item label="课程名称" prop="addr">    <el-input  placeholder="请输入地址" v-model="form.addr"></el-input></el-form-item>
                          <el-form-item label="任课教师" prop="addr">    <el-input  placeholder="请输入地址" v-model="form.addr"></el-input></el-form-item>
                            
                          <el-form-item>
                                <el-button  type="primary" @click="onSubmit('form')">确定</el-button>
                                <el-button @click="clearform()">取消</el-button>
                              
                              </el-form-item>
                  </el-form>
                  </el-dialog>
                  <el-dialog  :title="'用户信息修改'" :close-on-click-modal="false"  :before-close='closeEdit' :visible.sync="dialogVisible2"  width="50%"  >
                      
                    <el-form ref="form1" :model="form1" :rules="rules" label-width="80px"   @submit.prevent="submit">

                       <el-form-item label="姓名" prop="name" >   <el-input  placeholder="请输入姓名" v-model="form1.name"></el-input></el-form-item>
                          <el-form-item label="性别" prop="radio1"> 
                               <div>
                            <el-radio   v-model="form1.radio1" label="男" >男</el-radio>
                            <el-radio   v-model="form1.radio1" label="女" >女</el-radio>
                          </div>
                        </el-form-item>
                          <el-form-item label="电话" prop="tel">     <el-input  placeholder="请输入电话"
                             maxlength="11" show-word-limit v-model="form1.tel"></el-input></el-form-item>
                          <el-form-item label="地址" prop="addr">    <el-input  placeholder="请输入地址" v-model="form1.addr"></el-input></el-form-item>
                           <el-form-item label="部门号" prop="deptId">  
                             <el-select v-model="form1.deptId" placeholder="请选择">
                              <el-option
                                v-for="item in Dept"
                                :key="item.id"
                                :label="item.deptname"
                                :value="item.deptname">
                                <span style="float: left">{{ item.deptname }}</span>
                           
                              </el-option>
                            </el-select> </el-form-item>
                          
                            <el-form-item>
                                <el-button  type="primary" @click="onEdit('form1')">确定</el-button>
                                <el-button @click="clearform1()">取消</el-button>
                              
                              </el-form-item>
                  </el-form>
                  </el-dialog>
                 
            
            </el-header>
           
            <el-main>
        
                    <el-table   ref="multipleTable" :data="tableData"   :header-cell-style="{background:'#F2F6FC',color:'#303133'}" 
                    style="width:100% ;height: 50%" @selection-change="getSeletion">
                      <el-table-column type="selection"  style="width:12.5%"> </el-table-column>
                 
                            <el-table-column label="编号" type="index" prop="id"style="width:12.5%">
                           <template slot-scope="scope">
                                <span>{{(page - 1) * size + scope.$index + 1}}</span>
                              </template> 
                            </el-table-column>
                            <el-table-column label="姓名"  prop="name" style="width:12.5%"></el-table-column>
                            <el-table-column label="电话" prop="tel" style="width:12.5%"></el-table-column>
                            <el-table-column  label="性别" prop="gender" style="width:12.5%"></el-table-column>
                            <el-table-column label="地址" prop="addr"style="width:12.5%"></el-table-column>
                           <!-- <el-table-column label="code"  prop="code"  width="180"></el-table-column>
                            <el-table-column label="status" prop="status" width="180"></el-table-column>
                            <el-table-column label="密码" prop="password" width="180"></el-table-column> -->
                            <el-table-column label="部门" prop="deptId" style="width:12.5%"></el-table-column>
                            
                              <el-table-column fixed="right" label="操作" style="width:12.5%">
                                <template slot-scope="scope">
                                  <el-button type="text" size="small" icon="el-icon-edit"  @click="editUser(scope.row)">编辑</el-button>
                                    <el-button
                                 type="text" size="small"
                                 @click="delUser(scope.row)">删除</el-button> 
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
    <el-dialog
      title="提示"
      style="text-align:left !important"
      :visible.sync="dialogVisibleMany"
      :before-close="handleClose"
    >
      <span>你确定要删除这些用户吗?</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="delMany()" type="danger">删除</el-button>
        <el-button type="primary" @click="dialog2isibleMany = false"plain>我再想想</el-button>
      </span>
    </el-dialog>

    <el-dialog
    title="提示"
    style="text-align:left !important"
    :visible.sync="dialogVisibleone"
    :before-close="handleClose"
  >
    <span>你确定要删除这个用户吗?</span>
    <span slot="footer" class="dialog-footer">
      <el-button @click="delone()" type="danger">删除</el-button>
      <el-button type="primary" @click="dialogVisibleone = false"plain>我再想想</el-button>
    </span>
  </el-dialog>
               </el-main>

       </el-container>
        </el-container>
      </div>
    </template>
    <script >
      import {isPhone} from '../validate.js'
      import Cookies from 'js-cookie';
    export default {

      data() {
        
        return {
          status:'',
          username:'',
        rules:{ 
          //  tel1: [
          //   { required: true, message: '手机号不能为空', trigger: 'blur' },
          //   { type: 'string', min: 11, message: '手机号不允许小于11位', trigger: 'blur' },
          //   { validator: isPhone, trigger: 'blur' },
          // ],
         tel: [
            { required: true, message: '手机号不能为空', trigger: 'blur' },
            { type: 'string', min: 11, message: '手机号不允许小于11位', trigger: 'blur' },
            { validator: isPhone, trigger: 'blur' },
          ],
        name: [
            { required: true, message: '姓名不能为空', trigger: 'blur' },
           
          ],
        
 

        },
        

         
          dialogVisible: false,
          dialogVisibleMany: false,
          dialogVisibleone: false,
          dialog3Visible: false,
          dialogVisible2:false,
       
      total: 0,
      size: 10,
      page: 1,
          tableData: [],
          input: '',
          form:{
            id:'',
            name:'',
           radio1:'',
            tel:'',
            addr:'',
            deptId:this.id
          },
          form1:{
            id:'',
            name:'',
            radio1:'',
            tel:'',
            addr:'',
            deptId:this.id
          },
          
        
      
          search:'',
   ids:[],
  //  deptName:[],
  //  deptId:[],
  Dept:[],
  anyone:'',
     id:'',
     userid:'',
     curId:'',
    
   
        };
      },  
      watch:{
    //2.x版本的bug 以前用1.x发现没有 假如现在是第三页，只有一条数据了。将其删除，就没有第三页了。应该跳到第二页展示出5条数据。
    //可是数据没有展示。原因是获取list的时候page参数没有改变。依然是3
      total(){
        if(this.total==(this.page-1)*this.size&& this.total!=0){
          this.page-=1;
          this.getData()
        }
      }
    },
      mounted:function () {     //加载用户列表
        this.$nextTick(function () {
        this.getData()})
    
      } ,  
      methods: {
        // handleSelect(key,keyPath){
        //   console.log(key,keyPath)
        //   this.$route.path=key

        // },

        getDept(){   //读取部门信息
       //   location.reload();
      let self = this;
            let deptName = []
      this.axios({
        method: 'get',
         url: '/dept/all/'+1,
   
         })
         .then(function(response) {
       
          console.log(response);
          console.log(response.data);
          self.Dept=response.data
       

             });
               
  },
        getData(){
         
          let self = this
          this.getTotal()
          this.getDept()
          self.username=Cookies.get('username')
          if(self.username.value==''){
            self.$router.push({path:'/'})
          }
          else{
          this.axios({
          method: 'post',
            url: '/user/findByPH',
            data:{
             // total: 0,
            
              pageSize:this.size,
              currPage:this.page
            }
            })

          .then((response) => {
            console.log(response)
            console.log(response.data)
            self.tableData=response.data
            console.log(self.tableData)
          })
          .catch((error) => {
            console.log(error)
          })
        }
         
        },
        getTotal(){           //读取用户数
          let self = this
          this.axios({
          method: 'get',
            url: '/user/UserCount',
           
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
        
        onSubmit(formName){       //新增用户  
                 
          this.$refs[formName].validate((valid) => {
          if (valid) {
   
          this.axios({
          method: 'post',
            url: '/user/add',
            data:{
            id:this.form.id,
              name:this.form.name,
              tel:this.form.tel,
              gender:this.form.radio1,
              addr:this.form.addr,
              deptId:this.form.deptId
            },
            })
            .then(function(result) {
                   console.log(result)
                               
                });
                this.$message({
          showClose: true,
          message: '新增用户信息成功',
          type: 'success'
        });
            //   clearform();
                this.dialogVisible= false;
                this.clearform()
            //    dialogVisible2= false;
                  this.getData()   ;
              //    console.log(this.forms.name)
      }
      else{
        console.log('error submit!!');
         
            this.$message({
          showClose: true,
          message: '用户信息错误，请重新输入后再提交',
          type: 'error'
        });   
        return false;
      }
    });
                },
      
        
                       
    

    getSeletion ( selection ) {       //处理选中
    console.log(selection) 
    let self = this
     let ids = []
    
 selection.map((item)=> {
 self.ids.push(item.id)})
 console.log(ids) 

selection.map((item)=>{
  self.userid=item.id
  console.log(self.userid) 
      })
 },
  dodel(){
    if(this.ids == undefined || this.ids.length <= 0){
      this.$message({
          message: '你还未选择任何要删除的信息，请选择后再点击删除',
          type: 'warning'
        });
     }else{
      this.dialogVisibleMany = true

     }

  },

    delMany(){                //批量删除用户
   
     
   
      this.axios({
           method: 'delete',
         //   url: '/user/deleteById/'+this.id,
         url: '/user/delMany',
         data:{
         ids: this.ids
         }
            })
            .then(function(result) {
              console.log(ids)
                                console.log(result);
                           
                });
                this.$message({
          showClose: true,
          message: '删除用户记录成功',
          type: 'success'
        });
                this.dialogVisibleMany=false
                this.getData()       
     
    },
    toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          
     
        });
          
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
    
   
       handleClose(done) {
     done();
     },


    handleSizeChange(val){
      this.size = val
      this.getData()
    },
    handleCurrentChange(val) {
      this.page = val
      this.getData()
    },
   
   
    editUser(row) {
     
      // this.form= row;
      // this.form1=this.form;
      // this.form1.deptId=this.form.deptId
      // this.form1.radio1=this.form.radio1
      this.form1.name=row.name;
      this.form1.id=row.id;
      this.form1.tel=row.tel;
      this.form1.addr=row.addr;
      this.form1.deptId=row.deptId
       this.form1.radio1=row.gender
      this.dialogVisible2 = true;
    //  this.addFlag = false;
  
    },
    onEdit(formName){      //修改
   // let self=this
   this.$refs[formName].validate((valid) => {
          if (valid) {
   
      this.axios({
          method: 'put',
            url: '/user/upd',
            data:{
            id:this.form1.id,
              name:this.form1.name,
              tel:this.form1.tel,
              gender:this.form1.radio1,
              addr:this.form1.addr,
              deptId:this.form1.deptId,

            },
          
            })
            .then(function(result) {
           console.log(this.form1),
                 console.log(result)
                               
                });
                this.$message({
          showClose: true,
          message: '修改用户信息成功',
          type: 'success'
        });
       // clearform('form');

               this.dialogVisible2= false;
              this.getData()   ;    
            }
              else{
                console.log('error edit!!');
         
         this.$message({
       showClose: true,
       message: '用户信息错误，请重新输入后再提交',
       type: 'error'
     });   
     return false;
   }

              });

    },
    searchUser(){        //模糊查询查询用户信息
      let self = this
      this.axios({
          method: 'get',
            url: '/user/findByAny',
            params:{
              anyone:self.search,
            }
            })
            .then((response) => {
     console.log(response.data)

        this.tableData=response.data
      //  console.log(self.tableData)

          })
          .catch((error) => {
            console.log(error)
          })
          this.$message({
          showClose: true,
          message: '查询用户信息成功',
          type: 'success'
        });

                // this.dialogVisible2= false
                //                 this.getData()        


    },
    delUser(row) {     
       
    //  this.addFlag = false;
      this.dialogVisibleone = true;
      this.curId = row.id;
      
    },
   
    delone(){          //删除单个用户
   
     
   this.axios({
        method: 'delete',
         url: '/user/delById/'+this.curId,
     // url: '/user/delMany',
      // data:{
      // ids: this.ids
      // }
         })
         .then(function(result) {
         //  console.log(ids)
                             console.log(result);
                        
             });
             this.dialogVisibleone=false
             this.getData()        
    },
    check(){
    if (document.form.name.value==""){        
        alert("姓名不能为空");        
        return false;    
    }
    else{
        return true;
    }
},

clearform(){
　//　this.creatGroup = false;
 
this.form.name='',
this.form.tel='',
this.form.addr='',
this.form.radio1='',
this.form.deptId='',
this.$refs['form'].clearValidate();

　//　this.$refs[formName].resetFields();
this.dialogVisible=false
},
closeIt(done){
  done();
  this.clearform()
 
// location.reload();
},
clearform1(){
  
  
  this.form1.name='',
  this.form1.tel='',
  this.form1.addr='',
  this.form1.radio1='',
  this.form1.deptId='',
  this.$refs['form1'].clearValidate();
  
  　//　this.$refs[formName].resetFields();
  this.dialogVisible2=false
},
closeEdit(done){
  done();
  this.$refs['form1'].clearValidate();
  this.dialogVisible2=false;

},


   
  
  
    }  
    }
        
    
    </script>
    <style>
      .div3{ background-color: #F2F3F4; width:100%; height:50px;  text-align:right} 
    </style>
