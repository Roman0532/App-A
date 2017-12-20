package exception;

public interface ExceptionProviders<T> extends CheckedProviders<T> {
    @Override
    T get() throws Exception;
}
