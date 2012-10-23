package validation;

public interface Validator<E> {
	public boolean isValid(E object);
}
