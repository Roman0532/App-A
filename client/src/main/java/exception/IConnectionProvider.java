package exception;

/**
 * Created by Roman Maximov on 22.12.2017
 */
public interface IConnectionProvider<T> extends ICheckedProvider<T> {
        @Override
        T get() throws Exception;
    }

