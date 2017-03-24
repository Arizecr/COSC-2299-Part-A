package menu;

import java.util.Scanner;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class BusinessMenu {
    Login login = new Login();

    public void printMenu(){
        Scanner reader = new Scanner(System.in);

        //print business menu
        System.out.println("\n+----------------------------------+");
        System.out.println("|           Business               |");
        System.out.println("|              menu                |");
        System.out.println("+----------------------------------+");

        System.out.println("1. Add Employee");
        System.out.println("2. Add working dates/times");
        System.out.println("3. View summaries of bookings");
        System.out.println("4. View new Bookings");
        System.out.println("5. Show worker availability");
        System.out.println("6. Log out");

        /* debug purposes
        for(int i=0; i < login.businessList.size() ;i++){
            System.out.println(login.businessList.get(i).getName());
            System.out.println(login.businessList.get(i).getAddress());
            System.out.println(login.businessList.get(i).getPhoneNo());

        } */

        //infinite loop
        while(true) {

            System.out.print("Enter choice: ");
            int choice = reader.nextInt();

            if(choice == 6){
                System.out.println("Successfully logged out of the system!");
                System.exit(0);
            }

            else {
                System.out.println("Will do these options later!");
                System.exit(0);

            }


        }

    }





}
