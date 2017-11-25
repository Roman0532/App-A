package service;

public class DbException extends Exception{
    public DbException(String message, Throwable cause) {
        super(message, cause);
    }
}
