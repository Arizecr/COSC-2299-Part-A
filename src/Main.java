import menu.Login;
import menu.Register;

import java.util.Scanner;


/**
 * Created by Gabrielle on 5/03/2017.
 */
public class Main {
    public static void main(String[] args){


        Scanner reader = new Scanner(System.in);
        Login login = new Login();
        Register register = new Register();

        login.loadCustomerInformation(); //loads customer information

        //display main page
        System.out.println("\n+----------------------------------+");
        System.out.println("|           Welcome to the         |");
        System.out.println("|               system             |");
        System.out.println("+----------------------------------+");
        System.out.println("1. Login");
        System.out.println("2. Register an account");
        System.out.println("3. Exit system");



        //infinite loop
        while(true){
            login.loadCustomerInformation(); //loads customer information

            System.out.print("Enter choice (1-3): ");

            //error- user entered a non integer
            while(!reader.hasNextInt()) {
                System.out.println("Error: entered a non integer. Enter a number between 1-3.");
                System.out.print("Enter choice (1-3): ");
                reader.next();
            }
            int choice = reader.nextInt();

            //go to login menu
            if(choice == 1){
                login.loginMenu();

            }

            //go to registration menu
            else if(choice == 2){
                register.registerMenu();
                System.out.println("\nWhat would you like to do now?");
                System.out.println("1. Login\n2. Register another account\n3. Exit system.");

            }

            //log out of system
            else if(choice == 3){
                System.out.println("\nSuccessfully logged out of the system!");
                System.exit(0);

            }

            //invalid choice
            else{
                System.out.println("\nInvalid choice. Try again");
                System.out.println("Enter a number between 1-3\n");

            }
        }

    }


}
