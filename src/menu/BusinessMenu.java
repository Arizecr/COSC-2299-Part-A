package menu;

import BusinessWorkDays.Workday;
import EmployeeAvailabilityDays.AvailableDay;
import bookings.Services;
import coreFunctions.Driver;
import coreFunctions.WriteToFile;
import test.Logging;
import user.Employee;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;


/**
 * Created by Gabrielle on 5/03/2017.
 * test
 */
public class BusinessMenu {
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    Login login = new Login();
    Workday w = new Workday();
    AvailableDay av = new AvailableDay();
    Driver driver = new Driver();
    Employee emp = new Employee();


    /*
     * Print business menu and allows business owner to choose
     * a business function
     */
    public void printMenu(String bId){
        Scanner reader = new Scanner(System.in);
        Scanner read = new Scanner(System.in);
        Scanner r = new Scanner(System.in);
        String starttime;
        String endtime;
        String day;//day = day.substring(0,1).toUpperCase() + day.substring(1);
        String empID;
        String name;


        //infinite loop
        while(true) {
            //print business menu
            System.out.println("\n+----------------------------------+");
            System.out.println("|           Business               |");
            System.out.println("|              menu                |");
            System.out.println("+----------------------------------+");
            emp.printEmployeeInformation(bId);
            System.out.println("1. Add Employee");
            System.out.println("2. Add working days/times for Employee(s)");
            System.out.println("3. Remove working days/times for Employee(s)");
            System.out.println("4. Show worker availability");
            System.out.println("5. Add new service(s)");
            System.out.println("6. View summaries of bookings (including new bookings)");
            System.out.println("7. View/Adjust business hours");
            System.out.println("8. Log out");

            System.out.print("Enter choice (1-8): ");

            while(!reader.hasNextInt()) {
                System.out.println("Error: entered a non integer. Enter a number between 1-8.");
                System.out.print("Enter choice (1-8): ");
                reader.next();
            }

            int choice = reader.nextInt();

            //run choice of business owner
            switch(choice){

                //add Employee
                case 1:
                    driver.addEmployee(bId);
                    continue;

                    //Add/Remove/Edit working days/times of Employee
                case 2:
                case 3:
                    boolean valid = true;

                    //infinite loop for validility
                    while(valid){

                        reader = new Scanner(System.in);
                        do{
                            System.out.print("\nEnter employee ID: ");
                            empID = reader.nextLine();
                        }while(!emp.checkEmployeeID(bId,empID)); //check validity of employee id

                        driver.printEmployeeWorktimes(bId, empID);

                        do {
                            System.out.print("Enter Day: ");
                            day = reader.nextLine().toLowerCase();
                        }while(checkDay(day));

                        //add/edit employee shifts
                        if(choice == 2) {

                            do {
                                System.out.print("Enter shift start time (hour:min): ");
                                starttime = reader.nextLine();
                            }while(checktime(starttime)); //check validity of start time
                            do {
                                System.out.print("Enter shift end time (hour:min): ");
                                endtime = reader.nextLine();
                            }while(checktime(endtime)); //check validity of end time
                            //check validity of shift time
                            valid = Worktimes(bId, empID, day, starttime, endtime);

                        }

                        //remove employee shifts
                        if(choice==3){
                            valid = false;
                            driver.deleteEmployeeWorktimes(bId,empID,day);

                        }

                    }
                    continue;

                    //Show Employee availibility
                case 4:
                    reader = new Scanner(System.in);
                    do{
                        System.out.println("Enter employee ID or 'all':");
                        empID = reader.nextLine();
                    }while(!emp.checkEmployeeID(bId,empID)&&!empID.equals("all"));//check valid employee ID

                    //print the employee availability
                    av.printFile(bId,empID);
                    continue;

                    //Add services
                case 5:
                    addNewService(bId);
                    continue;


                case 6:
                    driver.viewBookings(bId);
                    continue;

                    //Adjust business hours
                case 7:
                    reader = new Scanner(System.in);
                    System.out.println("\n+----------------------------------+");
                    System.out.println("|        Current Business          |");
                    System.out.println("|              Hours               |");
                    System.out.println("+----------------------------------+");
                    w.printFile(bId); //display current business hours
                    System.out.println("+----------------------------------+\n");
                    System.out.println("1. Add/Change Business Hours");
                    System.out.println("2. Remove Business Hours");
                    System.out.println("OR Any key to return to business menu");
                    System.out.println("+----------------------------------+\n");
                    String nextChoice;
                    nextChoice = r.nextLine();

                    //user inputs choice
                    if(nextChoice.equals( "1")||nextChoice.equals("2")) {
                        valid = true;
                        while (valid) {
                            do {
                                System.out.println("Enter Day:");
                                day = read.nextLine().toLowerCase();
                            } while (checkDay(day));
                            if(nextChoice.equals( "1")) { //choose to add/change business hours
                                do {
                                    System.out.print("Enter opening time:");
                                    starttime = reader.nextLine();
                                } while (checktime(starttime));
                                do {
                                    System.out.print("Enter closing times:");
                                    endtime = reader.nextLine();
                                } while (checktime(endtime));
                                valid = BHours(bId, day, starttime, endtime);
                            }
                            if(nextChoice.equals( "2")){ //choose to remove business hours
                                w.removeDayFromFile(bId,day);
                                day = day.substring(0,1).toUpperCase() + day.substring(1);
                                System.out.print("Business Hours for "+ day + " removed");
                                valid = false;
                            }
                        }
                        continue;
                    }
                    else{continue;}

                    //Exit system
                case 8:
                    System.out.println("Logging out!");
                    System.exit(0);

            }

            /*
            if(choice == 7){ //function that add's employee availability
                boolean valid = true;
                while(valid) {
                    do {
                        System.out.println("Enter employee ID: ");
                        empID = reader.nextLine();
                    } while (!emp.checkEmployeeID(bId, empID));
                    do {
                        System.out.println("Enter day: ");
                        day = reader.nextLine();
                    } while (checkDay(day));
                    do {
                        System.out.println("Enter start time (hour:minute): ");
                        starttime = reader.nextLine();
                    } while (checktime(starttime));
                    do {
                        System.out.println("Enter end time (hour:minute): ");
                        endtime = reader.nextLine();
                    } while (checktime(endtime));
                    valid = ATimes(empID, day, starttime, endtime);
                    System.out.println("Successfully added new availability.");
                }
                continue;

            }
            if(choice == 9){
                System.out.println("Successfully logged out of the system!");
                System.exit(0);
            }
            else {
                System.out.println("Will do these options later!");
                System.exit(0);
            } */
        }
    }

