package com.project.bean;

/**
 * zTree���νṹ����VO��
 * 
 * @author Administrator
 * 
 */
public class TreeVO {
  private String id;//�ڵ�id
  private String pId;//���ڵ�pId I�����д
  private String name;//�ڵ�����
  private String open = "false";//�Ƿ�չ�����ڵ㣬Ĭ�ϲ�չ��
  private String url;  //����·���������洢ID
  
 
  public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getpId() {
    return pId;
  }

  public void setpId(String pId) {
    this.pId = pId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOpen() {
    return open;
  }

  public void setOpen(String open) {
    this.open = open;
  }

}
