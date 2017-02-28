package project.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import project.controller.AbstractController;
import project.listener.SelectionListener;

class IGEdge extends GridPane {

    IGEdge(AbstractController c) {
        SelectionListener listener = new SelectionListener(c);

        HBox hBox = new HBox();

        Button nonOriented = new Button("", new Line(0, 0, 10, 10));
        nonOriented.setOnAction(listener);

        hBox.getChildren().add(nonOriented);

        Label weightLabel = new Label("Weight");
        TextField weight = new TextField();
        weight.setPrefWidth(100);
        weight.setId("weight");
        weight.setOnAction(listener);

        add(hBox, 0, 0);
        add(weightLabel, 0, 1);
        add(weight, 0, 2);
    }
}
