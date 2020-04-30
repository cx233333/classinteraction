<template>

  <div>
      <el-container>
        <el-aside width="300px" style="background-color: rgb(238, 241, 246)">
          <el-menu
      default-active="1-3"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
     
      active-text-color="#ffd04b">
     
      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
        <template slot="title">
          <i class="el-icon-s-platform"></i>
          <span> {{ item.navItem }}</span>
        </template>

      </el-menu-item>

     
    </el-menu>
        </el-aside>
        <el-container>
            <el-header style="text-align: left;height: auto;" >
                <el-button  type="primary" @click="test">确定</el-button>
                    <el-button type="primary" icon="el-icon-circle-plus"   @click="add" >新增</el-button>

                    <el-button type="danger"  icon="el-icon-delete" plain @click="onDelete">删除</el-button>
              <el-button @click="toggleSelection()">取消选择</el-button>
              <el-button  icon="el-icon-refresh" @click="getData">刷新</el-button>

              <div  style="text-align: right">
                <el-input v-model="sdeptname" placeholder="请输入课程名称" style="width: 200px"></el-input>
                <el-button type="success" icon="el-icon-search" round   @click="onSelect">查询</el-button>
              </div>
            </el-header>


              <el-dialog title="新增" v-model="cdialogVisible" :visible.sync="cdialogVisible" :close-on-click-modal="false" :close-on-press-escape="false" >
                <el-form id="#create" :model="create" ref="create" label-width="100px">

                  <el-form-item  label="课程名称"  >
                   <!-- <el-input type="textarea" v-model="create.kcid" ></el-input> -->
                    <el-select v-model="create.kcid" placeholder="请选择">
                      <el-option
                        v-for="item in Course"
                        :key="item.id"
                        :label="item.kcname"
                        :value="item.id">
                        <span style="float: left">{{ item.kcname }}</span>
                   
                      </el-option>
                    </el-select>
                  </el-form-item>

                  <el-form-item label="学生学号" :rules="[
                    { required: true, message: '请输入', trigger: 'blur' },
                    { type: 'string', message: '请输入'}]" >
                    <el-input type="textarea" v-model="create.stid" ></el-input>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="onclear">取 消</el-button>
                  <el-button type="primary"  @click="onSubmit">确 定</el-button>
                </div>
              </el-dialog>


              <el-dialog title="修改" v-model="edialogVisible" :visible.sync="edialogVisible" :close-on-click-modal="false" :close-on-press-escape="false" >
                <el-form id="#eidt" :model="edit" ref="edit" label-width="100px">

                  <el-form-item  label="课程名称" :rules="[
                    { required: true, message: '不能为空', trigger: 'blur' },
                    { type: 'string', message: '请输入正确的课程名称'}]" >
                    <el-input  v-model="edit.kcname"></el-input>
                  </el-form-item>

                  <el-form-item label="任课教师"  :rules="[
                    { required: true, message: '请输入', trigger: 'blur' },
                    { type: 'string', message: '请输入正确的任课教师'}]">
                    <el-input  v-model="edit.jsid"></el-input>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="edialogVisible=false">取 消</el-button>
                  <el-button type="primary"  @click="onEdit">确 定</el-button>
                </div>
              </el-dialog>

              <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose">
                <span>您确定删除吗？</span>
                <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="Delete">确 定</el-button>
                 </span>
              </el-dialog>


            <el-container>
            <el-main>
              <el-table
                ref="multipleTable"
                :data="tableData"
                style="width: 100%;height: auto;"  @selection-change="getSeletion">
                <el-table-column
                  type="selection"
                  width="55">
                </el-table-column>
                         <el-table-column label="序号" type="index" prop="id" style="width:14.2%">
                           <template slot-scope="scope">
                             <span>{{(page - 1) * size + scope.$index + 1}}</span>
                          </template>
                          </el-table-column>
                        <el-table-column label="课程名称"  prop="kcname" style="width:14.2%"></el-table-column>
                        <el-table-column label="任课教师" prop="jsname" style="width:14.2%"></el-table-column>
                        <el-table-column label="学生姓名" prop="sname" style="width:14.2%"></el-table-column>
                        <el-table-column label="学生学号" prop="sno" style="width:14.2%"></el-table-column>
                         <el-table-column fixed="right" label="操作" style="width:14.2%">
                           <template slot-scope="scope">
                             <el-button type="text" size="small" icon="el-icon-edit"   @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                           </template>
                         </el-table-column>
                             <el-table-column fixed="right" label="删除" style="width:14.2%">
                               <template slot-scope="scope">
                             <el-button
                               size="mini"
                               type="danger"
                               @click="handleDelete(scope.row)">删除</el-button>
                           </template>
                         </el-table-column>
                         </el-table>
             
               </el-main>
        </el-container>
      </el-container>
       </el-container>
       
      </div>
    </template>


    <script>

    export default {

      data() {
        return {
          navList:[ 
  			{name:'/pages/course',navItem:'课程管理'}, 
  			{name:'/pages/xuanke',navItem:'课程设置'},
  			{name:'/pages/dm',navItem:'用户管理'}, 
  		
  			],

          cdialogVisible: false,
          edialogVisible: false,
          dialogVisible: false,
          tableData: [],
          total: 0,
          size: 10,
          page: 1,
          ids:[],//批量删除id
          id:'',
          sdeptname:'', //查询
          create: {
            kcid: '',
            stid: '',
          },
          edit: {
            id:0,
            deptname: '',
            remark: '',
          },
          Course:[]
            
        }
      },  
      mounted () {
        this.getData()
        this.getCourse()

      },

      watch:{
       
      },

      methods: {
        getCourse() {
          let self = this
         
         this.axios({
           method: 'get',
           url:'/Course/list',
          
         }).then((response) => {
           console.log("aa"+response.data)
           self.Course=response.data
           console.log("课程列表数据"+self.Course)
         }).catch((error) => {
           console.log(error)
         })

        },
        //列表
        getData(){
          let self = this
         
          this.axios({
            method: 'get',
            url:'XuanKe/xuankelist',
           
          }).then((response) => {
            console.log(response)
            self.tableData=response.data
            console.log("列表数据"+self.tableData)
          }).catch((error) => {
            console.log(error)
          })
        },


        onclear(){
          this.create.kcid=""
          this.create.stid=""
          this.cdialogVisible=false
        },

         add(){
           this.cdialogVisible=true
         },
         test(){
         var foo=1;
         function main(){
           console.log(foo);
           var foo=2;
           console.log(this.foo)
           this.foo=3
         }
        
        },

        //增加
        onSubmit(create) {
          console.log(this.create.kcid+"ss"+this.create.stid)
            if (this.$refs.create.validate()) {
              this.axios({
                url: 'XuanKe/add',
                method: 'post',
                data:{
                  kcid: this.create.kcid,
                 stid:this.create.stid,
                }
              }).then(function(result) {
                console.log(result)
              });
              this.create= {};
              this.cdialogVisible= false
              this.getData()
            } else {
              console.log('错误!');
              return false;
            }
        },



        //取消选择
        toggleSelection(rows) {
          if (rows) {
            rows.forEach(row => {
              this.$refs.multipleTable.toggleRowSelection(row);
            });
          } else {
            this.$refs.multipleTable.clearSelection();
          }
        },

        //单个删除
        handleClose(done) {
          done();
        },


        handleDelete( row) {
            this.dialogVisible = true;
            this.id = row.id
        },
        Delete() {
          this.axios({
            method: 'delete',
            url: 'Course/delete/' +this.id ,

          }).then(function (result) {
            console.log(result)
          });
          this.dialogVisible = false;
          this.getData()
        },

        // 获取表格选中时的数据
        getSeletion ( selection ) {
          console.log(selection)
          let self = this
          let ids = []
          selection.map((item)=> {
            self.ids.push(item.id)})
          console.log(ids)
          selection.map((item)=>{
            self.id=item.id
            console.log(self.id)
          })
        },

        onDelete(){
          if(this.ids.length == 0) {
            this.$message('请选择记录')
          }
          else {
            this.$confirm('此操作将永久删除选中行, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
              this.axios({
                method: 'delete',
                url: '/dept/del_much/'+this.ids,
            }).then((response) => {
                console.log(ids)
                console.log(result);
                this.getData()
            }).catch(() => {
              });
            this.getData()
          });
          }

        },


        //查询
        onSelect(){
          let self = this
          console.log(self.sdeptname)
          this.axios({
            method: 'get',
            url: 'Course/findByKcname/'+self.sdeptname,
          }).then((response) => {
            console.log(response)
            self.tableData=response.data
            console.log(self.tableData)

          })
            .catch((error) => {
              console.log(error)
            })
        },


        //修改
        handleEdit(index, row) {
          this.edialogVisible = true;
          this.edit = Object.assign({}, row);
        },
          onEdit(edit){
              if ( this.$refs.edit.validate()) {
                this.axios({
                  url: 'Course/edit',
                  method: 'post',
                  data:{
                    id:this.edit.id,
                    kcname: this.edit.kcname,
                    jsid:this.edit.jsid,
                  
                  }
                }).then(function(result) {
                  console.log(this.edit),
                    console.log(result)
                });
                this.getData(),
                this.edialogVisible= false
              } else {
                console.log('错误!');
                return false;
              }
          },
          handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }







      },



       
    }
    
    </script>
