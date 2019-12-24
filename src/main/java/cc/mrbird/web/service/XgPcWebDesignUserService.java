package cc.mrbird.web.service;

import cc.mrbird.system.domain.User;
import cc.mrbird.web.dto.in.UserPageIn;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/24 16:08
 * @Description:
 */

@Service
@Transactional
public interface XgPcWebDesignUserService {


    PageInfo<User> getDesignUserListByItems(UserPageIn userPageIn);


}
