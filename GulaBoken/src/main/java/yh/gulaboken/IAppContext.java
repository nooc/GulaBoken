package yh.gulaboken;

import java.util.Scanner;

public interface IAppContext {
    /**
     * Get contact database
     * @return contact database
     */
    IContactDatabase getContactDatabase();
    /**
     * Get user database
     * @return user database
     */
    IUserDatabase getUserDatabase();
    /**
     * Get session
     * @return session
     */
    ISession getSession();
    /**
     * Get scanner
     * @return scanner
     */
    Scanner getScanner();
}
