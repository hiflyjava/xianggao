package cc.mrbird.web.service.impl;

import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.service.IService;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.TreeUtils;
import cc.mrbird.system.domain.Menu;
import cc.mrbird.web.dao.XgFeedbackMapper;
import cc.mrbird.web.domain.XgFeedback;
import cc.mrbird.web.dto.in.XgSysFeedbackIn;
import cc.mrbird.web.service.XgFeedbackService;
import cc.mrbird.web.utils.FeedbackTree;
import cc.mrbird.web.utils.FeedbackTreeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/27 16:21
 * @Description:
 */

@Service("xgFeedbackService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class XgFeedbackServiceImpl extends BaseService<XgFeedback> implements XgFeedbackService {

    @Autowired
    XgFeedbackMapper xgFeedbackMapper;


    @Override
    public FeedbackTree<XgFeedback> getFeedbackTree(XgSysFeedbackIn xgFeedback) {
        List<FeedbackTree<XgFeedback>> trees = new ArrayList<FeedbackTree<XgFeedback>>();



        List<XgFeedback> xgFeedbacks = xgFeedbackMapper.getFeedList(xgFeedback);
        for (XgFeedback feedback : xgFeedbacks) {
            FeedbackTree<XgFeedback> tree = new FeedbackTree<XgFeedback>();
            tree.setId(feedback.getId().toString());
            tree.setParentId(feedback.getParentId().toString());
            tree.setText(feedback.getFeedbackInfo());
            tree.setToUsername(feedback.getToUsername());
            tree.setFromUsername(feedback.getFromUsername());
            tree.setToUserImg(feedback.getToUserImg());
            tree.setFromUserImg(feedback.getFromUserImg());
            tree.setCreateDate(feedback.getCreateDate());
            tree.setFeedbackTitle(feedback.getFeedbackTitle());
            tree.setSuggestInfo(feedback.getSuggestInfo());
            tree.setType(feedback.getType());
            tree.setSuggestionUserName(feedback.getSuggestionUserName());
            trees.add(tree);
        }
        FeedbackTree<XgFeedback> t = FeedbackTreeUtils.build(trees);
        return t;
    }

    @Override
    public PageInfo<XgFeedback> getFeedbackListByItems(XgSysFeedbackIn xgSysFeedbackIn) {
        PageHelper.startPage(xgSysFeedbackIn.getCurrentPage(),xgSysFeedbackIn.getPageSize());
        List<XgFeedback> feedList = xgFeedbackMapper.getFeedList(xgSysFeedbackIn);
        return new PageInfo<>(feedList);
    }

    @Override
    public int addFeedback(XgFeedback feedback) {
        int i = xgFeedbackMapper.addFeedback(feedback);
        if(i>=1){
            return  Integer.parseInt(feedback.getId()+"");
        }
        return i;
    }

    @Override
    public int updateFeedback(XgFeedback feedback) {

        return xgFeedbackMapper.updateFeedback(feedback);
    }

    @Override
    public List<XgFeedback> getParentFeedbackById(Long parentId) {
       return xgFeedbackMapper.getParentFeedbackById(parentId);

    }
}
