package game.generator;

import game.core.BoardSize;
import game.core.Cell;

import java.util.Random;

public class BoardGenerator {

    public static Cell[][] generateBoard(BoardSize size) {
        Cell[][] board = new Cell[size.value][size.value];
        board[0] = generateRow(size);
        for (int i = 1; i < size.value; i++) {
            board[i] = shift(board[i - 1]);
        }
        shuffleBoard(board);
        for (int row = 0; row < size.value; row++) {
            for (int col = 0; col < size.value; col++) {
                board[row][col].setRowCol(row, col);
            }
        }
        return board;
    }

    private static Cell[] generateRow(BoardSize size) {
        Cell[] row = generateArray(size.value);
        shuffleArray(row);
        return row;
    }

    private static Cell[] generateArray(int n) {
        Cell[] array = new Cell[n];
        for (int i = 0; i < n; i++) {
            array[i] = new Cell(i + 1, n);
        }
        return array;
    }

    private static void shuffleArray(Cell[] array) {
        int index;
        Cell temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public static void shuffleBoard(Cell[][] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndex = random.nextInt(array.length);
            if (i != randomIndex) {
                // Swap outer array elements
                Cell[] temp = array[i];
                array[i] = array[randomIndex];
                array[randomIndex] = temp;
            }
        }
    }

    private static Cell[] shift(Cell[] previous) {
        Cell[] row = new Cell[previous.length];
        row[0] = new Cell(previous[previous.length - 1].getValue(), previous[previous.length - 1].getDomain().size());
        for (int i = 1; i < previous.length; i++) {
            row[i] = new Cell(previous[i - 1].getValue(), previous[i - 1].getDomain().size());
        }
        return row;
    }
}
