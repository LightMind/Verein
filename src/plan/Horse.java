package plan;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import util.Factory;
import util.SmartHashMap;

public class Horse {
	public final int id;
    public String name;
    public Set<Lesson> lessons = new HashSet<Lesson>();
    public Set<Student> students = new HashSet<Student>();    
    public Map<Lesson,Set<Student>> riders = new SmartHashMap<Lesson, Set<Student>>(
			new Factory<Set<Student>>() {
				@Override
				public Set<Student> create() {
					return new HashSet<Student>();
				}
			});;
    public Map<Student,Set<Lesson>> inLesson = new SmartHashMap<Student, Set<Lesson>>(
			new Factory<Set<Lesson>>() {
				@Override
				public Set<Lesson> create() {
					return new HashSet<Lesson>();
				}
			});;

    public Horse(String name, int id){
        this.name = name;
        this.id = id;
    }

    // Accessors //

    public String getName(){
        return name;
    }

    public Set<Lesson> getLessons() {
        return new HashSet<Lesson>(lessons);
    }

    public Set<Student> getRiders() {
        return new HashSet<Student>(students);
    }

    public Set<Student> getStudents(Lesson l){
        return new HashSet<Student>(riders.get(l));
    }

    public Set<Lesson> getLessons(Student st){
        return new HashSet<Lesson>(inLesson.get(st));
    }

    // mutators //
    
    public void assign(Lesson l,Student st){
        lessons.add(l);
        students.add(st);
        inLesson.get(st).add(l);
        riders.get(l).add(st);
    }
    
    public void unassign(Lesson l,Student st){
        riders.get(l).remove(st);
        Set<Lesson> lset = inLesson.get(st);
        lset.remove(l);
        if ( lset.isEmpty() ){
            students.remove(st);
        }
        Set<Student> rset = riders.get(l);
        if ( rset.isEmpty() ){
            lessons.remove(l);
        }
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Horse [id=" + id + ", name=" + name + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Horse other = (Horse) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
