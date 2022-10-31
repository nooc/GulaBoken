package yh.gulaboken.userdatabase;

import yh.gulaboken.IUser;
import yh.gulaboken.IUserDatabase;

class HardcodedUserDatabase implements IUserDatabase {

    private final IUser[] users;

    HardcodedUserDatabase() {
        this.users = new IUser[] {
                new User("admin", "password")
        };
    }

    @Override
    public IUser authenticate(String username, String password) {
        return null;
    }
}
