package com.task;

import java.util.Date;

import org.apache.log4j.Logger;

import com.service.DealmakingService;
import com.thinkive.timerengine.Task;

public class DealmakingTask implements Task 
{

	private static Logger logger = Logger.getLogger(DealmakingTask.class);
	private static DealmakingService service = new DealmakingService();
	@Override
	public void execute() 
	{
		logger.info("��ʼִ����Ϣ��"+new Date().toLocaleString());
		//ִ�ж�����Ϣ
		service.chdc();
		//ִ�л�ȡ��Ϣ
		service.chhq();
		logger.info("����ִ����Ϣ��"+new Date().toLocaleString());
	}

	public static void main(String[] args) {
		service.chhq();
	}
}
