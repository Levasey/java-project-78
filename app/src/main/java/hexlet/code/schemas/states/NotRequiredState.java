package hexlet.code.schemas.states;

public class NotRequiredState<T> implements State<T> {
    @Override
    public boolean isValid(T value) {
        return true;
    }
}
