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
    public void press(int x, int y) {
        if (super.IsClicked(x, y))
            this.setLineColor(new Color(255, 255, 255));
    }
    public void release(int x, int y) {
        this.setLineColor(Color.LIGHT_GRAY);
    }

}
