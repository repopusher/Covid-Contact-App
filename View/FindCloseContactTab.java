//Tobias lennon
//R00191512
//SDH2-B
package OOP_Project.View;

import OOP_Project.Control.ControlClass;
import OOP_Project.CloseContact;
import OOP_Project.Model.CloseContactComparator;
import OOP_Project.Contact;
import OOP_Project.Model.DateComparator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FindCloseContactTab  extends Tab {

    ArrayList<Contact> contactArrayList;
    ArrayList<CloseContact> closeContactArray;
    public FindCloseContactTab() throws IOException, ClassNotFoundException {
        //GUI Objects

        ControlClass controlClass = new ControlClass();
        ControlClass controller = new ControlClass();
        VBox vb = new VBox();
        Label lb1 = new Label("Find Close Contact");
        TextArea ta = new TextArea();
        Button btnFind = new Button("Find");
        Button btnRefresh = new Button("Refresh");
        ChoiceBox<Contact> cp1 = new ChoiceBox<>();
        CheckBox cb1 = new CheckBox("Name");
        CheckBox cb2 = new CheckBox("Date");
        Label lb2 = new Label("Sort by: ");

        contactArrayList = controller.load("contacts.txt");
        closeContactArray = controller.load("closeContacts.txt");

        //Adding contactArrayList to ChoiceBox
        cp1.getItems().addAll(contactArrayList);
        if(!contactArrayList.isEmpty()){
            cp1.setValue(contactArrayList.get(0));
        }
        ta.setPrefHeight(250);
        vb.getChildren().addAll(lb1, ta);
        vb.setAlignment(Pos.TOP_CENTER);
        vb.setPadding(new Insets(10));
        vb.setSpacing(8);
        HBox hb1 = new HBox(cp1, btnFind, btnRefresh, lb2, cb1, cb2);
        hb1.setPadding(new Insets(10,10,10,10));
        hb1.setSpacing(10);
        hb1.setAlignment(Pos.CENTER);
        vb.getChildren().add(hb1);

        //Find button will find selected contact's close contacts.
        btnFind.setOnAction(e -> {
            try {
                //Loads from file.
                closeContactArray = controlClass.load("closeContacts.txt");
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
            //Finds passed closeContact and returns matching.
            closeContactArray = controlClass.findCloseContact(closeContactArray, cp1.getValue());

            if (cb1.isSelected()){
                Collections.sort(closeContactArray, new CloseContactComparator());
            }
            if(cb2.isSelected()){
                Collections.sort(closeContactArray, new DateComparator());
            }

            ta.clear();
            //Prints out result to textArea
            for (CloseContact closeCon: closeContactArray){
                ta.appendText(closeCon.toString());
            }
        });

        //Refreshes the contacts shown in the choice boxes
        btnRefresh.setOnAction(e -> {
            cp1.getItems().clear();
            try {
                ArrayList<Contact> contactArray = controller.load("contacts.txt");
                cp1.getItems().addAll(contactArray);
                //Choice boxes will not show anything if contactArrayList is empty
                if(!contactArray.isEmpty()){
                    cp1.setValue(contactArray.get(0));
                }
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        });

        setText("Find Close Contact");
        setContent(vb);
    }
}
