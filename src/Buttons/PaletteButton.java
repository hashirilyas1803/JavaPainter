package Buttons;

import Interfaces.Interactibility;

import java.awt.*;

public class PaletteButton extends ActiveButton implements Interactibility {

    public PaletteButton(int x, int y, int size, Color color) {
        super(x, y, size, size);
        this.setRectColor(color);
    }
}
