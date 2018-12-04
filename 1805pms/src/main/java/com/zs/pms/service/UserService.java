package com.zs.pms.service;

import java.util.List;

import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tuser;
import com.zs.pms.vo.QueryUser;

public interface UserService {
	//登录
	public Tuser chkLogin(QueryUser query)throws AppException;
	//通过条件查询
	public List<Tuser> queryByCon(QueryUser query);
	//批量删除
	public void deleteByIds(int[] ids);
	//修改
	public void update(Tuser user)throws AppException;
	//新增
	public int insert(Tuser user)throws AppException;
	//删除一条
	public void delete(int id)throws AppException;
	//获得一条
	public Tuser queryById(int id);
	//查分页
	public List<Tuser> queryByPage(QueryUser query,int page);
	//获得总页数
	public int queryPageCount(QueryUser query);
}
