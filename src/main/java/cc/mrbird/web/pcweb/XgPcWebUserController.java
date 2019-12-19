package cc.mrbird.web.pcweb;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.StringUtils;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.domain.XgUserFensi;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.service.XgUserFensiService;
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

    /**
     *点赞作品
     * @param
     * @return
     */

    @RequestMapping("/addFensi")
    public RespBean dianzanProductionById(@RequestBody XgProductionPageIn productionPageIn){

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


}
