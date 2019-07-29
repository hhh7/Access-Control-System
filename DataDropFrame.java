


import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DataDropFrame extends JFrame{
private int end;

	/*给year和month添加一个ItemListener来实时计算指定年和月中有多少天就可以了.
代码是用Swing写的,不过看你的图,不像是Swing界面.*/
      /**
	 * Create the frame.
	 */
	public DataDropFrame() {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		this.add(contentPane);
		
		final JComboBox year = new JComboBox();
		int start = 0;
		year.setModel(new DefaultComboBoxModel(getModel(start, end)));
		contentPane.add(year);

		final JComboBox month = new JComboBox();
		month.setModel(new DefaultComboBoxModel(getModel(1, 12)));
		contentPane.add(month);

		final JComboBox day = new JComboBox();
		contentPane.add(day);

		year.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setDay(year, month, day);
			}
		});
		month.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setDay(year, month, day);
			}
		});
		setDay(year, month, day);
		setContentPane(contentPane);
	}

	/**
	 * calculate days in select month & year
	 */
	private void setDay(JComboBox year, JComboBox month, JComboBox day) {
		int y = Integer.parseInt((String) year.getSelectedItem());
		int m = Integer.parseInt((String) month.getSelectedItem());
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, y);
		c.set(Calendar.MONTH, m - 1);
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		day.setModel(new DefaultComboBoxModel(getModel(1, days)));

	}

	/**
	 * get String array [start, end]
	 */
	private String[] getModel(int start, int end) {
		String[] m = new String[end - start + 1];
		for (int i = 0; i < m.length; i++) {
			m[i] = String.valueOf(i + start);
		}
		return m;
	}
	public static void main(String[] args) {
			new DataDropFrame();
		}
}