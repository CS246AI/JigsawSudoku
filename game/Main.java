package game;

import game.core.Board;
import game.ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        SwingUtilities.invokeLater(() -> new MainFrame(board));
    }
}
