package plan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import util.Assorted;
import util.Factory;
import util.SmartHashMap;

public class Student extends Person {
	public Set<Lesson> lessons = new HashSet<Lesson>();
	public Set<Horse> horses = new HashSet<Horse>();
	public Map<Lesson, Horse> rides = new HashMap<Lesson, Horse>();
	public Map<Horse, Set<Lesson>> inLessons = new SmartHashMap<Horse, Set<Lesson>>(
			new Factory<Set<Lesson>>() {
				@Override
				public Set<Lesson> create() {
					return new HashSet<Lesson>();
				}
			});

	public Student(String name, int id) {
		super(name, id);
	}

	// Accessors //

	public Set<Lesson> getLessons() {
		return new HashSet<Lesson>(lessons);
	}

	public Set<Horse> ridesOn() {
		return new HashSet<Horse>(horses);
	}

	public Horse getHorse(Lesson l) {
		return rides.get(l);
	}

	public Set<Lesson> getLessons(Horse h) {
		return new HashSet<Lesson>(inLessons.get(h));
	}

	// mutators //

	public void assign(Lesson l) {
		lessons.add(l);
	}

	@Override
	public String toString() {
		return "Student [inLessons=" + (inLessons) + ", lessons=" + Assorted.renderList(lessons)
				+ ", rides=" + rides + "] Super:" + super.toString();
	}

	public void unassign(Lesson l) {
		lessons.remove(l);
		Horse ride = rides.get(l);
		if (ride != null) {
			Set<Lesson> lset = inLessons.get(ride);
			lset.remove(l);
			if (lset.size() == 0) {
				horses.remove(ride);
			}
			rides.remove(l);
		}
	}

	public void assign(Lesson l, Horse h) {
		lessons.add(l);
		horses.add(h);
		rides.put(l, h);
		inLessons.get(h).add(l);
	}

	public void unassign(Lesson l, Horse h) {
		rides.put(l, null);
		Set<Lesson> lset = inLessons.get(h);
		lset.remove(h);
		if (lset.size() == 0) {
			lessons.remove(l);
		}
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