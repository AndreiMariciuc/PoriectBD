import constante.Ecran;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//Andrei intrat!
//Aless intrat!
//Tudorel
public class Aplicatie extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("autentificare/atutentificare.fxml"));
        primaryStage.setTitle("AGU");
        primaryStage.setScene(new Scene(root, Ecran.WIDTH, Ecran.HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("Tudorel a fost aici");
        System.out.println("Hello andrei");
        launch(args);
    }
}
