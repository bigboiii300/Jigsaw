package ikiryakov.jigsaw;

public class Board {
    /**
     * @return Массив, определяющий основной борд.
     */
    public static int[][] getMainBoard() {
        return new int[9][9];
    }

    /**
     * @return Массив, определяющий фигуру.
     */
    public static int[][] getGenerateShape() {
        return new int[3][3];
    }
}
