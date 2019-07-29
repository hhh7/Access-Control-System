package mycomp;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPicPanel extends JPanel {
	private Image image = null;
	private String picPath ="";
	private int mWidth = 0;
	private int mHeight = 0;
	public MyPicPanel(String picPath){
		try {
			image = ImageIO.read(new File(picPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	
public MyPicPanel(String picPath,int width,int height){
		try {
			image = ImageIO.read(new File(picPath));
			this.mWidth = width ;
			this.mHeight = height;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Override
protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//g.drawImage(image,0,0,null);
		//System.out.println("width="+this.WIDTH);
		g.drawImage(image, 0, 0,getSize().width, getSize().height, null);
	}
}
