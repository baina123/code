package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tarticle;
import com.zs.pms.po.Tchannel;
import com.zs.pms.po.Tdep;
import com.zs.pms.po.Tuser;
import com.zs.pms.service.ArticleService;
import com.zs.pms.service.ChannelService;
import com.zs.pms.vo.QueryTarticle;
import com.zs.pms.vo.QueryTchannel;

@Controller  //是一个控制器
public class ChannelController {
	@Autowired
	ChannelService cs;
	@RequestMapping("/channel/list.do")//二级URL
	public String list(QueryTchannel query,String page,ModelMap map){
		//page是空
		if (page==null||"".equals(page)) {
			//默认第一页
			page="1";
		}
		//带回分页数据
		map.addAttribute("LIST",cs.queryByPage(query, Integer.parseInt(page)));
		//带回总页数
		map.addAttribute("PAGECOUNT",cs.queryPageCount(query));
		//回带当前页数
		map.addAttribute("PAGE",page);
		//回带查询条件
		map.addAttribute("QUERY",query);
		//返回channel/list.jsp
		return "channel/list";	
	}
	/*	以ajax的方式响应
	 * 方法返回string 直接返回文本
	 * 方法返回对象 返回json格式 自动调用JSONArray
	 * */
	@RequestMapping("/channel/getchannel.do")
	@ResponseBody
	public List<Tchannel> getChannel(int pid){
		List<Tchannel> list=cs.queryByPid(pid);
		return list;	
	}
	@RequestMapping("/channel/toadd.do")
	public String toadd(ModelMap map){
		//获得频道id
//		List<Tchannel> channel=cs.queryBypid(pid);
//		map.addAttribute("CHANNEL",channel);
				return "channel/add";	
	}
	@RequestMapping("/channel/add.do")
	public String add(Tchannel channel,ModelMap map,HttpSession session){
	try {
		//获得session中的频道信息
		Tchannel tchannel=(Tchannel)session.getAttribute("CHANNEL");
		channel.setCreator(tchannel.getId());//创建人
		channel.setIsleaf(1);//
		cs.insert(channel);
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
		@RequestMapping("/channel/delete.do")
		public String delete(int id){
			try {
				cs.delete(id);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:list.do";
		}
		//删除多条
		@RequestMapping("/channel/deletes.do")
		public String deletes(int[] ids){
				cs.deleteByIds(ids);;
			return "redirect:list.do";
		}
		//修改
		@RequestMapping("/channel/get.do")
		public String get(int id,ModelMap map){
			Tchannel channel = cs.queryById(id);
			map.addAttribute("channel",channel);
//			//获得频道
//			List<Tchannel> channel=cs.queryByPid(0);
//			map.addAttribute("CHANNEL",channel);
			return "article/update";
		}
		@RequestMapping("channel/update.do")
		public String update(Tchannel channel,HttpSession session,ModelMap map){
			//获得session中的频道信息
			Tchannel tchannel=(Tchannel)session.getAttribute("channel");
			channel.setUpdator(tchannel.getId());
			try {
				cs.update(channel);
				return "redirect:list.do";
			} catch (AppException e) {
				map.addAttribute("MSG",e.getErrMsg());
				return get(channel.getId(), map);
			}
		}
}
