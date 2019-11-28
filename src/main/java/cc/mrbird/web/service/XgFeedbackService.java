package cc.mrbird.web.service;

import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.Menu;
import cc.mrbird.web.domain.XgFeedback;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.utils.FeedbackTree;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/27 16:20
 * @Description:
 */
public interface XgFeedbackService extends IService<XgFeedback> {

    FeedbackTree<XgFeedback> getFeedbackTree(XgFeedback xgFeedback);

    int addFeedback(XgFeedback feedback);

    int updateFeedback(XgFeedback feedback);
}
