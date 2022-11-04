package yh.gulaboken;

import yh.gulaboken.filedatabase.FileContactDatabaseFactory;
import yh.gulaboken.session.SessionFactory;
import yh.gulaboken.userdatabase.HardcodedUserDatabaseFactory;
import yh.gulaboken.utils.ConsoleLineReader;

import java.io.File;

public class Application implements IAppContext {
    private static final String FILE_DATABASE = "contacts.json";
    private final IUserDatabase userDatabase;
    private final IContactDatabase contactDatabase;
    private final ISession session;
    private final ILineReader reader;

    /**
     * Application constructor.
     * Initialize context.
     */
    Application() {
        userDatabase = HardcodedUserDatabaseFactory.create();
        contactDatabase = FileContactDatabaseFactory.create(new File(FILE_DATABASE));
        session = SessionFactory.create(userDatabase.getUser("guest"));
        reader = new ConsoleLineReader();
    }

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        new MenuHandler(new Application()).mainMenu();
    }

    @Override
    public IContactDatabase getContactDatabase() {
        return contactDatabase;
    }

    @Override
    public IUserDatabase getUserDatabase() {
        return userDatabase;
    }

    @Override
    public ISession getSession() {
        return session;
    }

    @Override
    public ILineReader getLineReader() {
        return reader;
    }
}