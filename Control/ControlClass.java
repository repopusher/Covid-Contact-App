//Tobias lennon
//R00191512
//SDH2-B
package OOP_Project.Control;

import OOP_Project.CloseContact;
import OOP_Project.Contact;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.io.*;
import java.util.ArrayList;

public class ControlClass implements Serializable{

    //Method validates Contact object is not already in array and returns boolean.
    public boolean validateContact(ArrayList<Contact> contactArrayList, Contact contactPlaceholder){
        boolean uniqueContact = true;
        if (!contactArrayList.isEmpty()) {                      //If contactArrayList is not empty
            for (Contact contact : contactArrayList) {
                if (contact.getUniqueID().contains(contactPlaceholder.getUniqueID())) {
                    uniqueContact = false;                      //If contact's ID is found then it is not unique and is set to false.
                    break;                                      //Break if condition is met
                }
            }
        }
        return uniqueContact;                                   //Return uniqueContact
    }

    //Method will remove selected contacts from the ListView and in tern the contact ArrayList
    public void removeContact(ListView<Contact> lv1, ArrayList<Contact> contactArrayList) {
        ObservableList<Contact> selectedContact;
        selectedContact = lv1.getSelectionModel().getSelectedItems();      //Returns selected contact in ListView

        ArrayList<Contact> IDs = new ArrayList<>(selectedContact);          //Holds IDs of selected objects to be deleted in the contactArraylist
        System.out.println(IDs + " <-- These IDs will be removed from contactList and ListView");

        for (Contact con : IDs) {
            contactArrayList.removeIf(con2 -> con.getUniqueID().equals(con2.getUniqueID()));       //Remove from contactArrayList if ID matches
        }
        lv1.getItems().removeAll(selectedContact);      //Removes selectedContact from ListView
    }

    //Method exits program
    public void closeWindow() {
        System.exit(1);
    }

    //Method validates there is no duplicate entries in the closeContactArray
    public boolean validateCloseContact(CloseContact closeContact, ArrayList<CloseContact> closeContactArray){
        if(!closeContactArray.isEmpty()){
            for(CloseContact closeCon: closeContactArray){
                if(((closeCon.getCon1().getUniqueID().equals(closeContact.getCon1().getUniqueID())) && (closeCon.getCon2().getUniqueID().equals(closeContact.getCon2().getUniqueID()))) || ((closeCon.getCon2().getUniqueID().equals(closeContact.getCon1().getUniqueID())) && (closeCon.getCon1().getUniqueID().equals(closeContact.getCon2().getUniqueID())))){
                    System.out.println("Close contact already recorded");
                    return false;
                }
            }
        }
        return true;
    }

    //Method saves an array to a file.
    public void save(ArrayList array, String fileName){
        try
        {
            FileOutputStream fos= new FileOutputStream("C:\\Users\\tobia\\IdeaProjects\\lab01\\src\\OOP_Project\\Model\\" + fileName);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(array);
            oos.close();
            fos.close();
        } catch (Exception ex) {
            System.out.println("could not save");
            ex.printStackTrace();
        }
    }

    //Method loads an array from a file and returns it.
    public ArrayList load(String fileName) throws IOException, ClassNotFoundException {
        ArrayList closeContactArrayList;

        FileInputStream fis = new FileInputStream("C:\\Users\\tobia\\IdeaProjects\\lab01\\src\\OOP_Project\\Model\\" + fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        closeContactArrayList = (ArrayList) ois.readObject();
        ois.close();
        fis.close();

        return closeContactArrayList;
    }

    //Method finds passed contact in the array of loaded close contacts.
    public ArrayList<CloseContact> findCloseContact(ArrayList<CloseContact> closeContactArrayList, Contact contact){
        ArrayList<CloseContact> res = new ArrayList<>();
        for (CloseContact con: closeContactArrayList){      //Iterating over array
            if(con.getCon1().getUniqueID().equals(contact.getUniqueID()) || con.getCon2().getUniqueID().equals(contact.getUniqueID())){
                res.add(con);
            }
        }
        return res;
    }
}
