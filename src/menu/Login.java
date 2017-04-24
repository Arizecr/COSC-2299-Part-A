package menu;

import test.*;
import user.Business;
import user.Customer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;
/**
 * Created by Gabrielle on 5/03/2017.
 */
public class Login {
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    public static ArrayList<Customer> customerList = new ArrayList<>();
    public static ArrayList<Business> businessList = new ArrayList<>();
    Logging l =new Logging();    public void loginMenu(){

        loadOwnerInformation();

        Scanner reader = new Scanner(System.in);

        // Logs user into the system
        System.out.println("\n\nGreat! Just enter your username and password below to log in. ");
        System.out.println("---------------------------------------------------------------- \n");

        //infinite loop
        while(true){

            System.out.print("Username: ");
            String username = reader.nextLine();

            System.out.print("Password: ");
            String password = reader.nextLine();


            if(username.length() <1 || password.length()<1){
                System.out.println("Error: username or password cannot be blank");
                continue;
            }

            testLogin(username,password);
        }
    }

    public void testLogin(String username, String password){
        CustomerMenu customer = new CustomerMenu();
        BusinessMenu business = new BusinessMenu();
        //test if customer login is valid
        if(username.charAt(0) == 'c'){
            loadOwnerInformation();
            if(verifyLoginDetails("customer",username, password)){
                customer.printMenu(username);

            }

        }

        //test if customer login is valid
        if(username.charAt(0) == 'b'){
            if(verifyLoginDetails("owner",username, password)){
                System.out.println("owner login works!\n");
                business.printMenu(username);

            }
            System.out.println("Invalid login details. Details do not exist in system.");

        }


        //Login details are not valid, try again
        else{
            System.out.println("Invalid login details. Try again");

        }
    }

    /*
     * Load customer data
     */

    public void loadCustomerInformation(){
        customerList = new ArrayList<>();
        BufferedReader br;
        try {


                br = new BufferedReader(new FileReader("customerinfo.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String loginDetails[] = x.split(":",5);
                    String username = loginDetails[0];
                    String password = loginDetails[1];
                    String fullName = loginDetails[2];
                    String address = loginDetails[3];
                    String phoneNo = loginDetails[4];
                    Customer course = new Customer(username, password, fullName, address, phoneNo);
                    customerList.add(course);
                }
                //prints error
            } catch (IOException e) {
                //e.printStackTrace();
                 l.Logging();
                LOGGER.log(Level.SEVERE,e.toString(),e);
            }
            catch (ArrayIndexOutOfBoundsException ae) {
                //e.printStackTrace();
                l.Logging();
                LOGGER.log(Level.SEVERE,ae.toString(),ae);

            }


            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            //e.printStackTrace();
             l.Logging();
            LOGGER.log(Level.WARNING,e.toString(),e);
        }

    }

    public void getOwnerinfo(){
        loadOwnerInformation();
    }//for testing purposes

    /*
     * Load owner information
     */

    private void loadOwnerInformation(){

        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("business.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String loginDetails[] = x.split(":",6);
                    String username = loginDetails[0];
                    String password = loginDetails[1];
                    String businessName = loginDetails[2];
                    String name = loginDetails[3];
                    String address = loginDetails[4];
                    String phone = loginDetails[5];
                    Business ownerInfo = new Business(username, password, businessName, name, phone, address);
                    businessList.add(ownerInfo);
                }
                //prints error
            } catch (IOException e) {
               // e.printStackTrace();
                 l.Logging();
                LOGGER.log(Level.SEVERE,e.toString(),e);
            }
            catch (ArrayIndexOutOfBoundsException ae) {
                //e.printStackTrace();
                l.Logging();
                LOGGER.log(Level.SEVERE,ae.toString(),ae);

            }


            //file cannot be found
        } catch (FileNotFoundException e) {
          //  System.out.println(e);
           // e.printStackTrace();
            System.out.println("File not Found");
             l.Logging();
            LOGGER.log(Level.WARNING,e.toString(),e);
        }

    }


    /*
     * Tests whether customer login details are valid
     */
    public boolean getVerification(String type, String username, String password){
        return verifyLoginDetails(type,username,password);
    }
    private boolean verifyLoginDetails(String type, String username, String password) {

        if (type.equals("customer")){//verify customer
            for(int i=0; i < customerList.size() ;i++){
                if(username.equals(customerList.get(i).getUsername())){
                    if(password.equals(customerList.get(i).getPassword())){
                        return true;
                    }
                    else{
                        return false;
                    }
                }

            }

            return false;

        }

        else{ //verify business owner
            for(int i=0; i < businessList.size() ;i++){
                if(username.equals(businessList.get(i).getUsername())){
                    if(password.equals(businessList.get(i).getPassword())){
                        return true;
                    }
                    else{
                        return false;
                    }
                }

            }

            return false;

        }



    }





}
