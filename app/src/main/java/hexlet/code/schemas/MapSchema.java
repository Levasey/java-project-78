package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>();

    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck((Predicate<Map<?, ?>>) value -> value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
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