<template>

  <div>
      <el-container>
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
          <el-menu
          default-active="1-2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
         
          active-text-color="#ffd04b">
         
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>课堂互动后台管理</span>
            </template>
            <el-menu-item-group>
              <template slot="title">选项</template>
              <el-menu-item index="1-1">用户管理</el-menu-item>
              <el-menu-item index="1-2">课程管理</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3" >课程设置</el-menu-item>
            </el-menu-item-group>
            
          </el-submenu>
         
        </el-menu>
        </el-aside>
        <el-container>
            <el-header style="text-align: left" >
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

                  <el-form-item  label="课程名称" :rules="[
                    { required: true, message: '请输入课程名称', trigger: 'blur' },
                    { type: 'string', message: '请输入正确的课程名称'}]"  >
                    <el-input type="textarea" v-model="create.kcname" ></el-input>
                  </el-form-item>

                  <el-form-item label="任课教师"  >
                    <!-- <el-input type="textarea" v-model="create.jsid" ></el-input> -->
                    <el-select v-model="create.jsid" placeholder="请选择">
                      <el-option
                        v-for="item in Teacher"
                        :key="item.id"
                        :label="item.jsname"
                        :value="item.id">
                        <span style="float: left">{{ item.jsname }}</span>
                   
                      </el-option>
                    </el-select>
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
                style="width: 100%"  @selection-change="getSeletion">
                <el-table-column
                  type="selection"
                  width="55">
                </el-table-column>
                         <el-table-column label="序号" type="index" prop="id" width="180">
                           <template slot-scope="scope">
                             <span>{{(page - 1) * size + scope.$index + 1}}</span>
                          </template>
                          </el-table-column>
                          <el-table-column label="课程编号"  prop="kcid" width="180"></el-table-column>
                        <el-table-column label="课程名称"  prop="kcname" width="180"></el-table-column>
                        <el-table-column label="任课教师" prop="jsname" width="180"></el-table-column>
                        <el-table-column label="任课教师编号" prop="jsno" width="180"></el-table-column>
                         <el-table-column fixed="right" label="操作" width="100">
                           <template slot-scope="scope">
                             <el-button type="text" size="small" icon="el-icon-edit"   @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                           </template>
                         </el-table-column>
                             <el-table-column fixed="right" label="删除" width="100">
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
          cdialogVisible: false,
          edialogVisible: false,
          dialogVisible: false,

          total: 0,
          size: 10,
          page: 1,
          tableData: [],
          ids:[],//批量删除id
          id:'',
          sdeptname:'', //查询

          create: {
            deptname: '',
            remark: '',
          },
          edit: {
            id:0,
            deptname: '',
            remark: '',
          },

        }
      },  
      mounted () {
        this.getData()
        this.getTeacher() 
      },

      watch:{
       
      },

      methods: {
        getTeacher() {
          let self = this
         
         this.axios({
           method: 'get',
           url:'/teacher/list',
          
         }).then((response) => {
           console.log(response)
           self.Teacher=response.data
           console.log("课程列表数据"+self.Teacher)
         }).catch((error) => {
           console.log(error)
         })

        },


        //列表
        getData(){
          let self = this
         
          this.axios({
            method: 'get',
            url:'Course/listView',
           
          }).then((response) => {
            console.log(response)
            self.tableData=response.data
            console.log("列表数据"+self.tableData)
          }).catch((error) => {
            console.log(error)
          })
        },


        onclear(){
          this.create.deptname=""
          this.create.remark=""
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
            if (this.$refs.create.validate()) {
              this.axios({
                url: 'Course/add',
                method: 'post',
                data:{
                  kcname: this.create.kcname,
                 jsid:this.create.jsid,
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






      },



       
    }
    
    </script>
