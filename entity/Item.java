package entity;

public class Item {
	private String ID;
	private String name;
public Item(String ID,String name){
	this.name=name;
	this.ID=ID;
	
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
}
