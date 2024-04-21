package game.core;

import game.generator.BoardGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private Cell[][] board;
    private BoardSize boardSize;
    private BoardHelper helper;
    private boolean[][] occupied;
    private ArrayList<ArrayList<Cell>> allShapes;

    public Board() {
        this(BoardSize.SMALL);
    }

    public Board(BoardSize size) {
        resize(size);
    }

    public void resize(BoardSize size) {
        boardSize = size;
        board = BoardGenerator.generateBoard(size);
        helper = new BoardHelper(this);
        occupied = new boolean[size.value][size.value];
        allShapes = new ArrayList<>();
        if (!generateAllShapes()) {
            resize(size);
        } else {
            int shapeNumber = 1;
            for (ArrayList<Cell> shape : allShapes) {
                for (Cell cell : shape) {
                    board[cell.row][cell.col].setShapeNumber(shapeNumber);
                }
                shapeNumber++;
            }
        }
    }

    public boolean generateAllShapes() {
        return tryPlaceShapes();
    }

    private boolean tryPlaceShapes() {
        if (isFullyOccupied()) {
            return true;
        }

        int startX = new Random().nextInt(boardSize.value);
        int startY = new Random().nextInt(boardSize.value);

        for (int i = 0; i < boardSize.value * boardSize.value; i++) {
            int x = (startX + i) % boardSize.value;
            int y = (startY + (startX + i) / boardSize.value) % boardSize.value;

            if (!occupied[x][y]) {
                ArrayList<ArrayList<Cell>> possibleShapes = helper.getAllPossibleShapes(x, y, boardSize.value, occupied);
                for (ArrayList<Cell> shape : possibleShapes) {
                    placeShape(shape);
                    if (tryPlaceShapes()) {
                        return true;
                    }
                    removeShape(shape);
                }

                return false;
            }
        }

        return false;
    }

    private boolean isFullyOccupied() {
        for (int i = 0; i < boardSize.value; i++) {
            for (int j = 0; j < boardSize.value; j++) {
                if (!occupied[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void placeShape(ArrayList<Cell> shape) {
        for (Cell cell : shape) {
            occupied[cell.row][cell.col] = true;
        }
        allShapes.add(shape);
    }

    private void removeShape(ArrayList<Cell> shape) {
        for (Cell cell : shape) {
            occupied[cell.row][cell.col] = false;
        }
        allShapes.remove(shape);
    }

    public void printBoard() {
        for (int i = 0; i < boardSize.value; i++) {
            for (int j = 0; j < boardSize.value; j++) {
                System.out.print(board[i][j] + " (" + board[i][j].getShapeNumber() + ") ");
            }
            System.out.println();
        }
    }

    public void printShapes() {
        int shapeNumber = 1;
        for (ArrayList<Cell> shape : allShapes) {
            System.out.println("Shape " + shapeNumber + ":");
            for (Cell cell : shape) {
                System.out.print("[" + cell.getValue() + " " + cell.getRow() + "," + cell.getCol() + "] ");
            }
            System.out.println();
            shapeNumber++;
        }
    }

    public ArrayList<ArrayList<Cell>> getAllShapes() {
        return allShapes;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public int getNumber(int row, int col) {
        return getCell(row, col).value;
    }
}
