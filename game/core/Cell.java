package game.core;

public class Cell {
    int row, col, value, shapeNumber;

    public Cell(int value) {
        this.value = value;
    }

    public void setRowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setShapeNumber(int shapeNumber) {
        this.shapeNumber = shapeNumber;
    }

    public int getShapeNumber() {
        return shapeNumber;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
