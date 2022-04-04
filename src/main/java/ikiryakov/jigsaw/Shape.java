package ikiryakov.jigsaw;

import enums.ShapeType;
import javafx.scene.canvas.Canvas;

/**
 * Фигура, которую перемещает пользователь.
 */
class Shape {
    /**
     * Тип фигуры, который рандомно генерируется.
     */
    ShapeType type;
    Canvas canvas;

    public Shape(double x, double y, int width, int height, ShapeType type) {
        this.type = type;
        canvas = new Canvas(width,height);
        canvas.setLayoutX(x);
        canvas.setLayoutY(y);
    }

    /**
     * @return Фигура с заданными в конструкторе параметрами.
     */
    public Canvas getShape() {
        return canvas;
    }

    /**
     * @return Массив, определяющий фигуру.
     */
    public int[][] generateShape() {
        return switch (type) {
            case FIRST -> new int[][]{{1, 1, 0}, {1, 0, 0}, {1, 0, 0}};
            case SECOND -> new int[][]{{0, 0, 0}, {1, 0, 0}, {1, 1, 1}};
            case THIRD -> new int[][]{{0, 0, 1}, {0, 0, 1}, {0, 1, 1}};
            case FOURTH -> new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 0, 1}};
            case FIFTH -> new int[][]{{0, 1, 1}, {0, 0, 1}, {0, 0, 1}};
            case SIXTH -> new int[][]{{0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
            case SEVENTH -> new int[][]{{1, 0, 0}, {1, 0, 0}, {1, 1, 0}};
            case EIGHTH -> new int[][]{{0, 0, 0}, {1, 1, 1}, {1, 0, 0}};
            case NINTH -> new int[][]{{1, 0, 0}, {1, 1, 0}, {0, 1, 0}};
            case TENTH -> new int[][]{{0, 0, 0}, {0, 1, 1}, {1, 1, 0}};
            case ELEVENTH -> new int[][]{{0, 1, 0}, {1, 1, 0}, {1, 0, 0}};
            case TWELFTH -> new int[][]{{0, 0, 0}, {1, 1, 0}, {0, 1, 1}};
            case THIRTEENTH -> new int[][]{{0, 0, 1}, {0, 0, 1}, {1, 1, 1}};
            case FOURTEENTH -> new int[][]{{1, 0, 0}, {1, 0, 0}, {1, 1, 1}};
            case FIFTEENTH -> new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 0, 0}};
            case SIXTEENTH -> new int[][]{{1, 1, 1}, {0, 0, 1}, {0, 0, 1}};
            case SEVENTEENTH -> new int[][]{{0, 1, 0}, {0, 1, 0}, {1, 1, 1}};
            case EIGHTEENTH -> new int[][]{{1, 1, 1}, {0, 1, 0}, {0, 1, 0}};
            case NINETEENTH -> new int[][]{{1, 0, 0}, {1, 1, 1}, {1, 0, 0}};
            case TWENTIETH -> new int[][]{{0, 0, 1}, {1, 1, 1}, {0, 0, 1}};
            case TWENTY_FIRST -> new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 0, 0}};
            case TWENTY_SECOND -> new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}};
            case TWENTY_THIRD -> new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
            case TWENTY_FOURTH -> new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 0, 0}};
            case TWENTY_FIFTH -> new int[][]{{0, 0, 0}, {0, 1, 1}, {0, 0, 1}};
            case TWENTY_SIXTH -> new int[][]{{0, 0, 0}, {0, 0, 1}, {0, 1, 1}};
            case TWENTY_SEVENTH -> new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 1, 1}};
            case TWENTY_EIGHTH -> new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 0, 0}};
            case TWENTY_NINTH -> new int[][]{{1, 1, 1}, {0, 1, 0}, {0, 0, 0}};
            case THIRTIETH -> new int[][]{{0, 0, 1}, {0, 1, 1}, {0, 0, 1}};
            case THIRTY_FIRST -> new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        };
    }
}
