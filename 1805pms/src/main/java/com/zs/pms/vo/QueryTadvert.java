package com.zs.pms.vo;

public class QueryTadvert extends queryPage{
	private String title;//标题
	private int isenabled;//是否可用
	
	public int getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
