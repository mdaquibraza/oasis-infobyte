import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Reservation Class
class Reservation {
    String trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String from;
    String to;
    int pnr;
    
    public Reservation(int pnr, String trainNumber, String trainName, String classType, 
                       String dateOfJourney, String from, String to) {
        this.pnr = pnr;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }
    
    public void display() {
        System.out.println("PNR: " + pnr);
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Train Name: " + trainName);
        System.out.println("Class Type: " + classType);
        System.out.println("Date of Journey: " + dateOfJourney);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
    }
}

// User Class for Login Credentials
class User {
    String userId;
    String password;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}

// Main Reservation System Class
public class OnlineReservationSystem {
    private static Map<String, User> users = new HashMap<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static int pnrCounter = 1000; // Starting PNR

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Adding a sample user for login
        users.put("admin", new User("admin", "password"));

        System.out.println("Welcome to the Online Reservation System");

        // Login
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (authenticateUser(userId, password)) {
            System.out.println("Login Successful!");
            boolean running = true;
            while (running) {
                System.out.println("\n1. Make a Reservation");
                System.out.println("2. Cancel a Reservation");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        makeReservation(scanner);
                        break;
                    case 2:
                        cancelReservation(scanner);
                        break;
                    case 3:
                        running = false;
                        System.out.println("Exiting the system. Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid login credentials.");
        }
        scanner.close();
    }

    // User Authentication Method
    private static boolean authenticateUser(String userId, String password) {
        User user = users.get(userId);
        return user != null && user.password.equals(password);
    }

    // Method for Making a Reservation
    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter Train Number: ");
        String trainNumber = scanner.nextLine();
        
        // Assuming train name is derived from train number
        String trainName = "Train-" + trainNumber;
        
        System.out.print("Enter Class Type: ");
        String classType = scanner.nextLine();
        
        System.out.print("Enter Date of Journey (DD-MM-YYYY): ");
        String dateOfJourney = scanner.nextLine();
        
        System.out.print("Enter From Location: ");
        String from = scanner.nextLine();
        
        System.out.print("Enter To Location: ");
        String to = scanner.nextLine();

        // Generating PNR and storing reservation
        int pnr = pnrCounter++;
        Reservation reservation = new Reservation(pnr, trainNumber, trainName, classType, dateOfJourney, from, to);
        reservations.add(reservation);
        
        System.out.println("Reservation Successful! Your PNR is: " + pnr);
    }

    // Method for Canceling a Reservation
    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter PNR Number to Cancel: ");
        int pnr = scanner.nextInt();

        Reservation reservationToCancel = null;
        for (Reservation res : reservations) {
            if (res.pnr == pnr) {
                reservationToCancel = res;
                break;
            }
        }

        if (reservationToCancel != null) {
            System.out.println("Reservation Details:");
            reservationToCancel.display();
            System.out.print("Are you sure you want to cancel? (yes/no): ");
            scanner.nextLine(); // Consume newline
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("yes")) {
                reservations.remove(reservationToCancel);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("No reservation found with PNR: " + pnr);
        }
    }
}
