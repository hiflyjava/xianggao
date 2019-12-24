package cc.mrbird.web.pcweb;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.dto.in.UserPageIn;
import cc.mrbird.web.dto.in.XgNeedPageIn;
import cc.mrbird.web.service.XgPcWebDesignUserService;
import cc.mrbird.web.utils.MyDateUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/24 16:46
 * @Description:
 */

@RestController
@RequestMapping("/pc/design")
public class XgPcWebDesignUserController  extends BaseController {


    @Autowired
    XgPcWebDesignUserService designUserService;




    /**
     * 得到首页设计师列表
     * @param
     * @return
     */
    @RequestMapping("/getDesignUserListByItems")
    public RespBean getDesignUserListByItems(@RequestBody UserPageIn userPageIn){

        PageInfo<User> designUsers = designUserService.getDesignUserListByItems(userPageIn);
        return  RespBean.ok("designUsers", designUsers);

    }


}
