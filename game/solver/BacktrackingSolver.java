package game.solver;

import game.core.Board;
import game.ui.MainFrame;

import java.util.ArrayList;

public class BacktrackingSolver {
    public static Boolean solve(Board board, MainFrame.BoardUpdater boardUpdater) {
        int size = board.getBoardSize().value;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getNumber(i, j) == 0) {
                    for (int k = 1; k <= size; k++) {
                        if (isValid(board, i, j, k)) {
                            board.getCell(i, j).setValue(k);
                            boardUpdater.update(board);
                            return solve(board, boardUpdater);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static Boolean checkIfSolvable(Board board) {
        int size = board.getBoardSize().value;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getNumber(i, j) == 0) {
                    for (int k = 1; k <= size; k++) {
                        if (isValid(board, i, j, k)) {
                            board.getCell(i, j).setValue(k);
                            return checkIfSolvable(new Board(board));
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid(Board board, int row, int col, int num) {
        return checkRow(board, row, num) && checkColumn(board, col, num) && checkShape(board, row, col, num);
    }

    private static boolean checkRow(Board board, int row, int num) {
        for (int i = 0; i < board.getBoardSize().value; i++) {
            if (board.getNumber(row, i) == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumn(Board board, int col, int num) {
        for (int i = 0; i < board.getBoardSize().value; i++) {
            if (board.getNumber(i, col) == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkShape(Board board, int row, int col, int num) {
        ArrayList<Integer> shapeNumbers = new ArrayList<>();
        int shapeNumber = board.getCell(row, col).getShapeNumber();
        outerloop:
        for (int i = 0; i < board.getBoardSize().value; i++) {
            for (int j = 0; j < board.getBoardSize().value; j++) {
                if (board.getCell(i, j).getShapeNumber() == shapeNumber) {
                    shapeNumbers.add(board.getNumber(i, j));
                    if (shapeNumbers.size() == board.getBoardSize().value - 1) {
                        break outerloop;
                    }
                }
            }
        }
        return !shapeNumbers.contains(num);
    }
}
