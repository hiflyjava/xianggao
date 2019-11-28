package cc.mrbird.web.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.domain.XgSuggestion;
import cc.mrbird.web.service.XgProductionService;
import cc.mrbird.web.service.XgSuggestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/27 16:18
 * @Description:
 */

@Service("xgSuggestionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class XgSuggestionServiceImpl extends BaseService<XgSuggestion> implements XgSuggestionService {
}
