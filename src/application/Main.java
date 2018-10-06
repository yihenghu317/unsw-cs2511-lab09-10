package application;
	
import javafx.application.Application;
import application.MoviesScreen;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
    public void start(Stage primaryStage) throws Exception {
        // set the stage height to be 400
        primaryStage.setHeight(700);
        // set the stage width to be 600
        primaryStage.setWidth(900);

        MoviesScreen moviesScreen = new MoviesScreen(primaryStage);
        moviesScreen.start();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
