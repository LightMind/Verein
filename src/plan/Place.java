package plan;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;

import util.Assorted;
import util.SortedObservingSet;

public class Place {
	public String name = "";
	public NavigableSet<Lesson> lessons = new SortedObservingSet<Lesson>();
	public final int id;

	public Place(String n, int id) {
		name = n;
		this.id = id;
	}

	// mutators //

	public void assign(Lesson l) {
		lessons.add(l);
	}

	public void unassign(Lesson l) {
		lessons.remove(l);
	}

	// accessors //

	public String getName() {
		return name;
	}

	public List<Lesson> getLessons() {
		return new ArrayList<Lesson>(lessons);
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Place other = (Place) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Place [lessons=" + Assorted.renderList(lessons) + ", name="
				+ name + "]";
	}

}