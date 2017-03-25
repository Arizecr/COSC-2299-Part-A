package test;
import coreFunctions.Driver;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Created by yesmi on 25/03/2017.
 */
public class AddEmployeeTesting {

    Driver driver = new Driver();
    int details;
    String name = "Employee";

    @BeforeClass
    public static void loadDriver(){

    }
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
    @Test // tfn and phone no should be restricted to a specific format
    public void correctNewEmployee9() {
        int tfn = 123456789;
        details = driver.verifyEmployeeTFN(tfn);
        assertEquals(details,tfn);
    }
    @Test // tfn and phone no should be restricted to a specific format
    public void correctNewEmployee8() {
        int tfn = 12345678;
        details = driver.verifyEmployeeTFN(tfn);
        assertEquals(details,tfn);
    }

    @Test
    public void correctUppBoundEmployeeMobile() {
        int data = 1234567890;
        details = driver.verifyEmployeeMobile(data);
        assertEquals(details,data);
    }

/*

    @Test // tfn and phone no should be restricted to a specific format
    public void correctNewEmployee8() {
        details = driver.VerifyEmployee("12345678","0393345678");
        assertFalse(details);
    }

    @Test
    public void correctUppBoundEmployeeNO() {
        details = driver.VerifyEmployee("123456789","1234567890");
        assertFalse(details);
    }

    @Test//not a number
    public void fakeNewEmployeeTFN() {
        details = driver.VerifyEmployee("123a67890","1234567890");
        assertTrue(details);
    }
    @Test //not a number
    public void fakeNewEmployeeNO() {
        details = driver.VerifyEmployee("123456789","04sascgf");
        assertTrue(details);
    }
    @Test
    public void fakeNewEmployeeBoth() {
        details = driver.VerifyEmployee("1234h567890","qwe");
        assertTrue(details);
    }
    @Test
    public void UppBoundNewEmployeeTFN() {
        details = driver.VerifyEmployee("12345678908","1234567890");
        assertTrue(details);
    }
    @Test
    public void UppBoundEmployeeNO() {
        details = driver.VerifyEmployee("123456789","12345678900");
        assertTrue(details);
    }
    @Test
    public void UppBoundNewEmployeeTFN2() {
        details = driver.VerifyEmployee("123456789082","1234567890");
        assertTrue(details);
    }
    @Test
    public void UppBoundEmployeeNO2() {
        details = driver.VerifyEmployee("123456789","123456789002");
        assertTrue(details);
    }
    @Test
    public void UppBoundNewEmployeeTFN3() {
        details = driver.VerifyEmployee("1234567890823","1234567890");
        assertTrue(details);
    }
    @Test
    public void UppBoundEmployeeNO3() {
        details = driver.VerifyEmployee("123456789","1234567890023");
        assertTrue(details);
    }
    @Test
    public void NullNewEmployeeTFN() {
        details = driver.VerifyEmployee("","1234567890");
        assertTrue(details);
    }
    @Test
    public void NullNewEmployeeNO() {
        details = driver.VerifyEmployee("1234567890","");
        assertTrue(details);
    }

*/
}