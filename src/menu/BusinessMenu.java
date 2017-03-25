package menu;
import coreFunctions.Driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Gabrielle on 5/03/2017.
 */
public class BusinessMenu {
    Login login = new Login();
    Driver driver = new Driver();

    public void printMenu(){
        Scanner reader = new Scanner(System.in);
        Scanner datereader = new Scanner(System.in);



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
            System.out.println("2. Add working dates/times");
            System.out.println("3. View summaries of bookings");
            System.out.println("4. View new Bookings");
            System.out.println("5. Show worker availability");
            System.out.println("6. Log out");

            System.out.print("Enter choice: ");
            int choice = reader.nextInt();

            if(choice == 1){
                driver.addEmployee();
                continue;
            }

            if(choice == 2){
                boolean valid = true;
                while(valid){
                    System.out.print("Enter Date (dd/mm/yyyy)");
                    String firstdate = datereader.nextLine();
                    System.out.print("Start Time (hh:mm:ss)");
                    String firsttime = datereader.nextLine();
                    System.out.print("End Time (hh:mm:ss)");
                    String endtime = datereader.nextLine();
                    valid = Worktimes(firstdate,firsttime,endtime);

                }
            }

            else if(choice == 6){
                System.out.println("Successfully logged out of the system!");
                System.exit(0);
            }

            else {
                System.out.println("Will do these options later!");
                System.exit(0);

            }


        }

    }


    private boolean Worktimes(String firstdate,String firsttime,String endtime){
        SimpleDateFormat dateformat2 = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
        String dateNtime = firstdate + " " + firsttime;
        String endNTime = firstdate + " " + endtime;

        try{
            Date magicdate = dateformat2.parse(dateNtime);
            Date enddate = dateformat2.parse(endNTime);

            long diff = enddate.getTime() - magicdate.getTime();
            long diffHours = diff / (60 * 60 * 1000) % 24;

                        /* If needed
                        long diffSeconds = diff / 1000 % 60;
                        long diffMinutes = diff / (60 * 1000) % 60;
                        long diffDays = diff / (24 * 60 * 60 * 1000);
                        */

            if(magicdate.after(enddate) || magicdate.equals(enddate)){


                System.out.println("Impossible dates");
                return true;
            }

            if(diffHours < 1 || diffHours > 7){

                System.out.println("Work day must be in range between 1 to 8 hours");
                return true;

            }



            System.out.println("The working time of:\t\t\t " + magicdate + "\nTil:\t\t\t\t\t\t\t " + enddate);

            driver.addWorkdays(dateNtime, endNTime);
            return false;

        }catch(ParseException e){
            System.out.println("Invalid Date/Time");
            return true;


        }

        }
    public  boolean Workt(String firstdate,String firsttime,String endtime){return Worktimes(firstdate,firsttime,endtime);}


}
