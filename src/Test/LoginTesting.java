package Test;

import Menu.Login;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by yesmi on 10/03/2017.
 */
public class LoginTesting {
    Login login = new Login();
    boolean toVerify = true;
    @BeforeClass
    public static void loadUsers(){
        Login log = new Login();
        log.loadCustomerInformation();
        log.getOwnerinfo();
    }
////////////////////////////////////user testing
    @Test
    public void verifyLoginAll() {
        toVerify =  login.getVerification("customer","c1","123");
        assertTrue(toVerify);
    }

    @Test //invalid Type + correct User Details
    public void IncorrectUN() {
        toVerify =  login.getVerification("c","c1","123");
        assertFalse(toVerify);
    }
    @Test
    public void IncorrectUNBoundary() {
        toVerify =  login.getVerification("c","c0000000000000000000000000000000001","123");
        assertFalse(toVerify);
    }

    @Test
    public void IncorrectPW() {
        toVerify =  login.getVerification("customer","c1","c");
        assertFalse(toVerify);
    }
    ////////////////////////////////////business owner testing
    @Test //invalid Type + User Details
    public void verifyLoginAllB() {
        toVerify =  login.getVerification("business","b1","123");
        assertTrue(toVerify);
    }

    @Test
    public void IncorrectUNB() {
        toVerify =  login.getVerification("business","b1notrealusername","123");
        assertFalse(toVerify);
    }

    @Test
    public void IncorrectPWB() {
        toVerify =  login.getVerification("business","b1","fakepassword");
        assertFalse(toVerify);
    }

    /////////////////////////////////////////should fail once addressed in code
    @Test (expected = StringIndexOutOfBoundsException.class)
    public void errorTest1() {
        login.testLogin("","fake");
    }
    @Test (expected = StringIndexOutOfBoundsException.class)
    public void errorTest2() {
        login.testLogin("","");
    }
}