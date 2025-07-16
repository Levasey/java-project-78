package hexlet.code.schemas;

import java.util.Objects;

public class StringSchema extends BaseSchema<String> {
    public StringSchema() {
        super();
    }

    public StringSchema required() {
        this.required = true;
        addCheck(
                Objects::nonNull,
                str -> !str.isEmpty()
        );
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(str -> str.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck(str -> str.contains(substring));
        return this;
    }
}