package com.zs.pms.service;

import java.util.List;

import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tarticle;
import com.zs.pms.po.Tchannel;
import com.zs.pms.vo.QueryTarticle;

public interface ArticleService {
	
			//通过条件查询
			public List<Tarticle> queryByCon(QueryTarticle query);
			//查分页
			public List<Tarticle> queryByPage(QueryTarticle query,int page);
			//根据主键查询
			public Tarticle queryById(int id);
			//批量删除
			public void deleteByIds(int[] ids);
			//修改
			public void update(Tarticle article)throws AppException;
			//新增
			public int insert(Tarticle article)throws AppException;
			//删除一条
			public void delete(int id)throws AppException;
			//查询总数
			public int queryCount(QueryTarticle query);
			//获得总页数
			public int queryPageCount(QueryTarticle query);
}
