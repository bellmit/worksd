package root.role;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.project.bean.TreeVO;
import com.project.service.role.PowerService;
import com.thinkive.base.jdbc.DataRow;
import com.thinkive.web.base.ActionResult;
import com.thinkive.web.base.BaseAction;

public class PowerAction extends BaseAction{
	private static Logger logger = Logger.getLogger(PowerAction.class);
	private static PowerService powerService=new PowerService();

	/**
	 * �õ���½�û���һ��Ȩ��
	 * @return
	 */
	public ActionResult doGetUserPowerMain(){
		HttpServletRequest request = getRequest();
		int roleId= Integer.valueOf(request.getParameter("roleid"));
		List<DataRow> list=powerService.getPowerListByRoleId(roleId);
		JSONArray jsArray=JSONArray.fromObject(list);
		this.getWriter().write(jsArray.toString());
		return null;
	}
	
	/**
	 * �����û���ID�ͽ�ɫ�������Ӧһ���µ��Ӳ˵�
	 * @return
	 */
	public ActionResult doGetUserPower(){
		HttpServletRequest request = getRequest();
		logger.info(request);
		int roleMain = Integer.valueOf(request.getParameter("roleMain"));
		int roleId= Integer.valueOf(request.getParameter("roleid"));
		//��������
		List<DataRow> list=powerService.getUserPower(roleId, roleMain);
		JSONArray isArray=JSONArray.fromObject(list);
		this.getWriter().write(isArray.toString());
		return null;
	}
	
	/**
	 * �õ����е��û�Ȩ�޻���ݽ�ɫ��������Ӧ��Ȩ��
	 * @return
	 */
	public ActionResult doGetAllPower(){
		List<DataRow> listMain=powerService.getAllPower(0);
		List<TreeVO> treePList=new ArrayList<TreeVO>();
		for (int i = 0; i < listMain.size(); i++) {
				DataRow dr=listMain.get(i);
				//����һ���˵�
				TreeVO vo=new TreeVO();
				int id=i+1;
				String pid=String.valueOf(id);
				vo.setId(pid);
				vo.setName(dr.getString("powername"));
				vo.setOpen("false");
				vo.setpId("0");
				vo.setUrl(String.valueOf(dr.getInt("id")));
				treePList.add(vo);
				List<DataRow> listSon=powerService.getAllPower(dr.getInt("id"));  //����һ���˵��õ������˵�
				for (int j = 0; j < listSon.size(); j++) {  
					DataRow drj=listSon.get(j);
					TreeVO vo1=new TreeVO();
					int id1=j+1;
					String pid1=pid+String.valueOf(id1);
					vo1.setId(pid1);
					vo1.setName(drj.getString("powername"));
					vo1.setOpen("false");
					vo1.setpId(pid);
					vo1.setUrl(String.valueOf(drj.getInt("id")));
					treePList.add(vo1);
				}
			}
		JSONArray isArray=JSONArray.fromObject(treePList);
		this.getWriter().write(isArray.toString());
		return null;
	}
	
	/**
	 * ���ݽ�ɫ��������Ӧ��Ȩ��
	 * @return
	 */
	public ActionResult doGetPowerByRole(){
		int roleId=getIntParameter("roleId");
		List<DataRow> listMain=this.mianPowerByRole(roleId);
		List<TreeVO> treePList=new ArrayList<TreeVO>();
		for (int i = 0; i < listMain.size(); i++) {
				DataRow dr=listMain.get(i);
				//����һ���˵�
				TreeVO vo=new TreeVO();
				int id=i+1;
				String pid=String.valueOf(id);
				vo.setId(pid);
				vo.setName(dr.getString("powername"));
				vo.setOpen("false");
				vo.setpId("0");
				treePList.add(vo);
				List<DataRow> listSon=this.sonPowerByRoleAndPower(roleId,dr.getInt("id"));  //����һ���˵��õ������˵�
				for (int j = 0; j < listSon.size(); j++) {  
					DataRow drj=listSon.get(j);
					TreeVO vo1=new TreeVO();
					int id1=j+1;
					String pid1=pid+String.valueOf(id1);
					vo1.setId(pid1);
					vo1.setName(drj.getString("powername"));
					vo1.setOpen("false");
					vo1.setpId(pid);
					treePList.add(vo1);
				}
			}
		JSONArray isArray=JSONArray.fromObject(treePList);
		this.getWriter().write(isArray.toString());
		return null;
	}
	
	/**
	 * �޸�Ȩ��
	 * @return
	 */
	public ActionResult doAddMenuQx(){
		JSONObject jsonObject = new JSONObject();
		String json_str=getStrParameter("json_str");
		int roleId=getIntParameter("role_id");
		//��ɾ�������ɫ�йص����м�¼
		Boolean bool=powerService.deleteQRguanxi(roleId);
		if(bool==true){
			//Ȼ�������������ɫ��Ȩ��
			String[] qxid=json_str.split(",");
			for (int i = 0; i < qxid.length; i++) {
				DataRow dr=new DataRow();
				dr.set("rid",roleId);
				dr.set("pid", Integer.valueOf(qxid[i]));
				Boolean bo=powerService.addQRguanxi("sdcms_user_rp", dr);
				if(bo==false){
					jsonObject.put("data", 1);
				}
			}
		}else{
			jsonObject.put("data", 1);
		}
		this.getWriter().write(jsonObject.toString());
		return null;
	}
	
	public List<DataRow> mianPowerByRole(int roleId){
		List<DataRow> list=powerService.getPowerListByRoleId(roleId);
		return list;
	}
	
	public List<DataRow> sonPowerByRoleAndPower(int roleId,int roleMain){
		List<DataRow> list=powerService.getUserPower(roleId, roleMain);
		return list;
	}
}

