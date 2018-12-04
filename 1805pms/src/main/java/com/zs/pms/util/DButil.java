package com.zs.pms.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.zs.pms.execption.AppException;


/**操作数据库的中间件
 * @author Administrator
 *jdbc:mysql:Mysql专有连接
 * localhost:连接数据库服务器地址 localhost:本机 如果连接别人数据则改成对方ip
 * 3306:mysql服务器端口
 * test:数据库实例(要访问的数据库的名字)
 * ?useUnicode=true&characterEncoding=utf-8:以utf-8的形式连接和传递数据 支持中文
 */

@Repository //操作数据库
public class DButil {
	//驱动
	private String drver="com.mysql.jdbc.Driver";
	//连接地址，参数是支持中文输入
	private String url="jdbc:mysql://localhost:3306/cms?useUnicode=true&characterEncoding=utf-8";
	//连接用户名
	private String user="root";
	//密码
	private String password="123456";
	//构造函数
	public DButil(){
		System.out.println("===============创建DButil=============");
	}
	
	/**获得数据库连接的方法，不允许外界调用
	 * @return
	 */
	private Connection getConn(){
		Connection  conn=null;
		try {
			//加载并注册驱动			
			Class.forName(drver);
			//获得数据库连接
			conn=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return conn;
	}
	
	/**增删改的方法
	 * @param sql 需要执行的带参SQL语句
	 * @param objs 带参SQL语句
	 * @return 执行结果
	 * @throws SysException 
	 */
	public int execUpdate(String sql,Object[] objs) throws AppException{
		//获得数据库连接
		Connection conn=this.getConn();
		//声明预编译对象
		PreparedStatement ps=null;
		//如果连接为空时 不能执行以下内容
		if (conn==null) {
			return 0;
		}
		try {
			//从连接中获得预编译对象			
			ps=conn.prepareStatement(sql);
			//依次将参数Set到ps中参数注入
			for (int i = 0; i < objs.length; i++) {
				//索引从1开始				
				ps.setObject(i+1, objs[i]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new AppException(100,"系统异常，请联系管理员");	
		}finally{
			//调用本类的关闭资源
			this.close(null, ps, conn);
		}	

	}
	
	/**查询的方法
	 * @param sql 带参的SQL语句
	 * @param objs 为SQL中的参数，因为不知道时Int还是String所以用Object存参数
	 * @return  
	 * List<Map<String, Object>> 不知道查询的数据为哪个Bean类型所以用Map封装
	 * @throws SysException 
	 */
	public  List<Map<String, String>> execQuery(String sql,Object[] objs) throws AppException{
		//获得连接
		Connection conn=this.getConn();
		//定义处理对象的变量初始化为null
		PreparedStatement ps=null;
		//定义结果集初始化为null		
		ResultSet  rs=null;
		//定义一个用于存放封装数据的Map集合的List集合
		List<Map<String, String>> maps=new ArrayList<>();
		
		try {
			//从数据库连接中获得处理对象
			ps=conn.prepareStatement(sql);
			if (objs!=null) {
				//把参数注入到SQL中
				for (int i = 0; i < objs.length; i++) {
					//依次把数组内的参数取出Set到ps的i+1位置				
					ps.setObject(i+1, objs[i]);
				}
			}
			
			//获得结果集
			rs=ps.executeQuery();
			//获得结果集的结构
			ResultSetMetaData  rm=rs.getMetaData();
			//依次从结果集中取出值
			while (rs.next()) {
				//map集合用于存放一条数据			
				Map<String, String> map=new HashMap<>();
				//把数据封装到map	
				for (int i =1; i <=rm.getColumnCount(); i++) {
					//结果集结构中获得字段名	rm.getColumnName(i)，i从1开始
					//结果集获得字段值 rs.getObject(i)，i从1开始
					map.put(rm.getColumnName(i), rs.getString(i));		
				}
				//把一条信息放入list集合
				maps.add(map);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			throw new AppException(100,"系统异常，请联系管理员");
		}finally{
			//调用本类的关闭资源
			this.close(rs, ps, conn);
		}
		return maps;	
	}
	/**关闭资源的方法
	 * @param rs 结果集
	 * @param ps 处理对象
	 * @param conn 连接
	 */
	private void close(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			if (rs!=null) {
				rs.close();
			}if (ps!=null) {
				ps.close();
			}if (conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
/*public static void main(String[] args) {
	DButil  db=new DButil();
//	String sql="update stu set name=?where  id=? ";
//	Object[] objs={"羽田",4};
//	db.execUpdate(sql, objs);
	String sql="select* from players";
	Object[] objs={};
	List<Map<String, String>> execQuery = db.execQuery(sql, objs);
	for (Map<String, String> map : execQuery) {
		Set<String> key=map.keySet();
		for (String string : key) {
			System.out.print(string+"="+map.get(string));
		}
		System.out.println();
	}
}
*/
}
