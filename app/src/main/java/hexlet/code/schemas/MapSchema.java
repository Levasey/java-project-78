package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    private Map<String, BaseSchema> shapeSchemas = new HashMap<>();

    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(new Predicate<Object>() {
            @Override
            public boolean test(Object value) {
                return ((Map<?, ?>) value).size() == size;
            }

            @Override
            public int hashCode() {
                return this.getClass().hashCode();
            }
        });
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.shapeSchemas = new HashMap<>(schemas);
        return this;
    }

    @Override
    public boolean isValid(Object value) {
        // Базовая проверка required и размера
        if (!super.isValid(value)) {
            return false;
        }

        // Если значение null и не требуется, то валидно
        if (value == null) {
            return true;
        }

        // Проверка shape-валидации
        if (!shapeSchemas.isEmpty()) {
            Map<?, ?> mapValue = (Map<?, ?>) value;
            for (Map.Entry<String, BaseSchema> entry : shapeSchemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema schema = entry.getValue();

                if (!mapValue.containsKey(key) || !schema.isValid(mapValue.get(key))) {
                    return false;
                }
            }
        }

        return true;
    }
}