    /*
     * Add's new service for business
     */
    public void addNewService(String b){

        boolean loop = true;
        while(loop) {
            System.out.println("\n+----------------------------------+");
            System.out.println("|        Current Services          |");
            System.out.println("|            Available             |");
            System.out.println("+----------------------------------+");
            Services s = new Services();
            s.printService(b);
            Scanner reader = new Scanner(System.in);
            System.out.println("\n======================================");
            System.out.println("1. Add Service");//choose employees who can do this service
            System.out.println("2. Remove Service");
            System.out.println("3. Return to business menu");
            System.out.println("======================================\n");
            System.out.println("Enter an option: ");
            while (!reader.hasNextInt()) {
                System.out.println("Error: entered a non integer. Enter a number between 1-8.");
                System.out.print("Enter choice (1-3): ");
                reader.next();
            }
            int choice = reader.nextInt();
            switch (choice) {

                //add Service
                case 1:
                    s.addService(b);
                    continue;
                case 2:
                    s.removeService(b);
                    continue;
                case 3:
                    loop = false;
                default:continue;
            }
        }
    }

    //checks validity of day
    public boolean checkD(String day){
        return checkDay(day);
    }
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

    //check general format
    private boolean checktime(String t){
        try{
            DateFormat time = new SimpleDateFormat("HH:mm");
            time.parse(t);
        }
        catch(ParseException e){
            System.out.println("Invalid time:");
            return true;
        }
        if (!t.contains(":00")&&!t.contains(":30")){System.out.println("In the form HH:30 or HH:00 only");return true;}
        return false;
    }
    public boolean ctime(String t){
        return checktime(t);
    }

    //check valid start and end time
    private boolean timeCheck (String starttime,String endtime){
        DateFormat time = new SimpleDateFormat("HH:mm");
        try{
            Date st = time.parse(starttime);
            Date et = time.parse(endtime);
            // This makes sure scheduled work day CANNOT be before the current time and date, Ending work time must not be before start time or equal.
            if(!et.after(st)){
                System.out.println("Can't start after its ended");
                return true;
            }else if(st.equals(et)){
                System.out.println("Can't start and end at same time");
                return true;
            }
        }catch(ParseException e){
            System.out.println("Invalid Time");
            return true;
        }
        return false;
    }
    public  boolean Workt(String bId,String empId, String day,String starttime,String endtime){return Worktimes(bId,empId,  day, starttime, endtime);}

    //check the time is valid given the constraints
    private boolean Worktimes(String bId, String empId, String day,String starttime,String endtime){
        DateFormat time = new SimpleDateFormat("HH:mm");
        day = day.toLowerCase();
        if( !timeCheck (starttime, endtime)){
            if( av.checkFile(bId,empId,day,starttime,endtime)){return true;}//check against current availability
            if( driver.checkWorktimes(bId,empId,day,starttime,endtime)){return true;}//check against current shifts on this day
            if( w.readWork(bId,day,starttime,endtime)){return true;}//checks based on business hours set


            String name = emp.getEmployeeName(bId,empId);
            System.out.println("Employee: "+ name);
            day = day.substring(0,1).toUpperCase() + day.substring(1);
            System.out.println("Added the working time of: " + day + ":  "+starttime+" - " + endtime);
            driver.addWorkdays(bId,empId,day,starttime,endtime);//adds the new employee shift into workdaysList.txt
            return false;
        }
        return true;
    }
    //checks the business hours are valid and in the correct format
    private boolean BHours(String bId, String day,String starttime,String endtime){
        if( !timeCheck (starttime, endtime)){
            day = day.substring(0,1).toUpperCase() + day.substring(1);
            System.out.println("The working hours of: " + day + ":  "+starttime+" - " + endtime);
            w.readFile(bId, day, starttime, endtime);
            return false;
        }
        return true;
    }

    WriteToFile filewriter = new WriteToFile();

    private boolean ATimes(String empid, String day,String starttime,String endtime){
        if( !timeCheck (starttime, endtime)){
            String combine = empid+" "+day + " "+starttime + " "+ endtime;
            filewriter.WriteToWorkingdayTXT(combine, "employeeAvailabilityList.txt");
            day = day.substring(0,1).toUpperCase() + day.substring(1).toLowerCase();
            System.out.println("New availability time is: " + day + ": " + starttime + "-" + endtime);
            return false;
        }
        return true;
    }

}
