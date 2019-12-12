package cc.mrbird.web.service;

import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.Menu;
import cc.mrbird.web.domain.XgFeedback;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.dto.in.XgSysFeedbackIn;
import cc.mrbird.web.utils.FeedbackTree;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/27 16:20
 * @Description:
 */
public interface XgFeedbackService extends IService<XgFeedback> {

    FeedbackTree<XgFeedback> getFeedbackTree(XgSysFeedbackIn xgFeedback);

    PageInfo<XgFeedback> getFeedbackListByItems(XgSysFeedbackIn xgSysFeedbackIn);

    int addFeedback(XgFeedback feedback);

    int updateFeedback(XgFeedback feedback);

    List<XgFeedback>getParentFeedbackById(Long parentId);
}
