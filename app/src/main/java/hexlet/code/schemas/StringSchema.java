package hexlet.code.schemas;

public class StringSchema {
    private boolean required = false;
    private Integer minLength = null;
    private String contains = null;

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema contains(String substring) {
        this.contains = substring;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public boolean isValid(String value) {
        if (!required && (value == null || value.isEmpty())) {
            return true;
        }

        if (required && (value == null || value.isEmpty())) {
            return false;
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
