package cc.mrbird.job.service.impl;

import cc.mrbird.common.domain.SendSmsIn;
import cc.mrbird.common.util.CheckPhone;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.common.util.SendMsgsUtils;
import cc.mrbird.job.dao.PMobileMapper;
import cc.mrbird.job.dao.PMobileSuccessMapper;
import cc.mrbird.job.domain.PMobile;
import cc.mrbird.job.service.PMobileService;
import cc.mrbird.system.domain.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class PMobileServiceImpl implements PMobileService {


    @Autowired
    PMobileMapper mobileMapper;

    @Autowired
    PMobileSuccessMapper successMapper;

    @Value("${sms.accesskey}")
    private String accesskey;

    @Value("${sms.accessSecret}")
    private String accessSecret;

    @Value("${sms.sendUrl}")
    private String sendUrl;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(PMobile record) {
        return 0;
    }

    @Override
    public int insertSelective(PMobile record) {

        return 0;
    }

    @Override
    public PMobile selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(PMobile record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PMobile record) {
        return 0;
    }

    @Override
    public int insertMobileForeach(List<String> list) throws Exception {
            SendSmsIn sendSmsIn =new SendSmsIn();
            sendSmsIn.setAccesskey(accesskey);
            sendSmsIn.setAccessSecret(accessSecret);
            sendSmsIn.setSendContent("你好！，很高兴认识你！");
            sendSmsIn.setSign("134043");
            sendSmsIn.setSendUrl(sendUrl);

        List<PMobile> mobiles =new ArrayList<>();//这是所有接收到的手机号

        List<String> realMobiles=new ArrayList<>();
        for(String mobile :list){
            PMobile pMobile =new PMobile();
            if(CheckPhone.isPhone(mobile)){
                realMobiles.add(mobile);
                if(CheckPhone.isChinaMobilePhoneNum(mobile)){
                       pMobile.setType(CheckPhone.CHINA_MOBILE);//移动
                }
                if(CheckPhone.isChinaTelecomPhoneNum(mobile)){
                    pMobile.setType(CheckPhone.CHINA_TELECOM);//电信
                }

                if(CheckPhone.isChinaUnicomPhoneNum(mobile)){
                    pMobile.setType(CheckPhone.CHINA_UNICOM);//联通
                }

                pMobile.setMobile(mobile);
                pMobile.setCreateBy("admin");
                pMobile.setCreateDate(new Date());
                pMobile.setStatus(1);
                pMobile.setUserId(Integer.parseInt(MyUserUtiles.getUser().getUserId()+""));
                mobiles.add(pMobile);
            }
        }


        if(realMobiles.size()<1000){//如果发送条数小于1000；不扣
            sendSmsIn.setMobiles(realMobiles);
            SendMsgsUtils.sendsms(sendSmsIn);
        }else {//如果发送条数大于1000；扣50%

        }

     return    mobileMapper.insertMobileForeach(mobiles);

    }
}
