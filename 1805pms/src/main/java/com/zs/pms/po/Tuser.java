package com.zs.pms.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zs.pms.util.DateUtil;
/*	用户表
 * */
public class Tuser implements Serializable{

	/**
	 * 网络唯一标识
	 */
	private static final long serialVersionUID = -2688565154950166939L;
	private int id;//用户id
	private String loginname;//登录名
	private String password;//密码
	private String sex;//性别
	private Date birthday;//出生日期
	private String email;//邮箱
	private Tdep dept;//部门 关联对象 一对一
	
	private String realname;
	private int creator;
	private Date creatime;
	private int updator;
	private Date updatime;
	private String pic;
	private int isenabled;
	//显示字段
	private String isenabledTxt;
	private String birthdayTxt;
	public String getBirthdayTxt() {
		return DateUtil.getStrDate(birthday);
	}

	public void setBirthdayTxt(String birthdayTxt) {
		this.birthdayTxt = birthdayTxt;
	}

	public String getIsenabledTxt() {
		if (isenabled==1) {
			return "可用";
		}else {
			return "不可用";	
		}
		
	}
	
	public int getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}
	
	//关联权限列表
	private List<Tpermission> permissions;
	//左侧菜单，由permissions整理出来
	private List<Tpermission> menu=new ArrayList<>();
	//整理菜单
	public List<Tpermission> getMenu() {
		//清空
		menu.clear();
		for(Tpermission per1:permissions){
			//一级菜单
			if(per1.getPid()==0){
				//清空
				per1.getChildren().clear();
				//装载一级菜单下二级菜单
				for(Tpermission per2:permissions){
					//一级菜单的id==二级菜单的pid
					//说明该权限是一级菜单的子权限
					if (per1.getId()==per2.getPid()) {
						per1.getChildren().add(per2);
					}
				}
				//把装载好的一级菜单放入菜单中
				menu.add(per1);
			}
		}
		return menu;
	}
	public List<Tpermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Tpermission> permissions) {
		this.permissions = permissions;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Tdep getDept() {
		return dept;
	}
	public void setDept(Tdep dept) {
		this.dept = dept;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
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
	public Date getUpdatime() {
		return updatime;
	}
	public void setUpdatime(Date updatime) {
		this.updatime = updatime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	

}
