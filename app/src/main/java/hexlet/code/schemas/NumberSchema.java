package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema() {
        super();
    }

    public NumberSchema required() {
        this.required = true;
        addCheck(Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addCheck(num -> num > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(num -> num >= min && num <= max);
        return this;
    }
}
