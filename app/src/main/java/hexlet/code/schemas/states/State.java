package hexlet.code.schemas.states;

public interface State {
    boolean isValid(Object value);
    String getCurrentState();
}
