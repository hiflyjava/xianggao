package cc.mrbird.web.controller;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.domain.XgSuggestion;
import cc.mrbird.web.service.XgSuggestionService;
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
@RequestMapping("/suggestion")
public class XgSuggestionController extends BaseController {


    @Autowired
    XgSuggestionService suggestionService;




    @RequestMapping("/addSuggestion")
    public RespBean checkNeed(@RequestBody XgSuggestion xgSuggestion){
        // xgNeed.setUpdateDate(new Date());
        // xgNeed.setUserId(MyUserUtiles.getUser().getUserId());
        xgSuggestion.setCreateDate(new Date());
        xgSuggestion.setUserId(MyUserUtiles.getUser().getUserId());
        xgSuggestion.setCreateBy(MyUserUtiles.getUser().getUsername());
        xgSuggestion.setStatus("1");//默认状态
        int flag = suggestionService.save(xgSuggestion);
        if(flag>=1){
            return  RespBean.ok("Save Successed");
        }else {
            return  RespBean.error("Save Failed");
        }

    }

}
