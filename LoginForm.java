import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import project.UDPRece;
import tool.UnitCommon;
import entity.Dao;
import entity.User;
import entity.status;

import java.sql.SQLException;
import java.util.*;
public class LoginForm extends JFrame implements ActionListener,ItemListener
{
		JLabel lbluser=new JLabel("账号:");
		JLabel lblpassword=new JLabel("密 码:");
		JLabel lblusertype=new JLabel("用户角色:");
		JButton btnlogin=new JButton("登录");
		JButton btncancel=new JButton("取消");
		int limit=0;
		String[] juese={"选择角色                 ","普通用户                  ","管理员                   "};
		JComboBox jComboBox1=new JComboBox(juese);
		ButtonGroup buttongroup=new ButtonGroup();
		JRadioButton manager=new JRadioButton("管理员");
		JRadioButton normaluser=new JRadioButton("普通用户");
		JTextField txtUserName=new JTextField(18);
		JPasswordField txtPassWord=new JPasswordField(18);
		public status sta=new status();
public LoginForm()
{
	super("登录窗口");
	setSize(300,200);
	setVisible(true);
	setResizable(false);
	//setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
	add(lbluser);
	add(txtUserName);	
	//add(jComboBox1);
	txtPassWord.setEchoChar('*');	
	add(lblpassword);
	add(txtPassWord);
	add(lblusertype);
	add(manager);
	add(normaluser);
	buttongroup.add(manager);
	buttongroup.add(normaluser);
	add(btnlogin);
	add(btncancel);
	manager.addItemListener(this);
	normaluser.addItemListener(this);
	btnlogin.addActionListener(this);
	btncancel.addActionListener(this);
	Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	setLocation((screen.width-300)/2,(screen.height-220)/2);
	ImageIcon img = new ImageIcon("images/login.png");// 这是背景图片
	JLabel imgLabel = new JLabel(img);// 将背景图放在标签里。
	this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的
																			// LayeredPane面板里。
	imgLabel.setBounds(0, 0, this.getWidth(), this.getHeight());// 设置背景标签的位置
//	imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());// 设置背景标签的位置
	Container cp = this.getContentPane();
	cp.setLayout(null); // 这里选择绝对布局管理器，对于边界布局管理器，放入控件后，无法显示背景图片；因为将整个面板都填充满了；
	((JPanel) cp).setOpaque(false); // 这样就能显示出背景图片出来了
	try {
		this.setIconImage(ImageIO.read(new File("images/door_in.png")));
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}//设置界面的图标
	addWindowListener(new WindowAdapter()
	{
		public void windowClosing(WindowEvent e)
		{
			dispose();
			System.exit(0);
		}
	});
	txtPassWord.addKeyListener(new KeyListener ()
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
				if (!checkInput()) return;		
				String userName = txtUserName.getText().trim();
				String passWord = txtPassWord.getText();
				
				try {
					Dao.getConnection();
					if (Dao.checkLogin(userName, passWord, limit))
					{
						User user = new User(userName,passWord);
						if(limit==1)
						{
							manage_MainForm mainForm = new manage_MainForm(user,sta);
							//添加线程
							new Thread(new UDPRece(sta)).start();		
							mainForm.showDialog();
							dispose();
						}
						else if(limit==0){
							normaluser_MainForm mainForm=new normaluser_MainForm(user);
							mainForm.showDialog();
							dispose();
						}
					}
					else{
						UnitCommon.showError("帐号或密码或权限有误，请重试！");
						txtUserName.requestFocus();
						txtUserName.selectAll();
					}
				} catch (SQLException e1) {
					UnitCommon.showError("数据库连接失败！");
				}	
		}
		}
		@Override//释放键的时候产生此事件
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
}
private boolean checkInput(){
	if (txtUserName.getText().trim().isEmpty()){
		UnitCommon.showMessage("帐号不允许为空！");
		txtUserName.setFocusable(true);
		txtUserName.requestFocus();
		return false;
	}	
	if (txtPassWord.getText().isEmpty()){
		UnitCommon.showMessage("密码不允许为空！");
		txtPassWord.requestFocus();
		return false;
	}	
	return true;
}
public void itemStateChanged(ItemEvent e)
{
	if(e.getSource()==manager)
	{
		limit=1;
		txtPassWord.requestFocus(true);
	}else if(e.getSource()==normaluser){
		limit=0;
		txtPassWord.requestFocus(true);
	}
}
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==btnlogin)
	{
		if (!checkInput()) return;		
		String userName = txtUserName.getText().trim();
		String passWord = txtPassWord.getText();
		
		
		try {
			Dao.getConnection();
			if (Dao.checkLogin(userName, passWord, limit))
			{
				User user = new User(userName,passWord);
				if(limit==1)
				{
					manage_MainForm mainForm = new manage_MainForm(user,sta);
					//添加线程
					new Thread(new UDPRece(sta)).start();		
					mainForm.showDialog();
					dispose();
				}
				else if(limit==0){
					normaluser_MainForm mainForm=new normaluser_MainForm(user);
					mainForm.showDialog();
					dispose();
				}
			}
			else{
				UnitCommon.showError("帐号或密码或权限有误，请重试！");
				txtUserName.requestFocus();
				txtUserName.selectAll();
			}
		} catch (SQLException e1) {
			UnitCommon.showError("数据库连接失败！");
		}	
	}
else if(e.getSource()==btncancel)
{
	dispose();
	System.exit(0);
}
}

public static void main(String args[]){
	new LoginForm();
}
}