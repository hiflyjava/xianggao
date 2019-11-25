package cc.mrbird.web.controller;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.dto.in.XgNeedPageIn;
import cc.mrbird.web.service.XgNeedService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/23 10:23
 * @Description:
 */

@RestController
@RequestMapping("/need")
public class XgNeedController extends BaseController {


    @Autowired
    XgNeedService xgNeedService;

    @RequestMapping("/addNeed")
    public RespBean addNeed(@RequestBody XgNeed xgNeed){
        xgNeed.setCreateDate(new Date());
        xgNeed.setUserId(MyUserUtiles.getUser().getUserId());
        int flag = xgNeedService.save(xgNeed);
        if(flag>=1){
            return  RespBean.ok("Save Successed");
        }else {
            return  RespBean.error("Save Failed");
        }

    }

    @RequestMapping("/updateNeed")
    public RespBean updateNeed(@RequestBody XgNeed xgNeed){
       // xgNeed.setUpdateDate(new Date());
       // xgNeed.setUserId(MyUserUtiles.getUser().getUserId());
        int flag = xgNeedService.updateNeedById(xgNeed);
        if(flag>=1){
            return  RespBean.ok("Updated Successed");
        }else {
            return  RespBean.error("Updated Failed");
        }

    }

    @RequestMapping("/checkNeed")
    public RespBean checkNeed(@RequestBody XgNeed xgNeed){
        // xgNeed.setUpdateDate(new Date());
        // xgNeed.setUserId(MyUserUtiles.getUser().getUserId());
        int flag = xgNeedService.checkedNeedByAdmin(xgNeed);
        if(flag>=1){
            return  RespBean.ok("Checked Successed");
        }else {
            return  RespBean.error("Checked Failed");
        }

    }


    @RequestMapping("/getNeedList")
    public RespBean getNeedList(@RequestBody XgNeedPageIn needPageIn){
        // xgNeed.setUpdateDate(new Date());
        // xgNeed.setUserId(MyUserUtiles.getUser().getUserId());
        PageInfo<XgNeed> needList = xgNeedService.getNeedList(needPageIn);
        return  RespBean.ok("needList",needList);

    }


}
