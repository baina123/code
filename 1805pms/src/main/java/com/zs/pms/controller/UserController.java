package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tdep;
import com.zs.pms.po.Tuser;
import com.zs.pms.service.DepService;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

/*	用户控制器
 * */

@Controller  //是一个控制器
public class UserController {
	@Autowired
	UserService us;//注入业务 用户服务
	@Autowired
	DepService ds;//部门服务
	@RequestMapping("/user/list.do")//二级URL
	public String list(QueryUser query,String page,ModelMap map){
		//page是空
		if (page==null||"".equals(page)) {
			//默认第一页
			page="1";
		}
		//带回分页数据
		map.addAttribute("LIST",us.queryByPage(query, Integer.parseInt(page)));
		//带回总页数
		map.addAttribute("PAGECOUNT",us.queryPageCount(query));
		//回带当前页数
		map.addAttribute("PAGE",page);
		//回带查询条件
		map.addAttribute("QUERY",query);
		//返回user/list.jsp
		return "user/list";	
	}
	
	/*	以ajax的方式响应
	 * 方法返回string 直接返回文本
	 * 方法返回对象 返回json格式 自动调用JSONArray
	 * */
	@RequestMapping("/user/getdep.do")
	@ResponseBody
	public List<Tdep> getDept(int pid){
		List<Tdep> list = ds.queryByPid(pid);
		return list;	
	}
	@RequestMapping("/user/toadd.do")
	public String toadd(ModelMap map){
		//获得一级部门列表
		List<Tdep> list1=ds.queryByPid(0);
		map.addAttribute("DLIST",list1);
		//获得默认一级部门下的二级部门列表
		List<Tdep> list2=ds.queryByPid(list1.get(0).getId());
		map.addAttribute("DLIST2",list2);
		return "user/add";	
	}
	@RequestMapping("/user/add.do")
	public String add(Tuser user,ModelMap map,HttpSession session){
	try {
		//获得session中的用户信息
		Tuser cuser=(Tuser)session.getAttribute("CUSER");
		user.setCreator(cuser.getId());//创建人
		user.setIsenabled(1);//可用
			us.insert(user);
			//跳转到指定url上，不需要传参
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			map.addAttribute("MSG",e.getErrMsg());
			//执行方法，传参
			return this.toadd(map);
		}
	}
	//删除一条
	@RequestMapping("/user/delete.do")
	public String delete(int id){
		try {
			us.delete(id);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:list.do";
	}
	//删除多条
	@RequestMapping("/user/deletes.do")
	public String deletes(int[] ids){
			us.deleteByIds(ids);;
		return "redirect:list.do";
	}
	//修改
	@RequestMapping("/user/get.do")
	public String get(int id,ModelMap map){
		Tuser user = us.queryById(id);
		map.addAttribute("USER",user);
		//获得一级部门列表
		List<Tdep> list1=ds.queryByPid(0);
		map.addAttribute("DLIST",list1);
		//获得该用户的一级部门下的二级部门列表
		List<Tdep> list2=ds.queryByPid(user.getDept().getPid());
		map.addAttribute("DLIST2",list2);
		return "user/update";
	}
	@RequestMapping("user/update.do")
	public String update(Tuser user,HttpSession session,ModelMap map){
		//获得session中的用户信息
		Tuser cuser=(Tuser)session.getAttribute("CUSER");
		user.setUpdator(cuser.getId());
		try {
			us.update(user);
			return "redirect:list.do";
		} catch (AppException e) {
			map.addAttribute("MSG",e.getErrMsg());
			return get(user.getId(), map);
		}
	}
}	
	
