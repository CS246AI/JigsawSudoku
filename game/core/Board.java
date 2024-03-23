package game.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board {
    private final Integer[] nums;
    private final Integer[][] board;
    private final Size boardSize;

    public Board() {
        this(Size.SMALL);
    }

    public Board(Size size) {
        boardSize = size;
        board = new Integer[size.value][size.value];
        nums = new Integer[size.value];
        populateBoard();
    }

    private void populateBoard() {
        generateNums();
        board[0] = nums;
        for (int i = 1; i < boardSize.value; i++) {
            board[i] = shift(board[i - 1]);
        }
        List<Integer[]> intList = Arrays.asList(board);
        Collections.shuffle(intList);
        intList.toArray(board);
    }

    private Integer[] shift(Integer[] integers) {
        Integer[] temp = new Integer[boardSize.value];
        temp[0] = integers[integers.length - 1];
        System.arraycopy(integers, 0, temp, 1, integers.length - 1);
        return temp;
    }

    private void generateNums() {
        for (int i = 0; i < boardSize.value; i++) {
            nums[i] = i + 1;
        }
        List<Integer> intList = Arrays.asList(nums);
        Collections.shuffle(intList);
        intList.toArray(nums);
    }

    public void printBoard() {
        for (int i = 0; i < boardSize.value; i++) {
            for (int j = 0; j < boardSize.value; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public enum Size {
        SMALL(5),
        MEDIUM(7),
        BIG(9);

        public final int value;

        Size(int size) {
            this.value = size;
        }
    }
}
