package game.generator;

import game.core.Board;
import game.core.BoardSize;
import game.core.Cell;
import game.core.GameDifficulty;
import game.solver.Backtracking;

import java.util.ArrayList;
import java.util.Collections;

public class ProblemGenerator {

    public static void generateProblem(Board board) {
        BoardSize boardSize = board.getBoardSize();
        int remove = getRemoveNumber(boardSize, board.getGameDifficulty());
        while (remove > 1) {
            int x, y;
            x = getRandomNumber(0, boardSize.value);
            y = getRandomNumber(0, boardSize.value);
            if (board.getNumber(x, y) != -1) {
                board.getCell(x, y).setValue(-1);
                if (true) {
                    remove--;
                    System.out.println("Removed (" + x + "," + y + ") Remaining: " + remove);
                } else {
//                    board.getCell(x, y).setValue(temp);
                }
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
