package ikiryakov.jigsaw;

import enums.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    @Test
    void getShape() {
        Shape shapeTest = new Shape(100, 120, 56, 56, ShapeType.generateShape());
        assertEquals(56, shapeTest.getShape().getHeight());
    }

    @Test
    void generateShape() {
        for (int i = 0; i < 90; i++) {
            Shape shapeTest = new Shape(100, 120, 56, 56, ShapeType.generateShape());
            assertEquals(56, shapeTest.getShape().getHeight());
            assertEquals(3, shapeTest.generateShape().length);
        }
    }
}