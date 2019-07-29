package project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

import project.Translate;
import tool.UnitCommon;
import entity.Dao;
import entity.Userinfo;
import entity.history;
import entity.status;
public class UDPRece implements Runnable {
	public Property mProperty = new Property();
	private status sta2=new status();
	private history info=new history();
	String id = null;// 获取返回ID
	String limit =null;// 获取返回密码
	String key =null;//获取密钥
	String name=null;//获取姓名
	String dorID=null;
	public  UDPRece(status sta){	
		 sta2= sta;
	}
	public void run() {
		System.out.println("服务端启动");
		mProperty.setudpconsta("服务端启动");
		sta2.setudpconsta(mProperty.getudpconsta());
		DatagramSocket pc = null;
		try {
			pc = new DatagramSocket(1500);
			pc.setSoTimeout(5000);
		} catch (SocketException e1) {
			e1.printStackTrace();
			mProperty.setudpconsta("服务端启动失败");
			sta2.setudpconsta(mProperty.getudpconsta());
		}
		JDBC sql = new JDBC();
		try {
			sql.getConnection(mProperty,sta2);
		} catch (ClassNotFoundException e1) {
			UnitCommon.showError(e1.getMessage());
			mProperty.setsqldrive("数据库驱动加载失败");
			sta2.setsqldrive(mProperty.getsqldrive());
		} catch (SQLException e1) {
			UnitCommon.showError(e1.getMessage());
			mProperty.setsqlconsta("数据库连接失败");
			sta2.setsqlconsta(mProperty.getsqlconsta());
		}//连接数据库
//		try {
//			UDPSend.sendback(Translate.HexString2Bytes("22"));
//		} catch (IOException e1) {
//			// TODO 自动生成的 catch 块
//			e1.printStackTrace();
//		}//如果没有搜索到该门禁卡ID，发送拒绝响应
		while(true){	 
				byte[] data=new byte[1024];//数据包
				byte[] judge = new byte[1024];//查询结果
				byte[] buf=new byte[1024];
				//byte[] buf=backdata;
				String yes="AE";//取消门禁卡请求
				String no ="AF";//验证密码
				String addbit="000000000000";
				
				DatagramPacket stm32 =new DatagramPacket(data,data.length);
				mProperty.setdoorsta("等待刷卡");
                sta2.setdoorsta(mProperty.getdoorsta());
				try {
				pc.receive(stm32);
				/*****显示相关内容**********/
				//String ip = stm32.getAddress().getHostAddress();//获取发送端地址
				//int port = stm32.getPort();//获取发送端端口
				//String text = new String(stm32.getData(),0,stm32.getLength());//获取发送内容
				//System.out.println(ip+":"+port+":"+text);

				byte[] data2bytes = Translate.bytes2HexString(data).getBytes();// 将十六进制数形式的内容转换成String类型,再转换成字节数组。
				String opc = new String (data2bytes,0,2);  //获取操作码
				 dorID = new String(data2bytes,2,4);//获取门ID
				/****门禁状态报告***/
				if(opc.equals("11"))//门禁状态报告
				{
					String dorstate=new String(data2bytes,6,2);
					if(dorstate.equals("01"))
					{
					    mProperty.setconsta("连接正常",dorID);
					    sta2.setconsta(mProperty.getconsta(sta2.getdoorid()));
					}
					if(dorstate.equals("0F"))
					{
						mProperty.setconsta("连接正常",dorID);
					    sta2.setconsta(mProperty.getconsta(sta2.getdoorid()));
					}
				}
				
				/****请求开门***/
				if(opc.equals("21"))
				{
					String dorstate=new String(data2bytes,6,2);//读取门状态
					String cardID=new String (data2bytes,8,8);//读取门禁卡ID
					System.out.println(dorstate+cardID);
					Dao.getConnection();
					Userinfo user=Dao.getuserinfo(cardID);
					String sta=user.getstuta();
					System.out.println(sta);
					if(sta.equals("正常"))
					{
						if(dorstate.equals("0F"))//如果门处于关状态，进行验证
						{
							judge = sql.getLocation(cardID).getBytes();
						   String wrong=new String(judge,0,2);
							if (wrong.equals("EE"))//如果查无此卡，则拒绝请求
							{
								buf = Translate.HexString2Bytes("22"+dorID+cardID+no+addbit);
								DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getByName("111.115.74.92"),1025);
				                pc.send(dp);
							}
								//System.out.println(no);
								//UDPSend.sendback(Translate.HexString2Bytes("22"+dorID+cardID+no+addbit));//如果没有搜索到该门禁卡ID，发送拒绝响应
							else
							{
								  
								id = new String(judge, 0, 8);// 获取返回ID
								limit = new String(judge, 8, 4);// 获取返回密码
								key = new String(judge, 12, 12);//获取密钥
								name=new String(judge,24,judge.length-24);//获取姓名
								if(limit.equals(dorID))
								{
									//UDPSend.sendback(Translate.HexString2Bytes("22"+limit+id+yes+key));//验证正确，并且门处于关状态，发送验证密码响应
									buf=Translate.HexString2Bytes("22"+limit+id+yes+key);
									DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getByName("111.115.74.92"),1025);
					                pc.send(dp);
								    System.out.println("22"+limit+id+yes+key);
								}
									
								else{
									buf = Translate.HexString2Bytes("22"+dorID+cardID+no+addbit);
									DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getByName("111.115.74.92"),1025);
					                pc.send(dp);
					                info.setID(cardID);
									info.setname(name);
									info.setdoor(dorID);
									info.settime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
									info.setresult("权限不符");
							        Connection con=Dao.getConnection();
									Dao.inserthistory(info);
								}
									//UDPSend.sendback(Translate.HexString2Bytes("22"+dorID+cardID+no+addbit));//权限不符，发送拒绝响应
								
							}
						}
						else {//如果门处于开状态，拒绝请求
							buf = Translate.HexString2Bytes("22"+dorID+cardID+no+addbit);
							DatagramPacket dp=new DatagramPacket(buf,buf.length,InetAddress.getByName("111.115.74.92"),1025);
			                pc.send(dp);
						}
						//UDPSend.sendback(Translate.HexString2Bytes("22"+dorID+cardID+no+addbit));//门处于开状态，发送拒绝响应
					}

				 }
				if(opc.equals("23"))
				{
					String dorstate=new String(data2bytes,14,2);//读取门状态
					String cardID=new String (data2bytes,6,8);//读取门禁卡ID
					String result=new String(data2bytes,16,2);//读取结果
					if(result.equals("BF"))
					{
						info.setID(cardID);
						info.setname(name);
						info.setdoor(dorID);
						info.settime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						info.setresult("密码错误");
				        Connection con=Dao.getConnection();
						Dao.inserthistory(info);
					}
					if(result.equals("B1"))
					{
						info.setID(cardID);
						info.setname(name);
						info.setdoor(dorID);
						info.settime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						info.setresult("正常开启");
				        Connection con=Dao.getConnection();
						Dao.inserthistory(info);
					}
					if(result.equals("B2"))
					{
						info.setID(cardID);
						info.setname(name);
						info.setdoor(dorID);
						info.settime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						info.setresult("门锁故障");
				        Connection con=Dao.getConnection();
						Dao.inserthistory(info);
					}
					if(result.equals("B3"))
					{
						info.setID(cardID);
						info.setname(name);
						info.setdoor(dorID);
						info.settime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						info.setresult("设备连接异常");
				        Connection con=Dao.getConnection();
						Dao.inserthistory(info);
					}
				}
				} catch (IOException e) {
					  mProperty.setallconsta("通信中断");
					  sta2.setconsta(mProperty.getconsta(sta2.getdoorid()));
					}
		    
	}
		//ds.close();
	}

}
