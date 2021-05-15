package com.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.service.DcStatusService;
import com.service.HongBaoService;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.timerengine.Task;

public class DcStatusTask implements Task{
	private static Logger logger = Logger.getLogger(DcStatusTask.class);
	private static DcStatusService dcstatusservice = new DcStatusService();
	private static HongBaoService hongbaoservice = new HongBaoService();
	@Override
	public void execute() {
		List<DataRow> list =  dcstatusservice.getjiamenglist();
		logger.info("��ʼ��������û��ﵽ����100��״̬");
		if(list.size() > 0){
			for (DataRow dataRow : list) {
				String userid = dataRow.getString("id");
				if(Integer.parseInt(userid) > 0){
					//��ѯ��ǰ�û��Ķ���Ͷ�ʽ���Ƿ�ﵽ100Ԫ
					if(Checkdc(userid)){
						dataRow.set("dcstatus", 1);
						hongbaoservice.updateUser(dataRow);
					}
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		logger.info("�������");
	}
	public static boolean Checkdc(String userid){
		double sumdc = dcstatusservice.getdcsum(userid);
		if(sumdc >= 100){
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		new DcStatusTask().execute();
	}
}
