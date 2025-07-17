package hexlet.code.schemas;

import hexlet.code.schemas.states.NotRequiredState;
import hexlet.code.schemas.states.RequiredState;
import hexlet.code.schemas.states.State;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema<T> {
    private State<T> requirement = new NotRequiredState<>();
    private final List<Predicate<T>> predicates = new ArrayList<>();

    public BaseSchema<T> required() {
        this.requirement = new RequiredState<>();
        return this;
    }

    protected void addCheck(Predicate<T> predicate) {
        predicates.removeIf(p -> p.getClass().equals(predicate.getClass()));
        predicates.add(predicate);
    }

    /**
    Принимает любой объект (Object) для валидации
    Безопасно приводит его к нужному типу T
    Делегирует проверку основному типизированному методу isValid
     */
    public boolean isValidUntyped(Object value) {
        //Пытаемся привести входящий Object к generic-типу T
        try {
            //@SuppressWarnings("unchecked") подавляет предупреждение о небезопасном приведении типов
            @SuppressWarnings("unchecked")
            T typedValue = (T) value;
            return isValid(typedValue);
        } catch (ClassCastException e) {
            return false;
        }
    }

    public boolean isValid(T value) {
        if (!requirement.isValid(value)) {
            return false;
        }

        if (value == null) {
            return requirement instanceof NotRequiredState;
        }

        return predicates.stream().allMatch(p -> p.test(value));
    }
}
