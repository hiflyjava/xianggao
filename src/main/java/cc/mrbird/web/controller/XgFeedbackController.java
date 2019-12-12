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
import cc.mrbird.web.dto.in.XgSysFeedbackIn;
import cc.mrbird.web.service.XgFeedbackService;
import cc.mrbird.web.utils.FeedbackTree;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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
           toUsername="Admin";
           xgFeedback.setType("ONE");//第一条
            xgFeedback.setStatus("N");//初始化，还没被阅读
            xgFeedback.setParentId(parentId);
        }else {


            User user = userService.findByName(xgFeedback.getToUsername());
            List<XgFeedback> feedbacks = xgFeedbackService.getParentFeedbackById(xgFeedback.getParentId());//查询父评论
            if(feedbacks!=null && feedbacks.size()>0){
                XgFeedback xgFeedback1 = feedbacks.get(0);
                xgFeedback.setType(xgFeedback1.getType());
                xgFeedback.setFeedbackTitle(xgFeedback1.getFeedbackTitle());
                xgFeedback.setStatus("H");//回复
                xgFeedback.setfType(xgFeedback1.getfType());
            }else {
                return  RespBean.error("没有父反馈");
            }

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


    /**
     * 根据feedback id 查询 评论的所有回复
     * @param feedbackIn
     * @return
     */
    @RequestMapping("/getFeedbackTreeById")
    public  RespBean getFeedbackTreeById(@RequestBody XgSysFeedbackIn feedbackIn){
        if(feedbackIn !=null && StringUtils.isEmptyOrNull( feedbackIn.getId()+"")){
            return  RespBean.error("please take id");
        }
        FeedbackTree<XgFeedback> feedbackTree = xgFeedbackService.getFeedbackTree(feedbackIn);
        List<FeedbackTree<XgFeedback>> children = feedbackTree.getChildren();
        for (FeedbackTree<XgFeedback> feeback: children) {
            String id = feeback.getId();
            if(feedbackIn.getId().equals(id)){
                return RespBean.ok("feedbackList",feeback);
            }
        }
        return  RespBean.ok("feedbackList", null);
    }

    /**
     * 查询所有评论 第一条
     * @param feedbackIn
     * @return
     */
    @RequestMapping("/getFeedbackList")
    public  RespBean getFeedbackList(@RequestBody XgSysFeedbackIn feedbackIn){
        feedbackIn.setType("ONE");
        PageInfo<XgFeedback> list = xgFeedbackService.getFeedbackListByItems(feedbackIn);
        return  RespBean.ok("feedbackList", list);
    }


    @RequestMapping("/getFeedbackTree")
    public  RespBean getFeedbackTree(@RequestBody XgSysFeedbackIn feedbackIn){
        FeedbackTree<XgFeedback> feedbackTree = xgFeedbackService.getFeedbackTree(feedbackIn);


        return  RespBean.ok("feedbackTree", feedbackTree);
    }

}
