package cc.mrbird.web.service;

import cc.mrbird.web.domain.XgProductionType;
import cc.mrbird.web.dto.in.XgProductionPageIn;

import cc.mrbird.web.dto.out.IndexProductionOuts;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/2 19:51
 * @Description:
 */
@Service
@Transactional
public interface DashboardService   {


   IndexProductionOuts getDashboardNums(XgProductionPageIn xgProductionPageIn);


   List<XgProductionType> getAllProductionType();

}
