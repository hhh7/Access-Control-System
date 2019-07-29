package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import entity.status;
public class JDBC {
	static Connection con;
	static Statement sql;
	public Connection getConnection(Property mProperty,status sta) throws SQLException, ClassNotFoundException{
			String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
			String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test";// 数据源注意IP地址和端口号，数据库名字！！！
//			try {
				Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类
//			} catch (ClassNotFoundException e) {
//				// e.printStackTrace();
//				System.out.println("加载数据库引擎失败");
//				System.exit(0);
//			}
			System.out.println("数据库驱动成功");
			mProperty.setsqldrive("数据库驱动成功");
			sta.setsqldrive(mProperty.getsqldrive());
//			try {
				String user = "Dsy";// 你自己创建的用户名字和密码
				String password ="123";
				 con = DriverManager.getConnection(connectDB, user,	password);// 连接数据库对象
				 mProperty.setsqlconsta("数据库连接成功");
				 sta.setsqlconsta(mProperty.getsqlconsta());
//				System.out.println("连接数据库成功");
//			} catch (SQLException e) {
//				e.printStackTrace();
//				// System.out.println("数据库连接错误");
//				System.exit(0);
//			}
			return con;
	}
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test";// 数据源注意IP地址和端口号，数据库名字！！！
		try {
			Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类
		} catch (ClassNotFoundException e) {
			System.exit(0);
		}
		try {
			String user = "Dsy";// 你自己创建的用户名字和密码
			String password ="123";
			 con = DriverManager.getConnection(connectDB, user,	password);// 连接数据库对象
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return con;
}
	public String getLocation(String id) {
		String cardID = null;
		String doorID = null;
		String key = null;
		String name=null;
		String wrong="EE";
		try {
			Statement stmt = con.createStatement();// 创建SQL命令对象
			ResultSet rs = stmt.executeQuery("SELECT * FROM student where ID='"+id+"'");
		   if(rs.next()==false)
		 	return wrong;
		   else {
				 cardID=rs.getString("ID");//查询的ID
				 name=rs.getString("姓名");
				 doorID=rs.getString("权限");//查询的密码
				 key=rs.getString("密钥");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		//System.out.println(cardID+doorID+key);
		return cardID+doorID+key+name;
	}
	
	public static void main(String[] args){

	}
}