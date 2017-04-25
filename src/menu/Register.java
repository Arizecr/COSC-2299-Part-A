package menu;
import coreFunctions.WriteToFile;
import test.Logging;
import user.Customer;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by Martin on 5/03/2017.
 */
public class Register {
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());

/*
 * Customer is presented with the registration menu
 */
    public void registerMenu(){
        String username;

        Scanner reader = new Scanner(System.in);
        WriteToFile toTxt = new WriteToFile();

        boolean loginSuccessful = false;

        // Logs user into the system
        System.out.println("\n\nRegister your details below. ");
        System.out.println("------------------------------------ \n");



        //infinite loop
        while(true){
            int valid = 0;

            while(true){

                System.out.print("Username (must start with C): ");
                username = reader.nextLine();
                if(testUsername(username)){
                    System.out.println("Username Valid\n");
                    break;

                }
                //remember to append c to the start of username
            }

            String p;
            String password;
            do{
                System.out.print("Password: ");
                password = reader.nextLine();

                System.out.print("re-enter Password: ");
                p = reader.nextLine();
                if(!p.equals(password)){ System.out.println("Passwords do not match");}

            }while(!p.equals(password));


            System.out.print("Name: ");
            String name = reader.nextLine();

            System.out.print("Address: ");
            String address = reader.nextLine();

            System.out.print("Mobile: [04xxxxxxxx]");
            String mobile = reader.nextLine();


            valid = testRegister(password, name, address, mobile);
            if(valid == 0){
                toTxt.WriteToTXT(new Customer(username, password, name, address, mobile), "customerinfo.txt");
                System.out.println("Succesfully Registered");
                break;
            }
        }

    }


    /*
     * tests related to register function
     */
    public int testReg(String password, String name, String address, String mobile){
        return testRegister(password, name, address, mobile);
    }
    public boolean testUser(String username){
        return testUsername(username);
    }
    private boolean testUsername(String username){
        Login login = new Login();
        //isEmpty just checks for null
        if(username.isEmpty() || (username.charAt(0) != 'c' )){

            System.out.println("Error: Username must start with a 'c'. Or length is over 15 characters. Try again");
            return false;

        }


        if((username.charAt(0) == 'c') && (username.length()<= 15) && (username.length()>1)){

            for(int i = 0; i< login.customerList.size(); i++) {

                if (login.customerList.get(i).getUsername().equals(username)) {
                    System.out.println("Username already exists. Re-enter valid username\n");
                    return false;
                }
            }


        }
        else{
            return false;
        }


        return true;
    }


    private int testRegister(String password, String name, String address, String mobile){
        //Login login = new Login();
        int valid = 0;
        //test if customer login is valid

        /*
        //isEmpty just checks for null
        if(username.isEmpty() || username.charAt(0) != 'c' ){

            System.out.println("Error: Username must start with a 'c'. Or length is over 15 characters. Try again");
            return ++valid;

        }


        if((username.charAt(0) == 'c') && (username.length()<= 15) && (username.length()>1)){

            for(int i = 0; i< login.customerList.size(); i++) {

                if (login.customerList.get(i).getUsername().equals(username)) {
                    System.out.println("Username already exists. Re-enter valid username");
                    return ++valid;
                }
            }


        }
        else return ++valid;

        */

        if(name.isEmpty() || (name.length() < 1)  ){

            System.out.println("Invalid Name");
            return ++valid;

        }
        if(!name.matches("[a-zA-z' ']+")){
            System.out.println("name is invalid [cannot contain numbers]");
            return ++valid;
        }
        if(address.isEmpty() || (address.length() < 1)){
            System.out.println("Invalid Address");
            return ++valid;

        }

        if(mobile.isEmpty() || (mobile.length() != 10)||mobile.charAt(0) != '0'||mobile.charAt(1) != '4' ){

            System.out.println("Invalid Mobile");
            return ++valid;

        }
        int m;
        try{
            m = Integer.parseInt(mobile);
        } catch (NumberFormatException e) {
            System.out.println("Mobile number not numeric");
            return ++valid;

        }

        if( password.isEmpty()) {
            System.out.println("Invalid Password");
            return ++valid;
        }

        return valid;
    }



}
