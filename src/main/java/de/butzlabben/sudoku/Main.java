package de.butzlabben.sudoku;

public class Main {

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 8, 3, 0, 0, 0, 0},
                {0, 0, 0, 0, 7, 4, 0, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 4, 0, 0, 6, 0, 0, 8},
                {2, 0, 0, 0, 8, 0, 0, 0, 9},
                {0, 6, 0, 1, 0, 2, 4, 0, 0},
                {0, 0, 5, 7, 0, 0, 9, 0, 3},
                {9, 8, 0, 0, 0, 0, 0, 0, 5},
                {0, 0, 1, 0, 6, 5, 0, 0, 4}
        }; // Source: https://www.raetseldino.de/sudoku-schwer/sudoku-16-sehr-schwer.jpg
        SudokuGame game = new SudokuGame(board);
        game.initializeRandom(Difficulty.HARD);
        game.print();
        game.solve();
        game.print();
    }
}
