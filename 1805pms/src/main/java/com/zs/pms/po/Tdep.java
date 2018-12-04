package com.zs.pms.po;

import java.io.Serializable;
/*	部门表
 * */
public class Tdep implements Serializable{

	/**
	 * 网络唯一标识
	 */
	private static final long serialVersionUID = -7546411475002623082L;
	private int id;
	private String dname;
	private int pid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setPname(String dname) {
		this.dname = dname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
}
