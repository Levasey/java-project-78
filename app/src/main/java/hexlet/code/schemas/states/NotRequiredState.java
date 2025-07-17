package hexlet.code.schemas.states;

public class NotRequiredState implements State {
    @Override
    public boolean isValid(Object value) {
        return true;
    }

    @Override
    public String getCurrentState() {
        return "not required";
    }
}
