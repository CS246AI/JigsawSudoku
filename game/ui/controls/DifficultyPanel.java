package game.ui.controls;

import game.core.GameDifficulty;

import javax.swing.*;

public class DifficultyPanel extends JPanel {
    public DifficultyPanel(DifficultyChangeInterface difficultyChangeInterface) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ButtonGroup group = new ButtonGroup();

        JRadioButton easyRadioButton = new JRadioButton(GameDifficulty.EASY.name);
        easyRadioButton.setSelected(true);
        easyRadioButton.addActionListener(e -> difficultyChangeInterface.onEasyClick());

        JRadioButton mediumRadioButton = new JRadioButton(GameDifficulty.MEDIUM.name);
        mediumRadioButton.addActionListener(e -> difficultyChangeInterface.onMediumClick());

        JRadioButton hardRadioButton = new JRadioButton(GameDifficulty.HARD.name);
        hardRadioButton.addActionListener(e -> difficultyChangeInterface.onHardClick());

        group.add(easyRadioButton);
        group.add(mediumRadioButton);
        group.add(hardRadioButton);

        add(easyRadioButton);
        add(mediumRadioButton);
        add(hardRadioButton);
    }

    public interface DifficultyChangeInterface {
        void onEasyClick();

        void onMediumClick();

        void onHardClick();
    }
}
