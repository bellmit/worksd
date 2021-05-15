package root.kefu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.project.service.kefu.KefuReturnVisitService;
import com.thinkive.base.jdbc.DBPage;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.web.base.ActionResult;
import com.thinkive.web.base.BaseAction;

public class KefuReturnVisitAction extends BaseAction{
	
	private static Logger logger=Logger.getLogger(KefuReturnVisitAction.class);
	private static KefuReturnVisitService kefuReturnVisitService=new KefuReturnVisitService();
	
	/**
	 * �õ���Ҫ�طõĿͻ�
	 * @return
	 * @throws Exception 
	 */
	public ActionResult doGetReturnVisit() throws Exception{
		logger.info("�����ѯ��Ҫ�طõ��û�");
    	int temp = getIntParameter("temp",0);
		String  tempVelue = getStrParameter("tempvl"); 		
		String  startDate =getStrParameter("startDate");
		String  endDate =getStrParameter("endDate");
		//���״̬
		String commit = getStrParameter("commit");
		//�����û�ѡ������
		if(!startDate.equals("")&&!endDate.equals("")){
			if(startDate.endsWith(endDate)){
				startDate+=" 00:00:00";
				endDate+=" 23:59:59";
			}
		}
		String userId ="" ;
		String realName="";
		String phone = "" ;	
		String idCard ="" ;
		logger.info("temp:"+temp+"startDate:"+startDate+" endDate"+endDate);
		if(temp ==1){
				   	
			userId =tempVelue ;
		}
		
		if(temp ==2){
					
			realName =tempVelue ;
		}
		if(temp ==3) {
					
			phone = tempVelue ;
		}
		if(temp ==4) {
			
			idCard = tempVelue ;
		}	
		//Ĭ�ϵ�һҳ
		int curPage  =getIntParameter("curPage",1);	
		DBPage page = kefuReturnVisitService.getReturnVisitList(curPage,15,userId,realName,phone,startDate,endDate,commit,idCard);
		DataRow row = new DataRow();
		row.set("list", page);
		row.set("temp",temp);
		row.set("tempvalu",tempVelue);
		row.set("commit ",commit);
		JSONObject object = JSONObject.fromBean(row);			
		this.getWriter().write(object.toString());
    	return null ;
	}
	
	/**
	 * �õ��ѻطõĿͻ�
	 * @return
	 * @throws Exception 
	 */
	public ActionResult doGetReturnVisited() throws Exception{
		logger.info("�����ѯ��Ҫ�طõ��û�");
    	int temp = getIntParameter("temp",0);
		String  tempVelue = getStrParameter("tempvl"); 		
		String  startDate =getStrParameter("startDate");
		String  endDate =getStrParameter("endDate");
		Integer state=getIntParameter("select");
		if(!startDate.equals("")&&!endDate.equals("")){
			if(startDate.endsWith(endDate)){
				startDate+=" 00:00:00";
				endDate+=" 23:59:59";
			}
		}
		
		//���״̬
		//String commit = getStrParameter("commit");
		//�����û�ѡ������
		String userId ="" ;
		String realName="";
		String phone = "" ;	
		logger.info("temp:"+temp+"startDate:"+startDate+" endDate"+endDate);
		if(temp ==1){
				   	
			userId =tempVelue ;
		}
		
		if(temp ==2){
					
			realName =tempVelue ;
		}
		if(temp ==3) {
					
			phone = tempVelue ;
		}	
		//Ĭ�ϵ�һҳ
		int curPage  =getIntParameter("curPage",1);	
		DBPage page = kefuReturnVisitService.getAllVisited(curPage, 15, userId, realName, phone, startDate, endDate, state);
		DataRow row = new DataRow();
		row.set("list", page);
		row.set("temp",temp);
		row.set("tempvalu",tempVelue);
		//row.set("commit ",commit);
		JSONObject object = JSONObject.fromBean(row);			
		this.getWriter().write(object.toString());
    	return null ;
	}

	/**
	 * ���ӻطü�¼
	 * @return
	 */
	public ActionResult doAddReturnVisit(){
		int jkid=getIntParameter("reid");
		int userid=getIntParameter("userid");
		String remark=getStrParameter("remark");
		int state=getIntParameter("state");
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		DataRow dr=new DataRow();
		dr.set("jkjl_id",jkid );
		dr.set("user_id",userid );
		dr.set("state",state );
		dr.set("visitdate", time);
		dr.set("content",remark );
		int id=kefuReturnVisitService.addReturnVisit("sd_returnvisit", dr);
		JSONObject json=new JSONObject();
		if(id!=0){
			Boolean bool=kefuReturnVisitService.updateKfhf_kfid(id, jkid);
			if(bool!=true){
				json.put("msg", "���ʧ��");
			}else{
				json.put("msg", "��ӳɹ�");
			}
		}
		this.getWriter().write(json.toString());
		return null;
	}
}
