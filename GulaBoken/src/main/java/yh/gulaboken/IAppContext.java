package yh.gulaboken;

import java.util.Scanner;

public interface IAppContext {
    IContactDatabase getContactDatabase();
    IUserDatabase getUserDatabase();
    ISession getSession();
    Scanner getScanner();
}
