package cc.mrbird.web.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.system.dao.UserMapper;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.dao.XgNeedMapper;
import cc.mrbird.web.dao.XgOrderMasterVersionMapper;
import cc.mrbird.web.dao.XgProductionDianzanMapper;
import cc.mrbird.web.dao.XgProductionMapper;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.domain.XgOrderMasterVersion;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.domain.XgProductionDianzan;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.dto.out.IndexUserOut;
import cc.mrbird.web.service.XgProductionService;
import cc.mrbird.web.utils.MyDateUtils;
import cc.mrbird.web.utils.XgCodeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/23 12:24
 * @Description:
 */

@Service("xgProductionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class XgProductionServiceImpl extends BaseService<XgProduction> implements XgProductionService {

    @Autowired
    XgProductionMapper productionMapper;

    @Autowired
    XgNeedMapper needMapper;

    @Autowired
    XgOrderMasterVersionMapper orderMasterVersionMapper;

   @Autowired
    XgProductionDianzanMapper productionDianzanMapper;

   @Autowired
    UserMapper userMapper;

    @Override
    public int updateProductionById(XgProduction xgProduction) {
        xgProduction.setUpdateDate(new Date());
        xgProduction.setUpdateBy(MyUserUtiles.getUser().getUsername());
        return productionMapper.updateProductionById(xgProduction);
    }

    @Override
    public int checkedProductionById(XgProduction xgProduction) {
        xgProduction.setCheckedBy(MyUserUtiles.getUser().getUsername());
        xgProduction.setCheckedDate(new Date());
        return productionMapper.updateProductionById(xgProduction);
    }

    @Override
    public int addProduction(XgProduction xgProduction) {
        return productionMapper.addProduction(xgProduction);
    }

    @Override
    public PageInfo<XgProduction> getProductionListByItems(XgProductionPageIn productionPageIn) {
        PageHelper.startPage(productionPageIn.getCurrentPage(), productionPageIn.getPageSize());
        List<XgProduction> list = productionMapper.getProductionListByItems(productionPageIn);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<XgProduction> getProductionListByItemsWithAll(XgProductionPageIn productionPageIn) {
        PageHelper.startPage(productionPageIn.getCurrentPage(), productionPageIn.getPageSize());
         productionPageIn.setUserId(MyUserUtiles.getUser().getUserId());
        List<XgProduction> list = productionMapper.getProductionListByItemsWithAll(productionPageIn);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<XgProduction> getPcWebPdList(XgProductionPageIn productionPageIn) {

        PageHelper.startPage(productionPageIn.getCurrentPage(), productionPageIn.getPageSize());
        //得到产品的点赞数；
        List<XgProduction> pdList = productionMapper.getPcWebPdList(productionPageIn);
        if(pdList==null || pdList.size()==0){
            return new PageInfo<>(null);
        }

        for (XgProduction xgProduction:pdList){
            List<XgProductionDianzan> dzs = productionDianzanMapper.getPdDzsByPid(xgProduction.getId());
              if(dzs !=null && dzs.size()>0){
                  xgProduction.setPdzCount(dzs.size());
              }else {
                  xgProduction.setPdzCount(0);
              }
        }

        //得到产品的评论数;
        //得到产品的阅读量；

        return new PageInfo<>(pdList);
    }

    @Override
    public XgProduction getProductionInfoById(XgProductionPageIn productionPageIn) {

        List<XgProduction> pdList = productionMapper.getPcWebPdList(productionPageIn);
        XgProduction xgProduction = pdList.get(0);
        List<XgProductionDianzan> dzs = productionDianzanMapper.getPdDzsByPid(productionPageIn.getId());
        if(dzs !=null && dzs.size()>0){
            xgProduction.setPdzCount(dzs.size());
        }else {
            xgProduction.setPdzCount(0);
        }

               //查询个人信息；
        User user = userMapper.getUserInfoById(xgProduction.getUserId());
        int fensiSize = user.getFensis().size();
        int productionSzize = user.getProductions().size();
        user.setFensiCount(fensiSize);
        user.setProductionCount(productionSzize);
        user.setPassword(null);
        user.setFensis(null);
        //user.setProductions(null);
        xgProduction.setUser(user);
        //得到产品的评论数;
        //得到产品的阅读量；

        return xgProduction;
    }

    @Override
    public Map<String, IndexUserOut> getDashBoardUpProNeedByItems(XgProductionPageIn productionPageIn) {
        Map<String, IndexUserOut> map =new HashMap<>();

        IndexUserOut indexUserOut =new IndexUserOut();
        //查询 所有新增用户 st=1;
        //productionPageIn.setSelectType(1);
        //得到当天的
        String startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.TODAT, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgProduction> upProByItemsToday = productionMapper.getDashBoardUpProByItems(productionPageIn);//新增的作品
        if(upProByItemsToday ==null){
            indexUserOut.setTodayNum(0);
        }else {
            indexUserOut.setTodayNum(upProByItemsToday.size());
        }

        //得到过去一周的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_WEEK, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgProduction> upProByItemsWeek = productionMapper.getDashBoardUpProByItems(productionPageIn);//新增的作品
        if(upProByItemsWeek ==null){
            indexUserOut.setWeekNum(0);
        }else {
            indexUserOut.setWeekNum(upProByItemsWeek.size());
        }

        //得到过去一个月的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_MOUTH, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgProduction> upProByItemsMouth = productionMapper.getDashBoardUpProByItems(productionPageIn);//新增的作品
        if(upProByItemsMouth ==null){
            indexUserOut.setMouthNum(0);
        }else {
            indexUserOut.setMouthNum(upProByItemsMouth.size());
        }


        //得到过去半年的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_HELF_YEAR, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgProduction> upProByItemsHelfYear = productionMapper.getDashBoardUpProByItems(productionPageIn);//新增的作品
        if(upProByItemsHelfYear ==null){
            indexUserOut.setHelfYear(0);
        }else {
            indexUserOut.setHelfYear(upProByItemsHelfYear.size());
        }


        //得到过去一年的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_YEAR, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgProduction> upProByItemsOneYear = productionMapper.getDashBoardUpProByItems(productionPageIn);//新增的作品
        if(upProByItemsOneYear ==null){
            indexUserOut.setOneYear(0);
        }else {
            indexUserOut.setOneYear(upProByItemsOneYear.size());
        }

        map.put("AddProductions",indexUserOut);




        IndexUserOut indexUserOut2 =new IndexUserOut();
        //查询 所有新增用户 st=1;
        //productionPageIn.setSelectType(1);
        //得到当天的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.TODAT, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgNeed> needByItemsToday = needMapper.getDashBoardUpNeedByItems(productionPageIn);//上传作品量
        if(needByItemsToday ==null){
            indexUserOut2.setTodayNum(0);
        }else {
            indexUserOut2.setTodayNum(needByItemsToday.size());
        }

        //得到过去一周的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_WEEK, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgNeed> needByItemsWeek = needMapper.getDashBoardUpNeedByItems(productionPageIn);//上传作品量
        if(needByItemsWeek ==null){
            indexUserOut2.setWeekNum(0);
        }else {
            indexUserOut2.setWeekNum(needByItemsWeek.size());
        }

        //得到过去一个月的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_MOUTH, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgNeed> needByItemsMouth = needMapper.getDashBoardUpNeedByItems(productionPageIn);//上传作品量
        if(needByItemsMouth ==null){
            indexUserOut2.setMouthNum(0);
        }else {
            indexUserOut2.setMouthNum(needByItemsMouth.size());
        }


        //得到过去半年的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_HELF_YEAR, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgNeed> needByItemsHelfYear = needMapper.getDashBoardUpNeedByItems(productionPageIn);//上传作品量
        if(needByItemsHelfYear ==null){
            indexUserOut2.setHelfYear(0);
        }else {
            indexUserOut2.setHelfYear(needByItemsHelfYear.size());
        }


        //得到过去一年的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_YEAR, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgNeed> needByItemsOneYear = needMapper.getDashBoardUpNeedByItems(productionPageIn);//上传作品量
        if(needByItemsOneYear ==null){
            indexUserOut2.setOneYear(0);
        }else {
            indexUserOut2.setOneYear(needByItemsOneYear.size());
        }

        map.put("AddNeeds",indexUserOut2);





        IndexUserOut indexUserOut3 =new IndexUserOut();
        //查询 所有新增用户 st=1;
        //productionPageIn.setSelectType(1);
        //得到当天的
        productionPageIn.setStatus(XgCodeUtil.ORDER_STATUS_SUCCESS);
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.TODAT, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgOrderMasterVersion> ordersSuccessToday = orderMasterVersionMapper.getOrderListByItems(productionPageIn);//成交的订单
        if(ordersSuccessToday ==null){
            indexUserOut3.setTodayNum(0);
        }else {
            indexUserOut3.setTodayNum(ordersSuccessToday.size());
        }

        //得到过去一周的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_WEEK, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgOrderMasterVersion> ordersSuccessWeek = orderMasterVersionMapper.getOrderListByItems(productionPageIn);//成交的订单
        if(ordersSuccessWeek ==null){
            indexUserOut3.setWeekNum(0);
        }else {
            indexUserOut3.setWeekNum(ordersSuccessWeek.size());
        }

        //得到过去一个月的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_MOUTH, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgOrderMasterVersion> ordersSuccessMouth = orderMasterVersionMapper.getOrderListByItems(productionPageIn);//成交的订单
        if(ordersSuccessMouth ==null){
            indexUserOut3.setMouthNum(0);
        }else {
            indexUserOut3.setMouthNum(ordersSuccessMouth.size());
        }


        //得到过去半年的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_HELF_YEAR, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgOrderMasterVersion> ordersSuccessHelfYear = orderMasterVersionMapper.getOrderListByItems(productionPageIn);//成交的订单
        if(ordersSuccessHelfYear ==null){
            indexUserOut3.setHelfYear(0);
        }else {
            indexUserOut3.setHelfYear(ordersSuccessHelfYear.size());
        }


        //得到过去一年的
        startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_YEAR, productionPageIn.getEndTime());
        productionPageIn.setStartTime(startTime);
        List<XgOrderMasterVersion> ordersSuccessOneYear = orderMasterVersionMapper.getOrderListByItems(productionPageIn);//成交的订单
        if(ordersSuccessOneYear ==null){
            indexUserOut3.setOneYear(0);
        }else {
            indexUserOut3.setOneYear(ordersSuccessOneYear.size());
        }

        map.put("SuccessOrders",indexUserOut3);


        return map;
    }

    @Override
    public PageInfo<XgProduction> myCollectionProductions(XgProductionPageIn productionPageIn) {
        PageHelper.startPage(productionPageIn.getCurrentPage(),productionPageIn.getPageSize());
        productionPageIn.setUserId(MyUserUtiles.getUser().getUserId());
        List<XgProduction> myCollectionProductions = productionMapper.myCollectionProductions(productionPageIn);

        //得到产品的点赞数；
        if(myCollectionProductions ==null || myCollectionProductions.size()==0){
            return new PageInfo<>(null);
        }
        for (XgProduction xgProduction:myCollectionProductions){
            List<XgProductionDianzan> dzs = productionDianzanMapper.getPdDzsByPid(xgProduction.getId());
            if(dzs !=null && dzs.size()>0){
                xgProduction.setPdzCount(dzs.size());
            }else {
                xgProduction.setPdzCount(0);
            }
        }

        //得到产品的评论数;
        //得到产品的阅读量；

        return new PageInfo<>(myCollectionProductions);
    }

    @Override
    public PageInfo<XgProduction> getMyStarProductionList(XgProductionPageIn productionPageIn) {
        PageHelper.startPage(productionPageIn.getCurrentPage(), productionPageIn.getPageSize());
        //得到产品的点赞数；
        List<XgProduction> pdList = productionMapper.getMyStarProductionList(productionPageIn);
        if(pdList==null || pdList.size()==0){
            return new PageInfo<>(null);
        }

        for (XgProduction xgProduction:pdList){
            List<XgProductionDianzan> dzs = productionDianzanMapper.getPdDzsByPid(xgProduction.getId());
            if(dzs !=null && dzs.size()>0){
                xgProduction.setPdzCount(dzs.size());
            }else {
                xgProduction.setPdzCount(0);
            }
        }

        //得到产品的评论数;
        //得到产品的阅读量；

        return new PageInfo<>(pdList);
    }


}
