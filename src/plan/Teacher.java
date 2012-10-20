package plan;

import java.util.HashSet;
import java.util.Set;

public class Teacher extends Person {
    Set<Lesson> lessons = new HashSet<Lesson>();

    public Teacher(String name, int id){
        super(name, id);
    }

    public void assign(Lesson l){
        lessons.add(l);
    }
    
    public void unassign(Lesson l){
        lessons.remove(l);
    }

    @Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}