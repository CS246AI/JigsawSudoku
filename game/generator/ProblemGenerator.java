package game.generator;

import game.core.Board;
import game.core.BoardSize;
import game.core.Cell;
import game.core.GameDifficulty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ProblemGenerator {

    public static void generateProblem(Board board) {
        BoardSize boardSize = board.getBoardSize();
        int remove = getRemoveNumber(boardSize, board.getGameDifficulty());
        ArrayList<Cell> cells = board.getAllShapes().stream().flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));
        while (remove > 1 && !cells.isEmpty()) {
            int x, y;
            Cell cell = cells.remove(getRandomNumber(0, cells.size() - 1));
            x = cell.getRow();
            y = cell.getCol();
            int temp = board.getNumber(x, y);
            board.getCell(x, y).setValue(0);
            if (BacktrackingSolverChecker.checkIfSolvable(new Board(board))) {
                remove--;
                System.out.println("Removed (" + x + "," + y + ") Remaining: " + remove);
            } else {
                board.getCell(x, y).setValue(temp);
            }
        }
    }

    private static int getRemoveNumber(BoardSize boardSize, GameDifficulty gameDifficulty) {
        int total = boardSize.value * boardSize.value;
        if (boardSize == BoardSize.SMALL) {
            if (gameDifficulty == GameDifficulty.EASY) {
                return total - getRandomNumber(11, 14);
            } else if (gameDifficulty == GameDifficulty.MEDIUM) {
                return total - getRandomNumber(9, 10);
            } else if (gameDifficulty == GameDifficulty.HARD) {
                return total - getRandomNumber(8, 9);
            }
        } else if (boardSize == BoardSize.MEDIUM) {
            if (gameDifficulty == GameDifficulty.EASY) {
                return total - getRandomNumber(21, 27);
            } else if (gameDifficulty == GameDifficulty.MEDIUM) {
                return total - getRandomNumber(19, 21);
            } else if (gameDifficulty == GameDifficulty.HARD) {
                return total - getRandomNumber(16, 18);
            }
        } else if (boardSize == BoardSize.BIG) {
            if (gameDifficulty == GameDifficulty.EASY) {
                return total - getRandomNumber(36, 46);
            } else if (gameDifficulty == GameDifficulty.MEDIUM) {
                return total - getRandomNumber(32, 35);
            } else if (gameDifficulty == GameDifficulty.HARD) {
                return total - getRandomNumber(28, 31);
            }
        }
        return -1;
    }

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
