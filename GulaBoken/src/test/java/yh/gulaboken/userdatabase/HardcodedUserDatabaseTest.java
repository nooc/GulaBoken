package yh.gulaboken.userdatabase;

import org.junit.BeforeClass;
import org.junit.Test;
import yh.gulaboken.IUserDatabase;

import static org.junit.Assert.*;

/**
 * Test HardcodedUserDatabase
 */
public class HardcodedUserDatabaseTest {

    private static IUserDatabase database;

    @BeforeClass
    public static void setUp() {
        database = HardcodedUserDatabaseFactory.create();
    }

    @Test
    public void getUser() {
        assertNull("Should be null.", database.getUser("foo"));
        assertNotNull("Should be non-null.", database.getUser("guest"));
        assertNotNull("Should be non-null.", database.getUser("admin"));
    }

    @Test
    public void authenticate() {
        assertNull("Should be null.", database.authenticate("admin",null));
        assertNotNull("Should be non-null.", database.authenticate("guest",null));
        assertNotNull("Should be non-null.", database.authenticate("admin","secret"));
    }
}
