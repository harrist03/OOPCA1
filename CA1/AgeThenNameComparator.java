package CA1;

import java.util.Comparator;

public class AgeThenNameComparator implements Comparator<Passenger> {
    public int compare(Passenger p1, Passenger p2) {
        if (p1.getAge() == p2.getAge()) {
            return p1.getName().compareTo(p2.getName());
        } else {
            return Integer.compare(p1.getAge(), p2.getAge());
        }
        
    }

}
