package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.Tarticle;
import com.zs.pms.vo.QueryTarticle;

public interface ArticleDao {
		//通过条件查询
		public List<Tarticle> queryByCon(QueryTarticle query);
		//查分页
		public List<Tarticle> queryByPage(QueryTarticle query);
		//根据主键查询
		public Tarticle queryById(int id);
		//批量删除
		public void deleteByIds(int[] ids);
		//修改
		public void update(Tarticle article);
		//新增
		public int insert(Tarticle article);
		//删除
		public void delete(int id);
		//查询总数
		public int queryCount(QueryTarticle query);
		//
}
