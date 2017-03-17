package Test;

import Menu.Login;
import Menu.Register;
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yesmi on 10/03/2017.
 */

public class RegisterTesting {
    Register reg = new Register();
    private int incorrect = 1; //
    private int correct = 0;
    @BeforeClass
    public static void loadUsers(){
        Login log = new Login();
        log.loadCustomerInformation();
    }

    @Test
    public void correctUsername() {
        String u = "c123456";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(correct,value);
    }

    @Test // expect  username to be larger than just c
    public void oneCharCUsername() {
        String u = "c";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(incorrect,value);
    }
    @Test // expect  username to be larger than just c
    public void invalidCharUsername() {

        String u = "a";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(incorrect,value);
    }
    @Test // expect  username to be larger than just c
    public void invalidUsername() {

        String u = "abc123";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(incorrect,value);
    }
    @Test
    public void invalidUsernameUppBound() {

        String u = "abcdef0123456789";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(incorrect,value);
    }

    @Test //fixed in code
    public void passwordNull() {
        String u = "c1gsy";
        String p = null;
        int value = reg.testReg(u,p);
        assertEquals(incorrect,value);
    }
    @Test
    public void passwordNullUppBound() {
        String u = "c1gsyc1gsy111256";
        String p = null;
        int value = reg.testReg(u,p);
        assert(correct != value);
    }

    @Test //(expected = ArrayIndexOutOfBoundsException.class)
    public void registerMenuTest() {
    }


    @Test
    public void sameUsername() {
        String u = "c12";
        String p = "123";
        int value = reg.testReg(u,p);
        assertEquals(incorrect, value);
    }
    @Test // length 16
    public void invalidUsernameBound() {

        String u = "c123456789101112";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(incorrect,value);
    }
    @Test // length 15
    public void validUsernameBound() {

        String u = "c12345678910112";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(correct,value);
    }
    @Test // length 14
    public void validUsernameBound2() {

        String u = "c1234978910112";
        String p = "passwooord";
        int value = reg.testReg(u,p);
        assertEquals(correct,value);
    }
    @Test // length 16
    public void invalidUsernameBound2() {

        String u = "cccccccccccccccc";
        String p = "passwooord";
        int value = reg.testReg(u,p);
        assertEquals(incorrect,value);
    }
    @Test // length 16
    public void invalidUsernameBoundLarge() {

        String u = "c12345678910111288888888888888888888888888888888888888888888888";
        String p = "password";
        int value = reg.testReg(u,p);
        assertEquals(incorrect,value);
    }
    @Test // length 14
    public void validUsernameBound3() {

        String u = "ccccccc8910112";
        String p = "passwooord";
        int value = reg.testReg(u,p);
        assertEquals(correct,value);
    }
    @Test // length 16
    public void invalidUsernameBound3() {

        String u = "bbc1234978910112";
        String p = "passwooord";
        int value = reg.testReg(u,p);
        assertEquals(incorrect,value);
    }
}