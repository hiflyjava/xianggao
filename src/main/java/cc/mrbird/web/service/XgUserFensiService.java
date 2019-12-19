package cc.mrbird.web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/19 09:57
 * @Description:
 */

@Service
@Transactional
public interface XgUserFensiService {

    int addUserFensi(Long toUserId);
}
