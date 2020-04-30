<template>
  <div class="homeBox" >
  <el-form  ref="loginForm" :model="loginForm" :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="loginForm.account"
      prefix-icon="el-icon-user"      auto-complete="off" placeholder="请输入账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
        <el-input placeholder="请输入密码"   prefix-icon="el-icon-lock"   v-model="loginForm.password" show-password></el-input>
      <!-- <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input> -->
    </el-form-item>
    <el-checkbox class="login_remember" v-model="checked"
                 label-position="left">记住密码</el-checkbox>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%" @click="submitClick('loginForm')">登录</el-button>
    </el-form-item>
  </el-form>

</div>
</template>
<script>
import Cookies from 'js-cookie';
  export default{
    data(){
      return {
        st:'',
        username:'',
        rules: {
          account: [
            { required: true, message: '账号不能为空', trigger: 'blur' },
           
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' },
            // { type: 'string', min: 6, message: '密码不允许小于6位', trigger: 'blur' },
          ],
         
        },
        checked: true,
        loginForm: {
          account: '',
          password: ''
        },
        loading: false
      }
    },
    methods: {
    
      submitClick(formName) {
      let self = this
      this.$refs[formName].validate((valid) => {
          if (valid) {
        this.axios({
          method: 'post',
            url: '/student/tologin',
            data:{
              name:self.loginForm.account,
              password:self.loginForm.password
            },
            })
            .then(function(result) {
                                console.log(result)
                              console.log(result.data)
                             
                             self.st= result.data
                             console.log(self.st)
                              console.log(self.loginForm.account)
                               console.log(self.loginForm.password)
                            //   console.log(this.st)
      if(self.st==0){     
            self.$message({
          message: '账号或密码错误，请重新登录',
          type: 'warning',
         
        });
      
              
                }else{  
                   self.$router.push({path:'/pages/index'})
                 
                   Cookies.set('username',self.loginForm.account,30)
              // location.reload();
                  localStorage.setItem('token',result.data)
        
         }
     
                    
      });
    }else{
      self.$message({
          message: '请输入账号密码后再登录',
          type: 'error',
         
        });

    }
  })
      // console.log(this.st)
      // if(self.st==0){     
      //       self.$message({
      //     message: '账号或密码错误，请重新登录',
      //     type: 'warning',
         
      //   });
      
              
      //           }else{  
      //              self.$router.push({path:'/pages/index'})
                  
      //    }
     
    },
  }
}
    
         
      // }     
      //           });
      //           if(this.st=="1"){
      //            self.$router.push({path:'/pages/index'})
      //           }else{
      //             self.$message({
      //     message: '账号或密码错误，请重新登录',
      //     type: 'warning'
      //   });

              
      // }     
            
        // this.getRequest("http://localhost:8081/user/tologin/" + this.loginForm.username + "/" + this.loginForm.password)
        //   .then(resp => {
        //     this.loading = false;
        //     if (resp && resp.status == 200){
        //       var data = resp.data;
        //       //_this.$store.commit('login', data.obj);
        //       var path = _this.$route.query.redirect;
        //       _this.$router
        //         .replace({path: path == '/' || path == undefined ? '/' : path});
        //     }
        //   } )
     
      // submitClick: function () {
      //   var _this = this;
      //   this.loading = true;
      //   this.postRequest('http://localhost:8082/user/tologin', {
      //     user: this.loginForm.username,
      //     password: this.loginForm.password
      //   }).then(resp=> {
      //     _this.loading = false;
      //     if (resp && resp.status == 200) {
      //       var data = resp.data;
      //       _this.$store.commit('login', data.obj);
      //       var path = _this.$route.query.redirect;
      //       _this.$router
      //         .replace({path: path == '/' || path == undefined ? '/home' : path});
      //     }
      //   });
      // }
      // submitClick: function () {
      //   var _this = this;
      //   this.loading = true;
      //   this.postRequest('http://localhost:8082/user/tologin/'+this.loginForm.username+'/'+this.loginForm.password+'', {
      //   }).then(resp=> {
      //     _this.loading = false;
      //     if (resp && resp.status == 200) {
      //       var data = resp.data;
      //       _this.$store.commit('login', data.obj);
      //       var path = _this.$route.query.redirect;
      //       _this.$router
      //         .replace({path: path == '/' || path == undefined ? '/home' : path});
      //     }
      //   });
      // }
    
  
</script>
<!-- <style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style> -->
<style scoped>
  .homeBox {
    position: fixed;
    width: 100%;
    height: 100%;
    top: 0px;
    background: url("../../static/login.jpg");
    background-size: 100% 100%;
    margin-top: auto;
  }
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>
