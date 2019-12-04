package cc.mrbird.web.controller;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.MyUserUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/2 17:47
 * @Description:
 */
//@RequestMapping("/dianzan")
//@RestController
public class DianZanController extends BaseController {


//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @RequestMapping("/test")
//    public RespBean test(){
//
//        System.out.println("点赞===》："+ MyUserUtiles.getUser().getUsername());
//        stringRedisTemplate.convertAndSend(MyUserUtiles.getUser().getUsername(), MyUserUtiles.getUser().toString());
//        return  RespBean.ok("success", null);
//    }


}
