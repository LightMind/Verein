package util;

public interface Observer<E> {
	public void notify(E source);
}
