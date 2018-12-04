package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.Tadvert;
import com.zs.pms.vo.QueryTadvert;

public interface AdvertDao {
		//通过条件查询
		public List<Tadvert> queryByCon(QueryTadvert query);
		//查分页
		public List<Tadvert> queryByPage(QueryTadvert query);
		//根据主键查询
		public Tadvert queryById(int id);
		//批量删除
		public void deleteByIds(int[] ids);
		//修改
		public void update(Tadvert advert);
		//新增
		public int insert(Tadvert advert);
		//删除
		public void delete(int id);
		//查询总数
		public int queryCount(QueryTadvert query);
}
