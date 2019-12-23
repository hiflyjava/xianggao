package cc.mrbird.web.pcweb;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.RespBean;
import cc.mrbird.web.domain.XgOrderMaster;
import cc.mrbird.web.dto.in.XgOrderPageIn;
import cc.mrbird.web.service.XgOrderMasterService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/23 16:15
 * @Description:
 */


@RestController
@RequestMapping("/pc/order")
public class XgPcWebOrderController extends BaseController {


    @Autowired
    XgOrderMasterService orderMasterService;


    /**
     * 查询我接的订单list
     * @param orderPageIn
     * @return
     */
    @RequestMapping("/getMyOrderListByUserId")
    public RespBean getMyOrderListByUserId(@RequestBody XgOrderPageIn orderPageIn){

        PageInfo<XgOrderMaster> orderListByUserId = orderMasterService.getMyOrderListByUserId(orderPageIn);

        return  RespBean.ok("myOrderList", orderListByUserId);
    }

    /**
     * 查询我发的需求list
     * @param orderPageIn
     * @return
     */
    @RequestMapping("/getMyNeedListByUserId")
    public RespBean getMyNeedListByUserId(@RequestBody XgOrderPageIn orderPageIn){
        PageInfo<XgOrderMaster> needListByUserId = orderMasterService.getMyNeedListByUserId(orderPageIn);
        return  RespBean.ok("myNeedList", needListByUserId);
    }
}
