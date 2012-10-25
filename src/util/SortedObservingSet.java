package util;

import java.util.*;

public class SortedObservingSet<E extends Observeable<E>> extends
		AbstractSet<E> implements Observer<E>, SortedSet<E>, NavigableSet<E> {

	private TreeSet<E> internal = new TreeSet<E>();
	private int hash = 31;

	public SortedObservingSet() {
		initHash();
	}

	private void initHash() {
		Random r = new Random();
		hash = r.nextInt(400);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + hash;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj;
	}

	@Override
	public void notifyAfterChange(E source) {
		internal.add(source);
	}

	@Override
	public void notifyBeforeChange(E source) {
		internal.remove(source);
	}

	@Override
	public boolean contains(Object e) {
		return internal.contains(e);
	}

	@Override
	public boolean add(E e) {
		if (!internal.contains(e)) {
			e.addObserver(this);
			internal.add(e);
			return true;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return internal.iterator();
	}

	@Override
	public int size() {
		return internal.size();
	}

	@Override
	public boolean remove(Object o) {
		if (o instanceof Observeable) {
			Observeable obs = (Observeable) o;
			obs.removeObserver(this);
		}
		return internal.remove(o);
	}

	public boolean remove(E e) {
		e.removeObserver(this);
		return internal.remove(e);
	}

	@Override
	public void clear() {
		while (internal.size() > 0) {
			E el = internal.first();
			remove(el);
		}
	}

	@Override
	public Comparator<? super E> comparator() {
		return internal.comparator();
	}

	@Override
	public E first() {
		return internal.first();
	}

	@Override
	public SortedSet<E> headSet(E toElement) {
		return internal.headSet(toElement);
	}

	@Override
	public E last() {
		return internal.last();
	}

	@Override
	public SortedSet<E> subSet(E fromObject, E toObject) {
		return internal.subSet(fromObject, toObject);
	}

	@Override
	public SortedSet<E> tailSet(E fromElement) {
		return internal.tailSet(fromElement);
	}

	@Override
	public E ceiling(E arg0) {
		return internal.ceiling(arg0);
	}

	@Override
	public Iterator<E> descendingIterator() {
		return internal.descendingIterator();
	}

	@Override
	public NavigableSet<E> descendingSet() {
		return internal.descendingSet();
	}

	@Override
	public E floor(E arg0) {
		return internal.floor(arg0);
	}

	@Override
	public NavigableSet<E> headSet(E arg0, boolean arg1) {
		return internal.headSet(arg0, arg1);
	}

	@Override
	public E higher(E arg0) {
		return internal.higher(arg0);
	}

	@Override
	public E lower(E arg0) {
		return internal.lower(arg0);
	}

	@Override
	public E pollFirst() {
		return internal.pollFirst();
	}

	@Override
	public E pollLast() {
		return internal.pollLast();
	}

	@Override
	public NavigableSet<E> subSet(E fromElement, boolean fromInclusive,
			E toElement, boolean toInclusive) {
		return internal.subSet(fromElement, fromInclusive, toElement,
				toInclusive);
	}

	@Override
	public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
		return internal.tailSet(fromElement, inclusive);
	}
}