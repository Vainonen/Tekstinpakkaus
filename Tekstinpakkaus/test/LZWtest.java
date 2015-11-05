
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tekstinpakkaus.LZW;

public class LZWtest {
    
    public LZWtest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void compressAndDecompress() throws UnsupportedEncodingException {
        String s = "ääääääääääääääärjrtjrtjrtjrjrtjrtjrtjrtäjrtj";
        byte [] b = s.getBytes();
        LZW pack = new LZW(b);
        ArrayList packResult = new ArrayList();
        packResult = pack.compress();
        LZW unpack = new LZW(packResult);
        ArrayList unpackResult = unpack.decompress();
        byte[] ba = new byte[unpackResult.size()];
        for(int i = 0; i<unpackResult.size(); i++){
            ba[i] = (byte)unpackResult.get(i);
        }
        String test = new String(ba);
        assertTrue(test.equals(s));
    }
    
}


