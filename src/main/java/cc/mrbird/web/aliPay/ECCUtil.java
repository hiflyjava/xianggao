package cc.mrbird.web.aliPay;

import com.alipay.api.internal.util.codec.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ECCUtil {

    /** 算法 */
    public static String        algorithm  = "SHA256withECDSA";

    /** 提供者  */
    public static String        provider   = "BC";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }



    /**
     * 验签
     * @param publicKey 公钥
     * @param context 加签内容
     * @param sign 签名
     * @return 验签是否通过
     * @throws Exception 异常
     */
    public static boolean verify(PublicKey publicKey, String context, String sign) throws Exception {

        /** ECDSA签名 */
        Signature ecdsaSign = Signature.getInstance(algorithm, provider);

        ecdsaSign.initVerify(publicKey);

        ecdsaSign.update(context.getBytes());

        return ecdsaSign.verify(Base64.decodeBase64(sign.getBytes()));
    }

    /**
     * 验签
     * @param publicKey 公钥字符串
     * @param params 参数
     * @param sign 签名
     * @return 是否验签通过
     */
    public static boolean verify(String publicKey, Map<String, String> params, String sign) {

        try {
            PublicKey pubKey = initPublicKey(publicKey);

            String context = getSignatureContent(params);

            return verify(pubKey, context, sign);

        } catch (Exception e) {
//            LoggerUtil.error(e, logger, "验签异常,[publicKey={0};params={1};sign={2}]", publicKey,
//                    params, sign);
            return false;
        }
    }

    /**
     * 获得需要签名的数据，按照参数名字母升序的顺序将所有参数用&连接起来。
     *
     * @param params 待签名参数集
     * @return 排好序的待签名字符串
     */
    public static String getSignatureContent(Map<String, String> params) {
        if (params == null) {
            return null;
        }
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }

        return content.toString();
    }

    /**
     * 初始化公钥
     * @param pubKey 公钥字符串
     * @return 公钥
     * @throws Exception 异常
     */
    public static PublicKey initPublicKey(String pubKey) throws Exception {
        PublicKey publicKey = null;
        X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(pubKey
                .getBytes()));

        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA");
        // 取公钥匙对象
        publicKey = keyFactory.generatePublic(bobPubKeySpec);

        return publicKey;
    }


}
