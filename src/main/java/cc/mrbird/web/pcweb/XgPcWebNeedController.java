package cc.mrbird.web.pcweb;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.common.util.StringUtils;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.domain.XgOrderMaster;
import cc.mrbird.web.domain.XgPayOrder;
import cc.mrbird.web.dto.in.XgNeedPageIn;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.service.XgNeedService;
import cc.mrbird.web.service.XgOrderMasterService;
import cc.mrbird.web.service.XgPayOrderService;
import cc.mrbird.web.utils.MyDateUtils;
import cc.mrbird.web.utils.MyUUIDUtils;
import cc.mrbird.web.utils.XgCodeUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/19 11:35
 * @Description:
 */



@RestController
@RequestMapping("/pc/need")
public class XgPcWebNeedController extends BaseController {





    @Autowired
    XgNeedService xgNeedService;


    @Autowired
    XgOrderMasterService orderMasterService;

    @Autowired
    XgPayOrderService payOrderService;//支付的订单



    /**
     * 雇佣，像特定的设计师发布需求；
     * @param xgNeed
     * @return
     */

    @RequestMapping("/addNeedandOrder")
    public RespBean addNeedandOrder(@RequestBody XgNeed xgNeed){


        if(StringUtils.isEmptyOrNull(xgNeed.getDesignUserId()+"")){
            return  RespBean.error("please take designUserId");
        }
        if(StringUtils.isEmptyOrNull(xgNeed.getStartTime()+"")){
            return  RespBean.error("please up startTime");
        }
        if(StringUtils.isEmptyOrNull(xgNeed.getEndTime()+"")){
            return  RespBean.error("please up endTime");
        }


        if(StringUtils.isEmptyOrNull(xgNeed.getNeedValidityEndTime()+"")){
            return  RespBean.error("please up NeedValidityEndTime");
        }

        if(StringUtils.isEmptyOrNull(xgNeed.getNeedValidityStartTime()+"")){
            return  RespBean.error("please up NeedValidityStartTime");
        }

        long daySub = MyDateUtils.getDaySub(MyDateUtils.getStringDate(xgNeed.getEndTime()), MyDateUtils.getStringDate(xgNeed.getStartTime()));
        xgNeed.setNeedDays(Integer.parseInt(daySub+""));//需求花费时间

        long daySubs = MyDateUtils.getDaySub(MyDateUtils.getStringDate(xgNeed.getNeedValidityEndTime()), MyDateUtils.getStringDate(xgNeed.getNeedValidityStartTime()));
        xgNeed.setNeedValidityDays(Integer.parseInt(daySubs+""));//需求有效期

        String orderNo=MyUUIDUtils.getUUid();

        xgNeed.setNeedMode(XgCodeUtil.NEED_MODEL_GY);//need模式设为雇佣
        xgNeed.setNeedCheckStatus(XgCodeUtil.NEED_CHECK_STATUS_INIT);//初始化状态
        xgNeed.setNeedPayStatus(XgCodeUtil.NEED_PAY_STATUS_INIT);//初始化状态
        int flag = xgNeedService.addNeed(xgNeed);
        if(flag>=1){
            XgOrderMaster xgOrderMaster =new XgOrderMaster();//新增订单 订单状态是：I
            xgOrderMaster.setStatus(XgCodeUtil.ORDER_STATUS_BUILD);//新增订单 订单状态是：I ,还没被设计师审核
            xgOrderMaster.setOrderNum(orderNo);
            xgOrderMaster.setDesignUserId(xgNeed.getDesignUserId());
            xgOrderMaster.setNeedId(xgNeed.getId());//主键回填
            xgOrderMaster.setMony(xgNeed.getBudget());//预算
            xgOrderMaster.setTelephone(xgNeed.getTelephone());
            int i = orderMasterService.addOrderMaster(xgOrderMaster);
            if(i>=1){
                //这个时候需 要做支付前准备订单;
                XgPayOrder payOrder =new XgPayOrder();
                payOrder.setBalance(new BigDecimal(xgNeed.getBudget()));//转类型
                payOrder.setOrderNo(orderNo);
                i = payOrderService.addPayOrder(payOrder);
                if(i>=1){
                    return  RespBean.ok("orderNo",orderNo);
                }else {
                    return  RespBean.error("Save Failed");
                }

            }else {
                return  RespBean.error("Save Failed");
            }
        }else {
            return  RespBean.error("Save Failed");
        }

    }


    /**
     * 得到首页需求列表
     * @param needPageIn
     * @return
     */
    @RequestMapping("/getNeedListByItems")
    public  RespBean getPcWebNeedList(@RequestBody XgNeedPageIn needPageIn){

        needPageIn.setNowTime(MyDateUtils.getTodayZeroTime());
        PageInfo<XgNeed> needList = xgNeedService.getPcWebNeedListByItems(needPageIn);
             return  RespBean.ok("needList", needList);

    }


    @RequestMapping("/addNeed")
    public RespBean addNeed(@RequestBody XgNeed xgNeed){


        if(StringUtils.isEmptyOrNull(xgNeed.getStartTime()+"")){
            return  RespBean.error("please up startTime");
        }
        if(StringUtils.isEmptyOrNull(xgNeed.getEndTime()+"")){
            return  RespBean.error("please up endTime");
        }


        if(StringUtils.isEmptyOrNull(xgNeed.getNeedValidityEndTime()+"")){
            return  RespBean.error("please up NeedValidityEndTime");
        }

        if(StringUtils.isEmptyOrNull(xgNeed.getNeedValidityStartTime()+"")){
            return  RespBean.error("please up NeedValidityStartTime");
        }

        long daySub = MyDateUtils.getDaySub(MyDateUtils.getStringDate(xgNeed.getEndTime()), MyDateUtils.getStringDate(xgNeed.getStartTime()));
        xgNeed.setNeedDays(Integer.parseInt(daySub+""));//需求花费时间

        long daySubs = MyDateUtils.getDaySub(MyDateUtils.getStringDate(xgNeed.getNeedValidityEndTime()), MyDateUtils.getStringDate(xgNeed.getNeedValidityStartTime()));
        xgNeed.setNeedValidityDays(Integer.parseInt(daySubs+""));//需求有效期

        xgNeed.setCreateDate(new Date());
        xgNeed.setUserId(MyUserUtiles.getUser().getUserId());
        xgNeed.setNeedCheckStatus(XgCodeUtil.NEED_CHECK_STATUS_INIT);//初始化状态
        xgNeed.setNeedPayStatus(XgCodeUtil.NEED_PAY_STATUS_INIT);//初始化状态
        int flag = xgNeedService.addNeed(xgNeed);
        if(flag>=1){
            return  RespBean.ok("Save Successed");
        }else {
            return  RespBean.error("Save Failed");
        }

    }




}
