package com.zs.pms.po;

import java.io.Serializable;
import java.util.Date;

import com.zs.pms.util.DateUtil;

public class Tadvert implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899348434695785584L;
	private int id;//广告ID
	private String title;//标题
	private String content;//内容
    private Date crtime;//添加日期
    private String crman;//创建人
    private int creator;
	private int updator;//修改人
    private Date updatime;//修改时间
    private int isenabled;//是否可用
    private String isenabledTxt;
    private String crtimeTxt;
    
    public String getCrtimeTxt() {
		return DateUtil.getStrDate(crtime);
	}
	public void setCrtimeTxt(String crtimeTxt) {
		this.crtimeTxt = crtimeTxt;
	}
	public String getIsenabledTxt() {
    	if (isenabled==1) {
			return "可用";
		}else{
		return "不可用";
		}
	}
	public void setIsenabledTxt(String isenabledTxt) {
		this.isenabledTxt = isenabledTxt;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public int getUpdator() {
		return updator;
	}
	public void setUpdator(int updator) {
		this.updator = updator;
	}
	public int getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}
    
	public Date getUpdatime() {
		return updatime;
	}
	public void setUpdatime(Date updatime) {
		this.updatime = updatime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCrtime() {
		return crtime;
	}
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}
	public String getCrman() {
		return crman;
	}
	public void setCrman(String crman) {
		this.crman = crman;
	}
}
