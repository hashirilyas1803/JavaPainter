package Default;

import Buttons.Button;
import Buttons.ColorButton;
import Buttons.ToggleButton;
import Interfaces.DrawButtons;
import Interfaces.Interactibility;

import java.awt.*;
import java.util.ArrayList;

public class ToolBar extends Rectangle implements Interactibility, DrawButtons {
    Board b;
    ArrayList<Button> buttons;
    public ToolBar(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke, Board b) {
        super(x, y, width, height, rectColor, lineColor, stroke);
        this.b = b;
        buttons = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!buttons.isEmpty()) {
            for (Button button : buttons) {
                if (button instanceof ColorButton)
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
                    for (int j = 0; j < buttons.size(); j++) {
                        if (i != j) ((ToggleButton) buttons.get(j)).Unclick(x, y);
                    }
                }
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
}
