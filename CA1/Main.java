package CA1;

// CA1
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        String fileName = "CA1/titanic-data-100.csv"; // file should be in the project folder (below pom.xml)

        ArrayList<Passenger> passengerList = new ArrayList<>();

        loadPassengerDataFromFile(passengerList, fileName);

        // displayAllPassengers( passengerList );

        // Assignment: Implement and test the following methods.
        // See the description of each method in the CA1 Specification PDF file from
        // Moodle

        // Q1, print out the contents of the passenger names array
        // System.out.println(Arrays.toString(getPassengerNames(passengerList)));

        // Q2, print out passenger objects containing "Harris"
        // System.out.println(getPassengersContainingNames(passengerList, "Harris"));

        // Q3, print out passengers over the age of 35
        // System.out.println(getPassengersOlderThan(passengerList, 35));

        // Q4, print out passengers that are male
        // System.out.println(countPassengersByGender(passengerList, "male"));

        // Q5, print out sum of passenger's fares
        // System.out.println(sumFares(passengerList));

        // Q6, print out the contents of male survivors array
        // System.out.println(Arrays.toString(maleSurvivors(passengerList)));

        // Q7, print out the passenger object that has the ticket "A/5 21171"
        // System.out.println(ticketOwner(passengerList, "A/5 21171"));

        // Q8, print out the average age of passengers
        // System.out.println(averageAge(passengerList));

        // Q9, print out the passengers that are in First Class
        // System.out.println(getPassengersByTicketClass(passengerList,s
        // PassengerClass.FIRST));

        // Q10, print out passengers sorted in passenger ID
        // System.out.println(sortPassengersByPassengerId(passengerList));

        // Q11, print out passengers sorted in passenger's names
        // System.out.println(sortPassengersByName(passengerList));

        // Q12, print out sotrted passenger's age then name
        // System.out.println(sortPassengersByAgeThenName(passengerList));

        // Q13, print out sorted passenger's gender then passenger number
        // System.out.println(sortPassengersByGenderThenPassengerNumber(passengerList));

        // Q14, print out sorted passenger's fare then survival
        // System.out.println(sortPassengersByFareThenSurvival(passengerList));

        // Q15, print out passenger's based on their ticket class
        // System.out.println(sortPassengersByTicketClass(passengerList));

        // Q16, print out passengers sorted by age using anonymous inner class
        // System.out.println(sortPassengersByAge(passengerList));

        // Q17, print out passengers sorted by ticket number using lambda function
        // System.out.println(sortPassengersByTicketNumberLambda(passengerList));

        // Q18, print out passengers sorted by ticket number using a static comparator
        // System.out.println(sortPassengersByTicketNumberStatic(passengerList));

        // Q19, create a dummy passenger and set the ticket number to be found
        // Passenger searchPassenger = new Passenger();
        // searchPassenger.setTicketNumber("PC 17599");
        // Passenger foundPassenger = findPassengerByTicketNumber(passengerList,
        // searchPassenger);
        // if (foundPassenger != null) {
        // System.out.println("Passenger found, " + foundPassenger.getTicketNumber());
        // } else {
        // System.out.println("Passenger not found.");
        // }

        // Q20, create a dummy passenger and set the passenger ID to be found
        Passenger searchPassengerID = new Passenger();
        searchPassengerID.setPassengerId("20");
        Passenger foundPassengerID = findPassengerByPassengerId(passengerList, searchPassengerID);
        if (foundPassengerID != null) {
        System.out.println("Passenger found, " + foundPassengerID.getPassengerId());
        } else {
        System.out.println("Passenger not found.");
        }
        System.out.println("Finished, Goodbye!");
    }

    public static Passenger findPassengerByPassengerId(ArrayList<Passenger> passengerList,
            Passenger searchPassengerID) {
        // sort the passenger's ID in order to perform binary search
        Collections.sort(passengerList);
        int index = Collections.binarySearch(passengerList, searchPassengerID);
        if (index >= 0) {
            return passengerList.get(index);
        } else {
            return null;
        }
    }

    public static Passenger findPassengerByTicketNumber(ArrayList<Passenger> passengerList, Passenger searchPassenger) {
        // sort the passenger's ticket number in order to perform binary search
        sortPassengersByTicketNumberStatic(passengerList);
        int index = Collections.binarySearch(passengerList, searchPassenger, Passenger.TicketNumberComparator);
        if (index >= 0) {
            return passengerList.get(index);
        } else {
            return null;
        }
    }

    public static ArrayList<Passenger> sortPassengersByTicketNumberStatic(ArrayList<Passenger> passengerList) {
        Collections.sort(passengerList, Passenger.TicketNumberComparator);
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByTicketNumberLambda(ArrayList<Passenger> passengerList) {
        passengerList.sort((p1, p2) -> p1.getTicketNumber().compareTo(p2.getTicketNumber()));
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByAge(ArrayList<Passenger> passengerList) {
        Collections.sort(passengerList, new Comparator<Passenger>() {
            @Override
            public int compare(Passenger p1, Passenger p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByTicketClass(ArrayList<Passenger> passengerList) {
        Collections.sort(passengerList, new TicketClassComparator());
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByFareThenSurvival(ArrayList<Passenger> passengerList) {
        Collections.sort(passengerList, new FareThenSurvivalComparator());
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByGenderThenPassengerNumber(ArrayList<Passenger> passengerList) {
        Collections.sort(passengerList, new GenderThenPassengerNumberComparator());
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByAgeThenName(ArrayList<Passenger> passengerList) {
        Collections.sort(passengerList, new AgeThenNameComparator());
        return passengerList;
    }

    public static ArrayList<Passenger> sortPassengersByName(ArrayList<Passenger> passengerList) {
        ArrayList<Passenger> passengersSortedByName = new ArrayList<>(passengerList);
        // lambda expression
        passengersSortedByName.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        return passengersSortedByName;
    }

    public static ArrayList<Passenger> sortPassengersByPassengerId(ArrayList<Passenger> passengerList) {
        ArrayList<Passenger> passengersSortedByID = new ArrayList<>(passengerList);
        Collections.sort(passengersSortedByID);
        return passengersSortedByID;
    }

    public static ArrayList<Passenger> getPassengersByTicketClass(ArrayList<Passenger> passengerList,
            PassengerClass passengerClass) {
        ArrayList<Passenger> filteredPassengers = new ArrayList<>();
        for (Passenger passenger : passengerList) {
            if (passenger.getPassengerClass() == passengerClass) {
                filteredPassengers.add(passenger);
            }
        }
        return filteredPassengers;
    }

    public static double averageAge(ArrayList<Passenger> passengerList) {
        double sumAge = 0;
        for (Passenger passenger : passengerList) {
            sumAge += passenger.getAge();
        }
        return sumAge / passengerList.size();
    }

    public static Passenger ticketOwner(ArrayList<Passenger> passengerList, String tickerNumber) {
        for (Passenger passenger : passengerList) {
            if (passenger.getTicketNumber().equals(tickerNumber)) {
                return passenger;
            }
        }
        return null;
    }

    public static String[] maleSurvivors(ArrayList<Passenger> passengerList) {
        ArrayList<String> maleSurvivorsList = new ArrayList<>();
        for (Passenger passenger : passengerList) {
            if (passenger.getGender().equalsIgnoreCase("male") && passenger.getSurvived() == 1) {
                maleSurvivorsList.add(passenger.getName());
            }
        }
        String[] maleSurvivors = new String[maleSurvivorsList.size()];

        return maleSurvivorsList.toArray(maleSurvivors);
    }

    public static double sumFares(ArrayList<Passenger> passengerList) {
        double sum = 0;
        for (Passenger passenger : passengerList) {
            sum += passenger.getFare();
        }
        return sum;
    }

    public static ArrayList<Passenger> countPassengersByGender(ArrayList<Passenger> passengerList, String gender) {
        ArrayList<Passenger> passengersBasedOnGender = new ArrayList<>();
        for (Passenger passenger : passengerList) {
            if (passenger.getGender().equalsIgnoreCase(gender)) {
                passengersBasedOnGender.add(passenger);
            }
        }
        return passengersBasedOnGender;
    }

    public static ArrayList<Passenger> getPassengersOlderThan(ArrayList<Passenger> passengerList, int age) {
        ArrayList<Passenger> passengersOlderThan = new ArrayList<>();
        for (Passenger passenger : passengerList) {
            if (passenger.getAge() > age) {
                passengersOlderThan.add(passenger);
            }
        }
        return passengersOlderThan;
    }

    public static ArrayList<Passenger> getPassengersContainingNames(ArrayList<Passenger> passengerList, String name) {
        ArrayList<Passenger> passengerListWithName = new ArrayList<>();
        for (Passenger passenger : passengerList) {
            if (passenger.getName().contains(name)) {
                passengerListWithName.add(passenger);
            }
        }
        return passengerListWithName;
    }

    public static String[] getPassengerNames(ArrayList<Passenger> passengerList) {
        // declare the array with the correct amount of size
        String[] names = new String[passengerList.size()];
        for (int i = 0; i < passengerList.size(); i++) {
            // get the passenger object then access the name of passenger
            names[i] = passengerList.get(i).getName();
        }
        return names;
    }

    /**
     * Populate an ArrayList of Passenger objects with data from a text file
     * 
     * @param passengerList - an ArrayList to be filled with Passenger objects
     * @param fileName      - name of CSV text file containing passenger details
     */
    public static void loadPassengerDataFromFile(ArrayList<Passenger> passengerList, String fileName) {

        // Format of each row of data is:
        // Name,Age,Height(m),GPA - these heading names are included as the first row in
        // file
        // John Malone,20,1.78,3.55 for example

        // Use a Regular Expression to set both comma and newline as delimiters.
        // sc.useDelimiter("[,\\r\\n]+");
        // Text files in windows have lines ending with "CR-LF" or "\r\n" sequences.
        // or may have only a newline - "\n"
        // Windows uses CRLF (\r\n, 0D 0A) line endings while Unix just uses LF (\n,
        // 0A).
        //
        try (Scanner sc = new Scanner(new File(fileName))
                .useDelimiter("[,\\r\\n]+")) {

            // skip past the first line, as it has field names (not data)
            if (sc.hasNextLine())
                sc.nextLine(); // read the header line containing column titles, but don't use it

            // while there is a next token to read....
            System.out.println("Go...");

            while (sc.hasNext()) {
                String passengerId = sc.next(); // read passenger ID
                int survived = sc.nextInt(); // 0=false, 1=true
                int passengerClass = sc.nextInt(); // passenger class, 1=1st, 2=2nd or 3rd
                String name = sc.next();
                String gender = sc.next();
                int age = sc.nextInt();
                int siblingsAndSpouses = sc.nextInt();
                int parentsAndChildren = sc.nextInt();
                String ticketNumber = sc.next();
                double fare = sc.nextDouble();
                String cabin = sc.next();
                String embarkedAt = sc.next();

                System.out.println(passengerId + ", " + name);

                passengerList.add(
                        new Passenger(passengerId, survived, passengerClass,
                                name, gender, age, siblingsAndSpouses, parentsAndChildren, ticketNumber,
                                fare, cabin, embarkedAt));
            }
        } catch (FileNotFoundException exception) {
            System.out.println("FileNotFoundException caught. The file " + fileName + " may not exist." + exception);
        }
    }

    public static void displayAllPassengers(ArrayList<Passenger> passengerList) {
        System.out.println("Displaying all passengers:");
        for (Passenger passenger : passengerList) {
            System.out.println(passenger);
        }
    }
}