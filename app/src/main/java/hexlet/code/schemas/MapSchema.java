package hexlet.code.schemas;

import hexlet.code.schemas.states.NotRequiredState;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer exactSize = null;

    public MapSchema sizeof(int size) {
        this.exactSize = size;
        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> value) {
        if (!checkRequired(value)) {
            return false;
        }

        if (value == null) {
            return requiredState instanceof NotRequiredState;
        }

        if (exactSize != null && value.size() != exactSize) {
            return false;
        }

        return true;
    }
}
