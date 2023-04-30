package Default;

import Buttons.MenuButton;
import Buttons.Button;
import Interfaces.DrawButtons;
import Interfaces.Interactibility;

import javax.swing.*;
import java.awt.*;

public class Header extends Rectangle implements Interactibility, DrawButtons {
    // Attributes
    Board b;
    MenuButton file;
    MenuButton edit;
    DropDownMenu fileMenu;
    DropDownMenu editMenu;

    // Methods
    public Header(int x, int y, int width, int height, Color rectColor, Color lineColor, int stroke, Board b) {
        super(x, y, width, height, rectColor, lineColor, stroke);
        this. b = b;
        file = new MenuButton(0, 0, 64, height, new ImageIcon("src/resources/file_button.png").getImage(), new ImageIcon("src/resources/file_button_pressed.png").getImage());
        edit = new MenuButton(64, 0, 64, height, new ImageIcon("src/resources/edit_button.png").getImage(), new ImageIcon("src/resources/edit_button_pressed.png").getImage());
        fileMenu = new DropDownMenu(file.x, file.y + height, 128, height * 3, Color.GRAY, Color.BLACK, 0);
        editMenu = new DropDownMenu(edit.x, edit.y + height, width / 8, height * 2, Color.GRAY, Color.BLACK, 0);
        setUp();
    }

    private void setUp() {
        // Set up the dropdown menu for the file button
        fileMenu.addButton(file.x, file.y + height, 128, height, new ImageIcon("src/resources/new_button.png").getImage(), new ImageIcon("src/resources/new_button_pressed.png").getImage());
        fileMenu.addButton(file.x, file.y + (2 * height), 128, height, new ImageIcon("src/resources/open_button.png").getImage(), new ImageIcon("src/resources/open_button.png").getImage());
        fileMenu.addButton(file.x, file.y + (3 * height), 128, height, new ImageIcon("src/resources/save_button.png").getImage(), new ImageIcon("src/resources/save_button.png").getImage());

        // Set up the dropdown menu for the edit button
        editMenu.addButton(edit.x, edit.y + height, 128, height, new ImageIcon("src/resources/undo_button.png").getImage(), new ImageIcon("src/resources/undo_button.png").getImage());
        editMenu.addButton(edit.x, edit.y + (2 * height), 128, height, new ImageIcon("src/resources/redo_button.png").getImage(), new ImageIcon("src/resources/redo_button.png").getImage());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawButton(file, g, b);
        drawButton(edit, g, b);
        if (file.IsPressed()) {
            fileMenu.paint(g);
            for (MenuButton button : fileMenu.getButtons()) {
                drawButton(button, g, b);
            }
        }
        if (edit.IsPressed()) {
            editMenu.paint(g);
            for (MenuButton button : editMenu.getButtons()) {
                drawButton(button, g, b);
            }
        }
    }

    private void drawButton(Button button, Graphics g, Board b) {
        g.drawImage(button.GetImage(), button.x, button.y, b);
    }

    @Override
    public void click(int x, int y) {
        file.click(x, y);
        edit.click(x, y);
    }

    public boolean IsClicked(int x, int y) {
        if (file.IsClicked(x, y) || edit.IsClicked(x, y))
            return true;
        return false;
    }
}
