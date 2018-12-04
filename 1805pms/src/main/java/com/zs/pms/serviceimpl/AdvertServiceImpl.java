package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.AdvertDao;
import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tadvert;
import com.zs.pms.service.AdvertService;
import com.zs.pms.util.Constants;
import com.zs.pms.vo.QueryTadvert;


@Service //实现服务
@Transactional //该业务支持事务
public class AdvertServiceImpl implements AdvertService{
	@Autowired //注入
	private AdvertDao addao;
	
	//通过条件查询
	public List<Tadvert> queryByCon(QueryTadvert query) {
		// TODO Auto-generated method stub
		return addao.queryByCon(query);
	}


	//查分页
	public List<Tadvert> queryByPage(QueryTadvert query, int page) {
		//将当前页设置到条件中
		query.setPage(page);
		//可以设置起始和截止
		return addao.queryByPage(query);
	}


	//根据主键查询
	public Tadvert queryById(int id) {
		// TODO Auto-generated method stub
		return addao.queryById(id);
	}


	//修改
	@Transactional(rollbackFor=Exception.class)
	public void update(Tadvert advert) throws AppException {
		// TODO Auto-generated method stub
		addao.update(advert);
	}


	//新增
	@Transactional(rollbackFor=Exception.class)
	public int insert(Tadvert advert) throws AppException {
		// TODO Auto-generated method stub
		return addao.insert(advert);
	}


	//根据条件查询
	public int queryCount(QueryTadvert query) {
		// TODO Auto-generated method stub
		return addao.queryCount(query);
	}


	//获得总页数
	public int queryPageCount(QueryTadvert query) {
		//获得总条数
		int count=addao.queryCount(query);
		//能整除
		if (count%Constants.PAGECOUNT==0) {
			return count/Constants.PAGECOUNT;
		}//不能整除
		else {
			return count/Constants.PAGECOUNT+1;
		}
	}


	//批量删除
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		addao.deleteByIds(ids);
	}


	//删除一条
	public void delete(int id) throws AppException {
		// TODO Auto-generated method stub
		addao.delete(id);
	}
}

