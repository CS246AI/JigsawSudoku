package game.solver;

import game.core.Board;
import game.core.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public abstract class Heuristic {
    public int computeHeuristic(Board board) {
        int conflicts = 0;
        for (int i = 0; i < board.getBoardSize().value; i++) {
            conflicts += checkConflicts(getRow(board, i));
            conflicts += checkConflicts(getColumn(board, i));
        }
        for (ArrayList<Cell> shape : board.getAllShapes()) {
            conflicts += checkConflicts(shape);
        }
        return conflicts;
    }

    private static int checkConflicts(ArrayList<Cell> cells) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (Cell cell : cells) {
//            if (cell.getValue() != 0) {
                if (!counts.containsKey(cell.getValue())) {
                    counts.replace(cell.getValue(), 0);
                }
                counts.put(cell.getValue(), counts.getOrDefault(cell.getValue(), 0) + 1);
//            }
        }
        return counts.values().stream()
                .filter(integer -> integer > 1)
                .mapToInt(integer -> integer - 1)
                .sum();
    }

    public static ArrayList<Cell> getRow(Board board, int row) {
        ArrayList<Cell> rowCells = new ArrayList<>();
        for (int column = 0; column < board.getBoardSize().value; column++) {
            rowCells.add(board.getCell(row, column));
        }
        return rowCells;
    }

    public static ArrayList<Integer> getRowValues(Board board, int row) {
        return getRow(board, row).stream().mapToInt(Cell::getValue).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Cell> getColumn(Board board, int column) {
        ArrayList<Cell> columnCells = new ArrayList<>();
        for (int row = 0; row < board.getBoardSize().value; row++) {
            columnCells.add(board.getCell(row, column));
        }
        return columnCells;
    }

    public static ArrayList<Integer> getColumnValues(Board board, int col) {
        return getColumn(board, col).stream().mapToInt(Cell::getValue).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Integer> getShapeValues(ArrayList<Cell> shape) {
        return shape.stream().mapToInt(Cell::getValue).boxed().collect(Collectors.toCollection(ArrayList::new));
    }
}
