package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        addCheck((Predicate<Integer>) value -> value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck((Predicate<Integer>) value -> value >= min && value <= max);
        return this;
    }
}