package menu;

import BusinessWorkDays.Workday;
import coreFunctions.Driver;
import user.Employee;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



/**
 * Created by Gabrielle on 5/03/2017.
 * test
 */
public class BusinessMenu {
    Login login = new Login();
    Workday w = new Workday();
    Driver driver = new Driver();
    Employee emp = new Employee();


    public void printMenu(String bId){
        Scanner reader = new Scanner(System.in);
        Scanner read = new Scanner(System.in);
        Scanner r = new Scanner(System.in);
        Scanner eID = new Scanner(System.in);
        String starttime;
        String endtime;
        String day;
        String empID;



        //infinite loop
        while(true) {
            //print business menu
            System.out.println("\n+----------------------------------+");
            System.out.println("|           Business               |");
            System.out.println("|              menu                |");
            System.out.println("+----------------------------------+");

            System.out.println("1. Add Employee");
            System.out.println("2. Add working days/times for Employee");
            System.out.println("3. Remove/edit working days/times for Employee");
            System.out.println("4. View summaries of bookings");
            System.out.println("5. View new Bookings");
            System.out.println("6. Show worker availability");
            System.out.println("7. View business hours");
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
                        }while(!emp.checkEmployeeID(bId,empID));

                        do {
                            System.out.println("Enter Day:");
                            day = read.nextLine().toLowerCase();
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
                continue;
            }
            else if(choice == 7){
                System.out.println("\n+----------------------------------+");
                System.out.println("|           Business               |");
                System.out.println("|              Hours                |");
                System.out.println("+----------------------------------+\n");
                w.printFile(bId);
                System.out.println("\n1. Add/Change Business Hours");
                System.out.println("OR\nAny key to return to business menu");

                String nextChoice;
                nextChoice = r.nextLine();
                if(nextChoice.equals( "1" )) {
                    boolean valid = true;

                    while (valid) {


                        do {
                            System.out.println("Enter Day:");
                            day = read.nextLine().toLowerCase();
                        } while (checkDay(day));
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
                    continue;
                }
                else{continue;}

            }
            if(choice == 9){
                System.out.println("--------- New Service---------");
                addNewService();
                continue;
            }

            if(choice == 8){
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
  /*  public boolean checkOpen(String bId, String day,String starttime,String endtime){
      if( w.readWork(bId,day,starttime,endtime)){return true;};
        System.out.println("employee ID invalid");
     return false;
    }*/
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
    private boolean timeCheck (String starttime,String endtime){
        DateFormat time = new SimpleDateFormat("HH:mm");

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

        }catch(ParseException e){
            System.out.println("Invalid Time");
            return true;

        }

        return false;
    }
    private boolean Worktimes(String bId, String empId, String day,String starttime,String endtime){
        DateFormat time = new SimpleDateFormat("HH:mm");
       if( !timeCheck (starttime, endtime)){
           if( !w.readWork(bId,day,starttime,endtime)){return true;}

            System.out.println("The working time of: " + day + ":  "+starttime+" - " + endtime);

            driver.addWorkdays(bId,empId,day,starttime,endtime);
            return false;


        }

        return true;

    }
    private boolean BHours(String bId, String day,String starttime,String endtime){

        if( !timeCheck (starttime, endtime)){

            System.out.println("The working hours of: " + day + ":  "+starttime+" - " + endtime);

            w.readFile(bId, day, starttime, endtime);
            return false;
        }
        return true;
    }
    public  boolean Workt(String bId,String empId, String day,String starttime,String endtime){return Worktimes(bId,empId,  day, starttime, endtime);}


}
