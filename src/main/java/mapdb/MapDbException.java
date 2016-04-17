package mapdb;

public class MapDbException extends Exception {

    public MapDbException(String message) {
        super(message);
    }

    public MapDbException(String message, Throwable cause) {
        super(message, cause);
    }
}
