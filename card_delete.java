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

public class card_delete extends  JPanel
{
	//JPanel a=new GetPanel(500,375,"images/mainform.png");
	private static final long serialVersionUID = 1L;
	private JLabel imageLabel=new JLabel();
	static  JLabel lblcardnum;//卡号标签
	static  JLabel lblname;//卡片主人的姓名
	static  JLabel lblmajor;//卡片主人的专业
	static  JLabel lblclass;//卡片主人的班级
	static  JLabel lbllimit;//卡片主人的权限
	static  JLabel lblstatus;//RFID卡的状态
	
	static  JTextField  txtcardnum;//卡号
	static  JTextField txtname;//姓名
	static  JTextField txtmajor;//专业
	static  JTextField txtclass;//班级
	static  JTextField txtlimit;//权限
	static  JTextField txtstatus;//RFID卡的状态
	static JPasswordField txtPassWord;//密码
	
	static JButton findbtn;//刷卡按钮
	static JButton delbtn;//删除按钮
	
	final static JPopupMenu popup = new JPopupMenu();
	JMenuItem del=new  JMenuItem("删除");
    JMenuItem add=new  JMenuItem("增加");
    private ImageIcon icon;
	private Image img;
  /*  private ImageIcon icon;
	private Image img;
	public void paintCoponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0,null);
	}*/
	public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h)
    {
        constraints.gridx=x;
        constraints.gridy=y; 
        constraints.gridwidth=w;
        constraints.gridheight=h;
        add(c,constraints);
    }   
	card_delete()
	{      
		//JPanel pane=new GetPanel(100,200,"images/mainform.png");  
		  icon=new ImageIcon("images/mainform.png");
		  img=icon.getImage(); 
		  Font font = new Font("宋体",Font.PLAIN,20);
		  UIManager.put("Button.font", font); 
		  UIManager.put("Label.font", font);
		  UIManager.put("MenuItem.font", font);
	      findbtn=new JButton("查找");
	      delbtn=new JButton("确认");
	      lblname=new JLabel("姓名");//存在重名该怎么办啊？在加入数据库的时候就进行标注
	      lblcardnum=new JLabel("卡号  :");
	      lblname= new JLabel("姓名 :");
	      lblmajor=new JLabel("专业 :");
	      lblclass=new JLabel("权限 :");
	      lblstatus=new JLabel("状态 :");
	      JLabel lblPassWord=new JLabel("密码");
	      
	      txtcardnum=new JTextField (18);
	    /*  txtcardnum.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,16));
		  //设置文本的水平对齐方式
		  txtcardnum.setHorizontalAlignment(JTextField.CENTER);*/
	      txtcardnum.setEditable(false);
	      txtname=new JTextField(18);
	      txtmajor=new JTextField(18);
	      txtclass=new JTextField(18);
	      txtlimit=new JTextField(18);
	      txtstatus=new JTextField(18);
	      txtPassWord=new JPasswordField(18);
	      JLabel title=new JLabel("RFID卡信息删除");
	  	  Toolkit kit=Toolkit.getDefaultToolkit();//得到默认toolkit
	      Dimension screenSize=kit.getScreenSize();//获得屏幕尺寸
	      int width=screenSize.width;
	      int height=screenSize.height;
	      int x=(width-WIDTH)/2;
	      int y=(height-HEIGHT)/2;
		  GridBagLayout lay=new GridBagLayout(); 
		  setLayout(lay);
	      GridBagConstraints constraints=new GridBagConstraints();
	      constraints.insets = new Insets(0,10, 5, 10);
	      constraints.weightx=0;
	      constraints.weighty=0.8;
	      constraints.fill=GridBagConstraints.CENTER;//单元格在其显示区域中的填充方式
	 //   constraints.anchor=GridBagConstraints.CENTER;
//        使用网格组布局管理器添加控件
	     add(title,constraints,2,0,2,1);    
	     add(lblname,constraints,1,1,1,1);add(txtname,constraints,2,1,1,1); 
	     add(findbtn,constraints,3,1,1,1);
	     add(lblcardnum,constraints,1,2,1,1);add(txtcardnum,constraints,2,2,1,1);
	  // add(lblPassWord,constraints,0,2,1,1);add(txtPassWord,constraints,1,2,1,1); 
	     add(delbtn,constraints,3,3,1,1); 
	     /*add(lblname,constraints,0,2,1,1);this.add(txtname,constraints,1,2,1,1); */
	    // add(lblmajor,constraints,2,2,1,1);this.add(txtmajor,constraints,3,2,0,1); 
	     /*this.add(lblclass,constraints,2,2,1,1);this.add(txtclass,constraints,3,2,1,1); 
	     this.add(lblstatus,constraints,0,3,1,1); this.add(txtstatus,constraints,1,3,1,1);*/
	    
	    /* icon =new ImageIcon("images/mainform.png");
	     img = icon.getImage();*/
	     //添加背景图片
	   /*  this.add(imageLabel);
	     ImageIcon img=new ImageIcon("images/mainform.png");
	     imageLabel.setIcon(img);
	     imageLabel.setText(null);*/
	     //按下删除按钮的时候同样进行删除操作
	     delbtn.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	  if(txtcardnum.getText().trim()==null)
	        	  {
	        		  //如果ID为空报错
	        	  }
	        	 Dao.getConnection();
	        	 Dao.deletuserinfo(txtcardnum.getText().trim());
	          }
	      });
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
	}	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img,0,0, getSize().width, getSize().height, null);
	}
}