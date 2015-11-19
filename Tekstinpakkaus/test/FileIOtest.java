
import FileIO.FileRead;
import FileIO.FileWrite;
import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileIOtest {
    
    public FileIOtest() {
    }
    
    @Before
    public void setUp() throws IOException {
   
    }
    
    @After
    public void tearDown() {
        File testfile = new File("testfile.txt");
        testfile.delete();
    }

    @Test
    public void testWriteAndRead() throws IOException {
        FileWrite writefile = new FileWrite("testfile.txt");  
        String s = "ääääääääääääääärjrtjrtjrtjrjrtjrtjrtjrtäjrtj";
        byte [] bytes = s.getBytes();
        for (byte b : bytes) {
            writefile.write(b);
        }
        FileRead readfile = new FileRead("testfile.txt");   
        byte [] test = readfile.read();
        String value = new String(test);
        assertTrue(value.equals(s));
    }
}