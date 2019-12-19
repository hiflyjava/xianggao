package cc.mrbird.web.pcweb;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.common.util.StringUtils;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.domain.XgProductionCollection;
import cc.mrbird.web.domain.XgProductionDianzan;
import cc.mrbird.web.domain.XgProductionShare;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.service.XgProductionCollectionService;
import cc.mrbird.web.service.XgProductionDianzanService;
import cc.mrbird.web.service.XgProductionService;
import cc.mrbird.web.service.XgProductionShareService;
import cc.mrbird.web.utils.XgCodeUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static java.awt.SystemColor.info;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/23 12:29
 * @Description: 网站端接口
 */

@RestController
@RequestMapping("/pc/production")
public class XgPcWebProductionController extends BaseController {

    @Autowired
    XgProductionService productionService;

    @Autowired
    XgProductionShareService productionShareService;


    @Autowired
    XgProductionCollectionService productionCollectionService;

    @Autowired
    XgProductionDianzanService productionDianzanService;

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



    /**
     *查询首页作品的列表
     * @param
     * @return
     */

    @RequestMapping("/getPcWebPdList")
    public RespBean getPcWebPdList(@RequestBody XgProductionPageIn productionPageIn){

        if(StringUtils.isEmptyOrNull(productionPageIn.getStatus())){
            productionPageIn.setStatus(XgCodeUtil.PRODUCTION_STATUS_A);
        }else {

          if(!XgCodeUtil.PRODUCTION_STATUS_B.equals( productionPageIn.getStatus())){
              return  RespBean.error("please up right status B");
          }

        }

        PageInfo<XgProduction> list = productionService.getPcWebPdList(productionPageIn);
        return  RespBean.ok("productionList", list);


    }



    /**
     *查询作品的详情
     * @param
     * @return
     */

    @RequestMapping("/getProductionInfoById")
    public RespBean getProductionInfoById(@RequestBody XgProductionPageIn productionPageIn){

        if(StringUtils.isEmptyOrNull(productionPageIn.getId()+"")){
            return  RespBean.error("please up id");
        }

        XgProduction info = productionService.getProductionInfoById(productionPageIn);
        return  RespBean.ok("productionInfo", info);


    }


    /**
     *分享作品
     * @param
     * @return
     */

    @RequestMapping("/shareProductionById")
    public RespBean shareProductionById(@RequestBody XgProductionPageIn productionPageIn){

        if(StringUtils.isEmptyOrNull(productionPageIn.getId()+"")){
            return  RespBean.error("please up id");
        }

        if(StringUtils.isEmptyOrNull(productionPageIn.getType()+"")){
            return  RespBean.error("please up type");
        }

        int flag = productionShareService.addProductionShare(productionPageIn.getId(),productionPageIn.getType());
        if(flag>=1){
            return  RespBean.ok("shared success");
        }else {
            return  RespBean.error("shared failed");
        }



    }



    /**
     *收藏作品
     * @param
     * @return
     */

    @RequestMapping("/collectionProductionById")
    public RespBean collectionProductionById(@RequestBody XgProductionPageIn productionPageIn){

        if(StringUtils.isEmptyOrNull(productionPageIn.getId()+"")){
            return  RespBean.error("please up id");
        }

        if(StringUtils.isEmptyOrNull(productionPageIn.getType()+"")){
            return  RespBean.error("please up type");
        }

        int flag = productionCollectionService.collectionProduction(productionPageIn.getId());
        if(flag>=1){
            return  RespBean.ok("collection success");
        }else {
            return  RespBean.error("collection failed");
        }



    }




    /**
     *点赞作品
     * @param
     * @return
     */

    @RequestMapping("/dianzanProductionById")
    public RespBean dianzanProductionById(@RequestBody XgProductionPageIn productionPageIn){

        if(StringUtils.isEmptyOrNull(productionPageIn.getId()+"")){
            return  RespBean.error("please up id");
        }

        if(StringUtils.isEmptyOrNull(productionPageIn.getType()+"")){
            return  RespBean.error("please up type");
        }

        int flag = productionDianzanService.addProductionDianzan(productionPageIn.getId());
        if(flag>=1){
            return  RespBean.ok("collection success");
        }else {
            return  RespBean.error("collection failed");
        }

    }

}
