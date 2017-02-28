package project.listener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import project.controller.AbstractController;

public class SelectionListener extends AbstractListener implements EventHandler<ActionEvent> {

    public SelectionListener(AbstractController c) {
        super(c);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            if (button.getGraphic() instanceof Rectangle) {
                controller.setAction("r");
            }
            else if (button.getGraphic() instanceof Circle) {
                controller.setAction("c");
            }
            else if (button.getGraphic() instanceof Polygon) {
                controller.setAction("t");
            }
            else if (button.getGraphic() instanceof Line){
                controller.setAction("e");
            }
            else if (button.getText().equals("<")){
                controller.setParcours("retour");
            }
            else if (button.getText().equals("►")){
            	controller.setLaunchAction();
            }
            else if (button.getText().equals("||")){
                controller.setParcours("pause");
            }
            else if (button.getText().equals(">")){
                controller.setParcours("avance");
            }
        }
        else if (event.getSource() instanceof TextField) {
            TextField tf = (TextField) event.getSource();
            if (tf.getId().equals("name")){
                controller.setAction("n", tf.getText());
            }
            else if (tf.getId().equals("weight")) {
                controller.setAction("p", tf.getText());
            }
        }
        else if (event.getSource() instanceof ColorPicker) {
            ColorPicker cp = (ColorPicker) event.getSource();
            controller.setAction("c", cp.getValue().toString());
        }
        else if (event.getSource() instanceof RadioButton) {

        }
    }
}
