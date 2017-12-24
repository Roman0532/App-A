package provider;

import com.google.inject.throwingproviders.CheckedProvider;
import service.DbException;

public interface ConnectionProvider<T> extends CheckedProvider<T> {
    T get() throws DbException;
}
