package util;

public interface Observeable<E> {
	public void addObserver(Observer<E> obs);

	public void removeObserver(Observer<E> obs);
}
