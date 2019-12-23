package cc.mrbird.web.aliPay;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.StringUtils;
import cc.mrbird.web.domain.XgOrderMaster;
import cc.mrbird.web.domain.XgPayOrder;
import cc.mrbird.web.service.XgOrderMasterService;
import cc.mrbird.web.service.XgPayOrderService;
import cc.mrbird.web.utils.XgCodeUtil;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayEbppInvoiceTitleListGetResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppQueryResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayUserInfoAuthResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付宝支付
 *
 */
@RestController
@RequestMapping("/alipay")
public class AliPayController {
	
	@Autowired
	private AlipayConfig alipayConfig;
	@Autowired
	private XgPayOrderService orderService;

	@Autowired
	private XgOrderMasterService orderMasterService;
//	@Autowired
////	private MerchantService merchantService;
	@Value("${env.status}")  
	private String envStatus;
	//生产环境订单金额为真实金额
	private static final String ENV_STATUS= "prod";
	
	Logger logger = Logger.getLogger(AliPayController.class);

	/**
	 * 支付宝支付
	 * 
	 * @param @param
	 *                orderNo
	 * @param @param
	 *                request
	 * @param @param
	 *                response
	 * @param @throws
	 *                IOException
	 * @param @throws
	 *                AlipayApiException
	 * @return void
	 */
	@RequestMapping("alipayApi")
	private RespBean alipayApi(String orderNo, HttpServletRequest request, HttpServletResponse response)
			throws IOException, AlipayApiException {
		// 获得初始化的AlipayClient
		//RespBean respBean = RespBean.build();
		AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.appId,
				alipayConfig.merchantPrivateKey, "json", alipayConfig.charset,alipayConfig.alipayPublicKey, alipayConfig.signType);
		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(alipayConfig.returnUrl);
		alipayRequest.setNotifyUrl(alipayConfig.notifyUrl);
		// 商户订单号，商户网站订单系统中唯一订单号，必填
		// String out_trade_no = new
		// String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String out_trade_no = orderNo;
		// 根据传的订单id去数据库查询订单


		XgPayOrder order = orderService.getPayOrderByOrderNo(orderNo);
		if(order!=null) {
			if(XgCodeUtil.PAY_ORDER_PAY_STATUS_YES.equals( order.getStatus())) {
				return RespBean.error("该订单已经支付过了") ;
		}
		//判断订单的支付状态是否为待付款,如果为其他状态则直接返回
		}else {
			return RespBean.error("请提供正确的订单号");
		}
		// 付款金额，必填
		String total_amount = "";
		if(ENV_STATUS.equals(alipayConfig.envStatus)) {
			total_amount = order.getBalance().toString();
		}else {
			//非生产环境订单的金额为0.01元
			total_amount = "0.01";
		}
		//String total_amount = order.getOrderAmount().add(order.getPostFee()).toString();
		// 订单名称，必填
		String subject = "爱购硬订单";
		// 商品描述，可空
		String body = "爱购硬订单";
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
				+ total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body
				+ "\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		// alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
		// + "\"total_amount\":\""+ total_amount +"\","
		// + "\"subject\":\""+ subject +"\","
		// + "\"body\":\""+ body +"\","
		// + "\"timeout_express\":\"10m\","
		// + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		// 请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

		// 请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();

