package entity;
/*
 * 对应数据库中不同的数据表，这些模型将被访问数据库的Dao类和程序中各个模块
 */
public class Userinfo implements java.io.Serializable{
    	private String ID;//用户ID
    	private String name;//姓名
    	private String limit;//权限
    	private String clas;//班级
    	private String key;//密钥
    	private String stuta;//状态
    	public Userinfo(){
//    		this.ID=ID;
//    		this.name=name;
//    		this.limit=limit;
//    		this.key=key;
//    		this.clas=clas;
    	}
    	public String getid()
    	{
    		return ID;
    	}
    	public void setID(String ID)
    	{
    		this.ID=ID;
    	}
    	public void setname(String name)
    	{
    		this.name=name;
    	}
    	public String getname()
    	{
    		return name;
    	}
    	public void setlimit(String limit)
    	{
    		this.limit=limit;
    	}
    	public String getlimit()
    	{
    		return limit;
    	}
    	public void setclasses(String clas)
    	{
    		this.clas=clas;
    	}
    	public String getclasses()
    	{
    		return clas;
    	}
    	public void setkey(String key)
    	{
    		this.key=key;
    	}
    	public String getkey()
    	{
    		return key;
    	}
    	public void setstuta(String stuta)
    	{
    		this.stuta=stuta;
    	}
    	public String getstuta()
    	{
    		return stuta;
    	}
    	@Override
    	public int hashCode(){
    		final int PRIME=31;
    		int result=1;
    		result=PRIME*result+((ID==null)?0:ID.hashCode());
    		result=PRIME*result+((name==null)?0:name.hashCode());
    		result=PRIME*result+((limit==null)?0:limit.hashCode());
    		result=PRIME*result+((clas==null)?0:clas.hashCode());
    		result=PRIME*result+((key==null)?0:key.hashCode());
    		return result;
    	}
    	@Override
    	public boolean equals(Object obj){
    		if(this==obj)
    			return true;
    		if(obj==null)
    			return false;
    		if(getClass()!=obj.getClass())
    			return false;
    		final Userinfo other=(Userinfo)obj;
    		if(ID==null){
    			if(other.ID!=null)
    				return false;
    		}else if(!ID.equals(other.ID))
    			return false;
    		if(name==null){
    			if(other.name!=null)
    				return false;
    		}else if(!name.equals(other.name))
    			return false;
    		if(limit==null){
    			if(other.limit!=null)
    				return false;
    		}else if(!limit.equals(other.limit))
    			return false;
    		if(clas==null){
    			if(other.clas!=null)
    				return false;
    		}else if(!clas.equals(other.clas))
    			return false;
    		if(key==null){
    			if(other.key!=null)
    				return false;
    		}else if(!key.equals(other.key))
    			return false;
    		return true;
    	}
    	
}
