package hexlet.code.schemas;

import hexlet.code.schemas.states.NotRequiredState;
import hexlet.code.schemas.states.RequiredState;
import hexlet.code.schemas.states.State;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema {
    private State requirement;
    private final List<Predicate<Object>> predicates = new ArrayList<>();

    public BaseSchema() {
        this.requirement = new NotRequiredState();
    }

    public BaseSchema required() {
        this.requirement = new RequiredState();
        return this;
    }

    protected String getCurrentState() {
        return requirement.getCurrentState();
    }

    protected void addCheck(Predicate<Object> predicate) {
        // Удаляем предыдущий предикат того же типа, если он существует
        predicates.removeIf(p -> p.getClass().equals(predicate.getClass()));
        predicates.add(predicate);
    }

    public boolean isValid(Object value) {
        // Проверка состояния required
        if (!requirement.isValid(value)) {
            return false;
        }

        // Если значение null и не требуется, то валидно
        if (value == null) {
            return requirement instanceof NotRequiredState;
        }

        // Проверка всех предикатов
        return predicates.stream().allMatch(p -> p.test(value));
    }
}
