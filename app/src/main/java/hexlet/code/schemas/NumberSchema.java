package hexlet.code.schemas;

import hexlet.code.schemas.states.NotRequiredState;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positive = false;
    private Integer minRange = null;
    private Integer maxRange = null;

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.minRange = min;
        this.maxRange = max;
        return this;
    }

    @Override
    public boolean isValid(Integer value) {
        if (!checkRequired(value)) {
            return false;
        }

        if (value == null) {
            return requiredState instanceof NotRequiredState;
        }

        if (positive && value <= 0) {
            return false;
        }

        if (minRange != null && value < minRange) {
            return false;
        }

        if (maxRange != null && value > maxRange) {
            return false;
        }

        return true;
    }
}
