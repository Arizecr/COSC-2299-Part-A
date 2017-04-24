package test;

import bookings.Services;
import coreFunctions.WriteToFile;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yesmi on 21/04/2017.
 */
public class ServicesTesting {
    Services s = new Services();
    @BeforeClass
    public static void setUp() {
        //sample data used during testing
        WriteToFile w = new WriteToFile();
        Services s = new Services();
        w.reWriteToWorkingdayTXT("b1:s1:shampooing:0-30:12", "services.txt");
        w.WriteToWorkingdayTXT("b1:s2:hair cut:0-30:40", "services.txt");
        w.WriteToWorkingdayTXT(  "b1:s3:shave:0-30:10", "services.txt");
        w.WriteToWorkingdayTXT("b1:s4:Women's haircut:0-30:50", "services.txt");
        w.WriteToWorkingdayTXT("b1:s5:Dye hair service:6-30:200", "services.txt");
        s.printService("b1");
    }

    //------------------------------------------------------------------service ID checking
    @Test
    public void checkIDTest(){
        String n = "s1";
    int index = s.checkID(n); //index = 0 indicates an incorrect service number
    assertNotEquals(index,0);

    }
    @Test
    public void checkIDFake(){
        String n = "s001";
        int index = s.checkID(n); //index = 0 indicates an incorrect service number
        assertEquals(index,0);

    }
    @Test
    public void checkIDFake2(){
        String n = "001";
        int index = s.checkID(n); //index = 0 indicates an incorrect service number
        assertEquals(index,0);

    }
    @Test
    public void checkIDFake3(){
        String n = "asdfghjkl001";
        int index = s.checkID(n); //index = 0 indicates an incorrect service number
        assertEquals(index,0);

    }
    @Test
    public void checkIDNull(){
        String n = "";
        int index = s.checkID(n); //index = 0 indicates an incorrect service number
        assertEquals(index,0);

    }


    //--------------------------------------------------------------------------service details checking-----------
    //--------------------------------------name checks
    @Test
    public void checkNameTest() {
        String input = "valid name";
        assertTrue(s.checkName(input));
    }
    @Test
    public void checkNameMINBoundary() {
        String input = "abca";//minimum length of name
        assertTrue(s.checkName(input));
    }//MORE OF THIS TYPE
    @Test
    public void checkNameMAXBoundary() {
        String input = "qwertyuiopQWERTY";//max length of name
        assertTrue(s.checkName(input));
    }
    @Test
    public void checkNameAboveMAXBoundary() {
        String input = "qwertyuiopooWERTY";//above max length of name
        assertFalse(s.checkName(input));
    }
    @Test
    public void checkNameAboveMAXBoundary2() {
        String input = "qwertyuiopxxzER0TY";//above max length of name
        assertFalse(s.checkName(input));
    }
    @Test
    public void checkNameAboveMAXBoundary3() {
        String input = "qwertyuiopoasxaxawWERT3Y";//above max length of name
        assertFalse(s.checkName(input));
    }
    @Test
    public void checkFakeName() {
        String input = "val798id name";
        assertFalse(s.checkName(input));
    }
    @Test
    public void checkFakeName2() {
        String input = "12345678901234567";
        assertFalse(s.checkName(input));
    }
    @Test
    public void checkFakeName3() {
        String input = "one,two";
        assertFalse(s.checkName(input));
    }

    //---------------------------------------time taken format check
    @Test
    public void checkDurTest()  {
        String input = "2-00";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTest2()  {
        String input = "2-0";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMin()  {
        String input = "0-30";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMinBound()  {
        String input = "0-10";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestMinBound2()  {
        String input = "0-1";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestMinBound0()  {
        String input = "0-0";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestAlmostMax()  {
        String input = "7-30";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMax()  {
        String input = "8-00";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMax2()  {
        String input = "8-0";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMax3()  {
        String input = "08-0";
        assertTrue(s.checkDur(input));
    }
    @Test
    public void checkDurTestMaxBound()  {
        String input = "08-01";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestMaxBound2()  {
        String input = "8-30";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestMaxBound3()  {
        String input = "8-3";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestMaxBoundLARGE()  {
        String input = "998-30";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestNotInt()  {
        String input = "0a-a1";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestNotIntWrongForm()  {
        String input = "0aa1";
        assertFalse(s.checkDur(input));
    }
    @Test
    public void checkDurTestNotIntWrongForm2()  {
        String input = "1";
        assertFalse(s.checkDur(input));
    }

}