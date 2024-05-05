package game.solver;

import game.core.Board;
import game.core.Cell;
import game.ui.MainFrame;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimulatedAnnealing extends Heuristic {

    public void solve(Board board, MainFrame.BoardUpdater boardUpdater, double temperature, double coolingRate) {
        while (temperature > 0.1) {
            int currentHeuristic = computeHeuristic(board);
            Cell cell = makeRandomChange(board, boardUpdater);
            if (cell == null) return;
            int newHeuristic = computeHeuristic(board);

            if (newHeuristic < currentHeuristic || acceptanceProbability(currentHeuristic, newHeuristic, temperature) > new Random().nextDouble()) {
                // Move accepted
            } else {
                undoChange(board, cell);
            }
            boardUpdater.update(board);
            temperature *= coolingRate;
        }
    }

    private Cell makeRandomChange(Board board, MainFrame.BoardUpdater boardUpdater) {
        ArrayList<Cell> emptyCells = new ArrayList<>();
        for (int row = 0; row < board.getBoardSize().value; row++) {
            for (int col = 0; col < board.getBoardSize().value; col++) {
                if (board.getNumber(row, col) == 0) {
                    Cell newCell = new Cell(board.getCell(row, col).getValue(), board.getCell(row, col).getDomain().size());
                    newCell.setRowCol(row, col);
                    emptyCells.add(newCell);
                }
            }
        }
        if (!emptyCells.isEmpty()) {
            Cell cell = emptyCells.get(new Random().nextInt(emptyCells.size()));
            ArrayList<Integer> possibleValues = IntStream.rangeClosed(1, board.getBoardSize().value)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayList::new));
            board.getCell(cell.getRow(), cell.getCol()).setValue(possibleValues.get(new Random().nextInt(possibleValues.size())));
            boardUpdater.update(board);
            return cell;
        }
        return null;
    }

    private void undoChange(Board board, Cell cell) {
        board.getCell(cell.getRow(), cell.getCol()).setValue(0);
    }

    private double acceptanceProbability(int heuristic, int newHeuristic, double temperature) {
        if (newHeuristic < heuristic)
            return 1.0;
        return Math.exp((heuristic - newHeuristic) / temperature);
    }
}
