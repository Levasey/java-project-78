package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    public StringSchema required() {
        super.required();
        addCheck(value -> !((String) value).isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(new Predicate<Object>() {
            @Override
            public boolean test(Object value) {
                return ((String) value).length() >= length;
            }

            @Override
            public int hashCode() {
                return this.getClass().hashCode();
            }
        });
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck(new Predicate<Object>() {
            @Override
            public boolean test(Object value) {
                return ((String) value).contains(substring);
            }

            @Override
            public int hashCode() {
                return this.getClass().hashCode();
            }
        });
        return this;
    }
}