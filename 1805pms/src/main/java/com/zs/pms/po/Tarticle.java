package com.zs.pms.po;

import java.io.Serializable;
import java.util.Date;

import com.zs.pms.util.DateUtil;

public class Tarticle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4078079948168721040L;
	private int id;//文章ID
	private String title;//标题
	private String content;//内容
	private String author;//作者
	private Date crtime;//添加日期
	private Tchannel channel;//所属频道   关联对象 一对一
	private int isremod;//是否推荐
	private int ishot;//是否热点
	private  int creator;//创建人
	private  int updator;//修改人
	private Date updtime;//修改时间
	//显示字段
	private String ishotTxt;
	private String isremodTxt;
	private String crtimeTxt;
	
	public String getIshotTxt() {
		if (ishot==1) {
			return "热点";
		}else{
		return "不是热点";
		}
	}
	public void setIshotTxt(String ishotTxt) {
		this.ishotTxt = ishotTxt;
	}
	public String getIsremodTxt() {
		if (isremod==1) {
			return "推荐";
		}else{
		return "不推荐";
		}
	}
	public void setIsremodTxt(String isremodTxt) {
		this.isremodTxt = isremodTxt;
	}
	public String getCrtimeTxt() {
		return DateUtil.getStrDate(crtime);
	}
	public void setCrtimeTxt(String crtimeTxt) {
		this.crtimeTxt = crtimeTxt;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCrtime() {
		return crtime;
	}
	public void setCrtime(Date crtime) {
		this.crtime = crtime;
	}
	public Tchannel getChannel() {
		return channel;
	}
	public void setChannel(Tchannel channel) {
		this.channel = channel;
	}
		public int getIsremod() {
		return isremod;
	}
	public void setIsremod(int isremod) {
		this.isremod = isremod;
	}
	public int getIshot() {
		return ishot;
	}
	public void setIshot(int ishot) {
		this.ishot = ishot;
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
	public Date getUpdtime() {
		return updtime;
	}
	public void setUpdtime(Date updtime) {
		this.updtime = updtime;
	}
}
