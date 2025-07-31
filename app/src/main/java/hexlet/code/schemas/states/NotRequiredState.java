package hexlet.code.schemas.states;

/**
 * Состояние "необязательное поле" (значение может быть null).
 *
 * @param <T> Тип проверяемого значения
 */
public final class NotRequiredState<T> implements State<T> {
    @Override
    public boolean isValid(T value) {
        return true;
    }
}
