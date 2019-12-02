package cc.mrbird.web.controller;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.system.domain.LoginLog;
import cc.mrbird.system.service.LoginLogService;
import cc.mrbird.system.service.UserService;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.domain.XgProductionType;
import cc.mrbird.web.dto.in.LoginLogIn;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.dto.out.IndexProductionOut;
import cc.mrbird.web.dto.out.LoginLogOut;
import cc.mrbird.web.service.DashboardService;
import cc.mrbird.web.service.XgNeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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
    @RequestMapping("/getDashboardProductionNums")
    public RespBean addNeed(@RequestBody XgProductionPageIn xgProductionPageIn){

        IndexProductionOut dashboardNums = dashboardService.getDashboardNums(xgProductionPageIn);
        return  RespBean.ok("dashboardNums",dashboardNums);
    }

    @RequestMapping("/getAllProductionType")
    public RespBean addNeed(){

        List<XgProductionType> types = dashboardService.getAllProductionType();
        return  RespBean.ok("productionTypes",types);
    }

}
