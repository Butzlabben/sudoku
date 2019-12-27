package de.butzlabben.sudoku;

public enum Difficulty {
    EASY(44), MEDIUM(52), HARD(60);

    final int meanNumbersToRemove;

    Difficulty(int meanNumbersToRemove) {
        this.meanNumbersToRemove = meanNumbersToRemove;
    }
}
