package EmployeeAvailabilityDays;

import BusinessWorkDays.Workday;
import coreFunctions.Driver;
import coreFunctions.WriteToFile;
import menu.BusinessMenu;
import test.Logging;
import user.Employee;

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by asus on 30-Mar-17.
 */
public class AvailableDay {

    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    WriteToFile write = new WriteToFile();
    Driver drive = new Driver();

    private String starttime;
    private String endtime;
    private String employeeid;
    private String workday;
    private String bid;

    public AvailableDay(){};

    Employee e = new Employee();
    //list for worker availability
    public static ArrayList<String> availability = new ArrayList<>();
    Logging l =new Logging();

    /*
     * Load list for worker availability
     */
    public void loadInfo(){
        availability = new ArrayList<>();


        BufferedReader br;
        try {

            br = new BufferedReader(new FileReader("employeeAvailabilityList.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    availability.add(x);
                }
                //prints error
            } catch (IOException error) {
                //error.printStackTrace();
                l.Logging();
                LOGGER.log(Level.WARNING,error.toString(),error);
            }
            //file cannot be found
        } catch (FileNotFoundException error) {
            l.Logging();
           // System.out.println(error);
            LOGGER.log(Level.WARNING,error.toString(),error);
        }

    }

    /*
     * Print worker availability
     */
    public void printFile(String bId,String eId){
        loadInfo();
        String bID= "" ;
        String eID= "" ;
        String day ="" ;
        String starttime ="";
        String endtime="";
        String name = "";

        for(int i=0; i < availability.size() ;i++){

            String Details[] = availability.get(i).split(" ",5);
            bID = Details[0];
            eID = Details[1];
            day = Details[2];
            starttime = Details[3];
            endtime = Details[4];
            name = e.getEmployeeName(bId,eID);
            if(bID.equals(bId)&&eID.equals(eId)){System.out.println(day+" " + starttime +" to  "+ endtime );}
            if((bID.equals(bId)&&eId.equals("all"))){System.out.println(name+" ("+ eID +"): "+day+" " + starttime +" to  "+ endtime );}

        }
    }
    public void addEmployeeAvailability(String bId,String employeeID){
        loadInfo();
        BusinessMenu b = new BusinessMenu();
        String workday;
        Workday w = new Workday();
        Scanner reader = new Scanner(System.in);
        do {
            do {
                System.out.print("Enter Day: ");
                workday = reader.nextLine().toLowerCase();
            } while (b.checkD(workday));

            //add/edit employee shifts


            do {
                System.out.print("Enter shift start time (hour:min): ");
                starttime = reader.nextLine();
            } while (b.ctime(starttime)); //check validity of start time
            do {
                System.out.print("Enter shift end time (hour:min): ");
                endtime = reader.nextLine();
            } while (b.ctime(endtime)); //check validity of end time
        }while( w.readWork(bId,workday,starttime,endtime));
        String x = bId+ " " +employeeID + " " + workday + " " + starttime + " " + endtime;
        availability.add(x);
        if(availability.size()==0){write.reWriteToWorkingdayTXT(x, "employeeAvailabilityList.txt");}
       else{
            write.WriteToWorkingdayTXT(x, "employeeAvailabilityList.txt");
        }
    }


    /*
     *
     */
    //check valid and not already existing
    public boolean checkFile(String b,String emp, String d,String st,String en)
    {
        loadInfo();
        String bID= "" ;
        String empid= "" ;
        String day ="" ;
        String start ="";
        String end="";
        int count = 0;
        int inFile = 0;
        for(int i=0; i < availability.size() ;i++){

            String Details[] = availability.get(i).split(" ",5);
            bID = Details[0];
            empid = Details[1];
            day = Details[2].toLowerCase();
            start = Details[3];
            end = Details[4];
            if(bID.equals(b)&&empid.equals(emp)&&day.equals(d)){
                DateFormat time = new SimpleDateFormat("HH:mm");
                inFile++;
                try {
                    Date sd = time.parse(st);
                    Date ed = time.parse(en);
                    Date Asd = time.parse(start);
                    Date Aed = time.parse(end);

                    // This makes sure scheduled employee shift is within operating availability of business
                    if (ed.after(Aed)) {
                        System.out.println("Error: this shift is not within the employees availability");
                        count++;

                    } else if (sd.before(Asd)) {
                        System.out.println("Error: this shift is not within the employees availability");
                        count++;

                    }
                 //   else {return false;}
                } catch (ParseException e) {
                    System.out.println("Invalid Time");
                    count ++;
                }
            }

        }
        if(inFile==0){
            System.out.println("Error: This employee is unavailable on this day & time");
            return true;
        }

        if(count>0){return true;}//invalid availability exists
        return false;
    }


    public String toString(){

        String format = bid + "" + employeeid + " " + workday + " " + starttime + " " + endtime;

        return format;
    }



}
