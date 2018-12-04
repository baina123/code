package com.zs.pms.controller;

import java.util.Date;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tuser;
import com.zs.pms.service.UserService;
import com.zs.pms.util.DateUtil;
import com.zs.pms.vo.QueryUser;


/*	控制器
 * */

@Controller  //是一个控制器
/*	登录页和主页
 * */
public class FrameController {
	@Autowired
	UserService us;//注入业务
	
	@RequestMapping("/tologin.do")
	/*	去登录页
	 * */
	public String toLogin(){
		return "login";	
	}
	
	@RequestMapping("/login.do")
	/*	检测登录
	 * query:登录名和密码
	 * map:带回数据
	 * return:返回页面
	 * session:产生会话
	 * code:验证码
	 * */
	public String login(QueryUser query,ModelMap map,HttpSession session,String code){
		//验证码验证
		//从session中取得kaptcha生成的验证码
		String ocode=(String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//验证码不同
		if (!ocode.equals(code)) {
			//页面回带信息
			map.addAttribute("MSG","验证码输入有误，请重新输入");
			//回到登录页面
			return "login";
		}
		//验证码相同，校验登录
		Tuser user;
		
		try {
			user=us.chkLogin(query);
			session.setAttribute("CUSER", user);
			//当前日期
			session.setAttribute("TODAY",DateUtil.getStrDate(new Date()));
//			map.addAttribute("TUSER", user);
//			List<Tpermission> permissions=user.getMenu();
//			session.setAttribute("PERMISSIONS", permissions);
			return "main";
			//业务异常
		} catch (AppException e) {
			//页面带信息
			map.addAttribute("MSG",e.getErrMsg());
			//回到登录页面
			return "login";
			//系统异常
		}catch (Exception e) {
			e.printStackTrace();
			return "error";	
		}
		
	}
	
	@RequestMapping("/toleft.do")
	/*	去左侧菜单
	 * */
	public String toLeft(){
		return "left";	
	}
	@RequestMapping("/totop.do")
	/*	去top页
	 * */
	public String toTop(){
		return "top";	
	}
	@RequestMapping("/toright.do")
	/*	去右侧页面
	 * */
	public String toRight(){
		return "right";	
	}
}
