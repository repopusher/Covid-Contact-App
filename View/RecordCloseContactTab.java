//Tobias lennon
//R00191512
//SDH2-B
package OOP_Project.View;

import OOP_Project.CloseContact;
import OOP_Project.Contact;
import OOP_Project.Control.ControlClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class RecordCloseContactTab extends Tab implements Serializable {

    ArrayList<CloseContact> closeContactArray = new ArrayList<>();
    ArrayList<Contact> contactArrayList;

    public RecordCloseContactTab() throws IOException, ClassNotFoundException {

        ControlClass controller = new ControlClass();

        contactArrayList = controller.load("contacts.txt");
        closeContactArray = controller.load("closeContacts.txt");

        //GUI Objects
        BorderPane bp = new BorderPane();
        VBox vb = new VBox();
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        HBox hb3 = new HBox();
        HBox hb4 = new HBox();
        HBox hb5 = new HBox();
        hb2.setPadding(new Insets(45, 10, 25,10));
        hb2.setSpacing(10);
        vb.setPadding(new Insets(10));
        vb.setSpacing(8);

        Label l1 = new Label("Record Close Contact");
        Label l2 = new Label("\t\t<---------------->\t\t");
        Label l3 = new Label("Date of Contact: ");
        Label l4 = new Label("Time of Contact: ");

        ChoiceBox<Contact> cp1 = new ChoiceBox<>();
        ChoiceBox<Contact> cp2 = new ChoiceBox<>();

        Button btnRecord = new Button("Record");
        Button btnRefresh = new Button("Refresh");
        TextField tfTime = new TextField("00:00");
        DatePicker dp = new DatePicker();

        //Adding contact array into choice boxes
        cp1.getItems().addAll(contactArrayList);
        cp2.getItems().addAll(contactArrayList);

        if (!contactArrayList.isEmpty()){
            cp1.setValue(contactArrayList.get(0));
            cp2.setValue(contactArrayList.get(0));
        }

        hb1.getChildren().add(l1);
        hb1.setAlignment(Pos.TOP_CENTER);
        l2.setAlignment(Pos.CENTER);
        l2.setPadding(new Insets(0,20,0,20));
        hb2.getChildren().addAll(cp1, l2, cp2);
        hb2.setAlignment(Pos.CENTER);
        hb3.getChildren().addAll(l3, dp);
        hb4.getChildren().addAll(l4, tfTime);
        hb3.setPadding(new Insets(0,10,5,25));
        hb4.setPadding(new Insets(0,10,5,25));
        hb5.getChildren().addAll(btnRefresh, btnRecord);
        hb5.setAlignment(Pos.CENTER);
        hb5.setPadding(new Insets(10,10,10,10));
        hb5.setSpacing(10);
        vb.getChildren().addAll(hb1, hb2, hb3, hb4, hb5);
        bp.setCenter(vb);


        btnRecord.setOnAction(e -> {

            boolean uniqueCloseContact;

            //Shows Alerts if the 2 choice boxes are the same.
            if(cp1.getValue().equals(cp2.getValue())){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Contacts are not unique");   //Alert object to be shown
                alert.setTitle("Warning");
                alert.setHeaderText("⚠ Please make contacts unique ⚠");
                alert.showAndWait();
            }
            //Shows Alert if date is null
            else if (dp.getValue() == null){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Date is null");   //Alert object to be shown
                alert.setTitle("Warning");
                alert.setHeaderText("⚠ Please enter a date ⚠");
                alert.showAndWait();
            }
            else{
                //Else, instantiates a contact and validates that it is not already in the array.
                CloseContact closeContactPlaceholder = new CloseContact(cp1.getValue(), cp2.getValue(), dp.getValue().toString(), tfTime.getText());   //instantiating object with GUI fields

                uniqueCloseContact = controller.validateCloseContact(closeContactPlaceholder, closeContactArray);     //Validates the current closeContacts

                if (uniqueCloseContact == true){
                    closeContactArray.add(closeContactPlaceholder);
                    controller.save(closeContactArray, "closeContacts.txt");
                }
                else{
                    //Alert shows if contact already in array
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Close Contact Duplicate");
                    alert.setTitle("Warning");
                    alert.setHeaderText("Close contact already recorded");
                    alert.showAndWait();
                }
            }
        });

        //Refreshes the contacts shown in the choice boxes
        btnRefresh.setOnAction(e -> {
            cp1.getItems().clear();
            cp2.getItems().clear();
            try {
                ArrayList<Contact> contactArray = controller.load("contacts.txt");
                cp1.getItems().addAll(contactArray);
                cp2.getItems().addAll(contactArray);
                //Choice boxes will not show anything if contactArrayList is empty
                if(!contactArray.isEmpty()){
                    cp1.setValue(contactArray.get(0));
                    cp2.setValue(contactArray.get(0));
                }

            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        });

        setText("Record Close Contact");
        setContent(bp);
    }
}
