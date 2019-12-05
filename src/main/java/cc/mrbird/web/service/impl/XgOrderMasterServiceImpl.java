package cc.mrbird.web.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.domain.XgOrderMaster;
import cc.mrbird.web.service.XgOrderMasterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("xgOrderMasterService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class XgOrderMasterServiceImpl extends BaseService<XgOrderMaster> implements XgOrderMasterService {
}
