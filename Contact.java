//Tobias lennon
//R00191512
//SDH2-B
package OOP_Project;

import java.io.Serializable;

public class Contact implements Serializable {
    //Initialise
    private String fName;
    private String lName;
    private String uniqueID;
    private String phoneNo;
    //Constructor
    public Contact(String fName, String lName, String uniqueID, String phoneNo){
        this.fName = fName;
        this.lName = lName;
        this.uniqueID = uniqueID;
        this.phoneNo = phoneNo;
    }

    public String toString(){
        return this.fName + " " + this.lName + " " + this.uniqueID + " " + this.phoneNo;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
