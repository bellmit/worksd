package root.tool;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import root.tool.HttpUtil;

/**
 * 2020��2��7��
 * ���� ���ŶԽ�  
 * @author Administrator
 *
 */

public class SendMsgCL {
	private static Logger logger = Logger.getLogger(HttpUtil.class); 
	

	/**
	 * ֻ�����ڷ�����֤�� �ӿ�
	 * @param content   ��������
	 * @param mobile    �绰����
	 * @return
	 */
	public static String sendOTP(String content,String mobile) {

		//�����ַ
		String url="http://intapi.253.com/send/json";
		
		//API�˺ţ�50λ���ڡ�����   IM0476103      -- I0774456
		String account="IM0476103";	
		
		//API�˺Ŷ�Ӧ��Կ����ϵ�ͷ���ȡ������   M36LFo8N2c7e68    --  8UwqxZuNoBa46a
		String password="M36LFo8N2c7e68";
		
		//�������ݡ����Ȳ��ܳ���536���ַ�������
		//String msg="Your etracker otp content is";
		String msg= content;
		//�ֻ����룬��ʽ(����+�ֻ�����)�����磺8615800000000������86Ϊ�й������ţ�����ǰ��ʹ��00��ͷ,15800000000Ϊ���ն��ŵ���ʵ�ֻ����롣5-20λ������
		// 840328599926
		//String mobile="840587321816";
		mobile="84"+mobile;
		
		//�û��յ�����֮����ʾ�ķ����ˣ����ڲ�֧���Զ��壬����֧�֣�������Ҫ��ǰ����Ӫ�̹�ͨע�ᣬ��������TIG�Խ���Աȷ����ѡ��
		String senderId = "OCEAN";
		
		//��װ�������
		JSONObject map=new JSONObject();
		map.put("account", account);
		map.put("password", password);
		map.put("msg", msg);
		map.put("mobile", mobile);
		map.put("senderId", senderId);

		String params=map.toString();
		
		logger.info("�������Ϊ:" + params);
		try {
			String result=HttpUtil.post(url, params);
			
			logger.info("���ز���Ϊ:" + result);
			
			JSONObject jsonObject =  JSON.parseObject(result);
			String code = jsonObject.get("code").toString();
			String msgid = jsonObject.get("msgid").toString();
			String error = jsonObject.get("error").toString();
			
			logger.info("״̬��:" + code + ",״̬��˵��:" + error + ",��Ϣid:" + msgid);
			return code;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("�����쳣��" + e);
		}
	
		return "-1"; 
	}
	
	/**
	 * ֻ�����ڷ���Ӫ�����ݽӿ�
	 * @param content   ��������
	 * @param mobile    �绰����
	 * @return
	 */
	public static String sendOtherMsg(String content,String mobile) {

		//�����ַ
		String url="http://intapi.253.com/send/json";
		
		//API�˺ţ�50λ���ڡ�����   IM0476103 --M36LFo8N2c7e68       I0774456 --8UwqxZuNoBa46a
		String account="I0774456";	
		
		//API�˺Ŷ�Ӧ��Կ����ϵ�ͷ���ȡ������   M36LFo8N2c7e68
		String password="8UwqxZuNoBa46a";
		
		//�������ݡ����Ȳ��ܳ���536���ַ�������
		//String msg="Your etracker otp content is";
		String msg= content;
		//�ֻ����룬��ʽ(����+�ֻ�����)�����磺8615800000000������86Ϊ�й������ţ�����ǰ��ʹ��00��ͷ,15800000000Ϊ���ն��ŵ���ʵ�ֻ����롣5-20λ������
		// 840328599926
		//String mobile="840587321816";
		mobile="84"+mobile;
		
		//�û��յ�����֮����ʾ�ķ����ˣ����ڲ�֧���Զ��壬����֧�֣�������Ҫ��ǰ����Ӫ�̹�ͨע�ᣬ��������TIG�Խ���Աȷ����ѡ��
		String senderId = "OCEAN";
		
		//��װ�������
		JSONObject map=new JSONObject();
		map.put("account", account);
		map.put("password", password);
		map.put("msg", msg);
		map.put("mobile", mobile);
		map.put("senderId", senderId);

		String params=map.toString();
		
		logger.info("�������Ϊ:" + params);
		try {
			String result=HttpUtil.post(url, params);
			
			logger.info("���ز���Ϊ:" + result);
			
			JSONObject jsonObject =  JSON.parseObject(result);
			String code = jsonObject.get("code").toString();
			String msgid = jsonObject.get("msgid").toString();
			String error = jsonObject.get("error").toString();
			
			logger.info("״̬��:" + code + ",״̬��˵��:" + error + ",��Ϣid:" + msgid);
			return code;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("�����쳣��" + e);
		}
	
		return "-1"; 
	}

}
