package yh.gulaboken;

import yh.gulaboken.filedatabase.FileContactDatabaseFactory;
import yh.gulaboken.session.UserSessionFactory;
import yh.gulaboken.userdatabase.HardcodedUserDatabaseFactory;

import java.io.File;

class TestApplication implements IAppContext {

    private final IUserDatabase userDatabase;
    private final IContactDatabase contactDatabase;
    private final IUserSession session;
    private final ILineReader reader;

    /**
     * Simulation run.
     * @param simulation List of commands to execute.
     */
    TestApplication(String[] simulation) {
        userDatabase = HardcodedUserDatabaseFactory.create();
        contactDatabase = FileContactDatabaseFactory.create(new File("test.json"));
        session = UserSessionFactory.create(userDatabase.getUser("guest"));
        reader = new FakeReader(simulation);
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
