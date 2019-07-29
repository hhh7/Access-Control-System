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
import javax.swing.JPasswordField;
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
import entity.Dao;
import entity.User;
import entity.Userinfo;

public class card_edit extends  JPanel
{
	private static final long serialVersionUID = 1L;
	static  JLabel lblcardnum;//卡号标签
	static JLabel lblpassword;//RFID密钥
	static  JLabel lblname;//卡片主人的姓名
	static  JLabel lblmajor;//卡片主人的专业
	static  JLabel lblclass;//卡片主人的班级
	static  JLabel lbllimit;//卡片主人的权限
	static  JLabel lbljuese;//卡片主人的角色
	static  JLabel lblstatus;//RFID卡的状态
	
	static  JTextField txtcardnum;//卡号
	static JPasswordField txtPassWord;//密钥
	static  JTextField txtname;//姓名
	static  JTextField txtmajor;//专业
	static  JTextField txtclass;//班级
	static  JTextField txtlimit;//权限
	static  JTextField txtstatus;//RFID卡的状态
	
	static JButton shuakabtn;//刷卡按钮
	static JButton editbtn;//修改按钮
	static JButton cancelbtn;//清空按钮
	
	final static JPopupMenu popup = new JPopupMenu();
	JMenuItem del=new  JMenuItem("删除");
    JMenuItem add=new  JMenuItem("增加");
    private ImageIcon icon;
	private Image img;
	public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h)
    {
        constraints.gridx=x;
        constraints.gridy=y; 
        constraints.gridwidth=w;
        constraints.gridheight=h;
        add(c,constraints);
    }         	
	card_edit()
	{      
		  JPanel pane=new JPanel();      
		  icon=new ImageIcon("images/mainform.png");
		  img=icon.getImage(); 
		  Font font = new Font("宋体",Font.PLAIN,16);
		  UIManager.put("Button.font", font); 
		  UIManager.put("Label.font", font);
	      UIManager.put("MenuItem.font", font);
		  UIManager.put("Table.font", font); 
	      shuakabtn=new JButton("刷卡");
	      editbtn=new JButton("确认");
	  	cancelbtn=new JButton("取消");
	      lblcardnum=new JLabel("卡号 :");
	      lblname= new JLabel("姓名 :");
	      lblpassword=new JLabel("密钥 ：");
	      lblmajor=new JLabel("专业 :");
	      lblclass=new JLabel("班级 :");
	      lbllimit=new JLabel("权限 :");
	      lblstatus=new JLabel("状态 :");
	      txtcardnum=new JTextField(18);
	  /*    txtcardnum.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,16));
		  //设置文本的水平对齐方式
		  txtcardnum.setHorizontalAlignment(JTextField.CENTER);*/
	      txtname=new JTextField(18);
	   /*   txtname.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,16));
		  //设置文本的水平对齐方式
		  txtname.setHorizontalAlignment(JTextField.CENTER);*/
	      txtPassWord=new JPasswordField (18);
	  	txtPassWord.setEchoChar('*');	
	      String[] Class={"电信13-1班","电信14-1班","电信15-1班","电信16-1班"};
	      JComboBox jComboBox2=new JComboBox(Class);
	      String[] Major={"电子信息工程","通信工程","计算机科学与技术"};
	      JComboBox jComboBox3=new JComboBox(Major);
	      String[] limit={"1225","2551","3434","4343","所有"};
	      JComboBox jComboBox4=new JComboBox(limit);
	      String[] status={"正常","挂失"};
	      JComboBox jComboBox5=new JComboBox(status);
	      JLabel title=new JLabel("RFID卡信息修改");
	  	  Toolkit kit=Toolkit.getDefaultToolkit();//得到默认toolkit
	      Dimension screenSize=kit.getScreenSize();//获得屏幕尺寸
	      int width=screenSize.width;
	      int height=screenSize.height;
	      int x=(width-WIDTH)/2;
	      int y=(height-HEIGHT)/2;
		  GridBagLayout lay=new GridBagLayout(); 
		  setLayout(lay);
		  GridBagConstraints constraints=new GridBagConstraints();  
		  constraints.weightx=0;
		  constraints.weighty=0.8;
		  constraints.insets = new Insets(0,10, 5, 20);
		  constraints.fill=GridBagConstraints.CENTER;
//        使用网格组布局管理器添加控件
	     add(title,constraints,0,0,4,1);    
	     add(lblcardnum,constraints,0,1,1,1);add(txtcardnum,constraints,1,1,1,1); 
	     add(shuakabtn,constraints,2,1,1,1); 
	     add(lblpassword,constraints,0,2,1,1);add(txtPassWord,constraints,1,2,1,1); 
	     add(lblname,constraints,0,3,1,1);add(txtname,constraints,1,3,1,1); 
	     add(lblclass,constraints,0,4,1,1);add(jComboBox2,constraints,1,4,1,1);
	     add(lbllimit,constraints,0,5,1,1);add(jComboBox4,constraints,1,5,1,1);
	     add(lblstatus,constraints,0,6,1,1);add(jComboBox5,constraints,1,6,1,1);
	     add(cancelbtn,constraints,1,8,1,1);  add(editbtn,constraints,2,8,1,1); 
	     //按下删除按钮的时候同样进行删除操作
	     editbtn.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	  Userinfo info=new Userinfo();
	        	  info.setID(txtcardnum.getText().trim());
	        	  info.setkey(txtPassWord.getText().trim());
	        	  info.setlimit((String) jComboBox4.getSelectedItem());
	        	  info.setname(txtname.getText().trim());
	        	  info.setclasses((String) jComboBox2.getSelectedItem());
	        	  info.setstuta((String) jComboBox5.getSelectedItem());
	        	  Dao.getConnection();
		         Dao.edituserinfo(info);
	          }
	      });
	     shuakabtn.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	  try{
	        		    JDBC sql=new JDBC();
					  	Connection con=null;
						try {
							con = sql.getConnection();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
		        	    Statement stmt = con.createStatement();// 创建SQL命令对象
		  			    ResultSet rs = stmt.executeQuery("SELECT * FROM student where 姓名='"+txtname.getText().trim()+"'");  
		  			    while(rs.next()){
		  			    	txtcardnum.setText(rs.getString("ID"));	
		  			    	txtPassWord.setText(rs.getString("密钥"));
		  			    	jComboBox2.setSelectedItem(rs.getString("班级"));
		  			    	jComboBox4.setSelectedItem(rs.getString("权限"));
		  			    	jComboBox5.setSelectedItem(rs.getString("状态"));
		  			    }
	        	        }catch (SQLException e) {
	      		  	          //显示没有该用户
	  		  	        } 
	        	  if (txtname.getText().trim().isEmpty()){
						 UnitCommon.showMessage("姓名不允许为空！");
						}
	          }
	      });
	     cancelbtn.addActionListener(new ActionListener()
	      {
	    	  public void actionPerformed(ActionEvent Event) 
	    	  {     
	    		 txtcardnum.setText("");
	    		 txtname.setText("");
	    		 txtPassWord.setText("");
	    		 jComboBox2.setSelectedIndex(0);
	    		 jComboBox4.setSelectedIndex(0);
	    		 jComboBox5.setSelectedIndex(0);
	    	  }
	      });
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img,0,0, getSize().width, getSize().height, null);
	}
	/*
	 * 设置编辑界面的整体字体
	 */
}