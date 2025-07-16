package hexlet.code.schemas;

import hexlet.code.schemas.states.NotRequiredState;
import hexlet.code.schemas.states.RequiredState;
import hexlet.code.schemas.states.ValidationState;

import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected ValidationState requiredState = new NotRequiredState();

    public BaseSchema<T> required() {
        this.requiredState = new RequiredState();
        return this;
    }

    protected boolean checkRequired(T value) {
        return requiredState.isValid(value);
    }

    public abstract boolean isValid(T value);
}
