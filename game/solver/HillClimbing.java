package game.solver;

import game.core.Board;
import game.core.Cell;
import game.ui.MainFrame;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HillClimbing extends Heuristic {

    public void stochasticHillClimbing(Board board, MainFrame.BoardUpdater boardUpdater, int maxIterationsWithoutImprovement) {
        int currentHeuristic = computeHeuristic(board);
        int iterationsWithoutImprovement = 0;
        boolean improvement = true;
        while (improvement || iterationsWithoutImprovement < maxIterationsWithoutImprovement) {
            boardUpdater.update(board);
            improvement = false;
            ArrayList<Cell> emptyCells = getEmptyCells(board);
            if (emptyCells.isEmpty() || currentHeuristic == 0) {
                return;
            }
            Collections.shuffle(emptyCells);
            for (Cell cell : emptyCells) {
                ArrayList<Cell> shape = null;
                for (ArrayList<Cell> cells : board.getAllShapes()) {
                    if (cells.getFirst().getShapeNumber() == board.getCell(cell.getRow(), cell.getCol()).getShapeNumber()) {
                        shape = cells;
                        break;
                    }
                }
                Set<Integer> usedNumbers = new HashSet<>();
                usedNumbers.addAll(getRowValues(board, cell.getRow()));
                usedNumbers.addAll(getColumnValues(board, cell.getCol()));
                assert shape != null;
                usedNumbers.addAll(getShapeValues(shape));
                ArrayList<Integer> possibleValues = IntStream.rangeClosed(1, board.getBoardSize().value)
                        .boxed()
                        .filter(num -> !usedNumbers.contains(num))
                        .collect(Collectors.toCollection(ArrayList::new));
                if (!possibleValues.isEmpty()) {
                    int newValue = possibleValues.get(new Random().nextInt(possibleValues.size()));
                    makeChange(cell, newValue);
                    int newHeuristic = computeHeuristic(board);
                    if (newHeuristic <= currentHeuristic) {
                        currentHeuristic = newHeuristic;
                        improvement = true;
                        iterationsWithoutImprovement = 0;
                        break;
                    } else {
                        cell.setValue(0);
                        iterationsWithoutImprovement++;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public void firstChoiceHillClimbing(Board board, MainFrame.BoardUpdater boardUpdater) {
        int currentHeuristic = computeHeuristic(board);
        boolean improvement = true;
        while (improvement) {
            boardUpdater.update(board);
            improvement = false;
            ArrayList<Cell> emptyCells = getEmptyCells(board);
            Collections.shuffle(emptyCells);
            for (Cell cell : emptyCells) {
                ArrayList<Cell> shape = null;
                for (ArrayList<Cell> cells : board.getAllShapes()) {
                    if (cells.getFirst().getShapeNumber() == board.getCell(cell.getRow(), cell.getCol()).getShapeNumber()) {
                        shape = cells;
                        break;
                    }
                }
                Set<Integer> usedNumbers = new HashSet<>();
                usedNumbers.addAll(getRowValues(board, cell.getRow()));
                usedNumbers.addAll(getColumnValues(board, cell.getCol()));
                assert shape != null;
                usedNumbers.addAll(getShapeValues(shape));
                ArrayList<Integer> possibleValues = IntStream.rangeClosed(1, board.getBoardSize().value)
                        .boxed()
                        .filter(num -> !usedNumbers.contains(num))
                        .collect(Collectors.toCollection(ArrayList::new));
                if (!possibleValues.isEmpty()) {
                    int newValue = possibleValues.get(new Random().nextInt(possibleValues.size()));
                    makeChange(cell, newValue);
                    int newHeuristic = computeHeuristic(board);
                    if (newHeuristic < currentHeuristic) {
                        currentHeuristic = newHeuristic;
                        improvement = true;
                        break;
                    } else {
                        cell.setValue(0);
                    }
                }
            }
        }
    }

    public void randomRestartHillClimbing(Board board, MainFrame.BoardUpdater boardUpdater, int numberOfRestarts) {
        Board initialBoard = new Board(board);
        Board bestSolution = new Board(board);
        int bestHeuristic = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfRestarts; i++) {
            System.out.println("Iteration " + i + " of " + numberOfRestarts);
            stochasticHillClimbing(board, boardUpdater, 10);
            int currentHeuristic = computeHeuristic(board);
            if (currentHeuristic < bestHeuristic) {
                bestHeuristic = currentHeuristic;
                saveCurrentSolution(board, bestSolution);
                boardUpdater.update(board);
            }
            loadSolution(board, initialBoard);
        }
        loadSolution(board, bestSolution);
        boardUpdater.update(board);
    }

    private static void makeChange(Cell cell, int newValue) {
        cell.setValue(newValue);
    }

    private static ArrayList<Cell> getEmptyCells(Board board) {
        ArrayList<Cell> emptyCells = new ArrayList<>();
        for (int row = 0; row < board.getBoardSize().value; row++) {
            for (int col = 0; col < board.getBoardSize().value; col++) {
                if (board.getNumber(row, col) == 0) {
                    emptyCells.add(board.getCell(row, col));
                }
            }
        }
        return emptyCells;
    }

    private static void saveCurrentSolution(Board board, Board bestSolution) {
        for (int i = 0; i < board.getBoardSize().value; i++) {
            for (int j = 0; j < board.getBoardSize().value; j++) {
                bestSolution.getCell(i, j).setValue(board.getCell(i, j).getValue());
            }
        }
    }

    private static void loadSolution(Board board, Board bestSolution) {
        for (int i = 0; i < board.getBoardSize().value; i++) {
            for (int j = 0; j < board.getBoardSize().value; j++) {
                board.getCell(i, j).setValue(bestSolution.getCell(i, j).getValue());
            }
        }
    }
}
