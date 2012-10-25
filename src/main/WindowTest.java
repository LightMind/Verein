package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class WindowTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Horse");
		frame.setBounds(0, 0, 500, 400);

		JLabel nameLabel = new JLabel("Horse A");
		frame.add(nameLabel);

		frame.setVisible(true);

	}

}
