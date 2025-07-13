package hexlet.code.schemas;

public class NumberSchema {
    private boolean required = false;
    private boolean positive = false;
    private Integer minRange = null;
    private Integer maxRange = null;

    public NumberSchema required() {
        this.required = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.minRange = min;
        this.maxRange = max;
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public boolean isValid(Integer value) {
        if (!required && value == null) {
            return true;
        }

        if (required && value == null) {
            return false;
        }

        if (positive && value != null && value <= 0) {
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
