package com.zs.pms.vo;

public class QueryTchannel extends queryPage{
	private String cname;
	private int isleaf;
	
	public int getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
