package BusinessWorkDays;

import coreFunctions.*;
import test.Logging;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Martin on 28/03/2017.
 */
public class Workday
{
    private static final Logger LOGGER = Logger.getLogger( Logging.class.getName() );
    Logging l =new Logging();
    public static ArrayList<Workday> workhours = new ArrayList<>();
    WriteToFile write = new WriteToFile();
    Driver drive = new Driver();
    private String starttime;
    private String endtime;
    private String businessid;
    private String workday;

    public Workday(){}
    public Workday(String businessid, String workday, String starttime, String endtime){
        this.businessid = businessid;
        this.workday = workday;
        this.starttime = starttime;
        this.endtime = endtime;
    }
    public String workD(){
        return workday;
    }
    public String getStarttime(){
        return starttime;
    }
    public String getEndtime(){
        return endtime;
    }
    public String getBId(){
        return businessid;
    }

    public void Details(){
        workhours = new ArrayList<>();
        BufferedReader br;
        String bId= "" ;
        String day ="" ;
        String starttime = "" ;
        String endtime = "";
        try {
            br = new BufferedReader(new FileReader("businessdaysList.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String Details[] = x.split(" ",4);
                    bId = Details[0];
                    day = Details[1].toLowerCase();
                    starttime = Details[2];
                    endtime = Details[3];
                    Workday n = new Workday(bId,day,starttime,endtime);
                    workhours.add(n);
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
            //System.out.println(e);
            //e.printStackTrace();
            l.Logging();
            LOGGER.log(Level.SEVERE,e.toString(),e);
        }
    }

    public void readFile(String b,String d,String s,String end){
        Details();    //read the business hours from file
        int num = 0;

        Workday n = new Workday(b,d,s,end);
        for(int i=0; i < workhours.size() ;i++){
            if(b.equals(workhours.get(i).getBId())){
                if(d.equals(workhours.get(i).workD().toLowerCase())){
                    num ++;
                    workhours.set(i,n);
                    System.out.println("Business Hours For "+d+" have been updated");
                }
            }
        }
        if (num>0){
            //this removes the employee worktimes outside the business hours
            drive.loadandWriteNEmployeeWorktimes( b, d, s,end);
        }
        else{System.out.println("Business Hours For "+d+" have been added"); workhours.add(n);}
        rewriteToFile(workhours);

    }
    public void removeDayFromFile(String b,String d){
        Details();
        for(int i=0; i < workhours.size() ;i++){
            if(b.equals(workhours.get(i).getBId())){
                if(d.equals(workhours.get(i).workD().toLowerCase())){
                    workhours.remove(i);
                    drive.removeWorktimes( b, d);
                    System.out.print("Business Hours for "+ d + " removed");
                    rewriteToFile(workhours);
                }
            }
        }
    }


    public boolean readWork(String b,String d,String s,String end){
        Details();
        //------------------------------------------------------check if date already exists
        int count = 0;
        for(int i=0; i < workhours.size() ;i++) {
            if (b.equals(workhours.get(i).getBId())) {
                if (d.equals(workhours.get(i).workD().toLowerCase())) {
                    DateFormat time = new SimpleDateFormat("HH:mm");
                    count++;
                    try {
                        Date st = time.parse(s);
                        Date et = time.parse(end);
                        Date Bst = time.parse(workhours.get(i).getStarttime());
                        Date Bet = time.parse(workhours.get(i).getEndtime());

                        // This makes sure scheduled employee shift is within operating hours of business
                        if (et.after(Bet)) {
                            System.out.println("Error: this shift is not within the operating hours of the business");
                            return true;

                        } else if (st.before(Bst)) {
                            System.out.println("Error: this shift is not within the operating hours of the business");
                            return true;
                        }
                    } catch (ParseException e) {
                        System.out.println("Error: Invalid Time. Example of valid time, 9:00 aka 9am");
                        return true;
                    }
                }
            }
        }

        if(count==0){System.out.println("Not Open on " + d);return true;}
        return false;
    }

    public void printFile(String realbId){
        BufferedReader br;
        String bId= "" ;
        String day ="" ;
        String starttime ="";
        String endtime="";
        try {
            br = new BufferedReader(new FileReader("businessdaysList.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    String Details[] = x.split(" ",4);
                    bId = Details[0];
                    day = Details[1];
                    starttime = Details[2];
                    endtime = Details[3];
                    day = day.substring(0,1).toUpperCase() + day.substring(1);
                    if(realbId.equals(bId)){System.out.println(day+" " + starttime +" to  "+ endtime );}
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
            //System.out.println(e);
            //e.printStackTrace();
            l.Logging();
            LOGGER.log(Level.WARNING,e.toString(),e);
        }
    }

    public void rewriteToFile( ArrayList workhours){
        if(workhours.size()>=0){write.reWriteToWorkingdayTXT(workhours.get(0).toString(), "businessdaysList.txt");}
        for(int i=1; i < workhours.size() ;i++){
            write.WriteToWorkingdayTXT(workhours.get(i).toString(), "businessdaysList.txt");
        }
    }

    public String toString(){
        String format = businessid + " " + workday + " " + starttime + " " + endtime;
        return format;
    }
}
