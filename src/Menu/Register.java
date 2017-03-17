package Menu;

import Actor.Customer;
import CoreFunctions.WriteToFile;

import java.util.Scanner;

/**
 * Created by Martin on 5/03/2017.
 */
public class Register {
/*
 * NOTE FOR MARTIN!!!! create additional registration details such as
 * full name of customer, their address, and phone number!!!! Also verification
 * + make sure username + password length is >= 3 (or some other reasonable number)
 */



    public void registerMenu(){

        Scanner reader = new Scanner(System.in);
        WriteToFile toTxt = new WriteToFile();

        boolean loginSuccessful = false;




        // Logs user into the system
        System.out.println("\n\nRegister your details below. ");
        System.out.println("------------------------------------ \n");



        //infinite loop
        while(true){
            int valid = 0;

            System.out.print("Username (must start with C): ");
            String username = reader.nextLine();
            //remember to append c to the start of username

            System.out.print("Password: ");
            String password = reader.nextLine();

            valid = testRegister(username, password);
            if(valid == 0){
                toTxt.WriteToTXT(new Customer(username, password, "temp", "temp", "temp"), "customerinfo.txt");
                System.out.println("Succesfully Registered");
                break;
            }
        }

    }
    public int testReg(String username,String password){
        return testRegister(username, password);
    }
    private int testRegister(String username,String password){
        Login login = new Login();
        int valid = 0;
        //test if customer login is valid
        if(username.charAt(0) == 'c' && username.length() <= 15 && username.length()>1){

            for(int i = 0; i< login.customerList.size(); i++){

                if( login.customerList.get(i).getUsername().equals(username) ){
                    System.out.println("Username already exists. Re-enter valid username");
                    valid++;
                }
            }

        }

        //Login details are not valid, try again
        else{
            System.out.println("Error: Username must start with a 'c'. Or length is over 15 characters. Try again");
            valid++;

        }
        if( password==null|| password == "\n"||password=="")
            valid++;

        return valid;
    }


}
