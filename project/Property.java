package project;

public class Property {
   
	  private String consta1 ="通信未连接";
	  private String consta2="通信未连接";
	  private String consta3="通信未连接";
	  private String consta4="通信未连接";
	  private String doorsta="门禁未开启";
	  private String sqldrive="数据库驱动未加载";
	  private String sqlconsta="数据库未连接";
	  private String udpconsta="服务端未启动";
	  
public void setallconsta(String consta){
	this.consta1=consta;
	this.consta2=consta;
	this.consta3=consta;
	this.consta4=consta;
}
	  public void setconsta(String consta,String doorid){
		  if(doorid.equals("1225"))
		  this.consta1=consta;
		  if(doorid.equals("2551"))
			  this.consta2=consta;
		  if(doorid.equals("3434"))
			  this.consta3=consta;
		  if(doorid.equals("4343"))
			  this.consta4=consta;
	  }
	  public String getconsta(String doorid) {
		  if(doorid.equals("1225"))
		      return this.consta1;
		  if(doorid.equals("2551"))
			  return this.consta2;
		  if(doorid.equals("3434"))
			  return this.consta3;
		  if(doorid.equals("4343"))
			  return this.consta4;
		  return null;
	}
	  public void setdoorsta(String doorsta){
		  this.doorsta=doorsta;
	  }
	  public String getdoorsta() {
		return this.doorsta;
	}
	  public void setsqldrive(String sqldrive){
		  this.sqldrive=sqldrive;
	  }
	  public String getsqldrive() {
		return this.sqldrive;
	}
	  public void setsqlconsta(String sqlconsta){
		  this.sqlconsta=sqlconsta;
	  }
	  public String getsqlconsta() {
		return this.sqlconsta;
	}  
	  public void setudpconsta(String udpconsta){
		  this.udpconsta=udpconsta;
	  }
	  public String getudpconsta() {
		return this.udpconsta;
	}   
}
