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
import com.zs.pms.service.ArticleService;
import com.zs.pms.service.ChannelService;
import com.zs.pms.vo.QueryTarticle;

@Controller  //是一个控制器
public class ArticleController {
	@Autowired
	ArticleService ars;
	@Autowired
	ChannelService cs;
	@RequestMapping("/article/list.do")//二级URL
	public String list(QueryTarticle query,String page,ModelMap map){
		//page是空
		if (page==null||"".equals(page)) {
			//默认第一页
			page="1";
		}
		//带回分页数据
		map.addAttribute("LIST",ars.queryByPage(query, Integer.parseInt(page)));
		//带回总页数
		map.addAttribute("PAGECOUNT",ars.queryPageCount(query));
		//回带当前页数
		map.addAttribute("PAGE",page);
		//回带查询条件
		map.addAttribute("QUERY",query);
		//返回article/list.jsp
		return "article/list";	
	}
	/*	以ajax的方式响应
	 * 方法返回string 直接返回文本
	 * 方法返回对象 返回json格式 自动调用JSONArray
	 * */
	@RequestMapping("/article/getchannel.do")
	@ResponseBody
	public List<Tchannel> getChannel(int pid){
		List<Tchannel> list=cs.queryByPid(pid);
		return list;	
	}
	@RequestMapping("/article/toadd.do")
	public String toadd(ModelMap map){
		//获得一级频道
//		List<Tchannel> list1=cs.queryByPid(0);
//		map.addAttribute("DLIST",list1);
//		//获得默认一级频道下的二级频道
//		List<Tchannel> list2=cs.queryByPid(list1.get(0).getId());
//		map.addAttribute("DLIST2",list2);
				return "article/add";	
	}
	@RequestMapping("/article/add.do")
	public String add(Tarticle article,ModelMap map,HttpSession session){
	try {
		//获得session中的文章信息
		Tarticle tarticle=(Tarticle)session.getAttribute("ARTICLE");
		article.setCreator(tarticle.getId());//创建人
		article.setIsremod(1);//推荐
		article.setIshot(1);//热点
		ars.insert(article);
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
		@RequestMapping("/article/delete.do")
		public String delete(int id){
			try {
				ars.delete(id);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:list.do";
		}
		//删除多条
		@RequestMapping("/article/deletes.do")
		public String deletes(int[] ids){
				ars.deleteByIds(ids);;
			return "redirect:list.do";
		}
		//修改
		@RequestMapping("/article/get.do")
		public String get(int id,ModelMap map){
			Tarticle article = ars.queryById(id);
			map.addAttribute("ARTICLE",article);
//			//获得一级频道
//			List<Tchannel> list1=cs.queryByPid(0);
//			map.addAttribute("DLIST",list1);
//			//获得默认一级频道下的二级频道
//			List<Tchannel> list2=cs.queryByPid(list1.get(0).getId());
//			map.addAttribute("DLIST2",list2);
			return "article/update";
		}
		@RequestMapping("article/update.do")
		public String update(Tarticle article,HttpSession session,ModelMap map){
			//获得session中的文章信息
			Tarticle tarticle=(Tarticle)session.getAttribute("article");
			article.setUpdator(tarticle.getId());
			try {
				ars.update(article);
				return "redirect:list.do";
			} catch (AppException e) {
				map.addAttribute("MSG",e.getErrMsg());
				return get(article.getId(), map);
			}
		}
}
