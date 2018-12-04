package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.UserDao;
import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tuser;
import com.zs.pms.service.UserService;
import com.zs.pms.util.Constants;
import com.zs.pms.util.MD5;
import com.zs.pms.vo.QueryUser;

/*user 服务实现
 * */
@Service //实现服务
@Transactional //该业务支持事务
public class UserServiceImpl implements UserService{
	@Autowired //注入
	private UserDao udao;
	@Override
	public Tuser chkLogin(QueryUser query) throws AppException {
//		//密码加密
//		MD5 md5=new MD5();
//		String p1=md5.getMD5ofStr(query.getPassword());
//		query.setPassword(p1);
		List<Tuser> list = udao.queryByCon(query);
		//没有用户
		if (list==null||list.size()!=1) {
			throw new AppException(1000,"用户名或密码输入错误,请重新输入");
		}
		//获得第一条
		Tuser user = list.get(0);
		//关联权限列表返回
		return udao.queryById(user.getId());
	}

	@Override
	//根据条件查询
	public List<Tuser> queryByCon(QueryUser query) {
		// TODO Auto-generated method stub
		return udao.queryByCon(query);
	}

	@Override
	//批量删除
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		udao.deleteByIds(ids);
	}

	@Override
	//修改
	@Transactional(rollbackFor=Exception.class)//有异常就回滚否则就提交
	public void update(Tuser user) throws AppException{
		//不可用
		if (user.getIsenabled()==-1) {
			throw new AppException(1003,"不能修改不可用用户");
		}
		//获得原来用户
		Tuser tuser=udao.queryById(user.getId());
		//原密码不等于新密码才加密
		if (user.getPassword()!=null&&(!"".equals(user.getPassword()))&&!user.getPassword().equals(tuser.getPassword())) {
			//密码加密
			MD5 md5=new MD5();
			user.setPassword(md5.getMD5ofStr(user.getPassword()));
		}
		udao.update(user);
	}

	
	//新增
	@Transactional(rollbackFor=Exception.class) //有异常就回滚否则提交
	public int insert(Tuser user) throws AppException {
//		//密码加密
//		MD5 md5=new MD5();
//		user.setPassword(md5.getMD5ofStr(user.getPassword()));
//		
//		udao.insert(user); //回滚 新增不成功
//		int a=1/0;//抛异常
		//登录名=admin
		if ("admin".equals(user.getLoginname())) {
			throw new AppException(1002,"登录名不能为admin");
		}
		return udao.insert(user);
	}


	//删除一条
	public void delete(int id) throws AppException {
		// TODO Auto-generated method stub
		udao.delete(id);
	}

	@Override
	//获得一条
	public Tuser queryById(int id) {
		// TODO Auto-generated method stub
		return udao.queryById(id);
	}

	
	/*	query:条件   page:当前页
	 * */
	public List<Tuser> queryByPage(QueryUser query, int page) {
		//将当前页设置到条件中
		query.setPage(page);
		//可以设置起始和截止
		return udao.queryByPage(query);
	}

	
	/*	计算总页数
	 * */
	public int queryPageCount(QueryUser query) {
		//获得总条数
		int count=udao.queryCount(query);
		//能整除
		if (count%Constants.PAGECOUNT==0) {
			return count/Constants.PAGECOUNT;
		}//不能整除
		else {
			return count/Constants.PAGECOUNT+1;
		}
	}
}
