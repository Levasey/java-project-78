package hexlet.code.schemas.states;

public class RequiredState implements State {
    @Override
    public boolean isValid(Object value) {
        return value != null;
    }

    @Override
    public String getCurrentState() {
        return "required";
    }
}