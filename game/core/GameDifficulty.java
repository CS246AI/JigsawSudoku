package game.core;

public enum GameDifficulty {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard");

    public final String name;

    GameDifficulty(String name) {
        this.name = name;
    }
}