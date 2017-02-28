package project.listener;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import project.controller.AbstractController;

public class MouseReleasedListener extends AbstractListener implements EventHandler<MouseEvent> {

    public MouseReleasedListener(AbstractController c) {
        super(c);
    }

    @Override
    public void handle(MouseEvent event) {
        int x = (int) event.getX(), y = (int) event.getY();
        if (event.getButton().toString().equals("SECONDARY")) {
            controller.removeAction();
        }
        else {
            controller.setReleaseAction(x, y);
        }
    }

}
