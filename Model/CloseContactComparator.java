package OOP_Project.Model;

import OOP_Project.CloseContact;

import java.util.Comparator;

public class CloseContactComparator implements Comparator<CloseContact> {
    public int compare(CloseContact con1, CloseContact con2){
        String c1 = con1.getCon1().getfName();
        String c2 = con2.getCon1().getfName();
        return c1.compareTo(c2);
    }
}
