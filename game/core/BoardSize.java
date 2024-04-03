package game.core;

public enum BoardSize {
    SMALL(5),
    MEDIUM(7),
    BIG(9);

    public final int value;

    BoardSize(int size) {
        this.value = size;
    }
}