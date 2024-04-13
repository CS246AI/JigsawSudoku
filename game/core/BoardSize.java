package game.core;

public enum BoardSize {
    SMALL(5, "Small"),
    MEDIUM(7, "Medium"),
    BIG(9, "Big");

    public final int value;
    public final String name;

    BoardSize(int size, String name) {
        this.value = size;
        this.name = name;
    }
}