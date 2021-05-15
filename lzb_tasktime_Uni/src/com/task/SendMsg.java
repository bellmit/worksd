package com.task;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class SendMsg {
	 //验证用户信息
	 /*private static final String loginName = "AB38MG3";
	 private static final String brandName = "n%2Fa";// n/a
	 private static final String serviceTypeId = "190";
	 private static final String content = "";
	 private static final String sign = "2b949d146e92f69664fffb5e4825e65f";*/
	 private static final String loginName = "AB38MG3";
	 private static final String brandName = "n%2Fa";// n/a
	 private static final String serviceTypeId = "190";
	 private static final String content = "";
	 private static final String sign = "2b949d146e92f69664fffb5e4825e65f";
	 //字符编码及其他参数
	//VERIFY
	 private static final String loginNameVLP = "AB38MG3";
	 private static final String sendSmsPassword = "34PURHEPK";
	 private static final String brandNameVLP = "Verify";// n/a
	 private static final String serviceTypeIdVLP = "30";
	 private static final String contentVLP = "";
	 private static final String signVLP = "715a3440d2859d96b910fd1a2c3c956a";
	 
	 //PLUTUS
	 private static final String loginNamePLUTUS = "AB38MG3";
	 private static final String brandNamePLUTUS = "PLUTUS";// n/a
	 private static final String serviceTypeIdPLUTUS = "30";
	 private static final String signPLUTUS = "a1aa0315acdf958b3cb8e7f97ed865bf";
	 
	 private static final String charset = "utf-8";
	 private static final String url = "http://api.abenla.com/Service.asmx/SendSms2";
	 /*
	 * 因请求返回的数据中只需要code和result两个字段的信息，因此方法只返回一个存有这两个值的map
	 */
	 //get方式请求
	 public static String sendMessageByGetBrandName(String content){
	        String map = "";
	        HttpClient httpClient = new DefaultHttpClient();
	        String fullUrl = url + "?loginName="+loginNameVLP+"&brandName="+brandNameVLP+"&serviceTypeId="+serviceTypeIdVLP+"&content="+content+"&sign="+signVLP;

	        HttpGet httpGet = new HttpGet(fullUrl);
	         try {
	            HttpResponse response = httpClient.execute(httpGet);
	            HttpEntity entity = response.getEntity();
	           if (null != entity) {
	               InputStream in = entity.getContent();//将返回的内容流入输入流内
	              // 创建一个Document解析工厂
	               DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	                DocumentBuilder builder = factory.newDocumentBuilder();
	              // 将输入流解析为Document
	                 Document document = builder.parse(in);//用输入流实例化Document
	 
	                Element rootElement = document.getDocumentElement();
	         
	                 NodeList codeNode = rootElement.getElementsByTagName("Code");
	                 map =codeNode.item(0).getTextContent();  
	            }
	         } catch (ClientProtocolException e) {
	            e.printStackTrace();
	         } catch (IllegalStateException e) {
	            e.printStackTrace();
	         } catch (DOMException e) {
	              e.printStackTrace();
	         } catch (IOException e) {
	              e.printStackTrace();
	         } catch (ParserConfigurationException e) {
	             e.printStackTrace();
	         } catch (SAXException e) {
	             e.printStackTrace();
	         }
	          return map;
	      }
	      
	    /*
	 * 因请求返回的数据中只需要code和result两个字段的信息，因此方法只返回一个存有这两个值的map
	 */
	 //get方式请求
	 public static String sendMessageByGet(String content){
	        String map = "";
	        HttpClient httpClient = new DefaultHttpClient();
	        String fullUrl = url + "?loginName="+loginNamePLUTUS+"&brandName="+brandNamePLUTUS+"&serviceTypeId="+serviceTypeIdVLP+"&content="+content+"&sign="+signPLUTUS;
	
	        HttpGet httpGet = new HttpGet(fullUrl);
	         try {
	            HttpResponse response = httpClient.execute(httpGet);
	            HttpEntity entity = response.getEntity();
	           if (null != entity) {
	               InputStream in = entity.getContent();//将返回的内容流入输入流内
	              // 创建一个Document解析工厂
	               DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	                DocumentBuilder builder = factory.newDocumentBuilder();
	              // 将输入流解析为Document
	                 Document document = builder.parse(in);//用输入流实例化Document
	
	                Element rootElement = document.getDocumentElement();
	         
	                 NodeList codeNode = rootElement.getElementsByTagName("Code");
	                 map =codeNode.item(0).getTextContent();  
	            }
	         } catch (ClientProtocolException e) {
	            e.printStackTrace();
	         } catch (IllegalStateException e) {
	            e.printStackTrace();
	         } catch (DOMException e) {
	              e.printStackTrace();
	         } catch (IOException e) {
	              e.printStackTrace();
	         } catch (ParserConfigurationException e) {
	             e.printStackTrace();
	         } catch (SAXException e) {
	             e.printStackTrace();
	         }
	          return map;
	      }

		//post方式请求
	     public static Map<String,String> sendMessageByPost(String content,String phones){
	         Map<String,String> map = new HashMap<String, String>();
	        // 创建默认的httpClient实例.    
	         CloseableHttpClient httpclient = HttpClients.createDefault(); 
	        // 创建httppost    
	          HttpPost httppost = new HttpPost(url);  
	        // 创建参数队列    
	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	        formparams.add(new BasicNameValuePair("user", loginName));  
	       formparams.add(new BasicNameValuePair("pwd", brandName)); 
	        formparams.add(new BasicNameValuePair("mobiles", phones)); 
	         formparams.add(new BasicNameValuePair("contents", content)); 
	         
	       UrlEncodedFormEntity uefEntity;
	        try{
	            uefEntity = new UrlEncodedFormEntity(formparams, charset);
	            httppost.setEntity(uefEntity);
	            System.out.println("executing request " + httppost.getURI());
	            CloseableHttpResponse response = httpclient.execute(httppost);
	            try{
	                HttpEntity entity = response.getEntity(); 
	                if (entity != null) { 
	                    //将返回的数据直接转成String
	                   String str = EntityUtils.toString(entity, "UTF-8") ;
	                     System.out.println("--------------------------------------");  
	                    //注意这里不能写成EntityUtils.toString(entity, "UTF-8"),因为EntityUtils只能调用一次，否则会报错：java.io.IOException: Attempted read from closed stream
	                    System.out.println("Response content: " + str);  
	                   System.out.println("--------------------------------------");  
	                    
	                   //这里用str作为参数获得 Document 对象
	                    org.dom4j.Document document = DocumentHelper.parseText(str);
	                    org.dom4j.Element rootElement = document.getRootElement();
	                   
	                    String code = rootElement.element("Code").getText();
	                    String result = rootElement.element("Result").getText();
	                    map.put("Code", code);
	                    map.put("Result", result);
	                }
	            }catch (DocumentException e) {
	                 // TODO Auto-generated catch block
	                 e.printStackTrace();
	             }finally{
	                 response.close();
	             }
	            
	        } catch (ClientProtocolException e) {  
	             e.printStackTrace();  
	        } catch (UnsupportedEncodingException e1) {  
	            e1.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }finally{
	             // 关闭连接,释放资源    
	             try {  
	                httpclient.close();  
	             } catch (IOException e) {  
	                 e.printStackTrace();  
	             }  
	        }
	         
	         return map ;
	     }
	     
	    //验证手机号方法
    public static boolean checkPhoneNo(String phone){
	        if(phone==null || phone.trim().equals("")){
	            return false;
	         }
	        String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$"; 
	        Pattern p = Pattern.compile(regExp);  
	        Matcher m = p.matcher(phone);  
	        return m.find();
	    }
	     
	     public static void main(String args[]) throws UnsupportedEncodingException{
	        //System.out.println(checkPhoneNo(null));
	    	 String content = "[{\"PhoneNumber\":\"0909048942\",\"Message\":\"Mofa xin chao quyen - Xin cap nhat lai thoi han vay 15 hoac 30 ngay. Moi thac mac vui long inbox http://bit.ly/2QJAh16, hotline: 02862769688\",\"SmsGuid\":\"0909048942\",\"ContentType\":1}]";
	    	 String con = URLEncoder.encode(content, "utf-8");
	    	 String returnString = sendMessageByGet(con);
	    	 System.out.println(returnString);
	    }
}

