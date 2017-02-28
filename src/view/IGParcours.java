package project.view;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Polygon;
import project.controller.AbstractController;
import project.listener.SelectionListener;

public class IGParcours extends GridPane {
    IGParcours(AbstractController c) {
        SelectionListener listener = new SelectionListener(c);
        ToggleGroup group = new ToggleGroup();
        Label algo = new Label("Algorithm");
        RadioButton prim = new RadioButton("Prim");
        prim.setOnAction(listener);
        RadioButton pep = new RadioButton("Parcours prof.");
        pep.setOnAction(listener);
        group.getToggles().addAll(prim, pep);
        HBox hBox = new HBox();
        Button retour = new Button("<");
        retour.setOnAction(listener);
        Button pause = new Button("||");
        pause.setOnAction(listener);
        Button play = new Button("►");
        play.setOnAction(listener);
        Button avance = new Button(">");
        avance.setOnAction(listener);
        Button launch = new Button("Lancer");
        hBox.getChildren().addAll(retour, pause, play, avance);

        add(algo, 0, 0);
        add(prim, 1, 0);
        add(pep, 2, 0);
        add(hBox, 0, 1);
    }
}
