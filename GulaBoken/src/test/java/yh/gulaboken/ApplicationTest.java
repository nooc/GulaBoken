package yh.gulaboken;

import org.junit.BeforeClass;
import org.junit.Test;
import yh.gulaboken.filedatabase.FileContactDatabaseFactory;
import yh.gulaboken.menus.MainMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApplicationTest {
    private static final String[] COMMAND_SIMULATION = {
            "free hello",
            "add",
            "name Test",
            "surname User",
            "phone +461234567, 0313303030",
            "street Test Street 4",
            "apply",
            "free user",
            "update 101",
            "login admin secret",
            "update 101",
            "city Hometown",
            "zip 4345",
            "zip 34565",
            "apply",
            "back",
            "search name test",
            "back",
            "quit"
    };

    private static final String TEST_FILE = "test.json";

    @BeforeClass
    public static void loadDatabase() {
        var file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void main() {
        var app = new TestApplication(COMMAND_SIMULATION);
        var menu = new MainMenu(app);
        menu.show();
    }
}