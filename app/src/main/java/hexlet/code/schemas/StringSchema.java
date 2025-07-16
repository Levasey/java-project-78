package hexlet.code.schemas;

import hexlet.code.schemas.states.RequiredState;

public class StringSchema extends BaseSchema<String> {
    private Integer minLength = null;
    private String contains = null;

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.contains = substring;
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (!checkRequired(value)) {
            return false;
        }

        if (value == null || value.isEmpty()) {
            return !(requiredState instanceof RequiredState);
        }

        if (minLength != null && value.length() < minLength) {
            return false;
        }

        if (contains != null && !value.contains(contains)) {
            return false;
        }

        return true;
    }
}
