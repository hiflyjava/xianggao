package cc.mrbird.job.service;

import cc.mrbird.job.domain.In.MobilePageIn;
import cc.mrbird.job.domain.PMobile;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PMobileService {


    int deleteByPrimaryKey(Long id);

    int insert(PMobile record);

    int insertSelective(PMobile record);

    PMobile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PMobile record);

    int updateByPrimaryKey(PMobile record);

    int  insertMobileForeach(List<String> list,String content) throws Exception;

    PageInfo<PMobile> getMobileListbByItems(MobilePageIn mobilePageIn);

}
