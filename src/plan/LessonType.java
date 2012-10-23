package plan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Observer;

public class LessonType implements Observer<Lesson>{
	public final int id;
    public String type;
    public List<Lesson> lessons = new ArrayList<Lesson>();

    public LessonType(String t, int id){
        type = t;
        this.id = id;
    }

    // mutators //

    public boolean assign(Lesson l){
        int index = Collections.binarySearch(lessons,l);
        if ( index  < 0 ){
            lessons.add((-1)*(index+1),l);
            return true;
        }
        return false;
    }

    public boolean unassign(Lesson l){
        int index = Collections.binarySearch(lessons,l);
        if ( index < 0 ){
            return false;
        }
        lessons.remove(index);
        return true;
    }

    // accessors //
    
    public String getType(){
        return type;
    }

    public List<Lesson> getLessons(){
        return new ArrayList<Lesson>(lessons);
    }

    public int numberOfStudents(){
        int counter = 0;
        for(Lesson l : lessons){
            counter += l.numberOfStudents();
        }
        return counter;
    }

	@Override
	public void notify(Lesson source) {
		Collections.sort(lessons);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		LessonType other = (LessonType) obj;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}