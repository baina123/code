package com.zs.pms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tadvert;
import com.zs.pms.service.AdvertService;
import com.zs.pms.vo.QueryTadvert;

@Controller  //是一个控制器
public class AdvertController {
	@Autowired
	AdvertService ads;
	
	@RequestMapping("/advert/list.do")//二级URL
	public String list(QueryTadvert query,String page,ModelMap map){
		//page是空
		if (page==null||"".equals(page)) {
			//默认第一页
			page="1";
		}
		//带回分页数据
		map.addAttribute("LIST",ads.queryByPage(query, Integer.parseInt(page)));
		//带回总页数
		map.addAttribute("PAGECOUNT",ads.queryPageCount(query));
		//回带当前页数
		map.addAttribute("PAGE",page);
		//回带查询条件
		map.addAttribute("QUERY",query);
		//返回advert/list.jsp
		return "advert/list";	
	}
	@RequestMapping("/advert/toadd.do")
	public String toadd(ModelMap map){

				return "advert/add";	
	}
	@RequestMapping("/advert/add.do")
	public String add(Tadvert advert,ModelMap map,HttpSession session){
	try {
		//获得session中的广告信息
		Tadvert tadvert=(Tadvert)session.getAttribute("ADVERT");
		advert.setCreator(tadvert.getId());//创建人
		advert.setIsenabled(1);//推荐
		ads.insert(advert);
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
		@RequestMapping("/advert/delete.do")
		public String delete(int id){
			try {
				ads.delete(id);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:list.do";
		}
		//删除多条
		@RequestMapping("/advert/deletes.do")
		public String deletes(int[] ids){
				ads.deleteByIds(ids);;
			return "redirect:list.do";
		}
		//修改
		@RequestMapping("/advert/get.do")
		public String get(int id,ModelMap map){
			Tadvert advert = ads.queryById(id);
			map.addAttribute("advert",advert);
		return "advert/update";
		}
		@RequestMapping("advert/update.do")
		public String update(Tadvert advert,HttpSession session,ModelMap map){
			//获得session中的广告信息
			Tadvert tadvert=(Tadvert)session.getAttribute("advert");
			advert.setUpdator(tadvert.getId());
			try {
				ads.update(advert);
				return "redirect:list.do";
			} catch (AppException e) {
				map.addAttribute("MSG",e.getErrMsg());
				return get(advert.getId(), map);
			}
		}
}
