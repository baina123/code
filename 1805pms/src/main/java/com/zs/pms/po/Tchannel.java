package com.zs.pms.po;

import java.io.Serializable;
import java.util.Date;

import com.zs.pms.util.DateUtil;

public class Tchannel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 248321059005526985L;
	private int id;//频道id
	private String cname;//频道
	private int pid;//上级频道
	private int lev;//
	private int isleaf;//是否叶子
	private int sort;//顺序
	private  int creator;//创建人
	private Date creatime;//创建时间
	private  int updator;//修改人
	private Date updtime;//修改时间
	private String levTxt;
	private String isleafTxt;
	private String creatimeTxt;
	
	public String getLevTxt() {
		if (lev==1) {
			return "一级";
		}else{
		return "二级";
		}
	}
	public void setLevTxt(String levTxt) {
		this.levTxt = levTxt;
	}
	public String getIsleafTxt() {
		if (isleaf==1) {
			return "叶子";
		}else{
		return "不是叶子";
		}
	}
	public void setIsleafTxt(String isleafTxt) {
		this.isleafTxt = isleafTxt;
	}
	public String getCreatimeTxt() {
		return DateUtil.getStrDate(creatime);
	}
	public void setCreatimeTxt(String creatimeTxt) {
		this.creatimeTxt = creatimeTxt;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public Date getCreatime() {
		return creatime;
	}
	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}
	public int getUpdator() {
		return updator;
	}
	public void setUpdator(int updator) {
		this.updator = updator;
	}
	public Date getUpdtime() {
		return updtime;
	}
	public void setUpdtime(Date updtime) {
		this.updtime = updtime;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}

}
