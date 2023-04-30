package Buttons;

import Interfaces.Interactibility;

import java.awt.*;

public class ActiveButton extends Button implements Interactibility {
    public ActiveButton(int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
        super(x, y, width, height, i_depressed, i_pressed);
    }

    public ActiveButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}
