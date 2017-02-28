package project.listener;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import project.controller.AbstractController;

public class MousePressedListener extends AbstractListener implements EventHandler<MouseEvent> {

    public MousePressedListener(AbstractController c) {
        super(c);
    }

    @Override
    public void handle(MouseEvent event) {
        int x = (int) event.getX(), y = (int) event.getY();
        controller.setPressAction(x, y);
    }
}
