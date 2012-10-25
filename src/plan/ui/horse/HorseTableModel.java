package plan.ui.horse;

import javax.swing.event.TableModelListener;
import java.util.*;
import plan.*;
import javax.swing.table.TableModel;

import plan.Horse;

public class HorseTableModel implements TableModel {

	private Horse horse;

	private List<TableModelListener> listeners = new ArrayList<TableModelListener>();

	public HorseTableModel(Horse h) {
		horse = h;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// lesson id, rider id
		switch (columnIndex) {
		case 0:
		case 1:
			return Integer.class;
		case 2:
			return String.class;
		default:
			return Integer.class;
		}
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
		case 1:
			return "Lesson";
		case 2:
			return "Rider";
		default:
			return "Name";
		}
	}

	@Override
	public int getRowCount() {
		// TODO Incorrect
		return horse.lessons.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		List<Lesson> lessons = new ArrayList<Lesson>(horse.lessons);
		Lesson current = lessons.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return current.id;
		case 1:
			return horse.getStudents(current).toArray(new Student[1])[0].id;
		case 2:
			return horse.getStudents(current).toArray(new Student[1])[0].name;
		default:
			break;
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

	}

}
