package util;

public interface Observer<E> {
	public void notifyBeforeChange(E source);

	public void notifyAfterChange(E source);
}
