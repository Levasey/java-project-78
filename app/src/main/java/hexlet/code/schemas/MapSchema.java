package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


/**
 * Схема для валидации объектов Map.
 * Поддерживает проверки на обязательность, размер и структуру содержимого.
 */
public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>();

    /**
     * Устанавливает, что Map обязателен (не null).
     * @return Текущий экземпляр схемы для цепочки вызовов
     */
    public MapSchema required() {
        super.required();
        return this;
    }

    /**
     * Устанавливает точный требуемый размер Map.
     *
     * @param size Требуемое количество пар ключ-значение
     * @return Текущий экземпляр схемы для цепочки вызовов
     */
    public MapSchema sizeof(int size) {
        addCheck((Predicate<Map<?, ?>>) value -> value.size() == size);
        return this;
    }

    /**
     * Устанавливает схемы валидации для значений конкретных ключей Map.
     *
     * @param schemas Map, где ключи - это имена полей,
     *                а значения - схемы валидации для этих полей
     * @param <T> Тип значений в Map
     * @return Текущий экземпляр схемы для цепочки вызовов
     */
    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        this.shapeSchemas = new HashMap<>(schemas);
        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> value) {
        if (!super.isValid(value)) {
            return false;
        }

        if (value == null) {
            return true;
        }

        for (Map.Entry<String, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema<?> schema = entry.getValue();
            Object fieldValue = value.get(key);

            if (!schema.isValidUntyped(fieldValue)) {
                return false;
            }
        }

        return true;
    }
}
