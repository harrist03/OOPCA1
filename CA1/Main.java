package CA1;

// CA1
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String fileName = "CA1/titanic-data-100.csv"; // file should be in the project folder (below pom.xml)

        ArrayList<Passenger> passengerList = new ArrayList<>();

        loadPassengerDataFromFile(passengerList, fileName);

        // displayAllPassengers( passengerList );

        // Assignment: Implement and test the following methods.
        // See the description of each method in the CA1 Specification PDF file from
        // Moodle

        // Q1, declared an array and a method with passengerList as parameter
        String[] passengerNames = getPassengerNames(passengerList);
        // print out the contents of the array
        // System.out.println(Arrays.toString(passengerNames));

        // Q2, print out passenger objects containing "Harris"
        System.out.println(getPassengersContainingNames(passengerList, "Harris"));

        // Q3, 
        // getPassengersOlderThan();
        // countPassengersByGender();
        // sumFares();
        // maleSurvivors();
        // ticketOwner();
        // averageAge();
        // getPassengersByTicketClass();
        // sortPassengersByPassengerId()
        // sortPassengersByName();
        // sortPassengersByAgeThenName();
        // sortPassengersByGenderThenPassengerNumber()
        // sortPassengersByFareThenSurvival();
        // sortPassengersByTicketClass()
        // sortPassengersByAge();
        // sortPassengersByTicketNumberLambda();
        // sortPassengersByTicketNumberStatic();
        // findPassengerByTicketNumber();
        // findPassengerByPassengerId();

        System.out.println("Finished, Goodbye!");
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