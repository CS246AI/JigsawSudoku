package game.ui.controls;

import game.core.BoardSize;

import javax.swing.*;
import java.awt.*;

public class SizePanel extends JPanel {
    public SizePanel(BoardSize boardSize, SizeChangeInterface sizeChangeInterface) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ButtonGroup group = new ButtonGroup();

        JRadioButton smallRadioButton = new JRadioButton(BoardSize.SMALL.name);
        smallRadioButton.addActionListener(e -> sizeChangeInterface.onSmallSizeClick());

        JRadioButton mediumRadioButton = new JRadioButton(BoardSize.MEDIUM.name);
        mediumRadioButton.addActionListener(e -> sizeChangeInterface.onMediumSizeClick());

        JRadioButton bigRadioButton = new JRadioButton(BoardSize.BIG.name);
        bigRadioButton.addActionListener(e -> sizeChangeInterface.onBigSizeClick());

        switch (boardSize) {
            case SMALL -> smallRadioButton.setSelected(true);
            case MEDIUM -> mediumRadioButton.setSelected(true);
            case BIG -> bigRadioButton.setSelected(true);
        }

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
