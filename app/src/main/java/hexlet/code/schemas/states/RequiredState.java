package hexlet.code.schemas.states;

public class RequiredState<T> implements State<T> {
    @Override
    public boolean isValid(T value) {
        return value != null;
    }
}