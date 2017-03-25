package test;
import coreFunctions.Driver;
import org.junit.*;

import static org.junit.Assert.*;


/**
 * Created by yesmi on 25/03/2017.
 */
public class AddEmployeeTesting {

    Driver driver = new Driver();
    Boolean details;

    /*-------------------correct-----------------------*/
    @Test // tfn and phone no should be restricted to a specific format
    public void correctNewTFN9() {
        details = driver.verifyEmployeeTFN("123456789");
        assertFalse(details);
    }
    @Test // tfn and phone no should be restricted to a specific format
    public void correctNewTFN8() {
        details = driver.verifyEmployeeTFN("12345678");
        assertFalse(details);
    }

    @Test
    public void correctName() {
        details = driver.verifyEmployeeName("employee");
        assertFalse(details);
    }
    @Test
    public void correctEmployeeNO() {
        details = driver.verifyEmployeeMobile("0412345678");
        assertFalse(details);
    }
    /*------------incorrect format------------------------*/
    @Test//not a number
    public void fakeNewEmployeeTFN() {
        details = driver.verifyEmployeeTFN("123a67890");
        assertTrue(details);
    }
    @Test //not a number
    public void fakeNewEmployeeNO() {
        details = driver.verifyEmployeeMobile("04sascgf");
        assertTrue(details);
    }
    /*----------------------------upper bound testing---------------------------------*/

    @Test
    public void UppBoundNewEmployeeTFN() {
        details = driver.verifyEmployeeTFN("12345678908");
        assertTrue(details);
    }

    @Test
    public void UppBoundNewEmployeeTFN2() {
        details = driver.verifyEmployeeTFN("123456789082");
        assertTrue(details);
    }
    @Test
    public void UppBoundNewEmployeeTFN3() {
        details = driver.verifyEmployeeTFN("1234567890823");
        assertTrue(details);
    }
    @Test
    public void UppBoundEmployeeNO() {
        details = driver.verifyEmployeeMobile("12345678900");
        assertTrue(details);
    }

    @Test
    public void UppBoundEmployeeNO2() {
        details = driver.verifyEmployeeMobile("123456789002");
        assertTrue(details);
    }

    @Test
    public void UppBoundEmployeeNO3() {
        details = driver.verifyEmployeeMobile("1234567890023");
        assertTrue(details);
    }

    @Test
    public void UppBoundName1() {
        details = driver.verifyEmployeeName("qwertyuiopasdfghjklzxcvbnm,qwertyuioasdfghjzxcvb");
        assertTrue(details);
    }
    @Test
    public void UppBoundName2() {
        details = driver.verifyEmployeeName("qwertyuiopasdfghjklzx");
        assertTrue(details);
    }
    @Test
    public void UppBoundName3() {
        details = driver.verifyEmployeeName("qwertyuiopasdfghjklzqx");
        assertTrue(details);
    }
    /*-----------------------null value tesing---------------*/
    @Test
    public void NullNewEmployeeTFN() {
        details = driver.verifyEmployeeTFN("");
        assertTrue(details);
    }
    @Test
    public void NullNewEmployeeNO() {
        details = driver.verifyEmployeeMobile("");
        assertTrue(details);
    }
    @Test
    public void NullNewEmployeeName() {
        details = driver.verifyEmployeeName("");
        assertTrue(details);
    }


}