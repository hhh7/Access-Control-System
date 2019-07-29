import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import entity.User;
import entity.status;
import tool.UnitCommon;

public class normaluser_MainForm extends JFrame {
	private JPanel pnlTool1;//设置一个panel,用于添加工具条

	private JPanel pnlContent;//设置中间的Panel，添加组件
	private JPanel pnlStatus;//设置一个状态条，用于显示当前登录用户的信息
	private JLabel lblCurrentUser;//设置当前的登录用户标签，
	static  JTextField carnuminput;//卡号
	static  JTextField mimainput;//密码

	private static final int WIDTH = 600;//设置整体界面的宽度
	private static final int HEIGHT = 550;//设置整体界面的高度
	//Container con=new Container();
	private User currentUser;//设置一个变量，表示当前用户
	//定义一个构造器，
	public void setUser(User user){
		this.currentUser = user;
	}

	public normaluser_MainForm(User user) {
		this.currentUser = user;
		init();		
	}

	public normaluser_MainForm() {
		init();
	}
	private void init() {
		this.setSize(WIDTH, HEIGHT);//设置主界面的大小
		this.setResizable(true);//设置主界面的大小可调
		this.setTitle("新疆大学信息科学与工程学院实验室门禁管理系统");//设置主界面的标题
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//设置窗体关闭按钮的动作为退出
		/* ImageIcon img = new ImageIcon("bg//1.gif");//这是背景图片,
		 JLabel imgLabel = new JLabel(img);//将背景图放在标签里。		 
		 this.setContentPane(con);*/	
		this.setLocationRelativeTo(null);//public void setLocationRelativeTo(Component c)设置窗口相对于指定组件的位置。 
	  //如果组件当前未显示，或者 c 为 null，则此窗口将置于屏幕的中央。中点可以使用 GraphicsEnvironment.getCenterPoint 确定。 
		try {
			this.setIconImage(ImageIO.read(new File("images/door_open.png")));//设置界面的图标
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initUI();	//初始化界面	
		registerLoginUser();//调用registerLoginUser方法
	}
	
	//获得当前登录用户的名字以及系统时间
	private void registerLoginUser()
	{
		String info = String.format("当前用户：%s   登录系统时间:%s", currentUser.getUserName(),
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));//将获得的信息存储在变量info中
		lblCurrentUser.setText(info);//将获得的信息给lblCurrentUser
	}
	//初始化界面，往界面中添加组件
	private void initUI() {
		/*pnlTool1 = UnitCommon.createPanel(true, new Dimension(WIDTH,45),new BorderLayout(),
				BorderFactory.createRaisedBevelBorder(), null);//创建一个panel
		//pnlTool1.setBackground(Color.decode("#CBE1FC"));
		this.add(pnlTool1, BorderLayout.NORTH);//将panel1添加在主界面MainForm中
*/		JMenuBar menubar1=new JMenuBar();//设置一个菜单条
		//设置菜单项
		//JMenu menu1=new JMenu("用户出入记录");
		JMenu menu2=new JMenu("用户信息");
		//JMenu menu3=new JMenu("RFID管理");
		//创建右键菜单
		JPopupMenu pop=new JPopupMenu();
		this.setJMenuBar(menubar1);
		//menubar1.add(menu1);
		menubar1.add(menu2);		
	   // menubar1.add(menu3);
		//设置子菜单项
		/*JMenuItem item1=new JMenuItem("按ID查询",new ImageIcon("images/search.png"));
		JMenuItem item2=new JMenuItem("按姓名查询");
		JMenuItem item3=new JMenuItem("按时间查询");
		JMenuItem item4=new JMenuItem("按门号查询");*/
	//	JMenuItem item5=new JMenuItem("用户出入记录");
		
		
		JMenuItem item5=new JMenuItem("用户基本信息",new ImageIcon("images/group.png"));
		
		JMenuItem item6=new JMenuItem("RFID制卡",new ImageIcon("images/add.png"));
		JMenuItem item7=new JMenuItem("RFID卡挂失");
		JMenuItem item10=new JMenuItem("RFID卡删除",new ImageIcon("images/delete.png"));
		JMenuItem item11=new JMenuItem("RFID卡修改",new ImageIcon("images/edit.png"));
		
		JMenuItem item8=new JMenuItem("门状态查询");
		JMenuItem item9=new JMenuItem("退出系统",new ImageIcon("images/exit.png"));
		//往菜单项中添加子菜单
		/*menu1.add(item1);menu1.addSeparator();
		menu1.add(item2);menu1.addSeparator();
		menu1.add(item3);menu1.addSeparator();
		menu1.add(item4);*/
	//	menu1.add(item5);menu1.addSeparator();
		
		menu2.add(item5);
		
		/*menu3.add(item6);menu3.addSeparator();
		menu3.add(item7);menu3.addSeparator();
		menu3.add(item10);menu3.addSeparator();
		menu3.add(item11);
		*/
		pop.add(item8);
		pop.add(item9);
		
		//con.add(bar);
	    //创建一个panel
		pnlContent = UnitCommon.createPanel(true, new Dimension(WIDTH, 475),
				new BorderLayout(), null,"images/mainform.png");
		//pnlContent.setBackground(Color.blue);
	    //pnlContent.add(pop);
		//Container contentPane = this.getContentPane();
		this.add(pnlContent, BorderLayout.CENTER);
		pnlContent.setBorder(new LineBorder(new Color(0, 0, 0)));
	//	showDialog();
		pnlStatus = UnitCommon.createPanel(true,new Dimension(WIDTH, 25),
				new FlowLayout(FlowLayout.LEFT),
				BorderFactory.createLoweredBevelBorder(), null);
	   //pnlStatus.setBackground(Color.green);
		this.add(pnlStatus, BorderLayout.SOUTH);
		lblCurrentUser = new JLabel();
		pnlStatus.add(lblCurrentUser);
		
		//按照RFID卡的ID号对此ID卡的用户这段时间的出入记录进行查询
		/*item1.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	   pnlContent.removeAll();
	        	   find_ID  a=new find_ID();
	        	   pnlContent.add(a,BorderLayout.CENTER);
	        	   showDialog();
	          }
	      });
		//按照姓名查询
		item2.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	  pnlContent.removeAll();
	        	  find_Name b=new find_Name();
	        	  pnlContent.add(b,BorderLayout.CENTER);
	        	  showDialog();
	          }
	      });
		//按用户出入时间查询
		item3.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {	        	 
	            	pnlContent.removeAll();
	            	find_Time c=new find_Time();
	            	pnlContent.add(c,BorderLayout.CENTER);
	            	showDialog();	
	          }
	      }); 
		//按照门号来查询用户的出入记录
		item4.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
		        	pnlContent.removeAll();
	          		find_Menhao c=new find_Menhao();
	          		pnlContent.add(c,BorderLayout.CENTER);
	          		showDialog();
	          }
	      });*/
		//显示用户的基本信息
		item5.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	  	pnlContent.removeAll();
	          		normaluserinfo c=new normaluserinfo();
	          		pnlContent.add(c,BorderLayout.CENTER);
	          		showDialog();
	          }
	      }); 
		//进行制卡功能
		item6.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	    pnlContent.removeAll();
	          		card_register c=new card_register();
	          	//	c.show(true);
	          		pnlContent.add(c,BorderLayout.CENTER);
	          		showDialog();
	          }
	      });
		//进行RFID卡的挂失
		item7.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	  pnlContent.removeAll();
          		  card_guashi c=new card_guashi();
          		  pnlContent.add(c,BorderLayout.CENTER);
          		  showDialog();
	          }
	      });
		//进行RFID卡的删除
		item10.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	  pnlContent.removeAll();
        		  card_delete c=new card_delete();
        		  pnlContent.add(c,BorderLayout.CENTER);
        		  showDialog();
	          }
	      });
		//进行RFID卡的修改
		item11.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	  pnlContent.removeAll();
      		  card_delete c=new card_delete();
      		  pnlContent.add(c,BorderLayout.CENTER);
      		  showDialog();
	          }
	      });
		
		 this.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (e.getButton() == MouseEvent.BUTTON3) {
	                    // 弹出菜单
	                    pop.show(e.getComponent(), e.getX(), e.getY());
	                }
	            }
	        });
		 item8.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        	   pnlContent.removeAll();
	        	   status  a=new status();
	        	   pnlContent.add(a,BorderLayout.CENTER);
	        	   showDialog();
	          }
	      });
		 //右键退出菜单
		 item9.addActionListener(new ActionListener()
	      {
	          public void actionPerformed(ActionEvent Event) 
	          {
	        		int i=JOptionPane.showConfirmDialog(null,"是否真的需要退出门禁系统","退出确认对话框", 
	        				JOptionPane.YES_NO_CANCEL_OPTION);
	            	if(i==0)
	            	{
	            		System.exit(0);	   //强制JVM（虚拟机）退出         		
	            	}
	          }
	      });
	}
	public void showDialog() {
		this.setVisible(true);
		/*this.pack();
		this.size();*/
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}	
}