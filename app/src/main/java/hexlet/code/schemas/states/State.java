package hexlet.code.schemas.states;

/**
 * Интерфейс состояния валидации (required/not required).
 *
 * @param <T> Тип проверяемого значения
 */
public interface State<T> {
    /**
     * Проверяет значение на соответствие состоянию.
     *
     * @param value Проверяемое значение
     * @return true если значение соответствует состоянию, false в противном случае
     */
    boolean isValid(T value);
}
