package game.generator;

import game.core.BoardSize;

import java.util.Random;

public class BoardGenerator {

    public static int[][] generateBoard(BoardSize size) {
        int[][] board = new int[size.value][size.value];
        board[0] = generateRow(size);
        for (int i = 1; i < size.value; i++) {
            board[i] = shift(board[i - 1]);
        }
        shuffleBoard(board);
        return board;
    }

    private static int[] generateRow(BoardSize size) {
        int[] row = generateArray(size.value);
        shuffleArray(row);
        return row;
    }

    private static int[] generateArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    private static void shuffleArray(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public static void shuffleBoard(int[][] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndex = random.nextInt(array.length);
            if (i != randomIndex) {
                // Swap outer array elements
                int[] temp = array[i];
                array[i] = array[randomIndex];
                array[randomIndex] = temp;
            }
        }
    }

    private static int[] shift(int[] previous) {
        int[] row = new int[previous.length];
        row[0] = previous[previous.length - 1];
        System.arraycopy(previous, 0, row, 1, previous.length - 1);
        return row;
    }
}
