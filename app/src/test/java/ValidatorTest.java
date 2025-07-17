import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
    public void testMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        // Проверка без ограничений
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        // Проверка required()
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        // Проверка sizeof()
        schema.sizeof(2);
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testShapeValidation() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        // Создаем схемы для проверки значений в Map
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        // Валидные данные
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        // Отсутствует lastName
        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        assertFalse(schema.isValid(human2));

        // lastName null
        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "John");
        human3.put("lastName", null);
        assertFalse(schema.isValid(human3));

        // lastName слишком короткий
        Map<String, String> human4 = new HashMap<>();
        human4.put("firstName", "Anna");
        human4.put("lastName", "B");
        assertFalse(schema.isValid(human4));

        // Дополнительные тесты с разными типами данных
        Map<String, BaseSchema<?>> mixedSchemas = new HashMap<>();
        mixedSchemas.put("name", v.string().required());
        mixedSchemas.put("age", v.number().positive().range(18, 99));

        schema.shape(mixedSchemas);

        Map<String, Object> person1 = new HashMap<>();
        person1.put("name", "Alice");
        person1.put("age", 25);
        assertTrue(schema.isValid(person1));

        Map<String, Object> person2 = new HashMap<>();
        person2.put("name", "Bob");
        person2.put("age", 17);
        assertFalse(schema.isValid(person2));
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

        // Проверка перетирания ограничений для map
        Map<String, String> data = new HashMap<>();
        MapSchema schema2 = v.map();
        schema2.sizeof(1).sizeof(3);
        data.put("key1", "value1");
        data.put("key2", "value2");
        data.put("key3", "value3");
        assertTrue(schema2.isValid(data));
        data.remove("key3");
        assertFalse(schema2.isValid(data));
    }
}
