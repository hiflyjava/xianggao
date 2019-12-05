package cc.mrbird.web.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.domain.XgOrderMasterVersion;
import cc.mrbird.web.service.XgOrderMasterVersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("xgOrderMasterVersionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class XgOrderMasterVersionServiceImpl extends BaseService<XgOrderMasterVersion> implements XgOrderMasterVersionService {
}
