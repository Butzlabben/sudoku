package de.butzlabben.sudoku;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Random;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SudokuGame {

    private final static int DEVIATION_MEAN_DIFFICULTY = 4;

    // First value is the row, second the column
    @Getter
    private int[][] values;

    /**
     * Solves the sudoku via the backtrace algorithm
     */
    public boolean solve() {
        int row = 0, column = 0;
        boolean isCompletelyFilled = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (values[i][j] == 0) {
                    row = i;
                    column = j;
                    isCompletelyFilled = false;
                    break;
                }
            }
            if (!isCompletelyFilled) break;
        }

        if (isCompletelyFilled) return true;
        for (int value = 1; value <= 9; value++) {
            SudokuGame copy = toBuilder().build();
            if (copy.set(row, column, value)) {
                // We can set the value safely, as we know, that is valid
                values[row][column] = value;

                // Check if board is completely filled
                if (solve()) {
                    return true;
                }
            }
            // If we come back here, reset value
            values[row][column] = 0;
        }
        return false;
    }

    public void initializeRandom(Difficulty difficulty) {
        values = new int[9][9];
        Random random = new Random();
        fillBox(0, 0);
        fillBox(3, 3);
        fillBox(6, 6);
        solve();
        int deviation = random.nextInt(DEVIATION_MEAN_DIFFICULTY * 2 + 1) - DEVIATION_MEAN_DIFFICULTY;
        int numbersToRemove = difficulty.meanNumbersToRemove - deviation;
        for (int i = 0; i < numbersToRemove; i++) {
            int row, column;
            do {
                row = random.nextInt(9);
                column = random.nextInt(9);
            } while(values[row][column] == 0);

            values[row][column] = 0;
        }
    }

    public void initializeRandom() {
        initializeRandom(Difficulty.EASY);
    }

    /**
     * @param row    in which row to set the value
     * @param column in which column to set the value
     * @param value  is the value
     * @return true if operation could be completed or false if the sudoku would then be not valid. Value is then not set
     */
    public boolean set(int row, int column, int value) {
        SudokuGame copy = toBuilder().build();
        copy.values[row][column] = value;
        if (!copy.isCurrentlyValid())
            return false;
        values[row][column] = value;
        return true;
    }

    /**
     * Fills randomly a 3x3 box
     */
    private void fillBox(int row, int column) {
        Random random = new Random();
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                int number;
                do {
                    number = 1 + random.nextInt(9);
                }
                while (!set(i, j, number));
            }
        }
    }

    public boolean isCurrentlyValid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!isFieldValid(i, j))
                    return false;
            }
        }
        return true;
    }

    private boolean isFieldValid(int row, int column) {
        int number = values[row][column];
        if (number == 0)
            return true;
        boolean rowValid = !isRepeatedInRow(row, number);
        boolean columnValid = !isRepeatedInColumn(column, number);
        boolean boxValid = !isRepeatedInBox(row - row % 3, column - column % 3, number);
        return rowValid && columnValid && boxValid;
    }

    private boolean isRepeatedInBox(int row, int column, int number) {
        int count = 0;
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                if (values[i][j] == number) count++;
                if (count == 2)
                    return true;
            }
        }
        return false;
    }

    private boolean isRepeatedInRow(int row, int number) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (values[row][i] == number) count++;
            if (count == 2)
                return true;
        }
        return false;
    }

    private boolean isRepeatedInColumn(int column, int number) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (values[i][column] == number) count++;
            if (count == 2)
                return true;
        }
        return false;
    }

    public void print() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            builder.append(Arrays.toString(values[i]));
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }
}
