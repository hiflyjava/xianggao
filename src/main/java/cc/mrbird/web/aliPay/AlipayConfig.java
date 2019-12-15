package cc.mrbird.web.aliPay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 支付宝配置参数
 */
@Component
public class AlipayConfig {
	
	@Value("${alipay.app_id}")
	public String appId;//APP ID
	@Value("${alipay.merchant_private_key}")
	public String merchantPrivateKey;//商户密钥
	@Value("${alipay.alipay_public_key}")
	public String alipayPublicKey;//支付宝公钥
	@Value("${alipay.gatewayUrl}")
	public String gatewayUrl;//支付宝网关
	@Value("${alipay.charset}")
	public String charset;//编码方式
	@Value("${alipay.sign_type}")
	public String signType;//签名方式
	@Value("${alipay.notify_url}")
	public String notifyUrl;//异步回调URL
	@Value("${alipay.return_url}")
	public String returnUrl;//同步回调URL
	
	@Value("${env.status}")
	public String envStatus;
	
}

