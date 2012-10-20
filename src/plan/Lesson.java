package plan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lesson implements Comparable<Lesson> {

    public final int id;
    public Set<Student> students = new HashSet<Student>();
    public Map<Student, Horse> riders = new HashMap<Student, Horse>();
    public Set<Teacher> teachers = new HashSet<Teacher>();
    public Calendar beginn = new GregorianCalendar();
    public Calendar end = new GregorianCalendar();
    public LessonType lessonType = null;
    public Place place = null;

    public Lesson(int id) {
        this.id = id;
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
        end.setTime(c.getTime());
    }

    public void setBeginn(Calendar c) {
        beginn.setTime(c.getTime());
        lessonType.notify(this);
        place.notify(this);
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
        int c1 = getBeginn().compareTo(arg0.getBeginn());
        if (c1 == 0) {
            int c2 = end.compareTo(arg0.getEnd());
            if (c2 == 0) {
                return id - arg0.id;
            }
            return c2;
        }
        return c1;
    }

    public boolean isOverlapping(Lesson lesson) {
        Calendar s1, s2, e1, e2;

        s1 = beginn;
        s2 = lesson.getBeginn();

        e1 = end;
        e2 = lesson.getEnd();

        return ((s1.before(s2) && e1.after(s2))
                || (s1.after(s2) && s1.before(e2))
                || s1.equals(s2));
    }

    @Override
    public String toString() {
        return "Lesson [id=" + id + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((beginn == null) ? 0 : beginn.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Lesson other = (Lesson) obj;
        if (beginn == null) {
            if (other.beginn != null) {
                return false;
            }
        } else if (!beginn.equals(other.beginn)) {
            return false;
        }
        if (end == null) {
            if (other.end != null) {
                return false;
            }
        } else if (!end.equals(other.end)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        return true;
    }
}