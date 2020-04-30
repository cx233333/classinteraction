<template>
    <div id="app"   >
        <el-container>
  
           
        <el-container>
            <!-- <el-aside width="250px">
            
              <keep-alive>
                <left></left>
                </keep-alive>
  
            </el-aside> -->
            <el-main>
              <!-- Body -->
              <el-header style="text-align: right; font-size: 12px"><div class="div3" style="right:100% ">
                <span style="font-size: 20px" >欢迎您, {{username}}  </span>
                <el-button type="primary" icon=" el-icon-switch-button" circle @click="exitlogin"></el-button>
              </div>
                 <!-- <router-link to="/"  >退出</router-link></div> -->
        </el-header> 
               <router-view></router-view>
            </el-main>
      </el-container>
          </el-container>
       
          <!-- 登录页 -->
          <router-view v-if="!$route.meta.keepAlive"></router-view>
         </div>
     
  </template>
  
  <script>

    import Cookies from 'js-cookie';
  export default {
   components:{
    //  left:left
   },
  
        name: 'App',
        username:'',
        mounted(){
         
       //   this.username=Cookies.get('username')
        // this. getusername()
        //  location.reload();
        },
        methods: {
        exitlogin(){
       let self=this
      
      this.axios({
          method: 'get',
           url: 'user/quit/'+self.username,
       // url: '/user/delMany',
        // data:{
        // ids: this.ids
        // }
           })
           .then(function(response) {
           //  console.log(ids)
                               console.log(response);
                            self.$router.push({path:'/'})   
                            Cookies.set('username','',30)
                            self.username=Cookies.get('username')
                            console.log(self.username);
                          //  location.reload();
               });
        }
  
     },
    
  
        
  }
  
  </script>
  
  <style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    height: 100%;
  }
  </style>