//Tobias lennon
//R00191512
//SDH2-B
package OOP_Project;

import OOP_Project.View.AddContactTab;
import OOP_Project.View.FindCloseContactTab;
import OOP_Project.View.RecordCloseContactTab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.Serializable;

public class Main extends Application implements Serializable {

    private TabPane tp1;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Instantiating
        primaryStage.setTitle("Covid Application");

        tp1 = new TabPane();
        tp1.getTabs().addAll(new AddContactTab(), new RecordCloseContactTab(), new FindCloseContactTab());

        primaryStage.setScene(new Scene(tp1, 720, 480));
        primaryStage.show();
    }
}