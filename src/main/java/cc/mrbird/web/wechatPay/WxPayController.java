package cc.mrbird.web.wechatPay;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.mrbird.common.domain.RespBean;
import cc.mrbird.common.util.MD5Utils;
import cc.mrbird.web.domain.XgPayOrder;
import cc.mrbird.web.service.XgPayOrderService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;

import com.google.zxing.common.BitMatrix;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 微信支付
 * 
 * @Description: TODO
 * @author 黄德安
 * @date 2018年1月12日 下午5:56:49
 */
@Controller
@RequestMapping("/wxPay")
public class WxPayController {

//	Logger logger = Logger.getLogger(WxPayController.class);
//	@Autowired
//	private WxPayConfig wxPayConfig;
//	@Autowired
//	private XgPayOrderService orderService;
////	@Autowired
////	private MerchantService merchantService;
//	//生产环境订单金额为真实金额
//	private static final String ENV_STATUS= "prod";
//
//	/**
//	 * 创建二维码
//	 *
//	 * @param orderNo
//	 * @param request
//	 * @param response
//	 * @throws NoSuchAlgorithmException
//	 * @return void
//	 * @throws IOException
//	 */
//	//@SuppressWarnings({ "unchecked", "rawtypes" })
//	//@RequestMapping("createQRCode")
//	public void createQRCode(String orderNo, HttpServletRequest request, HttpServletResponse response)
//			throws NoSuchAlgorithmException, IOException {
//		try {
//			// 根据订单号获取订单的信息
//			XgPayOrder order = new XgPayOrder();
//			//order.setId(orderNo);
//			order = orderService.getPayOrderByOrderNo(orderNo);
//			if (order.getStatus().compareTo(BigDecimal.ZERO) != 0) {
//				response.getWriter().write("该订单已经支付过了！");
//				return;
//			}
//
////			XgPayOrder orderMaster = new XgPayOrder();
////			orderMaster.setOrderNo(orderNo);
////			orderMaster.setBalance(order.getBalance());
////			//orderMaster.setOrderPayAmount(order.getOrderAmount().add(order.getPostFee()));
////			orderMaster.setCreateTime(order.getCreateTime());
////		//	orderMaster.setOrderDesc("爱购硬订单");
////		//	orderMaster.setCurrency("CNY");
////			orderMaster.setOrderStatus(order.getStatus() + "");
////			// 生成订单
//			String orderInfo = createOrderInfo(orderNo, order, request);
//
//			// 调统一下单API
//			String code_url = httpOrder(orderInfo);
//			// 将返回预支付交易链接（code_url）生成二维码图片
//
//			// 这里使用的是zxing
//			int width = 200;
//			int height = 200;
//			String format = "png";
//			Hashtable hints = new Hashtable();
//			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//			BitMatrix bitMatrix = new MultiFormatWriter().encode(code_url, BarcodeFormat.QR_CODE, width,height, hints);
//			OutputStream out = null;
//			out = response.getOutputStream();
//			MatrixToImageWriter.writeToStream(bitMatrix, format, out);
//			out.flush();
//			out.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 生成订单
//	 *
//	 * @param orderNo
//	 * @param orderMaster
//	 * @param request
//	 * @return
//	 * @throws NoSuchAlgorithmException
//	 * @return String
//	 */
//	@SuppressWarnings("unused")
//	private String createOrderInfo(String orderNo, XgPayOrder orderMaster, HttpServletRequest request)
//			throws NoSuchAlgorithmException {
//
//		// String ip = IPUtil.getClientIPAddress(request);
//		String clintIp = "";
//		// 生成订单对象
//		UnifiedOrderRequest unifiedOrderRequest = new UnifiedOrderRequest();
//		unifiedOrderRequest.setAppid(wxPayConfig.appId);// 公众账号ID
//		unifiedOrderRequest.setMch_id(wxPayConfig.mchId);// 商户号
//		unifiedOrderRequest.setNonce_str(UUIDUtils.uuid());// 随机字符串
//		unifiedOrderRequest.setBody(orderMaster.getOrderDesc());// 商品描述
//		unifiedOrderRequest.setOut_trade_no(orderNo);// 商户订单号
//		String total_fee;// 金额需要扩大100倍:1代表支付时是0.01
//		if(ENV_STATUS.equals(wxPayConfig.envStatus)) {
//			total_fee = (orderMaster.getOrderPayAmount()).multiply(new BigDecimal(100)).toString().replace(".00", "");
//		}else {
//			//非生产环境订单金额为0.01元
//			total_fee = "1";
//		}
//		unifiedOrderRequest.setTotal_fee(total_fee);
//		unifiedOrderRequest.setSpbill_create_ip(clintIp);// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
//		unifiedOrderRequest.setNotify_url(wxPayConfig.notifyUrl);// 通知地址
//		//logger.info("微信异步回调URL："+wxPayConfig.notifyUrl);
//		unifiedOrderRequest.setTrade_type("NATIVE");// JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
//		unifiedOrderRequest.setSign(createSign(unifiedOrderRequest));// 签名
//		// 将订单对象转为xml格式
//		XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
//		xStream.alias("xml", UnifiedOrderRequest.class);// 根元素名需要是xml
//		return xStream.toXML(unifiedOrderRequest);
//	}
//
//	/**
//	 * 调统一下单API
//	 *
//	 * @param orderInfo
//	 * @return
//	 */
//	private String httpOrder(String orderInfo) {
//		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//		try {
//			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
//			// 加入数据
//			conn.setRequestMethod("POST");
//			conn.setDoOutput(true);
//
//			BufferedOutputStream buffOutStr = new BufferedOutputStream(conn.getOutputStream());
//			buffOutStr.write(orderInfo.getBytes());
//			buffOutStr.flush();
//			buffOutStr.close();
//
//			// 获取输入流
//			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//			String line = null;
//			StringBuffer sb = new StringBuffer();
//			while ((line = reader.readLine()) != null) {
//				sb.append(line);
//			}
//
//			XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
//			// 将请求返回的内容通过xStream转换为UnifiedOrderRespose对象
//			xStream.alias("xml", UnifiedOrderRespose.class);
//			UnifiedOrderRespose unifiedOrderRespose = (UnifiedOrderRespose) xStream.fromXML(sb.toString());
//
//			// 根据微信文档return_code 和result_code都为SUCCESS的时候才会返回code_url
//			if (null != unifiedOrderRespose && "SUCCESS".equals(unifiedOrderRespose.getReturn_code())
//					&& "SUCCESS".equals(unifiedOrderRespose.getResult_code())) {
//				return unifiedOrderRespose.getCode_url();
//			} else {
//				return null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 生成签名
//	 *
//	 * @param appid_value
//	 * @param mch_id_value
//	 * @param productId
//	 * @param nonce_str_value
//	 * @param trade_type
//	 * @param notify_url
//	 * @param spbill_create_ip
//	 * @param total_fee
//	 * @param out_trade_no
//	 * @return
//	 * @throws NoSuchAlgorithmException
//	 */
//	@SuppressWarnings("rawtypes")
//	private String createSign(UnifiedOrderRequest unifiedOrderRequest) throws NoSuchAlgorithmException {
//		// 根据规则创建可排序的map集合
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("appid", unifiedOrderRequest.getAppid());
//		packageParams.put("body", unifiedOrderRequest.getBody());
//		packageParams.put("mch_id", unifiedOrderRequest.getMch_id());
//		packageParams.put("nonce_str", unifiedOrderRequest.getNonce_str());
//		packageParams.put("notify_url", unifiedOrderRequest.getNotify_url());
//		packageParams.put("out_trade_no", unifiedOrderRequest.getOut_trade_no());
//		packageParams.put("spbill_create_ip", unifiedOrderRequest.getSpbill_create_ip());
//		packageParams.put("trade_type", unifiedOrderRequest.getTrade_type());
//		packageParams.put("total_fee", unifiedOrderRequest.getTotal_fee());
//
//		StringBuffer sb = new StringBuffer();
//		Set es = packageParams.entrySet();// 字典序
//		Iterator it = es.iterator();
//		while (it.hasNext()) {
//			Map.Entry entry = (Map.Entry) it.next();
//			String k = (String) entry.getKey();
//			String v = (String) entry.getValue();
//			// 为空不参与签名、参数名区分大小写
//			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
//				sb.append(k + "=" + v + "&");
//			}
//		}
//		// 第二步拼接key，key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
//		sb.append("key=" + wxPayConfig.secretKeyId);
//		String sign = MD5Utils.encrypt(sb.toString()).toUpperCase();// MD5加密
//		return sign;
//	}
//
//	@SuppressWarnings("unchecked")
//	@RequestMapping("notifyUrl")
//	public synchronized void notify(HttpServletRequest request, HttpServletResponse response, Model model)
//			throws IOException, DocumentException, JDOMException {
//		logger.info("进入微信支付回调方法");
//		// 读取参数
//		InputStream inputStream;
//		StringBuffer sb = new StringBuffer();
//		inputStream = request.getInputStream();
//		String s;
//		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//		while ((s = in.readLine()) != null) {
//			sb.append(s);
//		}
//
//		logger.info("微信回调输入流数据：" + sb);
//		in.close();
//		inputStream.close();
//		// 解析xml成map
//		Map<String, String> m = new HashMap<String, String>();
//		m = XMLUtil.doXMLParse(sb.toString());
//		// 过滤空 设置 TreeMap
//		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
//		Iterator it = m.keySet().iterator();
//		while (it.hasNext()) {
//			String parameter = (String) it.next();
//			String parameterValue = m.get(parameter);
//
//			String v = "";
//			if (null != parameterValue) {
//				v = parameterValue.trim();
//			}
//			packageParams.put(parameter, v);
//		}
//		// 账号信息
//		String key = wxPayConfig.secretKeyId; // key
//		// 判断签名是否正确
//		if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
//			// ------------------------------
//			// 处理业务开始
//			// ------------------------------
//			String resXml = "";
//			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
//				// 这里是支付成功
//				////////// 执行自己的业务逻辑////////////////
//				String mch_id = (String) packageParams.get("mch_id");
//				//商户验证
//				if (!mch_id.equals(wxPayConfig.mchId)) {
//					logger.info(">>>>>回调的商户Id不正确！！！>>>>>>>");
//					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
//							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
//					out.write(resXml.getBytes());
//					out.flush();
//					out.close();
//					return;
//				}
//				String appid = (String) packageParams.get("appid");
//				//公众号验证
//				if (!appid.equals(wxPayConfig.appId)) {
//					logger.info("回调的公众号id不正确！！！");
//					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
//							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
//					out.write(resXml.getBytes());
//					out.flush();
//					out.close();
//					return;
//				}
//				String is_subscribe = (String) packageParams.get("is_subscribe");
//				String out_trade_no = (String) packageParams.get("out_trade_no");
//				String total_fee = (String) packageParams.get("total_fee");
//				BigDecimal total = new BigDecimal(total_fee);
//				logger.info("mch_id:" + mch_id);
//				logger.info("appid:" + appid);
//				logger.info("is_subscribe:" + is_subscribe);
//				logger.info("out_trade_no:" + out_trade_no);
//				logger.info("total_fee:" + total_fee);
//
//				// ===============================================================================
//				String orderNo = out_trade_no;
//				// 微信订单号
//				String wx_order_no = (String) packageParams.get("transaction_id");
//
//				// TODO 验证更新订单表
//				Order order = new Order();
//				order.setId(orderNo);
//				order = orderService.findorderdesc(order);
//				// 验证订单号
//				if (order == null) {
//					logger.info("回调的订单号不存在！！！");
//					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
//							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
//					out.write(resXml.getBytes());
//					out.flush();
//					out.close();
//					return;
//				}
//				BigDecimal ordermoney=(order.getOrderAmount().add(order.getPostFee())).multiply(new BigDecimal(100));
//				//订单金额验证
//				if(ENV_STATUS.equals(wxPayConfig.envStatus)) {
//					if(total.compareTo(ordermoney)!=0) {
//					logger.info(">>>>>订单金额不正确！！！>>>>>>>");
//					logger.info(">>>>>回调金额："+total);
//					logger.info(">>>>>订单金额："+ordermoney);
//					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
//						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
//					out.write(resXml.getBytes());
//			                out.flush();
//			                out.close();
//					return;
//				    }
//				}
//				//所有验证通过则修改订单状态
//				order.setPayTime(new Date());
//				order.setPayType(1);
//				order.setOrderStatus(2);
//				orderService.modifyOrderStatus(order);
//				//添加支付流水
//				PayFlow payFlow=new PayFlow();
//				payFlow.setCreateTime(order.getPayTime());
//				payFlow.setUserId(order.getUserId());
//				payFlow.setPayType(order.getPayType());
//				payFlow.setPayAmount(Double.valueOf(total_fee));
//				Integer row=orderService.addPayFlow(payFlow);
//				logger.info("微信支付成功");
//				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
//				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
//						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
//			} else {
//				// logger.info("支付失败,错误信息：" + packageParams.get("err_code"));
//				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
//						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
//			}
//			// ------------------------------
//			// 处理业务完毕
//			// ------------------------------
//			out.write(resXml.getBytes());
//			out.flush();
//			out.close();
//		} else {
//			logger.info("通知签名验证失败");
//		}
//	}
//
//	/**
//	 *
//	 *  @Description: 保证金回调
//	 *  @param  @param request
//	 *  @param  @param response
//	 *  @param  @param model
//	 *  @param  @throws IOException
//	 *  @param  @throws DocumentException
//	 *  @param  @throws JDOMException
//	 *  @return void
//	 */
////	@SuppressWarnings("unchecked")
////	@RequestMapping("/depositnotify")
////	public synchronized void depositnotify(HttpServletRequest request, HttpServletResponse response, Model model)
////			throws IOException, DocumentException, JDOMException {
////		logger.info("定金支付，进入微信支付回调方法");
////		// 读取参数
////		InputStream inputStream;
////		StringBuffer sb = new StringBuffer();
////		inputStream = request.getInputStream();
////		String s;
////		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
////		while ((s = in.readLine()) != null) {
////			sb.append(s);
////		}
////
////		logger.info("定金支付，微信回调输入流数据：" + sb);
////		in.close();
////		inputStream.close();
////		// 解析xml成map
////		Map<String, String> m = new HashMap<String, String>();
////		m = XMLUtil.doXMLParse(sb.toString());
////		// 过滤空 设置 TreeMap
////		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
////		Iterator it = m.keySet().iterator();
////		while (it.hasNext()) {
////			String parameter = (String) it.next();
////			String parameterValue = m.get(parameter);
////
////			String v = "";
////			if (null != parameterValue) {
////				v = parameterValue.trim();
////			}
////			packageParams.put(parameter, v);
////		}
////		// 账号信息
////		String key = wxPayConfig.secretKeyId; // key
////		// 判断签名是否正确
////		if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
////			// ------------------------------
////			// 处理业务开始
////			// ------------------------------
////			String resXml = "";
////			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
////			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
////				// 这里是支付成功
////				////////// 执行自己的业务逻辑////////////////
////				String mch_id = (String) packageParams.get("mch_id");
////				//商户验证
////				if (!mch_id.equals(wxPayConfig.mchId)) {
////					logger.info("回调的商户Id不正确！！！");
////					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
////							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
////					out.write(resXml.getBytes());
////					out.flush();
////					out.close();
////					return;
////				}
////				String appid = (String) packageParams.get("appid");
////				//公众号验证
////				if (!appid.equals(wxPayConfig.appId)) {
////					logger.info("回调的公众号id不正确！！！");
////					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
////							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
////					out.write(resXml.getBytes());
////					out.flush();
////					out.close();
////					return;
////				}
////				String is_subscribe = (String) packageParams.get("is_subscribe");
////				String out_trade_no = (String) packageParams.get("out_trade_no");
////				String total_fee = (String) packageParams.get("total_fee");
////				logger.info("mch_id:" + mch_id);
////				logger.info("appid:" + appid);
////				logger.info("is_subscribe:" + is_subscribe);
////				logger.info("out_trade_no:" + out_trade_no);
////				logger.info("total_fee:" + total_fee);
////
////				// ===============================================================================
////				String orderNo = out_trade_no;
////				// 微信订单号
////				String wx_order_no = (String) packageParams.get("transaction_id");
////
////				// TODO 验证更新商家状态
////				Deposit deposit=new Deposit();
////				deposit.setId(orderNo);
////				deposit=merchantService.findDeposit(deposit);
////				if(deposit==null) {
////					logger.info("回调的商家不正确！！！");
////					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
////							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
////				        out.write(resXml.getBytes());
////				        out.flush();
////				        out.close();
////				         return;
////				}
////				Merchant merchant=merchantService.findMerchantById(deposit.getMerchantId());
////				// 验证订单号
////				if(merchant==null) {
////					logger.info("回调的商家不正确！！！");
////					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
////							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
////				        out.write(resXml.getBytes());
////				        out.flush();
////				        out.close();
////				         return;
////				}
////				//订单金额验证
////				if(ENV_STATUS.equals(wxPayConfig.envStatus)) {
////					if(!total_fee.equals(deposit.getMoney()*100+"")) {
////						logger.info(">>>>>订单金额不正确！！！>>>>>>>");
////						logger.info(">>>>>回调金额："+total_fee);
////						logger.info(">>>>>订单金额："+deposit.getMoney());
////						resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
////							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
////						out.write(resXml.getBytes());
////				                out.flush();
////				                out.close();
////						return;
////				      }
////				}
////				//所有验证通过则修改订单状态
////				Date date=new Date();
////				merchant.setStatus(3);
////				merchant.setDepositTime(date);
////				merchantService.modifyMerchant(merchant);
////				deposit.setStatus(1);
////				deposit.setPayTime(date);
////				merchantService.updateDeposit(deposit);
////				logger.info("保证金支付成功");
////				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
////				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
////						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
////			} else {
////				// logger.info("支付失败,错误信息：" + packageParams.get("err_code"));
////				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
////						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
////			}
////			// ------------------------------
////			// 处理业务完毕
////			// ------------------------------
////			out.write(resXml.getBytes());
////			out.flush();
////			out.close();
////		} else {
////			logger.info("通知签名验证失败");
////		}
////	}
//
//	/**
//	 * 跳转支付结果页面
//	 *
//	 * @param @param
//	 *                orderNo
//	 * @param @param
//	 *                isSuccess
//	 * @param @param
//	 *                model
//	 * @param @return
//	 * @return String
//	 */
//	@RequestMapping("wxPayResult")
//	@ResponseBody
//	public RespBean wxPayResult(String orderNo) {
//		//ResultVo resultVo = new ResultVo(CODE.SUCCESS);
////		XgPayOrder order = new XgPayOrder();
////		order.setId(orderNo);
////		order = orderService.findorderdesc(order);
////		if (order.getOrderStatus() == 2) {
////			resultVo.setData(order.getId());
////			resultVo.setDesc("支付成功");
////		} else {
////			resultVo.setData(null);
////			resultVo.setCode(CODE.FAIL);
////		}
////		return resultVo;
//		return  null;
//	}

}
