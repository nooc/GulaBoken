package yh.gulaboken;

import org.junit.Test;
import yh.gulaboken.filedatabase.FileContactDatabaseFactory;
import yh.gulaboken.session.UserSessionFactory;
import yh.gulaboken.userdatabase.HardcodedUserDatabaseFactory;
import yh.gulaboken.utils.ConsoleLineReader;

import java.io.File;

import static org.junit.Assert.*;

public class ApplicationTest {

    class TestApplication implements IAppContext {

        IUserDatabase uData;
        IContactDatabase cData;
        IUserSession session;
        ILineReader reader;

        TestApplication() {
            uData = HardcodedUserDatabaseFactory.create();
            cData = FileContactDatabaseFactory.create(new File("test.json"));
            session = UserSessionFactory.create(uData.getUser("guest"));
            reader = new ConsoleLineReader();
        }

        @Override
        public IContactDatabase getContactDatabase() {
            return null;
        }

        @Override
        public IUserDatabase getUserDatabase() {
            return null;
        }

        @Override
        public IUserSession getSession() {
            return null;
        }

        @Override
        public ILineReader getLineReader() {
            return null;
        }
    }

    @Test
    public void main() {


    }
}