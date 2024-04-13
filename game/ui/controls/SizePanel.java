package game.ui.controls;

import game.core.BoardSize;

import javax.swing.*;

public class SizePanel extends JPanel {
    public SizePanel(SizeChangeInterface sizeChangeInterface) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ButtonGroup group = new ButtonGroup();

        JRadioButton smallRadioButton = new JRadioButton(BoardSize.SMALL.name);
        smallRadioButton.setSelected(true);
        smallRadioButton.addActionListener(e -> sizeChangeInterface.onSmallSizeClick());

        JRadioButton mediumRadioButton = new JRadioButton(BoardSize.MEDIUM.name);
        mediumRadioButton.addActionListener(e -> sizeChangeInterface.onMediumSizeClick());

        JRadioButton bigRadioButton = new JRadioButton(BoardSize.BIG.name);
        bigRadioButton.addActionListener(e -> sizeChangeInterface.onBigSizeClick());

        group.add(smallRadioButton);
        group.add(mediumRadioButton);
        group.add(bigRadioButton);

        add(smallRadioButton);
        add(mediumRadioButton);
        add(bigRadioButton);
    }

    public interface SizeChangeInterface {
        void onSmallSizeClick();

        void onMediumSizeClick();

        void onBigSizeClick();
    }
}
