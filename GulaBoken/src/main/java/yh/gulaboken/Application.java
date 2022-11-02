package yh.gulaboken;

import yh.gulaboken.filedatabase.FileContactDatabaseFactory;
import yh.gulaboken.session.SessionFactory;
import yh.gulaboken.userdatabase.HardcodedUserDatabaseFactory;

import java.io.File;
import java.util.Scanner;

public class Application implements IAppContext {
    private static final String FILE_DATABASE = "contacts.json";
    private final Scanner scanner;
    private final IUserDatabase userDatabase;
    private final IContactDatabase contactDatabase;
    private final ISession session;

    /**
     * Application constructor.
     * Initialize context.
     */
    Application() {
        scanner = new Scanner(System.in);
        userDatabase = HardcodedUserDatabaseFactory.create();
        contactDatabase = FileContactDatabaseFactory.create(new File(FILE_DATABASE));
        session = SessionFactory.create();
    }

    /**
     * Main function
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
    public Scanner getScanner() {
        return scanner;
    }
}