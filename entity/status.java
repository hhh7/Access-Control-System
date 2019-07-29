package entity;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class status extends JPanel
{
	private  final long serialVersionUID = 1L;
	static  JLabel lbllianjie;//连接状态
	static  JLabel lblmenjin;//门禁状态
	static  JLabel lbldbqd;//数据库驱动加载
	static  JLabel lbldb;//数据库连接状态
	static  JLabel lbls;//服务器端启动状态
	static  JLabel lblmenhao;//选择门号
	public  JTextArea txtlianjie;//连接状态
	public  JTextArea txtmenjin;//门禁状态
	public  JTextArea txtdbqd;//
	public  JTextArea txtdb;//
	public  JTextArea txts;//
	public JPanel pane1;
	String[] limit={"1225","2551","3434","4343"};
    JComboBox jComboBox4=new JComboBox(limit);
    private ImageIcon icon;
  	private Image img;
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
	
	public status()
	{    
		 icon=new ImageIcon("images/mainform.png");
		 img=icon.getImage(); 
		  JPanel pane=new JPanel();
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
	      JLabel lbllianjie=new JLabel("连接状态:");
		  JLabel lblmenjin=new JLabel("门禁状态:");
		  JLabel lbldbqd=new JLabel("数据库驱动加载:");
		  JLabel lbldb=new JLabel("数据库连接状态:");
		  JLabel lbls=new JLabel("服务器端启动状态:");
		  JLabel lblmenhao=new JLabel("门号：");
		  txtlianjie=new JTextArea(1,1);
	      txtlianjie.setEditable(false);
	      txtlianjie.setOpaque(false);
	      txtmenjin=new JTextArea(1,1);
	      txtmenjin.setEditable(false);
	      txtmenjin.setOpaque(false);
	      txtdbqd=new JTextArea(1,1);
	      txtdbqd.setEditable(false);
	      txtdbqd.setOpaque(false);
	      txtdb=new JTextArea(1,1);
	      txtdb.setEditable(false);
	      txtdb.setOpaque(false);
	      txts=new JTextArea(1,1);
	      txts.setEditable(false);
	      txts.setOpaque(false);
	      JLabel title=new JLabel("数据库及门禁系统状态信息");
	      GridBagConstraints constraints=new GridBagConstraints();
	      constraints.fill=GridBagConstraints.NORTHEAST;//设置组件在panel中的填充方式
	      constraints.anchor=GridBagConstraints.CENTER;//设置组件在单元格中的对齐方式
	      constraints.weightx=0;	     
	      constraints.weighty=0.8;
          //使用网格组布局管理器添加控件
	      add(title,constraints,0,0,4,1);          //列、行、水平间距、垂直间距，调整第二个参数是左右   
	      add(lblmenhao,constraints,0,1,1,1); add(jComboBox4,constraints,1,1,1,1);
	      add(lbllianjie,constraints,0,2,1,1); add(txtlianjie,constraints,1,2,1,1); 
	      add(lblmenjin,constraints,0,3,1,1); add(txtmenjin,constraints,1,3,1,1);
	      add(lbldbqd,constraints,0,4,1,1); add( txtdbqd,constraints,1,4,1,1);
	      add(lbldb,constraints,0,5,1,1); add(txtdb,constraints,1,5,1,1);
	      add(lbls,constraints,0,6,1,1); add(txts,constraints,1,6,1,1);
	      /*
	       * 添加事件监听机制
	       */
	      pane.setVisible(true);
	}
	public String getdoorid(){
		return (String) jComboBox4.getSelectedItem();
	}
	  public void setconsta(String consta){
		  txtlianjie.setText("");
		  txtlianjie.append(consta);
		  txtlianjie.invalidate();
	  }
	  public void setdoorsta(String doorsta){
		  txtmenjin.setText("");
		  txtmenjin.append(doorsta);
		  txtmenjin.invalidate();
	  }
	  public void setsqldrive(String sqldrive){
		  txtdbqd.setText("");
		  txtdbqd.append(sqldrive);
		  txtdbqd.invalidate();
	  }
	  public void setsqlconsta(String sqlconsta){
		  txtdb.setText("");
		  txtdb.append(sqlconsta);
		  txtdb.invalidate();
	  }
	  public void setudpconsta(String udpconsta){
		  txts.setText("");
		  txts.append(udpconsta);
		  txts.invalidate();
	  }
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img,0,0, getSize().width, getSize().height, null);
	}
}