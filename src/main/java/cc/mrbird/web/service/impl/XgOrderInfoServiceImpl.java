package cc.mrbird.web.service.impl;


import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.domain.XgOrderInfo;
import cc.mrbird.web.service.XgOrderInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("xgOrderInfoService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class XgOrderInfoServiceImpl extends BaseService<XgOrderInfo> implements XgOrderInfoService {
}
