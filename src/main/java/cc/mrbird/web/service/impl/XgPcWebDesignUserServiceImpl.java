package cc.mrbird.web.service.impl;

import cc.mrbird.system.dao.UserMapper;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.dao.XgProductionMapper;
import cc.mrbird.web.dao.XgUserFensiMapper;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.domain.XgUserFensi;
import cc.mrbird.web.dto.in.UserPageIn;
import cc.mrbird.web.service.XgPcWebDesignUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/24 16:08
 * @Description:
 */

@Service
@Transactional
public class XgPcWebDesignUserServiceImpl implements XgPcWebDesignUserService {



    @Autowired
    UserMapper userMapper;
    @Autowired
    XgUserFensiMapper userFensiMapper;

    @Autowired
    XgProductionMapper productionMapper;


    @Override
    public PageInfo<User> getDesignUserListByItems(UserPageIn userPageIn) {

        PageHelper.startPage(userPageIn.getCurrentPage(), userPageIn.getPageSize());

        List<User> userListByItems = userMapper.getDesignUserListByItems(userPageIn);
           for(User user :userListByItems){
               List<XgUserFensi> fensi = userFensiMapper.getUserFensiByUserId(user.getUserId());
                   user.setFensis(fensi);

               List<XgProduction> productionList = productionMapper.getDesignUserProductionList(user.getUserId());//查询点赞数最多的前三个作品
              user.setProductions(productionList);

           }


        return new PageInfo<>(userListByItems);
    }
}
