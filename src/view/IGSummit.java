package project.view;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import project.controller.AbstractController;
import project.listener.SelectionListener;
import project.listener.SliderListener;

class IGSummit extends GridPane {
    private Label sizeLabel;
    private TextField name;
    private Slider size;
    private ColorPicker color;

    IGSummit(AbstractController c) {
        SelectionListener listener = new SelectionListener(c);

        HBox hBox = new HBox();

        Button rectangle = new Button("", new Rectangle(10, 10));
        rectangle.setOnAction(listener);
        Button circle = new Button("", new Circle(5));
        circle.setOnAction(listener);
        Button triangle = new Button("", new Polygon(5, 0, 0, 10, 10, 10));
        triangle.setOnAction(listener);

        hBox.getChildren().addAll(rectangle, circle, triangle);

        Label nameLabel = new Label("Name");
        name = new TextField();
        name.setPrefWidth(50);
        name.setId("name");
        name.setOnAction(listener);

        sizeLabel = new Label("Size : 10");
        size = new Slider(10, 100, 10);
        size.setPrefWidth(50);
        size.valueProperty().addListener(new SliderListener(c));

        Label colorLabel = new Label("Color");
        color = new ColorPicker();
        color.setValue(Color.BLACK);
        color.setPrefWidth(100);
        color.setId("color");
        color.setOnAction(listener);

        add(hBox, 0, 0);

        add(nameLabel, 0, 1);
        add(name, 0, 2);

        add(sizeLabel, 0, 3);
        add(size, 0, 4);

        add(colorLabel, 0, 5);
        add(color, 0, 6);
    }

    void setName(String n) {
        name.setText(n);
    }
    void setSizeLabel(String s) {
        sizeLabel.setText(s);
    }
    void setSize(int s) {
        size.setValue(s);
    }
    void setColor(String c) {
        color.setValue(Color.web(c));
    }
}
