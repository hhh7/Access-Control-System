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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import entity.Dao;
import project.JDBC;
import tool.UnitCommon;

public class card_guashi extends JPanel
{
	private static final long serialVersionUID = 1L;
	static  JTextField txtcardnum;//卡号
	static  JTextField txtname;//姓名
	static JLabel lblPassWord;//密码
	static  JLabel lblname;//卡片主人的姓名
	static JPasswordField txtPassWord;//密码
	static JButton findbtn;//刷卡按钮
    String[] status={"正常","挂失"};
    JComboBox jComboBox5=new JComboBox(status);
	 String[] juese={"选择角色   ","普通用户  ","管理员"};
	JComboBox jComboBox1=new JComboBox(juese);
    private ImageIcon icon;
	private Image img;
	
	///下面方法的含义是可以在容器中添加组件	
		public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h)
	    {
	        constraints.gridx=x;
	        constraints.gridy=y;
	        constraints.gridwidth=w;
	        constraints.gridheight=h;
	        add(c,constraints);
	    }   
		card_guashi()
		{
			 icon=new ImageIcon("images/mainform.png");
			 img=icon.getImage(); 
			  JPanel pane=new JPanel();
			  Font font = new Font("宋体",Font.PLAIN,20);
			  UIManager.put("Button.font", font); 
			  UIManager.put("Label.font", font);
			  UIManager.put("MenuItem.font", font);
		  	  Toolkit kit=Toolkit.getDefaultToolkit();//得到默认toolkit
		      Dimension screenSize=kit.getScreenSize();//获得屏幕尺寸
		      int width=screenSize.width;
		      int height=screenSize.height;
		      int x=(width-WIDTH)/2;
		      int y=(height-HEIGHT)/2;
			  GridBagLayout lay=new GridBagLayout(); 
		      setLayout(lay);
		      JLabel title=new JLabel("RFID卡信息挂失");
		      JLabel carnum=new JLabel("卡号 :");
		      lblname= new JLabel("姓名 :");
		//      JLabel lblPassWord=new JLabel("密码");
		      JLabel lblStatus=new JLabel("状态");
		      txtcardnum=new JTextField(18);
		      /*txtcardnum.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,16));
			  //设置文本的水平对齐方式
			  txtcardnum.setHorizontalAlignment(JTextField.CENTER);*/
		      txtPassWord=new JPasswordField(18);
		      txtname=new JTextField(18);
		      JButton querenbtn=new JButton("确认");
		      JButton reregisterbtn=new JButton("重新注册");
		      findbtn=new JButton("查找");
		      GridBagConstraints constraints=new GridBagConstraints();
		      constraints.fill=GridBagConstraints.NONE;
		      constraints.weightx=0;
		      constraints.weighty=0.8;
		      constraints.insets = new Insets(0,10, 5, 20);
		      add(title,constraints,0,0,4,1);    
		      add(lblname,constraints,0,1,1,1); add(txtname,constraints,1,1,1,1);  add(findbtn,constraints,2,1,1,1); 
		      add(carnum,constraints,0,2,1,1); add(txtcardnum,constraints,1,2,1,1);      
		      add(lblStatus,constraints,0,3,1,1);  add(jComboBox5,constraints,1,3,1,1);
		      add(querenbtn,constraints,1,4,1,1);
		  //    add(reregisterbtn,constraints,4,1,1,1); 
		      //按下确认键后将此卡在数据库中的状态由正常变为挂失
		     querenbtn.addActionListener(new ActionListener()
		      {
		    	  public void actionPerformed(ActionEvent Event) 
		    	  {     
		    		     Dao.getConnection();
			        	 Dao.guashi((String) jComboBox5.getSelectedItem(),txtcardnum.getText().trim());
		    	  }
		      });
		     //按下重新注册按钮后跳转到另外一个panel，
		      findbtn.addActionListener(new ActionListener()
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
			  			    }
		        	        }catch (SQLException e) {
		      		  	          //显示没有该用户
		  		  	        } 
		    		  if (txtname.getText().trim().isEmpty()){
							 UnitCommon.showMessage("姓名不允许为空！");
							}
		    	  }
		      });
		     //当输入卡号文本框后检测到输入后，同样可以将数据库中此卡的状态改变
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(img,0,0, getSize().width, getSize().height, null);
		}
}