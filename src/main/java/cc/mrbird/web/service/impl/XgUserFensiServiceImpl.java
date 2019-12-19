package cc.mrbird.web.service.impl;

import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.system.dao.UserMapper;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.dao.XgUserFensiMapper;
import cc.mrbird.web.domain.XgUserFensi;
import cc.mrbird.web.service.XgUserFensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/19 09:59
 * @Description:
 */

@Service
@Transactional
public class XgUserFensiServiceImpl implements XgUserFensiService {


    @Autowired
    XgUserFensiMapper userFensiMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public int addUserFensi(Long toUserId) {

        User user = userMapper.getUserInfoById(toUserId);
        if(user ==null){
            return -1;
        }

        XgUserFensi xgUserFensi =new XgUserFensi();
        xgUserFensi.setCreateDate(new Date());
        xgUserFensi.setFromUserId(MyUserUtiles.getUser().getUserId());//点赞人
        xgUserFensi.setFromUserImg(MyUserUtiles.getUser().getAvatar());
        xgUserFensi.setFromUserName(MyUserUtiles.getUser().getUsername());


        xgUserFensi.setToUserId(toUserId);//被点赞人
        xgUserFensi.setToUserImg(user.getAvatar());
        xgUserFensi.setToUserName(user.getUsername());

        return userFensiMapper.addUserFensi(xgUserFensi);
    }
}
