package hexlet.code.schemas.states;

public class NotRequiredState implements ValidationState {
    @Override
    public boolean isValid(Object value) {
        return true;
    }
}
