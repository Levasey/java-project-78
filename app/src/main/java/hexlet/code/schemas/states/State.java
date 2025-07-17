package hexlet.code.schemas.states;

public interface State<T> {
    boolean isValid(T value);
}
