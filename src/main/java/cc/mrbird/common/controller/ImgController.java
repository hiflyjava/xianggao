package cc.mrbird.common.controller;

import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.service.FileService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: admin
 * @Date: 2019/6/3 19:06
 * @Description:
 */




@RestController
@RequestMapping("/sys/upImg")
public class ImgController {

    private  static  final Logger logger= LoggerFactory.getLogger(ImgController.class);
//    @Value("${zf.img}")
//    private String zipUrl;


    @Autowired
    private FileService fileService;


    /**
     * 上传图片文件七牛云
     * @param files
     * @return
     */
    @RequestMapping(value="/imgs", method = RequestMethod.POST)
    public RespBean uploadImg(@RequestParam("file") MultipartFile[] files) {

        // 验证非空
        if (StringUtils.isBlank(files[0].getOriginalFilename())) {
           return  RespBean.error("上传为 null");
        } else {
            Map<String,List<String>> map = new HashMap<>();

            map = fileService.uploadImgs(files);

            List<String> resultList = map.get("result");
            logger.info("图片上传返回结果:"+resultList);

            if ("error".equals(resultList.get(0))) {
               return  RespBean.error("上传失败");
            } else {
                return  RespBean.ok("result",map);
            }
        }

    }



}