		// 输出
		return  RespBean.ok(result);
	}

	/**
	 * 异步通知
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws AlipayApiException
	 * @date: 2017年5月17日 下午3:25:38
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("notifyUrl")
	public void notifyUrl(HttpServletRequest request, HttpServletResponse response)
			throws IOException, AlipayApiException {
		logger.info("支付宝异步通知");
		// 获取支付宝POST过来反馈信息


		if(StringUtils.isEmptyOrNull(request.getParameter("total_amount"))){//登录授权

			String appId = request.getParameter("app_id");
			String source = request.getParameter("source");
			String appAuthCode = request.getParameter("app_auth_code");
			String total_amount = request.getParameter("total_amount");
			System.out.println(total_amount);
			// 使用app_auth_code换取app_auth_token
			AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, // 支付宝网关（固定）
					"2016101600701324", // APPID 即创建应用后生成
					alipayConfig.merchantPrivateKey,
					"json",
					"utf-8",
					alipayConfig.alipayPublicKey,
					alipayConfig.signType); // 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
			AlipayOpenAuthTokenAppRequest aoataRequest = new AlipayOpenAuthTokenAppRequest();
			// 设置请求参数 使用app_auth_code换取app_auth_token
			aoataRequest.setBizContent("{\"grant_type\":\"authorization_code\",\"code\":\"" + appAuthCode + "\"}");
			// 发送请求得到响应
			AlipayOpenAuthTokenAppResponse aoataResponse = alipayClient.execute(aoataRequest);
			if (!aoataResponse.isSuccess()) {
				throw new RuntimeException("获取app_auth_token失败！" + aoataResponse.getSubMsg());
			}
			// 根据appAuthToken换取用户信息
			AlipayOpenAuthTokenAppQueryRequest aoataqRequest = new AlipayOpenAuthTokenAppQueryRequest();
			aoataqRequest.setBizContent("{\"app_auth_token\":\"" + aoataResponse.getAppAuthToken() + "\"}");
			AlipayOpenAuthTokenAppQueryResponse appQueryResponse = alipayClient.execute(aoataqRequest);
			if (!appQueryResponse.isSuccess()) {
				throw new RuntimeException("获取用户授权信息失败！" + appQueryResponse.getSubMsg());
			}
			// 用户授权成功 获取授权信息
			String userId = appQueryResponse.getUserId();
			String appID = appQueryResponse.getAuthAppId();
			String body = appQueryResponse.getBody();
			System.out.println(userId);
			System.out.println(appID);
			System.out.println(body);
			AlipayEbppInvoiceTitleListGetRequest request2 = new AlipayEbppInvoiceTitleListGetRequest();
			request2.setBizContent("{"+"\"user_id\":\""+userId+"\"" + "}");
			AlipayEbppInvoiceTitleListGetResponse responses = alipayClient.execute(request2, aoataResponse.getAppAuthToken());
			if (responses.isSuccess()) {
				System.out.println("调用成功");
			} else {
				System.out.println("调用失败");

			}


		}else {//支付回调

			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			logger.info("支付宝异步通知，回调参数："+Arrays.asList(params));

			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
			String app_id = new String(request.getParameter("app_id").getBytes("ISO-8859-1"), "UTF-8");
			BigDecimal total_amounts=new BigDecimal(total_amount);
			logger.info("异步通知，订单号：" + out_trade_no + " 支付宝订单号：" + trade_no);
			// 调用SDK验证签名
			logger.info("异步通知签名校验参数,公钥："+alipayConfig.alipayPublicKey+" 编码方式："+alipayConfig.charset+" 签名方式："+alipayConfig.signType);
			boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.alipayPublicKey,
					alipayConfig.charset, alipayConfig.signType);

			/*
			 * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
			 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）， 3、校验通知中的seller_id（或者seller_email)
			 * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
			 * 4、验证app_id是否为该商户本身。
			 */
			PrintWriter out = response.getWriter();
			// XgPayOrder order=new XgPayOrder();
			// order.setId(out_trade_no);
			XgPayOrder order = orderService.getPayOrderByOrderNo(out_trade_no);
			if(order==null) {
				logger.info("异步通知中订单号不存在");
				out.println("fail");
				return ;
			}
			BigDecimal ordermeney=order.getBalance();
			//生产环境才进行金额验证
			if(ENV_STATUS.equals(alipayConfig.envStatus)) {
				if(ordermeney.compareTo(total_amounts)!=0) {
					logger.info(">>>>>>>>>>>订单金额不正确>>>>>>>>>>>>>>>");
					logger.info(">>>>>>>>>>>回调金额："+total_amounts);
					logger.info(">>>>>>>>>>>实际金额："+ordermeney);
					out.println("fail");
					return ;
				}
			}
			if(!app_id.equals(alipayConfig.appId)) {
				logger.info("回调的商户id不正确");
				out.println("fail");
				return;
			}


			if (signVerified) {
				// 验证成功
				logger.info("异步通知，签名校验成功，订单号：" + out_trade_no + " 支付宝订单号："+ trade_no);
				// 交易状态
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
				if (trade_status.equals("TRADE_FINISHED")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					// 注意：
					// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 付款完成后，支付宝系统发送该交易状态通知
					// 修改订单状态为待发货
					order.setStatus(XgCodeUtil.PAY_ORDER_PAY_STATUS_YES);//支付成功
					order.setPayTime(new Date());
					order.setPayType(XgCodeUtil.PAY_TYPE_ZFB);//支付宝
					orderService.updatePayOrder(order);
					XgOrderMaster orderMaster =new XgOrderMaster();
					orderMaster.setOrderNum(out_trade_no);
					orderMaster.setPayStatus(XgCodeUtil.PAY_ORDER_PAY_STATUS_YES);
					orderMasterService.updateOrderMatder(orderMaster);
					//添加支付流水
//				PayFlow payFlow=new PayFlow();
//				payFlow.setCreateTime(order.getPayTime());
//				payFlow.setUserId(order.getUserId());
//				payFlow.setPayType(order.getPayType());
//				payFlow.setPayAmount(Double.valueOf(total_amount));
//				Integer row=orderService.addPayFlow(payFlow);
				}

				out.println("success");

			} else {
				logger.info("异步通知，签名校验失败");
				// 验证失败
				out.println("fail");
				// 调试用，写文本函数记录程序运行情况是否正常
				// String sWord = AlipaySignature.getSignCheckContentV1(params);
				// AlipayConfig.logResult(sWord);
			}

		}



		// ——请在这里编写您的程序（以上代码仅作参考）——
	}


	/**
	 * 退款
	 * 
	 * @param @param
	 *                request
	 * @param @param
	 *                response
	 * @param @throws
	 *                AlipayApiException
	 * @param @throws
	 *                IOException
	 * @return void
	 */
	@RequestMapping("refund")
	private void refund(HttpServletRequest request, HttpServletResponse response)
			throws AlipayApiException, IOException {
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.appId,
				alipayConfig.merchantPrivateKey, "json", alipayConfig.charset,alipayConfig.alipayPublicKey, alipayConfig.signType);
		// 设置请求参数
		AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

		// 商户订单号，商户网站订单系统中唯一订单号
		String out_trade_no = new String(request.getParameter("WIDTRout_trade_no").getBytes("ISO-8859-1"),
				"UTF-8");
		// 支付宝交易号
		String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 请二选一设置
		// 需要退款的金额，该金额不能大于订单金额，必填
		String refund_amount = new String(request.getParameter("WIDTRrefund_amount").getBytes("ISO-8859-1"),
				"UTF-8");
		// 退款的原因说明
		String refund_reason = new String(request.getParameter("WIDTRrefund_reason").getBytes("ISO-8859-1"),
				"UTF-8");
		// 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
		String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"),
				"UTF-8");

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no
				+ "\"," + "\"refund_amount\":\"" + refund_amount + "\"," + "\"refund_reason\":\""
				+ refund_reason + "\"," + "\"out_request_no\":\"" + out_request_no + "\"}");

		// 请求
		String result = alipayClient.execute(alipayRequest).getBody();

		// 输出
		PrintWriter out = response.getWriter();
		out.println(result);
	}

	/**
	 * 交易查询
	 * 
	 * @param @param
	 *                request
	 * @param @param
	 *                response
	 * @param @throws
	 *                AlipayApiException
	 * @param @throws
	 *                IOException
	 * @return void
	 */
	@RequestMapping("query")
	private void query(HttpServletRequest request, HttpServletResponse response)
			throws AlipayApiException, IOException {
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.appId,
				alipayConfig.merchantPrivateKey, "json", alipayConfig.charset,alipayConfig.alipayPublicKey, alipayConfig.signType);
		// 设置请求参数
		AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

		// 商户订单号，商户网站订单系统中唯一订单号
		String out_trade_no = new String(request.getParameter("WIDTQout_trade_no").getBytes("ISO-8859-1"),
				"UTF-8");
		// 支付宝交易号
		String trade_no = new String(request.getParameter("WIDTQtrade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 请二选一设置

		alipayRequest.setBizContent(
				"{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\"}");

		// 请求
		String result = alipayClient.execute(alipayRequest).getBody();

		// 输出
		PrintWriter out = response.getWriter();
		out.println(result);
	}

	/**
	 * 退款查询
	 * 
	 * @param @param
	 *                request
	 * @param @param
	 *                response
	 * @param @throws
	 *                AlipayApiException
	 * @param @throws
	 *                IOException
	 * @return void
	 */
	@RequestMapping("refundQuery")
	private void refundQuery(HttpServletRequest request, HttpServletResponse response)
			throws AlipayApiException, IOException {
		// 获得初始化的AlipayClient
				AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.appId,
						alipayConfig.merchantPrivateKey, "json", alipayConfig.charset,alipayConfig.alipayPublicKey, alipayConfig.signType);
		// 设置请求参数
		AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

		// 商户订单号，商户网站订单系统中唯一订单号
		String out_trade_no = new String(request.getParameter("WIDRQout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		// 支付宝交易号
		String trade_no = new String(request.getParameter("WIDRQtrade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 请二选一设置
		// 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号，必填
		String out_request_no = new String(request.getParameter("WIDRQout_request_no").getBytes("ISO-8859-1"),"UTF-8");

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no
				+ "\"," + "\"out_request_no\":\"" + out_request_no + "\"}");

		// 请求
		String result = alipayClient.execute(alipayRequest).getBody();

		// 输出
		PrintWriter out = response.getWriter();
		out.println(result);
	}


	/**
	 * 转账功能
	 * @return
	 */

	@RequestMapping("zhuanzhuang")
	public  RespBean zhuanzhuang(){
		Map<String, String> stringStringMap = ZhuanZhangUtil.getInstance().alipay2User(null, null, null, null);
		System.out.println(stringStringMap);

		return  RespBean.ok("success",stringStringMap);

	}


	/**
	 * 授权获取token
	 * @return
	 */

	@RequestMapping("getToken")
	public  RespBean getToken() throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(
				alipayConfig.gatewayUrl,
				"2016101600701324",
				alipayConfig.merchantPrivateKey,
				"json",
				"utf-8",
				alipayConfig.alipayPublicKey,
				alipayConfig.signType);
		AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
		request.setBizContent("{" +
				"\"grant_type\":\"authorization_code\"," +
				"\"code\":\"1cc19911172e4f8aaa509c8fb5d12F56\"," +
				"\"refresh_token\":\"201509BBdcba1e3347de4e75ba3fed2c9abebE36\"" +
				"  }");
		AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
		if(response.isSuccess()){
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}

		return  RespBean.ok("success",response.getAppAuthToken());
	}

	/**
	 * 授权登录获取用户信息 已经ok
	 * @param request
	 * @return
	 * @throws AlipayApiException
	 */

	@RequestMapping("getLogin")
	public  RespBean getLogin( HttpServletRequest  request) throws AlipayApiException {


		String appId = request.getParameter("app_id");
		String source = request.getParameter("source");
		String appAuthCode = request.getParameter("app_auth_code");
		String total_amount = request.getParameter("total_amount");
		System.out.println(total_amount);
		// 使用app_auth_code换取app_auth_token
		AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.gatewayUrl, // 支付宝网关（固定）
				"2016101600701324", // APPID 即创建应用后生成
				alipayConfig.merchantPrivateKey,
				"json",
				"utf-8",
				alipayConfig.alipayPublicKey,
				alipayConfig.signType); // 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
		AlipayOpenAuthTokenAppRequest aoataRequest = new AlipayOpenAuthTokenAppRequest();
		// 设置请求参数 使用app_auth_code换取app_auth_token
		aoataRequest.setBizContent("{\"grant_type\":\"authorization_code\",\"code\":\"" + appAuthCode + "\"}");
		// 发送请求得到响应
		AlipayOpenAuthTokenAppResponse aoataResponse = alipayClient.execute(aoataRequest);
		if (!aoataResponse.isSuccess()) {
			throw new RuntimeException("获取app_auth_token失败！" + aoataResponse.getSubMsg());
		}
		// 根据appAuthToken换取用户信息
		AlipayOpenAuthTokenAppQueryRequest aoataqRequest = new AlipayOpenAuthTokenAppQueryRequest();
		aoataqRequest.setBizContent("{\"app_auth_token\":\"" + aoataResponse.getAppAuthToken() + "\"}");
		AlipayOpenAuthTokenAppQueryResponse appQueryResponse = alipayClient.execute(aoataqRequest);
		if (!appQueryResponse.isSuccess()) {
			throw new RuntimeException("获取用户授权信息失败！" + appQueryResponse.getSubMsg());
		}
		// 用户授权成功 获取授权信息
		String userId = appQueryResponse.getUserId();
		String appID = appQueryResponse.getAuthAppId();
		String body = appQueryResponse.getBody();
		System.out.println(userId);
		System.out.println(appID);
		System.out.println(body);
		AlipayEbppInvoiceTitleListGetRequest request2 = new AlipayEbppInvoiceTitleListGetRequest();
		request2.setBizContent("{"+"\"user_id\":\""+userId+"\"" + "}");
		AlipayEbppInvoiceTitleListGetResponse response = alipayClient.execute(request2, aoataResponse.getAppAuthToken());
		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");

		}

		return null;


	}

	@RequestMapping("getTaitou")
	public  RespBean getTaitou(String accessToken,String userId) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(
				alipayConfig.gatewayUrl,
				"2016101600701324",
				alipayConfig.merchantPrivateKey,
				"json",
				"utf-8",
				alipayConfig.alipayPublicKey,
				alipayConfig.signType);

		AlipayEbppInvoiceTitleListGetRequest request = new AlipayEbppInvoiceTitleListGetRequest();
		request.setBizContent("{"+"\"user_id\":\""+userId+"\"" + "}");
		AlipayEbppInvoiceTitleListGetResponse response = alipayClient.execute(request, accessToken);
		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");

		}

		return RespBean.ok("success", response);
	}


}
