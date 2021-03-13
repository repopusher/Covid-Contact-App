//Tobias lennon
//R00191512
//SDH2-B
package OOP_Project;

import java.io.Serializable;

public class ModelClass implements Serializable{
    public static class Contact implements Serializable {
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
    public static class CloseContact implements Serializable {

        private Contact con1;
        private Contact con2;
        private String date;
        private String time;

        public CloseContact(Contact con1, Contact con2, String date, String time){
            this.con1 = con1;
            this.con2 = con2;
            this.date = date;
            this.time = time;
        }

        public String toString(){
            return this.con1.toString() + " <--------> " + this.con2.toString() + "\nDATE: " + this.date + "\nTIME: " + this.time + "\n";
        }

        public Contact getCon1() {
            return con1;
        }

        public void setCon1(Contact con1) {
            this.con1 = con1;
        }

        public Contact getCon2() {
            return con2;
        }

        public void setCon2(Contact con2) {
            this.con2 = con2;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
