package OOP_Project.View;

import OOP_Project.Contact;
import OOP_Project.Control.ControlClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class AddContactTab extends Tab {

    private GridPane gridLayout;
    private VBox mainLayout, vb1;
    private HBox row0, row1, row2, titleRow;
    private BorderPane borderLayout;
    private ControlClass controller;
    private static Button btnAdd, btnRemove, btnList, btnExit;
    private static Label labelFName, labelLName, labelID, labelPhoneNo, labelTitle;
    private TextField tfFName, tfLName, tfID, tfPhoneNo;
    private ArrayList<Contact> contactArray;
    private ListView<Contact> contactListView;
    private Menu fileMenu;
    private MenuItem menuLoad, menuSave;
    private MenuBar menuBar;

    public AddContactTab() throws IOException, ClassNotFoundException {
        contactArray =  new ArrayList<>();      //Hold contact objects
        contactListView =   new ListView<>();       //Holds objects to be shown in the ListView
        controller =        new ControlClass();          //OOP_Project.Control object to handles events
        contactListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);   //Allows the selection of 1 or more items on ListView

        //Button objects
        btnRemove = new Button("Remove");
        btnList =   new Button("List");
        btnExit =   new Button("Exit");
        btnAdd =    new Button("Add");

        //Label objects
        labelPhoneNo = new Label("Phone Number:");
        labelFName =   new Label("First Name:");
        labelLName =   new Label("Last Name:");
        labelTitle =   new Label("Contacts");
        labelID =      new Label("ID:");

        //TextField objects
        tfPhoneNo = new TextField();
        tfFName =   new TextField();
        tfLName =   new TextField();
        tfID =      new TextField();

        //Layout objects
        gridLayout = new GridPane();
        titleRow =   new HBox(labelTitle);
        row0 =       new HBox(btnAdd, btnRemove, btnList);
        row1 =       new HBox(contactListView);
        row2 =       new HBox(btnExit);
        vb1 =        new VBox(row0,row1,row2);
        gridLayout.add(labelFName, 0,2);
        gridLayout.add(tfFName, 1,2);
        gridLayout.add(labelLName, 0,3);
        gridLayout.add(tfLName, 1,3);
        gridLayout.add(labelID, 0,4);
        gridLayout.add(tfID, 1,4);
        gridLayout.add(labelPhoneNo, 0,5);
        gridLayout.add(tfPhoneNo, 1,5);

        //Positioning of layout objects
        contactListView.setMinWidth(680);
        row1.setAlignment(Pos.CENTER);
        row2.setAlignment(Pos.BOTTOM_RIGHT);
        row0.setSpacing(10);
        titleRow.setAlignment(Pos.TOP_CENTER);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        vb1.setSpacing(10);
        vb1.setPadding(new Insets(10));

        //FileMenu objects
        fileMenu = new Menu("File");
        menuLoad = new MenuItem("Load");
        menuSave = new MenuItem("Save");
        fileMenu.getItems().add(menuLoad);
        fileMenu.getItems().add(menuSave);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);

        mainLayout = new VBox(titleRow, gridLayout, vb1);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setSpacing(8);

        //Load from file on startup
        contactArray = controller.load("contacts.txt");
        contactListView.getItems().clear();                     //Clears ListView cache.
        contactListView.getItems().addAll(contactArray);         //Adds all items in contactArraylist to ListView.

        //BorderPane layout
        borderLayout = new BorderPane();
        borderLayout.setTop(menuBar);
        borderLayout.setCenter(mainLayout);

        //Button events
        //Add event
        btnAdd.setOnAction(e -> {
            boolean uniqueContact;
            Contact contactPlaceholder = new Contact(tfFName.getText(), tfLName.getText(), tfID.getText(), tfPhoneNo.getText());

            uniqueContact = controller.validateContact(contactArray, contactPlaceholder);

            if (uniqueContact){
                contactArray.add(contactPlaceholder);
                System.out.println(contactArray);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);   //Alert object to be shown to show the user that the ID entered is not unique
                alert.setContentText("Please re-enter the ID.");
                alert.setTitle("Invalid ID");
                alert.setHeaderText("⚠ ID ENTERED IS NOT UNIQUE ⚠");
                alert.showAndWait();
            }
        });
        //List event
        btnList.setOnAction(e -> {
            contactListView.getItems().clear();                     //Clears ListView cache.
            contactListView.getItems().addAll(contactArray);         //Adds all items in contactArraylist to ListView.
        });
        //Remove event
        btnRemove.setOnAction(e -> controller.removeContact(contactListView, contactArray));

        //Exit event uses Alert object, saves and exits on yes, exit on no
        btnExit.setOnAction(e -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Would you like to save before exiting?", yes, no);
            alert.setTitle("Exit");

            Optional<ButtonType> res = alert.showAndWait();
            // alert is exited, no button has been pressed.
            if(res.get() == yes){
                controller.save(contactArray, "contacts.txt");
                controller.closeWindow();
            }
            else if(res.get() == no){
                controller.closeWindow();
            }
            alert.showAndWait();
        });

        //Saves contact array to contact file.
        menuSave.setOnAction(e -> controller.save(contactArray, "contacts.txt"));

        //Gets the contactArrayList from file using loadContact(), then lists contactArraylist
        menuLoad.setOnAction(e -> {
            contactArray = null;
            try {
                contactArray = controller.load("contacts.txt");
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
            contactListView.getItems().clear();                     //Clears ListView cache.
            contactListView.getItems().addAll(contactArray);         //Adds all items in contactArraylist to ListView.
        });

        setText("Add Contact");
        setContent(borderLayout);
    }
}
