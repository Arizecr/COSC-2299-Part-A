package Menu;

import Actor.Customer;
import Actor.Business;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class Login {
    public static ArrayList<Customer> customerList = new ArrayList<>();
    public static ArrayList<Business> businessList = new ArrayList<>();

    public void loginMenu(){
        CustomerMenu customer = new CustomerMenu();
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

            //test if customer login is valid
            if(username.charAt(0) == 'c'){
                if(verifyLoginDetails("customer",username, password)){
                    customer.printMenu();
                    System.exit(0);
                }

            }

            //test if customer login is valid
            if(username.charAt(0) == 'b'){
                if(verifyLoginDetails("owner",username, password)){
                    System.out.println("owner login works!\n");

                    System.exit(0);
                }
                System.out.println("Invalid login details. Details do not exist in system.");

            }


            //Login details are not valid, try again
            else{
                System.out.println("Invalid login details. Try again");

            }
        }
    }

    /*
     * Load customer data
     */

    public void loadCustomerInformation(){
        BufferedReader br;
        try {


                br = new BufferedReader(new FileReader("customerinfo.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String loginDetails[] = x.split(":",2);
                    String username = loginDetails[0];
                    String password = loginDetails[1];
                    Customer course = new Customer(username, password);
                    customerList.add(course);
                }
                //prints error
            } catch (IOException e) {
                e.printStackTrace();
            }


            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }


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
                    String loginDetails[] = x.split(":",2);
                    String username = loginDetails[0];
                    String password = loginDetails[1];
                    Business ownerInfo = new Business(username, password);
                    businessList.add(ownerInfo);
                }
                //prints error
            } catch (IOException e) {
                e.printStackTrace();
            }


            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
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
