package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
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
}