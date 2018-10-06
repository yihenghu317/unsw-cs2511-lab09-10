package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MoviesScreen {

    private Stage s;
    private String title;
    private FXMLLoader fxmlLoader;
    
    public MoviesScreen(Stage s) {
        this.s = s;
        this.title = "Movie Rental";
        this.fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("application/Movie.fxml"));
    }
    
    public void start() {
        s.setTitle(title);
        fxmlLoader.setController(new MoviesController(s));
        try {
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root, 500, 300);
            s.setScene(sc);
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
