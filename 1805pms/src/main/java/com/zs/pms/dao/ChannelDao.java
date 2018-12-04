package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.Tchannel;
import com.zs.pms.vo.QueryTchannel;

public interface ChannelDao {
	//根据上级取频道
	public List<Tchannel> queryByPid(int pid);
	//通过条件查询
	public List<Tchannel> queryByCon(QueryTchannel query);
	//查分页
	public List<Tchannel> queryByPage(QueryTchannel query);
	//根据主键查询
	public Tchannel queryById(int id);
	//批量删除
	public void deleteByIds(int[] ids);
	//修改
	public void update(Tchannel channel);
	//新增
	public int insert(Tchannel channel);
	//删除
	public void delete(int id);
	//查询总数
	public int queryCount(QueryTchannel query);
}
