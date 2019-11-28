package cc.mrbird.web.controller;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.common.util.StringUtils;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.service.UserService;
import cc.mrbird.web.domain.XgFeedback;
import cc.mrbird.web.domain.XgSuggestion;
import cc.mrbird.web.service.XgFeedbackService;
import cc.mrbird.web.utils.FeedbackTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/27 16:29
 * @Description:
 */

@RestController
@RequestMapping("/feedback")
public class XgFeedbackController extends BaseController {


    @Autowired
    XgFeedbackService xgFeedbackService;

    @Autowired
    UserService userService;


    @RequestMapping("/addFeedBack")
    public RespBean checkNeed(@RequestBody XgFeedback xgFeedback){
        Long toUserId=0l;
        String toUsername="";
        Long parentId=0l;
      //  String toUserImg="";
        if(StringUtils.isEmptyOrNull(xgFeedback.getToUsername()+"")){//新增第一条评论
           toUserId=0l;
           toUsername="";
           xgFeedback.setType("ONE");//第一条
            xgFeedback.setParentId(parentId);
        }else {


            User user = userService.findByName(xgFeedback.getToUsername());
           if(user !=null){
               toUserId=user.getUserId();
               toUsername=user.getUsername();
              // toUserImg=user.getAvatar();
               xgFeedback.setType("MORE");//多条评论

           }else {
               return  RespBean.error("被评论的用户名不存在");
           }

        }
        xgFeedback.setToUserId(toUserId);
       // xgFeedback.setToUserImg(toUserImg);
     //   xgFeedback.setFromUserImg(MyUserUtiles.getUser().getAvatar());
        xgFeedback.setFromUsername(MyUserUtiles.getUser().getUsername());
        xgFeedback.setToUsername(toUsername);
        xgFeedback.setCreateBy(MyUserUtiles.getUser().getUsername());
        xgFeedback.setCreateDate(new Date());
        xgFeedback.setUserId(MyUserUtiles.getUser().getUserId());
        int flag = xgFeedbackService.addFeedback(xgFeedback);
        if(flag>=1){
            return  RespBean.ok("Save Successed",flag);
        }else {
            return  RespBean.error("Save Failed");
        }

    }


    @RequestMapping("/getFeedbackTree")
    public  RespBean getFeedbackTree(@RequestBody XgFeedback xgFeedback){
        FeedbackTree<XgFeedback> feedbackTree = xgFeedbackService.getFeedbackTree(xgFeedback);

         return  RespBean.ok("feedbackTree", feedbackTree);
    }



}
