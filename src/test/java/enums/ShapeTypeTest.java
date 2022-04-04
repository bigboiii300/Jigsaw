package enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTypeTest {

    @Test
    void generateShape() {
        System.out.println(ShapeType.generateShape());
    }

    @Test
    void values() {
        ShapeType[] values = ShapeType.values();
        assertEquals(31,values.length);
    }

    @Test
    void valueOf() {
        ShapeType[] values = ShapeType.values();
        for (ShapeType value : values) {
            System.out.println(ShapeType.valueOf(String.valueOf(value)));
        }
    }
}