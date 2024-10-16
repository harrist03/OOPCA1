package CA1;

import java.util.Comparator;

public class FareThenSurvivalComparator implements Comparator<Passenger>{
    public int compare(Passenger p1, Passenger p2) {
        if (p1.getFare() == p2.getFare()) {
            return Integer.compare(p1.getSurvived(), p2.getSurvived());
        } else {
            return Double.compare(p1.getFare(), p2.getFare());
        }
    }
}
