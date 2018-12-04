package com.zs.pms.po;

import java.io.Serializable;
/*	角色表
 * */
public class Trole implements Serializable{

	/**
	 * 网络唯一标识
	 */
	private static final long serialVersionUID = -7744514273436760998L;
	private int id;
	private String rname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}

}
