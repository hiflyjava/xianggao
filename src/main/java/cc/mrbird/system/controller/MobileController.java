package cc.mrbird.system.controller;



import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.job.domain.In.MobilePageIn;
import cc.mrbird.job.domain.PMobile;
import cc.mrbird.job.service.PMobileService;
import cc.mrbird.system.domain.Role;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.utils.ImportExcelUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.*;


@Controller
public class MobileController  extends BaseController {


    private static  final Logger logger = Logger.getLogger(MobileController.class);
   @Autowired
    PMobileService pMobileService;

    /**
     * 批量上传手机号
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/batchAddMobiles")
    @ResponseBody
    public String batchAddMobiles(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request,@RequestParam(value = "content")String content) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Set<String> mobiles = new HashSet<>();

        //excel文件上传
        String path = request.getSession().getServletContext().getRealPath("/");
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        try {
            if (!file.isEmpty()) {
                // 这里将上传得到的文件保存指定目录下
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, fileName));
            }

            InputStream in = null;
            List<List<Object>> listob = null;
            in = file.getInputStream();
            listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
            for (int i = 0; i < listob.size(); i++) {
                List<Object> lo = listob.get(i);
                //TODO 根据excel格式获取指定的参数,还可优化:用正则表达式匹配过滤不符合的参数
                String mobile = String.valueOf(lo.get(0));
                mobiles.add(mobile);
            }

        } catch (Exception e) {
            //TODO 记录完善的日志和异常处理
            logger.error("batchAddMobiles()失败,原因为:" + e.getMessage(), e);
        } finally {
            //处理完后需删除本地文件
            File file2 = new File(path + File.separator + fileName);
            if (file2.exists()) {
                file2.delete();
            }
        }

        //添加判断&业务处理
        if (null != mobiles && mobiles.size() > 0) {
            //TODO 业务逻辑处理
        }

        //返回结果
        result.put("mobiles", mobiles);
        List<String> mobileList=new ArrayList<>();
        mobileList.addAll(mobiles);
        pMobileService.insertMobileForeach(mobileList, content);
        return JSON.toJSONString(result);
    }




    @RequestMapping("mobile")
   // @RequiresPermissions("mobile:list")
    public String index() {

        return "system/mobile/mobile";
    }


    @RequestMapping("mobile/list")
    @ResponseBody
    public Map<String, Object> roleList(QueryRequest request, MobilePageIn mobile) {
        mobile.setCurrentPage(request.getPageNum());
        mobile.setPageSize(request.getPageSize());
       // PageHelper.startPage(request.getPageNum(), request.getPageSize());
       // List<Role> list = this.pMobileService.(role);
        PageInfo<PMobile> mobilePageInfo = pMobileService.getMobileListbByItems(mobile);
        //PageInfo<Role> pageInfo = new PageInfo<Role>(list);
        return getDataTable(mobilePageInfo);
    }


}
