import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import project.JDBC;
import tool.UnitCommon;
import entity.User;

public class yonghuinfo extends  JPanel
{
	private static final long serialVersionUID = 1L;
	static JTextField chaxun;//查询方式
	static  JLabel find;//
	static JButton findbtn;//删除按钮
	static JButton delbtn;//删除按钮
	static JButton addbtn;//增加按钮
	static JButton storebtn;//保存按扭
	final static JPopupMenu popup = new JPopupMenu();
	String[] chaxunway={"选择方式","按卡号","按姓名"};
	JComboBox jComboBox1=new JComboBox(chaxunway);
	private ImageIcon icon;
	private Image img;
    TableColumn column;
	public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h)
    {
        constraints.gridx=x;
        constraints.gridy=y; 
        constraints.gridwidth=w;
        constraints.gridheight=h;
        add(c,constraints);
    }         	
	yonghuinfo()
	{      
		  icon=new ImageIcon("images/mainform.png");
		  img=icon.getImage(); 
		  JPanel pane=new JPanel();
		  Font font = new Font("宋体",Font.PLAIN,16);
		  UIManager.put("Button.font", font); 
		  UIManager.put("Label.font", font);
	      UIManager.put("MenuItem.font", font);
		  UIManager.put("Table.font", font); 
		  find=new JLabel("查找");
	      findbtn=new JButton("查找");
	      chaxun=new JTextField(15);
	      JTable table=new JTable();//创建一个新表格
		  DefaultTableModel userinfo = new DefaultTableModel();//建立默认的JTable模型
		  DefaultTableModel searchinfo = new DefaultTableModel();
	        try{
	  	  	Vector<String> columnName = new Vector<String>();//字段名
	  	  	Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的	
	  	  	columnName.add("ID");
	  	  	columnName.add("姓名");
	  	  	columnName.add("权限");
	  	  	columnName.add("密钥");
	  	  	columnName.add("班级");
	  	  columnName.add("状态");
	  	  	JDBC sql=new JDBC();
	  	  	Connection con=null;
	  		try {
	  			con = sql.getConnection();
	  		} catch (ClassNotFoundException e) {
	  			e.printStackTrace();
	  		}
	  	  	Statement stmt = con.createStatement();// 创建SQL命令对象
	  		ResultSet rs = stmt.executeQuery("SELECT * FROM student");
	  	  	while(rs.next()){
	  	  	Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
	  	  	for(int i=1;i<=6;i++){
	  	  	vec.add(rs.getObject(i));
	  	  	}
	  	  	dataVector.add(vec);
	  	  	}
	  	  	userinfo.setDataVector(dataVector, columnName);//设定模型数据和字段
	  	  	} catch (SQLException e) {
	  	  	e.printStackTrace();
	  	  	}
	  	  	table.setModel(userinfo);
	       JLabel title=new JLabel("用户个人信息");
	  	  table.setPreferredScrollableViewportSize(new Dimension(getSize().width,300));//设置表格的大小
		  JScrollPane scrollPane=new JScrollPane(table);//建立一个带滚动条的pane，将表格添加进去
		  scrollPane.resize(getPreferredSize());
	  	  Toolkit kit=Toolkit.getDefaultToolkit();//得到默认toolkit
	      Dimension screenSize=kit.getScreenSize();//获得屏幕尺寸
	      int width=screenSize.width;
	      int height=screenSize.height;
	      int x=(width-WIDTH)/2;
	      int y=(height-HEIGHT)/2;
		  GridBagLayout lay=new GridBagLayout(); 
		  setLayout(lay);
		  GridBagConstraints constraints=new GridBagConstraints();
			 GridBagConstraints constraints1=new GridBagConstraints();
			 GridBagConstraints constraints2=new GridBagConstraints();
			 constraints1.weightx=-0.1;//设置横向的缩放比例。
			 constraints1.weighty=0;
			 constraints1.insets = new Insets(0,10,5,0);
			 constraints1.fill=GridBagConstraints.RELATIVE; //设置组件的填充方向
			 constraints1.anchor=GridBagConstraints.WEST;
			 constraints.weightx=0.0001;//设置横向的缩放比例。
			 constraints.weighty=0;
			 constraints.insets = new Insets(0,10,5,200);
			 constraints.fill=GridBagConstraints.CENTER;//设置组件的填充方向
			 constraints2.weightx=1.0;//设置横向的缩放比例
			 constraints2.weighty=0.001;
			 constraints2.insets = new Insets(0,10,5,10);
			 constraints2.fill=GridBagConstraints.BOTH;//设置组件的填充方向
		     add(title,constraints,3,0,0,1);    
		     add(find,constraints1,0,1,1,1);add(jComboBox1,constraints1,1,1,1,1); 
		     add(chaxun,constraints1,2,1,1,1);add(findbtn,constraints1,7,1,0,1); 
		     add(scrollPane,constraints2,0,2,12,2);
	     
	     //按下查询按钮的时候将根据下拉类表框选择的方式以及文本框中输入的内容对表格中的内容进行更新并显示出来。
	    findbtn.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	  //按照卡号的方式查找用户的信息
	        	  if(jComboBox1.getSelectedIndex()==1 && chaxun.getText().trim()!=null )
	        	  {    
	        		  table.setModel(userinfo);
					 int p=0;
					 Vector<String> columnName = new Vector<String>();//字段名
					  Vector<Vector<Object>> dataVector = new  Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的
				  	  	columnName.add("ID");
				  	  	columnName.add("姓名");
				  	  	columnName.add("权限");
				  	  	columnName.add("密钥");
				  	  	columnName.add("班级");
				  	  columnName.add("状态");
					 for(int i = 0; i <table.getRowCount();i++)
	       		    {
//						 System.out.println(table.getValueAt(i, 1));
	      	           if(table.getValueAt(i, 0).equals(chaxun.getText().trim()))//这个是判断文本框中输入的内容与表格中的哪一项是一样的,这句代码对了
	      	           {	
	      	        	   int k=0;
	      	        	   k=i;
	      	        	   p=p+1;
	      	        	   Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
	      	        	   for(int j=0;j<6;j++){
	      				       vec.add(table.getValueAt(k, j));
	      				   }
	      	        	   dataVector.add(vec);
	      	           } 
	       		     }
					 if(p==0){ 			
						 JOptionPane.showMessageDialog(pane,"此用户不存在","提示",JOptionPane.WARNING_MESSAGE);
						 p=0;
				  }
					 else{
						 searchinfo.setDataVector(dataVector, columnName);//设定模型数据和字段
					 table.setModel(searchinfo);
					 }  	        	 
	        	 }  

	        	  //按姓名的方式查找用户的信息
	        	  if(jComboBox1.getSelectedIndex()==2 && chaxun.getText().trim()!=null )
	        	  {  
	        		table.setModel(userinfo);
					int p=0;
					Vector<String> columnName = new Vector<String>();//字段名
					  Vector<Vector<Object>> dataVector = new  Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的
					  columnName.add("ID");
				  	  	columnName.add("姓名");
				  	  	columnName.add("权限");
				  	  	columnName.add("密钥");
				  	  	columnName.add("班级");
				  	  columnName.add("状态");
					 for(int i = 0; i <table.getRowCount();i++)
	       		  {
//						 System.out.println(table.getValueAt(i, 1));
	      	           if(table.getValueAt(i, 1).equals(chaxun.getText().trim()))//这个是判断文本框中输入的内容与表格中的哪一项是一样的,这句代码对了
	      	           {	
	      	        	   int k=0;
	      	        	   k=i;
	      	        	   p=p+1;
	      	        	   Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
	      	        	   for(int j=0;j<6;j++){
	      				       vec.add(table.getValueAt(k, j));
	      				   }
	      	        	   dataVector.add(vec);
	      	           } 
	       		   }
					 if(p==0){ 			
						 JOptionPane.showMessageDialog(pane,"此用户不存在","提示",JOptionPane.WARNING_MESSAGE);
						 p=0;
				  }
					 else{
						 searchinfo.setDataVector(dataVector, columnName);//设定模型数据和字段
					 table.setModel(searchinfo);
					 }
	        	  }//JOptionPane.showMessageDialog(pane,"没有选择查询方式或者输入为空","提示",JOptionPane.WARNING_MESSAGE);
	        	  //当用户没有选择查询方式或者没有输入的时候弹出提示对话框
	        	  if(jComboBox1.getSelectedIndex()==0 || chaxun.getText().trim()==null)
	        	  {
	        		  JOptionPane.showMessageDialog(pane,"没有选择查询方式或者输入为空","提示",JOptionPane.WARNING_MESSAGE);
	        	  }
	          }
	      });
	    chaxun.addKeyListener(new KeyListener ()
		{
			@Override//API上面解释说这个方法是键入键的时候使用，即输入字符时产生该事件
			public void keyTyped(KeyEvent e) 
			{
			}
			@Override//按下键的时候生成此事件
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) //判断按下的键是否是回车键
			    {  			  //按照卡号的方式查找用户的信息
		        	  if(jComboBox1.getSelectedIndex()==1 && chaxun.getText().trim()!=null )
		        	  {   
		        		  table.setModel(userinfo);
						int p=0;
						Vector<String> columnName = new Vector<String>();//字段名
						  Vector<Vector<Object>> dataVector = new  Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的
						  columnName.add("ID");
					  	  	columnName.add("姓名");
					  	  	columnName.add("权限");
					  	  	columnName.add("密钥");
					  	  	columnName.add("班级");
					  	  columnName.add("状态");
						 for(int i = 0; i <table.getRowCount();i++)
		       		  {
//							 System.out.println(table.getValueAt(i, 1));
		      	           if(table.getValueAt(i, 0).equals(chaxun.getText().trim()))//这个是判断文本框中输入的内容与表格中的哪一项是一样的,这句代码对了
		      	           {	
		      	        	   int k=0;
		      	        	   k=i;
		      	        	   p=p+1;
		      	        	   Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
		      	        	   for(int j=0;j<6;j++){
		      				       vec.add(table.getValueAt(k, j));
		      				   }
		      	        	   dataVector.add(vec);
		      	           } 
		       		   }
						 if(p==0){ 			
							 JOptionPane.showMessageDialog(pane,"此用户不存在","提示",JOptionPane.WARNING_MESSAGE);
							 p=0;
					  }
						 else{
							 searchinfo.setDataVector(dataVector, columnName);//设定模型数据和字段
						 table.setModel(searchinfo);
						 }
		        	  }
		        	  if(jComboBox1.getSelectedIndex()==2 && chaxun.getText().trim()!=null )
		        	  {    
		        		  table.setModel(userinfo);
						int p=0;
						Vector<String> columnName = new Vector<String>();//字段名
						  Vector<Vector<Object>> dataVector = new  Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的
						  columnName.add("ID");
					  	  	columnName.add("姓名");
					  	  	columnName.add("权限");
					  	  	columnName.add("密钥");
					  	  	columnName.add("班级");
					  	  columnName.add("状态");
						 for(int i = 0; i <table.getRowCount();i++)
		       		  {
//							 System.out.println(table.getValueAt(i, 1));
		      	           if(table.getValueAt(i, 1).equals(chaxun.getText().trim()))//这个是判断文本框中输入的内容与表格中的哪一项是一样的,这句代码对了
		      	           {	
		      	        	   int k=0;
		      	        	   k=i;
		      	        	   p=p+1;
		      	        	   Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
		      	        	   for(int j=0;j<6;j++){
		      				       vec.add(table.getValueAt(k, j));
		      				   }
		      	        	   dataVector.add(vec);
		      	           } 
		       		   }
						 if(p==0){ 			
							 JOptionPane.showMessageDialog(pane,"此用户不存在","提示",JOptionPane.WARNING_MESSAGE);
							 p=0;
					  }
						 else{
							 searchinfo.setDataVector(dataVector, columnName);//设定模型数据和字段
						 table.setModel(searchinfo);
						 }
		        	  }
		        	  if(jComboBox1.getSelectedIndex()==0 || chaxun.getText().trim()==null)
		        	  {
		        		  JOptionPane.showMessageDialog(pane,"没有选择查询方式或者输入为空","提示",JOptionPane.WARNING_MESSAGE);
		        	  }
			}
			}
			@Override//释放键的时候产生此事件
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub				
			}
		});

	     //给文本框添加回车事件监听
			chaxun.addKeyListener(new KeyListener ()
			{
				@Override//API上面解释说这个方法是键入键的时候使用，即输入字符时产生该事件
				public void keyTyped(KeyEvent e) 
				{
					// TODO Auto-generated method stub
				}
				@Override//按下键的时候生成此事件
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if(e.getKeyCode() == KeyEvent.VK_ENTER) //判断按下的键是否是回车键
				    {  
						 	if (chaxun.getText().trim()!=null)
						 	{
						 		if(jComboBox1.getSelectedIndex()==1 )
					        	  {     table.setModel(userinfo);
									int p=0;
									Vector<String> columnName = new Vector<String>();//字段名
									  Vector<Vector<Object>> dataVector = new  Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的
									  columnName.add("ID");
								  	  	columnName.add("姓名");
								  	  	columnName.add("权限");
								  	  	columnName.add("密钥");
								  	  	columnName.add("班级");
								  	  columnName.add("状态");
									 for(int i = 0; i <table.getRowCount();i++)
					       		  {
//										 System.out.println(table.getValueAt(i, 1));
					      	           if(table.getValueAt(i, 1).equals(jComboBox1.getSelectedItem()))//这个是判断文本框中输入的内容与表格中的哪一项是一样的,这句代码对了
					      	           {	
					      	        	   int k=0;
					      	        	   k=i;
					      	        	   p=p+1;
					      	        	   Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
					      	        	   for(int j=0;j<6;j++){
					      				       vec.add(table.getValueAt(k, j));
					      				   }
					      	        	   dataVector.add(vec);
					      	           } 
					       		   }
									 if(p==0){ 			
										 JOptionPane.showMessageDialog(pane,"此用户不存在","提示",JOptionPane.WARNING_MESSAGE);
										 p=0;
								  }
									 else{
										 searchinfo.setDataVector(dataVector, columnName);//设定模型数据和字段
									 table.setModel(searchinfo);
									 }
					        	  }//JOptionPane.showMessageDialog(pane,"此卡号不存在","提示",JOptionPane.WARNING_MESSAGE);
					        	  //按姓名的方式查找用户的信息
					        	  if(jComboBox1.getSelectedIndex()==2 )
					        	  {     table.setModel(userinfo);
									int p=0;
									Vector<String> columnName = new Vector<String>();//字段名
									  Vector<Vector<Object>> dataVector = new  Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的
									  columnName.add("ID");
								  	  	columnName.add("姓名");
								  	  	columnName.add("权限");
								  	  	columnName.add("密钥");
								  	  	columnName.add("班级");
								  	  columnName.add("状态");
									 for(int i = 0; i <table.getRowCount();i++)
					       		  {
//										 System.out.println(table.getValueAt(i, 1));
					      	           if(table.getValueAt(i, 1).equals(jComboBox1.getSelectedItem()))//这个是判断文本框中输入的内容与表格中的哪一项是一样的,这句代码对了
					      	           {	
					      	        	   int k=0;
					      	        	   k=i;
					      	        	   p=p+1;
					      	        	   Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
					      	        	   for(int j=0;j<6;j++){
					      				       vec.add(table.getValueAt(k, j));
					      				   }
					      	        	   dataVector.add(vec);
					      	           } 
					       		   }
									 if(p==0){ 			
										 JOptionPane.showMessageDialog(pane,"此用户不存在","提示",JOptionPane.WARNING_MESSAGE);
										 p=0;
								  }
									 else{
										 searchinfo.setDataVector(dataVector, columnName);//设定模型数据和字段
									 table.setModel(searchinfo);
									 }
					        	  }
						 	}	
				}
				}
				@Override//释放键的时候产生此事件
				public void keyReleased(KeyEvent e) {	
				}
			});
	}	
	 public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(img,0,0, getSize().width, getSize().height, null);
		}
}