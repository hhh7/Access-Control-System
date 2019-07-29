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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import project.JDBC;

public class find_zonghe extends JPanel
{
	 private static final long serialVersionUID = 1L;
	 static  JLabel lblcardnum;//卡号标签
	 private JTextField txtcardnum;//卡号的文本框
	 private JButton shuakabtn;//刷卡按钮
	 private JComboBox jcbYear;//年份的下拉列表框
	 private JComboBox jcbMonth;//月的下拉列表框
	 private JComboBox jcbDay;//日期的下拉列表框
	 private JComboBox jcbYear1;//年份的下拉列表框
	 private JComboBox jcbMonth1;//月的下拉列表框
	 private JComboBox jcbDay1;//日的下拉列表框
	 static  JLabel lbllimit;//卡片主人的权限，即门号
	 static JLabel lbldate;//选择日期
	 private JLabel lblto;//到
	 private Calendar cal;
	 private Calendar cal1;
	 String[] limit={"1225","2551","3434","4343","所有"};
     JComboBox jComboBox4=new JComboBox(limit);
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
	find_zonghe()
	{    
		icon=new ImageIcon("images/mainform.png");
		img=icon.getImage(); 
		Font font = new Font("宋体",Font.PLAIN,16);
		UIManager.put("Button.font", font); 
		UIManager.put("Label.font", font);
		UIManager.put("MenuItem.font", font);
		UIManager.put("Table.font", font); 
		jcbYear = new JComboBox();
		jcbMonth = new JComboBox();
		jcbDay = new JComboBox();
		jcbYear1 = new JComboBox();
		jcbMonth1 = new JComboBox();
		jcbDay1 = new JComboBox();
		jcbYear.setOpaque(false);
		jcbMonth.setOpaque(false);
		jcbDay.setOpaque(false);
		jcbYear1.setOpaque(false);
		jcbMonth1.setOpaque(false);
		jcbDay1.setOpaque(false);
		jcbYear.setEditable(false);
		jcbMonth.setEditable(false);
		jcbDay.setEditable(false);
		jcbYear1.setEditable(false);
		jcbMonth1.setEditable(false);
		jcbDay1.setEditable(false);
		jcbYear.setBounds(0, 0, 20, 20);
		jcbMonth.setBounds(0, 0, 50, 20);
		jcbDay.setBounds(0, 0, 70, 20);
		jcbYear1.setBounds(0, 0, 70, 20);
		jcbMonth1.setBounds(0, 0, 70, 20);
		jcbDay1.setBounds(0, 0, 70, 20);
		JPanel pane=new JPanel();
		Toolkit kit=Toolkit.getDefaultToolkit();//设置位置居中
	    Dimension screenSize=kit.getScreenSize();//获得屏幕尺寸
	    int width=screenSize.width;
	    int height=screenSize.height;
	    int x=(width-WIDTH)/2;
	    int y=(height-HEIGHT)/2; 
		GridBagLayout lay=new GridBagLayout();
		setLayout(lay);
		JLabel title=new JLabel("综合查找记录");
		JLabel lbllimit=new JLabel("门号  ：");
		JLabel lbldate=new JLabel("日期 ：");
		lblto=new JLabel("至");
		JButton shuakabtn=new JButton("刷卡");
		final JTextField carnuminput=new JTextField(15);//使用文本框获得卡ID
		carnuminput.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,16));
	    //设置文本的水平对齐方式
		carnuminput.setHorizontalAlignment(JTextField.CENTER);
		JButton findbtn=new JButton("查询");
	    DefaultTableModel userinfo = new DefaultTableModel();//建立默认的JTable模型
	    JTable table=new JTable();//创建一个新表格
	    DefaultTableModel searchinfo = new DefaultTableModel();
		try{
	    	Vector<String> columnName = new Vector<String>();//字段名
		  	Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的	
	    	columnName.add("ID");
		  	columnName.add("姓名");
		  	columnName.add("出入门禁号");
		  	columnName.add("出入时间");
		  	columnName.add("结果");
		  	JDBC sql=new JDBC();
		  	Connection con=null;
			try {
				con = sql.getConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		  	Statement stmt = con.createStatement();// 创建SQL命令对象
			ResultSet rs = stmt.executeQuery("SELECT * FROM history");
		  	while(rs.next()){
		  		Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
		  	for(int i=1;i<=5;i++){
		  	vec.add(rs.getObject(i));
		  	}
		  	dataVector.add(vec);
		  	}
		  	userinfo.setDataVector(dataVector, columnName);//设定模型数据和字段
		  	} catch (SQLException e) {
		  	e.printStackTrace();
		  	}
	    table.setModel(userinfo);
		table.setPreferredScrollableViewportSize(new Dimension(getSize().width,300));//设置表格的大小
		JScrollPane scrollPane=new JScrollPane(table);//建立一个带滚动条的pane，将表格添加进去


		GridBagConstraints constraints=new GridBagConstraints();
		GridBagConstraints constraints1=new GridBagConstraints();
		GridBagConstraints constraints2=new GridBagConstraints();
		GridBagConstraints constraints3=new GridBagConstraints();
		GridBagConstraints constraints4=new GridBagConstraints();
		GridBagConstraints constraints5=new GridBagConstraints();
		GridBagConstraints constraints6=new GridBagConstraints();
		constraints1.weightx=0;//设置横向的缩放比例。
		constraints1.weighty=0;
		constraints1.insets = new Insets(0,10, 5, 0);
		constraints1.fill=GridBagConstraints.BOTH; //设置组件的填充方向
		constraints.weightx=0.0001;//设置横向的缩放比例。
		constraints.weighty=0;
		constraints.insets = new Insets(0,10, 5, 100);
		constraints.fill=GridBagConstraints.CENTER; //设置组件的填充方向
		constraints2.weightx=1.0;//设置横向的缩放比例。
		constraints2.weighty=0.001;
		constraints2.insets = new Insets(0,10, 5, 10);
		constraints2.fill=GridBagConstraints.BOTH; //设置组件的填充方向
		constraints3.weightx=0;//设置横向的缩放比例
		constraints3.weighty=0;
		constraints3.insets = new Insets(0,5,5,0);
		constraints3.fill=GridBagConstraints.NONE; //设置组件的填充方向
		constraints4.weightx=0;//设置横向的缩放比例。
		constraints4.weighty=0;
		constraints4.insets = new Insets(0,0,5,0);
		constraints4.fill=GridBagConstraints.CENTER; //设置组件的填充方向
		constraints5.weightx=0;//设置横向的缩放比例。
		constraints5.weighty=0;
		constraints5.insets = new Insets(0,0,0,5);
		constraints5.fill=GridBagConstraints.RELATIVE; //设置组件的填充方向
		constraints5.anchor=GridBagConstraints.WEST;
		constraints6.weightx=0;//设置横向的缩放比例。
		constraints6.weighty=0;
		constraints6.insets = new Insets(0,10, 5, 0);
		constraints6.fill=GridBagConstraints.CENTER; //设置组件的填充方向
	    add(title,constraints6,1,0,0,1); //使用网格组布局添加控件
	    add(shuakabtn,constraints1,0,1,1,1);add(carnuminput,constraints5,1,1,1,1);
	    add(lbldate,constraints5,2,1,1,1);
	    add(jcbYear,constraints5,3,1,1,1);add(jcbMonth,constraints5,4,1,1,1);	    
	    add(jcbDay,constraints5,5,1,1,1);
	    add(lblto,constraints5,6,1,1,1);
	    add(jcbYear1,constraints5,7,1,1,1);add(jcbMonth1,constraints5,8,1,1,1);	    
	    add(jcbDay1,constraints5,9,1,1,1);
	    add(lbllimit,constraints5,10,1,1,1); add(jComboBox4,constraints5,11,1,1,1);   
	    add(findbtn,constraints5,12,1,1,1);
	    add(scrollPane,constraints2,0,2,20,2);	
	    initi();
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jcbMonth) {
					UpdateDayData();
				} else if (e.getSource() == jcbYear) {
					UpdateDayData();
				}
			}
		};
		jcbYear.addActionListener(listener);
		jcbMonth.addActionListener(listener);
		//给另外一段时间段添加事件监听
				ActionListener listener1 = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == jcbMonth1) {
							UpdateDayData1();
						} else if (e.getSource() == jcbYear1) {
							UpdateDayData1();
						}
					}
				};
				jcbYear1.addActionListener(listener1);
				jcbMonth1.addActionListener(listener1);
	//通过读取卡号，来查询数据库中相应姓名的其它所有信息，并且将之显示在查询窗口的文本区域中。 
    findbtn.addActionListener(new ActionListener()
      {
		@Override
		public void actionPerformed(ActionEvent e) {
			int p=0;
	    	Vector<String> columnName = new Vector<String>();//字段名
		  	Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的	
		  	Vector<Vector<Object>> dataVector1 = new Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的
		  	Vector<Vector<Object>> dataVector2= new Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的
	    	columnName.add("ID");
		  	columnName.add("姓名");
		  	columnName.add("出入门禁号");
		  	columnName.add("出入时间");
		  	columnName.add("结果");
		  	JDBC sql=new JDBC();
			String date=getDate();
			String date1= getDate1();
			table.setModel(userinfo);
			try{
			  	Connection con=null;
				try {
					con = sql.getConnection();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			  	Statement stmt = con.createStatement();// 创建SQL命令对象
			  	ResultSet rs = stmt.executeQuery("select *  from  history  where 出入时间 between '"+date+"'and '"+date1+"' order by 出入时间 desc ");
			  	while(rs.next()){
			  		Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
			  	for(int i=1;i<=5;i++){
			  	vec.add(rs.getObject(i));
			  	}
			  	dataVector.add(vec);
			  	}
			  	searchinfo.setDataVector(dataVector, columnName);//设定模型数据和字段
			  	} catch (SQLException e1) {
			  		JOptionPane.showMessageDialog(pane,"该日期区间内无用户出入","提示",JOptionPane.WARNING_MESSAGE);
			  	}
			  if(jComboBox4.getSelectedItem().equals("所有"))
				  table.setModel(searchinfo);
			  else{
				   for(int i = 0; i <table.getRowCount();i++)
     		  {
					   table.setModel(searchinfo);
  				// System.out.println(table.getValueAt(i, 1));
    	           if(table.getValueAt(i,2).equals(jComboBox4.getSelectedItem()))//这个是判断文本框中输入的内容与表格中的哪一项是一样的,这句代码对了
    	           {	
    	        	   int k=0;
    	        	   k=i;
    	        	   p=p+1;
    	        	   Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
    	        	   for(int j=0;j<5;j++){
    				       vec.add(table.getValueAt(k, j));
    				   }
    	        	   dataVector1.add(vec);
    	           } 
     		   }
				   searchinfo.setDataVector(dataVector1, columnName);//设定模型数据和字段
				 table.setModel(searchinfo);
				 if(p==0){ 			
					 JOptionPane.showMessageDialog(pane,"无符合条件的出入记录","提示",JOptionPane.WARNING_MESSAGE);
					 p=0;
			  } 
			  }
	
				 if(carnuminput.getText().trim().isEmpty())
				 {
					
				 }
				 else{
					 p=0;
					  for(int i = 0; i <table.getRowCount();i++)
		     		  {
		  				// System.out.println(table.getValueAt(i, 1));
		    	           if(table.getValueAt(i,0).equals(carnuminput.getText().trim()))//这个是判断文本框中输入的内容与表格中的哪一项是一样的,这句代码对了
		    	           {	
		    	        	   int k=0;
		    	        	   k=i;
		    	        	   p=p+1;
		    	        	   Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
		    	        	   for(int j=0;j<5;j++){
		    				       vec.add(table.getValueAt(k, j));
		    				   }
		    	        	   dataVector2.add(vec);
		    	           } 
		     		   }
						 if(p==0){ 			
							 JOptionPane.showMessageDialog(pane,"无符合条件的出入记录","提示",JOptionPane.WARNING_MESSAGE);
							 p=0;
					  }
						 else{
							 searchinfo.setDataVector(dataVector2, columnName);//设定模型数据和字段
						 table.setModel(searchinfo);
						 }
				 }
				 
		  
		}});
    
    carnuminput.addKeyListener(new KeyListener ()
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
		    {  		int p=0;
				 	if (carnuminput.getText().trim()!=null)
				 	{

				 	}	
		}
		}
		@Override//释放键的时候产生此事件
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub				
		}
	});
    pane.setVisible(true);
}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img,0,0, getSize().width, getSize().height, null);
	}
	//下面这个函数是初始化，表示现在系统的时间
	private void initi() {
		cal = Calendar.getInstance();
		for (int i = cal.get(Calendar.YEAR) + 1; i >= 2000; i--)
			jcbYear.addItem(i);
		for (int i = 1; i <= 12; i++)
			jcbMonth.addItem(i);
		jcbYear.setSelectedItem(cal.get(Calendar.YEAR));
		jcbMonth.setSelectedItem(cal.get(Calendar.MONTH) + 1);
		int days = getDayByYearAndMonth(
				Integer.parseInt(jcbYear.getSelectedItem().toString()),
				Integer.parseInt(jcbMonth.getSelectedItem().toString()));
		for (int i = 1; i <= days; i++)
			jcbDay.addItem(i);
		jcbDay.setSelectedItem(cal.get(Calendar.DATE));
		//另外一个时间段的获取
				cal1 = Calendar.getInstance();
				for (int i = cal1.get(Calendar.YEAR) + 1; i >= 2000; i--)
					jcbYear1.addItem(i);
				for (int i = 1; i <= 12; i++)
					jcbMonth1.addItem(i);
				jcbYear1.setSelectedItem(cal1.get(Calendar.YEAR));
				jcbMonth1.setSelectedItem(cal1.get(Calendar.MONTH) + 1);
				int days1 = getDayByYearAndMonth1(
						Integer.parseInt(jcbYear1.getSelectedItem().toString()),
						Integer.parseInt(jcbMonth1.getSelectedItem().toString()));
				for (int i = 1; i <= days1; i++)
					jcbDay1.addItem(i);
				jcbDay1.setSelectedItem(cal1.get(Calendar.DATE));
		/*System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH)+1);
		System.out.println(cal.get(cal.get(Calendar.DATE)));//输出日期不对
*/	}
	/*
	 * 根据年份和月份判断日期下拉列表框的最大值
	 */

	private int getDayByYearAndMonth(int parseInt, int parseInt2) {
		// TODO Auto-generated method stub
		if (parseInt2 == 2)
			if ((parseInt % 4 == 0 && parseInt % 100 != 0)
					|| (parseInt% 100 == 0 && parseInt % 400 == 0))
				return 29;
			else
				return 28;
		else if (parseInt2 == 1 || parseInt2 == 3 || parseInt2 == 5 || parseInt2 == 7
				||parseInt2 == 8 || parseInt2 == 10 || parseInt2 == 12)
			return 31;
		else
			return 30;
	}
	
	private int getDayByYearAndMonth1(int parseInt1, int parseInt21) {
		// TODO Auto-generated method stub
		if (parseInt21 == 2)
			if ((parseInt1 % 4 == 0 && parseInt1 % 100 != 0)
					|| (parseInt1% 100 == 0 && parseInt1 % 400 == 0))
				return 29;
			else
				return 28;
		else if (parseInt21 == 1 || parseInt21 == 3 || parseInt21 == 5 || parseInt21 == 7
				||parseInt21 == 8 || parseInt21 == 10 || parseInt21 == 12)
			return 31;
		else
			return 30;
	}
	/*
	 * 获取几号
	 */
	protected void UpdateDayData() {
		// TODO Auto-generated method stub
		jcbDay.removeAllItems();
		int days = getDayByYearAndMonth(
				Integer.parseInt(jcbYear.getSelectedItem().toString()),
				Integer.parseInt(jcbMonth.getSelectedItem().toString()));

		for (int i = 1; i <= days; i++)
			jcbDay.addItem(i);
		jcbDay.setSelectedItem(cal.get(Calendar.DATE));
	}
	
	protected void UpdateDayData1() {
		// TODO Auto-generated method stub
		jcbDay1.removeAllItems();
		int days1 = getDayByYearAndMonth(
				Integer.parseInt(jcbYear1.getSelectedItem().toString()),
				Integer.parseInt(jcbMonth1.getSelectedItem().toString()));

		for (int i = 1; i <= days1; i++)
			jcbDay1.addItem(i);
		jcbDay1.setSelectedItem(cal1.get(Calendar.DATE));
	}
	/*
	 * 可以获取到你选的时间
	 */
	public String getDate() {
		String Oyear =jcbYear.getSelectedItem().toString();
		String OMonth =jcbMonth.getSelectedItem().toString();
		String Oday = (jcbDay.getSelectedItem()).toString();
		String data=null;
		if (Oyear != null && OMonth != null && Oday != null) {
			if(OMonth.length()==1)
				OMonth="0"+OMonth;
			if(Oday.length()==1)
				Oday="0"+Oday;
			data=Oyear+"-"+OMonth+"-"+Oday+" 00:00:00";
		}
		return data;
	}
	public String getDate1() {
		String Oyear =jcbYear1.getSelectedItem().toString();
		String OMonth =  jcbMonth1.getSelectedItem().toString();
		String Oday = jcbDay1.getSelectedItem().toString();
		String data=null;
		if (Oyear != null && OMonth != null && Oday != null) {
			if(OMonth.length()==1)
				OMonth="0"+OMonth;
			if(Oday.length()==1)
				Oday="0"+Oday;
			data=Oyear+"-"+OMonth+"-"+Oday+" 23:59:59";
		}
		return data;
	}
	public void setEnabled(boolean enabled) {
		jcbYear.setEnabled(enabled);
		jcbMonth.setEnabled(enabled);
		jcbDay.setEnabled(enabled);
		repaint();
	}
	
	public void setEnabled1(boolean enabled) {
		jcbYear1.setEnabled(enabled);
		jcbMonth1.setEnabled(enabled);
		jcbDay1.setEnabled(enabled);
		repaint();
	}
	/*
	 * 设置时间
	 */
	public void setDate(Date date) {
		cal.setTime(date);
		jcbYear.setSelectedItem(cal.get(Calendar.YEAR));
		jcbMonth.setSelectedItem(cal.get(Calendar.MONTH));
		jcbDay.setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
		
		cal1.setTime(date);
		jcbYear1.setSelectedItem(cal1.get(Calendar.YEAR));
		jcbMonth1.setSelectedItem(cal1.get(Calendar.MONTH));
		jcbDay1.setSelectedItem(cal1.get(Calendar.DAY_OF_MONTH));
		repaint();
	}
	public JComboBox getJcbYear() {
		return jcbYear;
	}

	public void setJcbYear(JComboBox jcbYear) {
		this.jcbYear = jcbYear;
	}

	public JComboBox getJcbMonth() {
		return jcbMonth;
	}

	public void setJcbMonth(JComboBox jcbMonth) {
		this.jcbMonth = jcbMonth;
	}

	public JComboBox getJcbDay() {
		return jcbDay;
	}

	public void setJcbDay(JComboBox jcbDay) {
		this.jcbDay = jcbDay;
	}
	
	public JComboBox getJcbYear1() {
		return jcbYear1;
	}

	public void setJcbYear1(JComboBox jcbYear) {
		this.jcbYear1= jcbYear;
	}

	public JComboBox getJcbMonth1() {
		return jcbMonth1;
	}

	public void setJcbMonth1(JComboBox jcbMonth) {
		this.jcbMonth1 = jcbMonth;
	}

	public JComboBox getJcbDay1() {
		return jcbDay1;
	}

	public void setJcbDay1(JComboBox jcbDay) {
		this.jcbDay1 = jcbDay;
	}
}