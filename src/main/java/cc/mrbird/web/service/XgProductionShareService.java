package cc.mrbird.web.service;

import cc.mrbird.web.domain.XgProductionShare;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/18 18:25
 * @Description:
 */

@Service
@Transactional
public interface XgProductionShareService {

    int addProductionShare(Long pid,String type);
}
