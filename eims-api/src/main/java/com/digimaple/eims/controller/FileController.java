package com.digimaple.eims.controller;
import com.digimaple.eims.model.CourseFile;
import com.digimaple.eims.model.view.CourseFileView;
import com.digimaple.eims.service.CourseFileService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

import static javafx.scene.input.DataFormat.FILES;

@RequestMapping("File")
@CrossOrigin        //允许跨域
@RestController
public class FileController {
    @Autowired
    @Resource
    private CourseFileService courseFileService;

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/upload")
    public String upload(@RequestParam(value = "file", required=true) MultipartFile FILES ,
                         @RequestParam(value = "kcid", required=false) String kcid) {
        if(kcid.isEmpty()) log.info("kcid为空");
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
            String filePath = "E:\\class\\file\\coursedata";
            String path = filePath + "\\"+fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            FILES.transferTo(dest);// 文件写入
           // courseFile.setFileid(1);
            Date uptime = new Date(System.currentTimeMillis());
        CourseFile courseFile = new CourseFile();
          courseFile.setFilepath(path);
          courseFile.setFileid(1);
//          courseFile.setId();
          courseFile.setKcid(Integer.parseInt(kcid) );
           courseFile.setFilename(fileName);
           courseFile.setUptime(uptime);
            courseFileService.save(courseFile);

            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @PostMapping("/batch")
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String filePath = "/Users/dalaoyang/Downloads/";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第 " + i + " 个文件上传失败 ==> "
                            + e.getMessage();
                }
            } else {
                return "第 " + i
                        + " 个文件上传失败因为文件为空";
            }
        }
        return "上传成功";
    }

    @GetMapping("/downloa/{fileName}")
    public String downloadFile(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {

        if (fileName != null) {
            //设置文件路径
            File file = new File("E:/class/file/coursedata/"+fileName);
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

    @ApiOperation("课程id和学生id组合查询(可为空）")
    @PostMapping("/findKcidStid")
    public ResponseEntity<List<CourseFileView>>
    getByKcidStid(@RequestParam(value = "kcid",required = false) Integer kcid,
                  @RequestParam(value = "stid",required = false) Integer stid){

        List<CourseFileView> courseFileViews = courseFileService.findFileView(kcid,stid);
        return ResponseEntity.ok(courseFileViews);
    }

    @ApiOperation("按kcid查询")
    @GetMapping("/findByKcid/{kcid}")
    public ResponseEntity<List<CourseFile>> getById(@PathVariable Integer kcid){
        List<CourseFile> courseFiles = courseFileService.findByKcid(kcid);
        return ResponseEntity.ok(courseFiles);
    }

    @ApiOperation("课程id和filename组合查询")
    @PostMapping("/findKcidAndFilename")
    public ResponseEntity<List<CourseFile>> getByKcidAndFilename(@RequestParam(value = "kcid",required = false) Integer kcid,
                                                          @RequestParam(value = "filename",required = false) String filename){

        List<CourseFile> courseFile= courseFileService.findByFilenameLike(kcid,"%"+ filename+"%");
        return ResponseEntity.ok(courseFile);
    }
}
