package com.zs.pms.service;

import java.util.List;

import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tadvert;
import com.zs.pms.vo.QueryTadvert;

public interface AdvertService {
	
			//通过条件查询
			public List<Tadvert> queryByCon(QueryTadvert query);
			//查分页
			public List<Tadvert> queryByPage(QueryTadvert query,int page);
			//根据主键查询
			public Tadvert queryById(int id);
			//批量删除
			public void deleteByIds(int[] ids);
			//修改
			public void update(Tadvert advert)throws AppException;
			//新增
			public int insert(Tadvert advert)throws AppException;
			//删除一条
			public void delete(int id)throws AppException;
			//查询总数
			public int queryCount(QueryTadvert query);
			//获得总页数
			public int queryPageCount(QueryTadvert query);
}
