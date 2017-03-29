package menu;

import coreFunctions.Driver;
import user.Employee;
import BusinessWorkDays.Workday;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



/**
 * Created by Gabrielle on 5/03/2017.
 * test
 */
public class BusinessMenu {
    Login login = new Login();

    Driver driver = new Driver();
    public static ArrayList<Employee> employeeList = new ArrayList<>();

    public void printMenu(String bId){
        Scanner reader = new Scanner(System.in);
        Scanner eID = new Scanner(System.in);
        String starttime;
        String endtime;
        String day;
        String empID;



        /* debug purposes
        for(int i=0; i < login.businessList.size() ;i++){
            System.out.println(login.businessList.get(i).getName());
            System.out.println(login.businessList.get(i).getAddress());
            System.out.println(login.businessList.get(i).getPhoneNo());

        } */

        //infinite loop
        while(true) {
            //print business menu
            System.out.println("\n+----------------------------------+");
            System.out.println("|           Business               |");
            System.out.println("|              menu                |");
            System.out.println("+----------------------------------+");

            System.out.println("1. Add Employee");
            System.out.println("2. Add working days/times");
            System.out.println("3. Remove working days/times");
            System.out.println("4. View summaries of bookings");
            System.out.println("5. View new Bookings");
            System.out.println("6. Show worker availability");
            System.out.println("7. Adjust business hours");
            System.out.println("8. Log out");

            System.out.print("Enter choice: ");
            int choice = reader.nextInt();

            if(choice == 1){
                driver.addEmployee(bId);
                continue;
            }

            if(choice == 2){
                boolean valid = true;

                while(valid){

                        do{
                        System.out.print("Enter employee ID:");
                        empID = eID.nextLine();
                        }while(!checkEmployeeID(bId,empID));
                    day = reader.nextLine();
                        do {
                            System.out.println("Enter Day:");
                            day = reader.nextLine();
                        }while(checkDay(day));
                    do {
                        System.out.print("Enter shift start time:");
                        starttime = reader.nextLine();
                    }while(checktime(starttime));
                    do {
                        System.out.print("Enter shift end time:");
                        endtime = reader.nextLine();
                    }while(checktime(endtime));

                    valid = Worktimes(bId,empID, day,starttime,endtime);

                }
            }
            if(choice == 7){
                boolean valid =true;

                while(valid){

                    day = reader.nextLine();
                    do {
                        System.out.println("Enter Day:");
                        day = reader.nextLine();
                    }while(checkDay(day));
                    do {
                        System.out.print("Enter opening time:");
                        starttime = reader.nextLine();
                    }while(checktime(starttime));
                    do {
                        System.out.print("Enter closing times:");
                        endtime = reader.nextLine();
                    }while(checktime(endtime));

                    valid = BHours(bId,day,starttime,endtime);

                }
            }
            if(choice == 8){
                System.out.println("--------- New Service---------");
                addNewService();

            }

            else if(choice == 8){
                System.out.println("Successfully logged out of the system!");
                System.exit(0);
            }

            else {
                System.out.println("Will do these options later!");
                System.exit(0);

            }


        }

    }
    public void addNewService()
    {
        // owner adds the service
        // name
        // cost
        // time length
        // description
        // this is sent to a text file to allow the customer to choose from service when booking
    }
public boolean checkD(String day){return checkDay(day);}
    private boolean checkDay(String day){
        try{
            DateFormat time = new SimpleDateFormat("EEEE");
            time.parse(day);
        }
        catch(ParseException e){
            System.out.println("Invalid Day");
            return true;

        }
        return false;
    }
    private boolean checktime(String t){
        try{
            DateFormat time = new SimpleDateFormat("HH:mm:ss");
            time.parse(t);
        }
        catch(ParseException e){
            System.out.println("Invalid time:");
            return true;

        }
        return false;
    }
    private boolean Worktimes(String bId, String empId, String day,String starttime,String endtime){
        DateFormat time = new SimpleDateFormat("HH:mm:ss");

        try{

            Date st = time.parse(starttime);
            Date et = time.parse(endtime);


            // This makes sure scheduled work day CANNOT be before the current time and date, Ending work time must not be before start time or equal.
            if(!et.after(st)){
                System.out.println("Can't Start after its ended");
                return true;

            }else if(st.equals(et)){
                System.out.println("Can't Start and end at same time");
                return true;
            }
         /*   if(//call to method ){
         System.out.println("This is an invalid shift");
         return true;
         }
         */


            System.out.println("The working time of: " + day + ":  "+starttime+" - " + endtime);

            driver.addWorkdays(bId,empId,day,starttime,endtime);
            return false;

        }catch(ParseException e){
            System.out.println("Invalid Date/Time");
            return true;

        }
        //CHECK AGAINST TEXTFILE WITH DAY RESTRICTIONS SET BY BUSINESS
    }
    private boolean BHours(String bId, String day,String starttime,String endtime){
        Workday w = new Workday();
        DateFormat time = new SimpleDateFormat("HH:mm:ss");

        try{

            Date st = time.parse(starttime);
            Date et = time.parse(endtime);


            // This makes sure scheduled work day CANNOT be before the current time and date, Ending work time must not be before start time or equal.
            if(!et.after(st)){
                System.out.println("Can't Start after its ended");
                return true;

            }else if(st.equals(et)){
                System.out.println("Can't Start and end at same time");
                return true;
            }

            System.out.println("The working hours time of: " + day + ":  "+starttime+" - " + endtime);

            //


        }catch(ParseException e){
            System.out.println("Invalid Date/Time");
            return true;

        }
        w.readFile(bId, day, starttime, endtime);
        return false;

    }
    public  boolean Workt(String bId,String empId, String day,String starttime,String endtime){return Worktimes(bId,empId,  day, starttime, endtime);}
    public void loadCustomerInformation(){
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("employeeList.txt"));

            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String loginDetails[] = x.split(":",5);
                    String bId = loginDetails[0];
                    String empID = loginDetails[1];
                    String fullName = loginDetails[2];
                    String TFN = loginDetails[3];
                    String phoneNo = loginDetails[4];
                    Employee e = new Employee(bId, empID, fullName, TFN, phoneNo);
                    employeeList.add(e);
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

    public boolean checkEmployeeID(String bId,String empID){

        loadCustomerInformation();

            for(int i=0; i < employeeList.size() ;i++){
                if(empID.equals(employeeList.get(i).geteId())){
                    if(bId.equals(employeeList.get(i).getbId())){
                        return true;
                    }
                }

            }
            System.out.println("employee ID invalid");
            return false;

    }

}
