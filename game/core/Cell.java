package game.core;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cell {
    int row, col, value, shapeNumber;
    Set<Integer> domain;

    public Cell(int value, int size) {
        this.value = value;
        this.domain = IntStream.rangeClosed(1, size).boxed().collect(Collectors.toSet());
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

    public Set<Integer> getDomain() {
        return domain;
    }

    public void removeFromDomain(Integer value) {
        domain.remove(value);
    }

    public void removeDomain(Set<Integer> removeDomains) {
        domain.removeAll(removeDomains);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
