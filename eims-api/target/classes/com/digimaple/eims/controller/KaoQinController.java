package com.digimaple.eims.controller;

import com.digimaple.eims.model.KaoQin;
import com.digimaple.eims.model.view.KaoQinDateView;
import com.digimaple.eims.model.view.KaoQinView;
import com.digimaple.eims.service.KaoQinService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//未成功项：如何保证数据传输过程中被拦截窃取，设置数据乱码传输后被劫持是否还能被接收
//用于登录后才能用的操作
@CrossOrigin
@RestController

@RequestMapping("KaoQin")
public class KaoQinController {
    @Autowired
    @Resource
    private KaoQinService kaoQinService;


    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @ApiOperation("用户列表")
    @GetMapping("/list")
    public ResponseEntity<List<KaoQin>> getAll(){
        List<KaoQin> kaoqin = kaoQinService.getKaoQinList();
        return ResponseEntity.ok(kaoqin);
    }



    @ApiOperation("按课程id查询用户")
    @GetMapping("/findByKcid/{kcid}")
    public ResponseEntity<KaoQin> getByKcid(@PathVariable Integer kcid){
        KaoQin kaoqin = kaoQinService.findByKcid(kcid);
        return ResponseEntity.ok(kaoqin);
    }

    @ApiOperation("课程id和学生id组合查询")
    @GetMapping("/findByKcidAndStid/{kcid}/{stid}")
    public ResponseEntity<KaoQin>
    getByKcidAndStid(@PathVariable(name = "kcid",required = false) Integer kcid,
                     @PathVariable(name = "stid",required = false) Integer stid){

        KaoQin kaoqin = kaoQinService.findByKcidAndStid(kcid,stid);
        return ResponseEntity.ok(kaoqin);
    }

    @ApiOperation("课程id和学生id组合查询(可为空）")
    @PostMapping("/findKcidStid")
    public ResponseEntity<List<KaoQinView>>
    getByKcidStid(@RequestParam(value = "kcid",required = false) Integer kcid,
                  @RequestParam(value = "stid",required = false) Integer stid){

        List<KaoQinView> kaoqin = kaoQinService.findKcidStid(kcid,stid);
        return ResponseEntity.ok(kaoqin);
    }

    //教师按课程id和时间查询签到情况
    @ApiOperation("课程id和时间段组合查询")
    @PostMapping("/findKcidAndTime")
    public ResponseEntity<List<KaoQinView>>
    getByKcidAndTime(@RequestParam(value = "kcid",required = true) Integer kcid,
                     @RequestParam(value = "starttime",required = true)Date starttime,
                     @RequestParam(value = "endtime",required = true) Date endtime ){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日期格式
        List<KaoQinView> kaoqin = kaoQinService.findKaoQinByKcidAndKqtimeBetween(kcid,starttime,endtime);
        return ResponseEntity.ok(kaoqin);


    }

    //教师按课程id和时间查询签到情况
    @ApiOperation("课程id和时间组合查询")
    @PostMapping("/findKcidAndDate")
    public ResponseEntity<List<KaoQinView>>
    getByKcidAndTime(@RequestParam(value = "kcid",required = true) Integer kcid,
                     @RequestParam(value = "kqdate",required = true)Date kqdate
                      ){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//日期格式
        List<KaoQinView> kaoqin = kaoQinService.findKaoQinByKcidAndKqdate(kcid,kqdate);
        return ResponseEntity.ok(kaoqin);
    }

    //教师按课程id和时间查询签到情况
    @ApiOperation("课程id统计考勤次数")
    @GetMapping("/countByKcidAndDate/{kcid}")
    public ResponseEntity<List<KaoQinDateView>>
    countByKcidAndDate(@PathVariable(name = "kcid",required = false) Integer kcid
    ){
       List<KaoQinDateView> kaoQinDateViews = kaoQinService.countKcidAndKqdate(kcid);
        return ResponseEntity.ok(kaoQinDateViews);
    }

    @ApiOperation("新增考勤")
    @PostMapping("/add")
    public String add(@RequestBody KaoQin kaoqin) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        kaoqin.setKqdate(currentTime_2);// new Date()为获取当前系统时间
        kaoQinService.save(kaoqin);
        return "redirect:/list";
    }




}
