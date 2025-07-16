package hexlet.code.schemas;

import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    //флаг, указывающий, является ли значение обязательным (не может быть null)
    protected boolean required = false;
    //массив предикатов (условий проверки), которые будут применяться к значению
    protected Predicate<T>[] checks = new Predicate[0];

    //аннотация, подавляющая предупреждение о возможных проблемах с безопасностью типов при использовании varargs с дженериками
    @SafeVarargs
    protected final void addCheck(Predicate<T>... predicates) {
        this.checks = predicates;
    }

    public boolean isValid(T value) {
        // Проверка на null для необязательных значений
        if (!required && value == null) {
            return true;
        }

        // Проверка на null для обязательных значений
        if (required && value == null) {
            return false;
        }

        // Применение всех проверок
        for (Predicate<T> check : checks) {
            if (!check.test(value)) {
                return false;
            }
        }

        return true;
    }
}
