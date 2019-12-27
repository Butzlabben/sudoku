package de.butzlabben.sudoku;


import org.junit.Test;
import static org.junit.Assert.*;

public class SudokuGameTest {

    final int[][] board = {
            {0, 0, 0, 8, 3, 0, 0, 0, 0},
            {0, 0, 0, 0, 7, 4, 0, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 6, 0, 0, 8},
            {2, 0, 0, 0, 8, 0, 0, 0, 9},
            {0, 6, 0, 1, 0, 2, 4, 0, 0},
            {0, 0, 5, 7, 0, 0, 9, 0, 3},
            {9, 8, 0, 0, 0, 0, 0, 0, 5},
            {0, 0, 1, 0, 6, 5, 0, 0, 4}
    };

    final int[][] solved = {
            {5, 4, 2, 8, 3, 1, 7, 9, 6},
            {1, 9, 8, 6, 7, 4, 3, 5, 2},
            {6, 3, 7, 5, 2, 9, 8, 4, 1},
            {7, 1, 4, 3, 9, 6, 5, 2, 8},
            {2, 5, 3, 4, 8, 7, 6, 1, 9},
            {8, 6, 9, 1, 5, 2, 4, 3, 7},
            {4, 2, 5, 7, 1, 8, 9, 6, 3},
            {9, 8, 6, 2, 4, 3, 1, 7, 5},
            {3, 7, 1, 9, 6, 5, 2, 8, 4},
    };

    @Test
    public void solve() {
        SudokuGame game = new SudokuGame(board);
        game.solve();
        assertArrayEquals(board, solved);
    }

    @Test
    public void initializeRandom() {
    }

    @Test
    public void set() {
    }

    @Test
    public void isCurrentlyValid() {
    }

    @Test
    public void print() {
    }

    @Test
    public void getValues() {
    }
}