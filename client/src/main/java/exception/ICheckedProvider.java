package exception;

public interface ICheckedProvider<T> {
    T get() throws Exception;
}
