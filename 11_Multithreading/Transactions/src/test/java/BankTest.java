import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


public class BankTest extends TestCase {

    HashMap<String, Account> accounts = new HashMap<>();
    ConcurrentHashMap<String, Account> lockedAccounts;
    Bank bank;
    Random random = new Random();
    long OPERATION_LIMIT = 50000L;
    Account a1, a2, a3, a4, a5;

    public BankTest() {
        super();
    }

    @Before
    public void setUp() throws Exception {

        a1 = new Account(60000, "a1");
        a2 = new Account(60000, "a2");
        a3 = new Account(60000, "a3");
        a4 = new Account(60000, "a4");
        a5 = new Account(60000, "a5");
        accounts.put("a1", a1);
        accounts.put("a2", a2);
        accounts.put("a3", a3);
        accounts.put("a4", a4);
        accounts.put("a5", a5);

        /*for (int i = 1; i < 6; i++) {
            accounts.put("a"+i, new Account(60000, "a"+i));
        }*/
        bank = new Bank(accounts);
        System.out.println(accounts);
    }
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testGetBalance(){
        long actual = accounts.get("a1").getMoney();
        System.out.println(actual);
        long expected = 60000;
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testTransfer() throws InterruptedException {
        bank.transfer("a2", "a4", 30000);
        long actual1 = bank.getAccounts().get("a2").getMoney();
//        long actual2 = accounts.get("a4").getMoney();
        long expected1 = 30000;
//        long expected2 = 90000;
        assertEquals(expected1, actual1, 0);
//        assertEquals(expected2, actual2, 0);
    }

}
