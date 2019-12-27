package de.butzlabben.sudoku;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Random;

@Builder(toBuilder = true)
public class SudokuGame {

    // First value is the row, second the column
    @Getter
    private int[][] values = new int[9][9];

    public void initializeRandom() {
        Random random = new Random();
        // Random number between 10 and 20
        int numberCount = 11 + random.nextInt(10);

        for (int i = 0; i < numberCount; i++) {
            // Generate random row and column values
            int row = random.nextInt(9);
            int column = random.nextInt(9);
            // Check if a value isn't already set in this place
            while (values[row][column] != 0) {
                row = random.nextInt(9);
                column = random.nextInt(9);
            }

            int value = 1 + random.nextInt(9);
            // Set random values until it is valid
            while (!set(row, column, value)) {
                value = 1 + random.nextInt(9);
            }
        }
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
        if(number == 0)
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
