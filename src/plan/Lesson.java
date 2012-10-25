package plan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Observeable;
import util.Observer;

public class Lesson implements Comparable<Lesson>, Observeable<Lesson> {
	public final int id;

	public Set<Student> students = new HashSet<Student>();
	public Map<Student, Horse> riders = new HashMap<Student, Horse>();
	public Set<Teacher> teachers = new HashSet<Teacher>();
	public Calendar beginn = new GregorianCalendar();
	public Calendar end = new GregorianCalendar();
	public LessonType lessonType = new LessonType("None", -1);
	public Place place = new Place("None", -1);

	public Lesson(int id) {
		this.id = id;
	}

	public Lesson(int id, Calendar beginn) {
		this.id = id;
		this.beginn = new GregorianCalendar();
		this.beginn.setTime(beginn.getTime());

		this.end = new GregorianCalendar();
		this.end.setTime(beginn.getTime());
		this.end.add(Calendar.MINUTE, 60);
	}

	// mutators //

	public void setPlace(Place p) {
		if (place != null) {
			place.unassign(this);
		}
		p.assign(this);
		this.place = p;
	}

	public void setLessonType(LessonType lt) {
		if (lessonType != null) {
			lessonType.unassign(this);
		}
		lt.assign(this);
		lessonType = lt;
	}

	public void setEnd(Calendar c) {
		if (c.after(beginn)) {
			end.setTime(c.getTime());
		}
	}

	public int getDuration() {
		long duration = end.getTimeInMillis() - beginn.getTimeInMillis();
		int lessonDuration = (int) (duration / 60000);
		return lessonDuration;
	}

	public void setDuration(int minutes) {
		Calendar lend = getBeginn();
		lend.add(Calendar.MINUTE, minutes);
		setEnd(lend);
	}

	public void setBeginn(Calendar c) {
		int lessonDuration = getDuration();
		if (lessonDuration < 5) {
			lessonDuration = 5;
		}

		for (Observer<Lesson> observer : obser) {
			observer.notifyBeforeChange(this);
		}
		beginn.setTime(c.getTime());
		for (Observer<Lesson> observer : obser) {
			observer.notifyAfterChange(this);
		}

		this.end = new GregorianCalendar();
		this.end.setTime(beginn.getTime());
		this.end.add(Calendar.MINUTE, lessonDuration);
	}

	public void assign(Teacher t) {
		teachers.add(t);
		t.assign(this);
	}

	public void unassign(Teacher t) {
		teachers.remove(t);
		t.unassign(this);
	}

	public boolean addStudent(Student student) {
		student.assign(this);
		return students.add(student);
	}

	public void removeStudent(Student st) {
		if (students.contains(st)) {
			st.unassign(this);
			students.remove(st);
		}
	}

	public void assignHorse(Student st, Horse h) {
		if (!students.contains(st)) {
			addStudent(st);
		}
		if (null != riders.get(st)) {
			Horse hOld = riders.get(st);
			hOld.unassign(this, st);
			st.unassign(this, hOld);
		}
		st.assign(this, h);
		h.assign(this, st);
		riders.put(st, h);
	}

	// accessors //

	public Calendar getEnd() {
		Calendar r = new GregorianCalendar();
		r.setTime(end.getTime());
		return r;
	}

	public List<Student> getStudents() {
		return new ArrayList<Student>(students);
	}

	public int numberOfStudents() {
		return students.size();
	}

	public Horse rides(Student st) {
		return riders.get(st);
	}

	public List<Horse> getHorses() {
		return new ArrayList<Horse>(riders.values());
	}

	public Calendar getBeginn() {
		Calendar r = new GregorianCalendar();
		r.setTime(beginn.getTime());
		return r;
	}

	public Set<Teacher> getTeachers() {
		return new HashSet<Teacher>(teachers);
	}

	@Override
	public int compareTo(Lesson arg0) {
		int comp = beginn.compareTo(arg0.beginn);
		if (comp == 0) {
			return this.id - arg0.id;
		}
		return comp;
	}

	@Override
	public String toString() {
		return "Lesson [id=" + id + "]";
	}

	public String formatTime() {
		DateFormat md = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat time = new SimpleDateFormat("HH:mm");
		return "On the " + md.format(beginn.getTime()) + " from "
				+ time.format(beginn.getTime()) + " - "
				+ time.format(end.getTime());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		if (id != other.id)
			return false;
		return true;
	}

	private Set<Observer<Lesson>> obser = new HashSet<Observer<Lesson>>();

	@Override
	public void addObserver(Observer<Lesson> obs) {
		obser.add(obs);
	}

	@Override
	public void removeObserver(Observer<Lesson> obs) {
		obser.remove(obs);
	}

	public static Comparator<Lesson> comparator() {
		return new Comparator<Lesson>() {
			@Override
			public int compare(Lesson o1, Lesson o2) {
				int comp = o1.beginn.compareTo(o2.beginn);
				if (comp == 0) {
					return o1.id - o2.id;
				}
				return comp;
			}
		};
	}

	public void snapTo(int minutes) {
		int startMinutes = beginn.get(Calendar.MINUTE);
		int snapCheckStart = startMinutes % minutes;
		if (snapCheckStart != 0) {
			Calendar lstart = getBeginn();
			lstart.add(Calendar.MINUTE, -snapCheckStart);
			setBeginn(lstart);
		}

		int duration = getDuration();
		if (duration > minutes) {
			int snapCheckDuration = duration % minutes;
			setDuration(duration - snapCheckDuration);
		} else {
			setDuration(minutes);
		}
	}
}