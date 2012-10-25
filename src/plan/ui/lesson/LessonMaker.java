package plan.ui.lesson;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import data.InfoMap;

import plan.Lesson;
import plan.LessonPlan;

public class LessonMaker extends JPanel {
	private static final long serialVersionUID = -7091198244339232775L;

	private final LessonPlan plan;

	public LessonMaker(LessonPlan plan) {
		this.plan = plan;
		initComponents();
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JFormattedTextField jFormattedTextField2;
	private javax.swing.JFormattedTextField jFormattedTextField3;
	private javax.swing.JFormattedTextField jFormattedTextField4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTextPane jTextPane1;

	private void initComponents() {
		jSeparator1 = new javax.swing.JSeparator();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextPane1 = new javax.swing.JTextPane();
		jLabel1 = new javax.swing.JLabel();
		final SimpleDateFormat date1 = new SimpleDateFormat("dd.MM.yyyy");
		final SimpleDateFormat beginFormat = new SimpleDateFormat("HH:mm");
		final SimpleDateFormat endFormat = new SimpleDateFormat("HH:mm");
		jFormattedTextField2 = new javax.swing.JFormattedTextField(date1);
		jFormattedTextField3 = new javax.swing.JFormattedTextField(beginFormat);
		jFormattedTextField4 = new javax.swing.JFormattedTextField(endFormat);
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jComboBox2 = new javax.swing.JComboBox();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();

		FocusListener fc = new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				Component c = arg0.getComponent();
				if (c instanceof JFormattedTextField) {
					((JFormattedTextField) c).selectAll();
				}
			}
		};
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Calendar date1cal = date1.getCalendar();
				int year = date1cal.get(Calendar.YEAR);
				int month = date1cal.get(Calendar.MONTH);
				int dayOfMonth = date1cal.get(Calendar.DAY_OF_MONTH);

				Calendar startCal = beginFormat.getCalendar();
				int startHour = startCal.get(Calendar.HOUR_OF_DAY);
				int startMinute = startCal.get(Calendar.MINUTE);

				Calendar endCal = endFormat.getCalendar();
				int endHour = endCal.get(Calendar.HOUR_OF_DAY);
				int endMinute = endCal.get(Calendar.MINUTE);

				Calendar lessonBeginn = new GregorianCalendar(year, month,
						dayOfMonth, startHour, startMinute);
				Calendar lessonEnd = new GregorianCalendar(year, month,
						dayOfMonth, endHour, endMinute);

				Lesson newLesson = InfoMap
						.createLesson(lessonBeginn, lessonEnd);
				plan.addLesson(newLesson);
				jTextPane1.setText("Lesson created with id: " + newLesson.id
						+ "\n" + newLesson.formatTime());
			}
		};
		jButton1.addActionListener(al);
		jButton1.setText("Create Lesson");
		jFormattedTextField2.addFocusListener(fc);
		jFormattedTextField3.addFocusListener(fc);
		jFormattedTextField4.addFocusListener(fc);
		jTextPane1.setEditable(false);
		jScrollPane1.setViewportView(jTextPane1);

		jLabel1.setText("Date");

		jFormattedTextField2.setText("01.01.2012");

		jFormattedTextField3.setText("15:30");

		jFormattedTextField4.setText("16:30");

		jLabel2.setText("From");

		jLabel3.setText("To");

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));

		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel4.setText("Teacher");

		jLabel5.setText("Lesson");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jSeparator1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 269,
								Short.MAX_VALUE)
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, 269,
								Short.MAX_VALUE)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jButton1)
														.addGroup(
																layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				layout
																						.createSequentialGroup()
																						.addComponent(
																								jLabel2)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								jFormattedTextField3,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								jLabel3)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								jFormattedTextField4,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				layout
																						.createSequentialGroup()
																						.addGroup(
																								layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(
																												jLabel1)
																										.addComponent(
																												jLabel4)
																										.addComponent(
																												jLabel5))
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(
																								layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																										.addComponent(
																												jComboBox1,
																												0,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												jFormattedTextField2,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												182,
																												Short.MAX_VALUE)
																										.addComponent(
																												jComboBox2,
																												0,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)))))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																jFormattedTextField2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jFormattedTextField4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel3)
														.addComponent(
																jFormattedTextField3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel2))
										.addGap(18, 18, 18)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jComboBox1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel4))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jComboBox2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel5))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jButton1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												34, Short.MAX_VALUE)
										.addComponent(
												jSeparator1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												54,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
	}

}
