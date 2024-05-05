package game.solver;

import game.core.Board;
import game.core.Cell;
import game.ui.MainFrame;

import java.util.*;
import java.util.stream.Collectors;

public class CSP {
    private final boolean mrv;
    private final boolean lcv;
    private final boolean forwardChecking;

    public CSP(boolean mrv, boolean lcv, boolean forwardChecking) {
        this.mrv = mrv;
        this.lcv = lcv;
        this.forwardChecking = forwardChecking;
    }

    public Boolean solve(Board board, MainFrame.BoardUpdater boardUpdater) {
        if (complete(board)) return true;
        Cell cell = getEmptyCell(board);
        for (Integer num : getDomainValues(board, cell)) {
            if (isValid(board, cell.getRow(), cell.getCol(), num)) {
                cell.setValue(num);
                if (forwardChecking) {
                    if (checkInference(board, cell, num)) {
                        runInference(board, cell, num);
                    } else {
                        cell.setValue(0);
                    }
                }
                boardUpdater.update(board);
                return solve(board, boardUpdater);
            }
        }
        return false;
    }

    // MRV
    private Cell getEmptyCell(Board board) {
        if (mrv) {
            return getMinimumRemainingValueCell(board);
        } else {
            return getFirstEmptyCell(board);
        }
    }

    private Cell getMinimumRemainingValueCell(Board board) {
        Cell cell = null;
        for (int i = 0; i < board.getBoardSize().value; i++) {
            for (int j = 0; j < board.getBoardSize().value; j++) {
                if (board.getNumber(i, j) == 0) {
                    if (cell == null) {
                        cell = board.getCell(i, j);
                    } else {
                        if (board.getCell(i, j).getDomain().size() < cell.getDomain().size()) {
                            cell = board.getCell(i, j);
                        }
                    }
                }
            }
        }
        return cell;
    }

    private Cell getFirstEmptyCell(Board board) {
        for (int i = 0; i < board.getBoardSize().value; i++) {
            for (int j = 0; j < board.getBoardSize().value; j++) {
                if (board.getNumber(i, j) == 0) {
                    return board.getCell(i, j);
                }
            }
        }
        return null;
    }

