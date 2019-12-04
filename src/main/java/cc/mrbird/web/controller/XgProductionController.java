package cc.mrbird.web.controller;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.service.XgProductionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/23 12:29
 * @Description:
 */

@RestController
@RequestMapping("/production")
public class XgProductionController extends BaseController {

    @Autowired
    XgProductionService productionService;



    @RequestMapping("/checkProduction")
    public RespBean checkNeed(@RequestBody XgProduction xgProduction){
        // xgNeed.setUpdateDate(new Date());
        // xgNeed.setUserId(MyUserUtiles.getUser().getUserId());
        int flag = productionService.checkedProductionById(xgProduction);
        if(flag>=1){
            return  RespBean.ok("Checked Successed");
        }else {
            return  RespBean.error("Checked Failed");
        }

    }



    @RequestMapping("/updateProduction")
    public RespBean updateProduction(@RequestBody XgProduction xgProduction){
        // xgNeed.setUpdateDate(new Date());
        // xgNeed.setUserId(MyUserUtiles.getUser().getUserId());
        int flag = productionService.updateProductionById(xgProduction);
        if(flag>=1){
            return  RespBean.ok("Updated Successed");
        }else {
            return  RespBean.error("Updated Failed");
        }

    }


    @RequestMapping("/addProduction")
    public RespBean addProduction(@RequestBody XgProduction xgProduction){
        // xgNeed.setUpdateDate(new Date());
        // xgNeed.setUserId(MyUserUtiles.getUser().getUserId());
        xgProduction.setCreateBy(MyUserUtiles.getUser().getUsername());
        xgProduction.setUserId(MyUserUtiles.getUser().getUserId());
        xgProduction.setCreateDate(new Date());
        xgProduction.setStatus("A");
        int flag = productionService.addProduction(xgProduction);
        if(flag>=1){
            return  RespBean.ok("Save Successed");
        }else {
            return  RespBean.error("Save Failed");
        }

    }

    @RequestMapping("/getProductionList")
    public RespBean getProductionList(@RequestBody XgProductionPageIn productionPageIn){
        // xgNeed.setUpdateDate(new Date());
        // xgNeed.setUserId(MyUserUtiles.getUser().getUserId());

        PageInfo<XgProduction> list = productionService.getProductionListByItems(productionPageIn);
        return  RespBean.ok("productionList", list);


    }


    /**
     *
     * @param productionPageIn
     * @return
     */

    @RequestMapping("/getProductionListWithAll")
    public RespBean getProductionListWithAll(@RequestBody XgProductionPageIn productionPageIn){
        // xgNeed.setUpdateDate(new Date());
        // xgNeed.setUserId(MyUserUtiles.getUser().getUserId());

        PageInfo<XgProduction> list = productionService.getProductionListByItemsWithAll(productionPageIn);
        return  RespBean.ok("productionList", list);


    }




}
