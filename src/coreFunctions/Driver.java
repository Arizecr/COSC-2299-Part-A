package coreFunctions;
import java.util.Scanner;
/**
 * Created by Gabrielle on 24/03/2017.
 */
public class Driver {

    public void addEmployee(){
        Scanner reader = new Scanner(System.in);

        System.out.println("\nAdd Employee");
        System.out.println("====================");

        //to implement: automatically generate employee id

        //other stuff
        System.out.println("Enter full name: ");
        String employeeName = reader.nextLine();
        System.out.println("Enter address: ");
        String address = reader.nextLine();
        System.out.println("Enter tax file number");
        String tfn = reader.nextLine();
        System.out.println("Enter phone number");
        String phoneNo = reader.nextLine();
    }

}
