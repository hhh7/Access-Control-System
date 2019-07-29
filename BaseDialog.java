import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JDialog;


public class BaseDialog extends JDialog {
	public BaseDialog() {
		this.setSize(400, 300);
		this.setResizable(false);
		this.setTitle("门禁系统");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		try {
			this.setIconImage(ImageIO.read(new File("images/xju.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}