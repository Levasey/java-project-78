package hexlet.code.schemas;

import java.util.function.Predicate;

/**
 * Схема для валидации строковых значений.
 * Поддерживает проверки на обязательность, минимальную длину и содержание подстроки.
 */
public final class StringSchema extends BaseSchema<String> {
    /**
     * Устанавливает, что строка обязательна (не null и не пустая).
     * @return Текущий экземпляр схемы для цепочки вызовов
     */
    public StringSchema required() {
        super.required();
        addCheck(value -> !value.isEmpty());
        return this;
    }

    /**
     * Устанавливает минимальную длину строки.
     *
     * @param length Минимальная допустимая длина строки
     * @return Текущий экземпляр схемы для цепочки вызовов
     */
    public StringSchema minLength(int length) {
        addCheck((Predicate<String>) value -> value.length() >= length);
        return this;
    }

    /**
     * Устанавливает, что строка должна содержать указанную подстроку.
     *
     * @param substring Подстрока, которая должна содержаться
     * @return Текущий экземпляр схемы для цепочки вызовов
     */
    public StringSchema contains(String substring) {
        addCheck((Predicate<String>) value -> value.contains(substring));
        return this;
    }
}
