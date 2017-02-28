package project.listener;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import project.controller.AbstractController;

public class MouseDraggedListener extends AbstractListener implements EventHandler<MouseEvent> {

    public MouseDraggedListener(AbstractController c) {
        super(c);
    }

    @Override
    public void handle(MouseEvent event) {
        int x = (int) event.getX(), y = (int) event.getY();
        controller.setDragAction(x, y);
    }

}
