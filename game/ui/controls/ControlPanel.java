package game.ui.controls;

import game.core.BoardSize;
import game.core.GameDifficulty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ControlPanel extends JPanel {

    public ControlPanel(
            BoardSize boardSize,
            SizePanel.SizeChangeInterface sizeChangeInterface,
            GameDifficulty gameDifficulty,
            DifficultyPanel.DifficultyChangeInterface difficultyChangeInterface
    ) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton geneticAlgorithm = new JButton("Genetic Algorithm");
        geneticAlgorithm.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton simulatedAnnealing = new JButton("Simulated Annealing");
        simulatedAnnealing.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton cspMRV = new JButton("CSP MRV");
        cspMRV.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton cspLCV = new JButton("CSP LCV");
        cspLCV.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        JButton forwardChecking = new JButton("Forward Checking");
        forwardChecking.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        SizePanel sizePanel = new SizePanel(boardSize, sizeChangeInterface);
        sizePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        DifficultyPanel difficultyPanel = new DifficultyPanel(gameDifficulty, difficultyChangeInterface);
        difficultyPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(geneticAlgorithm);
        add(simulatedAnnealing);
        add(cspMRV);
        add(cspLCV);
        add(forwardChecking);
        add(Box.createVerticalGlue());
        add(sizePanel);
        add(Box.createVerticalGlue());
        add(difficultyPanel);
        add(Box.createVerticalGlue());
    }
}
