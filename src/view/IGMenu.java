package project.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import project.controller.AbstractController;
import project.listener.MenuListener;

class IGMenu extends MenuBar {

    IGMenu(AbstractController c) {
        MenuListener listener = new MenuListener(c);
        Menu file = new Menu("File");
        MenuItem fileOpen = new MenuItem("Open...");
        fileOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        fileOpen.setOnAction(listener);
        MenuItem fileSave = new MenuItem("Save as...");
        fileSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        fileSave.setOnAction(listener);
        MenuItem fileLaunch = new MenuItem("Launch");
        fileLaunch.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));
        fileLaunch.setOnAction(listener);
        file.getItems().addAll(fileOpen, fileSave, fileLaunch);

        this.getMenus().add(file);
    }

}
