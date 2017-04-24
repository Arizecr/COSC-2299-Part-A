package menu;

import BusinessWorkDays.Workday;
import bookings.Services;
import coreFunctions.Driver;
import test.Logging;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class CustomerMenu {
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    Login login = new Login();
    Driver driver = new Driver();
    Workday workday = new Workday();

    /*
     * Prints menu for business owner
     * and runs all the functions
     */
    public void printMenu(String username){

        Scanner reader = new Scanner(System.in);

        int bID = getBusiness();
        //infinite loop
        while(true) {
            //print customer menu

            System.out.println("\n+----------------------------------+");
            System.out.println("|           Customer               |");
            System.out.println("|              menu                |");
            System.out.println("+----------------------------------+");

            System.out.println("\n1. Book appointment (view available days/times)");
            System.out.println("2. View Booking(s)");
            System.out.println("3. Log out");

            System.out.print("Enter choice: ");

            //user has entered a non integer input
            while(!reader.hasNextInt()) {
                System.out.println("Error: entered a non integer. Enter a number between 1-8.");
                System.out.print("Enter choice (1-8): ");
                reader.next();
            }

            int choice = reader.nextInt();
            switch (choice) {
                case 1: //View available days/times (to book an appointment for a business)
                    availableBookings(bID);
                    continue;
                case 2:
                    System.out.println("Current Bookings: ");
                    String b = "b"+Integer.toString(bID+1);
                    // System.out.println(b);
                    driver.viewBookingsCustomer(username,b); //view current bookings
                    continue;
                case 3:
                    System.out.println("Successfully logged out of the system!");
                    System.exit(0);
                    continue;
                default:
                    System.out.println("Invalid numeric input!");
                    continue;
            }
        }



    }

    /*
     * Asks user which business they would like to view the days/hours they are open
     */
    public int getBusiness(){
        int count = 0;
        Scanner reader = new Scanner(System.in);

        System.out.println("\n\nWhich business would you like to view/book for?");

        //gets all the names of all business's registered to the system
        for(int i=1;i<login.businessList.size();i++){
            System.out.println(i+". "+login.businessList.get(i).getName());
            count++;
        }
        System.out.print("Choose option: ");

        //checks for non integer input
        while(!reader.hasNextInt()) {
            System.out.println("Error: entered a non integer. Enter a number between 1-8.");
            reader.next();
        }

        //stores input
        int id = reader.nextInt();

        //check whether validity of option
        while(!verifyBusinessID(id, count)){
            System.out.println("Error: Invalid choice. Choose a number between 1-"+count);
            System.out.print("Choose option: ");

            //checks again for non-integer input
            while(!reader.hasNextInt()) {
                System.out.println("Error: entered a non integer. Try again.");
                System.out.print("Choose option: ");
                reader.next();
            }


            id = reader.nextInt();

        }


        return id -1;
    }

    /*
     * verifies that number entered is valid
     */
    public Boolean verifyBusinessID(int id, int count){
        //checks that number entered is more than 0 and less than max
        if(id > count || id <=0){
            return false;
        }

        return true;
    }

    /*
     * Prints the available booking hours of a given business
     */
    public void availableBookings(int bID){
        int index=0;
        String n = null;
        Services s = new Services();

        System.out.println("\n"+login.businessList.get(bID).getName()+ " [opening hours]");
        System.out.println("-----------------------------");
        workday.printFile(login.businessList.get(bID).getUsername());
        System.out.println("-----------------------------");
        System.out.println("\nSelect a Service.");
        s.printService(login.businessList.get(bID).getUsername());

        Scanner reader = new Scanner(System.in);
        do {
            System.out.print("Enter Service ID: ");
            n = reader.nextLine();
            index = s.checkID(n);
        }while(index==0);

    }
}
