package yh.gulaboken.filedatabase;

import org.junit.BeforeClass;
import org.junit.Test;
import yh.gulaboken.IContactDatabase;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Test FileContactDatabase
 */
public class FileContactDatabaseTest {
    private static final String TEST_FILE = "test.json";
    private static IContactDatabase database;

    @BeforeClass
    public static void loadDatabase() {
        var file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
        database = FileContactDatabaseFactory.create(file);
    }

    @Test
    public void create() {
        var person = new HashMap<String, String>();
        person.put("name", "Julia");
        person.put("surname", "Bobsdottir");
        person.put("phone", "+46154875, 0313303030");
        person.put("street", "Narrow-Alley 3");
        assertNotNull(database.create(person));
        person.clear();

        person.put("surname", "Freeman");
        person.put("phone", "1471414");
        assertNull(database.create(person));

        person.put("name", "Harry");
        person.put("street", "Never st. 55");
        person.put("city", "Far-town");
        person.put("zip", "12552");
        assertNotNull(database.create(person));
        person.clear();

        person.put("name", "Jonas");
        person.put("surname", "Fjärrman");
        person.put("age", "37");
        person.put("phone", "123456789");
        person.put("street", "Kanelvägen 1");
        person.put("city", "Spökstad");
        person.put("zip", "12345");
        assertNotNull(database.create(person));

        person.put("name", "Sam");
        person.put("surname", "Bobsson");
        person.put("phone", "+46144875, 0313399030");
        person.put("street", "Narrow-Alley 4");
        assertNotNull(database.create(person));
    }

    @Test
    public void read() {
        assertEquals("Julia", database.read(101).getName());
        assertEquals("Freeman", database.read(102).getSurname());
        assertEquals("Sam", database.read(104).getName());
    }

    @Test
    public void update() {
        var contact = database.read(101);
        contact.setAge("22");
        assertTrue(database.update(contact));
    }

    @Test
    public void delete() {
        assertTrue(database.delete(103)); // delete Jonas Fjärrman
    }

    @Test
    public void query() {
        assertEquals(0, database.queryByProperty("name", "foobar").size());
        assertEquals(1, database.queryByProperty("name", "harry").size());

        assertEquals(2, database.queryByKeywords(Arrays.asList("narrow")).size());
        assertEquals(1, database.queryByKeywords(Arrays.asList("narrow", "sam")).size());
    }
}