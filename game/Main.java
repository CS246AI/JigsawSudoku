package game;

import game.core.Board;
import game.ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SwingUtilities.invokeLater(() -> new MainFrame(board));
    }
}
