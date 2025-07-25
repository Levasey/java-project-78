package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        super.required();
        addCheck(value -> !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck((Predicate<String>) value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck((Predicate<String>) value -> value.contains(substring));
        return this;
    }
}
