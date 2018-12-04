import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.execption.AppException;
import com.zs.pms.po.Tarticle;
import com.zs.pms.po.Tchannel;
import com.zs.pms.service.ArticleService;
import com.zs.pms.vo.QueryTarticle;

@RunWith(SpringJUnit4ClassRunner.class) //使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml") //引入配置文件
public class TestArticle {
@Autowired
ArticleService ars;
//	@Test
	public void testQuery(){
		QueryTarticle query=new QueryTarticle();
		query.setTitle("静夜思");
		query.setAuthor("李白");
		ars.queryByCon(query);
	}
//	@Test
	public void testdelete(){
		try {
		ars.delete(121);
		} catch (AppException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
//	@Test
	public void testAdd(){
		Tarticle article=new Tarticle();
		article.setTitle("编程");
		article.setContent("数据库的机会好处低见客户");
		article.setAuthor("大部分");
		Tchannel channel=new Tchannel();
		channel.setId(6);
		article.setChannel(channel);
		try {
			ars.insert(article);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testupdate(){
		Tarticle article=new Tarticle();
		article.setTitle("导航");
		article.setContent("很快就会比较好把不好解决户口");
		article.setAuthor("王大拿");
		Tchannel channel=new Tchannel();
		channel.setId(3);
		article.setChannel(channel);
		article.setUpdator(1000);
		article.setIshot(1);
		article.setIsremod(1);
		article.setId(18);
		try {
			ars.update(article);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Test
	public void testdels(){
		int[] ids={20,21,22,23};
		ars.deleteByIds(ids);
	}
//	@Test
	public void testPage(){
		QueryTarticle query=new QueryTarticle();
		List<Tarticle> queryByPage = ars.queryByPage(query, 3);
		for (Tarticle article : queryByPage) {
			System.out.println(article.getAuthor()+":"+article.getContent());
		}
	}
//	@Test
	public void testCount(){
		QueryTarticle query=new QueryTarticle();
		System.out.println("当前总页数为:"+ars.queryPageCount(query)); 
		
	}
//	@Test
	public void testId(){
		Tarticle article = ars.queryById(1);
		System.out.println(article.getContent());
	}
}