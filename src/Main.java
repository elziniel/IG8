package project;

import project.controller.AbstractController;
import project.controller.Controller;
import project.model.AbstractModel;
import project.model.Model;
import project.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        AbstractModel model = new Model(primaryStage);
        AbstractController controller = new Controller(model);
        View view = new View(controller);
        model.addObserver(view);

        primaryStage.setTitle("Projet Interface Graphique");
        Scene scene = new Scene(view, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
