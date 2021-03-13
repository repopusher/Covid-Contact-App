//Tobias lennon
//R00191512
//SDH2-B
package OOP_Project;

import java.io.Serializable;

public class CloseContact implements Serializable {
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
