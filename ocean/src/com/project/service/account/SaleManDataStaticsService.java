
package com.project.service.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;
import com.thinkive.base.jdbc.DBPage;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.base.jdbc.JdbcTemplate;
import com.thinkive.base.service.BaseService;

public class SaleManDataStaticsService extends BaseService{
	private static Logger logger = Logger.getLogger(OLVService.class);
	private StringBuffer stringBuffer;
	public JdbcTemplate getJdbcTemplate() {
		return getJdbcTemplate("web");
	}
	/**
	 * 统计每个月的订单数量
	 * @return
	 *//*
    public  List<Map<String,Object>>	getTotalOrderByMonth(int dataType){
		     stringBuffer =new StringBuffer();
		     stringBuffer.append("SELECT bz_ren,count(phone)phone_sum FROM sd_yingxiao_phone_normal ");
		     stringBuffer.append("WHERE DATE_FORMAT( create_time, '%Y%m' ) = ");
   		     stringBuffer.append("DATE_FORMAT( CURDATE( ) , '%Y%m' ) AND bz_ren is not null  and data_type='"+dataType+"' GROUP BY bz_ren");
     return   getJdbcTemplate().query(stringBuffer.toString());
	}
    
    *//**
     * 总单数(有备注的)
     *//*
    public List<Map<String,Object>> getTotalOrder(int dataType){
    	stringBuffer =new StringBuffer();
    	stringBuffer.append("SELECT bz_ren,count(phone)as phone_sum FROM sd_yingxiao_phone_normal WHERE DATE_FORMAT( create_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) ,'%Y%m' ) and bz_content is not null ");
    	stringBuffer.append("AND data_type ='"+dataType+"' ");
    	stringBuffer.append("GROUP BY bz_ren");
    	return getJdbcTemplate().query(stringBuffer.toString());
    }
    *//**
     * 每月成功的订单数
     *//*
    public  List<Map<String,Object>>	getSucessOrderByMonth(int dataType){
        String sql = "SELECT bz_ren,succcess_order as total_order FROM  (SELECT bz_ren,  IFNULL(count(phone),0) as succcess_order, sfyfk,data_type "+
				 "FROM (SELECT  od.sfyfk,s.bz_ren,s.phone as phone ,s.data_type FROM sd_yingxiao_phone_normal s "+
				"LEFT JOIN sd_user u ON s.phone =u.mobilePhone "+
				"LEFT JOIN  sd_new_jkyx od "+
				"ON od.userid =u.id "+
				"WHERE DATE_FORMAT( s.create_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) )t_orders "+
				"GROUP BY bz_ren) new_order "+
				"WHERE bz_ren is not null AND sfyfk=1 "+
				"AND data_type='"+dataType+"'"+
				"GROUP BY bz_ren";
        return getJdbcTemplate().query(sql);
   	}
	
    *//**
     * 每个月失败的总单数
     * @return
     *//*
    public List<Map<String,Object>> getTotalFialOrder(int dataType){
      String sql="SELECT  s.bz_ren, count(s.phone) as fail_order FROM sd_yingxiao_phone_normal s "+
				"LEFT JOIN sd_user u ON s.phone =u.mobilePhone "+
				"LEFT JOIN  sd_new_jkyx od "+
				"ON od.userid =u.id "+
				"WHERE DATE_FORMAT( s.create_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) "+
				"and (od.cl_status=3 OR od.cl02_status =3 OR od.cl03_status=3) AND bz_ren is not NULL "+
				"AND data_type ='"+dataType+"' "+
				"GROUP BY s.bz_ren";
      return getJdbcTemplate().query(sql);
    }
    
    *//**
     * 今日总成功的订单数
     *//*
    public  List<Map<String,Object>> getTodaySuccessOrder(int dataType){
    	String sql ="LEFT JOIN sd_user u ON s.phone =u.mobilePhone "+
					"LEFT JOIN  sd_new_jkyx od "+
					"ON od.userid =u.id "+
					"WHERE to_days(s.create_time) = to_days(now()) AND od.sfyfk=1 AND s.data_type='"+dataType+"' "+
					"GROUP BY s.bz_ren";
        return getJdbcTemplate().query(sql);
    }
    
    *//**
     * 今日的总失败借款
     *//*
    public  List<Map<String,Object>> getTodayFailOrder(int dataType){
    	String sql="SELECT s.bz_ren, count(s.phone) as fail_order FROM sd_yingxiao_phone_normal s"+
				"LEFT JOIN sd_user u ON s.phone =u.mobilePhone "+
				"LEFT JOIN  sd_new_jkyx od "+
				"ON od.userid =u.id "+
				"WHERE to_days(s.create_time) = to_days(now()) "+
				"and (od.cl_status=3 OR cl02_status =3 OR cl03_status=3)  AND s.data_type='"+dataType+"' "+
				"GROUP BY s.bz_ren";
    	return getJdbcTemplate().query(sql);
    }
    *//**
     * 当天的总单数
     *//*
    public  List<Map<String,Object>> getTodayTotalOrder(int dataType){
    	String sql ="SELECT  bz_ren,  COUNT(phone)as today_orders FROM sd_yingxiao_phone_normal  where to_days(create_time) = to_days(now()) AND data_type='"+dataType+"' GROUP BY bz_ren";
    	return getJdbcTemplate().query(sql);
    }*/
/*public static void main(String[] args) {
	logger.info("总单数");
	SaleManDataStaticsService service =new SaleManDataStaticsService();
	List<Map<String,Object>>mp =service.getTotalOrderByMonth(20);
	Map<String,Object> saveData;
	//总订单数
	if(mp.size()>0){
		for(Map m:mp){
			System.out.println("m="+m.get("bz_ren"));
			List<Map<String,Object>>mpSumOrder =service.getSucessOrderByMonth(20);
			//总的订单数(有备注的)
			if(mpSumOrder.size()>0){
				  saveData =new HashedMap();
				for(Map sumOrder:mpSumOrder){
					//判断有值的时候
					if(m.get("bz_ren").equals(sumOrder.get("bz_ren"))){
					  saveData.put(m.get("bz_ren").toString(), sumOrder.get("phone_sum"));
					}
				}
			}else {
				  saveData =new HashedMap();
				  saveData.put(m.get("bz_ren").toString(),0);
			}
		}
	}
	System.out.println("总单数"+service.getTotalOrderByMonth(20).get(0).get("bz_ren"));
	System.out.println("每月成功的订单数"+service.getSucessOrderByMonth(20));
}*/
	/**
	 * 获取营销数据
	 */
   public   DBPage getSaleDatasByPage(int curPage ,int numPerPage,Integer 
		   dataType,String startDate,
		   String endDate,String startImportDate,
		   String endImportDate){
	   StringBuffer sb =new StringBuffer();
	  sb.append("SELECT  t1.bz_ren,IFNULL(phone_num,0) as phone_num,IFNULL(success_order,0) AS success_order, IFNULL(fail_order,0) AS fail_order ,   "
	  		+ " IFNULL(t4.today_fail_order,0) as today_fail_order,  "
	  		+ "IFNULL(t5.today_success_order,0)as today_success_order ,"
	  		+ "IFNULL (t6.today_remark_orders,0) as today_remark_orders,  "
	  		+ "IFNULL(t7.today_orders,0) as today_orders,"
	  		+ "IFNULL(t8.sum_remark_orders,0) as sum_remark_orders  FROM  ");
	  sb.append(" (SELECT bz_ren, count(phone)as phone_num FROM sd_yingxiao_phone_normal ");
	  sb.append(" WHERE bz_ren is not null ");
	  if(null!=dataType){
		  sb.append("AND data_type='"+dataType+"'  ");
	  }
	  System.out.println("startDate=="+startDate);
	  System.out.println("endDate=="+endDate);
	  if((!"".equals(startDate))&&(!"".equals(startDate))){
		  sb.append("and create_time between '"+ startDate+"'  and '"+endDate+"'  ");
	   }
	  if((!"".equals(startImportDate))&&(!"".equals(endImportDate))){
		  sb.append(" AND  CONCAT(SUBSTRING(bz_time,7,4),'-',SUBSTRING(bz_time,4,2),'-',SUBSTRING(bz_time,1,2)) >='"+startImportDate+"' " );
		  sb.append("  AND  CONCAT(SUBSTRING(bz_time,7,4),'-',SUBSTRING(bz_time,4,2),'-',SUBSTRING(bz_time,1,2)) <='"+endImportDate+"'");
	   }
	  sb.append("GROUP BY bz_ren)t1  ");
	  sb.append("LEFT JOIN  ");
	  
	  //查询成功单数
	  sb.append(" (SELECT s.bz_ren,COUNT(sfyfk) AS success_order FROM sd_yingxiao_phone_normal s   ");
	  sb.append(" LEFT JOIN sd_user u ON s.phone =u.mobilePhone ");
	  sb.append(" LEFT JOIN  sd_new_jkyx od  ");
	  sb.append(" ON od.userid =u.id  ");
	  sb.append(" WHERE  od.sfyfk=1 GROUP BY s.bz_ren) t2  ");
	  sb.append(" ON t1.bz_ren =t2.bz_ren ");
	  sb.append(" LEFT JOIN   ");
	  //查询失败单数
	  sb.append(" (SELECT s.bz_ren, count(s.phone) as fail_order FROM sd_yingxiao_phone_normal s  ");
	  sb.append(" LEFT JOIN sd_user u ON s.phone =u.mobilePhone  ");
	  sb.append("LEFT JOIN  sd_new_jkyx od  ");
	  sb.append("ON od.userid =u.id  ");
	  sb.append("WHERE (od.cl_status=3 OR cl02_status =3 OR cl03_status=3)  GROUP BY s.bz_ren) t3  ");
	  sb.append("ON t1.bz_ren = t3.bz_ren  ");
	  
	  sb.append(" LEFT JOIN   ");
	  //查询今天失败的订单数
	  sb.append("(SELECT s.bz_ren, count(s.phone) as today_fail_order FROM sd_yingxiao_phone_normal s ");
	  sb.append("LEFT JOIN sd_user u ON s.phone =u.mobilePhone ");
	  sb.append("LEFT JOIN  sd_new_jkyx od ");
	  sb.append("ON od.userid =u.id ");
	  sb.append("WHERE to_days(od.daytime) = to_days(now()) ");
	  sb.append("and (od.cl_status=3 OR cl02_status =3 OR cl03_status=3) ");
	  sb.append("GROUP BY s.bz_ren )t4 ");
	  sb.append("ON t1.bz_ren = t4.bz_ren ");
	  sb.append(" LEFT JOIN   ");
	  //今日成功的单数
	  sb.append("(SELECT bz_ren,  IFNULL( count(phone) ,0) as today_success_order FROM sd_yingxiao_phone_normal ");
	  sb.append("WHERE phone  in (SELECT mobilePhone  FROM sd_user WHERE id in  (SELECT userid FROM sd_new_jkyx     "
	  		+ "WHERE to_days(daytime) = to_days(now()) AND   sfyfk=1))  GROUP BY bz_ren  )t5 ");
	  sb.append("ON t4.bz_ren = t5.bz_ren ");
	  //今天的备注总数
	  sb.append(" LEFT JOIN   ");
	  sb.append("(SELECT s.bz_ren, IFNULL(count(s.phone),0) as today_remark_orders FROM sd_yingxiao_phone_normal s ");
	  sb.append("LEFT JOIN sd_user u ON s.phone =u.mobilePhone ");
	  sb.append("LEFT JOIN  sd_new_jkyx od   ");
	  sb.append("ON od.userid =u.id ");
	  sb.append("WHERE s.bz_content is NOT null   and  TO_DAYS(str_to_date(s.bz_time, '%d-%m-%Y'))=TO_DAYS(NOW()) ");
	  sb.append("GROUP BY s.bz_ren )t6  ");
	  sb.append("ON t5.bz_ren = t6.bz_ren");
	  sb.append(" LEFT JOIN   ");
	  //今天上传的总数
	  sb.append("(SELECT  bz_ren,  COUNT(phone)as today_orders FROM sd_yingxiao_phone_normal   "
	  		+ "  where to_days(create_time) = to_days(now()) GROUP BY bz_ren) t7  ");
	  
	  sb.append("ON t6.bz_ren = t7.bz_ren ");
	  sb.append(" LEFT JOIN   ");
	  //上传的总数
	  sb.append("(SELECT bz_ren, COUNT(phone)as sum_remark_orders FROM sd_yingxiao_phone_normal  WHERE bz_ren is NOT null AND (bz_content IS not null AND bz_content!='' )  GROUP BY bz_ren) t8   ");
	  sb.append("  ON t1.bz_ren = t8.bz_ren  ");
	  
	  //
	  System.out.println("sql===="+sb.toString());
	   return getJdbcTemplate().queryPage(sb.toString(), curPage, numPerPage);
	}
	/*public static void main(String[] args) {
		SaleManDataStaticsService saleService =new SaleManDataStaticsService();
		List list = saleService.getSaleDatas(20);
		System.out.println("list==="+list);
		
	}*/
// 获取营销数据类型
		public List<Map<String,Object>> getDataTypeList(){
			String sql ="SELECT remark,data_type, substring(create_time,1,10) as 'create_time' FROM sd_yingxiao_phone_normal WHERE data_type > 0  GROUP BY remark  ORDER BY data_type";
			return getJdbcTemplate().query(sql);
		} 
		
		
		
		
  /*************修改营销统计页面********************************/
	public DBPage getSaleDatasPersonPage(int curPage ,int numPerPage,Integer dataType,String startDate, String endDate,String startImportDate, String endImportDate){
		 StringBuffer sb =new StringBuffer();
		 sb.append("SELECT bz_ren,COUNT(*) AS 'total',COUNT(CASE WHEN  SUBSTRING(create_time,1,10) =DATE_FORMAT(NOW(),'%Y-%m-%d') THEN 1 END) AS 'total_now',COUNT(CASE WHEN bz_content  IS NOT NULL THEN 1 END) AS 'bz_content',");
		 sb.append(" COUNT(CASE WHEN bz_content  IS NOT NULL  AND  SUBSTRING(bz_time,1,10) =DATE_FORMAT(NOW(),'%d-%m-%Y') THEN 1 END) AS 'bz_content_now' FROM  sd_yingxiao_phone_normal ");
		 sb.append( " WHERE product_type =20 AND show_state =1 ");
		 
		 if(dataType > 0) {
			 sb.append( "  and  data_type ="+dataType );
		 }
		 
		 if(!"".equals(startDate) && !"".equals(endDate)) {
			 sb.append( "  AND  SUBSTRING(create_time,1,10) >='"+startDate+"'" );
			 sb.append(	"  AND  SUBSTRING(create_time,1,10) <='"+endDate+"'" );
			}
			if(!"".equals(startImportDate) && !"".equals(endImportDate)) {
				sb.append( "  AND  CONCAT(SUBSTRING(bz_time,7,4),'-',SUBSTRING(bz_time,4,2),'-',SUBSTRING(bz_time,1,2)) >='"+startImportDate+"'");
				sb.append("  AND  CONCAT(SUBSTRING(bz_time,7,4),'-',SUBSTRING(bz_time,4,2),'-',SUBSTRING(bz_time,1,2)) <='"+endImportDate+"'" );
			}
		 
		 sb.append(" GROUP BY bz_ren");
		
		 return getJdbcTemplate().queryPage(sb.toString(), curPage, numPerPage);
		
	}
	
	
	//注册用户数量
	public int geyingxiaoPhonetUserCount(Integer bz_ren ,Integer dataType,String startDate, String endDate,String startImportDate, String endImportDate,int isToday) {
		StringBuffer sb =new StringBuffer();
		 sb.append(" SELECT COUNT(*) FROM sd_user  u LEFT JOIN  sd_yingxiao_phone_normal p ON u.mobilephone = p.phone   WHERE p.product_type =20 AND p.show_state =1 ");
		if(dataType > 0) {
			 sb.append( "  and  p.data_type ="+dataType );
		 }
		if(bz_ren > 0) {
			 sb.append( "  and  p.bz_ren ="+bz_ren );
		 }
		 
		if(1 == isToday ) {
			if(!"".equals(endDate)) {
				 sb.append(	"  AND  SUBSTRING(u.yearmonthday,1,10) ='"+endDate+"'" );
			}else {
				sb.append("  AND   SUBSTRING(u.yearmonthday,1,10) =DATE_FORMAT(NOW(),'%Y-%m-%d') " );
			}
			
			if( !"".equals(endImportDate)) {
				sb.append("  AND  CONCAT(SUBSTRING(p.bz_time,7,4),'-',SUBSTRING(p.bz_time,4,2),'-',SUBSTRING(p.bz_time,1,2)) ='"+endImportDate+"'" );
			}
			
		}else {
			if(!"".equals(startDate) && !"".equals(endDate)) {
				 sb.append( "  AND  SUBSTRING(p.create_time,1,10) >='"+startDate+"'" );
				 sb.append(	"  AND  SUBSTRING(p.create_time,1,10) <='"+endDate+"'" );
			}
			if(!"".equals(startImportDate) && !"".equals(endImportDate)) {
				sb.append( "  AND  CONCAT(SUBSTRING(p.bz_time,7,4),'-',SUBSTRING(p.bz_time,4,2),'-',SUBSTRING(p.bz_time,1,2)) >='"+startImportDate+"'");
				sb.append("  AND  CONCAT(SUBSTRING(p.bz_time,7,4),'-',SUBSTRING(p.bz_time,4,2),'-',SUBSTRING(p.bz_time,1,2)) <='"+endImportDate+"'" );
			}
		}
		
		
		return getJdbcTemplate().queryInt(sb.toString());
		
	}
	
	
	//查询借款情况
	public DataRow geyingxiaoPhonetJKRow(Integer bz_ren ,Integer dataType,String startDate, String endDate,String startImportDate, String endImportDate,int isToday) {
		StringBuffer sb =new StringBuffer();
		 sb.append(" SELECT  COUNT(DISTINCT j.userid) AS 'tj_num', COUNT(CASE WHEN j.sfyfk = 1 THEN 1 END) AS 'fk_num', COUNT(CASE WHEN j.sfyfk = 1  AND j.sfyhw =1 THEN 1 END) AS 'hk_num',");
		 sb.append( " COUNT(CASE WHEN j.sfyfk = 1  AND j.sfyhw =0 AND j.yuq_ts > 0 THEN 1 END) AS 'yq_num' FROM sd_user  u");
		 sb.append( " LEFT JOIN  sd_yingxiao_phone_normal p ON u.mobilephone = p.phone LEFT JOIN sd_new_jkyx j ON u.id = j.userid   WHERE u.id = j.userid");
		 sb.append( " AND p.product_type =20 AND p.show_state =1 AND j.is_old_user = 0 ");
		if(dataType > 0) {
			 sb.append( "  and  data_type ="+dataType );
		 }
		if(bz_ren > 0) {
			 sb.append( "  and  p.bz_ren ="+bz_ren );
		 }
		if(1 == isToday ) {
			if(!"".equals(endDate)) {
				 sb.append(	"  AND  SUBSTRING(u.yearmonthday,1,10) ='"+endDate+"'" );
			}else {
				sb.append("  AND   SUBSTRING(u.yearmonthday,1,10) =DATE_FORMAT(NOW(),'%Y-%m-%d') " );
			}
			
			if( !"".equals(endImportDate)) {
				sb.append("  AND  CONCAT(SUBSTRING(p.bz_time,7,4),'-',SUBSTRING(p.bz_time,4,2),'-',SUBSTRING(p.bz_time,1,2)) ='"+endImportDate+"'" );
			}
		}else {
			if(!"".equals(startDate) && !"".equals(endDate)) {
				 sb.append( "  AND  SUBSTRING(p.create_time,1,10) >='"+startDate+"'" );
				 sb.append(	"  AND  SUBSTRING(p.create_time,1,10) <='"+endDate+"'" );
			}
			if(!"".equals(startImportDate) && !"".equals(endImportDate)) {
				sb.append( "  AND  CONCAT(SUBSTRING(p.bz_time,7,4),'-',SUBSTRING(p.bz_time,4,2),'-',SUBSTRING(p.bz_time,1,2)) >='"+startImportDate+"'");
				sb.append("  AND  CONCAT(SUBSTRING(p.bz_time,7,4),'-',SUBSTRING(p.bz_time,4,2),'-',SUBSTRING(p.bz_time,1,2)) <='"+endImportDate+"'" );
			}
		}
		
		
		return getJdbcTemplate().queryMap(sb.toString());
		
	}
	
	
}
