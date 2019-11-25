package cc.mrbird.common.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Auther: admin
 * @Date: 2019/8/7 09:59
 * @Description:
 */
public class QiniuUtil {

    private static  final Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    //设置好账号的ACCESS_KEY和SECRET_KEY
   // final String ACCESS_KEY = "UPSO2MA5ZT9YU2rGAp1G_JfHeRJhPuTFDtrFfF7N";
   // final String SECRET_KEY = "YqBJ-apvJQjVjnYX7f36d6QudybFUN4yjRAPDSWg";
    //要上传的空间
   // final String BUCKET_NAME = "myzfimgs";

    /**
     * 七牛云上传图片
     * @param localFilePath
     * @return
     */
    public String uoloapQiniu (File localFilePath, String fileName,String ips,String ACCESS_KEY,String SECRET_KEY,String BUCKET_NAME){
        //构造一个带指定Zone对象的配置类
        Configuration cfg;
        cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
      //  String accessKey = ACCESS_KEY;
   //     String secretKey = SECRET_KEY;
     //   String bucket = BUCKET_NAME;
        //如果是Windows情况下，格式是 D:\23912475_130759767000_2.jpg
//        String localFilePath = "D:\\23912475_130759767000_2.jpg";
        //        String localFilePath = "/home/qiniu/test.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "/images/"+fileName+"%3FtId="+System.currentTimeMillis();
        com.qiniu.util.Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET_NAME);

        String result = null;

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            logger.info("{七牛图片上传key: "+ putRet.key+",七牛图片上传hash: "+ putRet.hash+"}");

            //result = "外链域名(如:image.domain.com)"+putRet.key;
            result =ips+putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            result = null;
        }
        return result;
    }

}
