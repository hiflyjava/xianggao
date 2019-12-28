package cc.mrbird.web.pcweb;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.common.util.StringUtils;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.domain.XgUserFensi;
import cc.mrbird.web.dto.in.UserPageIn;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.service.XgPcWebDesignUserService;
import cc.mrbird.web.service.XgProductionService;
import cc.mrbird.web.service.XgUserFensiService;
import cc.mrbird.web.utils.XgCodeUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/19 09:56
 * @Description:
 */

@RestController
@RequestMapping("/pc/user")
public class XgPcWebUserController  extends BaseController {

    @Autowired
    XgUserFensiService userFensiService;

    @Autowired
    XgPcWebDesignUserService designUserService;

    @Autowired
    XgProductionService productionService;

    /**
     *关注设计师
     * @param
     * @return
     */

    @RequestMapping("/addFensi")
    public RespBean addFensi(@RequestBody XgProductionPageIn productionPageIn){

        if(StringUtils.isEmptyOrNull(productionPageIn.getToUserId()+"")){
            return  RespBean.error("please up toUserId");
        }



        int flag = userFensiService.addUserFensi(productionPageIn.getToUserId());
        if(flag>=1){
            return  RespBean.ok("addFensi success");
        }else {
            return  RespBean.error("addFensi failed");
        }

    }


    /**
     * 得到我关注的人的发布的作品
     * @param productionPageIn
     * @return
     */

    @RequestMapping("/getMyStarProductionList")
    public RespBean getMyStarProductionList(@RequestBody XgProductionPageIn productionPageIn){

        if(StringUtils.isEmptyOrNull(productionPageIn.getStatus())){  //这里是可以帅选首推的作品
            productionPageIn.setStatus(XgCodeUtil.PRODUCTION_STATUS_A);
        }else {

            if(!XgCodeUtil.PRODUCTION_STATUS_B.equals( productionPageIn.getStatus())){
                return  RespBean.error("please up right status B");
            }

        }
        PageInfo<XgProduction> starProductionList = productionService.getMyStarProductionList(productionPageIn);
             return RespBean.ok("starProductionList",starProductionList);

    }




    /**
     * 得到我关注的设计师列表
     * @param
     * @return
     */
    @RequestMapping("/getMyStarDesignUserListByItems")
    public RespBean getMyStarDesignUserListByItems(@RequestBody UserPageIn userPageIn){

        userPageIn.setFromUserId(MyUserUtiles.getUser().getUserId());
        PageInfo<User> designUsers = designUserService.getMyStarDesignUserListByItems(userPageIn);
        return  RespBean.ok("designUsers", designUsers);

    }

    /**
     * 得到关注我的设计师列表(粉丝表)
     * @param
     * @return
     */
    @RequestMapping("/getMyFansDesignUserListByItems")
    public RespBean getMyFansDesignUserListByItems(@RequestBody UserPageIn userPageIn){

        userPageIn.setToUserId(MyUserUtiles.getUser().getUserId());
        PageInfo<User> designUsers = designUserService.getMyFansDesignUserListByItems(userPageIn);
        return  RespBean.ok("designUsers", designUsers);

    }




}
