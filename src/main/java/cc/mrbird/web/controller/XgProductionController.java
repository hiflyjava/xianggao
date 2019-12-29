package cc.mrbird.web.controller;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.service.XgProductionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/23 12:29
 * @Description:
 */
@Api(description = "作品操作接口")
@RestController
@RequestMapping("/production")
public class XgProductionController extends BaseController {

    @Autowired
    XgProductionService productionService;



    @RequestMapping("/checkProduction")
    @ApiOperation(value = "审核作品",httpMethod = "POST")
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


    @ApiOperation(value = "修改作品",httpMethod = "POST")
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

    @ApiOperation(value = "新增作品",httpMethod = "POST")
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

    @ApiOperation(value = "查询作品列表", notes="查询所有作品列表",httpMethod ="POST")
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
    @ApiOperation(value = "查询所有的作品列表", notes="查询所有作品列表",response = XgProduction.class )
    @RequestMapping("/getProductionListWithAll")
    public RespBean getProductionListWithAll(@RequestBody XgProductionPageIn productionPageIn){
        // xgNeed.setUpdateDate(new Date());
        // xgNeed.setUserId(MyUserUtiles.getUser().getUserId());

        PageInfo<XgProduction> list = productionService.getProductionListByItemsWithAll(productionPageIn);
        return  RespBean.ok("productionList", list);


    }




}
