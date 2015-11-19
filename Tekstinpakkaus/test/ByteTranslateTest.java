
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tools.ByteTranslator;

public class ByteTranslateTest {
    
    private ByteTranslator bt;
    
    public ByteTranslateTest() {
    }
    
    @Before
    public void setUp()  {
        bt = new ByteTranslator();
    }
    
    @Test
    public void testTranslatingToBytes () {
        ArrayList eightbit = bt.toBytes(255, 9);
        //palauttaa 011111111:stä ensimmäiset 8 bittiä
        assertTrue(eightbit.contains((byte)127));
    }
        
    @Test
    public void testTranslatingFromBytes () {
        byte b = 0;
        byte c = 127; 
        bt.fromBytes(b, 9);
        int eightbit = bt.fromBytes(b, 9);
        //syötteinä 100000000 ja 000000000 palauttaa ensimmäiset 8 bittiä
        assertTrue(eightbit == 128);
    }
    
    @Test
    public void testTranslatingFromBytesWithTooSmallParameter () {
        byte b = 0;
        int eightbit = bt.fromBytes(b, 9);
        //9. bittiä ei ole vielä olemassa ja metodi palauttaa virhearvon
        assertTrue(eightbit == -1);
    }
    
    @Test
    public void testGetRemainder() {
        bt.toBytes(116, 9);
        bt.toBytes(104, 9);
        bt.toBytes(105, 9);
        bt.toBytes(115, 9);
        bt.toBytes(258, 9);
        bt.toBytes(256, 9);
        bt.toBytes(101, 9);
        int remains = bt.getRemainder();
        
        //jäljelle jää viimeinen tavu:
        assertTrue(remains == 101);
    }
}
