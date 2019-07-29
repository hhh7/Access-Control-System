package entity;

public class history {
	private String ID;
	private String name;//姓名
	private String door;//门禁号
	private String time;//刷卡时间
	private String result;//结果
	public history()//默认构造方法
	{
//		this.name=name;
//		this.door=door;
//		this.time=time;
//		this.result=result;
	}
	public void setID(String id)
	{
		this.ID=id;
	}
	public String getID()
	{
		return ID;
	}
	public void setname(String name)
	{
		this.name=name;
	}
	public String getname()
	{
		return name;
	}
	public void setdoor(String door){
		this.door=door;
	}
	public String getdoor(){
		return door;
	}
	public void settime(String time){
		this.time=time;
	}
	public String gettime(){
		return time;
	}
	public  void setresult(String result){
		this.result=result;
	}
	public String getresult(){
		return result;
	}
	
  public String toString(){
	  return getname();
	  }
}
