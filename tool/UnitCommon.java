package tool;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import mycomp.MyPicPanel;

public class UnitCommon 
{
	public static JPanel createPanel(boolean isOpaque, Dimension dimension, LayoutManager layout,
			Border border, String imagePath) {

		JPanel jpanel = null;
		if (imagePath != null) {
			jpanel = new MyPicPanel(imagePath, dimension.width,
					dimension.height);
		} else {
			jpanel = new JPanel();
		}
		jpanel.setOpaque(isOpaque);
		jpanel.setPreferredSize(dimension);
		if (layout != null)
			jpanel.setLayout(layout);
		if (border != null)
			jpanel.setBorder(border);
		return jpanel;
	}
	public static Component createLine(Color color, int width, int height) {
		JLabel label = new JLabel("");
		label.setBackground(color);
		label.setPreferredSize(new Dimension(width, height));
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		label.setOpaque(true);
		return label;
	}

	public static void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "门禁系统",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public  static void showError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "门禁系统",
				JOptionPane.ERROR_MESSAGE);
	}
}