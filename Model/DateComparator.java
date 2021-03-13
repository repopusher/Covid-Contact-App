package OOP_Project.Model;

import OOP_Project.CloseContact;

import java.util.Comparator;

public class DateComparator implements Comparator<CloseContact> {
    public int compare(CloseContact con1, CloseContact con2){
        String c1 = con1.getDate();
        String c2 = con2.getDate();
        return c1.compareTo(c2);
    }
}
