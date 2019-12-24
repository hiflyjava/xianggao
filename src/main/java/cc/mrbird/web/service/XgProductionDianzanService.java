package cc.mrbird.web.service;

import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import com.github.pagehelper.PageInfo;
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
