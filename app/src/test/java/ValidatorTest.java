import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    @Test
    public void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("test string"));

        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("test string"));

        schema.minLength(5);
        assertFalse(schema.isValid("test"));
        assertTrue(schema.isValid("test string"));

        schema.contains("test");
        assertTrue(schema.isValid("test string"));
        assertFalse(schema.isValid("another string"));
    }

    @Test
    public void testNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));

        schema.positive();
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));

        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testMethodChaining() {
        Validator v = new Validator();

        // Проверка перетирания ограничений для строк
        StringSchema stringSchema = v.string();
        stringSchema.minLength(10).minLength(4);
        assertTrue(stringSchema.isValid("Hexlet"));

        // Проверка перетирания ограничений для чисел
        NumberSchema numberSchema = v.number();
        numberSchema.range(1, 10).range(5, 15);
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid(4));
    }
}
