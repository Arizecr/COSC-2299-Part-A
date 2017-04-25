package user;

import test.Logging;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Gabrielle on 24/03/2017.
 */
public class Employee {
    private static final Logger LOGGER = Logger.getLogger(Logging.class.getName());
    private String employeeID;
    private String businessId;
    private String fullName;
    private String taxFileNo;
    private String phoneNo;
    public static ArrayList<Employee> employeeList = new ArrayList<>();
    Logging l = new Logging();

    public Employee() {
    }

    public Employee(String businessId, String employeeID, String fullName, String taxFileNo, String phoneNo) {
        this.employeeID = employeeID;
        this.businessId = businessId;
        this.fullName = fullName;
        this.taxFileNo = taxFileNo;
        this.phoneNo = phoneNo;
    }

    public String toString() {

        return businessId + ":" + employeeID + ":" + fullName + ":" + taxFileNo + ":" + phoneNo;
    }

    public String geteId() {

        return employeeID;
    }

    public String getbId() {

        return businessId;
    }

    public String getName() {

        return fullName;
    }

    public void loadEmployeeInformation() {
        employeeList = new ArrayList<>();
        BufferedReader br;
        try {


            br = new BufferedReader(new FileReader("employeeList.txt"));

            try {
                String x;
                while ((x = br.readLine()) != null) {
                    // printing out each line in the file
                    String Details[] = x.split(":", 5);
                    String bId = Details[0];
                    String empID = Details[1];
                    String fullName = Details[2];
                    String TFN = Details[3];
                    String phoneNo = Details[4];
                    Employee e = new Employee(bId, empID, fullName, TFN, phoneNo);
                    employeeList.add(e);
                }
                //prints error
            } catch (IOException e) {
                l.Logging();
                LOGGER.log(Level.WARNING,e.toString(),e);
            }
            catch (ArrayIndexOutOfBoundsException ae) {
                //e.printStackTrace();
                System.out.println("Error encountered");
                l.Logging();
                LOGGER.log(Level.SEVERE,ae.toString(),ae);

            }


            //file cannot be found
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            //e.printStackTrace();
            l.Logging();
            LOGGER.log(Level.WARNING,e.toString(),e);
        }

    }

    public void printEmployeeInformation(String bID) {
        loadEmployeeInformation();
        System.out.println("Current Employees");
        System.out.println("_________________");
        for(int i=0; i < employeeList.size() ;i++){
            if(bID.equals(employeeList.get(i).businessId))
                System.out.println(employeeList.get(i).employeeID+" : "+employeeList.get(i).fullName);


        }
        System.out.println("_________________\n");

    }
    //prints error


    public boolean checkEmployeeID(String bId, String empID) {

        loadEmployeeInformation();

        for (int i = 0; i < employeeList.size(); i++) {
            if (empID.equals(employeeList.get(i).geteId())) {
                if (bId.equals(employeeList.get(i).getbId())) {
                    return true;
                }
            }

        }
        if(!empID.equals("all")){
            System.out.println("employee ID invalid");}
        return false;

    }
    public String getEmployeeName(String bId, String empID) {

        loadEmployeeInformation();

        for (int i = 0; i < employeeList.size(); i++) {
            if (empID.equals(employeeList.get(i).geteId())) {
                if (bId.equals(employeeList.get(i).getbId())) {
                    return employeeList.get(i).getName();
                }
            }

        }
        return null;

    }

}
