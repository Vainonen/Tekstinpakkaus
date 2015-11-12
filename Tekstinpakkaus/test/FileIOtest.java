
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tekstinpakkaus.FileIO;

public class FileIOtest {
    
    public FileIOtest() {
    }
    
    @Before
    public void setUp() throws IOException {
        File testfile = new File("testfile.txt");        
        testfile.createNewFile();
    }
    
    @After
    public void tearDown() {
        File testfile = new File("testfile.txt");
        testfile.delete();
    }

    @Test
    public void testRead() throws IOException {
        String s = "ääääääääääääääärjrtjrtjrtjrjrtjrtjrtjrtäjrtj";
        byte [] bytes = s.getBytes();
        FileIO.write("testfile.txt", bytes);
        byte [] test = FileIO.read("testfile.txt");
        String value = new String(test);
        assertTrue(value.equals(s));
    }
}