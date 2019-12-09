package cc.mrbird.web.controller;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.system.domain.LoginLog;
import cc.mrbird.system.service.LoginLogService;
import cc.mrbird.system.service.UserService;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.domain.XgOrderMasterVersion;
import cc.mrbird.web.domain.XgProductionType;
import cc.mrbird.web.dto.in.LoginLogIn;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.dto.out.IndexProductionOuts;
import cc.mrbird.web.dto.out.IndexUserOut;
import cc.mrbird.web.dto.out.LoginLogOut;
import cc.mrbird.web.service.DashboardService;
import cc.mrbird.web.service.XgNeedService;
import cc.mrbird.web.service.XgOrderMasterVersionService;
import cc.mrbird.web.service.XgProductionService;
import cc.mrbird.web.service.impl.XgProductionServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/2 15:52
 * @Description:
 */

@RestController
@RequestMapping("/dashboard")
public class DashBoardController extends BaseController {



    @Autowired
    XgNeedService xgNeedService;

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    UserService userService;

    @Autowired
    DashboardService dashboardService;

    @Autowired
    XgOrderMasterVersionService orderMasterVersionService;


    @Autowired
    XgProductionService productionService;


    /**
     * 每日登陆过的数量和总用户数
     * @param LoginLogIn
     * @return
     */
    @RequestMapping("/loginLog")
    public RespBean addNeed(@RequestBody LoginLogIn LoginLogIn){
        System.out.println("aaaa");
        LoginLogOut loginLogOut = loginLogService.getLoginListByItems(LoginLogIn);

        return  RespBean.ok("loginLogOut",loginLogOut);
    }


    /**
     * 获得首页作品的数据
     * @param xgProductionPageIn
     * @return
     */
    @RequestMapping("/getDashboardInteractionNums")
    public RespBean addNeed(@RequestBody XgProductionPageIn xgProductionPageIn){

        IndexProductionOuts dashboardNums = dashboardService.getDashboardNums(xgProductionPageIn);
        return  RespBean.ok("dashboardInteractionNums",dashboardNums);
    }



    @RequestMapping("/getAllProductionType")
    public RespBean addNeed(){

        List<XgProductionType> types = dashboardService.getAllProductionType();
        return  RespBean.ok("productionTypes",types);
    }


    /**
     * 首页得到不同时间内的创建的订单
     * @param xgProductionPageIn
     * @return
     */

    @RequestMapping("/getOrderListByItems")
    public RespBean getOrderListByItems(@RequestBody XgProductionPageIn xgProductionPageIn){

        PageInfo<XgOrderMasterVersion> list = orderMasterVersionService.getOrderListByItems(xgProductionPageIn);
        return  RespBean.ok("orderList",list);
    }



    /**
     * 首页得到不同时间内的user vip
     * @param xgProductionPageIn
     * @return
     */

    @RequestMapping("/getDashBoardUsersByItems")
    public RespBean getDashBoardUsersByItems(@RequestBody XgProductionPageIn xgProductionPageIn){

        Map<String, IndexUserOut> map = userService.getDashBoardUsersByItems(xgProductionPageIn);

        return  RespBean.ok("userMap",map);
    }


    /**
     * 得到首页工作量数据
     * @param xgProductionPageIn
     * @return
     */

    @RequestMapping("/getDashBoardUpProNeedByItems")
    public RespBean getDashBoardUpProductionsByItems(@RequestBody XgProductionPageIn xgProductionPageIn){

        Map<String, IndexUserOut> map = productionService.getDashBoardUpProNeedByItems(xgProductionPageIn);

        return  RespBean.ok("userMap",map);
    }



}
