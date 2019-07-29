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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import project.JDBC;
public class find_Limit extends JPanel
{
	 private static final long serialVersionUID = 1L;
	 static JPanel pane1;
	 static JPanel pane;
	 String[] limit={"选择门号","1225","3434","所有"};
	 JComboBox jComboBox1=new JComboBox(limit);
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
	find_Limit()
	{   
		icon=new ImageIcon("images/mainform.png");
		img=icon.getImage(); 
		pane=new JPanel();
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
		JLabel title=new JLabel("按门号查询用户出入记录");
		JButton findbtn=new JButton("查询");
		findbtn.setBounds(20,30,20,5);
	    DefaultTableModel userinfo = new DefaultTableModel();//建立默认的JTable模型
	    DefaultTableModel searchinfo = new DefaultTableModel();
	    JTable table=new JTable();//创建一个新表格
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
				ResultSet rs = stmt.executeQuery("SELECT * FROM history order by 出入时间 desc");
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
		constraints1.weightx=0;//设置横向的缩放比例。
		constraints1.weighty=0;
		constraints1.insets = new Insets(0,10, 5, 10);
		constraints1.fill=GridBagConstraints.RELATIVE; //设置组件的填充方向
		constraints1.anchor=GridBagConstraints.WEST; //设置组件的填充方向
		constraints.weightx=0.0001;//设置横向的缩放比例。
		constraints.weighty=0;
		constraints.insets = new Insets(0,10, 5, 10);
		constraints.fill=GridBagConstraints.CENTER; //设置组件的填充方向
		constraints2.weightx=1.0;//设置横向的缩放比例。
		constraints2.weighty=0.001;
		constraints2.insets = new Insets(0,10, 5, 10);
		constraints2.fill=GridBagConstraints.BOTH; //设置组件的填充方向
		constraints3.weightx=0;//设置横向的缩放比例。
		constraints3.weighty=0;
		constraints3.insets = new Insets(0,10, 5,1000);
		constraints3.fill=GridBagConstraints.BOTH; //设置组件的填充方向
	    add(title,constraints,0,0,2,1);                 //使用网格组布局添加控件
	    add(jComboBox1,constraints1,0,1,1,1);  
	    add(findbtn,constraints1,1,1,1,1);
	    add(scrollPane,constraints2,0,2,12,2);
	    //通过下拉列表框选取门号，来查询数据库中相应姓名的其它所有信息，并且将之显示在表格中。 
	    findbtn.addActionListener(new ActionListener()
	      {
			@SuppressWarnings("null")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jComboBox1.getSelectedItem().equals("所有"))
					table.setModel(userinfo);
				else{
				 table.setModel(userinfo);
					int p=0;
					Vector<String> columnName = new Vector<String>();//字段名
					  Vector<Vector<Object>> dataVector = new  Vector<Vector<Object>>(); //存储所有数据，里面每个小的Vector是存单行的
					  columnName.add("ID");
					  columnName.add("姓名");
					  columnName.add("出入门禁号");
					  columnName.add("出入时间");
					  columnName.add("结果");
					 for(int i = 0; i <table.getRowCount();i++)
	       		  {
//						 System.out.println(table.getValueAt(i, 4));
	      	           if(table.getValueAt(i, 2).equals(jComboBox1.getSelectedItem()))//这个是判断文本框中输入的内容与表格中的哪一项是一样的,这句代码对了
	      	           {	
	      	        	   int k=0;
	      	        	   k=i;
	      	        	   p=p+1;
	      	        	   Vector<Object> vec = new Vector<Object>();//就是这个存单行的，最后放到上面的大的Vector里面
	      	        	   for(int j=0;j<5;j++){
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
				}}});
	      pane.setVisible(true);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img,1,0, getSize().width, getSize().height, null);
	}
}