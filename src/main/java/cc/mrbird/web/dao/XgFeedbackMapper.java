package cc.mrbird.web.dao;


import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgFeedback;
import cc.mrbird.web.dto.in.XgSysFeedbackIn;

import java.util.List;

public interface XgFeedbackMapper  extends MyMapper<XgFeedback> {

    int updateFeedback(XgFeedback xgFeedback);
    int addFeedback(XgFeedback xgFeedback);

    List<XgFeedback> getFeedList(XgSysFeedbackIn xgFeedback);
    List<XgFeedback> getParentFeedbackById(Long parentId);
}