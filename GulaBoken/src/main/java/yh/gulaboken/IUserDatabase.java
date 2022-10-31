package yh.gulaboken;

public interface IUserDatabase {
    IUser authenticate(String username, String password);
}
