package com.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.service.JoinJsService;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.timerengine.Task;

public class JoinJsTask implements Task{
	private static Logger logger = Logger.getLogger(JoinJsTask.class);
	private static JoinJsService joinjsservice = new JoinJsService();
	@Override
	public void execute() {
		logger.info("��ʼ����ÿ�¼���������~~~");
		//��ѯ���еļ����û�
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		logger.info(time);
		String y = time.substring(8, 10);
		if("01".equals(y)){
			List<DataRow> list = new ArrayList();
			list = joinjsservice.findJoin();
			if(list.size() > 0){
				for (DataRow dataRow : list) {
					int groupid = dataRow.getInt("groupid");
					int userid = dataRow.getInt("userid");
					List<DataRow> list2 = new ArrayList();
					BigDecimal sum = new BigDecimal("0");
					BigDecimal lv = new BigDecimal("0.004");
					list2 = joinjsservice.findJoinList(groupid);//��ѯ������û��б�
					if(list2.size() > 0){
						for (DataRow dataRow2 : list2) {
							String wxid = dataRow2.getString("wxid");
							//ÿ��΢��id�µ������û�
							List<DataRow> userlist = new ArrayList();
							userlist = joinjsservice.finduserlist(wxid);
							if(userlist.size() > 0){
								for (DataRow dataRow3 : userlist) {
									String uid = dataRow3.getString("id");
									//��ѯ��ǰ�û�������Ͷ���ܶ�   ���ڲ��������
//									BigDecimal sum1 = new BigDecimal("0");
									String DC = joinjsservice.getDCSum(uid);
//									String HQ = joinjsservice.getHQSum(uid);
//									logger.info("DC:"+DC+",HQ:"+HQ);
									BigDecimal D = new BigDecimal(DC);
//									BigDecimal H = new BigDecimal(HQ);
//									sum1 = D.add(H);
									sum = sum.add(D);
								}
							}
						}
					}
					logger.info(sum);
					
					DataRow data = new DataRow();
					data.set("userid", userid);
					data.set("cunliang", sum.doubleValue());
					
					sum = sum.multiply(lv);
					double z = sum.doubleValue();
					logger.info(z);
					if(z >= 0.005){
						//��ȡ��ǰ�û���Ϣ
//						DataRow row = joinjsservice.findUserById(userid);
//						double yue = row.getDouble("usablesum");
//						yue = yue + z;
//						row.set("usablesum", yue);
//						joinjsservice.updateUser(row);
//						DataRow x = new DataRow();
						data.set("time", time);
						data.set("money", z);
//						x.set("money", z);
//						x.set("userid", userid);
//						x.set("type", 3);
//						x.set("time", time);
//						joinjsservice.insertzhcz(x);
						joinjsservice.insertyue(data);
					}
				}
			}
		}
		logger.info("����ÿ�¼������������~~~");
	}
	public static void main(String[] args) {
		new JoinJsTask().execute();
	}
}
