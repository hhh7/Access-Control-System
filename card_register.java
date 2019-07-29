import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import tool.UnitCommon;
import entity.Dao;
import entity.Userinfo;

public class card_register extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	static  JLabel lblcardnum;//卡号标签
	static JLabel lblpassword;//RFID密钥
	static  JLabel lblname;//卡片主人的姓名
	static  JLabel lbllimit;//卡片主人的权限
	static  JLabel lblclass;//卡片主人的班级	
	static  JLabel lblstatus;//RFID卡的状态
	String[] juese={"选择角色   ","普通用户  ","管理员"};
	JComboBox jComboBox1=new JComboBox(juese);
	
	static  JTextField txtcardnum;//卡号
	static JPasswordField txtPassWord;//密钥
	static  JTextField txtname;//姓名
	static  JTextField txtmajor;//专业
	static  JTextField txtclass;//班级
	static  JTextField txtlimit;//权限
	static  JTextField txtstatus;//RFID卡的状态
	static JButton shuakabtn;//刷卡按钮
	static JButton storebtn;//修改按钮
	static JButton clearbtn;//清空按钮
	private ImageIcon icon;
	private Image img;
	private JLabel imageLabel =new JLabel();
	private Userinfo info=new Userinfo();
	///下面方法的含义是可以在容器中添加组件///此方法用来添加控件到容器中
	///按照网格组布局方式排列组件的方法
	///x指控件位于第几列。
	///y指控件位于第几行。
	///w指控件需要占几列。
	///h指控件需要占几行。	
	public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h)
    {
        constraints.gridx=x;
        constraints.gridy=y;
        constraints.gridwidth=w;
        constraints.gridheight=h;
        add(c,constraints);
    }   
	card_register()
	{    
		  icon=new ImageIcon("images/mainform.png");
		  img=icon.getImage(); 
		  JPanel pane=new JPanel();
		  pane.setOpaque( false );
		  Font font = new Font("宋体",Font.PLAIN,16);
		  UIManager.put("Button.font", font); 
		  UIManager.put("Label.font", font);
		  UIManager.put("MenuItem.font", font);
		  UIManager.put("Table.font", font); 
	  	  Toolkit kit=Toolkit.getDefaultToolkit();//设置位置居中
	      Dimension screenSize=kit.getScreenSize();//获得屏幕尺寸
	      int width=screenSize.width;
	      int height=screenSize.height;
	      int x=(width-WIDTH)/2;
	      int y=(height-HEIGHT)/2;
		  GridBagLayout lay=new GridBagLayout(); 
	      setLayout(lay);
	      shuakabtn=new JButton("刷卡");
	      storebtn=new JButton("确认");
	      clearbtn=new JButton("取消");
	      lblcardnum=new JLabel("卡号 ：");
	      lblpassword=new JLabel("密钥 : ");
	      lblname= new JLabel("姓名 ：");
	  //   lblmajor=new JLabel("专业 ：");
	      lblclass=new JLabel("班级 ：");
	      lbllimit=new JLabel("权限 ：");
	      lblstatus=new JLabel("状态 ：");
	//    lbldate= new JLabel("RFID卡注册时间 :");
	      
	      txtcardnum=new JTextField(18);
	      /*txtcardnum.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,16));
		  //设置文本的水平对齐方式
		  txtcardnum.setHorizontalAlignment(JTextField.CENTER);*/
	      txtPassWord=new JPasswordField (18);
	  	  txtPassWord.setEchoChar('*');	
	      txtname=new JTextField(18);
	      String[] Class={"电信13-1班","电信14-1班","电信15-1班","电信16-1班"};
	      JComboBox jComboBox2=new JComboBox(Class);
	      String[] Major={"电子信息工程","通信工程","计算机科学与技术"};
	      JComboBox jComboBox3=new JComboBox(Major);
	      String[] limit={"1225","3434","5221","4343","所有"};
	      JComboBox jComboBox4=new JComboBox(limit);
	      String[] status={"正常","挂失"};
	      JComboBox jComboBox5=new JComboBox(status);
	      JLabel title=new JLabel("RFID卡信息注册");
	      GridBagConstraints constraints=new GridBagConstraints();
	      constraints.weightx=0;///该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间 	     
	      constraints.weighty=0.8;//此方法设置组件垂直方向的拉伸幅度，如果为0就说明不拉伸，	  
	      constraints.insets = new Insets(0,10, 5, 20);
	      constraints.fill=GridBagConstraints.CENTER;//设置组件在panel中的填充方式
	      constraints.anchor=GridBagConstraints.CENTER;//设置组件在单元格中的对齐方式  	         
	    //使用网格组布局管理器添加控件
	      add(title,constraints,0,0,5,1);    
		  add(lblcardnum,constraints,0,1,1,1);add(txtcardnum,constraints,1,1,1,1); 
		  add(shuakabtn,constraints,2,1,1,1); 
		  add(lblpassword,constraints,0,2,1,1);add(txtPassWord,constraints,1,2,1,1); 
		  add(lblname,constraints,0,3,1,1);add(txtname,constraints,1,3,1,1); 
		  add(lblclass,constraints,0,4,1,1);add(jComboBox2,constraints,1,4,1,1);
		  add(lbllimit,constraints,0,5,1,1);add(jComboBox4,constraints,1,5,1,1);
		  add(lblstatus,constraints,0,6,1,1);add(jComboBox5,constraints,1,6,1,1);
		 add(clearbtn,constraints,0,7,1,1);add(storebtn,constraints,1,7,0,1);
	       //* 按下刷卡后，通过通信将卡号读取进来，并显示在卡号和密码的文本框中	       
	      shuakabtn.addActionListener(new ActionListener()
	      {
	    	  public void actionPerformed(ActionEvent Event) 
	    	  {     
	    		  
	    	  }
	      });	 
	      clearbtn.addActionListener(new ActionListener()
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
	    //* 按下确认键后，将卡号和密码存储进入数据库中，存储完成后显示提示用户数据已经存储完成。	       
	      storebtn.addActionListener(new ActionListener()
	      {
	    	  public void actionPerformed(ActionEvent Event) 
	    	  {     
			 if (txtcardnum.getText().trim().isEmpty()){
						 UnitCommon.showMessage("账号不允许为空！");
						}
			 else if (txtPassWord.getText().trim().isEmpty()){
				 UnitCommon.showMessage("密码不允许为空！");
				}
			 else if (txtname.getText().trim().isEmpty()){
					 UnitCommon.showMessage("姓名不允许为空！");
					}
			 else{
				 info.setID(txtcardnum.getText().trim());
	    		 info.setname(txtname.getText().trim());
	    		 info.setkey(txtPassWord.getText().trim());
	    		 info.setclasses((String) jComboBox2.getSelectedItem());
	    		 info.setlimit((String) jComboBox4.getSelectedItem());
	    		 info.setstuta((String) jComboBox5.getSelectedItem());
	    		 Connection con=Dao.getConnection();
					Dao.insertuserinfo(info);
			 }
	    	  }
	      });
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img,0,0, getSize().width, getSize().height, null);
	}
}