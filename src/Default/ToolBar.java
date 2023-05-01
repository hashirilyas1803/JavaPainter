package Default;

import Buttons.*;
import Buttons.Button;
import Interfaces.DrawButtons;
import Interfaces.Interactibility;
import Windows.PopUpWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToolBar extends Rectangle implements Interactibility, DrawButtons {
    Board b;
    ArrayList<Button> buttons;
    ColorButton selected;
    PopUpWindow gradient;
    public ToolBar(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke, Board b) {
        super(x, y, width, height, rectColor, lineColor, stroke);
        this.b = b;
        buttons = new ArrayList<>();
        int h = b.getheight();
        int w = b.getwidth();
        gradient = new PopUpWindow(w / 4, h / 4, w / 2, h / 2, Color.WHITE, Color.LIGHT_GRAY, "Gradient");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        gradient.paint(g);
        if (!buttons.isEmpty()) {
            for (Button button : buttons) {
                if (button instanceof ColorButton || button instanceof PaletteButton)
                    button.paint(g);
                else
                    drawButton(button, g, b);
            }
        }
    }


    private void drawButton(Button button, Graphics g, Board b) {
        g.drawImage(button.GetImage(), button.x, button.y, b);
    }

    @Override
    public void click(int x, int y) {
        for (int i = 0; i < buttons.size(); i++) {
            if(buttons.get(i).IsClicked(x, y)) {
                if (buttons.get(i) instanceof ToggleButton) {
                    buttons.get(i).click(x, y);
                    if (buttons.get(i) instanceof ColorButton)
                        selected = (ColorButton) buttons.get(i);
                    for (int j = 0; j < buttons.size(); j++) {
                        if (i != j && buttons.get(j) instanceof ToggleButton) ((ToggleButton) buttons.get(j)).Unclick(x, y);
                    }
                }
            }
        }
    }

    public void press(int x, int y) {
        for (Button button : buttons) {
            if (button instanceof PaletteButton && button.IsClicked(x, y)) {
                button.press(x, y);
                if (selected != null)
                    selected.setColorRect(button.getRectColor());
            }
        }
    }
    public void release(int x, int y) {
        for (Button button : buttons) {
            if (button instanceof PaletteButton && button.IsClicked(x, y)) {
                button.release(x, y);
            }
        }
    }

    @Override
    public boolean IsClicked(int x, int y) {
        for (Button button  : buttons) {
            if (button.IsClicked(x, y))
                return true;
        }
        return false;
    }

    public void addShapes(int x, int y, int size) {
        buttons.add(new ToggleButton(x, y, size, size, new ImageIcon("src/resources/right_triangle_button.png").getImage(), new ImageIcon("src/resources/right_triangle_button_pressed.png").getImage()));
        buttons.add(new ToggleButton(x + b.getHEIGHT(), y, size, size, new ImageIcon("src/resources/equilateral_triangle_button.png").getImage(), new ImageIcon("src/resources/equilateral_triangle_button_pressed.png").getImage()));
        buttons.add(new ToggleButton(x + (b.getHEIGHT() * 2), y, size, size, new ImageIcon("src/resources/rectangle_button.png").getImage(), new ImageIcon("src/resources/rectangle_button_pressed.png").getImage()));
        buttons.add(new ToggleButton(x, y + b.getHEIGHT(), size, size, new ImageIcon("src/resources/circle_button.png").getImage(), new ImageIcon("src/resources/circle_button_pressed.png").getImage()));
        buttons.add(new ToggleButton(x + b.getHEIGHT(), y + b.getHEIGHT(), size, size, new ImageIcon("src/resources/hexagon_button.png").getImage(), new ImageIcon("src/resources/hexagon_button_pressed.png").getImage()));
        buttons.add(new ToggleButton(x + (b.getHEIGHT() * 2), y + b.getHEIGHT(), size, size, new ImageIcon("src/resources/pentagram_button.png").getImage(), new ImageIcon("src/resources/pentagram_button_pressed.png").getImage()));
    }

    public void addPaletteButtons(int x, int y, int size, int num) {
        // Populate array of colors to iteratively assign to palette buttons
        Color[] colors = {Color.BLACK, Color.gray, Color.RED, Color.ORANGE, Color.PINK, Color.WHITE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE};

        // Iterative assign an a grid of colors
        int j = num / 2;
        for (int i = 0; i < num; i++) {
            buttons.add(new PaletteButton(x + ((i % j) * size), y + ((i / j) * size), size, colors[i]));
        }
    }
}
