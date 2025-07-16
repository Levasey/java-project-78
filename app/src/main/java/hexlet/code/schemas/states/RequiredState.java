package hexlet.code.schemas.states;

public class RequiredState implements ValidationState {
    @Override
    public boolean isValid(Object value) {
        return value != null;
    }
}