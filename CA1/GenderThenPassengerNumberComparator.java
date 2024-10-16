package CA1;

import java.util.Comparator;

public class GenderThenPassengerNumberComparator implements Comparator<Passenger> {
    public int compare(Passenger p1, Passenger p2) {
        if (p1.getGender().equals(p2.getGender())) {
            return Integer.compare(Integer.parseInt(p1.getPassengerId()), Integer.parseInt(p2.getPassengerId()));
        } else {
            return p1.getGender().compareTo(p2.getGender());
        }
        
    }

}
