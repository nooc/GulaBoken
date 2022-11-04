package yh.gulaboken;

import javax.validation.constraints.NotNull;

public interface IAppContext {
    /**
     * Get contact database
     *
     * @return contact database
     */
    @NotNull
    IContactDatabase getContactDatabase();

    /**
     * Get user database
     *
     * @return user database
     */
    @NotNull
    IUserDatabase getUserDatabase();

    /**
     * Get session
     *
     * @return session
     */
    @NotNull
    ISession getSession();

    /**
     * Get command line reader.
     *
     * @return ILineReader instance
     */
    @NotNull
    ILineReader getLineReader();
}
