package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        addCheck(new Predicate<Object>() {
            @Override
            public boolean test(Object value) {
                return ((Integer) value) > 0;
            }

            @Override
            public int hashCode() {
                return this.getClass().hashCode();
            }
        });
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(new Predicate<Object>() {
            @Override
            public boolean test(Object value) {
                int num = (Integer) value;
                return num >= min && num <= max;
            }

            @Override
            public int hashCode() {
                return this.getClass().hashCode();
            }
        });
        return this;
    }
}