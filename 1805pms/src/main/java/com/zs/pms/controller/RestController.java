package com.zs.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.po.Tuser;
import com.zs.pms.service.UserService;

@Controller
/*	提供restful服务
 * */
public class RestController {
	@Autowired	
	UserService us;
	@RequestMapping(value="/userinfo/{id}.do")
	//{id}占位  实际/userinfo/3080.do
	@ResponseBody //json返回
	public Tuser queryById(@PathVariable("id")int id){
		return us.queryById(id);	
	}
}
