package yh.gulaboken.session;

import org.junit.BeforeClass;
import org.junit.Test;
import yh.gulaboken.IUser;
import yh.gulaboken.IUserSession;
import yh.gulaboken.userdatabase.HardcodedUserDatabaseFactory;

import static org.junit.Assert.assertEquals;

public class SessionTest {

    private static IUser admin;
    private static IUser guest;
    private static IUserSession session;

    @BeforeClass
    public static void setUp() throws Exception {
        var userDatabase = HardcodedUserDatabaseFactory.create();
        admin = userDatabase.getUser("admin");
        guest = userDatabase.getUser("guest");
        session = UserSessionFactory.create(guest);
    }

    @Test
    public void getAndSetUser() {
        assertEquals("Should be guest.", guest, session.getUser());
        session.setUser(admin);
        assertEquals("Should be admin.", admin, session.getUser());
        session.setUser(null);
        assertEquals("Should be guest.", guest, session.getUser());
    }
}