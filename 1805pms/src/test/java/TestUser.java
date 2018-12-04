import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tdep;
import com.zs.pms.po.Tpermission;
import com.zs.pms.po.Tuser;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml") //引入配置文件
public class TestUser {
	@Autowired
	UserService us;
	
//	@Test
	public void testlogin(){
		QueryUser query=new QueryUser();
		query.setLoginname("test1");
		query.setPassword("123456");
		try {
			Tuser user=us.chkLogin(query);
			System.out.println(user.getRealname()+","+user.getDept().getDname());
			for(Tpermission permission:user.getPermissions()){
				System.out.println(permission.getPname());
			}
			System.out.println("------------整理后-------------");
			for(Tpermission per1:user.getMenu()){
				System.out.println(per1.getPname());
				for(Tpermission per2:per1.getChildren()){
					System.out.println("\t"+per2.getPname());
				}
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getErrMsg());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
//	@Test
	public void testQuery(){
		QueryUser query=new QueryUser();
		query.setLoginname("test1");
		query.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
//		query.setIsenabled(1);
//		query.setPage(2);
		us.queryByCon(query);
	}
//	@Test
	public void testdels(){
		int[] ids={3001,3002};
		us.deleteByIds(ids);
	}
//	@Test
	public void testUpdate(){
		Tuser user=new Tuser();
		user.setId(3105);
//		user.setBirthday(new Date());
		//部门
		Tdep dep=new Tdep();
		dep.setId(6);
//		user.setDept(dep);
//		user.setEmail("qwe@123.com");
		user.setIsenabled(-1);
//		user.setPassword("4321");
//		user.setPic("aaaa.jpg");
		user.setRealname("测试员6");
		user.setSex("女");
		user.setUpdator(1001);
		try {
			us.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Test
	public void testAdd(){
		Tuser user=new Tuser();
		user.setBirthday(new Date());
		//部门
		Tdep dep=new Tdep();
		dep.setId(6);
		user.setDept(dep);
		user.setLoginname("test7");
		user.setEmail("qwe@123.com");
		user.setIsenabled(-1);
		user.setPassword("4321");
		user.setPic("aaaa.jpg");
		user.setRealname("测试员7");
		user.setSex("女");
		user.setCreator(1001);
		try {
			us.insert(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Test
	public void testPage(){
		QueryUser query=new QueryUser();
		query.setSex("男");
		System.out.println("当前总页数:"+us.queryPageCount(query));
		for (Tuser user:us.queryByPage(query,2)) {
			System.out.println(user.getRealname());
		}
	}
	@Test
	public void testdelete(){
		try {
			us.delete(3006);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
