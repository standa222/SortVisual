package cz.cvut.fel.sortvisual;

import cz.cvut.fel.sortvisual.controller.Controller;
import cz.cvut.fel.sortvisual.model.Model;
import cz.cvut.fel.sortvisual.utils.AlgorithmFactory;
import cz.cvut.fel.sortvisual.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AlgorithmFactory factory = new AlgorithmFactory();

        Model model  = new Model();
        Controller controller = new Controller(model, factory.createAll());
        View view = new View(stage, controller);
        controller.setView(view);

        stage.setTitle("Sorting Algorithm Visualizer");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
