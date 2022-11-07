package yh.gulaboken;

import yh.gulaboken.filedatabase.FileContactDatabaseFactory;
import yh.gulaboken.menus.MainMenu;
import yh.gulaboken.session.UserSessionFactory;
import yh.gulaboken.userdatabase.HardcodedUserDatabaseFactory;
import yh.gulaboken.utils.ConsoleLineReader;

import java.io.File;

public class Application implements IAppContext {
    private static final String FILE_DATABASE = "contacts.json";
    private final IUserDatabase userDatabase;
    private final IContactDatabase contactDatabase;
    private final IUserSession session;
    private final ILineReader reader;

    /**
     * Application constructor.
     * Initialize context.
     */
    Application() {
        userDatabase = HardcodedUserDatabaseFactory.create();
        contactDatabase = FileContactDatabaseFactory.create(new File(FILE_DATABASE));
        session = UserSessionFactory.create(userDatabase.getUser("guest"));
        reader = new ConsoleLineReader();
    }

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        // create app instance
        var app = new Application();
        // create menu handler instance with app context
        var mainMenu = new MainMenu(app);
        // show main menu
        mainMenu.show();
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
    public IUserSession getSession() {
        return session;
    }

    @Override
    public ILineReader getLineReader() {
        return reader;
    }
}