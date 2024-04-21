package game.core;

import java.util.ArrayList;
import java.util.HashSet;

public class BoardHelper {
    private final Board board;
    private final BoardSize boardSize;

    public BoardHelper(Board board) {
        this.board = board;
        boardSize = board.getBoardSize();
    }

    public ArrayList<ArrayList<Cell>> getAllPossibleShapes(int x, int y, int n, boolean[][] occupied) {
        ArrayList<ArrayList<Cell>> allShapes = new ArrayList<>();
        ArrayList<Cell> currentShape = new ArrayList<>();
        HashSet<Integer> currentNumbers = new HashSet<>();
        boolean[][] visited = new boolean[boardSize.value][boardSize.value];
        exploreShapes(x, y, n, currentShape, allShapes, currentNumbers, visited, occupied);
        return allShapes;
    }

    private void exploreShapes(int x, int y, int n, ArrayList<Cell> currentShape, ArrayList<ArrayList<Cell>> allShapes, HashSet<Integer> currentNumbers, boolean[][] visited, boolean[][] occupied) {
        if (x < 0 || y < 0 || x >= boardSize.value || y >= boardSize.value || visited[x][y] || occupied[x][y] || currentNumbers.contains(board.getNumber(x, y)))
            return;

        visited[x][y] = true;
        currentShape.add(board.getCell(x, y));
        currentNumbers.add(board.getNumber(x, y));

        if (currentShape.size() == n) {
            if (currentNumbers.size() == n) {
                allShapes.add(new ArrayList<>(currentShape));
            }
        } else {
            exploreShapes(x + 1, y, n, currentShape, allShapes, currentNumbers, visited, occupied);
            exploreShapes(x - 1, y, n, currentShape, allShapes, currentNumbers, visited, occupied);
            exploreShapes(x, y + 1, n, currentShape, allShapes, currentNumbers, visited, occupied);
            exploreShapes(x, y - 1, n, currentShape, allShapes, currentNumbers, visited, occupied);
        }

        visited[x][y] = false;
        currentShape.removeLast();
        currentNumbers.remove(board.getNumber(x, y));
    }
}