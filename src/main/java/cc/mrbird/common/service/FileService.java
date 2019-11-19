package cc.mrbird.common.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Auther: admin
 * @Date: 2019/8/7 10:05
 * @Description:
 */
public interface FileService {


    /**
     * 多文件上传
     * @param file
     * @return
     */
    Map<String, List<String>> uploadImgs(MultipartFile[] file);

}
