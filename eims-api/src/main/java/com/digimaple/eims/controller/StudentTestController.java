package com.digimaple.eims.controller;

import com.digimaple.eims.model.Student;
import com.digimaple.eims.model.StudentTest;
import com.digimaple.eims.model.Test;
import com.digimaple.eims.model.view.StudentTestView;
import com.digimaple.eims.repository.StudentTestRepository;
import com.digimaple.eims.service.StudentTestService;
import com.digimaple.eims.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//未成功项：如何保证数据传输过程中被拦截窃取，设置数据乱码传输后被劫持是否还能被接收
//用于登录后才能用的操作
@CrossOrigin
@RestController

@RequestMapping("StudentTest")
public class StudentTestController {
    @Autowired
    @Resource
    private StudentTestService studentTestService;
    private static final Logger log = LoggerFactory.getLogger(FileController.class);



    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @ApiOperation("列表")
    @GetMapping("/list")
    public ResponseEntity<List<StudentTest>> getAll(){
        List<StudentTest> studentTests = studentTestService.getStudentTestList();
        return ResponseEntity.ok(studentTests);
    }



    @ApiOperation("上传测试答案")
    @PostMapping(value = "/upload")
    public String upload(@RequestParam(value = "file", required=true) MultipartFile FILES ,
                         @RequestParam(value = "testid", required=false) Integer testid,
                         @RequestParam(value = "stid", required=false) Integer stid

                         ) {
//@RequestParam(value = "createtime", required=false)Date createtime,
//                         @RequestParam(value = "endtime", required=false)Date endtime
        try {
            if (FILES.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = FILES.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = "E:\\class\\file\\studentposttest";
            String path = filePath + "\\"+fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            FILES.transferTo(dest);// 文件写入
            // courseFile.setFileid(1);

            Date date = new Date(System.currentTimeMillis());//当前时间

           StudentTest studentTest= new StudentTest();
           studentTest.setStid(stid);
           studentTest.setTestid(testid);
           studentTest.setAnswer(fileName);
           studentTest.setTesttime(date);
           studentTestService.save(studentTest);


            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @ApiOperation("按kcid和testid查询view")
    @GetMapping("/findByKcidAndTestid/{kcid}/{testid}")
    public ResponseEntity<List<StudentTestView>> getByKcid(@PathVariable Integer kcid,@PathVariable Integer testid){
        List<StudentTestView> studentTestViews = studentTestService.findStudentTestViewByKcidAndTestid(kcid,testid);
        return ResponseEntity.ok(studentTestViews);
    }


    @ApiOperation("按kcid和stid查询view")
    @GetMapping("/findByKcidAndStid/{kcid}/{stid}")
    public ResponseEntity<List<StudentTestView>> getByKcidAndStid(@PathVariable Integer kcid,@PathVariable Integer stid){
        List<StudentTestView> studentTestViews = studentTestService.findStudentTestViewByKcidAndStid(kcid,stid);
        return ResponseEntity.ok(studentTestViews);
    }

    @GetMapping("/download/{fileName}")
    public String downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {

        if (fileName != null) {
            //设置文件路径
            File file = new File("E:/class/file/studentposttest/"+fileName);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @ApiOperation("教师打分")
    @PostMapping("/updatescore")
    public ResponseEntity updatescore(@RequestParam(value = "score",required = true) String score,
                                     @RequestParam(value = "id",required = true) Integer id){

        int YesOrNo = studentTestService.updateScore(score, id);
        return ResponseEntity.ok(YesOrNo);
    }


}
