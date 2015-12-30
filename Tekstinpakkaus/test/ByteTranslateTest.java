
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tools.ByteTranslator;
import tools.Node;
import tools.Nodes;


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
        Nodes eightbit = bt.toBytes(255, 9);
        //palauttaa 011111111:stä ensimmäiset 8 bittiä
        byte first = (byte)eightbit.getFirst().getValue();
        assertTrue(first == (byte)127);
    }
        
    @Test
    public void testTranslatingFromBytes () {
        byte b = 0;
        byte c = 127; 
        //bt.fromBytes(b, 9);
        //bt.fromBytes(c, 9);
        int eightbit = bt.fromBytes(b, 9);
        System.out.println(eightbit);
        System.out.println((byte)128);
        //syötteinä 100000000 ja 000000000 palauttaa ensimmäiset 8 bittiä
        assertTrue(eightbit == (byte)128);
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
