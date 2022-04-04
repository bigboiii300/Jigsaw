package ikiryakov.jigsaw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void getMainBoard() {
        int[][] arrayTest = Board.getMainBoard();
        assertArrayEquals(new int[9][9],arrayTest);
    }

    @Test
    void getGenerateShape() {
        int[][] arrayTest = Board.getGenerateShape();
        assertArrayEquals(new int[3][3],arrayTest);
    }
}