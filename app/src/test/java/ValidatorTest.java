import hexlet.code.StringSchema;
import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    @Test
    public void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        // Проверка без ограничений
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("test string"));

        // Проверка required()
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("test string"));

        // Проверка minLength()
        schema.minLength(5);
        assertFalse(schema.isValid("test"));
        assertTrue(schema.isValid("test string"));

        // Проверка contains()
        schema.contains("test");
        assertTrue(schema.isValid("test string"));
        assertFalse(schema.isValid("another string"));

        // Проверка перетирания ограничений
        StringSchema schema2 = v.string();
        schema2.minLength(10).minLength(4);
        assertTrue(schema2.isValid("Hexlet"));
    }
}
