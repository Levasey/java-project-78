package hexlet.code.schemas.states;

/**
 * Состояние "обязательное поле" (значение не может быть null).
 *
 * @param <T> Тип проверяемого значения
 */
public final class RequiredState<T> implements State<T> {
    @Override
    public boolean isValid(T value) {
        return value != null;
    }
}