package exception;

public interface CheckedProviders<T> {
    T get() throws Exception;
}
