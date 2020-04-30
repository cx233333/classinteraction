package com.digimaple.eims.controller;

import com.digimaple.eims.model.KaoQin;
import com.digimaple.eims.model.XuanKe;
import com.digimaple.eims.model.view.KaoQinView;
import com.digimaple.eims.model.view.XuanKeView;
import com.digimaple.eims.service.KaoQinService;
import com.digimaple.eims.service.StudentService;
import com.digimaple.eims.service.XuanKeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//未成功项：如何保证数据传输过程中被拦截窃取，设置数据乱码传输后被劫持是否还能被接收
//用于登录后才能用的操作
@CrossOrigin
@RestController

@RequestMapping("XuanKe")
public class XuanKeController {
    @Autowired
    @Resource
    private XuanKeService xuanKeService;
    @Autowired
    private StudentService studentService;


    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @ApiOperation("选课列表")
    @GetMapping("/list")
    public ResponseEntity<List<XuanKe>> getAll(){
        List<XuanKe> xuanKes = xuanKeService.getXuanKeList();
        return ResponseEntity.ok(xuanKes);
    }

    @ApiOperation("选课列表带名字")
    @GetMapping("/xuankelist")
    public ResponseEntity<List<XuanKeView>> getList(){
        List<XuanKeView> xuanKeList= xuanKeService.findList();
        return ResponseEntity.ok(xuanKeList);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete")
    public String delete(Integer id) {
        xuanKeService.delete(id);
        return "1";
    }

    @ApiOperation("按课程名字模糊查询选课列表")
    @PostMapping("/findbykcname")
    public ResponseEntity<List<XuanKeView>> getByKcname( @RequestParam(value = "kcname",required = true)String kcname){
        List<XuanKeView> xuanKeList= xuanKeService.findByKcname( "%"+kcname+"%");
        return ResponseEntity.ok(xuanKeList);
    }


    @ApiOperation("新增选课")
    @PostMapping("/add")
    public String add(@RequestBody XuanKe xuanKe) {
      Integer stid =   studentService.findBySno(xuanKe.getStid()).get(0).getId();
      xuanKe.setStid(stid);
       xuanKeService.save(xuanKe);
        return "redirect:/list";
    }




}
