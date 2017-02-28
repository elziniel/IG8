package project.listener;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import project.controller.AbstractController;

public class MenuListener extends AbstractListener implements EventHandler<ActionEvent> {

    public MenuListener(AbstractController c) {
        super(c);
    }

    @Override
    public void handle(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        if (menuItem.getText().equals("Open...")) {
            controller.setOpenAction();
        }
        else if (menuItem.getText().equals("Save as...")) {
            controller.setSaveAction();
        }
        else if (menuItem.getText().equals("Launch")) {
            controller.setLaunchAction();
        }
    }
}
