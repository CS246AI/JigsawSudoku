package game.ui.controls;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ControlPanel extends JPanel {

    public ControlPanel(SizePanel.SizeChangeInterface sizeChangeInterface, DifficultyPanel.DifficultyChangeInterface difficultyChangeInterface) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton geneticAlgorithm = new JButton("Genetic Algorithm");
        JButton simulatedAnnealing = new JButton("Simulated Annealing");
        JButton forwardChecking = new JButton("Forward Checking");

        add(Box.createVerticalGlue());
        add(geneticAlgorithm);
        add(simulatedAnnealing);
        add(forwardChecking);
        add(Box.createVerticalGlue());
        add(new SizePanel(sizeChangeInterface));
        add(Box.createVerticalGlue());
        add(new DifficultyPanel(difficultyChangeInterface));
        add(Box.createVerticalGlue());
    }
}
