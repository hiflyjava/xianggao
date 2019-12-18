package cc.mrbird.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/18 18:45
 * @Description:
 */

@Service
@Transactional
public interface XgProductionDianzanService {


    int addProductionDianzan(Long pid);

}
