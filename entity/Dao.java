package entity;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import tool.UnitCommon;
public class Dao {
	static Connection con;
	static PreparedStatement sql;
	public static Connection getConnection(){
			String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
			String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test";// 数据源注意IP地址和端口号，数据库名字！！！
			try {
				Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类
			} catch (ClassNotFoundException e) {
				System.out.println("加载数据库引擎失败");
				System.exit(0);
			}
			System.out.println("数据库驱动成功");
			try {
				String user = "Dsy";// 你自己创建的用户名字和密码
				String password ="123";
				 con = DriverManager.getConnection(connectDB, user,
						password);// 连接数据库对象
				 System.out.println(con);
				System.out.println("连接数据库成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("数据库连接错误");
				System.exit(0);
			}
			return con;
	}
	/*
	 public String getLocation(String id) {
		String cardID = null;
		String doorID = null;
		String key = null;
		String wrong="EE";
		try {
			Statement stmt = con.createStatement();// 创建SQL命令对象
			ResultSet rs = stmt.executeQuery("SELECT * FROM student where ID='"+id+"'");
		   if(rs.next()==false)
		 	return wrong;
		   else {
				 cardID=rs.getString("ID");//查询的ID
				 doorID=rs.getString("limit");//查询的密码
				 key=rs.getString("key");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		//System.out.println(cardID+doorID+key);
		return cardID+doorID+key;
	}
	public Vector getuser(Connection con,String sql)
	{
		Vector v=new Vector();
		try{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				String ID=rs.getString(1);
				User user=new User(ID);
				String name=rs.getString(2);
				String limit =rs.getString(3);
				String key=rs.getString(4);
				String classes= rs.getString(5);
				user.setname(name);
				user.setlimit(limit);
				user.setkey(key);
				user.setclasses(classes);
				v.add(user);
				
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return v;
	}
	public User getobject(Connection con ,String ID)
	{
		User c=null;
		try{
			Statement st = con.createStatement();
			String sql="selece*from student where ID='"+ID+"'";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				ID=rs.getString(1);
				String name=rs.getString(2);
				String limit =rs.getString(3);
				String key=rs.getString(4);
				String classes= rs.getString(5);
				c=new User(ID);
				c.setname(name);
				c.setlimit(limit);
				c.setkey(key);
				c.setclasses(classes);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return c;
	}
	
	 */
private Dao(){
	}
//登陆验证
public static boolean checkLogin(String username,String password,int limit) throws SQLException{
	sql=con.prepareStatement("select*from logincheck where 用户名='"+username+"'and 密码='"+password+"' ");
    ResultSet set=sql.executeQuery();
    String a = null;
    String b = null;
    int c = 0;
    if(set.next()==false)
	 	return false;
    else{
	 a = set.getString("用户名");
	 b = set.getString("密码");
	 c = set.getInt("权限");
}
if(a.equals(username)&&b.equals(password)&&c==limit)
	return true;
else
	return false;
}
//查询用户信息
public static Userinfo getuserinfo(String id){
    Userinfo info =new Userinfo();
    try{
        sql=con.prepareStatement("select*from student where ID='"+id+"'");
        ResultSet set=sql.executeQuery();
    	if(set.next()){
    		info.setID(set.getString("ID").trim());
    		info.setname(set.getString("姓名").trim());
    		info.setclasses(set.getString("权限").trim());
    		info.setlimit(set.getString("密钥").trim());
    		info.setkey(set.getString("班级").trim());
    		info.setstuta(set.getString("状态").trim());
    	}
    }catch (SQLException e){
    	e.printStackTrace();
    }
    return info;
	
}

//查询历史纪录
public static history gethistory(Item user){
	String where = "ID='"+user.getid()+"'";
	if(user.getname()!=null)
		where="name='"+user.getname()+"'";
    history info =new history();
    try{
        sql=con.prepareStatement("select*from history where"+where);
        ResultSet set=sql.executeQuery();
    	if(set.next()){
    		info.setname(set.getString("姓名").trim());
    		info.setdoor(set.getString("出入门禁号").trim());
    		info.settime(set.getString("出入时间").trim());
    		info.setresult(set.getString("结果").trim());
    	}
    }catch (SQLException e){
    	e.printStackTrace();
    }
    return info;
	
}

//插入用户信息
public static void insertuserinfo(Userinfo info){
	try{
		sql=con.prepareStatement("insert into student values('"+info.getid()
				+"','"+info.getname()+"','"+info.getlimit()
				+"','"+info.getkey()+"','"+info.getclasses()+"','"+info.getstuta()+"')");
		sql.executeUpdate();
		UnitCommon.showMessage("添加成功");
	}catch(SQLException e){
		e.printStackTrace();
	}
}

//插入历史纪录
public static  void inserthistory(history info){
	try{
		sql=con.prepareStatement("insert into history values('"+info.getID()+"','"+info.getname()
				+"','"+info.getdoor()+"','"+info.gettime()
				+"','"+info.getresult()+"')");
		sql.executeUpdate();
		sql=con.prepareStatement("select *from history order by 出入时间 desc");
	}catch(SQLException e){
		e.printStackTrace();
	}
}
//删除用户信息
public static void deletuserinfo(String id){
	try{
		sql=con.prepareStatement("delete from student where ID ='"+id+"'");
		sql.executeUpdate();
		UnitCommon.showMessage("删除成功");
	}catch(SQLException e){
		e.printStackTrace();
	}
}
//挂失
public static void guashi(String stuta,String id){
	try{
		sql=con.prepareStatement("update student set 状态 ='"+stuta+"' where ID ='"+id+"'");
		sql.executeUpdate();
		UnitCommon.showMessage("挂失成功");
	}catch(SQLException e){
		e.printStackTrace();
	}
}
//修改用户信息
public static void edituserinfo(Userinfo info){
	try{
		sql=con.prepareStatement("update student set 姓名 ='"+info.getname()+"' where ID ='"+info.getid()+"'");
		sql.executeUpdate();
		sql=con.prepareStatement("update student set 密钥 ='"+info.getkey()+"' where ID ='"+info.getid()+"'");
		sql.executeUpdate();
		sql=con.prepareStatement("update student set 权限 ='"+info.getlimit()+"' where ID ='"+info.getid()+"'");
		sql.executeUpdate();
		sql=con.prepareStatement("update student set 班级 ='"+info.getclasses()+"' where ID ='"+info.getid()+"'");
		sql.executeUpdate();
		sql=con.prepareStatement("update student set 状态 ='"+info.getstuta()+"' where ID ='"+info.getid()+"'");
		sql.executeUpdate();
		UnitCommon.showMessage("修改成功");
	}catch(SQLException e){
		e.printStackTrace();
	}
}
}