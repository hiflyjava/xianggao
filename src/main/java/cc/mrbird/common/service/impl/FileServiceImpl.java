package cc.mrbird.common.service.impl;

import cc.mrbird.common.service.FileService;
import cc.mrbird.common.util.QiniuUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: admin
 * @Date: 2019/8/7 10:05
 * @Description:
 */

@Service
public class FileServiceImpl implements FileService {

    private static  final Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    @Value("${qiniuyun.ip}")
     private String ips;
    @Value("${qiniuyun.ACCESS_KEY}")
    private String ACCESS_KEY;
    @Value("${qiniuyun.SECRET_KEY}")
    private String SECRET_KEY;
    @Value("${qiniuyun.BUCKET_NAME}")
    private String BUCKET_NAME;

    @Override
    public Map<String, List<String>> uploadImgs(MultipartFile[] file) {
        Map<String, List<String>> resultMap = new HashMap<>();
        List<String> list = new LinkedList<>();
        String result = null;

        for (int i = 0; i < file.length; i++) {
            String fileName = file[i].getOriginalFilename();

            // 创建一个临时目录文件
            String tempFiles = "temp/"+fileName;
            File dest = new File(tempFiles);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            BufferedOutputStream out = null;
            QiniuUtil qn = new QiniuUtil();

            try {
                out = new BufferedOutputStream(new FileOutputStream(dest));
                out.write(file[i].getBytes());
                result = qn.uoloapQiniu(dest,fileName,ips,ACCESS_KEY,SECRET_KEY,BUCKET_NAME);

                if (StringUtils.isNotBlank(result)) {
                    list.add(result);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e1) {
                e1.getMessage();
            }  finally{
                try {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (dest.getParentFile().exists()) {
                    dest.delete();
                }
            }
        }
        logger.info("imagesList == " + list);
        if (list.isEmpty()) {
            list.add("error");
        }
        resultMap.put("result",list);
        return resultMap;

    }
}
