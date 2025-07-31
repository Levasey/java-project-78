package hexlet.code.schemas;

import java.util.function.Predicate;

/**
 * Схема для валидации числовых значений.
 * Поддерживает проверки на обязательность, положительность и диапазон значений.
 */
public final class NumberSchema extends BaseSchema<Integer> {
    /**
     * Устанавливает, что число обязательно (не null).
     * @return Текущий экземпляр схемы для цепочки вызовов
     */
    public NumberSchema required() {
        super.required();
        return this;
    }

    /**
     * Устанавливает, что число должно быть положительным (> 0).
     * @return Текущий экземпляр схемы для цепочки вызовов
     */
    public NumberSchema positive() {
        addCheck((Predicate<Integer>) value -> value > 0);
        return this;
    }

    /**
     * Устанавливает допустимый диапазон значений числа.
     *
     * @param min Минимальное допустимое значение (включительно)
     * @param max Максимальное допустимое значение (включительно)
     * @return Текущий экземпляр схемы для цепочки вызовов
     */
    public NumberSchema range(int min, int max) {
        addCheck((Predicate<Integer>) value -> value >= min && value <= max);
        return this;
    }
}
