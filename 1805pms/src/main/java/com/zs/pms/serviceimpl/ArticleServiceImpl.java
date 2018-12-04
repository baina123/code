package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.ArticleDao;
import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tarticle;
import com.zs.pms.service.ArticleService;
import com.zs.pms.util.Constants;
import com.zs.pms.vo.QueryTarticle;

@Service //实现服务
@Transactional //该业务支持事务
public class ArticleServiceImpl implements ArticleService{
	@Autowired //注入
	private ArticleDao ardao;
	
	
	//通过条件查询
	public List<Tarticle> queryByCon(QueryTarticle query) {
		return ardao.queryByCon(query);
	}

	
	//查分页
	public List<Tarticle> queryByPage(QueryTarticle query,int page) {
		//将当前页设置到条件中
		query.setPage(page);
		//可以设置起始和截止
		return ardao.queryByPage(query);
		
	}

	//根据主键查询
	public Tarticle queryById(int id) {
		// TODO Auto-generated method stub
		return ardao.queryById(id);
	}

	//批量删除
	@Transactional(rollbackFor=Exception.class)
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		ardao.deleteByIds(ids);
	}

	//修改
	@Transactional(rollbackFor=Exception.class)
	public void update(Tarticle article) throws AppException{
		// TODO Auto-generated method stub
		ardao.update(article);
	}

	//新增
	@Transactional(rollbackFor=Exception.class)
	public int insert(Tarticle article) throws AppException{
		// TODO Auto-generated method stub
		return ardao.insert(article);
	}

	//删除一条
	public void delete(int id) throws AppException{
		// TODO Auto-generated method stub
		ardao.delete(id);
	}

	//根据条件查询
	public int queryCount(QueryTarticle query) {
		// TODO Auto-generated method stub
		return ardao.queryCount(query);
	}


	//获得总页数
	public int queryPageCount(QueryTarticle query) {
		//获得总条数
		int count=ardao.queryCount(query);
		//能整除
		if (count%Constants.PAGECOUNT==0) {
				return count/Constants.PAGECOUNT;
		}//不能整除
		else {
			return count/Constants.PAGECOUNT+1;
		}
	}

}

