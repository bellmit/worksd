package com.project.constant;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ����: վ�㹫�ó�����
 * ��Ȩ: Copyright (c) 2010
 * ��˾: ˼��
 * ����: �  lijian@thinkive.com
 * �汾: 1.0
 * ��������: Mar 11, 2010
 * ����ʱ��: 5:29:43 PM
 */
public class WebConstants extends HashMap
{
	
	
	private static Log log = LogFactory.getLog(WebConstants.class);
	
	
	private static Map map;
	
	
	
	//	>>>>>>>>>>>>>>>>>>>>>>>>>> վ����س��� ��ʼ	
	
	/**
	 * web��վվ��
	 */
	public static final String SITE_MAIN = "main";
	
	
	
	//	<<<<<<<<<<<<<<<<<<<<<<<<<< վ����س��� ����
	
	
	//	>>>>>>>>>>>>>>>>>>>>>>>>>> ���ݿ���س��� ��ʼ
	
	/**
	 * ccϵͳ����Ӧ�����ݿ�
	 */
	public static final String DB_CC = "cc";
	
	
	
	/**
	 * web��վ����Ӧ�����ݿ�
	 */
	public static final String DB_WEB = "web";
	
	
	
	//	<<<<<<<<<<<<<<<<<<<<<<<<<< ���ݿ���س��� ����
	
	
	//	>>>>>>>>>>>>>>>>>>>>>>>>>> ������س��� ��ʼ
	
	/**
	 * �����ѷ���״̬
	 */
	public static final int ARTICLE_STATE_PUBLISHED = 3;
	
	
	public static final int ARTICLE_IS_HEAD = 1;
	
	
	
	/**
	 * ����չʾʱ��������Ŀ
	 */
	public static final int ARTICLE_LIST_IS_HAVE_CHILDREN = 1;
	
	
	
	//	<<<<<<<<<<<<<<<<<<<<<<<<<< ������س��� ����
	
	
	//	>>>>>>>>>>>>>>>>>>>>>>>>>> SESSION�û���س��� ��ʼ
	/**
	 * session�б���Ŀͻ���½IDkey
	 */
	public static final String SESSION_CLIENT_LOGIN_ID = "@home_client_loginid";
	
	
	
	/**
	 * session�б���Ŀͻ�������id��key
	 */
	public static final String SESSION_CLIENT_ID = "@home_client_id";
	
	
	
	/**
	 * session�б���Ŀͻ�������
	 */
	public static final String SESSION_CLIENT_NAME = "@home_client_name";
	
	
	
	/**
	 * session�б���Ŀͻ�cif�е�����
	 */
	public static final String SESSION_CIF_CLIENT_NAME = "@home_cif_client_name";
	
	
	
	/**
	 * session�б���Ŀͻ�������
	 */
	public static final String SESSION_CLIENT_TYPE = "@home_client_type";
	
	
	
	/**
	 * session�б���Ŀͻ��ļ���
	 */
	public static final String SESSION_CLIENT_LEVEL = "@home_client_level";
	
	
	
	/**
	 * session�б���Ŀͻ�������
	 */
	public static final String SESSION_CLIENT_ATTRIBUTE = "@home_client_attribute";
	
	
	
	/**
	 * session�б���Ŀͻ��ʽ��˺ŵ�key
	 */
	public static final String SESSION_CLIENT_ASSETACCOUNT = "@home_client_assetaccount";
	
	
	
	/**
	 * session�б���Ŀͻ��ʽ��˺ŵ�����
	 */
	public static final String SESSION_CLIENT_ASSET_PASSWORD = "@trade_client_asset_password";
	
	
	
	/**
	 * session�б���Ŀͻ�����
	 */
	public static final String SESSION_CLIENT_BRANCHNO = "@home_client_branchno";
	
	
	
	/**
	 * session�б���Ŀͻ�����ID
	 */
	public static final String SESSION_CLIENT_MNG_ID = "@home_client_mng_id";
	
	
	
	/**
	 * session�б���Ŀͻ�����Name
	 */
	public static final String SESSION_CLIENT_MNG_NAME = "@home_client_mng_name";
	
	
	
	//	<<<<<<<<<<<<<<<<<<<<<<<<<< SESSION�û���س��� ����
	
	
	//	>>>>>>>>>>>>>>>>>>>>>>>>>> COOKIE�û���س��� ��ʼ
	
	/**
	 * cookie�б����û���Ϣ��key name
	 */
	public static final String COOKIE_CLIENT_INFO_KEY = "user_login";
	
	
	
	//	<<<<<<<<<<<<<<<<<<<<<<<<<< COOKIE�û���س��� ����
	
	
	//	>>>>>>>>>>>>>>>>>>>>>>>>>> �û�ע����Ϣ��س��� ��ʼ
	/**
	 * �Ƿ��
	 */
	public static final String CLIENT_IS_VALIDATE = "1";
	
	
	
	/**
	 * �ʼ�δ��֤
	 */
	public static final String CLIENT_MAIL_NO_VALIDATE = "0";
	
	
	
	/**
	 * �ʼ��Ѿ���֤
	 */
	public static final String CLIENT_MAIL_VALIDATE = "1";
	
	
	
	/**
	 * �ֻ���δ��֤
	 */
	public static final String CLIENT_PHONE_NO_VALIDATE = "0";
	
	
	
	/**
	 * �ֻ�������֤
	 */
	public static final String CLIENT_PHONE_VALIDATE = "1";
	
	
	
	//	<<<<<<<<<<<<<<<<<<<<<<<<<< �û�ע����Ϣ��س��� ����
	
	
	//	>>>>>>>>>>>>>>>>>>>>>>>>>> �������� ��ʼ
	
	/**
	 * ��Ʊ��������
	 */
	public static final String MY_STOCK_TYPE = "0;1;2;3;4;9;10;11;12;18;";
	
	
	
	
	//	<<<<<<<<<<<<<<<<<<<<<<<<<< �������� ����
	
	
	//	>>>>>>>>>>>>>>>>>>>>>>>>>> ��ʱ���� ��ʼ
	
	
	//	<<<<<<<<<<<<<<<<<<<<<<<<<< ��ʱ���� ����
	
	/**
	 * ʹ�øù��췽��ʵ��һ��JSTL�ɷ��ʵ�ϵͳ������
	 */
	public WebConstants()
	{
		// initialize only once...
		if (map != null)
			return;
		
		map = new HashMap();
		Class c = this.getClass();
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++)
		{
			Field field = fields[i];
			int modifier = field.getModifiers();
			//�ų�private����
			if (Modifier.isFinal(modifier) && !Modifier.isPrivate(modifier))
			{
				try
				{
					this.put(field.getName(), field.get(this));
				}
				catch (IllegalAccessException e)
				{
					//e.printStackTrace();
					log.error(e);
				}
			}
		}
	}
	
	
	@Override
	public Object get(Object key)
	{
		return map.get(key);
	}
	
	
	@Override
	public Object put(Object key, Object value)
	{
		return map.put(key, value);
	}
	
}