    // LCV
    private Iterable<Integer> getDomainValues(Board board, Cell cell) {
        if (lcv) {
            Map<Integer, Integer> orderedValues = new HashMap<>();
            for (Integer value : cell.getDomain()) {
                orderedValues.put(value, getConstrainingAmount(board, cell, value));
            }
            List<Map.Entry<Integer, Integer>> resultList = orderedValues.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .toList();
            List<Integer> sortedValues = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : resultList) {
                sortedValues.add(entry.getKey());
            }
            return sortedValues;
        } else {
            return cell.getDomain();
        }
    }

    private Integer getConstrainingAmount(Board board, Cell cell, Integer value) {
        return getRowConstraintAmount(board, cell, value) + getColumnConstraintAmount(board, cell, value) + getShapeConstraintAmount(board, cell, value);
    }

    private Integer getRowConstraintAmount(Board board, Cell cell, Integer value) {
        int amount = 0;
        for (int i = 0; i < board.getBoardSize().value; i++) {
            if (i != cell.getCol()
                    && board.getNumber(cell.getRow(), i) != 0
                    && board.getCell(cell.getRow(), i).getDomain().contains(value)) {
                amount++;
            }
        }
        return amount;
    }

    private Integer getColumnConstraintAmount(Board board, Cell cell, Integer value) {
        int amount = 0;
        for (int i = 0; i < board.getBoardSize().value; i++) {
            if (i != cell.getRow()
                    && board.getNumber(i, cell.getCol()) != 0
                    && board.getCell(i, cell.getCol()).getDomain().contains(value)) {
                amount++;
            }
        }
        return amount;
    }

    private Integer getShapeConstraintAmount(Board board, Cell cell, Integer value) {
        int amount = 0;
        for (ArrayList<Cell> shape : board.getAllShapes()) {
            if (shape.getFirst().getShapeNumber() == cell.getShapeNumber()) {
                for (Cell shapeCell : shape) {
                    if (shapeCell.getRow() != cell.getRow()
                            && shapeCell.getCol() != cell.getCol()
                            && shapeCell.getDomain().contains(value)) {
                        amount++;
                    }
                }
            }
        }
        return amount;
    }

    // Forward checking

    private Boolean checkInference(Board board, Cell cell, Integer num) {
        return checkRowInference(board, cell, num) && checkColumnInference(board, cell, num) && checkShapeInference(board, cell, num);
    }

    private Boolean checkRowInference(Board board, Cell cell, Integer num) {
        ArrayList<Cell> rowCells = getRowCells(board, cell.getRow());
        rowCells.remove(cell);
        for (Cell rowCell : rowCells) {
            if (rowCell.getValue() == 0 && rowCell.getDomain().size() == 1 && rowCell.getDomain().contains(num)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumnInference(Board board, Cell cell, Integer num) {
        ArrayList<Cell> columnCells = getColumnCells(board, cell.getCol());
        columnCells.remove(cell);
        for (Cell colCell : columnCells) {
            if (colCell.getValue() == 0 && colCell.getDomain().size() == 1 && colCell.getDomain().contains(num)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkShapeInference(Board board, Cell cell, Integer num) {
        ArrayList<Cell> shapeCells = getShapeCells(board, cell.getRow(), cell.getCol());
        shapeCells.remove(cell);
        for (Cell shapeCell : shapeCells) {
            if (shapeCell.getValue() == 0 && shapeCell.getDomain().size() == 1 && shapeCell.getDomain().contains(num)) {
                return false;
            }
        }
        return true;
    }

    private void runInference(Board board, Cell cell, Integer num) {
        for (Cell rowCell : getRowCells(board, cell.getRow())) {
            rowCell.removeFromDomain(num);
        }
        for (Cell colCell : getColumnCells(board, cell.getCol())) {
            colCell.removeFromDomain(num);
        }
        for (Cell shapeCell : getShapeCells(board, cell.getRow(), cell.getCol())) {
            shapeCell.removeFromDomain(num);
        }
    }

    private boolean complete(Board board) {
        for (int i = 0; i < board.getBoardSize().value; i++) {
            for (int j = 0; j < board.getBoardSize().value; j++) {
                if (board.getNumber(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(Board board, int row, int col, int num) {
        return checkRow(board, row, num) && checkColumn(board, col, num) && checkShape(board, row, col, num);
    }

    private static boolean checkRow(Board board, int row, int num) {
        return !getRowValues(board, row).contains(num);
    }

    private static boolean checkColumn(Board board, int col, int num) {
        return !getColumnValues(board, col).contains(num);
    }

    private static boolean checkShape(Board board, int row, int col, int num) {
        return !getShapeValues(board, row, col).contains(num);
    }

    private static Set<Integer> getRowValues(Board board, int row) {
        return getRowCells(board, row).stream().map(Cell::getValue).collect(Collectors.toSet());
    }

    private static Set<Integer> getColumnValues(Board board, int col) {
        return getColumnCells(board, col).stream().map(Cell::getValue).collect(Collectors.toSet());
    }

    private static Set<Integer> getShapeValues(Board board, int row, int col) {
        return getShapeCells(board, row, col).stream().map(Cell::getValue).collect(Collectors.toSet());
    }

    private static ArrayList<Cell> getRowCells(Board board, int row) {
        ArrayList<Cell> rowCells = new ArrayList<>();
        for (int i = 0; i < board.getBoardSize().value; i++) {
            rowCells.add(board.getCell(row, i));
        }
        return rowCells;
    }

    private static ArrayList<Cell> getColumnCells(Board board, int col) {
        ArrayList<Cell> columnCells = new ArrayList<>();
        for (int i = 0; i < board.getBoardSize().value; i++) {
            columnCells.add(board.getCell(i, col));
        }
        return columnCells;
    }

    private static ArrayList<Cell> getShapeCells(Board board, int row, int col) {
        ArrayList<Cell> shapeValues = new ArrayList<>();
        int shapeNumber = board.getCell(row, col).getShapeNumber();
        outerloop:
        for (int i = 0; i < board.getBoardSize().value; i++) {
            for (int j = 0; j < board.getBoardSize().value; j++) {
                if (board.getCell(i, j).getShapeNumber() == shapeNumber) {
                    shapeValues.add(board.getCell(i, j));
                    if (shapeValues.size() == board.getBoardSize().value - 1) {
                        break outerloop;
                    }
                }
            }
        }
        return shapeValues;
    }

}
