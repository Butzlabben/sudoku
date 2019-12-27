package de.butzlabben.sudoku;

public class Main {

    public static void main(String[] args) {
        SudokuGame game = new SudokuGame();
        game.initializeRandom();
        game.print();
        game.solve();
        game.print();
    }
}
