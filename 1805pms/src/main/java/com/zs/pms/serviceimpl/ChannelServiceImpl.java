package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.ArticleDao;
import com.zs.pms.dao.ChannelDao;
import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tchannel;
import com.zs.pms.service.ChannelService;
import com.zs.pms.util.Constants;
import com.zs.pms.vo.QueryTchannel;

@Service //实现服务
@Transactional //该业务支持事务
public class ChannelServiceImpl implements ChannelService{
	@Autowired
	ChannelDao cdao;
	@Autowired
	ArticleDao ardao;
	//通过条件查询
	public List<Tchannel> queryByCon(QueryTchannel query) {
		// TODO Auto-generated method stub
		return cdao.queryByCon(query);
	}

	//查分页
	public List<Tchannel> queryByPage(QueryTchannel query,int page) {
		//将当前页设置到条件中
		query.setPage(page);
		//可以设置起始和截止
		return cdao.queryByPage(query);
	}

	//根据主键查询
	public Tchannel queryById(int id) {
		// TODO Auto-generated method stub
		return cdao.queryById(id);
	}

	//批量删除
	@Transactional(rollbackFor=Exception.class)
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		cdao.deleteByIds(ids);
	}

	//修改
	@Transactional(rollbackFor=Exception.class)
	public void update(Tchannel channel) {
		// TODO Auto-generated method stub
		cdao.update(channel);
	}


	//删除一条
	public void delete(int id) throws AppException{
		// TODO Auto-generated method stub
		cdao.delete(id);
	}

	//根据条件查询
	public int queryCount(QueryTchannel query) {
		// TODO Auto-generated method stub
		return cdao.queryCount(query);
	}

	//获得总页数
	public int queryPageCount(QueryTchannel query) {
				//获得总条数
				int count=cdao.queryCount(query);
				//能整除
				if (count%Constants.PAGECOUNT==0) {
						return count/Constants.PAGECOUNT;
				}//不能整除
				else {
					return count/Constants.PAGECOUNT+1;
				}
			}
	

	//新增
	@Transactional(rollbackFor=Exception.class)
	public int insert(Tchannel channel) throws AppException {
		// TODO Auto-generated method stub
		return cdao.insert(channel);
	}

	@Override
	public List<Tchannel> queryByPid(int pid) {
		// TODO Auto-generated method stub
		return cdao.queryByPid(pid);
	}
	
}

